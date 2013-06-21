package cz.zcu.kiv.bp.unimocker.bindings.adapted;

public class ShortCollectionAdapter extends MyCollectionAdapter<Short>
{
	public ShortCollectionAdapter()
	{
		this.collectionWrapper = ShortCollection.class;
	}
}
