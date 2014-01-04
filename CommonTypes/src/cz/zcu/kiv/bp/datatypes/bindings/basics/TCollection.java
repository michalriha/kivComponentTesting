package cz.zcu.kiv.bp.datatypes.bindings.basics;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import cz.zcu.kiv.bp.datatypes.bindings.*;


/**
 * Common superclass for all T?Collection classes
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TCollection")
@XmlSeeAlso({
    TLongCollection.class,
    TIntCollection.class,
    TBooleanCollection.class,
    TByteCollection.class,
    TFloatCollection.class,
    TBigDecimalCollection.class,
    TBigIntegerCollection.class,
    TDoubleCollection.class,
    TStringCollection.class,
    TFileCollection.class,
    TShortCollection.class
})
public abstract class TCollection<T>
{
	/**
	 * Returns content of this collection.
	 * @return content of collection, each item is wrapped in T?CollectionItem instance
	 */
	public abstract List<TCollectionItem<T>> getItems();
	
	/**
	 * Return collection component wrapper class.
	 * Wrapper are used for storing information of item's type event if the value is null.
	 * @return Wrapper class corresponding to it's collection
	 */
	public abstract Class<? extends TCollectionItem<T>> getComponentWrapperClass();

	/**
	 * Wraps given value in proper wrapper and adds it into collection.
	 * @param value value to add into collection
	 * @throws InstantiationException Could not create new instance of wrapper class. 
	 * @throws IllegalAccessException Could not create new instance of wrapper class. 
	 */
	public void add(T value) throws InstantiationException, IllegalAccessException
	{
		TCollectionItem<T> wrappedItem = this.getComponentWrapperClass().newInstance();
		wrappedItem.setOrdNum(this.getItems().size());
		wrappedItem.setValue(value);
		this.getItems().add(wrappedItem);
	}
	
	/**
	 * Adds all values into collection.
	 * @param values values to add into collection
	 * @throws InstantiationException Could not create new instance of wrapper class. 
	 * @throws IllegalAccessException Could not create new instance of wrapper class. 
	 */
	public void addAll(Collection<T> values) throws InstantiationException, IllegalAccessException
	{
		for (T value : values)
		{
			this.add(value);
		}
	}
	
	/**
	 * Returns component type of this collection.
	 * @return componet type class
	 */
	@SuppressWarnings("unchecked")
	public Class<T> getComponentType()
	{
		Type type = getClass().getGenericSuperclass(); // returned super class is actually this class
		ParameterizedType paramType = (ParameterizedType) type;
		return (Class<T>) paramType.getActualTypeArguments()[0];
	}
	
    @XmlAttribute(name = "type")
    protected TCollectionType type;
	
    /**
     * Gets the value of the type property. Return type of this collection that should be used as value.
     * Possible values are Array, ArrayList, LinkedList
     * 
     * @return
     *     possible object is
     *     {@link TCollectionType }
     *     
     */
    public TCollectionType getType() {
        if (this.type == null) {
            return TCollectionType.ARRAY_LIST;
        } else {
            return this.type;
        }
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link TCollectionType }
     *     
     */
    public void setType(TCollectionType value) {
        this.type = value;
    }

}
