package dummies.types.factories;

import dummies.types.Type3;

public class Type3Factory
{
	public static dummies.types.Type3 newInstance(Object o)
	{
		System.out.printf("Creating instance of interface Type3.%n");
		return new Type3()
		{
			{
				System.out.println("Instance of Type3 created");
			}
		};
	}
}
