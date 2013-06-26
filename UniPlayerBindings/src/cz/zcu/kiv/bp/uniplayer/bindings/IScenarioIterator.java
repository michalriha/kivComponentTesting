package cz.zcu.kiv.bp.uniplayer.bindings;

import java.util.Iterator;

/**
 * Iterator for simple iterating over the loaded scenario. 
 * @author Michal
 *
 */
public interface IScenarioIterator extends Iterator<TCommand>
{
	/**
	 * Returns simulation time of current position.
	 * @return
	 */
	long getCurrentTime();
	
	/**
	 * Returns simulation time of next position.
	 * @return
	 */
	long getNextTime();
}
