package cz.zcu.kiv.bp.unimocker.bindings.adapted;

public class LongCollectionAdapter extends MyCollectionAdapter<Long>
{
	public LongCollectionAdapter()
	{
		this.collectionWrapper = LongCollection.class;
	}
}
