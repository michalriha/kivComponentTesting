package cz.zcu.kiv.bp.uniplayer;

import java.io.FileNotFoundException;
import javax.xml.bind.JAXBException;

import org.eclipse.osgi.framework.console.CommandInterpreter;
import org.eclipse.osgi.framework.console.CommandProvider;
import org.xml.sax.SAXException;

/**
 * Implements simple user interface for controlling UniPlayer.
 * Implements CommandProvider - command prefixed with "up".<br>
 * <ul>
 *  <li>up load file.xml - loads scenario from file file.xml</li>
 *  <li>up play - starts or resumes iterating over loaded scenario and executing each action</li>
 *  <li>up stop - stops currently playing scenario</li>
 * </ul> 
 * @author Michal
 */
public class Commander implements CommandProvider
{
	public static final String DEFAULT_SCENARIO_FILE = "player_scenario.xml";
    
    private Commander _ = this;
    
    /**
     * user player implementation
     */
    private IPlayer plr;
    
    /**
     * Sets used player
     * @param plr IPlayer implementation
     */
    public void setPlr(IPlayer plr)
    {
        _.plr = plr;
    }
    
    /**
     * bundles init method - prints help
     */
    public void init()
    {
		System.out.println("\n" + _.getHelp());
    }
    
    /**
     * CommandProvider processes console command
     * @param ci instance of {@link CommandInterpreter}
     * @throws FileNotFoundException
     * @throws JAXBException
     * @throws SAXException
     */
    public void _up(CommandInterpreter ci)
    throws FileNotFoundException, JAXBException, SAXException
    {
        String cmd = ci.nextArgument();
            
        switch (cmd.toUpperCase())
        {
            case ("LOAD"):
                String fileName = ci.nextArgument();
                if (fileName == null)
                {
                    fileName = Commander.DEFAULT_SCENARIO_FILE;
                    System.out.printf("Loading scenario from default location config/scenario.xml ... %n");
                }
                try { _.plr.loadFile(fileName); }
                catch (FileNotFoundException e) { System.out.println(e.getLocalizedMessage()); }
                catch (Throwable e) { e.printStackTrace(); }
                
                break;
                
            case ("PLAY"):
                try
                {
                    Thread thr = new Thread(
                        new Runnable()
                        {
                            @Override
                            public void run()
                            {
                                try
                                {
                                	System.out.println();
                                    _.plr.play();
                                }
                                catch (Exception e)
                                {
                                    System.out.println("Scenario replay error!");
                                    e.printStackTrace();
                                }                                
                            }
                        }
                    );
                    thr.start();
                }
                catch (IllegalThreadStateException e)
                {
                    System.out.println(e.getLocalizedMessage());
                }
            
                break;
            case ("STOP"):
                _.plr.stop();
                break;
                
            case ("DIAG"):
                _.plr.diag();
                break;
                
            default:
                System.out.printf("Unknown command!%n");
                _.getHelp();
        }
    }
    
    /**
     * CommandProvider interface implementation. Prints help to console.
     */
    @Override
    public String getHelp()
    {
        StringBuilder sb = new StringBuilder(String.format("UniPlayer help:%n"));
        sb.append(String.format(
            "up load [filename]%n" +
            "Loads simulation scenario [filename]. Simple up load loads scenario from default location config/scenario.xml.%n%n"
        ));
        sb.append(String.format(
            "up play%n" +
            "Plays the scenario previously loaded.%n%n"
        ));
        sb.append(String.format(
            "up diag%n" +
            "Calls the diag on player. Prints diagnostics info - loaded scenario in marshaled form.%n"
        ));
        return sb.toString();
    }

}
