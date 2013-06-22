package cz.zcu.kiv.bp.unimocker;

import java.io.FileNotFoundException;

import org.eclipse.osgi.framework.console.CommandInterpreter;
import org.eclipse.osgi.framework.console.CommandProvider;

import cz.zcu.kiv.bp.unimocker.bindings.basics.InvalidFileException;

public class Commander implements CommandProvider
{
	public static final String
		DEFAULT_SCENARIO_FILE = "config/scenario.xml",
		CMD_LOAD = "load",
		CMD_MOCK = "mock",
		CMD_DIAG = "diag";

	private Commander _ = this;
	
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

	public void start()
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
	            catch (InvalidFileException e) { System.out.println("Unable to load file. Invalid format."); }
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
