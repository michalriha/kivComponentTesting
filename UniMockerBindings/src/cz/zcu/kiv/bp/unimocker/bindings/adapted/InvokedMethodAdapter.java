package cz.zcu.kiv.bp.unimocker.bindings.adapted;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import cz.zcu.kiv.bp.unimocker.bindings.TInvokedMethod;

public class InvokedMethodAdapter extends XmlAdapter<TInvokedMethod, InvokedMethod>
{

    @Override
    public InvokedMethod unmarshal(TInvokedMethod v) throws Exception
    {
        return new InvokedMethod(v.getName(), v.getInvocation());
    }

    @Override
    public TInvokedMethod marshal(InvokedMethod v) throws Exception
    {
        TInvokedMethod method = new TInvokedMethod();
        method.setName(v.getName());
        method.getInvocation().addAll(v.getInvocations());
        return method;
    }

}