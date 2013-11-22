package cz.zcu.kiv.bp.datatypes.bindings.adapted;

/**
 * Adapter for ShortCollection
 * @author Michal
 *
 */
public class ShortCollectionAdapter extends MyCollectionAdapter<Short>
{
	@Override
	protected Class<? extends MyCollection<Short>> getCollectionWrapper()
	{
		return ShortCollection.class;
	}
}
