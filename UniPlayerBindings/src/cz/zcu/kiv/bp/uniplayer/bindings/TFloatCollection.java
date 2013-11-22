//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.06.18 at 08:12:58 PM CEST 
//


package cz.zcu.kiv.bp.uniplayer.bindings;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import cz.zcu.kiv.bp.uniplayer.bindings.basics.TCollection;
import cz.zcu.kiv.bp.uniplayer.bindings.basics.TCollectionItem;

/**
 * <p>Java class for TFloatCollection complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TFloatCollection">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.kiv.zcu.cz/component-testing/player}TCollection">
 *       &lt;sequence>
 *         &lt;element name="item" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;extension base="{http://www.kiv.zcu.cz/component-testing/player}TCollectionItem">
 *                 &lt;sequence>
 *                   &lt;element name="Float" type="{http://www.w3.org/2001/XMLSchema}float"/>
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
@XmlType(name = "TFloatCollection", propOrder = {
    "items"
})
public class TFloatCollection extends TCollection<Float>
{

    @XmlElement(name = "item", type = TFloatCollection.Item.class)
    protected List<TCollectionItem<Float>> items;

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
     * {@link TFloatCollection.Item }
     * 
     * 
     */
    public List<TCollectionItem<Float>> getItems() {
        if (items == null) {
            items = new ArrayList<>();
        }
        return this.items;
    }

	@Override
	public Class<? extends TCollectionItem<Float>> getComponentWrapperClass() {
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
     *     &lt;extension base="{http://www.kiv.zcu.cz/component-testing/player}TCollectionItem">
     *       &lt;sequence>
     *         &lt;element name="Float" type="{http://www.w3.org/2001/XMLSchema}float"/>
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
    public static class Item extends TCollectionItem<Float>
    {

        @XmlElement(name = "Float")
        protected float value;

        /**
         * Gets the value of the value property.
         * 
         */
        public Float getValue() {
            return value;
        }

        /**
         * Sets the value of the value property.
         * 
         */
        public void setValue(Float value) {
            this.value = value;
        }

    }

}
