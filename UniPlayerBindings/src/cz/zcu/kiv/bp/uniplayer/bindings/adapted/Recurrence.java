package cz.zcu.kiv.bp.uniplayer.bindings.adapted;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * Represents the recurrence description for a single command of simulation. 
 * @author Michal
 */
@XmlJavaTypeAdapter(RecurrenceAdapter.class)
public class Recurrence
{    
    private Recurrence _ = this;
    
    private long count;
    
    private long repeatUntil;
        
    private ADistribution distribution;

    /**
     * Returns the number of occurrences of this command.
     * @return long number of occurrences
     */
    public long getCount()
    {
        return _.count;
    }
    
    /**
     * Sets the number of occurrences of this command.
     * @param count
     */
    public void setCount(long count)
    {
        _.count = count;
    }
    
    /**
     * Returns the time until when this command can occur.
     * @return time limit
     */
    public long getRepeatUntil()
    {
        return _.repeatUntil;
    }
    
    /**
     * Sets the time until then this command can occur.
     * @param repeatUntil
     */
    public void setRepeat(long repeatUntil)
    {
        _.repeatUntil = repeatUntil;
    }
    
    /**
     * Return the probability distribution of this command.
     * @return used probability distribution
     */
    public ADistribution getDistribution()
    {
        return _.distribution;
    }
    
    /**
     * Sets the probability distribution of this command.
     * @param distribution
     */
    public void setDistribution(ADistribution distribution)
    {
        _.distribution = distribution;
    }
    
    /**
     * Decreases the number of time this command can occur.
     */
    public void decCount()
    {
        _.count--;
    }

}
