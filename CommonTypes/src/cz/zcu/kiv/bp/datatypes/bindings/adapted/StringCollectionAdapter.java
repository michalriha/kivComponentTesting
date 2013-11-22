package cz.zcu.kiv.bp.datatypes.bindings.adapted;

/**
 * Adapter for StringCollection
 * @author Michal
 *
 */
public class StringCollectionAdapter extends MyCollectionAdapter<String>
{
	@Override
	protected Class<? extends MyCollection<String>> getCollectionWrapper()
	{
		return StringCollection.class;
	}
}
