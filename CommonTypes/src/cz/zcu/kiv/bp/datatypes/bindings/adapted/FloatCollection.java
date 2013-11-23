package cz.zcu.kiv.bp.datatypes.bindings.adapted;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * MyCollection for Float numbers
 * @author Michal
 */
@XmlJavaTypeAdapter(FloatCollectionAdapter.class)
public class FloatCollection extends MyCollection<Float>
{
	private static final long serialVersionUID = 2618856988537836188L;
}
