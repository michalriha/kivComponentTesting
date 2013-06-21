package cz.zcu.kiv.bp.unimocker.bindings;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.bind.JAXBException;

import org.xml.sax.SAXException;

import cz.zcu.kiv.bp.unimocker.bindings.basics.InvalidFileException;


public interface IScenario
{
    public abstract void loadFile(String fileName)
    throws JAXBException,
           SAXException,
           FileNotFoundException,
           InvalidFileException,
           IOException;
    
    public abstract void diag();
}
