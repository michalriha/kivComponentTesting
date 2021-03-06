package cz.zcu.kiv.bp.uniplayer.bindings;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TExponential complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TExponential">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="rate" use="required" type="{http://www.kiv.zcu.cz/component-testing/player}TNonNegativeLong" />
 *       &lt;attribute name="time-span" use="required" type="{http://www.kiv.zcu.cz/component-testing/player}TNonNegativeLong" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TExponential")
public class TExponential {

    @XmlAttribute(name = "rate", required = true)
    protected long rate;
    @XmlAttribute(name = "time-span", required = true)
    protected long timeSpan;

    /**
     * Gets the value of the rate property.
     * 
     */
    public long getRate() {
        return rate;
    }

    /**
     * Sets the value of the rate property.
     * 
     */
    public void setRate(long value) {
        this.rate = value;
    }

    /**
     * Gets the value of the timeSpan property.
     * 
     */
    public long getTimeSpan() {
        return timeSpan;
    }

    /**
     * Sets the value of the timeSpan property.
     * 
     */
    public void setTimeSpan(long value) {
        this.timeSpan = value;
    }

}
