package cz.zcu.kiv.bp.unimocker.bindings.adapted;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


@XmlJavaTypeAdapter(InvocationAdapter.class)
public class Invocation
{

    private Invocation _ = this;

    private ArgumentsList arguments;
    
    private Value returnValue;
    
    public Value getReturnValue()
    {
        return returnValue;
    }
    
    public ArgumentsList getArguments()
    {
        return _.arguments;
    }

    public void setReturnValue(Value returnVaue)
    {
        _.returnValue = _.returnValue;
    }

    public void setArguments(ArgumentsList arguments)
    {
        _.arguments = _.arguments;
    }
    
    public Invocation() { }
    
    public Invocation(ArgumentsList arguments, Value returnValue)
    {
        _.arguments = arguments;
        _.returnValue = returnValue;
    }
}
