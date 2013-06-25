package cz.zcu.kiv.bp.unimocker;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import cz.zcu.kiv.bp.unimocker.ArgumentScenarioTable.Posibility;

public class UniHandler implements InvocationHandler 
{	
	private Class<?> mockedClass;
	
	private Map<Method, Map<Object[], Object>> retVals = null;
	
	private Map<Method, ArgumentScenarioTable> retMatrix = new HashMap<Method, ArgumentScenarioTable>();
	
	private Map<Method, Object> paramLessMethods = new HashMap<>();
	
	private enum EQUALITY
	{
		EQUAL, INEQUAL, NO_SURE
	}
	
	public UniHandler(Class<?> mockedClass)
	{
		this(mockedClass, new HashMap<Method, Map<Object[], Object>>());
	}
	
	public UniHandler(Class<?> mockedClass, Map<Method, Map<Object[], Object>> retVals)
	{
		this.mockedClass = mockedClass;
		this.retVals = retVals;
		
		for (Entry<Method, Map<Object[], Object>> posib : retVals.entrySet())
		{
			int argsCount = posib.getKey().getParameterTypes().length;
			if (argsCount == 0)
			{ // method has no parameters
				// since parameter less method will have only one meaningful description,
				// we can save only the first possibility
				for (Object retunValue: posib.getValue().values())
				{
					this.paramLessMethods.put(posib.getKey(), retunValue);
					break;
				}
				continue;
			}
			
			ArgumentScenarioTable matrix = new ArgumentScenarioTable(posib.getKey().getParameterTypes());
			retMatrix.put(posib.getKey(), matrix);
			
			for (Entry<Object[], Object> row : posib.getValue().entrySet())
			{
				Posibility tmp = matrix.new Posibility();
				tmp.returnValue = row.getValue();
				for (Object o : row.getKey())
				{
					tmp.add(o);
				}
				matrix.addPosibility(tmp);
			}
		}
	}
	
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
			// check if the call is for mocked interface or for Object methods			160 161 162 163 164 165 166
			Class<?>[] paramTypes = method.getParameterTypes();
			tmpMet = this.mockedClass.getMethod(method.getName(), paramTypes);

			if (!this.retMatrix.containsKey(tmpMet) && !this.paramLessMethods.containsKey(tmpMet))
			{
				throw new Exception("Undefined method: " + tmpMet + " width args: " + Arrays.deepToString(args) + " " + Arrays.toString(paramTypes));
			}
			
			if (args != null) ret = this.retMatrix.get(tmpMet).find(args);
			else ret = this.paramLessMethods.get(tmpMet);
		}
		catch (NoSuchMethodException ignore)
		{ // method has not been found -> method is from Object
			
//			if (method.getDeclaringClass() != this.mockedClass)
//			{
//				System.out.println("called method belongs into super interface: " + method.getDeclaringClass().getCanonicalName());
//				System.out.println(this.retMatrix);
//				System.out.println(this.paramLessMethods);
//				System.out.println(this.mockedClass.getMethod(method.getName(), method.getParameterTypes()));
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
//			Map<Object[], Object> methodsScenario = this.getScenarioForMethod(tmpMet);
//			this.checkArgumentsCounts(tmpMet, methodsScenario);
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
//					EQUALITY eq = this.checkIfComparable(possibleArgument, givenArgument, argumentsType);
//					if (eq == EQUALITY.EQUAL)
//					{
//						continue;
//					}
//					else if (eq == EQUALITY.INEQUAL)
//					{
//						break;
//					}
//
////System.out.println("array match: " + this.checkIfArrays(possibleArgument, givenArgument, argumentsType));
//					// if argument type is array, try to test whole array
//					eq = this.checkIfArrays(possibleArgument, givenArgument, argumentsType);
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
//			tmpMet = this.mockedClass.getDeclaredMethod(method.getName(), method.getParameterTypes());
//		}
//		catch (NoSuchMethodException ignore) { /* TODO: throw invalid method */}
//		
//		if (this.retVals.containsKey(method))
//		{
//			Object[] foundExpectation = null;
//			
//			System.out.println("Given argument: " + Arrays.deepToString(args));
//			System.out.println("Available invocations:");
//			for (Entry<Object[], Object> entry : this.retVals.get(method).entrySet())
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
//			ret =  this.retVals.get(method).get(foundExpectation);
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
//		if (!this.retVals.containsKey(method))
//		{
//			throw new Exception("Return values registry does not contain scenario for this method.\n" + method);
//		}
//		return this.retVals.get(method);
//	}
}
