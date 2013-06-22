package cz.zcu.kiv.bp.unimocker;

//import ifcs.IPrinter;

import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.bind.JAXBException;

import org.apache.commons.lang.reflect.MethodUtils;
import org.eclipse.osgi.framework.console.CommandProvider;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.springframework.osgi.context.BundleContextAware;
import org.xml.sax.SAXException;

import cz.zcu.kiv.bp.unimocker.bindings.Scenario;
import cz.zcu.kiv.bp.unimocker.bindings.TSimulatedService;
import cz.zcu.kiv.bp.unimocker.bindings.adapted.BundlesMap;
import cz.zcu.kiv.bp.unimocker.bindings.adapted.Invocation;
import cz.zcu.kiv.bp.unimocker.bindings.adapted.InvokedMethod;
import cz.zcu.kiv.bp.unimocker.bindings.basics.InvalidFileException;

public class Mocker implements IMocker, BundleContextAware {

	private Mocker _ = this;
	
	private BundleContext context;
	
	private Scenario scenarioProject;
	
	private ServiceRegistration<CommandProvider> reg = null; 
    /**
     * ServiceRegistrations for mocks registered as OSGi services.
     */
    private List<ServiceRegistration<?>> serviceRegistrations = new LinkedList<ServiceRegistration<?>>();
	
    /**
     * Tries to found bundle described by string in format symbolic.name:Major.Minor.Micro.
     * Stops with the first bundle that has matching description! 
     * @param description symbolic.name:Major.Minor.Micro fromated bundle description
     * @return found Bundle instance or null when not found
     */
    private Bundle findBundle(String description)
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
		
		return ret;
	}

    /**
     * Tries to load given described from the given bundle.
     * @param bundle which should be probed
     * @param classesToFind array of class names to find
     * @return array of loaded classes
     * @throws ClassNotFoundException when bundles classloader fails to load class
     */
	private Class<?>[] findClassesInBundle(Bundle bundle, String[] classesToFind) throws ClassNotFoundException
	{
		List<Class<?>> ret = new ArrayList<>(classesToFind.length);

		for (String className : classesToFind)
		{
			ret.add(bundle.loadClass(className));
		}
		
		return ret.toArray(new Class<?>[0]);
	}

	/**
     * Tries to load given described from the given bundle.
     * @param mockedBundle which should be probed
     * @param classesToFind array of class names to find
     * @return array of loaded classes or null if loading was not successful
     */
	private Class<?>[] findClassesToMock(Bundle mockedBundle, String[] classesToFind)
	{
		Class<?>[] ret = null;
		try
		{
			ret = _.findClassesInBundle(mockedBundle, classesToFind);
		}
		catch (ClassNotFoundException ignore)
		{
			System.out.printf(
				"Class \"%s\" has not been found in bundle \"%s\".%n",
				ignore.getMessage(),
				mockedBundle
			);
		}
		return ret;
	}
	
	/**
	 * Creates mockup as instance of Java Proxy from given class and UniHandler object.
	 * Handler object if filled with invocation expectation. Object is registered as OSGi service.
	 * Service registration descriptor is stored for proper unregistration when bundle stops.
	 * @param clazz class to mock
	 * @param returns map of invocation expectations
	 */
	private void createService(
			Class<?> clazz,
			Map<Method, Map<Object[], Object>> returns)
	{
		// create mockup object
		Object mockup = Proxy.newProxyInstance(
			clazz.getClassLoader(),
			new Class<?> [] { clazz },
			new UniHandler(clazz, returns)
		);
		
		// register object as service
		// TODO implement the possibility to add property map for service object
		ServiceRegistration<?> reg = _.context.registerService(clazz.getCanonicalName(), mockup, null);
		_.serviceRegistrations.add(reg);
	}

	/**
	 * Build invocation expectations map for mockup object. Throws NoSuchMethodException when mocked
	 * class does not have method that match description in description map.
	 * @param clazz Class that will be mocked.
	 * @param simulation Expectations description as binding object.
	 * @return Expectations map
	 * @throws NoSuchMethodException
	 */
	private Map<Method, Map<Object[], Object>> buildInvocationPossibilitiesForClass(
		Class<?> clazz,
		TSimulatedService simulation)
	throws NoSuchMethodException
	{
		// Contains return values for all described methods.
		Map<Method, Map<Object[], Object>> returns = new HashMap<>();
		for (InvokedMethod methodToFind : simulation.getMethod())
		{
			for (Invocation inv : methodToFind.getInvocations())
			{
				Class<?>[] parameterTypes = inv.getArguments().getTypes();
				// Tries to match method w/o wrapper classes for primitive types.
				Method foundMethod = MethodUtils.getMatchingAccessibleMethod(
					clazz,
					methodToFind.getName(),
					parameterTypes
				);
				
				// If this method has not been yet described, create new collection.
				if (!returns.containsKey(foundMethod))
				{ // new method
					returns.put(foundMethod, new HashMap<Object[], Object>());
				}
				// Contains return values for current method and all possible invocation arguments. 
				Map<Object[], Object> methodsInvocationPossibilities = returns.get(foundMethod);
				methodsInvocationPossibilities.put(
					inv.getArguments().toArray(),
					inv.getReturnValue()
				);
			}
		}
		return returns;
	}

	public void stop() throws Exception
	{		
		// unregister mockups
		for (ServiceRegistration<?> reg : _.serviceRegistrations)
		{
			System.out.println("Unregistering service: " + reg);
			reg.unregister();
		}
	}

	@Override
	public void mock()
	{
		if (_.scenarioProject == null)
		{
			throw new IllegalStateException("Mockup scenario has not been loaded."); 
		}
		BundlesMap scenario = _.scenarioProject.getProject().getSimulatedComponents();
		for (Entry<String, HashMap<String, TSimulatedService>> bundle : scenario.entrySet())
		{
			Bundle mockedBundle = _.findBundle(bundle.getKey());
			if (mockedBundle == null)
			{
				System.out.printf("Bundle %s has not been found! Skipping bundle.%n", bundle.getKey());
				continue;
			}

			String[] classesToFind = bundle.getValue().keySet().toArray(new String[0]);
			Class<?>[] mockedClasses = _.findClassesToMock(mockedBundle, classesToFind);
			if (mockedClasses == null) continue;
			
			try
			{
				for (Class<?> clazz : mockedClasses)
				{
					// find all described invocations for current class
					TSimulatedService simulation = bundle.getValue().get(clazz.getName());
					Map<Method, Map<Object[], Object>> returns = _.buildInvocationPossibilitiesForClass(clazz, simulation);
					
					// building mockup
					_.createService(clazz, returns);
				}
			}
			catch (NoSuchMethodException ignore) { ignore.printStackTrace(); }
		}
	}
	
	@Override
	public void diag()
	{
		if (_.scenarioProject == null)
		{
			System.out.println("Mockup scenario has not been loaded.");
			return;
		}
		_.scenarioProject.diag();
	}

	@Override
	public void loadFile(String fileName)
    throws JAXBException, SAXException, InvalidFileException, IOException
	{
		_.scenarioProject = new Scenario();
		_.scenarioProject.loadFile(fileName);
	}

	@Override
	public void setBundleContext(BundleContext context)
	{
		_.context = context;
	}

}
