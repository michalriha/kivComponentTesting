package cz.zcu.kiv.bp.unimocker.bindings.adapted;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import cz.zcu.kiv.bp.unimocker.bindings.TInvocation;

/**
 * Adapter for transforming TInvocation to Invocation.
 * @author Michal
 */
public class InvocationAdapter extends XmlAdapter<TInvocation, Invocation>
{

    @Override
    public Invocation unmarshal(TInvocation v) throws Exception
    {
    	Value val = v.getReturn();
    	if (val == null)
    	{
    		val = new Value();
    		val.setType(void.class);
    		val.setValue(null);
    	}
        return new Invocation(v.getArguments(), val, v.getCountLimit());
    }

    @Override
    public TInvocation marshal(Invocation v) throws Exception
    {
        TInvocation inv = new TInvocation();
        inv.setArguments(v.getArguments());
        inv.setCountLimit(v.getCountLimit());
        if (v.getReturnValue().getType() != void.class) inv.setReturn(v.getReturnValue());
        
        return inv;
    }

}
