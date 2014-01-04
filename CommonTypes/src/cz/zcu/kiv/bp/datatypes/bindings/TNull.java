package cz.zcu.kiv.bp.datatypes.bindings;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TNull complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TNull">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="base-type" use="required" type="{http://www.kiv.zcu.cz/component-testing/types}TValueType" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TNull")
@XmlRootElement(name = "Null")
public class TNull {

    @XmlAttribute(name = "base-type", required = true)
    protected TValueType baseType;

    /**
     * Gets the value of the baseType property.
     * 
     * @return
     *     possible object is
     *     {@link TValueType }
     *     
     */
    public TValueType getBaseType() {
        return baseType;
    }

    /**
     * Sets the value of the baseType property.
     * 
     * @param value
     *     allowed object is
     *     {@link TValueType }
     *     
     */
    public void setBaseType(TValueType value) {
        this.baseType = value;
    }

    /**
     * Returns a string representation of this object in format
     * "TNull of {@link TValueType}"
     */
    @Override
    public String toString()
    {
    	StringBuilder sb= new StringBuilder();
    
    	sb.append(this.getClass().getSimpleName());
    	sb.append(" of ");
    	sb.append(this.getBaseType());
    	
    	return sb.toString();
    }
    
}
