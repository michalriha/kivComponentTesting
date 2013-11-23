package cz.zcu.kiv.bp.uniplayer.bindings.old.adapted;

/**
 * Adapter for FloatCollection
 * @author Michal
 *
 */
public class FloatCollectionAdapter extends MyCollectionAdapter<Float>
{
	public FloatCollectionAdapter()
	{
		this.collectionWrapper = FloatCollection.class;
	}
}
