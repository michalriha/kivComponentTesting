package dummies.types.factories;

public class Type1
{
	public Type1()
	{
		System.out.println("creating new instantce of factory object");
	}
	
	public static dummies.types.Type1 newInstance(String value)
	{
		System.out.println("creating new instance of Type1 with argument " + value + " by static method");
		return new dummies.types.Type1();
	}
	
	public dummies.types.Type1 newInstance2(String value)
	{
		System.out.println("creating new instance of Type1 with argument " + value + " by instance method");
		return new dummies.types.Type1();
	}
	
	public String newString(String arg)
	{
		return new String(arg);
	}
}
