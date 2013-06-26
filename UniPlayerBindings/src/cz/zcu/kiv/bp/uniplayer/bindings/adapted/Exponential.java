package cz.zcu.kiv.bp.uniplayer.bindings.adapted;

import java.util.Random;

import org.uncommons.maths.number.NumberGenerator;
import org.uncommons.maths.random.ExponentialGenerator;

/**
 * Represents exponential distribution for number generating.
 * @author Michal
 */
public class Exponential extends ADistribution
{
    private Exponential _ = this;
    
    /**
     * generator's rate parameter
     */
    private long rate;
    
    /**
     * time span - for the rate (the event occurs 'rate times' per 'time span')
     */
    private long timeSpan;
    
    /**
     * number generator
     */
    private NumberGenerator<Double> generator;
    
    /**
     * Create generator with rate 0 and time span 0
     */
    public Exponential()
    {
        this(0, 0);
    }
    
    /**
     * Creates generator
     * @param rate rate parameter
     * @param timeSpan time span
     */
    public Exponential(long rate, long timeSpan)
    {
        _.rate = rate;
        _.timeSpan = timeSpan;
        _.generator = new ExponentialGenerator(rate, new Random());
    }
    
    @Override
    public Type getType()
    {
        return Type.EXPONENTIAL;
    }

    @Override
    public double nextOccurrence()
    {
        return _.generator.nextValue() * _.timeSpan;
    }

    @Override
    public NumberGenerator<Double> getGenerator()
    {
        return _.generator;
    }

    /**
     * Returns generator's rate parameter.
     * @return rate parameter
     */
    public long getRate()
    {
        return _.rate;
    }

    /**
     * Returns generator's time span parameter.
     * @return
     */
    public long getTimeSpan()
    {
        return _.timeSpan;
    }

}
