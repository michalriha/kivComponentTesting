package cz.zcu.kiv.bp.datatypes.bindings.adapted;

import java.math.BigDecimal;

/**
 * Adapter for BigDecimalCollection
 * @author Michal
 *
 */
public class BigDecimalCollectionAdapter extends MyCollectionAdapter<BigDecimal>
{
	public BigDecimalCollectionAdapter()
	{
		this.collectionWrapper = BigDecimalCollection.class;
	}
}
