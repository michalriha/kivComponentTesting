package cz.zcu.kiv.bp.uniplayer.bindings.adapted;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import cz.zcu.kiv.bp.uniplayer.bindings.TEquidistant;
import cz.zcu.kiv.bp.uniplayer.bindings.TExponential;
import cz.zcu.kiv.bp.uniplayer.bindings.TGaussian;
import cz.zcu.kiv.bp.uniplayer.bindings.TRecurrence;



public class RecurrenceAdapter extends XmlAdapter<TRecurrence, Recurrence>
{

    @Override
    public Recurrence unmarshal(TRecurrence v) throws Exception
    {
        Recurrence ret = new Recurrence();
        ret.setCount(v.getCount());
        ret.setRepeat(v.getRepeatUntil());
        
        if (v.getEquidistant() != null)
        {
            ret.setDistribution(
                new Equidistant(
                    v.getEquidistant().getStep()
                )
            );
        }
        else if (v.getExponential() != null)
        {
            ret.setDistribution(
                new Exponential(
                    v.getExponential().getRate(),
                    v.getExponential().getTimeSpan()
                )
            );
        }
        else if (v.getGaussian() != null)
        {
            ret.setDistribution(
                new Gaussian(
                    v.getGaussian().getMean(),
                    v.getGaussian().getDeviation()
                )
            );
        }
        
        return ret;
    }

    @Override
    public TRecurrence marshal(Recurrence v) throws Exception
    {
        TRecurrence ret = new TRecurrence();
        ret.setCount(v.getCount());
        ret.setRepeatUntil(v.getRepeatUntil());
        
        ADistribution dist= v.getDistribution();
        if (dist instanceof Equidistant)
        {
            TEquidistant eq = new TEquidistant();
            eq.setStep(((Equidistant)dist).getStep());
            ret.setEquidistant(eq);
        }
        else if (dist instanceof Exponential)
        {
            TExponential exp = new TExponential();
            exp.setRate(((Exponential)dist).getRate());
            exp.setTimeSpan(((Exponential)dist).getTimeSpan());
            ret.setExponential(exp);
        }
        else if (dist instanceof Gaussian)
        {
            TGaussian gau = new TGaussian();
            gau.setDeviation(((Gaussian)dist).getDeviation());
            gau.setMean(((Gaussian)dist).getMean());
            ret.setGaussian(gau);
        }
        
        return ret;
    }

}
