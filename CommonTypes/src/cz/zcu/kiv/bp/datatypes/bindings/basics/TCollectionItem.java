package cz.zcu.kiv.bp.datatypes.bindings.basics;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * Common superclass for all T?CollectionItem wrapper classes
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TCollectionItem")
@XmlSeeAlso({
    cz.zcu.kiv.bp.datatypes.bindings.TLongCollection.Item.class,
    cz.zcu.kiv.bp.datatypes.bindings.TIntCollection.Item.class,
    cz.zcu.kiv.bp.datatypes.bindings.TBooleanCollection.Item.class,
    cz.zcu.kiv.bp.datatypes.bindings.TByteCollection.Item.class,
    cz.zcu.kiv.bp.datatypes.bindings.TFloatCollection.Item.class,
    cz.zcu.kiv.bp.datatypes.bindings.TBigDecimalCollection.Item.class,
    cz.zcu.kiv.bp.datatypes.bindings.TBigIntegerCollection.Item.class,
    cz.zcu.kiv.bp.datatypes.bindings.TDoubleCollection.Item.class,
    cz.zcu.kiv.bp.datatypes.bindings.TStringCollection.Item.class,
    cz.zcu.kiv.bp.datatypes.bindings.TFileCollection.Item.class,
    cz.zcu.kiv.bp.datatypes.bindings.TShortCollection.Item.class
})
public abstract class TCollectionItem<T> {

	/**
	 * index for ordering purposes
	 */
    @XmlAttribute(name = "ord-num", required = true)
    protected int ordNum;

    /**
     * Gets the value of the ordNum property.
     * 
     */
    public int getOrdNum() {
        return ordNum;
    }

    /**
     * Sets the value of the ordNum property.
     * 
     */
    public void setOrdNum(int value) {
        this.ordNum = value;
    }

    /**
     * Gets item's value. Parameterized method used for type safety in CollectionAdapter.
     * @return item's value
     */
    public abstract T getValue();
    
    /**
     * Sets item's value. Parameterized method used for type safety in CollectionAdapter.
     * @param value
     */
    public abstract void setValue(T value);

}
