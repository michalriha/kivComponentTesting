//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.06.20 at 09:31:37 PM CEST 
//


package cz.zcu.kiv.bp.unimocker.bindings;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for TBundle complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TBundle">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="service" type="{http://www.kiv.zcu.cz/component-testing/mocker}TSimulatedService" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *       &lt;attribute name="symbolic-name" type="{http://www.kiv.zcu.cz/component-testing/mocker}TBundleName" />
 *       &lt;attribute name="version" type="{http://www.kiv.zcu.cz/component-testing/mocker}TVersion" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TBundle", propOrder = {
    "service"
})
public class TBundle {

    @XmlElement(required = true)
    protected List<TSimulatedService> service;
    @XmlAttribute(name = "symbolic-name")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String symbolicName;
    @XmlAttribute(name = "version")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String version;

    /**
     * Gets the value of the service property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the service property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getService().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TSimulatedService }
     * 
     * 
     */
    public List<TSimulatedService> getService() {
        if (service == null) {
            service = new ArrayList<TSimulatedService>();
        }
        return this.service;
    }

    /**
     * Gets the value of the symbolicName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSymbolicName() {
        return symbolicName;
    }

    /**
     * Sets the value of the symbolicName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSymbolicName(String value) {
        this.symbolicName = value;
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

}
