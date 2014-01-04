package cz.zcu.kiv.bp.datatypes.bindings;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for TImportedType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TImportedType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="factory">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;choice>
 *                   &lt;element name="static-member" type="{http://www.kiv.zcu.cz/component-testing/types}TStaticMemberFactory"/>
 *                   &lt;element name="constructor" type="{http://www.kiv.zcu.cz/component-testing/types}TConstructFactory"/>
 *                   &lt;element name="external" type="{http://www.kiv.zcu.cz/component-testing/types}TExternalFactory"/>
 *                 &lt;/choice>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *       &lt;attribute name="bundle" type="{http://www.kiv.zcu.cz/component-testing/types}TBundleName" use="required"/>
 *       &lt;attribute name="version" type="{http://www.kiv.zcu.cz/component-testing/types}TVersion" use="required" />
 *       &lt;attribute name="canonical-name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TImportedType", propOrder = {
    "factory",
    "canonicalName",
    "bundle",
    "version"
})
public class TImportedType {

    @XmlElement(required = true)
    protected TFactory factory;
    
    @XmlAttribute(name = "canonical-name", required = true)
    protected String canonicalName;
    
    @XmlAttribute(name = "bundle", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String bundle;
    
    @XmlAttribute(name = "version", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String version;

    /**
     * Gets the value of the bundle property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBundle() {
        return bundle;
    }

    /**
     * Sets the value of the bundle property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBundle(String value) {
        this.bundle = value;
    }

    /**
     * Gets the value of the version property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVersion() {
        return version;
    }

    /**
     * Sets the value of the version property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVersion(String value) {
        this.version = value;
    }

    /**
     * Gets the value of the factory property.
     * 
     * @return
     *     possible object is
     *     {@link TFactory }
     *     
     */
    public TFactory getFactory() {
        return factory;
    }

    /**
     * Sets the value of the factory property.
     * 
     * @param value
     *     allowed object is
     *     {@link TFactory }
     *     
     */
    public void setFactory(TFactory value) {
        this.factory = value;
    }

    /**
     * Gets the value of the cannonicalName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCanonicalName() {
        return canonicalName;
    }

    /**
     * Sets the value of the cannonicalName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCannonicalName(String value) {
        this.canonicalName = value;
    }
}
