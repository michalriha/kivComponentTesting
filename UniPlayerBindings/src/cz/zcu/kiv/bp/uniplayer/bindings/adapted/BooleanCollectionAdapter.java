package cz.zcu.kiv.bp.uniplayer.bindings.adapted;

/**
 * Adapter for BooleanlCollection
 * @author Michal
 *
 */
public class BooleanCollectionAdapter extends MyCollectionAdapter<Boolean>
{
	public BooleanCollectionAdapter()
	{
		this.collectionWrapper = BooleanCollection.class;
	}
}
