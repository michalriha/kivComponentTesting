package cz.zcu.kiv.bp.datatypes.bindings.adapted;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * MyCollection for Short numbers
 * @author Michal
 */
@XmlJavaTypeAdapter(ShortCollectionAdapter.class)
public class ShortCollection extends MyCollection<Short>
{
	private static final long serialVersionUID = -4430340326766549436L;
}
