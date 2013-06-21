package cz.zcu.kiv.bp.unimocker;

public interface IMocker
{
	/**
	 * Register mockups created according to loaded scenario file. 
	 */
	void mock();
	
	/**
	 * Loads scenario file and tries to unmarshall.
	 * @param fileName file to load
	 * @throws JAXBException when unmarshalling failed
	 * @throws FileNotFoundException when unable to read given file
	 * @throws InvalidFileException when file format is invalid
	 * @throws Throwable other exceptions that might occur
	 */
	void loadFile(String fileName) throws Throwable;
	
	/**
	 * Prints diagnostics info about loaded file.
	 * Prints loaded scenario in unmarshalled format.
	 */
	void diag();
}
