package cz.zcu.kiv.bp.unimocker;

import java.io.FileNotFoundException;

import org.eclipse.osgi.framework.console.CommandInterpreter;
import org.eclipse.osgi.framework.console.CommandProvider;

/**
 * Implements simple user interface for controlling UniMocker.
 * Implements CommandProvider - command prefixed with "um".<br>
 * <ul>
 *  <li>um load file.xml - loads scenario from file file.xml</li>
 *  <li>um mock - makes IMocker to create mockups and registers them as OSGi services</li>
 * </ul> 
 * @author Michal
 */
public class Commander implements CommandProvider
{
	public static final String
		DEFAULT_SCENARIO_FILE = "mocker_scenario.xml",
		CMD_LOAD = "load",
		CMD_MOCK = "mock",
		CMD_DIAG = "diag";

	private Commander _ = this;
	
	/**
	 * used mocker implementation
	 */
	private IMocker mocker;
	
	/**
	 * @return the mocker
	 */
	public IMocker getMocker() {
		return mocker;
	}

	/**
	 * @param mocker implementation of IMocker to set
	 */
	public void setMocker(IMocker mocker) {
		this.mocker = mocker;
	}

	/**
	 * bundle init method - print help
	 */
	public void init()
	{
		System.out.println("\n" + _.getHelp());
	}
	
	
	/**
	 * Implementation of CommandProvider - processes commands.
	 * @param ci
	 */
	public void _um(CommandInterpreter ci)
	{
		String cmd = ci.nextArgument();
		
		switch (cmd.toLowerCase())
		{
	        case (CMD_LOAD):
	            String fileName = ci.nextArgument();
	            if (fileName == null)
	            {
	                fileName = Commander.DEFAULT_SCENARIO_FILE;
	                System.out.printf("Loading scenario from default location config/scenario.xml ... %n");
	            }
	            try { _.mocker.loadFile(fileName); }
	            catch (FileNotFoundException e) { System.out.println(e.getLocalizedMessage()); }
	            catch (Throwable e) { e.printStackTrace(); }
	            
	            break;
	        
	        case (CMD_MOCK):
	        	try
		        {
		        	_.mocker.mock();
		        } catch (Exception ex) { ex.printStackTrace(); }
	        	break;
	            
            case (CMD_DIAG):
                _.mocker.diag();
                break;
                
            default:
                System.out.printf("Unknown command!%n");
                _.getHelp();
		}
	}
	
	/**
	 * Prints help for UniMocker bundle commands.
	 */
	@Override
	public String getHelp()
	{
		String cmdId = "um";
        StringBuilder sb = new StringBuilder(String.format("UniMocker help:%n"));
        sb.append(String.format(
            "%s %s [filename]%n" +
            "Loads mocking scenario [filename]. Simple %s load loads scenario from default location config/scenario.xml.%n%n",
            cmdId, CMD_LOAD, cmdId
        ));
        sb.append(String.format(
            "%s %s%n" +
            "Creates and registers mockup based on information from loaded scenario file.%n%n",
            cmdId, CMD_MOCK
        ));
        sb.append(String.format(
            "%s %s%n" +
            "Calls the diagnostics on mocker. Prints diagnostics info - loaded scenario in marshaled form.%n",
            cmdId, CMD_DIAG
        ));
        return sb.toString();
	}

}
