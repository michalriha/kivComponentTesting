package cz.zcu.kiv.bp.unimocker.bindings;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.bind.JAXBException;

import org.xml.sax.SAXException;

import cz.zcu.kiv.bp.unimocker.bindings.adapted.BundlesMap;

/**
 * Mocking scenario interface 
 * @author Michal
  */
public interface IScenario
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
     * Return map of bundles that should contain classes that should be mocked.
     * @return mocking scenario map
     */
    public BundlesMap getSimulatedComponents();
}
