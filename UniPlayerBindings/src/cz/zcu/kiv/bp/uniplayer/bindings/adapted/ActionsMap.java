package cz.zcu.kiv.bp.uniplayer.bindings.adapted;

import java.math.BigInteger;
import java.util.LinkedList;
import java.util.TreeMap;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import cz.zcu.kiv.bp.uniplayer.bindings.TAction;


@XmlJavaTypeAdapter(ActionsMapAdapter.class)
public class ActionsMap extends TreeMap<Long, LinkedList<TAction>>
{
    private BigInteger stepsCount = BigInteger.ZERO;
    
    /**
     * default serial ID
     */
    private static final long serialVersionUID = -3594065285605714504L;

    public void put(long time, TAction action)
    {
        LinkedList<TAction> actionsInSameTime = get(time);
        if (actionsInSameTime == null)
        {
            actionsInSameTime = new LinkedList<TAction>();
            put(time, actionsInSameTime);
        }
        actionsInSameTime.add(action);
        this.put(time, actionsInSameTime);
        if (action.getRecurrence() != null)
        {
            this.addSteps(action.getRecurrence().getCount());
        }
        else
        {
            System.out.println(action.getRecurrence());
        }
    }
    
    public void put(TAction action)
    {
        put(action.getTime(), action);
    }
    
    private void addSteps(long steps)
    {
        stepsCount = stepsCount.add(BigInteger.valueOf(steps));
    }
    
    public BigInteger getStepsCount()
    {
        return stepsCount;
    }
    
}
