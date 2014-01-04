package cz.zcu.kiv.bp.uniplayer.bindings;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Java class for TCommand complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TCommand">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;element name="call" type="{http://www.kiv.zcu.cz/component-testing/player}TCall"/>
 *         &lt;element name="event" type="{http://www.kiv.zcu.cz/component-testing/player}TEvent"/>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TCommand", propOrder = {
    "call",
    "event"
})
public class TCommand {

    protected TCall call;
    protected TEvent2 event;

    /**
     * Gets the value of the call property.
     * 
     * @return
     *     possible object is
     *     {@link TCall }
     *     
     */
    public TCall getCall() {
        return call;
    }

    /**
     * Sets the value of the call property.
     * 
     * @param value
     *     allowed object is
     *     {@link TCall }
     *     
     */
    public void setCall(TCall value) {
        this.call = value;
    }

    /**
     * Gets the value of the event property.
     * 
     * @return
     *     possible object is
     *     {@link TEvent2 }
     *     
     */
    public TEvent2 getEvent() {
        return event;
    }

    /**
     * Sets the value of the event property.
     * 
     * @param value
     *     allowed object is
     *     {@link TEvent2 }
     *     
     */
    public void setEvent(TEvent2 value) {
        this.event = value;
    }

}
