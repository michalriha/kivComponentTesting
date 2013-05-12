package cz.zcu.kiv.bp.uniplayer.bindings.adapted;

import java.util.Random;

import org.uncommons.maths.number.NumberGenerator;
import org.uncommons.maths.random.ExponentialGenerator;

public class Exponential extends ADistribution
{

    private Exponential _ = this;
    
    private long rate;
    
    private long timeSpan;
    
    private NumberGenerator<Double> generator;
    
    public Exponential()
    {
        this(0, 0);
    }
    
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

    public long getRate()
    {
        return _.rate;
    }

    public long getTimeSpan()
    {
        return _.timeSpan;
    }

}
