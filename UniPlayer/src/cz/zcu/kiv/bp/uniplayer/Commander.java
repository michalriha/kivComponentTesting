package cz.zcu.kiv.bp.uniplayer;

import java.io.FileNotFoundException;
import javax.xml.bind.JAXBException;

import org.eclipse.osgi.framework.console.CommandInterpreter;
import org.eclipse.osgi.framework.console.CommandProvider;
import org.xml.sax.SAXException;

import cz.zcu.kiv.bp.uniplayer.bindings.IScenario;
import cz.zcu.kiv.bp.uniplayer.bindings.InvalidFileException;

/**
 * Component tester implementation
 * @author Michal
 *
 */
public class Commander implements CommandProvider
{
	public static final String
    	DEFAULT_SCENARIO_FILE = "config/scenario.xml";
    
    private Commander _ = this;
    
    private IScenario ldr; 
    
    private IPlayer plr;
    
    public void setLdr(IScenario ldr)
    {
        _.ldr = ldr;
    }
    
    public void setPlr(IPlayer plr)
    {
        _.plr = plr;
    }
    
    /**
     * CommandProvider proceses console command
     * @param ci
     * @throws FileNotFoundException
     * @throws JAXBException
     * @throws SAXException
     */
    public void _up(CommandInterpreter ci)
    throws FileNotFoundException,
           JAXBException,
           SAXException
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
                try { _.ldr.loadFile(fileName); }
                catch (FileNotFoundException e) { System.out.println(e.getLocalizedMessage()); }
                catch (InvalidFileException e) { System.out.println("Unable to load file. Invalid format."); }
                catch (Exception e) { e.printStackTrace(); }
                
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
                _.ldr.diag();
                break;
            
            default:
                System.out.printf("Unknown command!%n");
                _.getHelp();
        }
    }
    
    /**
     * CommandProvider interface implementation
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
            "Plays the scenario previusly loaded. If no scenario has not been loaded, loads the file from defautl location config/scenario.xml.%n%n"
        ));
        sb.append(String.format(
            "up diag%n" +
            "Calls the diag on player. Prints diagnostics info - loaded scenario in marshaled form.%n"
        ));
        return sb.toString();
    }

}
