package cz.zcu.kiv.bp.unimocker.bindings.adapted;

public class IntegerCollectionAdapter extends MyCollectionAdapter<Integer>
{
	public IntegerCollectionAdapter()
	{
		this.collectionWrapper = IntegerCollection.class;
	}
}
