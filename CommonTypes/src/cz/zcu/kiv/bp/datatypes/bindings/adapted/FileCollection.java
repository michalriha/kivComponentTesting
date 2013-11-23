package cz.zcu.kiv.bp.datatypes.bindings.adapted;

import java.io.File;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * MyCollection for File objects
 * @author Michal
 */
@XmlJavaTypeAdapter(FileCollectionAdapter.class)
public class FileCollection extends MyCollection<File>
{
	private static final long serialVersionUID = -7793750121287919309L;
}
