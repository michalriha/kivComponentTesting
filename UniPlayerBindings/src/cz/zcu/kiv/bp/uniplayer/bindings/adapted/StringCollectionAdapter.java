package cz.zcu.kiv.bp.uniplayer.bindings.adapted;



public class StringCollectionAdapter extends MyCollectionAdapter<String>
{
	public StringCollectionAdapter()
	{
		this.collectionWrapper = StringCollection.class;
	}
}
