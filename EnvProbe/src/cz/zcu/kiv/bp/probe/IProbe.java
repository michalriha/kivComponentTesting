package cz.zcu.kiv.bp.probe;

import org.osgi.framework.Bundle;
import org.osgi.util.tracker.ServiceTracker;

public interface IProbe
{
	public static int DEFAULT_WAIT_LIMIT = 30000;
	
	/**
	 * Tries to found bundle described by string in format symbolic.name:Major.Minor.Micro.
	 * Stops with the first bundle that has matching description! 
	 * @param description symbolic.name:Major.Minor.Micro fromated bundle description
	 * @return found Bundle instance or null when not found
	 */
	public abstract Bundle findBundle(String description);

	/**
	 * Tries to load described classes from the given bundle.
	 * @param bundle which should be probed
	 * @param classesToFind array of class names to find
	 * @return array of loaded classes
	 * @throws ClassNotFoundException when bundle's classloader fails to load class
	 */
	public abstract Class<?>[] findClassesInBundle(Bundle bundle, String[] classesToFind)
	throws ClassNotFoundException;

	/**
	 * Tries to load described class from the given bundle.
	 * @param bundle which should be probed
	 * @param classToFind name of the class to find
	 * @return array of loaded classes
	 * @throws ClassNotFoundException when bundle's classloader fails to load class
	 */
	public abstract Class<?> findClassInBundle(Bundle bundle, String classToFind)
	throws ClassNotFoundException;
	
	/**
	 * Tries to acquire service from OSGi context.
	 * @param serviceName name of the service to acquire
	 * @param waitLimit time in [ms] to wait for service to be activated
	 * @return service instance
	 */
	public abstract Object getServiceInstance(String serviceName, int waitLimit);
	
	/**
	 * Tries to acquire service from OSGi context using given filter.
	 * @param serviceName name of the service to acquire
	 * @param filter LDAP filter - Do not use (objectclass=...), it is already used from serviceName parameter!
	 * @param waitLimit time in [ms] to wait for service to be activated
	 * @return service instance
	 */
	public abstract Object getServiceInstance(String serviceName, String filter, int waitLimit);

	/**
	 * Tries to acquire service from OSGi context.
	 * @param serviceName name of the service to acquire
	 * @return service instance
	 */
	public abstract Object getServiceInstance(String serviceName);

	/**
	 * Tries to acquire service from OSGi context using given filter.
	 * @param serviceName name of the service to acquire
	 * @param filter LDAP filter - Do not use (objectclass=...), it is already used from serviceName parameter!
	 * @return service instance
	 */
	public abstract Object getServiceInstance(String serviceName, String filter);

	/**
	 * Tries to acquire all service instances from OSGi context.
	 * @param serviceName name of the service to acquire
	 * @param waitLimit time in [ms] to wait for service to be activated
	 * @return array of instances
	 */
	public Object[] getServiceInstances(String serviceName, int waitLimit);

	/**
	 * Tries to acquire all service instances from OSGi context.
	 * @param serviceName name of the service to acquire
	 * @return array of instances
	 */
	public Object[] getServiceInstances(String serviceName);

	/**
	 * Tries to acquire all service instances from OSGi context using given filter.
	 * @param serviceName name of the service to acquire
	 * @param filter LDAP filter - Do not use (objectclass=...), it is already used from serviceName parameter!
	 * @param waitLimit time in [ms] to wait for service to be activated
	 * @return array of instances
	 */
	public Object[] getServiceInstances(String serviceName, String filter, int waitLimit);

	/**
	 * Tries to acquire all service instances from OSGi context usign given filter.
	 * @param serviceName name of the service to acquire
	 * @param filter LDAP filter - Do not use (objectclass=...), it is already used from serviceName parameter!
	 * @return array of instances
	 */
	public Object[] getServiceInstances(String serviceName, String filter);

	/**
	 * Returns ServiceTracker for given service. If the tracker does not exist, it creates and stores new one.
	 * @param serviceName service to track
	 * @param filter LDAP filter - Do not use (objectclass=...), it is already used from serviceName parameter!
	 * @param should this tracker track all instances?
	 * @return corresponding ServiceTracker
	 */
	public abstract ServiceTracker<?, ?> getTracker(String serviceName, String filter, boolean tractAllServices);

	/**
	 * Create ServiceTracker for current OSGi context and given service.
	 * @param serviceName name of the tracked service
	 * @param filter LDAP filter - Do not use (objectclass=...), it is already used from serviceName parameter!
	 * @param should this tracker track all instances?
	 */
	public abstract void createServiceTracker(String serviceName, String filter, boolean tractAllServices);

	/**
	 * Sets the stopped flag so that method getServiceInstance(String serviceName, int waitLimit) can stop waiting for service.
	 */
	public void stopWaiting();
}