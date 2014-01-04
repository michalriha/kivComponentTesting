package cz.zcu.kiv.bp.uniplayer.bindings;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.osgi.service.event.Event;

import cz.zcu.kiv.bp.uniplayer.bindings.adapted.Event2Property;
import cz.zcu.kiv.bp.uniplayer.bindings.adapted.Event2PropertyAdapter;

/**
 * <p>Java class for TEvent2 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 *  &lt;complexType name="TEvent2">
 *      &lt;sequence>
 *          &lt;element name="property" type="{http://www.kiv.zcu.cz/component-testing/player}TEvent2Property" minOccurs="1" maxOccurs="unbounded" />
 *      &lt;sequence>
 *      &lt;attribute name="topic" type="{http://www.w3.org/2001/XMLSchema}string" />
 *  &lt;complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(name = "TEvent2", propOrder = {
	"topic",
	"eventProperties"
})
public class TEvent2
{
	protected String topic;
	
	protected List<Event2Property> eventProperties;

	protected Event cache = null;

	/**
     * Gets the value of the topic property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @XmlAttribute(name= "topic")
	public String getTopic() {
		return topic;
	}

    /**
     * Sets the value of the topic property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTopic(String value) {
		this.topic = value;
	}

    /**
     * Gets the value of the eventProperties property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the action property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEventProperties().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Event2Property }
     * 
     * 
     */
    @XmlElement(name = "property", type = TEvent2Property.class)
	@XmlJavaTypeAdapter(Event2PropertyAdapter.class)
	public List<Event2Property> getEventProperties() {
		if (eventProperties == null) {
			eventProperties = new ArrayList<>();
		}
		return eventProperties;
	}
}
