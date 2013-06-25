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
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import cz.zcu.kiv.bp.unimocker.bindings.basics.TCollection;
import cz.zcu.kiv.bp.unimocker.bindings.basics.TCollectionItem;


/**
 * <p>Java class for TByteCollection complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TByteCollection">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.kiv.zcu.cz/component-testing/mocker}TCollection">
 *       &lt;sequence>
 *         &lt;element name="item" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;extension base="{http://www.kiv.zcu.cz/component-testing/mocker}TCollectionItem">
 *                 &lt;sequence>
 *                   &lt;element name="Byte" type="{http://www.w3.org/2001/XMLSchema}byte"/>
 *                 &lt;/sequence>
 *               &lt;/extension>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TByteCollection", propOrder = {
    "items"
})
public class TByteCollection extends TCollection<Byte>
{

    @XmlElement(name = "item", type = TByteCollection.Item.class)
    protected List<TCollectionItem<Byte>> items;

    /**
     * Gets the value of the items property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the items property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getItems().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TByteCollection.Item }
     * 
     * 
     */
    public List<TCollectionItem<Byte>> getItems() {
        if (items == null) {
            items = new ArrayList<>();
        }
        return this.items;
    }

	@Override
	public Class<TByteCollection.Item> getComponentWrapperClass() {
		return Item.class;
	}


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;extension base="{http://www.kiv.zcu.cz/component-testing/mocker}TCollectionItem">
     *       &lt;sequence>
     *         &lt;element name="Byte" type="{http://www.w3.org/2001/XMLSchema}byte"/>
     *       &lt;/sequence>
     *     &lt;/extension>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "value"
    })
    public static class Item extends TCollectionItem<Byte>
    {

        @XmlElement(name = "Byte")
        protected byte value;

        /**
         * Gets the value of the value property.
         * 
         */
        public Byte getValue() {
            return value;
        }

        /**
         * Sets the value of the value property.
         * 
         */
        public void setValue(Byte value) {
            this.value = value;
        }

    }

}
