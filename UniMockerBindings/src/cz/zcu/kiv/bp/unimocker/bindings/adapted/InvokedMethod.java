package cz.zcu.kiv.bp.unimocker.bindings.adapted;

import java.util.List;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlJavaTypeAdapter(InvokedMethodAdapter.class)
public class InvokedMethod
{
    private InvokedMethod _ = this;
    
    private String name;
    
    private List<Invocation> invocations;
    
    public InvokedMethod() { }
    
    public InvokedMethod(String name, List<Invocation> invocations)
    {
        _.name = name;
        _.invocations = invocations;
    }
    
    public void setName(String name)
    {
        _.name = name;
    }
    
    public void setInvocation(List<Invocation> invocations)
    {
        _.invocations = invocations;
    }
    
    public String getName()
    {
        return _.name;
    }
    
    public List<Invocation> getInvocations()
    {
        return _.invocations;
    }
}
