package cz.zcu.kiv.bp.uniplayer.bindings.adapted;

import org.uncommons.maths.number.ConstantGenerator;
import org.uncommons.maths.number.NumberGenerator;

public class Equidistant extends ADistribution
{

    private Equidistant _ =this;
    
    private long step;
    
    private NumberGenerator<Double> generator;
    
    public Equidistant()
    {
        this(0);
    }
    
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
