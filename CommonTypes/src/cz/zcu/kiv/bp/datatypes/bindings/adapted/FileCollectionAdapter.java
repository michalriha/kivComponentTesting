package cz.zcu.kiv.bp.datatypes.bindings.adapted;

import java.io.File;

/**
 * Adapter for FileCollection
 * @author Michal
 *
 */
public class FileCollectionAdapter extends MyCollectionAdapter<File>
{
	@Override
	protected Class<? extends MyCollection<File>> getCollectionWrapper()
	{
		return FileCollection.class;
	}
}
