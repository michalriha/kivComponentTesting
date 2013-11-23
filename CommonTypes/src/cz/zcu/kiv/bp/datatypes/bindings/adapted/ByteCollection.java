package cz.zcu.kiv.bp.datatypes.bindings.adapted;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * MyCollection for Byte numbers
 * @author Michal
 */
@XmlJavaTypeAdapter(ByteCollectionAdapter.class)
public class ByteCollection extends MyCollection<Byte>
{
	private static final long serialVersionUID = -2994576284398065298L;
}
