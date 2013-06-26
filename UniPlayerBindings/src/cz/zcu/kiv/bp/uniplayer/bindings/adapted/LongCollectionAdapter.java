package cz.zcu.kiv.bp.uniplayer.bindings.adapted;

/**
 * Adapter for LongCollection
 * @author Michal
 *
 */
public class LongCollectionAdapter extends MyCollectionAdapter<Long>
{
	public LongCollectionAdapter()
	{
		this.collectionWrapper = LongCollection.class;
	}
}
