package cz.zcu.kiv.bp.uniplayer;

public interface IPlayer
{
	/**
	 * Starts currently loaded simulation.
	 * @throws Exception
	 */
    abstract void play() throws Exception;

    /**
     * Stops currently running simulation.
     */
    abstract void stop();
    
    /**
	 * Loads scenario file and tries to unmarshall.
	 * @param fileName file to load
	 * @throws JAXBException when unmarshalling failed
	 * @throws FileNotFoundException when unable to read given file
	 * @throws InvalidFileException when file format is invalid
	 * @throws Throwable other exceptions that might occur
	 */
	abstract void loadFile(String fileName) throws Throwable;

	/**
	 * Prints diagnostics info about loaded file.
	 * Prints loaded scenario in unmarshalled format.
	 */
	abstract void diag();
}
