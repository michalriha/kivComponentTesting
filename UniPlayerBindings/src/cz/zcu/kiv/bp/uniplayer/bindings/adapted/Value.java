package cz.zcu.kiv.bp.uniplayer.bindings.adapted;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlJavaTypeAdapter(ValueAdapter.class)
public class Value
{
    private Object val;
    
    private Class<?> type; 
    
    public Object getVal()
    {
        return this.val;
    }
    
    public void setVal(Object val)
    {
        this.val = val;
    }
    
    public void setType(Class<?> type)
    {
    	this.type = type;
    }
    
    public Class<?> getType()
    {
    	return this.type;
    }

}
