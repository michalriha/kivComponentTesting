package cz.zcu.kiv.bp.uniplayer.bindings.adapted;

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
