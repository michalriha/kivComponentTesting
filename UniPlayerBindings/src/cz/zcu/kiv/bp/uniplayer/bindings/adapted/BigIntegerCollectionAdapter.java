package cz.zcu.kiv.bp.uniplayer.bindings.adapted;

import java.math.BigInteger;

/**
 * Adapter for BigIntegerCollection
 * @author Michal
 *
 */
public class BigIntegerCollectionAdapter extends MyCollectionAdapter<BigInteger>
{	
	public BigIntegerCollectionAdapter()
	{
		this.collectionWrapper = BigIntegerCollection.class;
	}
}
