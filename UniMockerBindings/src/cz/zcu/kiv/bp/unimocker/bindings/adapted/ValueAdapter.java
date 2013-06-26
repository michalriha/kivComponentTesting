package cz.zcu.kiv.bp.unimocker.bindings.adapted;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.JAXBIntrospector;
import javax.xml.bind.annotation.adapters.XmlAdapter;

import cz.zcu.kiv.bp.unimocker.bindings.TAnyValue;
import cz.zcu.kiv.bp.unimocker.bindings.TCollectionType;
import cz.zcu.kiv.bp.unimocker.bindings.TNull;
import cz.zcu.kiv.bp.unimocker.bindings.TValue;
import cz.zcu.kiv.bp.unimocker.bindings.TValueType;
import cz.zcu.kiv.bp.unimocker.bindings.basics.TCollection;

/**
 * Adapter for transforming TValue to Value
 * @author Michal
 *
 */
public class ValueAdapter extends XmlAdapter<TValue, Value>
{

	private ValueAdapter _ = this;
	/**
	 * Walks through unmarshalled object and extracts contained value.
	 * Given that TValue can only contain one value, it's enough to end after single found value.
	 * May return null in case something goes wrong during unmarshalling process.
	 * @param probed instance of TValue
	 * @return transported value from probed instance
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public Object probeUnMarshalledInstanceForValue(TValue probed)
	throws IllegalAccessException, InvocationTargetException
	{		
		Object ret = null;
		
		// get all method of TValue class - presumes that TValue class
		// has only setters/getters generated from schema.
		for (Method met : TValue.class.getMethods())
		{
			// skip non public method
			if ((met.getModifiers() & Modifier.PUBLIC) == 0) continue;
			
			// skip non getter method
			if (!met.getName().startsWith("get")) continue;
			
			// try to invoke getter
			ret = met.invoke(probed, (Object[]) null);

			// if the getter returns value -> we have found something
			// and therefore can end seeking, else try another method
			if (ret == null) continue;
			else
			{
//				System.out.println("# found: " + met.getName() + " : " + (ret != null ? ret.getClass() : void.class).getSimpleName());
				break;
			}
		}
		return ret;
	}

	/**
	 * Extracts collection object in proper shape given by CollectionType
	 * (array/linkedlist/arraylist) and stores that object into Value instance.
	 * @param Collection to transform.
	 * @param Destination for transformed collection.
	 */
	private void extractAndStoreProperCollection(MyCollection<Object> col, Value ret)
	{		
		//save XmlType wrapper class
		ret.setXmlTypeWrapper(col.getXmlType());
		
		// save AdaptedType wrapper class
		@SuppressWarnings("unchecked")
		Class<? extends MyCollection<?>> adaptedTypeWrapper = (Class<? extends MyCollection<?>>) col.getClass();
		ret.setAdaptedTypeWrapper(adaptedTypeWrapper);
		
		// transform collection to proper object
		List<Object> list;
		switch (col.getCollectionType())
		{
			case ARRAY:
				ret.setValue(col.toArray());
				ret.setType(Array.newInstance(col.getComponentType(), 0).getClass());
				break;
				
			case ARRAY_LIST:
				list = new ArrayList<>(col);
				ret.setValue(list);
				ret.setType(ArrayList.class);
				break;
				
			case LINKED_LIST:
				list = new LinkedList<>(col);
				ret.setValue(list);
				ret.setType(LinkedList.class);
				break;			
		}
	}

	/**
	 * Extracts collection transported as Value instance and transforms it into proper XmlType object.
	 * @param value which to extract from
	 * @return transformed collection 
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	private MyCollection<Object> extractAndTransformCollection2XmlType(Value value)
	throws InstantiationException, IllegalAccessException
	{
		@SuppressWarnings("unchecked")
		MyCollection<Object> col = (MyCollection<Object>) value.getAdaptedTypeWrapper().newInstance();
		
		@SuppressWarnings("unchecked")
		Class<? extends TCollection<Object>> xmlTypeWrapper = (Class<? extends TCollection<Object>>) value.getXmlTypeWrapper();
		col.setXmlType(xmlTypeWrapper);

		Class<?> clazz = value.getType();
		if (clazz.isArray())
		{
			col.setCollectionType(TCollectionType.ARRAY);
			for (Object item : (Object[]) value.getValue())
			{
				col.add(item);
			}
		}
		else if (List.class.isAssignableFrom(clazz))
		{
			if (ArrayList.class.isAssignableFrom(clazz))
			{
				col.setCollectionType(TCollectionType.ARRAY_LIST);
			}
			else if (LinkedList.class.isAssignableFrom(clazz))
			{
				col.setCollectionType(TCollectionType.LINKED_LIST);
			}
			
			@SuppressWarnings("unchecked")
			List<Object> list = (List<Object>) value.getValue();
			col.addAll(list);
		}
		return col;
	}

	/**
	 * Finds proper setter method and invokes it on return object with given value.
	 * Separate value type is required in case of the value could be null.
	 * So far not possible. For null value use TNull elements instead.
	 * @param ret Return object to set the value into.
	 * @param paramClass value type
	 * @param paramValue value object
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	private void setValueToReturnedObject(
		TValue ret,
		Class<?> paramClass,
		Object paramValue)
	throws IllegalAccessException, InvocationTargetException
	{
		for (Method met : TValue.class.getMethods())
		{
			if (met.getName().startsWith("set"))
			{
				if (met.getParameterTypes().length == 1)
				{	
					if (met.getParameterTypes()[0] == paramClass)
					{ // found setter compatible with this collectionWrapper
						met.invoke(ret, paramValue);
						break;
					}
				}
			}
		}
	}

	private TNull createNullElement(Value v)
	throws InstantiationException, IllegalAccessException
	{
		TNull nul = new TNull();
		if (v.getXmlTypeWrapper() != null)
		{ // found wrapped value (collection)
			nul.setBaseType(
				TValueType.fromBaseClass(
					v.getType(),
					v.getXmlTypeWrapper().newInstance().getComponentType()
				)
			);
		}
		else
		{ // simple value
			nul.setBaseType(
				TValueType.fromBaseClass(
					v.getType(),
					null
				)
			);
		}
		return nul;
	}
	
    @Override
    public Value unmarshal(TValue arg) throws Exception
    {
        Value ret = new Value();
        
        // find contained value
     	Object foundValue = _.probeUnMarshalledInstanceForValue(arg);

     	if (foundValue == null)
     	{ // should not occur during unmarshalling of valid xml file
     		throw new JAXBException("Null values in TValue element are not allowed!");
     	}
     	else if (foundValue instanceof JAXBElement)
     	{
     		foundValue = JAXBIntrospector.getValue(foundValue);
     	}
     	else if (foundValue instanceof TAnyValue)
     	{
			ret.setValue(foundValue);
			ret.setType(foundValue.getClass());
     	}
		else if (foundValue instanceof TNull)
		{ // the instance is transporting null-type value
			ret.setValue(null);
			ret.setType(((TNull) foundValue).getBaseType().baseClass());
			ret.setXmlTypeWrapper(((TNull) foundValue).getBaseType().wrapperClass());
		}
		else if (foundValue instanceof MyCollection)
		{ // value is some kind of collection
			@SuppressWarnings("unchecked")
			MyCollection<Object> col = (MyCollection<Object>) foundValue;
			_.extractAndStoreProperCollection(col, ret);
		}
		else
		{ // value is a simple types
			ret.setValue(foundValue);
			ret.setType(foundValue.getClass());
		}
        
        return ret;
    }

	@Override
    public TValue marshal(Value arg) throws Exception
    {
        TValue ret = new TValue();
        
        if (arg == null)
        { // ?????
        	ret = null;
        }
        else if (arg.getValue() == null)
		{ // found null value
			this.setValueToReturnedObject(ret, TNull.class, this.createNullElement(arg));
		}
		else if (arg.getType().isArray() || List.class.isAssignableFrom(arg.getType()))
		{ // found some sort of collection (array/list)
			MyCollection<Object> col = _.extractAndTransformCollection2XmlType(arg);
			this.setValueToReturnedObject(ret, col.getClass(), col);
		}
		else
		{ // primitive or unknown type
			this.setValueToReturnedObject(ret, arg.getType(), arg.getValue());
		}
		
        return ret;
    }
}
