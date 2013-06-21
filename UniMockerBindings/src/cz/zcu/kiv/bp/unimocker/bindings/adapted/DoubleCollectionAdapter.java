package cz.zcu.kiv.bp.unimocker.bindings.adapted;

public class DoubleCollectionAdapter extends MyCollectionAdapter<Double>
{
	public DoubleCollectionAdapter()
	{
		this.collectionWrapper = DoubleCollection.class;
	}
}
