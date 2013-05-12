package cz.zcu.kiv.bp.uniplayer.bindings.adapted;

import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import cz.zcu.kiv.bp.uniplayer.bindings.TAction;
import cz.zcu.kiv.bp.uniplayer.bindings.TActionsList;


public class ActionsMapAdapter extends XmlAdapter<TActionsList, ActionsMap>
{
    @Override
    public TActionsList marshal(ActionsMap actionMap)
    {
        TActionsList ret = new TActionsList();
        List<TAction> varList = ret.getAction();
        for (Entry<Long, LinkedList<TAction>> entry : actionMap.entrySet())
        {
            varList.addAll(entry.getValue());
        }
        
        return ret;
    }

    @Override
    public ActionsMap unmarshal(TActionsList varList) throws Exception
    {        
        ActionsMap ret = new ActionsMap();
        for (TAction action : varList.getAction())
        {
            ret.put(action);
        }
        
        return ret;
    }

}
