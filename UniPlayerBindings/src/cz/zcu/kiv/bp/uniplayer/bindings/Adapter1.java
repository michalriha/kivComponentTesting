//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.05.09 at 09:44:48 AM CEST 
//


package cz.zcu.kiv.bp.uniplayer.bindings;

import java.io.File;
import javax.xml.bind.annotation.adapters.XmlAdapter;

public class Adapter1
    extends XmlAdapter<String, File>
{


    public File unmarshal(String value) {
        return (cz.zcu.kiv.bp.uniplayer.bindings.adapted.FileAdapter.parse(value));
    }

    public String marshal(File value) {
        return (cz.zcu.kiv.bp.uniplayer.bindings.adapted.FileAdapter.print(value));
    }

}
