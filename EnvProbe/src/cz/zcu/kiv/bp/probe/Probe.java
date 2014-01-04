package cz.zcu.kiv.bp.probe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Filter;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTracker;
import org.springframework.osgi.context.BundleContextAware;

/**
 * Provides convenient methods for picking stuff off the OSGi environment.
 * @author Michal
 */
public class Probe implements BundleContextAware, IProbe
{
	/**
	 * How long to wait for service to activate
	 */
	public static int DEFAULT_WAIT_LIMIT = 30000; // ms

	private Probe _ = this;
	
	/**
	 * OSGi context
	 */
	private BundleContext context;
    
	/**
	 * flag signaling whether the scenario replay has been stopped  
	 */
	private volatile boolean stopped = false;
	
	/**
     * ServiceTracker for services required in scenario
     */
    private Map<String, ServiceTracker<?, ?>> svcTrackers = new HashMap<>();
	
	@Override
	public void setBundleContext(BundleContext context)
	{
		_.context = context;
	}
	
	/**
	 * Bundle init-method
	 */
	public void init() { }
	
	/**
	 * Bundle destroy-method. Stops waiting for service activation
	 * and releases service references.
	 */
	public void destroy()
	{
		_.stopped = true;
		
		for (ServiceTracker<?, ?> st : _.svcTrackers.values())
		{
			ServiceReference<?> ref = st.getServiceReference();
			_.context.ungetService(ref);
			st.close();
		}
	}
	
    /* (non-Javadoc)
	 * @see cz.zcu.kiv.bp.probe.IProbe#findBundle(java.lang.String)
	 */
    @Override
	public Bundle findBundle(String description) throws NoSuchBundleException
	{
		Bundle ret = null;
		
		for (Bundle bundle : _.context.getBundles())
		{
            String key = String.format(
            	"%s:%s.%s.%s",
            	bundle.getSymbolicName(),
            	bundle.getVersion().getMajor(),
            	bundle.getVersion().getMinor(),
            	bundle.getVersion().getMicro()
            );
            if (description.equalsIgnoreCase(key))
            {
            	ret = bundle;
            	break;
            }
		}
		
		if (ret == null) throw new NoSuchBundleException(String.format("Bundle %s not found.", description));
		
		return ret;
	}

    /* (non-Javadoc)
	 * @see cz.zcu.kiv.bp.probe.IProbe#findClassesInBundle(org.osgi.framework.Bundle, java.lang.String[])
	 */
	@Override
	public Class<?>[] findClassesInBundle(Bundle bundle, String[] classesToFind) throws ClassNotFoundException
	{
		List<Class<?>> ret = new ArrayList<>(classesToFind.length);

		for (String className : classesToFind)
		{
			ret.add(bundle.loadClass(className));
		}
		
		return ret.toArray(new Class<?>[0]);
	}

	/* (non-Javadoc)
	 * @see cz.zcu.kiv.bp.probe.IProbe#findClassInBundle(org.osgi.framework.Bundle, java.lang.String)
	 */
	@Override
	public Class<?> findClassInBundle(Bundle bundle, String classToFind) throws ClassNotFoundException
	{
		return bundle.loadClass(classToFind);
	}
	
	/* (non-Javadoc)
	 * @see cz.zcu.kiv.bp.probe.IProbe#getServiceInstances(java.lang.String serviceName, int waitLimit)
	 */
	@Override
	public Object[] getServiceInstances(String serviceName, int waitLimit)
	{
		return _.getServiceInstances(serviceName, "", waitLimit);
	}
	
	/* (non-Javadoc)
	 * @see cz.zcu.kiv.bp.probe.IProbe#getServiceInstances(java.lang.String serviceName, java.lang.String, int waitLimit)
	 */
	@Override
	public Object[] getServiceInstances(String serviceName, String filter, int waitLimit)
	{
		Object[] ret = null;
		ServiceTracker<?, ?> st = _.getTracker(serviceName, filter, true);
		System.out.printf("Tracker for %s is %s%n", _.createTrackerKey(serviceName, filter, true), st.toString());
		int i = 0;
		while ((ret = st.getServices()) == null && i < waitLimit && !_.stopped)
		{ // service instance is not active, wait
			if (i == 0)
			{ // this is the first waiting, print warning message
				System.out.printf(
					"Waiting for service. Please activate bundle providing service %s%n",
					serviceName
				);
			}
			else
			{
				System.out.printf(".");
			}
			try
			{
				// wait 1s for service to activate
				st.waitForService(1000);
			}
			catch (InterruptedException ignore) {}
			i += 1000;
		}
		
		return ret;
	}
	
	/* (non-Javadoc)
	 * @see cz.zcu.kiv.bp.probe.IProbe#getServiceInstances(java.lang.String)
	 */
	@Override
	public Object[] getServiceInstances(String serviceName)
	{
		return _.getServiceInstances(serviceName, "");
	}
	
	/* (non-Javadoc)
	 * @see cz.zcu.kiv.bp.probe.IProbe#getServiceInstances(java.lang.String, java.lang.String)
	 */
	@Override
	public Object[] getServiceInstances(String serviceName, String filter)
	{
		Object[] ret = null;
		ServiceTracker<?, ?> st = _.getTracker(serviceName, filter, true);
		ret = st.getServices();
		return ret;
	}
	
	/* (non-Javadoc)
	 * @see cz.zcu.kiv.bp.probe.IProbe#getServiceInstance(java.lang.String, int)
	 */
	@Override
	public Object getServiceInstance(String serviceName, int waitLimit)
	{
		return _.getServiceInstance(serviceName, "", waitLimit);
	}
	
	/* (non-Javadoc)
	 * @see cz.zcu.kiv.bp.probe.IProbe#getServiceInstance(java.lang.String, java.lang.String, int)
	 */
	@Override
	public Object getServiceInstance(String serviceName, String filter, int waitLimit)
	{
		Object ret = null; // service instance
		ServiceTracker<?, ?> st = _.getTracker(serviceName, filter, false);
		ret = st.getService();
		if (ret == null)
		{ // service instance is not active, wait
			System.out.printf(
				"Waiting for service. Please activate bundle providing service %s%n",
				serviceName
			);		
			int i = 0;
			while (ret == null && i < waitLimit && !_.stopped)
			{
				ret = st.getService();
				if (ret != null)
				{ // service instance has been activated, exit loop
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
	
	/* (non-Javadoc)
	 * @see cz.zcu.kiv.bp.probe.IProbe#getServiceInstance(java.lang.String)
	 */
	@Override
	public Object getServiceInstance(String serviceName)
	{
		return _.getServiceInstance(serviceName, "");
	}
	
	/* (non-Javadoc)
	 * @see cz.zcu.kiv.bp.probe.IProbe#getServiceInstance(java.lang.String, java.lang.String)
	 */
	@Override
	public Object getServiceInstance(String serviceName, String filter)
	{
		Object ret = null; // service instance
		ServiceTracker<?, ?> st = _.getTracker(serviceName, filter, false);
		ret = st.getService();		
		return ret;
	}
	
	/* (non-Javadoc)
	 * @see cz.zcu.kiv.bp.probe.IProbe#getTracker(java.lang.String)
	 */
	@Override
	public ServiceTracker<?, ?> getTracker(String serviceName, String filter, boolean tractAllServices)
	{
		String trackerKey = _.createTrackerKey(serviceName, filter, tractAllServices);
		
		if (!_.svcTrackers.containsKey(trackerKey))
		{
			_.createServiceTracker(serviceName, filter, tractAllServices);
		}		
		return _.svcTrackers.get(trackerKey);
	}

	/**
	 * Create string representing the key associated with described service.
	 * @param serviceName name of the service
	 * @param filter optional filter
	 * @param tractAllServices track all the service instances
	 * @return tracker key
	 */
	private String createTrackerKey(String serviceName, String filter, boolean tractAllServices)
	{
		String trackerKey = String.format("all:%s(&(objectclass=%s)%s)", tractAllServices, serviceName, filter);
		return trackerKey;
	}
	
	/* (non-Javadoc)
	 * @see cz.zcu.kiv.bp.probe.IProbe#createServiceTracker(java.lang.String)
	 */
	@Override
	public void createServiceTracker(String serviceName, String filter, boolean tractAllServices)
	{
		String filterString = String.format("(&(objectclass=%s)%s)", serviceName, filter);
		String trackerKey = _.createTrackerKey(serviceName, filter, tractAllServices);
		try 
		{
			Filter fltr = _.context.createFilter(filterString);
			ServiceTracker<?, ?> st = new ServiceTracker<>(_.context, fltr, null);
			st.open(tractAllServices);
			_.svcTrackers.put(trackerKey, st);
			System.out.printf("Tracker for %s created.%n", trackerKey);
		}
		catch (InvalidSyntaxException e)
		{
			System.out.printf("Invalid filter string: %s%nTracker for %s has not been created!%n", filterString, trackerKey);
			e.printStackTrace();
		}
	}
    
	/* (non-Javadoc)
	 * @see cz.zcu.kiv.bp.probe.IProbe#stopWaiting()
	 */
	@Override
	public void stopWaiting()
	{
		_.stopped = true;
	}
}
