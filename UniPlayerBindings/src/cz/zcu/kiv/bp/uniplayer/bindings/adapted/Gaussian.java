package cz.zcu.kiv.bp.uniplayer.bindings.adapted;

import java.util.Random;

import org.uncommons.maths.number.NumberGenerator;
import org.uncommons.maths.random.GaussianGenerator;

/**
 * Represents gaussian distribution for number generating.
 * @author Michal
 */
public class Gaussian extends ADistribution
{
    private Gaussian _ =this;

    /**
     * generator's mean parameter
     */
    private double mean;
    
    /**
     * generator's standard deviation parameter
     */
    private double deviation;
    
    /**
     * used number generator
     */
    private NumberGenerator<Double> generator;
    
    /**
     * Create generator with 0 mean and 0 deviation
     */
    public Gaussian()
    {
        this(0, 0);
    }
    /**
     * Create new generator.
     * @param mean mean parameter
     * @param deviation deviation parameter
     */
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

    /**
     * Returns generator's mean parameter.
     * @return mean parameter
     */
    public double getMean()
    {
        return _.mean;
    }

    /**
     * Returns generator's standard deviation parameter.
     * @return standard deviation parameter
     */
    public double getDeviation()
    {
        return _.deviation;
    }

}
