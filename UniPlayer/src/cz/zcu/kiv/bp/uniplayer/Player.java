package cz.zcu.kiv.bp.uniplayer;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.JAXBException;

import org.apache.commons.lang.reflect.MethodUtils;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventAdmin;
import org.osgi.util.tracker.ServiceTracker;
import org.springframework.osgi.context.BundleContextAware;
import org.xml.sax.SAXException;

import cz.zcu.kiv.bp.uniplayer.bindings.IScenario;
import cz.zcu.kiv.bp.uniplayer.bindings.IScenarioIterator;
import cz.zcu.kiv.bp.uniplayer.bindings.Scenario;
import cz.zcu.kiv.bp.uniplayer.bindings.TCall;
import cz.zcu.kiv.bp.uniplayer.bindings.TCommand;
import cz.zcu.kiv.bp.uniplayer.bindings.TEvent;

/**
 * IPlayer implementation. Implements local scenario player.
 * @author Michal
 */
public class Player implements IPlayer, BundleContextAware
{
	/**
	 * How long to wait for service to activate
	 */
	// TODO: add to scenario file format
	private static int SERVICE_WAIT_LIMIT = 30000; // ms

	private Player _ = this;
	
	/**
	 * OSGi context
	 */
	private BundleContext context;
	
	/**
	 * OSGI EventAdmin service
	 */
	private EventAdmin eventAdmin;
	
	/**
	 * flag signaling whether the scenario has replay has been stopped  
	 */
	private volatile boolean stopped = false;
	
	/**
	 * UniPlayerBinding loader
	 */
	private IScenario scenario;
    
    /**
     * ServiceTracker for services required in scenario
     */
    private Map<String, ServiceTracker<?, ?>> svcTrackers = new HashMap<>();
    
    /**
     * BundleContextAware setter 
     */
    @Override
    public void setBundleContext(BundleContext context)
    {
        _.context = context;
    }

    /**
     * OSGi EventAdmin setter
     * @param eventAdmin
     */
    public void setEventAdmin(EventAdmin eventAdmin)
    {
        _.eventAdmin = eventAdmin;
    }

    /**
     * Bundle 
     * @param bundleContext
     * @throws Exception
     */
	public void destroy() throws Exception
	{
		for (ServiceTracker<?, ?> st : _.svcTrackers.values())
		{
			ServiceReference<?> ref = st.getServiceReference();
			_.context.ungetService(ref);
			st.close();
		}
	}

	@Override
	public void play() throws Exception
	{
		_.stopped = false;
		IScenarioIterator iter = _.scenario.iterator();
    	while (!_.stopped && iter.hasNext())
		{
			TCommand currentCommand = iter.next();
			
			TCall call = currentCommand.getCall();
			TEvent event = currentCommand.getEvent();
			
			System.out.printf(
				"%10d: %s => ",
				iter.getCurrentTime(),
				call != null ? "call" : "event"
			);
			
			if (call != null)
			{
				System.out.printf(
					" %s.%s (%s)",
					call.getService(),
					call.getMethod(),
					call.getArguments()
				);
				_.execute(call);
			}
			else if (event != null)
			{
				System.out.printf(
					" %s/%s[%s]",
					event.getTopic(),
					event.getKey(),
					event.getArgument()
				);
				_.execute(event);
			}
			System.out.println();
			
			Thread.sleep(scenario.getSimulStepDelay());
		}
	}

	/**
	 * Fires given event.
	 * @param event
	 */
	private void execute(TEvent event)
	{
        HashMap<String, Object> arguments = new HashMap<>();
        arguments.put(event.getKey(), event.getArgument().getValue());
        _.eventAdmin.sendEvent(new Event(event.getTopic(), arguments));
	}
	
	/**
	 * Invokes method on service with arguments described in call argument.
	 * @param call invocation description
	 */
	private void execute(TCall call)
	{
		// find service instance
		Object serviceInstance = _.getServiceInstance(call.getService());
		System.out.println(serviceInstance);
		if (serviceInstance == null)
		{ // no service implementation is active in current context
			System.out.printf("No instance of %s has not been found. skipping ...%n", call.getService());
			return;
		}

		try
		{ // try to execute requirred action
			// apache method utils used for it's better matching capabilities
			MethodUtils.invokeMethod(
				serviceInstance,
				call.getMethod(),
				call.getArguments().toArray(),
				call.getArguments().getTypes()
			);
		}
		catch (NoSuchMethodException ex)
		{ // required method does not exist
			System.out.printf(
				"Service does not provide method %s (%s). skipping ...%n",
				call.getMethod(),
				printTypes(call.getArguments().getTypes())
			);
		}
		catch (
			IllegalAccessException
			| IllegalArgumentException
			| InvocationTargetException e)
		{ // invocation failed
			System.out.println("Invocation of method %s in service %s failled. stack trace:%n");
			e.printStackTrace();
		}
		catch (Throwable e)
		{ // unexpected exception
			e.printStackTrace();
		}
	}

	/**
	 * Implodes array of Class<?> using it's getName() method.
	 * @param types
	 * @return list of comma separated class names 
	 */
	private String printTypes(Class<?>[] types)
	{
		StringBuilder sb = new StringBuilder();
		for (Class<?> type : types)
		{
			sb.append(type.getName());
		}
		return sb.length() == 0 ? void.class.getName() : sb.toString();
	}

	/**
	 * Tries to acquire service from OSGi context.
	 * @param serviceName name of the service to acquire
	 * @return service instance
	 */
	private Object getServiceInstance(String serviceName)
	{
		Object ret = null; // service instance
		ServiceTracker<?, ?> st = _.getTracker(serviceName);
		ret = st.getService();
		if (ret == null)
		{ // service instance is not active, wait
			System.out.printf(
				"Waiting for service. Please activate bundle providing service %s%n",
				serviceName
			);		
			int i = 0;
			while (ret == null && i < SERVICE_WAIT_LIMIT)
			{
				ret = st.getService();
				if (ret != null)
				{ // service instance has been active, exit loop
					break;
				}
				
				// wait 1s for service to activate
				try
				{
					System.out.print(".");
					st.waitForService(1000);
				}
				catch (InterruptedException ignore) {}
				i += 1000;
			}			
		}

		
		return ret;
	}
	
	/**
	 * Returns ServiceTracker for given service. If the tracker does not exist, it creates and stores new one.
	 * @param serviceName service to track
	 * @return corresponding ServiceTracker
	 */
	private ServiceTracker<?, ?> getTracker(String serviceName)
	{
		if (!_.svcTrackers.containsKey(serviceName))
		{
			_.createServiceTracker(serviceName);
		}
		return _.svcTrackers.get(serviceName);
	}
	
	/**
	 * Create ServiceTracker for current OSGi context and given service.
	 * @param serviceName name of the tracked service
	 */
	private void createServiceTracker(String serviceName)
	{
		ServiceTracker<?, ?> st = new ServiceTracker<>(_.context, serviceName, null);
		st.open();
		_.svcTrackers.put(serviceName, st);
	}
	
	/**
	 * Bundel destroy-method
	 */
	public void stop()
	{
		_.stopped = true;
	}

	@Override
	public void loadFile(String fileName)
	throws JAXBException, SAXException, IOException
	{
		_.scenario = new Scenario();
		_.scenario.loadFile(fileName);
	}
	
	@Override
	public void diag()
	{
		if (_.scenario == null)
		{
			System.out.println("Simulation scenario has not been loaded.");
			return;
		}
		_.scenario.diag();
	}
}
