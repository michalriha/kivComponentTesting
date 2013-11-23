package cz.zcu.kiv.bp.datatypes.bindings.adapted;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * MyCollection for Integer numbers
 * @author Michal
 */
@XmlJavaTypeAdapter(IntegerCollectionAdapter.class)
public class IntegerCollection extends MyCollection<Integer>
{
	private static final long serialVersionUID = 8307838773725057281L;
}
