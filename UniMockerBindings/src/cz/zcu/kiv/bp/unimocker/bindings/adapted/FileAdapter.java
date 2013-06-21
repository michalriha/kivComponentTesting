package cz.zcu.kiv.bp.unimocker.bindings.adapted;

import java.io.File;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class FileAdapter extends XmlAdapter<String, File> {

	public File unmarshal(String v) {
		return new File(v);
	}

	public String marshal(File v) {
		return v.toString();
	}

}
