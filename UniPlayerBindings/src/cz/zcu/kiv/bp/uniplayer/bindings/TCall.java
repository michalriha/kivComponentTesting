package cz.zcu.kiv.bp.uniplayer.bindings;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import cz.zcu.kiv.bp.uniplayer.bindings.adapted.ArgumentsList;


/**
 * <p>Java class for TCall complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TCall">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="arguments" type="{http://www.kiv.zcu.cz/component-testing/player}TArgumentsList"/>
 *       &lt;/sequence>
 *       &lt;attribute name="service" use="required" type="{http://www.kiv.zcu.cz/component-testing/player}TInterfaceName" />
 *       &lt;attribute name="method" use="required" type="{http://www.kiv.zcu.cz/component-testing/player}TMethodName" />
 *       &lt;attribute name="filter" type="{http://www.w3.org/2001/XMLSchema}string" />
 *		 &lt;attribute name="use-all-services-available" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false" />
 *		 &lt;attribute name="returned-value-id" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TCall", propOrder = {
    "arguments"
})
public class TCall {

    @XmlElement(required = true, type = TArgumentsList.class)
    protected ArgumentsList arguments;
    
    @XmlAttribute(name = "service", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String service;
    
    @XmlAttribute(name = "method", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String method;
    
    @XmlAttribute(name = "filter")
    protected String filter;
    
    @XmlAttribute(name = "use-all-services-available")
    protected boolean useAllServicesAvailable;
    
    @XmlAttribute(name = "returned-value-id")
    protected String returnedValueId;

    /**
     * Gets the value of the arguments property.
     * 
     * @return
     *     possible object is
     *     {@link TArgumentsList }
     *     
     */
    public ArgumentsList getArguments() {
        return arguments;
    }

    /**
     * Sets the value of the arguments property.
     * 
     * @param value
     *     allowed object is
     *     {@link TArgumentsList }
     *     
     */
    public void setArguments(ArgumentsList value) {
        this.arguments = value;
    }

    /**
     * Gets the value of the service property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getService() {
        return service;
    }

    /**
     * Sets the value of the service property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setService(String value) {
        this.service = value;
    }

    /**
     * Gets the value of the method property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMethod() {
        return method;
    }

    /**
     * Sets the value of the method property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMethod(String value) {
        this.method = value;
    }

    /**
     * Gets the value of the filter property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFilter() {
        return filter;
    }

    /**
     * Sets the value of the filter property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFilter(String value) {
        this.filter = value;
    }

    /**
     * Gets the value of the filter property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public boolean isUseAllServicesAvailable() {
        return this.useAllServicesAvailable;
    }

    /**
     * Sets the value of the filter property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUseAllServicesAvailable(boolean value) {
        this.useAllServicesAvailable = value;
    }

    /**
     * Gets the value of the returnedValueId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReturnedValueId() {
		return returnedValueId;
	}

    /**
     * Sets the value of the returnedValueId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReturnedValueId(String value) {
		this.returnedValueId = value;
	}
}
