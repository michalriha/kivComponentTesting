package cz.zcu.kiv.bp.uniplayer.bindings.old.adapted;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;

import cz.zcu.kiv.bp.uniplayer.bindings.old.TCollectionType;
import cz.zcu.kiv.bp.uniplayer.bindings.old.basics.TCollection;

/**
 * Common collection implementation. Is intended to be extended, but can be used on it's own. 
 * @author Michal
 *
 * @param <T> component type
 */
public class MyCollection<T> extends ArrayList<T>
{
	private static final long serialVersionUID = 7918734790903751197L;

	private MyCollection<T> _ = this;
	
	/**
	 * The type of collection that should be actually used.
	 */
	private TCollectionType collectionType;
	
	/**
	 * Corresponding XMLType (T?Collection)
	 */
	private Class<? extends TCollection<T>> xmlType;
	
	/**
	 * Gets the type of collection that should be used as actual value.
	 * @return collection type
	 */
	public TCollectionType getCollectionType()
	{
		return _.collectionType;
	}

	/**
	 * Sets the type of collection that should be used as actual value.
	 * @param type collection type
	 */
	public void setCollectionType(TCollectionType type)
	{
		_.collectionType = type;
	}
	
	/**
	 * Convenience method allowing to avoid tedious if-elseif
	 * tree for choosing proper XmlType collection wrapper.
	 * Expects good behavior of a user (must manually set the wrapper class)!
	 * @return the xmlType
	 */
	public Class<? extends TCollection<T>> getXmlType() {
		return xmlType;
	}

	/**
	 * @param xmlType the xmlType to set
	 */
	public void setXmlType(Class<? extends TCollection<T>> xmlType) {
		this.xmlType = xmlType;
	}
	
	/**
	 * Used for creating empty array of proper objects, which is
	 * needed for having proper array class. 
	 * @return collection component type
	 */
	public Class<T> getComponentType()
	{		
		Type type = getClass().getGenericSuperclass(); // returned super class is ArrayList<T>
		ParameterizedType paramType = (ParameterizedType) type;
		
		@SuppressWarnings("unchecked")
		Class<T> ret2 = (Class<T>) paramType.getActualTypeArguments()[0];
				
		return ret2;
	}
}
