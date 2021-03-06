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
import cz.zcu.kiv.bp.unimocker.bindings.adapted.InvokedMethod;


/**
 * <p>Java class for TSimulatedService complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TSimulatedService">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="method" type="{http://www.kiv.zcu.cz/component-testing/mocker}TInvokedMethod" minOccurs="1" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *       &lt;attribute name="interface" type="{http://www.kiv.zcu.cz/component-testing/mocker}TInterfaceName" />
 *       &lt;attribute name="ignore-undefined-methods" type="{http://www.w3.org/2001/XMLSchema}boolean" use="optional" default="false" />
 *       &lt;attribute name="ignore-undefined-possibilities" type="{http://www.w3.org/2001/XMLSchema}boolean" use="optional" default="false" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TSimulatedService", propOrder = {
    "methods"
})
public class TSimulatedService {

    @XmlElement(name = "method", required = true, type = TInvokedMethod.class)
    protected List<InvokedMethod> methods;
    
    @XmlAttribute(name = "interface")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String _interface;
    
    @XmlAttribute(name = "ignore-undefined-methods")
    protected Boolean ignoreUndefinedMethods;
    
    @XmlAttribute(name = "ignore-undefined-possibilities")
    protected Boolean ignoreUndefinedPossibilities;

    /**
     * Gets the value of the methods property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the methods property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMethods().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link InvokedMethod }
     * 
     * 
     */
    public List<InvokedMethod> getMethods() {
        if (methods == null) {
            methods = new ArrayList<InvokedMethod>();
        }
        return this.methods;
    }

    /**
     * Gets the value of the interface property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInterface() {
        return _interface;
    }

    /**
     * Sets the value of the interface property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInterface(String value) {
        this._interface = value;
    }

    /**
     * Gets the value of the ignoreUndefinedMethods property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isIgnoreUndefinedMethods() {
        if (ignoreUndefinedMethods == null) {
            return false;
        } else {
            return ignoreUndefinedMethods;
        }
    }

    /**
     * Sets the value of the ignoreUndefinedMethods property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIgnoreUndefinedMethods(Boolean value) {
        this.ignoreUndefinedMethods = value;
    }

    /**
     * Gets the value of the ignoreUndefinedPossibilities property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isIgnoreUndefinedPossibilities() {
        if (ignoreUndefinedPossibilities == null) {
            return false;
        } else {
            return ignoreUndefinedPossibilities;
        }
    }

    /**
     * Sets the value of the ignoreUndefinedPossibilities property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIgnoreUndefinedPossibilities(Boolean value) {
        this.ignoreUndefinedPossibilities = value;
    }

}
