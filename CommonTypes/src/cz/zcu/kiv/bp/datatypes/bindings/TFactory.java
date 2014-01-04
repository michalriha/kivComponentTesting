package cz.zcu.kiv.bp.datatypes.bindings;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;element name="static-member" type="{http://www.kiv.zcu.cz/component-testing/types}TStaticMemberFactory"/>
 *         &lt;element name="constructor" type="{http://www.kiv.zcu.cz/component-testing/types}TConstructFactory"/>
 *         &lt;element name="external" type="{http://www.kiv.zcu.cz/component-testing/types}TExternalFactory"/>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "staticMember",
    "constructor",
    "external"
})
public class TFactory {

    @XmlElement(name = "static-member")
    protected TStaticMemberFactory staticMember;
    protected TConstructFactory constructor;
    protected TExternalFactory external;

    /**
     * Gets the value of the staticMember property.
     * 
     * @return
     *     possible object is
     *     {@link TStaticMemberFactory }
     *     
     */
    public TStaticMemberFactory getStaticMember() {
        return staticMember;
    }

    /**
     * Sets the value of the staticMember property.
     * 
     * @param value
     *     allowed object is
     *     {@link TStaticMemberFactory }
     *     
     */
    public void setStaticMember(TStaticMemberFactory value) {
        this.staticMember = value;
    }

    /**
     * Gets the value of the constructor property.
     * 
     * @return
     *     possible object is
     *     {@link TConstructFactory }
     *     
     */
    public TConstructFactory getConstructor() {
        return constructor;
    }

    /**
     * Sets the value of the constructor property.
     * 
     * @param value
     *     allowed object is
     *     {@link TConstructFactory }
     *     
     */
    public void setConstructor(TConstructFactory value) {
        this.constructor = value;
    }

    /**
     * Gets the value of the external property.
     * 
     * @return
     *     possible object is
     *     {@link TExternalFactory }
     *     
     */
    public TExternalFactory getExternal() {
        return external;
    }

    /**
     * Sets the value of the external property.
     * 
     * @param value
     *     allowed object is
     *     {@link TExternalFactory }
     *     
     */
    public void setExternal(TExternalFactory value) {
        this.external = value;
    }
}