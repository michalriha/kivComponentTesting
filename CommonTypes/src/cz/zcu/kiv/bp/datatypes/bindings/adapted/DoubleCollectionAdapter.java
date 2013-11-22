package cz.zcu.kiv.bp.datatypes.bindings.adapted;

/**
 * Adapter for DoubleCollection
 * @author Michal
 *
 */
public class DoubleCollectionAdapter extends MyCollectionAdapter<Double>
{
	@Override
	protected Class<? extends MyCollection<Double>> getCollectionWrapper()
	{
		return DoubleCollection.class;
	}
}
