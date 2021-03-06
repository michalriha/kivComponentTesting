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
import cz.zcu.kiv.bp.unimocker.bindings.adapted.Invocation;


/**
 * <p>Java class for TInvokedMethod complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TInvokedMethod">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="code-injection" type="{http://www.kiv.zcu.cz/component-testing/mocker}TCodeInjection" minOccurs="0"/>
 *         &lt;element name="invocation" type="{http://www.kiv.zcu.cz/component-testing/mocker}TInvocation" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *       &lt;attribute name="name" type="{http://www.kiv.zcu.cz/component-testing/mocker}TMethodName" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TInvokedMethod", namespace = "http://www.kiv.zcu.cz/component-testing/mocker", propOrder = {
    "codeInjection",
    "invocations"
})
public class TInvokedMethod {

    @XmlElement(name = "invocation", required = true, type = TInvocation.class)
    protected List<Invocation> invocations;
    
    @XmlAttribute(name = "name")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String name;

    @XmlElement(name = "code-injection")
    protected TCodeInjection codeInjection;
    
    /**
     * Gets the value of the invocations property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the invocations property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getInvocations().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TInvocation }
     * 
     * 
     */
    public List<Invocation> getInvocations() {
        if (invocations == null) {
            invocations = new ArrayList<Invocation>();
        }
        return this.invocations;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the codeInjection property.
     * 
     * @return
     *     possible object is
     *     {@link TCodeInjection }
     *     
     */
    public TCodeInjection getCodeInjection() {
    	return this.codeInjection;
    }
    
    /**
     * Sets the value of the codeInjection property.
     * 
     * @param value
     *     allowed object is
     *     {@link TCodeInjection }
     *     
     */
    public void setCodeInjection(TCodeInjection value) {
    	this.codeInjection = value;
    }
}
