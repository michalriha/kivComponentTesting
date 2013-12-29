package cz.zcu.kiv.bp.datatypes.bindings.adapted;

import java.util.Arrays;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import cz.zcu.kiv.bp.datatypes.bindings.adapted.MyCollection;
import cz.zcu.kiv.bp.datatypes.bindings.basics.TCollection;

/**
 * Container class for storing values for arguments.
 * @author Michal
 *
 */
@XmlJavaTypeAdapter(ValueAdapter.class)
public class Value
{
	/**
	 * Runtime type of transfered value. Used in case when the value is null.
	 */
	Class<?> type = void.class;
	
	/**
	 * Type of wrapper class. Used for transferring collections. 
	 */
	Class<? extends TCollection<?>> xmlTypeWrapper;
	
	Class<? extends MyCollection<?>> adaptedTypeWrapper;
	
	/**
	 * Transfered value.
	 */
	Object value;
	
	/**
	 * @return the type of transferred value
	 */
	public synchronized Class<?> getType() {
		return type;
	}

	/**
	 * @return the transferred value
	 */
	public synchronized Object getValue() {
		return value;
	}

	/**
	 * sets the type of transferred value
	 * @param type of value
	 */
	public synchronized void setType(Class<?> type) {
		this.type = type;
	}

	/**
	 * sets the transferred value
	 * @param value to set
	 */
	public synchronized void setValue(Object value) {
		this.value = value;
	}

	/**
	 * gets the wrapperType type
	 * @return XmlType wrapper class
	 */
	public synchronized Class<? extends TCollection<?>> getXmlTypeWrapper() {
		return xmlTypeWrapper;
	}

	/**
	 * sets wrapperType type
	 * @param wrapperType XmlType wrapper class
	 */
	public synchronized void setXmlTypeWrapper(Class<? extends TCollection<?>> wrapperType) {
		this.xmlTypeWrapper = wrapperType;
	}
	
	/**
	 * @return the wrapperType2
	 */
	public synchronized Class<? extends MyCollection<?>> getAdaptedTypeWrapper() {
		return adaptedTypeWrapper;
	}

	/**
	 * @param wrapperType2 the wrapperType2 to set
	 */
	public synchronized void setAdaptedTypeWrapper(Class<? extends MyCollection<?>> wrapperType2) {
		this.adaptedTypeWrapper = wrapperType2;
	}

	@Override
	public String toString()
	{;
		return String.format(
			"%s: %s (wrapper: %s)",
			this.type.getCanonicalName(),
			(
				this.type.isArray()
				? Arrays.toString((Object[]) this.value)
				: this.value
			),
			this.xmlTypeWrapper
		);
	}

}
