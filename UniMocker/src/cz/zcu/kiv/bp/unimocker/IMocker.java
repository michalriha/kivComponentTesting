package cz.zcu.kiv.bp.unimocker;

/**
 * Describes the methods that mocker has to implement. Useful for possible mocker
 * implementation replacement with OSGi service provided by other bundle. 
 * @author Michal
 */
public interface IMocker
{
	/**
	 * Register mockups created according to loaded scenario file. 
	 */
	abstract void mock();
	
	/**
	 * Loads scenario file and tries to unmarshall.
	 * @param fileName file to load
	 * @throws JAXBException when unmarshalling failed
	 * @throws FileNotFoundException when unable to read given file
	 * @throws Throwable other exceptions that might occur
	 */
	abstract void loadFile(String fileName) throws Throwable;
	
	/**
	 * Prints diagnostics info about loaded file.
	 * Prints loaded scenario in unmarshalled format.
	 */
	abstract void diag();
}
