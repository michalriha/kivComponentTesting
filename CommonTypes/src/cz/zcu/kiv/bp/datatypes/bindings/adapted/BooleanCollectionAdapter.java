package cz.zcu.kiv.bp.datatypes.bindings.adapted;

/**
 * Adapter for BooleanlCollection
 * @author Michal
 *
 */
public class BooleanCollectionAdapter extends MyCollectionAdapter<Boolean>
{
	@Override
	protected Class<? extends MyCollection<Boolean>> getCollectionWrapper()
	{
		return BooleanCollection.class;
	}
}
