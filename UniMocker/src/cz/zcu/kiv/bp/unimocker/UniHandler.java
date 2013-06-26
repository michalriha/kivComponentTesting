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
	
//	private Map<Method, Map<Object[], Object>> retVals = null;
	
	/**
	 * possibility matrix (scenario)
	 */
	private Map<Method, ArgumentScenarioTable> retMatrix = new HashMap<Method, ArgumentScenarioTable>();
	
	/**
	 * list for pairs method=>return value for method without arguments
	 */
	private Map<Method, Object> paramLessMethods = new HashMap<>();
	
//	private enum EQUALITY
//	{
//		EQUAL, INEQUAL, NO_SURE
//	}
	
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
//		_.retVals = retVals;
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
				// since parameter less method will have only one meaningful description,
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
			{
				if(!_.ignoreUndefinedMethods)
				{
					throw new UndefinedMethodInvocationException(
						String.format(
							"Undefined method: %s width args: %s of type (%s)",
							tmpMet,
							Arrays.deepToString(args),
							Arrays.toString(paramTypes)
						)
					);
				}
				else return null;
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
			
//			if (method.getDeclaringClass() != _.mockedClass)
//			{
//				System.out.println("called method belongs into super interface: " + method.getDeclaringClass().getCanonicalName());
//				System.out.println(_.retMatrix);
//				System.out.println(_.paramLessMethods);
//				System.out.println(_.mockedClass.getMethod(method.getName(), method.getParameterTypes()));
//				return null;
//			}
			
			// try to invoke required method on current object in case of Object methods
			// possible exception are populated
			ret = method.invoke(this, args);
		}
		
		return ret;

			
////System.out.println(tmpMet + "\n" + Arrays.deepToString(args));
//			Class<?>[] methodsArgumentTypes = tmpMet.getParameterTypes();
//			
//			Map<Object[], Object> methodsScenario = _.getScenarioForMethod(tmpMet);
//			_.checkArgumentsCounts(tmpMet, methodsScenario);
//			
//			for (Object[] possibleArguments : methodsScenario.keySet())
//			{
//				int i = 0;
//				for (; i < methodsArgumentTypes.length; i++)
//				{
//					Object possibleArgument = possibleArguments[i];
//					Object givenArgument = args[i];
//					Class<?> argumentsType = methodsArgumentTypes[i];
//					
//					// if possible argument is AnyValue
//					if (possibleArgument instanceof TAnyValue)
//					{
//						// no testing is necessary, type equality is guaranteed with that
//						// the method signature is used as key to the scenario and is
//						// create based on the scenario file
////System.out.println("any value => no matching");
//						continue;
//					}
//
////System.out.println("possible: " + possibleArgument + " equals actual: " + givenArgument + " > " + possibleArgument.equals(givenArgument));
//					// simple equality test
//					if (possibleArgument.equals(givenArgument))
//					{
//						continue;
//					}
//										
//					// if argument type is Comparable, try to test by comparison
//					EQUALITY eq = _.checkIfComparable(possibleArgument, givenArgument, argumentsType);
//					if (eq == EQUALITY.EQUAL)
//					{
//						continue;
//					}
//					else if (eq == EQUALITY.INEQUAL)
//					{
//						break;
//					}
//
////System.out.println("array match: " + _.checkIfArrays(possibleArgument, givenArgument, argumentsType));
//					// if argument type is array, try to test whole array
//					eq = _.checkIfArrays(possibleArgument, givenArgument, argumentsType);
//					if (eq == EQUALITY.EQUAL)
//					{
//						continue;
//					}
//					if (eq == EQUALITY.INEQUAL)
//					{
//						break;
//					}
//					
//					// no test proved equality of argument therefore the invocation is not described
//					throw new Exception("Undeclared expectation.");
//				}
////System.out.println("i: " + i);
//				if (i == methodsArgumentTypes.length)
//				{ // all arguments successfully checked, test is not really necessary, because if
//				  // it does not match, it throws exception
//					
//					if (!methodsScenario.containsKey(possibleArguments))
//					{
//						throw new Exception("Return value not defined fo this method and arguments.\n" + tmpMet + "\n" + Arrays.deepToString(args));
//					}
//					
//					if (tmpMet.getReturnType() == void.class)
//					{
//						ret = null;
//					}
//					else
//					{
//						ret = methodsScenario.get(possibleArguments);
//					}
//					break;
//				}/*
//				else
//				{
//					throw new Exception("Return value not defined fo this method and arguments.\n" + tmpMet + "\n" + Arrays.deepToString(args));
//				}*/
//			}
//		}
//		catch (NoSuchMethodException ignore)
//		{ // method has not been found -> method is from Object
//			// try to invoke required method on current object in case of Object methods
//			try
//			{
//				//System.out.println("delegating call directly to proxy object. Method: " + method.getName());
//				ret = method.invoke(this, args);
//			}
//			catch (IllegalArgumentException ex)
//			{ // unable to resolve
//				throw new Exception("Undefined call.\n" + method);
//			}
//		}
////System.out.println("supposed rturn: " + ret);
//		return ret;
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		Object ret = null;
//		
//		Method tmpMet = null;
//		try
//		{
//			tmpMet = _.mockedClass.getDeclaredMethod(method.getName(), method.getParameterTypes());
//		}
//		catch (NoSuchMethodException ignore) { /* TODO: throw invalid method */}
//		
//		if (_.retVals.containsKey(method))
//		{
//			Object[] foundExpectation = null;
//			
//			System.out.println("Given argument: " + Arrays.deepToString(args));
//			System.out.println("Available invocations:");
//			for (Entry<Object[], Object> entry : _.retVals.get(method).entrySet())
//			{
//				if (args.length != entry.getKey().length) throw new Exception("Wrong number of arguments!");
//				
//				System.out.println("  Expected argument: " + Arrays.deepToString(entry.getKey()));
//				System.out.println("    array test: " + Arrays.deepEquals(entry.getKey(), args));
//				
//				int i = 0;
//				for (; i < args.length; i++)
//				{
//					Object expectedArgumen = entry.getKey()[i];
//					Object actualArgument = args[i];
//
//					if (expectedArgumen instanceof TAnyValue)
//					{ // match only type
//						
//						TAnyValue anyVal = (TAnyValue) expectedArgumen;
//						System.out.printf(
//							"Any Value parameter: testing type - %s%n",
//							(anyVal.getBaseType().baseClass() == actualArgument.getClass())
//						);
//						if (anyVal.getBaseType().baseClass() == actualArgument.getClass())
//						{ // current argument is instance of TAnyValue therefore only it's type needs to be checked
//							continue;
//						}
//						else
//						{ // although any value is allowed, types do not match
//							break;
//						}
//					}
//					
////					// check object classes
////					System.out.printf(
////						"    equality of classes [%s == %s]: %s%n",
////						expectedArgumen.getClass().getSimpleName(),
////						actualArgument.getClass().getSimpleName(),
////						(expectedArgumen.getClass() == actualArgument.getClass())
////					);
////					if (expectedArgumen.getClass() != actualArgument.getClass()) break;
//					
//					// check object simple equality
//					System.out.println("    equality of argument: " + (expectedArgumen == actualArgument));
//					if (expectedArgumen != actualArgument)
//					{ // if not equal but Comparable, try to compare
//						
//						@SuppressWarnings("unchecked")
//						Comparable<Object> comparableArgument = (Comparable<Object>) expectedArgumen;
//						
//						// test equality for comparable objects
//						if (expectedArgumen instanceof Comparable && comparableArgument.compareTo(actualArgument) == 0)
//						{
//							System.out.println("    equality by comparasion: " + comparableArgument.compareTo(actualArgument));
//						}
//						else
//						{
//							break;
//						}
//					}
//				}
//				if (i == args.length)
//				{
//					foundExpectation = entry.getKey();
//					break;
//				}
//			}
//			
//			if (foundExpectation == null) throw new Exception("Undeclared expectation.");
//			ret =  _.retVals.get(method).get(foundExpectation);
//		}
//		else
//		{
//			if (tmpMet != null)
//			{ // invoked method belong into mocked class, but has not been described
//				throw new Exception("Return values registry does not contain return value.");
//			}
//			
//			// try to invoke required method on current object in case of Object methods
//			try
//			{
//				System.out.println("delegating call directly to proxy object. Method: " + method.getName());
//				ret = method.invoke(this, args);
//			}
//			catch (java.lang.IllegalArgumentException ex)
//			{ // unable to resolve
//				throw new Exception("Trhni si nohou");
//			}
//		}
//
//		System.out.println("returning " + ret);
//		if (method.getReturnType() == void.class)
//		{
//			System.out.println("Called method does not return any value.");
//			ret = null;
//		}
//		System.out.println("call exited\n------------------------------------------------");
//		return ret;
	}

//	private EQUALITY checkIfArrays(
//		Object possibleArgument,
//		Object givenArgument,
//		Class<?> argumentsType)
//	{
//		EQUALITY eq2 = EQUALITY.INEQUAL;
//		if (argumentsType.isArray())
//		{
//			eq2 = Arrays.deepEquals((Object[]) possibleArgument, (Object[]) givenArgument) ? EQUALITY.EQUAL : EQUALITY.INEQUAL;						
//		}
//		return eq2;
//	}
//
//	private EQUALITY checkIfComparable(
//		Object possibleArgument,
//		Object givenArgument,
//		Class<?> argumentsType)
//	{
//		EQUALITY ret = EQUALITY.NO_SURE;
//		
//		// if the argument is instance of Comparable, use compareTo method
//		if (Comparable.class.isAssignableFrom(argumentsType))
//		{
//			@SuppressWarnings("unchecked")
//			Comparable<Object> comp1 = (Comparable<Object>) givenArgument;
//			if (comp1.compareTo(possibleArgument) != 0)
//			{ // argument are different
//				ret = EQUALITY.INEQUAL;
//			}
//			else
//			{ // arguments are equal by comparison
//				ret = EQUALITY.EQUAL;
//			}
//		}
//		
//		return ret;
//	}
//
//	private void checkArgumentsCounts(
//		Method tmpMet,
//		Map<Object[], Object> methodsScenario)
//	throws Exception
//	{
//		Class<?>[] methodsArgumentTypes = tmpMet.getParameterTypes();
//		for (Object[] possibleArguments : methodsScenario.keySet())
//		{
//			if (possibleArguments.length != methodsArgumentTypes.length)
//			{
//				throw new Exception("Invalid number of preprogramed arguments.\n" + tmpMet + "\n" + Arrays.deepToString(possibleArguments));
//			}
//		}
//	}
//
//	private Map<Object[], Object> getScenarioForMethod(Method method)
//	throws Exception
//	{
//		if (!_.retVals.containsKey(method))
//		{
//			throw new Exception("Return values registry does not contain scenario for this method.\n" + method);
//		}
//		return _.retVals.get(method);
//	}
}
