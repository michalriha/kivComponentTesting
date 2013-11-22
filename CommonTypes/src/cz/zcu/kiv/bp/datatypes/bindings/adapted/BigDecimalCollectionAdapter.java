package cz.zcu.kiv.bp.datatypes.bindings.adapted;

import java.math.BigDecimal;

/**
 * Adapter for BigDecimalCollection
 * @author Michal
 *
 */
public class BigDecimalCollectionAdapter extends MyCollectionAdapter<BigDecimal>
{
	@Override
	protected Class<? extends MyCollection<BigDecimal>> getCollectionWrapper()
	{
		return BigDecimalCollection.class;
	}
}
