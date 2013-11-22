package cz.zcu.kiv.bp.datatypes.bindings.adapted;

/**
 * Adapter for FloatCollection
 * @author Michal
 *
 */
public class FloatCollectionAdapter extends MyCollectionAdapter<Float>
{
	@Override
	protected Class<? extends MyCollection<Float>> getCollectionWrapper()
	{
		return FloatCollection.class;
	}
}
