package cz.zcu.kiv.bp.unimocker.bindings.basics;

import com.sun.xml.internal.bind.marshaller.NamespacePrefixMapper;

public class MyPrefixMapper extends NamespacePrefixMapper
{
	
	public static final String SCENARIO_PREFIX = "um"; // DEFAULT NAMESPACE
	public static final String SCENARIO_URI = "http://www.kiv.zcu.cz/component-testing/mocker";
	
	@Override
	public String getPreferredPrefix(
		String namespaceUri,
		String suggestion,
		boolean requirePrefix)
	{
		switch(namespaceUri)
		{
			case (SCENARIO_URI):
				return SCENARIO_PREFIX;
		
			default:
				return suggestion;
		}
	}
	
    @Override
    public String[] getPreDeclaredNamespaceUris() {
        return new String[] { SCENARIO_URI };
    }
}