package cz.zcu.kiv.bp.unimocker.bindings.adapted;

/**
 * Adapter for ShortCollection
 * @author Michal
 *
 */
public class ShortCollectionAdapter extends MyCollectionAdapter<Short>
{
	public ShortCollectionAdapter()
	{
		this.collectionWrapper = ShortCollection.class;
	}
}
