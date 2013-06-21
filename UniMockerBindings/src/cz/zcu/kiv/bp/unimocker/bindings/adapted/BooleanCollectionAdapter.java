package cz.zcu.kiv.bp.unimocker.bindings.adapted;

public class BooleanCollectionAdapter extends MyCollectionAdapter<Boolean>
{
	public BooleanCollectionAdapter()
	{
		this.collectionWrapper = BooleanCollection.class;
	}
}
