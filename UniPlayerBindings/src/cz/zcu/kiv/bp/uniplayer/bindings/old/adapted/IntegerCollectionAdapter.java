package cz.zcu.kiv.bp.uniplayer.bindings.old.adapted;

/**
 * Adapter for IntegerCollection
 * @author Michal
 *
 */
public class IntegerCollectionAdapter extends MyCollectionAdapter<Integer>
{
	public IntegerCollectionAdapter()
	{
		this.collectionWrapper = IntegerCollection.class;
	}
}