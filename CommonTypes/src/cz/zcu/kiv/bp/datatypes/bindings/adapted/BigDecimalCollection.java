package cz.zcu.kiv.bp.datatypes.bindings.adapted;

import java.math.BigDecimal;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * MyCollection for BigDecimal numbers
 * @author Michal
 */
@XmlJavaTypeAdapter(BigDecimalCollectionAdapter.class)
public class BigDecimalCollection extends MyCollection<BigDecimal>
{
	private static final long serialVersionUID = -764355562059209332L;
}
