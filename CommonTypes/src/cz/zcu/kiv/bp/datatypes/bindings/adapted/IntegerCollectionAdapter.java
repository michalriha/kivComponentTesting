package cz.zcu.kiv.bp.datatypes.bindings.adapted;

/**
 * Adapter for IntegerCollection
 * @author Michal
 *
 */
public class IntegerCollectionAdapter extends MyCollectionAdapter<Integer>
{
	@Override
	protected Class<? extends MyCollection<Integer>> getCollectionWrapper()
	{
		return IntegerCollection.class;
	}
}
