//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.11.16 at 09:24:17 PM CET 
//


package cz.zcu.kiv.bp.unimocker.bindings;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TArgument complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TArgument">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.kiv.zcu.cz/component-testing/mocker}TValue">
 *       &lt;attribute name="ord-num" type="{http://www.w3.org/2001/XMLSchema}int" default="0" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TArgument")
public class TArgument
    extends TValue
{

    @XmlAttribute(name = "ord-num")
    protected Integer ordNum;

    /**
     * Gets the value of the ordNum property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public int getOrdNum() {
        if (ordNum == null) {
            return  0;
        } else {
            return ordNum;
        }
    }

    /**
     * Sets the value of the ordNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setOrdNum(Integer value) {
        this.ordNum = value;
    }

}
