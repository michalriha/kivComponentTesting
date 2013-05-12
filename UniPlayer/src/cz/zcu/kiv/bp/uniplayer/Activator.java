package cz.zcu.kiv.bp.uniplayer;

import org.eclipse.osgi.framework.console.CommandProvider;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import cz.zcu.kiv.bp.uniplayer.bindings.IScenario;
import cz.zcu.kiv.bp.uniplayer.bindings.IScenarioIterator;
import cz.zcu.kiv.bp.uniplayer.bindings.Scenario;
import cz.zcu.kiv.bp.uniplayer.bindings.TCall;
import cz.zcu.kiv.bp.uniplayer.bindings.TCommand;
import cz.zcu.kiv.bp.uniplayer.bindings.TEvent;

public class Activator implements BundleActivator, IPlayer {

	private static BundleContext context;

	private Activator _ = this;
	
	private Commander commander;
	
	private IScenario loader;
	
	private volatile boolean stopped = false;
	
	private ServiceRegistration<CommandProvider> reg;
	
	static BundleContext getContext()
	{
		return context;
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext bundleContext) throws Exception
	{
		Activator.context = bundleContext;
		_.loader = new Scenario();
		_.commander = new Commander();
		_.commander.setLdr(_.loader);
		_.commander.setPlr(this);
		_.reg = Activator.context.registerService(
			CommandProvider.class,
			_.commander,
			null
		);
		
		System.out.println("\n" + _.commander.getHelp());
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception
	{
		Activator.context = null;
		_.reg.unregister();
	}

	@Override
	public void play() throws Exception
	{
		_.stopped = false;	
		IScenarioIterator iter = _.loader.iterator();
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
					" %s.%s",
					call.getService(),
					call.getMethod()
				);
			}
			else if (event != null)
			{
				System.out.printf(
					" %s/%s",
					event.getTopic(),
					event.getKey()
				);
			}
			System.out.println();
			
			Thread.sleep(_.loader.getSimulStepDelay());
		}
	}

	@Override
	public void stop()
	{
		_.stopped = true;
	}

}
