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
public class TExternalFactory extends TFactoryType {

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
