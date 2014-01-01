package dummies.injector;

import java.io.File;
import java.util.List;

public class Observer implements IObserver
{
	public static void executeAction(List<File> files, String str1, String str2)
	{
		System.out.println("Observed cal &&&&&&&&&&&&&&&#############\n");
		System.out.println("\targ1: " + files.toString());
		System.out.println("\targ2: " + str1);
		System.out.println("\targ3: " + str2);
	}
	
	public static String executeAction()
	{
		return "prdka";
	}
	
	public static String executeAction2()
	{
		return "Prdel 4.5";
	}

	@Override
	public String getName()
	{
		return "Value overriden by injected method.";
	}
}
