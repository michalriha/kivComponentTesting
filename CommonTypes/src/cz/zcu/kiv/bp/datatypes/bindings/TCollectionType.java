package cz.zcu.kiv.bp.datatypes.bindings;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TCollectionType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="TCollectionType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="array"/>
 *     &lt;enumeration value="ArrayList"/>
 *     &lt;enumeration value="LinkedList"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "TCollectionType")
@XmlEnum
public enum TCollectionType {

    @XmlEnumValue("array")
    ARRAY("array"),
    @XmlEnumValue("ArrayList")
    ARRAY_LIST("ArrayList"),
    @XmlEnumValue("LinkedList")
    LINKED_LIST("LinkedList");
    
    private final String value;

    TCollectionType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    /**
     * Creates TValueType instance from a string representing the name of TCollectionType.
     * @param v name of the TCollectionType
     * @return TCollectionType
     */
    public static TCollectionType fromValue(String v) {
        for (TCollectionType c: TCollectionType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
