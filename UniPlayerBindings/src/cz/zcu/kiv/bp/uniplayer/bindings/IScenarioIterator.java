package cz.zcu.kiv.bp.uniplayer.bindings;

import java.util.Iterator;


public interface IScenarioIterator extends Iterator<TCommand>
{
	long getCurrentTime();
	long getNextTime();
}
