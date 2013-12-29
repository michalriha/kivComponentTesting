package cz.zcu.kiv.bp.datatypes.bindings.adapted;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import cz.zcu.kiv.bp.datatypes.bindings.TImportedType;
import cz.zcu.kiv.bp.datatypes.bindings.TImportedTypes;

public class CustomTypesRegistryAdapter extends XmlAdapter<TImportedTypes, CustomTypesRegistry>
{

	@Override
	public CustomTypesRegistry unmarshal(TImportedTypes v)
	throws Exception
	{
		CustomTypesRegistry ret = new CustomTypesRegistry();
		
		for (TImportedType type : v.getType())
		{
			ret.put(type.getCannonicalName(), type);
		}
		
		return ret;
	}

	@Override
	public TImportedTypes marshal(CustomTypesRegistry v)
	throws Exception
	{
		TImportedTypes ret = new TImportedTypes();
		ret.getType().addAll(v.values());
		return ret;
	}

}
