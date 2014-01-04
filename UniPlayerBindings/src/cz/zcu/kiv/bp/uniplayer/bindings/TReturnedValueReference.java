package cz.zcu.kiv.bp.uniplayer.bindings;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
/**
 * <p>Java class for TReturnedValueReference complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 *  &lt;complexType name="TReturnedValueReference">
 *      &lt;attribute name="ref" type="{http://www.w3.org/2001/XMLSchema}string" />
 *  &lt;complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TReturnedValueReference", propOrder = {
	"ref"
})
public class TReturnedValueReference
{
	@XmlAttribute(name = "ref")
	protected String ref;

	/**
     * Gets the value of the ref property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRef() {
		return ref;
	}

    /**
     * Sets the value of the ref property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRef(String value) {
		this.ref = value;
	}
}
