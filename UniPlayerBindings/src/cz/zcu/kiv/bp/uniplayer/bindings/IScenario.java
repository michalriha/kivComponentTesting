package cz.zcu.kiv.bp.uniplayer.bindings;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.bind.JAXBException;

import org.xml.sax.SAXException;

import cz.zcu.kiv.bp.datatypes.bindings.TCustomTypesSupport;

/**
 * Playing scenario interface 
 * @author Michal
  */
public interface IScenario extends Iterable<TCommand>
{

	/**
	 * Loads the scenario from given file.
	 * @param fileName scenario file to load
	 * @throws JAXBException unmarshaling of file has failed
	 * @throws SAXException file is not well formed or valid
	 * @throws FileNotFoundException file can not bee read
	 * @throws IOException file can not bee read
	 */
    public abstract void loadFile(String fileName)
    throws JAXBException,
           SAXException,
           FileNotFoundException,
           IOException;
    
    /**
     * Prints diagnostic info about current JAXB environment and current scenario in unmarshaled form 
     */
    public abstract void diag();

    /**
     * Returns simulation time limit for the scenario to run.
     * @return time limit
     */
    public abstract long getScenarioDurrationLimit(); 
    
    /**
     * Return the time delay in milliseconds between two actions. Intended for easier visualization.
     * @return delay between two steps of simulation
     */
    public abstract long getSimulStepDelay();

    /**
     * Returns iterator for simple iterating over loaded scenario.
     */
    @Override
    public abstract IScenarioIterator iterator();
    
    /**
     * Returns structure with description of custom types and prepared values.
     */
    public TCustomTypesSupport getCustomTypesSupportStructure();
}
