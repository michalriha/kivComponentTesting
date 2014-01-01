package dummies.types;

public class Type2
{
	private Type2() {}
	
	public static Type2 newInstance()
	{
		System.out.println("instantiation by empty method");
		return new Type2();
	}
	
	public static Type2 newInstance(Integer i) throws InstantiationException
	{
		System.out.println("instantiation by single int arg method");
		return new Type2();
	}
	
	public static Type2 newInstance(Integer i, int j)
	{
		System.out.println("instantiation by dual int arg method " + i + " " + j);
		return new Type2();
	}
	
	public static Type2 newInstance(String str)
	{
		System.out.println("instantiation by single string arg method " + str);
		return new Type2();
	}
}
