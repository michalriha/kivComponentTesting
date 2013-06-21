package cz.zcu.kiv.bp.unimocker.bindings.adapted;



public class StringCollectionAdapter extends MyCollectionAdapter<String>
{
	public StringCollectionAdapter()
	{
		this.collectionWrapper = StringCollection.class;
	}
}
