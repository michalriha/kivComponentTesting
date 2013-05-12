package cz.zcu.kiv.bp.uniplayer.bindings.adapted;

import java.util.ArrayList;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
@XmlJavaTypeAdapter(ArgumentsListAdapter.class)
public class ArgumentsList extends ArrayList<Argument>
{

    /**
     * 
     */
    private static final long serialVersionUID = -5563512348473133868L;

    public Class<?>[] getTypes()
    {
        Class<?>[] ret = new Class<?>[this.size()];
        
        for (int i = 0; i < this.size(); i++)
        {
            ret[i] = this.get(i).getVal().getClass();
        }
        
        return ret;
    }
    
    public Object[] toArray()
    {
        Object[] ret = new Object[this.size()];
        
        for (int i = 0; i < this.size(); i++)
        {
            ret[i] = this.get(i).getVal();
        }
        
        return ret;
    }
    
}
