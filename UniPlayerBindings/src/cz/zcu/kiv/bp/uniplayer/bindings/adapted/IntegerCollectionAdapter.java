package cz.zcu.kiv.bp.uniplayer.bindings.adapted;

public class IntegerCollectionAdapter extends MyCollectionAdapter<Integer>
{
	public IntegerCollectionAdapter()
	{
		this.collectionWrapper = IntegerCollection.class;
	}
}
