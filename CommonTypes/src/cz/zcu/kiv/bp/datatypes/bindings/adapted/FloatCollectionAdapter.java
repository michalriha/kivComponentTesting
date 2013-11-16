package cz.zcu.kiv.bp.datatypes.bindings.adapted;

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
