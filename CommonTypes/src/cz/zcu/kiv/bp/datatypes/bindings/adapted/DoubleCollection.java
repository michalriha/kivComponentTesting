package cz.zcu.kiv.bp.datatypes.bindings.adapted;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * MyCollection for Double numbers
 * @author Michal
 */
@XmlJavaTypeAdapter(DoubleCollectionAdapter.class)
public class DoubleCollection extends MyCollection<Double>
{
	private static final long serialVersionUID = -923060142701526588L;
}
