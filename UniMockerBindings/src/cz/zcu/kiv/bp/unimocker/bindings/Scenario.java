package cz.zcu.kiv.bp.unimocker.bindings;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.ValidationEvent;
import javax.xml.bind.ValidationEventHandler;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.SAXException;

import cz.zcu.kiv.bp.unimocker.bindings.TProject;
import cz.zcu.kiv.bp.unimocker.bindings.adapted.BundlesMap;
import cz.zcu.kiv.bp.unimocker.bindings.adapted.Invocation;
import cz.zcu.kiv.bp.unimocker.bindings.adapted.InvokedMethod;
import cz.zcu.kiv.bp.unimocker.bindings.ObjectFactory;
import cz.zcu.kiv.bp.unimocker.bindings.basics.InvalidFileException;

public class Scenario implements IScenario
{
    public static final String
    SCHEMA_FILE      = "schema/unimocker.xsd",
    BINDINGS_PACKAGE = "cz.zcu.kiv.bp.unimocker.bindings"
    				+ ":cz.zcu.kiv.bp.unimocker.bindings.basics"
    				+ ":cz.zcu.kiv.bp.unimocker.bindings.adapted";
    
	private Scenario _ = this;
    
    /**
     * JAXB providers
     */
    private SchemaFactory sf;
    private JAXBContext jc; 
    private Schema sch;
    private Unmarshaller  u;
    private Marshaller m;

    /**
     * Unmarshaled scenario file 
     */
    private TProject scenario = null;
    
    /**
     * On init prepares all JAXB providers required for (u)nmarshaling of xml file
     * @throws JAXBException
     * @throws SAXException
     * @throws FileNotFoundException 
     */
    public Scenario()
    throws JAXBException, SAXException, FileNotFoundException
    {
        _.sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        _.jc  = JAXBContext.newInstance(BINDINGS_PACKAGE, TProject.class.getClassLoader());
		_.sch = sf.newSchema(_.getSchemaFile());
		_.u = jc.createUnmarshaller();
        _.m = jc.createMarshaller();
        _.u.setSchema(_.sch);
        _.u.setEventHandler(new ValidationEventHandler() {
			@Override
			public boolean handleEvent(ValidationEvent event) {
				System.out.println(event.getLocator());
				System.out.println(event.getMessage());
				return true;
			}
		});
        _.m.setSchema(_.sch);
        _.m.setEventHandler(new ValidationEventHandler() {
			@Override
			public boolean handleEvent(ValidationEvent event) {
				System.out.println(event.getLocator());
				System.out.println(event.getLocator());
				return true;
			}
		});
        _.m.setProperty("jaxb.formatted.output", true);

        _.scenario = new TProject();
        _.scenario.setSimulatedComponents(new BundlesMap());
    }

	private URL getSchemaFile() throws FileNotFoundException
	{
		URL schemaFile = _.getClass().getClassLoader().getResource(SCHEMA_FILE);
		if (schemaFile == null)
		{
			try
			{
				File schFile = new File(SCHEMA_FILE);
				if (! schFile.canRead()) throw new FileNotFoundException(SCHEMA_FILE);

				schemaFile = schFile.toURI().toURL();
			}
			catch (MalformedURLException e) { throw new FileNotFoundException(SCHEMA_FILE); }
		}
		return schemaFile;
	}
    
	public TProject getProject()
	{
		return _.scenario;
	}
	
    /**
     * Loads and unmarshales scenario description file
     * @throws IOException 
     */
    @Override
    public void loadFile(String fileName)
    throws JAXBException,
           SAXException,
           InvalidFileException,
           IOException
    {
        try 
        (
        	InputStream is = new FileInputStream(new File(fileName));
        	InputStreamReader isr = new InputStreamReader(is , "UTF-8");
        )
        {
            JAXBElement<?> je = (JAXBElement<?>) u.unmarshal(isr);
            _.scenario = (TProject)je.getValue();
        }
    }

    public void saveToFile(String fileName)
    throws FileNotFoundException, IOException, JAXBException
    {
    	try (
    		StringWriter sw = new StringWriter();
	    	OutputStream os = new FileOutputStream(new File(fileName));
	    	OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
    	)
    	{
    		
    		ObjectFactory of = new ObjectFactory();
            JAXBElement<TProject> je = of.createProject(_.scenario);
            
            _.m.marshal(je, sw);
            osw.write(sw.toString());
    	}
    }

	@Override
	public void diag()
	{
		System.out.println(_.jc);
		
        if (_.scenario != null)
        try
        {
    		System.out.println("loaded scenario");
    		for (Entry<String, HashMap<String, TSimulatedService>> bundle : scenario.getSimulatedComponents().entrySet())
    		{
    			System.out.println("bundle: " + bundle.getKey());
    			for (Entry<String, TSimulatedService> service : bundle.getValue().entrySet())
    			{
    				System.out.println("    interface: " + service.getValue().getInterface());
    				for (InvokedMethod method : service.getValue().getMethod())
    				{
    					System.out.println("        method: " + method.getName());
    					for (Invocation inv : method.getInvocations())
    					{
    						System.out.printf(
    							"        invocation: %n"
    							+ "            arguments: %s %s%n"
    							+ "            return: %s %s%n",
    							Arrays.toString(inv.getArguments().getTypes()),
    							Arrays.toString(inv.getArguments().toArray()),
    							inv.getReturnValue().getType(),
    							inv.getReturnValue().getValue()
    						);
    					}
    				}
    			}
    		}
    		
            ObjectFactory of = new ObjectFactory();
            StringWriter wr = new StringWriter();
            _.m.marshal(of.createProject(_.scenario), wr);
            System.out.println(wr);
        }
        catch (JAXBException e)
        {
            System.out.println(e.getMessage());
        }
	}
	
	
}
