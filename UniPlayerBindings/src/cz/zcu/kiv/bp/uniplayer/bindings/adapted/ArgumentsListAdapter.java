package cz.zcu.kiv.bp.uniplayer.bindings.adapted;

import java.util.Map;
import java.util.TreeMap;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import cz.zcu.kiv.bp.uniplayer.bindings.TArgumentsList;
import cz.zcu.kiv.bp.uniplayer.bindings.basics.UnOrderableItemsException;



public class ArgumentsListAdapter extends XmlAdapter<TArgumentsList, ArgumentsList>
{

    @Override
    public TArgumentsList marshal(ArgumentsList sortedArguments) throws Exception
    {
        TArgumentsList ret = new TArgumentsList();
        
        for (int i = 0; i < sortedArguments.size(); i++)
        {
            Argument arg = new Argument();
            arg.setArgumentOrder(i);
            arg.setType(sortedArguments.get(i).getType());
            arg.setValue(sortedArguments.get(i).getValue());
            arg.setAdaptedTypeWrapper(sortedArguments.get(i).getAdaptedTypeWrapper());
            arg.setXmlTypeWrapper(sortedArguments.get(i).getXmlTypeWrapper());
            
            ret.getArgument().add(arg);
        }
        
        return ret;
    }

    @Override
    public ArgumentsList unmarshal(TArgumentsList argumentsList) throws Exception
    {
        Map<Integer, Argument> tmp = new TreeMap<Integer, Argument>();
                
        ArgumentsList ret = new ArgumentsList();
        for (Argument arg : argumentsList.getArgument())
        {            
            if (tmp.containsKey((Integer) arg.getArgumentOrder()))
            {
                throw new UnOrderableItemsException("Seznam argumentu obsahuje argumenty se stejnym poradovym cislem.");
            }
            tmp.put(arg.getArgumentOrder(),  arg);
        }
        
        ret.addAll(tmp.values());
        return ret;
    }
}
