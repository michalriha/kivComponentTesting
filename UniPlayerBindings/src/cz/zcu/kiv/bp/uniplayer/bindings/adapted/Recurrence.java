package cz.zcu.kiv.bp.uniplayer.bindings.adapted;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


@XmlJavaTypeAdapter(RecurrenceAdapter.class)
public class Recurrence
{    
    private Recurrence _ = this;
    
    private long count;
    
    private long repeatUntil;
        
    private ADistribution distribution;
    
    public long getCount()
    {
        return _.count;
    }
    
    public void setCount(long count)
    {
        _.count = count;
    }
    
    public long getRepeatUntil()
    {
        return _.repeatUntil;
    }
    
    public void setRepeat(long repeatUntil)
    {
        _.repeatUntil = repeatUntil;
    }
    
    public ADistribution getDistribution()
    {
        return _.distribution;
    }
    
    public void setDistribution(ADistribution distribution)
    {
        _.distribution = distribution;
    }
    
    public void decCount()
    {
        _.count--;
    }

}
