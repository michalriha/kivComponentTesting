package cz.zcu.kiv.bp.unimocker.bindings.adapted;

/**
 * Adapter for StringCollection
 * @author Michal
 *
 */
public class StringCollectionAdapter extends MyCollectionAdapter<String>
{
	public StringCollectionAdapter()
	{
		this.collectionWrapper = StringCollection.class;
	}
}
