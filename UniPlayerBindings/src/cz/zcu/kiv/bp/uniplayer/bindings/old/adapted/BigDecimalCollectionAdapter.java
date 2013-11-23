package cz.zcu.kiv.bp.uniplayer.bindings.old.adapted;

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
