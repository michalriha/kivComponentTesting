package cz.zcu.kiv.bp.unimocker;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class UniHandler implements InvocationHandler 
{	
	private Class<?> mockedClass;
	
	private Map<Method, Map<Object[], Object>> retVals = null;
	
	public UniHandler(Class<?> mockedClass)
	{
		this(mockedClass, new HashMap<Method, Map<Object[], Object>>());
	}
	
	public UniHandler(Class<?> mockedClass, Map<Method, Map<Object[], Object>> retVals)
	{
		this.mockedClass = mockedClass;
		this.retVals = retVals;
	}
	
	@Override
	public Object invoke(
		Object proxy,
		Method method,
		Object[] args)
	throws Throwable
	{

		Method tmpMet = null;
		try
		{
			tmpMet = this.mockedClass.getDeclaredMethod(method.getName(), method.getParameterTypes());
		}
		catch (NoSuchMethodException ignore) { }
		
		if(method.getReturnType() == void.class)
		{
			return null;
		}
		
		if (this.retVals.containsKey(method))
		{
			Object[] foundExpectation = null;
			
			System.out.println("Given argument: " + Arrays.toString(args));
			System.out.println("Available invocations:");
			for (Entry<Object[], Object> entry : this.retVals.get(method).entrySet())
			{
				System.out.println("  Expected argument: " + Arrays.toString(entry.getKey()));
				
				if (args.length != entry.getKey().length) throw new Exception("Wrong number of arguments!");
				
				System.out.println("    array test: " + Arrays.deepEquals(entry.getKey(), args));
				
				int i = 0;
				for (; i < args.length; i++)
				{
					Object expectedArgumen = entry.getKey()[i];
					Object actualArgument = args[i];
					
					// check object classes
					System.out.println("    equality of class: " + (expectedArgumen.getClass() == actualArgument.getClass()));
					if (expectedArgumen.getClass() != actualArgument.getClass()) break;
					
					// check object simple equality
					System.out.println("    equality of argument: " + (expectedArgumen == actualArgument));
					if (expectedArgumen != actualArgument)
					{ // if not equal but Comparable, try to compare
						
						@SuppressWarnings("unchecked")
						Comparable<Object> comparableArgument = (Comparable<Object>) expectedArgumen;
						
						// test equality for comparable objects
						if (expectedArgumen instanceof Comparable && comparableArgument.compareTo(actualArgument) == 0)
						{
							System.out.println("    equality by comparasion: " + comparableArgument.compareTo(actualArgument));
						}
						else
						{
							break;
						}
					}
				}
				if (i == args.length)
				{
					foundExpectation = entry.getKey();
					break;
				}
			}
			
			if (foundExpectation == null) throw new Exception("Undeclared expectation.");
			
			return this.retVals.get(method).get(foundExpectation);
		}
		else
		{
			if (tmpMet != null)
			{ // invoked method belong into mocked class, but has not been described
				throw new Exception("Return values registry does not contain return value.");
			}
			
			// try to invoke required method on current object in case of Object methods
			try
			{
				return method.invoke(this, args);
			}
			catch (java.lang.IllegalArgumentException ignore)
			{ // unable to resolve
				throw new Exception("Trhni si nohou");
			}
		}
	}
}
