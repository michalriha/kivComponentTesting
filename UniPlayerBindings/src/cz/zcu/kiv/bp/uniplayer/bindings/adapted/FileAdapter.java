package cz.zcu.kiv.bp.uniplayer.bindings.adapted;

import java.io.File;

public class FileAdapter
{

	public static File parse(String name)
	{
		return new File(name);
	}
	
	public static String print(File file)
	{
		return file.toString();
	}
}
