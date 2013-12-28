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
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.ValidationEvent;
import javax.xml.bind.ValidationEventHandler;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.SAXException;

import cz.zcu.kiv.bp.datatypes.bindings.TCustomTypesSupport;
import cz.zcu.kiv.bp.unimocker.bindings.TProject;
import cz.zcu.kiv.bp.unimocker.bindings.adapted.BundlesMap;
import cz.zcu.kiv.bp.unimocker.bindings.adapted.Invocation;
import cz.zcu.kiv.bp.unimocker.bindings.adapted.InvokedMethod;

/**
 * Implementation of IScenario. Provides method for loading/storing mocking scenario using JAXB technology.
 * @author Michal
  */
public class Scenario implements IScenario
{
    public static final String
        SCHEMA_FILE      = "schema/unimocker.xsd",
        BINDINGS_PACKAGE = "cz.zcu.kiv.bp.unimocker.bindings"
                         + ":cz.zcu.kiv.bp.unimocker.bindings.basics"
                         + ":cz.zcu.kiv.bp.unimocker.bindings.adapted"
                         + ":cz.zcu.kiv.bp.datatypes.bindings"
                         + ":cz.zcu.kiv.bp.datatypes.bindings.adapted"
                         + ":cz.zcu.kiv.bp.datatypes.bindings.basics"
						 ;

    public static final Source[] XML_SCHEMAS_TO_USE =  new Source[]
    {
    	new StreamSource(cz.zcu.kiv.bp.namespaces.UniMocker.SCENARIO_SCHEMA),
    	new StreamSource(cz.zcu.kiv.bp.namespaces.DataTypes.SCENARIO_SCHEMA),
    };
    
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
     * ValidationEventHandler
     */
    private static final ValidationEventHandler VALIDATION_EVENT_HANDLER= new ValidationEventHandler()
    {
        @Override
        public boolean handleEvent(ValidationEvent event)
        {
            System.out.println(event.getLocator());
            System.out.println(event.getMessage());
            Throwable linkedException = event.getLinkedException();
            if (linkedException != null) linkedException.printStackTrace();
            System.out.println();
            return true;
        }
    };
    
    /**
     * On init prepares all JAXB providers required for (u)nmarshaling of xml file
     * @throws JAXBException
     * @throws SAXException
     * @throws FileNotFoundException 
     */
    public Scenario()
    throws JAXBException, SAXException, FileNotFoundException
    {
    	// prepare validating JAXB context based on configured xml schemas
        _.sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
      	_.jc  = JAXBContext.newInstance(BINDINGS_PACKAGE, TProject.class.getClassLoader());
        _.sch = sf.newSchema(XML_SCHEMAS_TO_USE);
        
        // prepare JAXB UnMarshaller
        _.u = jc.createUnmarshaller();
        _.u.setSchema(_.sch);
        _.u.setEventHandler(VALIDATION_EVENT_HANDLER);

        // prepare JAXB Marshaller
        _.m = jc.createMarshaller();
        _.m.setSchema(_.sch);
        _.m.setEventHandler(VALIDATION_EVENT_HANDLER);
        _.m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        _.m.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, cz.zcu.kiv.bp.namespaces.UniMocker.SCENARIO_SCHEMA_LOCATION);

        _.scenario = new TProject();
        _.scenario.setSimulatedComponents(new BundlesMap());
    }
    
    @Override
    public BundlesMap getSimulatedComponents()
    {
        return _.scenario.getSimulatedComponents();
    }
    
    /**
     * Loads and unmarshals scenario description file
     * @throws IOException 
     */
    @Override
    public void loadFile(String fileName)
    throws JAXBException,
           SAXException,
           IOException
    {
        try 
        (
        	InputStream is = new FileInputStream(new File(fileName));
        	InputStreamReader isr = new InputStreamReader(is , "UTF-8");
        )
        {
            JAXBElement<?> je = (JAXBElement<?>) u.unmarshal(isr);
            
            if (je.getValue() instanceof TProject)
            {
	            _.scenario = (TProject)je.getValue();
            }
            else
            {
            	System.out.println("Given xml file does not have project element as root! Scenario not loaded");
            	System.out.println(je.getValue());
            }
        }
        catch (Exception ex) { ex.printStackTrace(); }
    }

    /**
     * Marshals currently loaded scenario and saves it resulting string into specified file.
     * @param fileName output file
     * @throws FileNotFoundException
     * @throws IOException
     * @throws JAXBException
     */
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
    		for (Entry<String, HashMap<String, List<TSimulatedService>>> bundle : scenario.getSimulatedComponents().entrySet())
    		{
    			System.out.println("bundle: " + bundle.getKey());
    			for (Entry<String, List<TSimulatedService>> servicesEntry : bundle.getValue().entrySet())
    			{
    				System.out.println("    interface: " + servicesEntry.getKey());
    				for (TSimulatedService service : servicesEntry.getValue())
    				{
        				for (InvokedMethod method : service.getMethods())
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

	@Override
	public TCustomTypesSupport getCustomTypesSupportStructure()
	{
		if (_.scenario.getSettings() != null && _.scenario.getSettings().getCustomTypesSupport() != null)
		{
			return _.scenario.getSettings().getCustomTypesSupport();
		}
		else return null;
	}
	
	
}
