package cz.zcu.kiv.bp.unimocker.bindings.adapted;

import java.util.List;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * Describes single method in scenario. Specific method is determined by method's name and the list of arguments.
 * @author Michal
 */
@XmlJavaTypeAdapter(InvokedMethodAdapter.class)
public class InvokedMethod
{
    private InvokedMethod _ = this;
    
    /**
     * methods name
     */
    private String name;
    
    /**
     * possible invocations
     */
    private List<Invocation> invocations;
    
    /**
     * Creates new instance based on given arguments.
     * @param name name of the invoked method
     * @param invocations invocation possibilities - mocking scenario
     */
    public InvokedMethod(String name, List<Invocation> invocations)
    {
        _.name = name;
        _.invocations = invocations;
    }

    /**
     * Return method's name.
     * @return name of the method
     */
    public String getName()
    {
        return _.name;
    }
    
    /**
     * Return's list possible invocations.
     * @return mocking scenario
     */
    public List<Invocation> getInvocations()
    {
        return _.invocations;
    }
}
