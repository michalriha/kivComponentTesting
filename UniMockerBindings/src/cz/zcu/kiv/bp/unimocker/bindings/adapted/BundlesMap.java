package cz.zcu.kiv.bp.unimocker.bindings.adapted;

import java.util.HashMap;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import cz.zcu.kiv.bp.unimocker.bindings.TSimulatedService;

@XmlJavaTypeAdapter(BundlesMapAdapter.class)
public class BundlesMap extends HashMap<String, HashMap<String, TSimulatedService>>
{

    /**
     * 
     */
    private static final long serialVersionUID = 1531257046441868515L;

}
