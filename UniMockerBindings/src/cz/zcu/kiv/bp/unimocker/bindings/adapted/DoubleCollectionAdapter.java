package cz.zcu.kiv.bp.unimocker.bindings.adapted;

/**
 * Adapter for DoubleCollection
 * @author Michal
 *
 */
public class DoubleCollectionAdapter extends MyCollectionAdapter<Double>
{
	public DoubleCollectionAdapter()
	{
		this.collectionWrapper = DoubleCollection.class;
	}
}
