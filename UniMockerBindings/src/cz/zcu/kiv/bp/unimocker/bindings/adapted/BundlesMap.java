package cz.zcu.kiv.bp.unimocker.bindings.adapted;

import java.util.HashMap;
import java.util.List;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import cz.zcu.kiv.bp.unimocker.bindings.TSimulatedService;

/**
 * Maps bundle symbolic name to the map of pairs service => simulation description 
 * @author Michal
 */
@XmlJavaTypeAdapter(BundlesMapAdapter.class)
public class BundlesMap extends HashMap<String, HashMap<String, List<TSimulatedService>>>
{
    private static final long serialVersionUID = 1531257046441868515L;
}
