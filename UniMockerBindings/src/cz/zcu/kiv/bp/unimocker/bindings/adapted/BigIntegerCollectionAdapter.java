package cz.zcu.kiv.bp.unimocker.bindings.adapted;

import java.math.BigInteger;

public class BigIntegerCollectionAdapter extends MyCollectionAdapter<BigInteger>
{	
	public BigIntegerCollectionAdapter()
	{
		this.collectionWrapper = BigIntegerCollection.class;
	}
}
