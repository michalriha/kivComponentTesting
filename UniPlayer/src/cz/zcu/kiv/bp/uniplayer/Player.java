package cz.zcu.kiv.bp.uniplayer;

import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang.reflect.MethodUtils;
import org.eclipse.osgi.framework.console.CommandProvider;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventAdmin;
import org.osgi.util.tracker.ServiceTracker;
import org.springframework.osgi.context.BundleContextAware;

import cz.zcu.kiv.bp.uniplayer.bindings.IScenario;
import cz.zcu.kiv.bp.uniplayer.bindings.IScenarioIterator;
import cz.zcu.kiv.bp.uniplayer.bindings.TCall;
import cz.zcu.kiv.bp.uniplayer.bindings.TCommand;
import cz.zcu.kiv.bp.uniplayer.bindings.TEvent;

public class Player implements IPlayer, BundleContextAware
{
	private static int SERVICE_WAIT_LIMIT = 3000; // ms
		
	private Player _ = this;
	
	private BundleContext context;
	
	private volatile boolean stopped = false;
	
	private ServiceRegistration<CommandProvider> reg;
	
	private EventAdmin eventAdmin;
    
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

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception
	{
		_.context = null;
		_.reg.unregister();
	}

	@Override
	public void play(IScenario scenario) throws Exception
	{
		_.stopped = false;
		IScenarioIterator iter = scenario.iterator();
    	while (!_.stopped && iter.hasNext())
		{
			TCommand currentCommand = iter.next();
			
			TCall call = currentCommand.getCall();
			TEvent event = currentCommand.getEvent();
			
			System.out.printf(
				"%10d: %s",
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
				_.execute(event);
				System.out.printf(
					" %s/%s[%s]",
					event.getTopic(),
					event.getKey(),
					event.getArgument()
				);
			}
			System.out.println();
			
			Thread.sleep(scenario.getSimulStepDelay());
		}
	}

	private void execute(TEvent event)
	{
        HashMap<String, Object> arguments = new HashMap<>();
        arguments.put(event.getKey(), event.getArgument());
        _.eventAdmin.sendEvent(new Event(event.getTopic(), arguments));
	}
	
	private void execute(TCall call)
	{
		// find service instance
		Object serviceInstance = _.getServiceInstance(call.getService());
		if (serviceInstance == null)
		{ // no service implementation is active in current context
			System.out.printf("No instance of %s has not been found. skipping ...%n", call.getService());
			return;
		}
		
//		// find service's interface
//		Class<?> serviceInterface = _.getServiceClass(serviceInstance, call.getService());
//		if (serviceInterface == null)
//		{ // interface does not exist in current context 
//			System.out.printf("Service %s has not been found. skipping ...%n", call.getService());
//			return;
//		}
//		
//		// find invoked method in service
//		// apache method utils have better matching capabilities than implicit java reflection
//		Method invokedMethod = MethodUtils.getMatchingAccessibleMethod(
//			serviceInterface,
//			call.getMethod(),
//			call.getArguments().getTypes()
//		);
//		if (invokedMethod == null)
//		{ // required method does not exist
//			System.out.printf(
//				"Service does not provide method %s (%s). skipping ...%n",
//				call.getMethod(),
//				printTypes(call.getArguments().getTypes())
//			);
//			return;
//		}

		try
		{ // try to execute requirred action
//			invokedMethod.invoke(serviceInstance, call.getArguments().toArray());
			
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
		{
			System.out.println("Invocation of method %s in service %s failled. stack trace:%n");
			e.printStackTrace();
		}
	}

	private String printTypes(Class<?>[] types)
	{
		StringBuilder sb = new StringBuilder();
		for (Class<?> type : types)
		{
			sb.append(type.getName());
		}
		return sb.length() == 0 ? void.class.getName() : sb.toString();
	}

//	private Class<?> getServiceClass(Object serviceInstance, String service)
//	{
//		if (serviceInstance == null) return null;
//		
//		Class<?> ret = null;
//		
//		try
//		{
//			ret = serviceInstance.getClass().getClassLoader().loadClass(service);
//		}
//		catch (ClassNotFoundException ignore)
//		{ // should never occur since the object is either regular instance of class implementing serviceName
//		  //interface or it is mockup which already has found the proper interface class in bundle context.
//		}
//		
//		return ret;
//	}

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
	
	private ServiceTracker<?, ?> getTracker(String serviceName)
	{
		if (!_.svcTrackers.containsKey(serviceName))
		{
			_.createServiceTracker(serviceName);
		}
		return _.svcTrackers.get(serviceName);
	}
	
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
		for (ServiceTracker<?, ?> st : _.svcTrackers.values())
		{
			st.close();
		}
		_.stopped = true;
	}
}
