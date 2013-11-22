package cz.zcu.kiv.bp.datatypes.bindings.adapted;

import java.math.BigInteger;

/**
 * Adapter for BigIntegerCollection
 * @author Michal
 *
 */
public class BigIntegerCollectionAdapter extends MyCollectionAdapter<BigInteger>
{	
	@Override
	protected Class<? extends MyCollection<BigInteger>> getCollectionWrapper()
	{
		return BigIntegerCollection.class;
	}
}
