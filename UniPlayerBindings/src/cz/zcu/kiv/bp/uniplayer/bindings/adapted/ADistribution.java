package cz.zcu.kiv.bp.uniplayer.bindings.adapted;

import org.uncommons.maths.number.NumberGenerator;

/**
 * Abstract repeated occurrence generator.
 * @author Michal
 */
public abstract class ADistribution
{
	/**
	 * Representation of distribution type.
	 * @author Michal
	 *
	 */
    public enum Type
    {
        EQUIDISTANT,
        EXPONENTIAL,
        GAUSSIAN
    }
    
    /**
     * Returns repeating distribution type.
     * @return type of distribution {@link Type}
     */
    public abstract Type getType();
    
    /**
     * Return next time to occur.
     * @return new generated number
     */
    public abstract double nextOccurrence();
    
    /**
     * Return used number generator.
     * @return number generator
     */
    public abstract NumberGenerator<Double> getGenerator();
}
