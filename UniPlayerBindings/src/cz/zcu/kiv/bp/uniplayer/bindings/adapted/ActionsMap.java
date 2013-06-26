package cz.zcu.kiv.bp.uniplayer.bindings.adapted;

import java.math.BigInteger;
import java.util.LinkedList;
import java.util.TreeMap;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import cz.zcu.kiv.bp.uniplayer.bindings.TAction;

/**
 * Represents scenario actions. Scenario maps simulation time of Long type to list of Actions occurring in that time.
 * @author Michal
 */
@XmlJavaTypeAdapter(cz.zcu.kiv.bp.uniplayer.bindings.adapted.ActionsMapAdapter.class)
public class ActionsMap extends TreeMap<Long, LinkedList<TAction>>
{
    private static final long serialVersionUID = -3594065285605714504L;

    /**
     * stores total count of actions that should be executed.
     */
    private BigInteger stepsCount = BigInteger.ZERO;
    
    /**
     * Adds new action into map.
     * @param time time of this action
     * @param action stored action 
     */
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
    
    /**
     * Adds new action into map. Uses it'S own time information.
     * @param action
     */
    public void put(TAction action)
    {
        put(action.getTime(), action);
    }
    
    /**
     * Adds numbers of occurrences to counter
     * @param steps
     */
    private void addSteps(long steps)
    {
        stepsCount = stepsCount.add(BigInteger.valueOf(steps));
    }
    
    /**
     * Returns current total count of actions that should be executed. 
     * @return
     */
    public BigInteger getStepsCount()
    {
        return stepsCount;
    }
    
}
