package cz.zcu.kiv.bp.uniplayer.bindings.adapted;

import java.util.Random;

import org.uncommons.maths.number.NumberGenerator;
import org.uncommons.maths.random.GaussianGenerator;

public class Gaussian extends ADistribution
{
    
    private Gaussian _ =this;
    
    private double mean;
    
    private double deviation;
    
    private NumberGenerator<Double> generator;
    
    public Gaussian()
    {
        this(0, 0);
    }
    
    public Gaussian(double mean, double deviation)
    {
        _.mean = mean;
        _.deviation = deviation;
        _.generator = new GaussianGenerator(mean, deviation, new Random());
    }
    
    @Override
    public Type getType()
    {
        return Type.GAUSSIAN;
    }

    @Override
    public double nextOccurrence()
    {
        return _.generator.nextValue();
    }

    @Override
    public NumberGenerator<Double> getGenerator()
    {
        return _.generator;
    }

    public double getMean()
    {
        return _.mean;
    }

    public double getDeviation()
    {
        return _.deviation;
    }

}
