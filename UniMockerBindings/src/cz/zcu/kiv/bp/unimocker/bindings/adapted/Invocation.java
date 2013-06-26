package cz.zcu.kiv.bp.unimocker.bindings.adapted;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * Describes single invocation possibility
 * @author Michal
 */
@XmlJavaTypeAdapter(InvocationAdapter.class)
public class Invocation
{
    private Invocation _ = this;

    private ArgumentsList arguments;
    
    private Value returnValue;
    
    /**
     * Returns return value associated with this invocation.
     * @return this invocatio's return value
     */
    public Value getReturnValue()
    {
        return returnValue;
    }
    
    /**
     * Returns invocation arguments.
     * @return this invocation's arguments
     */
    public ArgumentsList getArguments()
    {
        return _.arguments;
    }
    
    /**
     * Creates new Invocation possibility based on given argument and return value;
     * @param arguments
     * @param returnValue
     */
    public Invocation(ArgumentsList arguments, Value returnValue)
    {
        _.arguments = arguments;
        _.returnValue = returnValue;
    }
}
