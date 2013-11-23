package cz.zcu.kiv.bp.datatypes.bindings.adapted;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * MyCollection for Strings
 * @author Michal
 */
@XmlJavaTypeAdapter(StringCollectionAdapter.class)
public class StringCollection extends MyCollection<String>
{
	private static final long serialVersionUID = -9083327057165641724L;
}
