package cz.zcu.kiv.bp.uniplayer.bindings;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Java class for TEvent2Property complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 *  &lt;complexType name="TEvent2Property">
 *      &lt;complexContent>
 *          &lt;extension base="{http://www.kiv.zcu.cz/component-testing/player}TValue">
 *              &lt;attribute name="key" type="{http://www.w3.org/2001/XMLSchema}string" />
 *          &lt;extension>
 *      &lt;complexContent>
 *  &lt;complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TEvent2Property", propOrder = {
	"key"
})
public class TEvent2Property extends TValue
{
	@XmlAttribute(name = "key")
	protected String key;

	/**
     * Gets the value of the key property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKey() {
		return key;
	}

    /**
     * Sets the value of the recurrence property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKey(String value) {
		this.key = value;
	}

}
