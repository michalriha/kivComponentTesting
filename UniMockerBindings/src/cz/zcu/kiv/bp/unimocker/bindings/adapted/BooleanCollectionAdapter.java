package cz.zcu.kiv.bp.unimocker.bindings.adapted;

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
