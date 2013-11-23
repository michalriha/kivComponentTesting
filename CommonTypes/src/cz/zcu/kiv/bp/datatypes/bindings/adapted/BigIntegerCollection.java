package cz.zcu.kiv.bp.datatypes.bindings.adapted;

import java.math.BigInteger;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * MyCollection for BigInteger numbers
 * @author Michal
 */
@XmlJavaTypeAdapter(BigIntegerCollectionAdapter.class)
public class BigIntegerCollection extends MyCollection<BigInteger>
{
	private static final long serialVersionUID = -8936724067703455601L;
}
