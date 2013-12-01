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
	 * @throws ClassNotFoundException when bundles classloader fails to load class
	 */
	public abstract Class<?>[] findClassesInBundle(Bundle bundle, String[] classesToFind)
	throws ClassNotFoundException;

	/**
	 * Tries to acquire service from OSGi context.
	 * @param serviceName name of the service to acquire
	 * @param waitLimit time in [ms] to wait for service to be activated
	 * @return service instance
	 */
	public abstract Object getServiceInstance(String serviceName, int waitLimit);

	/**
	 * Tries to acquire service from OSGi context.
	 * @param serviceName name of the service to acquire
	 * @return service instance
	 */
	public abstract Object getServiceInstance(String serviceName);

	/**
	 * Returns ServiceTracker for given service. If the tracker does not exist, it creates and stores new one.
	 * @param serviceName service to track
	 * @return corresponding ServiceTracker
	 */
	public abstract ServiceTracker<?, ?> getTracker(String serviceName);

	/**
	 * Create ServiceTracker for current OSGi context and given service.
	 * @param serviceName name of the tracked service
	 */
	public abstract void createServiceTracker(String serviceName);

	/**
	 * Sets the stopped flag so that method getServiceInstance(String serviceName, int waitLimit) can stop waiting for service.
	 */
	public void stopWaiting();
}