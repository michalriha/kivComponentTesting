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
    
    private Long countLimit;
    
    /**
     * Returns return value associated with this invocation.
     * @return this invocation's return value
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
    public Invocation(ArgumentsList arguments, Value returnValue, Long countLimit)
    {
        _.arguments = arguments;
        _.returnValue = returnValue;
        _.countLimit = countLimit;
    }

	public Long getCountLimit() {
		return countLimit;
	}

	public void setCountLimit(Long countLimit) {
		this.countLimit = countLimit;
	}
}
