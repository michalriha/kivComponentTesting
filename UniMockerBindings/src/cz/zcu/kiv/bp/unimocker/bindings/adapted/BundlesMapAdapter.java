package cz.zcu.kiv.bp.unimocker.bindings.adapted;

import java.util.HashMap;
import java.util.Map.Entry;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import cz.zcu.kiv.bp.unimocker.bindings.TBundle;
import cz.zcu.kiv.bp.unimocker.bindings.TBundleList;
import cz.zcu.kiv.bp.unimocker.bindings.TSimulatedService;

/**
 * Adapter for transforming TBundleList to BundlesMap 
 * @author Michal
 */
public class BundlesMapAdapter extends XmlAdapter<TBundleList, BundlesMap>
{

    @Override
    public BundlesMap unmarshal(TBundleList v) throws Exception
    {
        BundlesMap ret = new BundlesMap();
        
        for (TBundle bundle : v.getBundles())
        { 
        	HashMap<String, TSimulatedService> bundleServices = new HashMap<String, TSimulatedService>();
            for (TSimulatedService service : bundle.getServices())
            {
                bundleServices.put(service.getInterface(), service);
            }

            String bundleKey = bundle.getSymbolicName() + ":" + bundle.getVersion();   
            ret.put(bundleKey, bundleServices);
        }
        
        return ret;
    }

    @Override
    public TBundleList marshal(BundlesMap v) throws Exception
    {
        TBundleList ret = new TBundleList();

        for (Entry<String, HashMap<String, TSimulatedService>> entry : v.entrySet())
        {
            TBundle bndl = new TBundle();
            bndl.setSymbolicName(entry.getKey().split(":")[0]);
            bndl.setVersion(entry.getKey().split(":")[1]);
            bndl.getServices().addAll(entry.getValue().values());
            
            ret.getBundles().add(bndl);
        }
        
        return ret;
    }

}
