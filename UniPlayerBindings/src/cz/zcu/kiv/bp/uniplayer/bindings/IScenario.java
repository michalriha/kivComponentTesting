package cz.zcu.kiv.bp.uniplayer.bindings;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.bind.JAXBException;
import org.xml.sax.SAXException;

import cz.zcu.kiv.bp.uniplayer.bindings.InvalidFileException;

public interface IScenario extends Iterable<TCommand>
{

    public abstract void loadFile(String fileName)
    throws JAXBException,
           SAXException,
           FileNotFoundException,
           InvalidFileException,
           IOException;
    
    public abstract void diag();

    public abstract long getScenarioDurrationLimit(); 
    
    public abstract long getSimulStepDelay();

    @Override
    public abstract IScenarioIterator iterator();
}
