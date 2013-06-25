package cz.zcu.kiv.bp.unimocker.bindings.adapted;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import cz.zcu.kiv.bp.unimocker.bindings.TInvocation;

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
        return new Invocation(v.getArguments(), val);
    }

    @Override
    public TInvocation marshal(Invocation v) throws Exception
    {
        TInvocation inv = new TInvocation();
        inv.setArguments(v.getArguments());
        System.out.println(v.getReturnValue());
        if (v.getReturnValue().getType() != void.class) inv.setReturn(v.getReturnValue());
        
        return inv;
    }

}
