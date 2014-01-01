package dummies.types.factories;

import java.util.Arrays;
import java.util.LinkedList;

import dummies.types.Type4;

public class Type4Factory
{
	public static Type4 newInstance(Short[] o1, LinkedList<Short> o2)
	{
		System.out.printf("Creating new instance of abstract class Type4 with arguments %s.%n", Arrays.toString(o1) + o2.toString());
		return new Type4()
		{
			{
				System.out.println("New instance of abstract type Type4 created.");
			}
			
			@Override
			public String toString()
			{
				return "Blboune";
			}
		};
	}
}
