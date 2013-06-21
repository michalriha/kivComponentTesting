package cz.zcu.kiv.bp.unimocker.bindings.adapted;

import java.math.BigDecimal;

public class BigDecimalCollectionAdapter extends MyCollectionAdapter<BigDecimal>
{
	public BigDecimalCollectionAdapter()
	{
		this.collectionWrapper = BigDecimalCollection.class;
	}
}
