package cz.zcu.kiv.bp.unimocker.bindings.adapted;

public class ByteCollectionAdapter extends MyCollectionAdapter<Byte>
{
	public ByteCollectionAdapter()
	{
		this.collectionWrapper = ByteCollection.class;
	}
}