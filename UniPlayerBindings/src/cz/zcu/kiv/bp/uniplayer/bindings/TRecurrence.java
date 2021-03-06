package cz.zcu.kiv.bp.uniplayer.bindings;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TRecurrence complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TRecurrence">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;element name="equidistant" type="{http://www.kiv.zcu.cz/component-testing/player}TEquidistant"/>
 *         &lt;element name="gaussian" type="{http://www.kiv.zcu.cz/component-testing/player}TGaussian"/>
 *         &lt;element name="exponential" type="{http://www.kiv.zcu.cz/component-testing/player}TExponential"/>
 *       &lt;/choice>
 *       &lt;attribute name="count" type="{http://www.kiv.zcu.cz/component-testing/player}TPositiveLong" default="1" />
 *       &lt;attribute name="repeat-until" type="{http://www.kiv.zcu.cz/component-testing/player}TPositiveLong" default="9223372036854775807" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TRecurrence", propOrder = {
    "equidistant",
    "gaussian",
    "exponential"
})
public class TRecurrence {

    protected TEquidistant equidistant;
    protected TGaussian gaussian;
    protected TExponential exponential;
    
    @XmlAttribute(name = "count")
    protected Long count;
    
    @XmlAttribute(name = "repeat-until")
    protected Long repeatUntil;

    /**
     * Gets the value of the equidistant property.
     * 
     * @return
     *     possible object is
     *     {@link TEquidistant }
     *     
     */
    public TEquidistant getEquidistant() {
        return equidistant;
    }

    /**
     * Sets the value of the equidistant property.
     * 
     * @param value
     *     allowed object is
     *     {@link TEquidistant }
     *     
     */
    public void setEquidistant(TEquidistant value) {
        this.equidistant = value;
    }

    /**
     * Gets the value of the gaussian property.
     * 
     * @return
     *     possible object is
     *     {@link TGaussian }
     *     
     */
    public TGaussian getGaussian() {
        return gaussian;
    }

    /**
     * Sets the value of the gaussian property.
     * 
     * @param value
     *     allowed object is
     *     {@link TGaussian }
     *     
     */
    public void setGaussian(TGaussian value) {
        this.gaussian = value;
    }

    /**
     * Gets the value of the exponential property.
     * 
     * @return
     *     possible object is
     *     {@link TExponential }
     *     
     */
    public TExponential getExponential() {
        return exponential;
    }

    /**
     * Sets the value of the exponential property.
     * 
     * @param value
     *     allowed object is
     *     {@link TExponential }
     *     
     */
    public void setExponential(TExponential value) {
        this.exponential = value;
    }

    /**
     * Gets the value of the count property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public long getCount() {
        if (count == null) {
            return  1L;
        } else {
            return count;
        }
    }

    /**
     * Sets the value of the count property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setCount(Long value) {
        this.count = value;
    }

    /**
     * Gets the value of the repeatUntil property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public long getRepeatUntil() {
        if (repeatUntil == null) {
            return  9223372036854775807L;
        } else {
            return repeatUntil;
        }
    }

    /**
     * Sets the value of the repeatUntil property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setRepeatUntil(Long value) {
        this.repeatUntil = value;
    }

}
