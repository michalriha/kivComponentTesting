package cz.zcu.kiv.bp.uniplayer;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
//import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.JAXBException;

import org.apache.commons.lang.reflect.ConstructorUtils;
import org.apache.commons.lang.reflect.MethodUtils;
import org.osgi.framework.Bundle;
//import org.osgi.framework.BundleContext;
//import org.osgi.framework.ServiceReference;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventAdmin;
//import org.osgi.util.tracker.ServiceTracker;
//import org.springframework.osgi.context.BundleContextAware;
import org.xml.sax.SAXException;

import cz.zcu.kiv.bp.datatypes.bindings.TCustomTypeData;
import cz.zcu.kiv.bp.datatypes.bindings.TCustomTypesSupport;
import cz.zcu.kiv.bp.datatypes.bindings.TExternalFactory;
import cz.zcu.kiv.bp.datatypes.bindings.TImportedType;
import cz.zcu.kiv.bp.datatypes.bindings.TListOfValuesOfImportedTypes;
import cz.zcu.kiv.bp.datatypes.bindings.TValueOfImportedType;
import cz.zcu.kiv.bp.datatypes.bindings.adapted.CustomTypesRegistry;
import cz.zcu.kiv.bp.probe.IProbe;
import cz.zcu.kiv.bp.probe.NoSuchBundleException;
import cz.zcu.kiv.bp.uniplayer.bindings.IScenario;
import cz.zcu.kiv.bp.uniplayer.bindings.IScenarioIterator;
import cz.zcu.kiv.bp.uniplayer.bindings.Scenario;
import cz.zcu.kiv.bp.uniplayer.bindings.TCall;
import cz.zcu.kiv.bp.uniplayer.bindings.TCommand;
//import cz.zcu.kiv.bp.uniplayer.bindings.TEvent;
import cz.zcu.kiv.bp.uniplayer.bindings.TEvent2;
import cz.zcu.kiv.bp.uniplayer.bindings.TReturnedValueReference;
import cz.zcu.kiv.bp.uniplayer.bindings.adapted.Event2Property;

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
	
	/**
	 * Custom types support structure
	 */
	private TCustomTypesSupport custTypesStruct;
	
	private Map<String, Object> customTypeValues = new HashMap<>();
	
	private Map<String, Class<?>> customTypeClasses = new HashMap<>();
	
	private Map<String, Object> storedReturnValues = new HashMap<>();
	
	private Map<String, Class<?>> storedReturnClasses = new HashMap<>();

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
			TEvent2 event = currentCommand.getEvent();
			
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
//			else if (event != null)
//			{
//				System.out.printf(
//					" %s/%s[%s]%n",
//					event.getTopic(),
//					event.getKey(),
//					event.getArgument()
//				);
//				_.execute(event);
//			}
			else if (event != null)
			{
				System.out.printf(
					"topic: %s%n" +
					"\tproperties: %n\t\t%s%n",
					event.getTopic(),
					event.getEventProperties().toString()
				);
				_.execute(event);
			}
			
			System.out.println();
			
			_.eventAdmin.sendEvent(new Event(IPlayer.SIMULATION_PLAYER_EVENT_TOPIC_FINISH, actionEventArgs));
			Thread.sleep(_.scenario.getSimulStepDelay());
		}
	}

//	/**
//	 * Fires given event.
//	 * @param event
//	 */
//	private void execute(TEvent event)
//	{
//        HashMap<String, Object> arguments = new HashMap<>();
//        arguments.put(event.getKey(), event.getArgument().getValue());
//        _.eventAdmin.sendEvent(new Event(event.getTopic(), arguments));
//	}
	
	private void execute(TEvent2 event)
	{
		Map<String, Object> actualProperties = new HashMap<>();
		for (Event2Property property : event.getEventProperties())
		{
			Object propValue = property.getValue();
			Class<?> valueType = property.getType();
			if (valueType == TCustomTypeData.class)
			{
				TCustomTypeData value = (TCustomTypeData) property.getValue();
				String key = value.getRef().getId();
				propValue = _.customTypeValues.get(key);
				System.out.printf(
					"\t\tCustom data type found: %s %s%n",
					_.customTypeClasses.get(key),
					propValue
				);
			}
			else if (valueType == TReturnedValueReference.class)
			{
				TReturnedValueReference value = (TReturnedValueReference) property.getValue();
				String key = value.getRef();
				if (_.storedReturnValues.containsKey(key))
				{
					propValue = _.storedReturnValues.get(key);
					System.out.printf(
						"\t\tReference to returned value found: %s %s%n",
						_.storedReturnClasses.get(key),
						propValue
					);
				}
				else
				{
					System.out.printf("\t\tReferenced value %s does not exist.%n", key);
				}
			}
			actualProperties.put(property.getKey(), propValue);
		}
		
		try
		{
			_.eventAdmin.sendEvent(new Event(event.getTopic(), actualProperties));
		} catch (Throwable ignore) {}
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
		Class<?>[] types = call.getArguments().getTypes();
		Object[] values = call.getArguments().toArray();
		String key;
		for (int i = 0; i < types.length; i++)
		{
			if (types[i] == TCustomTypeData.class)
			{
				TCustomTypeData value = (TCustomTypeData) values[i];
				key = value.getRef().getId();
				values[i] = _.customTypeValues.get(key);
				types[i] = _.customTypeClasses.get(key);
				System.out.printf(
					"\t\tCustom data type found: %s %s%n",
					types[i],
					values[i]
				);
			}
			else if (types[i] == TReturnedValueReference.class)
			{
				TReturnedValueReference value = (TReturnedValueReference) values[i];
				key = value.getRef();
				if (_.storedReturnClasses.containsKey(key) && _.storedReturnValues.containsKey(key))
				{
					values[i] = _.storedReturnValues.get(key);
					types[i] = _.storedReturnClasses.get(key);
					System.out.printf(
						"\t\tReference to returned value found: %s %s%n",
						types[i],
						values[i]
					);
				}
			}
		}
		
		try
		{ // try to execute required action
			System.out.println("\t\tinstance: " + serviceInstance);
			Method invokedMethod = MethodUtils.getMatchingAccessibleMethod(
				serviceInstance.getClass(),
				call.getMethod(),
				types
			);
			if (invokedMethod == null) throw new NoSuchMethodException();
			
			// apache method utils used for it's better matching capabilities
			Object returnedValue = invokedMethod.invoke(
				serviceInstance,
				values
			);
			
			Class<?> returnedType = invokedMethod.getReturnType();
			if (returnedType != void.class)
			{ // invoked method does not return any value
				_.storedReturnValues.put(call.getReturnedValueId(), returnedValue);
				_.storedReturnClasses.put(call.getReturnedValueId(), returnedType);
			}
		}
		catch (NoSuchMethodException ex)
		{ // required method does not exist
			System.out.printf(
				"Service does not provide method %s (%s). skipping ...%n",
				call.getMethod(),
				printTypes(types)
			);
		}
		catch (
			IllegalAccessException
			| IllegalArgumentException
			| InvocationTargetException e)
		{ // invocation failed
			System.out.printf(
				"Invocation of method %s in service %s (filter: %s) failled.%n\targuments: %s (%s)%n\tstack trace:%n",
				call.getMethod(),
				call.getService(),
				call.getFilter(),
				Arrays.deepToString(values),
				Arrays.deepToString(types)
			);
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
		
		String filter = call.getFilter();
		
		if (call.isUseAllServicesAvailable())
		{ // find all service instances
			serviceInstances = _.envProbe.getServiceInstances(call.getService(), filter == null ? "" : filter, IProbe.DEFAULT_WAIT_LIMIT);
		}
		else
		{ // find single service instance
			Object singleInstance = _.envProbe.getServiceInstance(call.getService(), filter == null ? "" : filter, IProbe.DEFAULT_WAIT_LIMIT);
			if (singleInstance != null)
			{
				serviceInstances = new Object[] {singleInstance};
			}
		}
		return serviceInstances;
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
			sb.append(type.getCanonicalName());
			sb.append("; ");
		}
		return sb.length() == 0 ? void.class.getName() : sb.toString();
	}

	private String printValues(Object[] values)
	{
		StringBuilder sb = new StringBuilder();
		
		for (Object o : values)
		{
			if (o == null)
			{
				sb.append("null");
			}
			else if (o.getClass().isArray())
			{
				sb.append(Arrays.deepToString((Object[]) o) + "( array of: " + o.getClass().getComponentType().getCanonicalName() + ")");
			}
			else
			{
				sb.append(o.toString());
			}
			sb.append("; ");
		}
		
		return sb.toString();
	}
	
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
		_.custTypesStruct = _.scenario.getCustomTypesSupportStructure();
		_.loadCustomTypesAndValues();
	}

	private void loadCustomTypesAndValues() throws JAXBException
	{
		for (TValueOfImportedType value : _.custTypesStruct.getListOfValues().getValues())
		{
			System.out.println("loading type " + value.getType());
			String valueId = value.getId();
			String typeName = value.getType();
			TImportedType typeDescription = _.custTypesStruct.getListOfTypes().get(typeName);
			_.checkTypeNameEquality(typeName, typeDescription);

			
			Object[] argumentValues = value.getArguments().toArray();
			Class<?>[] argumentTypes = value.getArguments().getTypes();
			// prepare empty data for case the loading fails
			Object customValue = null;
			Class<?> customClazz = void.class;
			// string describing OSGi bundle
			String exportingBundleName = String.format(
				"%s:%s",
				typeDescription.getBundle(),
				typeDescription.getVersion()
			);
			try
			{
				Bundle bndl = _.envProbe.findBundle(exportingBundleName);
				customClazz = _.envProbe.findClassInBundle(bndl, typeDescription.getCannonicalName());

				if ((customClazz.isInterface() || Modifier.isAbstract(customClazz.getModifiers())) && typeDescription.getFactory().getExternal() == null)
				{ // Can not directly instantiate interface
					throw new InstantiationException(String.format(
						"Custom type %s is either interface of abstract class but factory class is not defined. Unable to create instance.",
						customClazz
					));
				}
				
				if (typeDescription.getFactory().getConstructor() != null)
				{ // creating instance by constructor
					customValue = ConstructorUtils.invokeConstructor(
						customClazz,
						argumentValues,
						argumentTypes
					);
				}
				else if (typeDescription.getFactory().getStaticMember() != null)
				{ // creating an instance by static factory method on the same class
					String factoryMethodName = typeDescription.getFactory().getStaticMember().getMethod();
					
					customValue = _.invokeStaticFactoryMethod(
						argumentValues,
						argumentTypes,
						customClazz,
						factoryMethodName
					);
				}
				else if (typeDescription.getFactory().getExternal() != null)
				{ // creating instance of interface
					TExternalFactory fact = typeDescription.getFactory().getExternal();
					Bundle factBundle = _.envProbe.findBundle(fact.getBundle().getName() + ":" + fact.getBundle().getVersion());
					Class<?> factClazz = _.envProbe.findClassInBundle(factBundle, fact.getMethod().getClazz());
					String factoryMethodName = fact.getMethod().getName();
					
					customValue = _.invokeStaticFactoryMethod(
						argumentValues,
						argumentTypes,
						factClazz,
						factoryMethodName
					);
				}
				else
				{ // some strange conditions caused that document contains other type of factory type
				  // for creating instances of given class, document should never be successfully validated
				  // and therefore the loading should never get here
					throw new JAXBException(String.format(
						"Unknow factory type for custom type %s.",
						customClazz.getCanonicalName()
					));
				}
			}
			catch (NoSuchBundleException ex)
			{ // bundle not active
				System.out.printf(
					"Bundle % not found or not accessible. Empty data stored.%n",
					exportingBundleName
				);
			}
			catch (ClassNotFoundException e)
			{ // bundle does not contain described class
				System.out.printf(
					"Class %s or it's factory class not found or not accessible in bundle %s. Empty data storred.%n\tException: %s%n",
					typeDescription.getCannonicalName(),
					exportingBundleName,
					e.getMessage()
				);
			}
			catch (NoSuchMethodException e)
			{ //  described class does not have compatible method / constructor
				System.out.printf(
					"Class %s has no compatible constructor/Method for arguments %s. Empty data storred.%n\tException: %s%n",
					typeName,
					_.printTypes(argumentTypes),
					e.getMessage()
				);
			}
			catch (InstantiationException e)
			{ // instantiation failed
				System.out.printf("Unable to create new instance of class %s .%n\tException: %s%n", typeName, e.getMessage());
			}
			catch (IllegalArgumentException e)
			{
				System.out.printf(
					"Invocation of factory method for class %s with arguments %s (%s) has failled.%n\tException: %s%n",
					customClazz.getCanonicalName(),
					_.printTypes(argumentTypes),
					_.printValues(argumentValues),
					e.getMessage()
				);
				
				Integer[] i = new Integer[]{0, 1, 2};
				System.out.println(_.printValues(new Object[]{i}));
			}
			catch (Throwable e)
			{
				System.out.printf("Unable to create new instance of class %s .%n\tException: %s%n", typeName, e.getMessage());
				e.printStackTrace();
			}
			finally
			{ // always store the value, because the scenario will try to load that value
			  // and we would have to check it's existence
				_.customTypeValues.put(valueId, customValue);
				_.customTypeClasses.put(valueId, customClazz);
			}
		}
	}

	private Object invokeStaticFactoryMethod(
		Object[] argumentValues,
		Class<?>[] argumentTypes,
		Class<?> factClazz,
		String factoryMethodName)
	throws NoSuchMethodException, IllegalAccessException, InvocationTargetException
	{
		Object customValue;
		Method factoryMethod = MethodUtils.getMatchingAccessibleMethod(
			factClazz,
			factoryMethodName,
			argumentTypes
		);
		if (factoryMethod == null)
		{ // custom type class does not have any method with described name
			throw new NoSuchMethodException(String.format(
				"Factory method %s not found in class %s.%n",
				factoryMethodName,
				factClazz
			));
		}
		if (!Modifier.isStatic(factoryMethod.getModifiers()))
		{ // class has method with describe name, but it is not static
			throw new NoSuchMethodException(String.format(
				"Factory method %s found in class %s is not static.%n",
				factoryMethodName,
				factClazz
			));
		}
		customValue = factoryMethod.invoke(null, argumentValues);
		return customValue;
	}

	private void checkTypeNameEquality(String typeName, TImportedType typeDescription)
	throws JAXBException
	{
		if (typeDescription == null)
		{ // referencing key references non-existent type import
		  // document should never be successfully validated and therefore the loading should never get here
			throw new JAXBException(
				"Type attribute of VALUE element has to reference existing " +
			    "TYPE element. Unable to continue with current scenario."
			);
		}
		if (!typeName.equals(typeDescription.getCannonicalName()))
		{ // some strange conditions caused that referencing key differs from referenced key,
		  // document should never be successfully validated and therefore the loading should never get here
			throw new JAXBException(
				"Type attribute of VALUE element has to be equal to cannonical-name " +
			    "of TYPE element. Unable to continue with current scenario."
			);
		}
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
