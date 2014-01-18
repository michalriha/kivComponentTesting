package cz.zcu.kiv.bp.unimocker;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.reflect.MethodUtils;
import org.osgi.framework.Bundle;

import cz.zcu.kiv.bp.probe.IProbe;
import cz.zcu.kiv.bp.probe.NoSuchBundleException;
import cz.zcu.kiv.bp.unimocker.ArgumentScenarioTable.Posibility;
import cz.zcu.kiv.bp.unimocker.bindings.TCodeInjection;
import cz.zcu.kiv.bp.unimocker.bindings.adapted.Invocation;

/**
 * Implements InvocationHandler. This class is universal invocation handler pro proxy objects used as mockup.
 * When some method has been invoked, it checks whether the invoked method and actual invocation arguments
 * have been described in mocking scenario. If not throws corresponding exception. If they were, return the value
 * associated with given arguments as described in mocking scenario.
 * @author Michal
 */
public class UniHandler implements InvocationHandler 
{	
	private UniHandler _ = this;
	
	/**
	 * class that is mocked by proxy using this handler instance
	 */
	private Class<?> mockedClass;
	
	/**
	 * flag whether or not to throw UndefinedMethodInvocationException
	 */
	private boolean ignoreUndefinedMethods;
	
	/**
	 * flag whether or not to throw UndefinedPossibilityException
	 */
	private boolean ignoreUndefinedPossibilities;
	
	/**
	 * possibility matrix (scenario)
	 */
	private Map<Method, ArgumentScenarioTable> retMatrix = new HashMap<Method, ArgumentScenarioTable>();
	
	/**
	 * list for pairs method=>return value for method without arguments
	 */
	private Map<Method, Invocation /*Object*/> paramLessMethods = new HashMap<>();
	
	/**
	 * Map of injected executions for defined methods. 
	 */
	private Map<Method, TCodeInjection> injectedCode;

	/**
	 * OSGi environmental probe
	 */
	private IProbe envProbe = null;
	
	/**
	 * Sets the envProbe property.
	 * @param envProbe instance of {@link IProbe}
	 */
	public void setEnvProbe(IProbe envProbe)
	{
		_.envProbe = envProbe;
	}
	
	/**
	 * Sets the injectedCode property.
	 * @param injectedCode instance of {@link Map}<{@link Method}, {@link TCodeInjection}>
	 */
	public void setInjectedCode(Map<Method, TCodeInjection> injectedCode)
	{
		_.injectedCode = injectedCode;
	}
	
	/**
	 * Create handler with empty scenario, propagates both exceptions
	 * @param mockedClass class that is mocked by proxy using this handler instance
	 */
	public UniHandler(Class<?> mockedClass)
	{
		this(mockedClass, new HashMap<Method, Map<Object[], Invocation /*Object*/>>(), false, false);
	}
	
	/**
	 * Creates handler with given scenario. Propagates both exceptions
	 * @param mockedClass class that is mocked by proxy using this handler instance
	 * @param retVals mocking scenario
	 */
	public UniHandler(Class<?> mockedClass, Map<Method, Map<Object[], Invocation /*Object*/>> retVals)
	{
		this(mockedClass, retVals, false, false);
	}
	
	/**
	 * Create handler with given scenario and exception propagating flags.
	 * @param mockedClass mockedClass class that is mocked by proxy using this handler instance
	 * @param retVals mocking scenario
	 * @param ignoreUndefMethods flag whether or not to throw {@link UndefinedMethodInvocationException}
	 * @param ignoreUndefPossibs flag whether or not to throw {@link UndefinedPossibilityException}
	 */
	public UniHandler(
		Class<?> mockedClass,
		Map<Method, Map<Object[], Invocation /*Object*/>> retVals,
		boolean ignoreUndefMethods,
		boolean ignoreUndefPossibs)
	{
		_.mockedClass = mockedClass;
		_.ignoreUndefinedMethods = ignoreUndefMethods;
		_.ignoreUndefinedPossibilities = ignoreUndefPossibs;
		
		_.buildPossibilitiesMatrices(retVals);
	}

	/**
	 * Creates possibility matrix for methods with parameters and fills simple
	 * list of pairs method => return value for parameterless methods.
	 * @param retVals matrix possibilities and it's respective return values
	 */
	private void buildPossibilitiesMatrices(Map<Method, Map<Object[], Invocation /*Object*/>> retVals)
	{
		for (Entry<Method, Map<Object[], Invocation /*Object*/>> posib : retVals.entrySet())
		{
			int argsCount = posib.getKey().getParameterTypes().length;
			if (argsCount == 0)
			{ // method has no parameters
				// since parameterless method will have only one meaningful description,
				// we can save only the first possibility
				for (Invocation invocationDescription /*Object retunValue*/: posib.getValue().values())
				{
					_.paramLessMethods.put(posib.getKey(), invocationDescription /*retunValue*/);
					break;
				}
				continue;
			}
			
			ArgumentScenarioTable matrix = new ArgumentScenarioTable(posib.getKey().getParameterTypes());
			retMatrix.put(posib.getKey(), matrix);
			
			for (Entry<Object[], Invocation /*Object*/> row : posib.getValue().entrySet())
			{
				Posibility tmp = matrix.new Posibility();
				tmp.addAll(Arrays.asList(row.getKey()));
				tmp.invocationDescription = row.getValue();
				matrix.addPosibility(tmp);
			}
		}
	}
	
	/**
	 * @exception {@link UndefinedMethodInvocationException}
	 * @exception {@link UndefinedPossibilityException}
	 */
	public Object invoke(
		Object proxy,
		Method actuallyInvokedMethod,
		Object[] args)
	throws Throwable
	{
		Object ret = null;
		
		String methodName = actuallyInvokedMethod.getName();
		Class<?>[] parameterTypes = actuallyInvokedMethod.getParameterTypes();
		Method mockedInterfaceMemberMethod = MethodUtils.getAccessibleMethod(_.mockedClass, methodName, parameterTypes);
		
		if (mockedInterfaceMemberMethod == null)
		{ // invoked method is neither direct member nor inherited member of the mocked interface
		  // method belongs to some part of the tree of Proxy class or Object class
			// invoke method directly on this object
			return actuallyInvokedMethod.invoke(this, args);
		}
		
		// mockedInterfaceMemberMethod isn't null due to previous condition
		if (_.injectedCode.containsKey(mockedInterfaceMemberMethod))
		{ // injection for current method has been specified,
		  // since it is not possible (as defined in UniMockers XML Schema) to inject code without
		  // describing at least one invocation possibility, meeting this condition also implicitly
		  // means that the possibility matrix contains mockedInterfaceMemberMethod as key
			TCodeInjection injectedCode = _.injectedCode.get(mockedInterfaceMemberMethod);
			if (injectedCode.isCall())
			{ // injected code is call
				Object tmpReturn = null;
				boolean overridesReturnValues = false;
				TCodeInjection.Call call = injectedCode.getCall();
				if (call.isStatic())
				{ // injected code is static call
					tmpReturn = _.invokeCall(call.getStatic(), actuallyInvokedMethod, args);
					overridesReturnValues = call.getStatic().getMethod().isOverridesReturnValues();
				}
				else if (call.isService())
				{ // injected code is provided by OSGi service
					tmpReturn = _.invokeCall(call.getService(), actuallyInvokedMethod, args);
					overridesReturnValues = call.getService().getMethod().isOverridesReturnValues();
				}
				
				if (overridesReturnValues && tmpReturn != void.class)
				{ // injection overrides return value
					return tmpReturn;
				}
			}
		}
		else if (!_.retMatrix.containsKey(mockedInterfaceMemberMethod) && !_.paramLessMethods.containsKey(mockedInterfaceMemberMethod))
		{ // invoked method has not been described in scenario
			if(!_.ignoreUndefinedMethods)
			{ // flag ignoreUndefinedMethods has been cleared
				throw new UndefinedMethodInvocationException(
					String.format(
						"Undefined method: %s width args: %s of type (%s)",
						mockedInterfaceMemberMethod,
						Arrays.deepToString(args),
						Arrays.toString(parameterTypes)
					)
				);
			}
			else 
			{ // flag ignoreUndefinedMethods has been set
				System.out.println("undefined method: " + actuallyInvokedMethod);
				return null;
			}
		}
		
		try
		{
			// find defined return value
			ret = args != null ? _.retMatrix.get(mockedInterfaceMemberMethod).find(args).getReturnValue() : _.paramLessMethods.get(mockedInterfaceMemberMethod).getReturnValue();
			Invocation inv = args != null ? _.retMatrix.get(mockedInterfaceMemberMethod).find(args) : _.paramLessMethods.get(mockedInterfaceMemberMethod);
			long invLimit = inv.getCountLimit();
			if (inv.getInvocationCount() == invLimit)
			{
				throw new InvocationCountLimitReachedException(String.format("Current invocation reached it %d allowed invocations.", invLimit));
			}
			inv.incInvocationCount();
			ret = inv.getReturnValue().getValue();
		}
		catch (UndefinedPossibilityException ex)
		{
			if (!_.ignoreUndefinedPossibilities)
			{
				throw ex;
			}
			System.out.println("Undefined incovation possibility. Ignore flag set.");
			System.out.println("actuallyInvokedMethod: " + actuallyInvokedMethod + " with arguments: " + Arrays.deepToString(args));
		}
		
		return ret;
	}

	/**
	 * Invokes the injected method on service instance.
	 * @param injectedService service providing the method to be invoked
	 * @param mockedMethod actually invoked method
	 * @param args argument given to invocation
	 * @return return value of the injected method
	 */
	private Object invokeCall(
		TCodeInjection.Call.Service injectedService,
		Method mockedMethod,
		Object[] args)
	{
		Object ret = null;
		
		TCodeInjection.Call.Service.Method injectedMethodDescription = injectedService.getMethod();
		String injectedServiceName = injectedService.getName();
		
		Object serviceInstance = _.envProbe.getServiceInstance(injectedServiceName);
		if (serviceInstance == null)
		{
			System.out.printf("Service %s has not been found! Skipping injected call.%n", injectedServiceName);
		}
		else
		{
			String methodName = injectedMethodDescription.getName();
			try
			{
				ret = MethodUtils.invokeMethod(serviceInstance, methodName, args, mockedMethod.getParameterTypes());

				Method injectedMethod = MethodUtils.getAccessibleMethod(serviceInstance.getClass(), methodName, mockedMethod.getParameterTypes());
				_.checkReturnTypes(mockedMethod, injectedMethod);
				if (injectedMethod.getReturnType() == void.class)
				{
					ret = void.class;
				}
			}
			catch (NoSuchMethodException ignored)
			{
				System.out.printf("Service %s does not have method %s. Skipping injected call.%n", injectedServiceName, methodName);
			}
			catch (IllegalAccessException | InvocationTargetException ex)
			{
				System.out.printf("Invocation of injected call was not succesfull.%n");
				ex.printStackTrace();
			}
		}
		
		return ret;
	}

	/**
	 * Invokes the injected static method.
	 * @param injectedCall class with the static method to be invoked
	 * @param mockedMethod name of the method to be invoked
	 * @param args invocation arguments
	 * @return return value of the injected method
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws ClassCastException
	 */
	private Object invokeCall(
		TCodeInjection.Call.Static injectedCall,
		Method mockedMethod,
		Object[] args)
	throws IllegalAccessException, InvocationTargetException, ClassCastException
	{
		Object ret = null;
		
		TCodeInjection.Call.Static.Bundle bndl = injectedCall.getBundle();
		TCodeInjection.Call.Static.Method injectedMethodDescription = injectedCall.getMethod();
		String bundleKey = bndl.getSymbolicName() + ":" + bndl.getVersion();
		Bundle foundBundle;
		try
		{
			foundBundle = _.envProbe.findBundle(bundleKey);
			String classToFind = injectedMethodDescription.getName().substring(0, injectedMethodDescription.getName().lastIndexOf("."));
			String methodToFind = injectedMethodDescription.getName().substring(injectedMethodDescription.getName().lastIndexOf(".") + 1);
			try
			{
				Class<?> foundClass = _.envProbe.findClassInBundle(foundBundle, classToFind);
				
				Method injectedMethod = MethodUtils.getMatchingAccessibleMethod(
					foundClass,
					methodToFind,
					mockedMethod.getParameterTypes()
				);
				
				if (injectedMethod == null)
				{
					throw new NoSuchMethodException();
				}
				if (!Modifier.isStatic(injectedMethod.getModifiers()))
				{
					throw new NoSuchMethodException();
				}
				
				ret = injectedMethod.invoke(null, args);
				
				_.checkReturnTypes(mockedMethod, injectedMethod);
				if (injectedMethod.getReturnType() == void.class)
				{
					ret = void.class;
				}
			}
			catch (ClassNotFoundException ignored)
			{
				System.out.printf("Class %s has not been found! Skipping injected call.%n", classToFind);
			}
			catch (NoSuchMethodException ignored)
			{
				System.out.printf("Class %s does not have method %s similar to : %s. Skipping injected call.%n", classToFind, injectedMethodDescription.getName(), mockedMethod);
				ignored.printStackTrace();
			}
			catch (IllegalAccessException | InvocationTargetException ex)
			{
				System.out.printf("Invocation of injected static call was not succesfull.%n");
				ex.printStackTrace();
			}
		}
		catch (NoSuchBundleException e)
		{ // bundle described in scenario has not been found in current context
			System.out.printf("Bundle %s has not been found! Skipping bundle.%n", bundleKey);
		}
		
		return ret;
	}

	/**
	 * Check if the return type is compatible with the return type the injected method. 
	 * @param originalMethod
	 * @param injectedMethod
	 * @throws ClassCastException
	 */
	private void checkReturnTypes(Method originalMethod, Method injectedMethod) throws ClassCastException
	{
		if (!originalMethod.getReturnType().isAssignableFrom(injectedMethod.getReturnType()))
		{
			throw new ClassCastException(
				String.format(
					"Injected method returned value of inasignable type!%n\tinvoked: %s%n\tinjected: %s%n",
					originalMethod,
					injectedMethod
				)
			);
		}
	}
}
