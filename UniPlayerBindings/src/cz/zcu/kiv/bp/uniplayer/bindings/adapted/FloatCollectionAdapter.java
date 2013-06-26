package cz.zcu.kiv.bp.uniplayer.bindings.adapted;

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
