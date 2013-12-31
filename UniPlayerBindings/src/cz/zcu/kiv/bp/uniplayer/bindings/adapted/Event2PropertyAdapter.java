package cz.zcu.kiv.bp.uniplayer.bindings.adapted;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import cz.zcu.kiv.bp.uniplayer.bindings.TArgument;
import cz.zcu.kiv.bp.uniplayer.bindings.TEvent2Property;
import cz.zcu.kiv.bp.uniplayer.bindings.TValue;

public class Event2PropertyAdapter extends XmlAdapter<TEvent2Property, Event2Property>
{

	protected ValueAdapter valAdapter = new ValueAdapter();
	
	@Override
	public Event2Property unmarshal(TEvent2Property v) throws Exception
	{
		Event2Property ret = new Event2Property();
		Value val = valAdapter.unmarshal((TValue) v);
		ret.setKey(v.getKey());
		ret.setType(val.getType());
		ret.setValue(val.getValue());
		ret.setAdaptedTypeWrapper(val.getAdaptedTypeWrapper());
		ret.setXmlTypeWrapper(val.getXmlTypeWrapper());
		return ret;
	}

	@Override
	public TEvent2Property marshal(Event2Property v) throws Exception
	{
		TEvent2Property ret = null;
		TValue marVal = valAdapter.marshal((Value) v);
		ret = createArgumentFromValue(marVal);
		ret.setKey(v.getKey());
		return ret;
	}

	private TEvent2Property createArgumentFromValue(TValue marVal)
	{
		TEvent2Property ret = new TEvent2Property();
		
		Object foundValue = null;
				
		for (Method met : TValue.class.getDeclaredMethods())
		{
			// skip non public method
			if ((met.getModifiers() & Modifier.PUBLIC) == 0) continue;
			
			// skip non getter method
			if (!met.getName().startsWith("get")) continue;
			
			// try to invoke getter
			try
			{
				
				foundValue = met.invoke(marVal, (Object[]) null);
				// if the getter returns value -> we have found something
				// and therefore can end seeking, else try another method
				if (foundValue == null) continue;
				else
				{
					String setterName = "set" + met.getName().substring(3);
					try
					{
						met = TArgument.class.getMethod(setterName, foundValue.getClass());
					}
					catch (NoSuchMethodException e)
					{
						e.printStackTrace(); break;
					}
					met.invoke(ret, foundValue);
					break;
				}
			}
			catch (IllegalAccessException
					| IllegalArgumentException
					| InvocationTargetException
					| SecurityException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return ret;
	}
}
