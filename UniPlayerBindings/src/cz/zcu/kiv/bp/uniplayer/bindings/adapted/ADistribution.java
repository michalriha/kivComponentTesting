package cz.zcu.kiv.bp.uniplayer.bindings.adapted;

import org.uncommons.maths.number.NumberGenerator;

/**
 * Abstract repeated occurrence generator.
 * @author Michal
 */
public abstract class ADistribution
{
    public enum Type
    {
        EQUIDISTANT,
        EXPONENTIAL,
        GAUSSIAN
    }
    
    /**
     * Returns repeating distribution type.
     * @return
     */
    public abstract Type getType();
    
    /**
     * Return next time to occur.
     * @return
     */
    public abstract double nextOccurrence();
    
    /**
     * Return used number generator.
     * @return number generator
     */
    public abstract NumberGenerator<Double> getGenerator();
}
