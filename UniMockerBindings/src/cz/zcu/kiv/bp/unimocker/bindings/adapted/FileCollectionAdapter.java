package cz.zcu.kiv.bp.unimocker.bindings.adapted;

import java.io.File;

/**
 * Adapter for FileCollection
 * @author Michal
 *
 */
public class FileCollectionAdapter extends MyCollectionAdapter<File>
{
	public FileCollectionAdapter()
	{
		this.collectionWrapper = FileCollection.class;
	}
}
