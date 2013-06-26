package cz.zcu.kiv.bp.uniplayer.bindings.adapted;

import java.io.File;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * Adapter for mapping between File and String constrained by xsd:anyURI type
 * @author Michal
 */
public class FileAdapter extends XmlAdapter<String, File> {

	public File unmarshal(String v) {
		return new File(v);
	}

	public String marshal(File v) {
		return v.toString();
	}

}
