package cz.zcu.kiv.bp.datatypes.bindings.adapted;

/**
 * Adapter for LongCollection
 * @author Michal
 *
 */
public class LongCollectionAdapter extends MyCollectionAdapter<Long>
{
	@Override
	protected Class<? extends MyCollection<Long>> getCollectionWrapper()
	{
		return LongCollection.class;
	}
}
