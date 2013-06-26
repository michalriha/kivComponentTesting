package cz.zcu.kiv.bp.uniplayer.bindings;

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
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
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

import org.osgi.service.event.Event;
import org.osgi.service.event.EventAdmin;
import org.xml.sax.SAXException;

import cz.zcu.kiv.bp.uniplayer.bindings.IScenarioIterator;
import cz.zcu.kiv.bp.uniplayer.bindings.adapted.ActionsMap;
import cz.zcu.kiv.bp.uniplayer.bindings.adapted.Argument;
import cz.zcu.kiv.bp.uniplayer.bindings.adapted.Value;

/**
 * Scenario file loader. Provides methods for proper unmarshalling of scenario file
 * @author Michal
 *
 */
public class Scenario implements IScenario
{
    public static final String
        SCHEMA_FILE      = "schema/scenario.xsd",
        BINDINGS_PACKAGE = "cz.zcu.kiv.bp.uniplayer.bindings"
        		+ ":cz.zcu.kiv.bp.uniplayer.bindings.basics"
        		+ ":cz.zcu.kiv.bp.uniplayer.bindings.adapted";

    private Scenario _ = this;
    
    /**
     * OSGI EventAdmin service
     */
    private EventAdmin eventAdmin;
    
    public void setEventAdmin(EventAdmin eventAdmin)
    {
    	_.eventAdmin = eventAdmin;
    }
    
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
     * On init prepares all JAXB providers required for (un)marshaling of xml file
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
				System.out.println(event.getMessage());
				return true;
			}
		});
        _.m.setSchema(_.sch);
        _.m.setEventHandler(new ValidationEventHandler() {
			@Override
			public boolean handleEvent(ValidationEvent event) {
				event.getLinkedException().printStackTrace();
				return true;
			}
		});
        _.m.setProperty("jaxb.formatted.output", true);

        _.scenario = new TProject();
        _.scenario.setSettings(new TSettings());
        _.scenario.setActions(new ActionsMap());
    }

    /**
     * Loads schema file.
     * @return URL of the schema file
     * @throws FileNotFoundException when unable to locate schema file
     */
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
    
	/**
	 * Returns top element of current scenario. Use with caution! Intended for building new scenario.
	 * @return top element of raw scenario
	 */
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

    /**
     * Saves current scenario into file.
     * @param fileName where to save the scenario
     * @throws FileNotFoundException 
     * @throws IOException
     * @throws JAXBException marshaling of current scenario has failed - possibly for improper nodes in scenario tree
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
    
    /**
     * Prints diagnostics information about existing scenario project.
     */
    @Override
    public void diag()
    {
        System.out.println(_.jc);

        System.out.printf("Actions: (%d)%n", _.scenario.getActions().size());
        for (Entry<Long, LinkedList<TAction>> entry : _.scenario.getActions().entrySet())
        {
            System.out.printf("time: %d%n", entry.getKey());
            for (TAction action : entry.getValue())
            {
                if (action.getCommand().getCall() != null)
                {
                    System.out.printf("call: %s (rep: %d <= %d)%n\targuments:%n", action.getCommand().getCall().getMethod(), action.getRecurrence().getCount(), action.getRecurrence().getRepeatUntil());
                    for (Argument arg : action.getCommand().getCall().getArguments())
                    {
                        System.out.printf("\t\t%s %s (%s, %s)%n", arg.getArgumentOrder(), arg.getValue(), arg.getValue() == null ? "null" : arg.getValue().getClass(), arg.getType());
                    }
                }
                if (action.getCommand().getEvent() != null)
                {
                	Value arg = action.getCommand().getEvent().getArgument();
                    System.out.printf(
                    	"event: (rep: %d <= %d) %s (%s, %s)%n",
                    	action.getRecurrence().getCount(),
                    	action.getRecurrence().getRepeatUntil(),
                    	arg != null ? arg.getValue() : "void",
                    	arg.getType(),
                    	arg.getValue() != null ? arg.getValue().getClass().getCanonicalName() : "void"
                    );
                }
            }
        }
        
        try
        {
            ObjectFactory of = new ObjectFactory();
            JAXBElement<TProject> je = of.createProject(_.scenario);
            
            StringWriter sw = new StringWriter();
            _.m.marshal(je, sw);
            System.out.println(sw);
        }
        catch (JAXBException e)
        {
            System.out.println(e.getMessage());
        }
    }
    
    private ActionsMap actions;
    
    private List<TAction> currentTimeList;
    
    private TAction currentAction = null;
    
    private long currentTime = 0;

    /**
     * Convenient event objects for signaling steps in scenario.
     */
    private Event
    	actionRemovedEvent,
    	actionUpdatedEvent;
    {
    	Map<String, String> actionRemovedEventProps = new HashMap<String, String>();
    	actionRemovedEventProps.put("removed", "");
    	actionRemovedEvent = new Event("scenario/action", actionRemovedEventProps);

    	Map<String, String> actionUpdatedEventProps = new HashMap<String, String>();
    	actionRemovedEventProps.put("updated", "");
    	actionUpdatedEvent = new Event("scenario/action", actionUpdatedEventProps);
    }
    
	@Override
	public IScenarioIterator iterator() {
		if (_.scenario == null)
		{
			throw new IllegalStateException("No scenario has been loaded.");
		}
		_.actions = _.scenario.getActions();
		
		return new IScenarioIterator()
		{
			@Override
			public boolean hasNext()
			{
				boolean ret = false;
				if ((_.currentTimeList != null && !_.currentTimeList.isEmpty()) ||
					(_.actions.size() != 0 && _.scenario.getSettings().getTimeLimit() > this.getNextTime()))
				{
					ret = true;
				}						
				return ret;
			}

			/**
			 * Returns next action in scenario.
			 */
			@Override
			public TCommand next()
			{
				while (_.currentTimeList == null || _.currentTimeList.isEmpty())
				{
					_.currentTimeList = _.actions.pollFirstEntry().getValue();
				}
				
				_.currentAction = _.currentTimeList.remove(0);
				_.currentTime = _.currentAction.getTime();
				TCommand ret = _.currentAction.getCommand();
				
				this.update();
				
				return ret;
			}

			@Override
			public void remove()
			{
				throw new UnsupportedOperationException();
			}

			/**
			 * Updates current action that is pointed by this iterator.
			 * If the current action has reached it's occurrence limit,
			 * it's removed and corresponding event is fired.
			 */
			private void update()
			{
				_.currentAction.getRecurrence().decCount();
				long nextTime = _.currentAction.getTime() + (long) _.currentAction.getRecurrence().getDistribution().nextOccurrence();
				
				// action has reached its number of executions  ||
				// action has reached its maximum execution time
				if (_.currentAction.getRecurrence().getCount() == 0 ||
					nextTime > _.currentAction.getRecurrence().getRepeatUntil()) 
				{
					if (_.eventAdmin != null) _.eventAdmin.postEvent(_.actionRemovedEvent);
					return;
				}
				
				_.currentAction.setTime(nextTime);
				_.actions.put(_.currentAction);
				if (_.eventAdmin != null) _.eventAdmin.postEvent(_.actionUpdatedEvent);
			}
			
			@Override
			public long getCurrentTime()
			{
				return _.currentTime;
			}
			
			@Override
			public long getNextTime()
			{
				return _.actions.firstKey();
			}
		};
	}

	@Override
	public long getScenarioDurrationLimit()
	{
		return (long) _.scenario.getSettings().getTimeLimit();
	}

	@Override
	public long getSimulStepDelay()
	{
		return (long) _.scenario.getSettings().getSimulStepDelay();
	}

}
