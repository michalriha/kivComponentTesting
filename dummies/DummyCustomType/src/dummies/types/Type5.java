package dummies.types;

import java.io.File;
import java.util.ArrayList;

public class Type5 extends ArrayList<File>
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static Type5 newInstance(String... paths)
	{
		Type5 ret = new Type5();
		for (String path : paths)
		{
			ret.add(new File(path));
		}
		
		return ret;
	}
}
