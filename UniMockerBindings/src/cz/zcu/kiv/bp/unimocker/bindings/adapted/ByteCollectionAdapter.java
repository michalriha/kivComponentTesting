package cz.zcu.kiv.bp.unimocker.bindings.adapted;

/**
 * Adapter for ByteCollection
 * @author Michal
 *
 */
public class ByteCollectionAdapter extends MyCollectionAdapter<Byte>
{
	public ByteCollectionAdapter()
	{
		this.collectionWrapper = ByteCollection.class;
	}
}
