//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.12.25 at 04:59:18 PM CET 
//


package cz.zcu.kiv.bp.datatypes.bindings;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TExternalFactory complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TExternalFactory">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.kiv.zcu.cz/component-testing/types}TFactory">
 *       &lt;sequence>
 *         &lt;element name="bundle" type="{http://www.kiv.zcu.cz/component-testing/types}TBundle"/>
 *         &lt;element name="method" type="{http://www.kiv.zcu.cz/component-testing/types}TMethod"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TExternalFactory", propOrder = {
    "bundle",
    "method"
})
public class TExternalFactory
    extends TFactory
{

    @XmlElement(required = true)
    protected TBundle bundle;
    @XmlElement(required = true)
    protected TMethod method;

    /**
     * Gets the value of the bundle property.
     * 
     * @return
     *     possible object is
     *     {@link TBundle }
     *     
     */
    public TBundle getBundle() {
        return bundle;
    }

    /**
     * Sets the value of the bundle property.
     * 
     * @param value
     *     allowed object is
     *     {@link TBundle }
     *     
     */
    public void setBundle(TBundle value) {
        this.bundle = value;
    }

    /**
     * Gets the value of the method property.
     * 
     * @return
     *     possible object is
     *     {@link TMethod }
     *     
     */
    public TMethod getMethod() {
        return method;
    }

    /**
     * Sets the value of the method property.
     * 
     * @param value
     *     allowed object is
     *     {@link TMethod }
     *     
     */
    public void setMethod(TMethod value) {
        this.method = value;
    }

}