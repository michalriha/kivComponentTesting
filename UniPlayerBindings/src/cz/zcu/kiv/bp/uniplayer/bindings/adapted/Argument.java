package cz.zcu.kiv.bp.uniplayer.bindings.adapted;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * Invocation argument. Inherits value from Value class.
 * @author Michal
 */
@XmlJavaTypeAdapter(ArgumentAdapter.class)
public class Argument extends Value
{
    private int argumentOrder = 0;
    
    /**
     * Gets arguments position number.
     * @return position in the argument list
     */
    public int getArgumentOrder()
    {
        return this.argumentOrder;
    }
    
    /**
     * Sets arguments position number.
     **/
    public void setArgumentOrder(int order)
    {
        this.argumentOrder = order;
    }
}
