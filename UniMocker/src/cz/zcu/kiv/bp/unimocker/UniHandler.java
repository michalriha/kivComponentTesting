package cz.zcu.kiv.bp.unimocker;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import cz.zcu.kiv.bp.unimocker.ArgumentScenarioTable.Posibility;

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
	private Map<Method, Object> paramLessMethods = new HashMap<>();
		
	/**
	 * Create handler with empty scenario, propagates both exceptions
	 * @param mockedClass class that is mocked by proxy using this handler instance
	 */
	public UniHandler(Class<?> mockedClass)
	{
		this(mockedClass, new HashMap<Method, Map<Object[], Object>>(), false, false);
	}
	
	/**
	 * Creates handler with given scenario. Propagates both exceptions
	 * @param mockedClass class that is mocked by proxy using this handler instance
	 * @param retVals mocking scenario
	 */
	public UniHandler(Class<?> mockedClass, Map<Method, Map<Object[], Object>> retVals)
	{
		this(mockedClass, retVals, false, false);
	}
	
	/**
	 * Create handler with given scenario and exception propagating flags.
	 * @param mockedClass mockedClass class that is mocked by proxy using this handler instance
	 * @param retVals mocking scenario
	 * @param ignoreUndefMethods flag whether or not to throw UndefinedMethodInvocationException
	 * @param ignoreUndefPossibs flag whether or not to throw UndefinedPossibilityException
	 */
	public UniHandler(
		Class<?> mockedClass,
		Map<Method, Map<Object[], Object>> retVals,
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
	 * @param retVals possibilities
	 */
	private void buildPossibilitiesMatrices(Map<Method, Map<Object[], Object>> retVals)
	{
		for (Entry<Method, Map<Object[], Object>> posib : retVals.entrySet())
		{
			int argsCount = posib.getKey().getParameterTypes().length;
			if (argsCount == 0)
			{ // method has no parameters
				// since parameterless method will have only one meaningful description,
				// we can save only the first possibility
				for (Object retunValue: posib.getValue().values())
				{
					_.paramLessMethods.put(posib.getKey(), retunValue);
					break;
				}
				continue;
			}
			
			ArgumentScenarioTable matrix = new ArgumentScenarioTable(posib.getKey().getParameterTypes());
			retMatrix.put(posib.getKey(), matrix);
			
			for (Entry<Object[], Object> row : posib.getValue().entrySet())
			{
				Posibility tmp = matrix.new Posibility();
				tmp.addAll(Arrays.asList(row.getKey()));
				tmp.returnValue = row.getValue();
				matrix.addPosibility(tmp);
			}
		}
	}

	/**
	 * @exception UndefinedMethodInvocationException
	 * @exception UndefinedPossibilityException
	 */
	@Override
	public Object invoke(
		Object proxy,
		Method method,
		Object[] args)
	throws Throwable
	{
		// return value
		Object ret = null;
		
		Method tmpMet = null;
		try
		{
			// check if the call is for mocked interface or for Object methods
			Class<?>[] paramTypes = method.getParameterTypes();
			tmpMet = _.mockedClass.getMethod(method.getName(), paramTypes);

			if (!_.retMatrix.containsKey(tmpMet) && !_.paramLessMethods.containsKey(tmpMet))
			{ // invoked method has not been described in scenario
				if(!_.ignoreUndefinedMethods)
				{ // flag ignoreUndefinedMethods has been cleared
					throw new UndefinedMethodInvocationException(
						String.format(
							"Undefined method: %s width args: %s of type (%s)",
							tmpMet,
							Arrays.deepToString(args),
							Arrays.toString(paramTypes)
						)
					);
				}
				else 
				{ // flag ignoreUndefinedMethods has been set
					return null;
				}
			}
			
			if (args != null)
			{
				ret = _.retMatrix.get(tmpMet).find(args);
			}
			else
			{
				ret = _.paramLessMethods.get(tmpMet);
			}
		}
		catch (UndefinedPossibilityException ex)
		{
			if (!_.ignoreUndefinedPossibilities)
			{
				throw ex;
			}
		}
		catch (NoSuchMethodException ignore)
		{ // method has not been found -> method is from Object
			// try to invoke required method on current object in case of Object methods
			// possible exception are populated
			ret = method.invoke(this, args);
		}
		
		return ret;
	}
}
