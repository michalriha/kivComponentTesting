package cz.zcu.kiv.bp.datatypes.bindings.adapted;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * MyCollection for Long numbers
 * @author Michal
 */
@XmlJavaTypeAdapter(LongCollectionAdapter.class)
public class LongCollection extends MyCollection<Long>
{
	private static final long serialVersionUID = 4542051917154593629L;
}
