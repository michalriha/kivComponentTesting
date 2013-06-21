package cz.zcu.kiv.bp.unimocker.bindings.adapted;

import java.util.Map;
import java.util.TreeMap;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import cz.zcu.kiv.bp.unimocker.bindings.basics.TCollection;
import cz.zcu.kiv.bp.unimocker.bindings.basics.TCollectionItem;

public abstract class MyCollectionAdapter<T> extends XmlAdapter<TCollection<T>, MyCollection<T>> {

	protected Class<? extends MyCollection<T>> collectionWrapper = null; //(Class<? extends MyCollection<T>>) MyCollection.class;
	
	@Override
	public MyCollection<T> unmarshal(TCollection<T> v) throws Exception
	{
		MyCollection<T> ret = this.collectionWrapper.newInstance();
		// sort and set values - using TreeMap avoids sorting
		// and another loop for extracting contained values
		Map<Integer, T> map = new TreeMap<>();
		for (TCollectionItem<T> item : v.getItems())
		{
			map.put(item.getOrdNum(), item.getValue());
		}
		ret.addAll(map.values());

		// set type of collection (array/linkedlist/arraylist)
		ret.setCollectionType(v.getType());
		
		// set xmlType wrapper
		@SuppressWarnings("unchecked")
		Class<? extends TCollection<T>> xmlType = (Class<? extends TCollection<T>>) v.getClass();
		ret.setXmlType(xmlType);

		return ret;
	}

	@Override
	public TCollection<T> marshal(MyCollection<T> v) throws Exception
	{
		if (v == null)
		{ // ??????????? Why is this occurring??? The JAXBMarhsaller is trying to marshal null from all TValue instances.
			return null; //throw new JAXBException("No value has been set for marshalled collection.");
		}
		TCollection<T> ret = v.getXmlType().newInstance();
		ret.setType(v.getCollectionType());
		ret.addAll(v);
		
		return ret;
	}
}
