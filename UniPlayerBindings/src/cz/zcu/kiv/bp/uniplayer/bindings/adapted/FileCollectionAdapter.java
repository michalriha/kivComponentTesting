package cz.zcu.kiv.bp.uniplayer.bindings.adapted;

import java.io.File;

public class FileCollectionAdapter extends MyCollectionAdapter<File>
{
	public FileCollectionAdapter()
	{
		this.collectionWrapper = FileCollection.class;
	}
}