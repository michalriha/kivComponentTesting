package cz.zcu.kiv.bp.uniplayer;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.JAXBException;

import org.apache.commons.lang.reflect.MethodUtils;
//import org.osgi.framework.BundleContext;
//import org.osgi.framework.ServiceReference;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventAdmin;
//import org.osgi.util.tracker.ServiceTracker;
//import org.springframework.osgi.context.BundleContextAware;
import org.xml.sax.SAXException;

import cz.zcu.kiv.bp.probe.IProbe;
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
public class Player implements IPlayer//, BundleContextAware
{
//	/**
//	 * How long to wait for service to activate
//	 */
//	// TODO: add to scenario file format
//	private static int SERVICE_WAIT_LIMIT = 30000; // ms

	private Player _ = this;
	
//	/**
//	 * OSGi context
//	 */
//	private BundleContext context;
	
	/**
	 * OSGI EventAdmin service
	 */
	private EventAdmin eventAdmin;
	
	/**
	 * OSGI environment probe
	 */
	private IProbe envProbe;
	
	/**
	 * flag signaling whether the scenario replay has been stopped  
	 */
	private volatile boolean stopped = false;
	
	/**
	 * UniPlayerBinding loader
	 */
	private IScenario scenario;

//    /**
//     * ServiceTracker for services required in scenario
//     */
//    private Map<String, ServiceTracker<?, ?>> svcTrackers = new HashMap<>();
    
//    /**
//     * BundleContextAware setter 
//     */
//    @Override
//    public void setBundleContext(BundleContext context)
//    {
//        _.context = context;
//    }

    /**
     * OSGi EventAdmin setter
     * @param eventAdmin
     */
    public void setEventAdmin(EventAdmin eventAdmin)
    {
        _.eventAdmin = eventAdmin;
    }
    
    /**
     * EnvProbe setter
     */
    public void setEnvProbe(IProbe envProbe)
    {
    	_.envProbe = envProbe;
    }

//    /**
//     * Bundle destroy method.
//     * @throws Exception
//     * @deprecated
//     */
//	public void destroy() throws Exception
//	{
//		for (ServiceTracker<?, ?> st : _.svcTrackers.values())
//		{
//			ServiceReference<?> ref = st.getServiceReference();
//			_.context.ungetService(ref);
//			st.close();
//		}
//	}

	@Override
	public void play() throws Exception
	{
		Map<String, TCommand> actionEventArgs = new HashMap<>();
		
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
			
			actionEventArgs.clear();
			
			actionEventArgs.put(IPlayer.SIMULATION_PLAYER_EVENT_KEY, currentCommand);
			_.eventAdmin.sendEvent(new Event(IPlayer.SIMULATION_PLAYER_EVENT_TOPIC_START, actionEventArgs));
			
			if (call != null)
			{
				System.out.printf(
					" %s.%s (%s)%n",
					call.getService(),
					call.getMethod(),
					call.getArguments()
				);
				_.execute(call);
			}
			else if (event != null)
			{
				System.out.printf(
					" %s/%s[%s]%n",
					event.getTopic(),
					event.getKey(),
					event.getArgument()
				);
				_.execute(event);
			}
			System.out.println();
			
			_.eventAdmin.sendEvent(new Event(IPlayer.SIMULATION_PLAYER_EVENT_TOPIC_FINISH, actionEventArgs));
			Thread.sleep(_.scenario.getSimulStepDelay());
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
		Object[] serviceInstances = _.getServiceInstances(call);
		
		if (serviceInstances == null)
		{ // no service implementation is active in current context
			System.out.printf("No instance of %s has not been found. skipping ...%n", call.getService());
			return;
		}
		
		for (Object serviceInstance : serviceInstances)
		{ // invoke method on each service instance
			_.invokeMethodOnInstance(call, serviceInstance);
		}
	}

	/**
	 * Invokes method described in the call argument on service Instance object.
	 * @param call - invocation description
	 * @param serviceInstance - instance on which the method will be invoked
	 */
	private void invokeMethodOnInstance(TCall call, Object serviceInstance)
	{
		try
		{ // try to execute required action
			System.out.println("\t\tinstance: " + serviceInstance);
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
	 * Build array of service instances. Array has only one element when use-all-services-available is set to false.
	 * @param call - invocation description
	 * @return array of service instances, null when no instance is found
	 */
	private Object[] getServiceInstances(TCall call)
	{
		Object[] serviceInstances = null;
		
		if (call.isUseAllServicesAvailable())
		{ // find all service instances
			serviceInstances = _.envProbe.getServiceInstances(call.getService(), IProbe.DEFAULT_WAIT_LIMIT);
		}
		else
		{ // find single service instance
			Object singleInstance = _.envProbe.getServiceInstance(call.getService(), IProbe.DEFAULT_WAIT_LIMIT);
			if (singleInstance != null)
			{
				serviceInstances = new Object[] {singleInstance};
			}
		}
		return serviceInstances;
	}
	
//	/**
//	 * Invokes method on service with arguments described in call argument.
//	 * @param call invocation description
//	 */
//	private void executeOld(TCall call)
//	{
//		// find service instance
//		Object serviceInstance = _.envProbe.getServiceInstance(call.getService(), IProbe.DEFAULT_WAIT_LIMIT);
//		if (call.isUseAllServicesAvailable())
//		{
//			Object[] serviceInstanc2 = _.envProbe.getServiceInstances(call.getService(), IProbe.DEFAULT_WAIT_LIMIT);
//
//		}
//		
//		System.out.println(serviceInstance);
//		if (serviceInstance == null)
//		{ // no service implementation is active in current context
//			System.out.printf("No instance of %s has not been found. skipping ...%n", call.getService());
//			return;
//		}
//
//		try
//		{ // try to execute required action
//			// apache method utils used for it's better matching capabilities
//			MethodUtils.invokeMethod(
//				serviceInstance,
//				call.getMethod(),
//				call.getArguments().toArray(),
//				call.getArguments().getTypes()
//			);
//		}
//		catch (NoSuchMethodException ex)
//		{ // required method does not exist
//			System.out.printf(
//				"Service does not provide method %s (%s). skipping ...%n",
//				call.getMethod(),
//				printTypes(call.getArguments().getTypes())
//			);
//		}
//		catch (
//			IllegalAccessException
//			| IllegalArgumentException
//			| InvocationTargetException e)
//		{ // invocation failed
//			System.out.println("Invocation of method %s in service %s failled. stack trace:%n");
//			e.printStackTrace();
//		}
//		catch (Throwable e)
//		{ // unexpected exception
//			e.printStackTrace();
//		}
//	}

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

//	/**
//	 * Tries to acquire service from OSGi context.
//	 * @param serviceName name of the service to acquire
//	 * @return service instance
//	 * @deprecated
//	 */
//	private Object getServiceInstance(String serviceName)
//	{
//		Object ret = null; // service instance
//		ServiceTracker<?, ?> st = _.getTracker(serviceName);
//		ret = st.getService();
//		if (ret == null)
//		{ // service instance is not active, wait
//			System.out.printf(
//				"Waiting for service. Please activate bundle providing service %s%n",
//				serviceName
//			);		
//			int i = 0;
//			while (ret == null && i < SERVICE_WAIT_LIMIT && !_.stopped)
//			{
//				ret = st.getService();
//				if (ret != null)
//				{ // service instance has been active, exit loop
//					break;
//				}
//				
//				// wait 1s for service to activate
//				try
//				{
//					System.out.print(".");
//					st.waitForService(1000);
//				}
//				catch (InterruptedException ignore) {}
//				i += 1000;
//			}
//		}
//
//		
//		return ret;
//	}
//	
//	/**
//	 * Returns ServiceTracker for given service. If the tracker does not exist, it creates and stores new one.
//	 * @param serviceName service to track
//	 * @return corresponding ServiceTracker
//	 * @deprecated
//	 */
//	private ServiceTracker<?, ?> getTracker(String serviceName)
//	{
//		if (!_.svcTrackers.containsKey(serviceName))
//		{
//			_.createServiceTracker(serviceName);
//		}
//		return _.svcTrackers.get(serviceName);
//	}
//	
//	/**
//	 * Create ServiceTracker for current OSGi context and given service.
//	 * @param serviceName name of the tracked service
//	 * @deprecated
//	 */
//	private void createServiceTracker(String serviceName)
//	{
//		ServiceTracker<?, ?> st = new ServiceTracker<>(_.context, serviceName, null);
//		st.open();
//		_.svcTrackers.put(serviceName, st);
//	}
	
	/**
	 * Signals the player to stop the replay
	 */
	public void stop()
	{
		_.envProbe.stopWaiting();
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
