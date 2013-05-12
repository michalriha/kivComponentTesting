package cz.zcu.kiv.bp.uniplayer.bindings.adapted;

import org.uncommons.maths.number.NumberGenerator;

public abstract class ADistribution
{
    public enum Type
    {
        EQUIDISTANT,
        EXPONENTIAL,
        GAUSSIAN
    }
    
    public abstract Type getType();
    
    public abstract double nextOccurrence();
    
    public abstract NumberGenerator<Double> getGenerator();
}
