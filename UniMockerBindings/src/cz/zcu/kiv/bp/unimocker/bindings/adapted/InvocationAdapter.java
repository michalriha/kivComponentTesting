package cz.zcu.kiv.bp.unimocker.bindings.adapted;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import cz.zcu.kiv.bp.unimocker.bindings.TInvocation;

public class InvocationAdapter extends XmlAdapter<TInvocation, Invocation>
{

    @Override
    public Invocation unmarshal(TInvocation v) throws Exception
    {
        return new Invocation(v.getArguments(), v.getReturn());
    }

    @Override
    public TInvocation marshal(Invocation v) throws Exception
    {
        TInvocation inv = new TInvocation();
        inv.setArguments(v.getArguments());
        inv.setReturn(v.getReturnValue());
        
        return inv;
    }

}
