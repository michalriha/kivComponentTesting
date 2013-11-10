package cz.zcu.kiv.bp.uniplayer;

/**
 * Describes the methods that player has to implement. Useful for possible mocker
 * implementation replacement with OSGi service provided by other bundle. 
 * @author Michal
 */
public interface IPlayer
{
	public String
		SIMULATION_PLAYER_EVENT_TOPIC_START = "simulation_action_started",
		SIMULATION_PLAYER_EVENT_TOPIC_FINISH = "simulation_action_finished",
		SIMULATION_PLAYER_EVENT_KEY = "command";
	
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
	 * @throws Throwable other exceptions that might occur
	 */
	abstract void loadFile(String fileName) throws Throwable;

	/**
	 * Prints diagnostics info about loaded file.
	 * Prints loaded scenario in unmarshalled format.
	 */
	abstract void diag();
}
