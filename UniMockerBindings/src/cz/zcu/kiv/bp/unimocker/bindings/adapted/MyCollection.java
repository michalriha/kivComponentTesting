package cz.zcu.kiv.bp.unimocker.bindings.adapted;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;

import cz.zcu.kiv.bp.unimocker.bindings.TCollectionType;
import cz.zcu.kiv.bp.unimocker.bindings.basics.TCollection;

@SuppressWarnings("serial")
public class MyCollection<T> extends ArrayList<T>
{
	private MyCollection<T> _ = this;
	
	private TCollectionType collectionType;
	
	private Class<? extends TCollection<T>> xmlType;
	
	public TCollectionType getCollectionType()
	{
		return _.collectionType;
	}

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
