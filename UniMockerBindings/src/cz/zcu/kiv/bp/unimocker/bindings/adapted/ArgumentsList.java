package cz.zcu.kiv.bp.unimocker.bindings.adapted;

import java.util.ArrayList;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import cz.zcu.kiv.bp.unimocker.bindings.TAnyValue;


/**
 * Representation of the list of arguments for invocation.
 * @author Michal
 */
@XmlJavaTypeAdapter(ArgumentsListAdapter.class)
public class ArgumentsList extends ArrayList<Argument>
{
    private static final long serialVersionUID = -5563512348473133868L;

    /**
     * Returns types of argument the arguments should be.
     * If argument is universal, use it's base-type.
     * @return arguments types
     */
    public Class<?>[] getTypes()
    {
        Class<?>[] ret = new Class<?>[this.size()];
        
        for (int i = 0; i < this.size(); i++)
        {
        	if (this.get(i).getType() == TAnyValue.class && this.get(i).getValue() instanceof TAnyValue)
        	{
        		ret[i] = ((TAnyValue) this.get(i).getValue()).getBaseType().baseClass();
        	}
        	else ret[i] = this.get(i).getType();
        }
        
        return ret;
    }
    
    /**
     * Returns array of argument values.
     * @return array of values
     */
    @Override
    public Object[] toArray()
    {
        Object[] ret = new Object[this.size()];
        
        for (int i = 0; i < this.size(); i++)
        {
            ret[i] = this.get(i).getValue();
        }
        
        return ret;
    }
    
}
