package cz.zcu.kiv.bp.uniplayer.bindings;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TReturnedValueReference", propOrder = {
	"ref"
})
public class TReturnedValueReference
{
	@XmlAttribute(name = "ref")
	protected String ref;

	public String getRef() {
		return ref;
	}

	public void setRef(String ref) {
		this.ref = ref;
	}
}
