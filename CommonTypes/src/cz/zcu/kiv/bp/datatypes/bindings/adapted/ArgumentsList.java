package cz.zcu.kiv.bp.datatypes.bindings.adapted;

import java.util.ArrayList;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
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
     * @return arguments types
     */
    public Class<?>[] getTypes()
    {
        Class<?>[] ret = new Class<?>[this.size()];
        
        for (int i = 0; i < this.size(); i++)
        {
            ret[i] = this.get(i).getType();
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
