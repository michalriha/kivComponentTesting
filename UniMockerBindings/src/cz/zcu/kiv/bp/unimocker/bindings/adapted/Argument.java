package cz.zcu.kiv.bp.unimocker.bindings.adapted;

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
     * property for guaranteeing proper ordering of arguments
     * @return arguments position
     */
    public int getArgumentOrder()
    {
        return this.argumentOrder;
    }
    
    public void setArgumentOrder(int order)
    {
        this.argumentOrder = order;
    }
}
