package cz.zcu.kiv.bp.datatypes.bindings.adapted;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import cz.zcu.kiv.bp.datatypes.bindings.TArgument;
import cz.zcu.kiv.bp.datatypes.bindings.TValue;

/**
 * Adapter for transforming TArgument to Argument 
 * @author Michal
 *
 */
public class ArgumentAdapter extends XmlAdapter<TArgument, Argument>
{

    @Override
    public Argument unmarshal(TArgument arg) throws Exception
    {
        ValueAdapter valAdapt = new ValueAdapter();
        Value unmVal = valAdapt.unmarshal((TValue) arg);
        Argument ret = new Argument();
        ret.setType(unmVal.getType());
        ret.setValue(unmVal.getValue());
        ret.setAdaptedTypeWrapper(unmVal.getAdaptedTypeWrapper());
        ret.setXmlTypeWrapper(unmVal.getXmlTypeWrapper());
        ret.setArgumentOrder(arg.getOrdNum());

        return ret;
    }

    @Override
    public TArgument marshal(Argument arg) throws Exception
    {
        ValueAdapter valAdapt = new ValueAdapter();
        TValue marVal = valAdapt.marshal(arg);
        TArgument ret = createArgumentFromValue(marVal);
        ret.setOrdNum(arg.getArgumentOrder());
       
        return ret;
    }

    /**
     * Creates {@link TArgument} element representation from {@link TValue}
     * @param value to be used as template for the new TArgument
     * @return new TArgument instance
     */
	private TArgument createArgumentFromValue(TValue value)
	{
		TArgument ret = new TArgument();
		
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
				
				foundValue = met.invoke(value, (Object[]) null);
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
