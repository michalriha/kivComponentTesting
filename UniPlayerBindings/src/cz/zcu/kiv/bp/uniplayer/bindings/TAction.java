package cz.zcu.kiv.bp.uniplayer.bindings;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import cz.zcu.kiv.bp.uniplayer.bindings.adapted.Recurrence;


/**
 * <p>Java class for TAction complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TAction">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="recurrence" type="{http://www.kiv.zcu.cz/component-testing/player}TRecurrence" minOccurs="0"/>
 *         &lt;element name="command" type="{http://www.kiv.zcu.cz/component-testing/player}TCommand"/>
 *       &lt;/sequence>
 *       &lt;attribute name="time" use="required" type="{http://www.w3.org/2001/XMLSchema}long" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TAction", propOrder = {
    "recurrence",
    "command"
})
public class TAction {

    @XmlElement(type = TRecurrence.class)
    protected Recurrence recurrence;
    @XmlElement(required = true)
    protected TCommand command;
    @XmlAttribute(name = "time", required = true)
    protected long time;

    /**
     * Gets the value of the recurrence property.
     * 
     * @return
     *     possible object is
     *     {@link TRecurrence }
     *     
     */
    public Recurrence getRecurrence() {
        return recurrence;
    }

    /**
     * Sets the value of the recurrence property.
     * 
     * @param value
     *     allowed object is
     *     {@link TRecurrence }
     *     
     */
    public void setRecurrence(Recurrence value) {
        this.recurrence = value;
    }

    /**
     * Gets the value of the command property.
     * 
     * @return
     *     possible object is
     *     {@link TCommand }
     *     
     */
    public TCommand getCommand() {
        return command;
    }

    /**
     * Sets the value of the command property.
     * 
     * @param value
     *     allowed object is
     *     {@link TCommand }
     *     
     */
    public void setCommand(TCommand value) {
        this.command = value;
    }

    /**
     * Gets the value of the time property.
     * 
     */
    public long getTime() {
        return time;
    }

    /**
     * Sets the value of the time property.
     * 
     */
    public void setTime(long value) {
        this.time = value;
    }

}
