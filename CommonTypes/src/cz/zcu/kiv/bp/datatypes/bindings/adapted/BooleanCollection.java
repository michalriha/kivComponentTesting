package cz.zcu.kiv.bp.datatypes.bindings.adapted;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * MyCollection for Boolean values
 * @author Michal
 */
@XmlJavaTypeAdapter(BooleanCollectionAdapter.class)
public class BooleanCollection extends MyCollection<Boolean>
{
	private static final long serialVersionUID = -2944969404401831285L;
}
