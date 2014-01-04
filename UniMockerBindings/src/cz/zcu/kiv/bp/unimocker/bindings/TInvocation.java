package cz.zcu.kiv.bp.unimocker.bindings;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import cz.zcu.kiv.bp.unimocker.bindings.adapted.ArgumentsList;
import cz.zcu.kiv.bp.unimocker.bindings.adapted.Value;


/**
 * <p>Java class for TInvocation complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TInvocation">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="arguments" type="{http://www.kiv.zcu.cz/component-testing/mocker}TArgumentsList">
 *              &lt;unique name="argumentOrdNum">
 *                  &lt;selector xpath="{http://www.kiv.zcu.cz/component-testing/mocker}argument" />
 *                  &lt;field xpath="@ord-num" />
 *              &lt;/unique>
 *         &lt;/element>
 *         &lt;element name="return" type="{http://www.kiv.zcu.cz/component-testing/mocker}TValue" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TInvocation", propOrder = {
    "arguments",
    "_return"
})
public class TInvocation {

    @XmlElement(required = true, type = TArgumentsList.class)
    protected ArgumentsList arguments;
    @XmlElement(name = "return", required = false, type = TValue.class)
    protected Value _return;

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
     * Gets the value of the return property.
     * 
     * @return
     *     possible object is
     *     {@link TValue }
     *     
     */
    public Value getReturn() {
        return _return;
    }

    /**
     * Sets the value of the return property.
     * 
     * @param value
     *     allowed object is
     *     {@link TValue }
     *     
     */
    public void setReturn(Value value) {
        this._return = value;
    }

}
