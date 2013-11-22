package cz.zcu.kiv.bp.datatypes.bindings.adapted;

/**
 * Adapter for ByteCollection
 * @author Michal
 *
 */
public class ByteCollectionAdapter extends MyCollectionAdapter<Byte>
{
	@Override
	protected Class<? extends MyCollection<Byte>> getCollectionWrapper()
	{
		return ByteCollection.class;
	}
}
