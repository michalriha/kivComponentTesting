package cz.zcu.kiv.bp.uniplayer.bindings.adapted;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlJavaTypeAdapter(ArgumentAdapter.class)
public class Argument extends Value
{
    private int argumentOrder = 0;
    
    public int getArgumentOrder()
    {
        return this.argumentOrder;
    }
    
    public void setArgumentOrder(int order)
    {
        this.argumentOrder = order;
    }
}
