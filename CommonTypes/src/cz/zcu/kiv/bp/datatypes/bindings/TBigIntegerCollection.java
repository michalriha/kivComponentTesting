package cz.zcu.kiv.bp.datatypes.bindings;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import cz.zcu.kiv.bp.datatypes.bindings.basics.TCollection;
import cz.zcu.kiv.bp.datatypes.bindings.basics.TCollectionItem;


/**
 * <p>Java class for TBigIntegerCollection complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TBigIntegerCollection">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.kiv.zcu.cz/component-testing/types}TCollection">
 *       &lt;sequence>
 *         &lt;element name="item" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;extension base="{http://www.kiv.zcu.cz/component-testing/types}TCollectionItem">
 *                 &lt;sequence>
 *                   &lt;element name="BigInteger" type="{http://www.w3.org/2001/XMLSchema}integer"/>
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
@XmlType(name = "TBigIntegerCollection", propOrder = {
    "items"
})
@XmlRootElement(name = "BigInts")
public class TBigIntegerCollection extends TCollection<BigInteger>
{

    @XmlElement(name = "item", type = TBigIntegerCollection.Item.class)
    protected List<TCollectionItem<BigInteger>> items;

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
     * {@link TBigIntegerCollection.Item }
     * 
     * 
     */
    public List<TCollectionItem<BigInteger>> getItems() {
        if (items == null) {
            items = new ArrayList<>();
        }
        return this.items;
    }

	@Override
	public Class<TBigIntegerCollection.Item> getComponentWrapperClass() {
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
     *     &lt;extension base="{http://www.kiv.zcu.cz/component-testing/types}TCollectionItem">
     *       &lt;sequence>
     *         &lt;element name="BigInteger" type="{http://www.w3.org/2001/XMLSchema}integer"/>
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
    public static class Item extends TCollectionItem<BigInteger>
    {

        @XmlElement(name = "BigInteger", required = true)
        protected BigInteger value;

        /**
         * Gets the value of the value property.
         * 
         * @return
         *     possible object is
         *     {@link BigInteger }
         *     
         */
        public BigInteger getValue() {
            return value;
        }

        /**
         * Sets the value of the value property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigInteger }
         *     
         */
        public void setValue(BigInteger value) {
            this.value = value;
        }

    }

}
