package dummies.types;

public class Type1
{

	public Type1()
	{
		super();
		System.out.println("instantiation by empty constructor");
	}
	
	public Type1(String nazdar)
	{
		System.out.println("instantiation by constructor with argument " + nazdar);
	}
	
	public Type1(Integer i)
	{
		System.out.println("instantiation by construct with argument " + i);
	}
}
