package cz.zcu.kiv.bp.uniplayer.bindings.adapted;

import org.uncommons.maths.number.ConstantGenerator;
import org.uncommons.maths.number.NumberGenerator;

/**
 * Represents equidistant distribution for number generating. Simply said generates numbers with constant step.
 * @author Michal
 */
public class Equidistant extends ADistribution
{
    private Equidistant _ =this;
    
    /**
     * distance between two numbers
     */
    private long step;
    
    /**
     * number generator
     */
    private NumberGenerator<Double> generator;
    
    /**
     * Creates generator generating with step 0 -> generates constant value.
     */
    public Equidistant()
    {
        this(0);
    }
    
    /**
     * Creates generator starting generating with given step.
     * @param step distance between two numbers
     */
    public Equidistant(long step)
    {
        _.step = step;
        _.generator = new ConstantGenerator<Double>((double) step);
    }
    
    @Override
    public Type getType()
    {
        return Type.EQUIDISTANT;
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

    public long getStep()
    {
        return _.step;
    }

}
