package cz.zcu.kiv.bp.datatypes.bindings;

import java.io.File;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

import cz.zcu.kiv.bp.datatypes.bindings.basics.TCollection;


/**
 * <p>Java class for TValueType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="TValueType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="String"/>
 *     &lt;enumeration value="StringArrayList"/>
 *     &lt;enumeration value="StringLinkedList"/>
 *     &lt;enumeration value="StringArray"/>
 *     &lt;enumeration value="BigInteger"/>
 *     &lt;enumeration value="BigIntegerArrayList"/>
 *     &lt;enumeration value="BigIntegerLinkedList"/>
 *     &lt;enumeration value="BigIntegerArray"/>
 *     &lt;enumeration value="Long"/>
 *     &lt;enumeration value="LongArrayList"/>
 *     &lt;enumeration value="LongLinkedList"/>
 *     &lt;enumeration value="LongArray"/>
 *     &lt;enumeration value="Integer"/>
 *     &lt;enumeration value="IntegerArrayList"/>
 *     &lt;enumeration value="IntegerLinkedList"/>
 *     &lt;enumeration value="IntegerArray"/>
 *     &lt;enumeration value="Short"/>
 *     &lt;enumeration value="ShortArrayList"/>
 *     &lt;enumeration value="ShortLinkedList"/>
 *     &lt;enumeration value="ShortArray"/>
 *     &lt;enumeration value="Byte"/>
 *     &lt;enumeration value="ByteArrayList"/>
 *     &lt;enumeration value="ByteLinkedList"/>
 *     &lt;enumeration value="ByteArray"/>
 *     &lt;enumeration value="BigDecimal"/>
 *     &lt;enumeration value="BigDecimalArrayList"/>
 *     &lt;enumeration value="BigDecimalLinkedList"/>
 *     &lt;enumeration value="BigDecimalArray"/>
 *     &lt;enumeration value="Double"/>
 *     &lt;enumeration value="DoubleArrayList"/>
 *     &lt;enumeration value="DoubleLinkedList"/>
 *     &lt;enumeration value="DoubleArray"/>
 *     &lt;enumeration value="Float"/>
 *     &lt;enumeration value="FloatArrayList"/>
 *     &lt;enumeration value="FloatLinkedList"/>
 *     &lt;enumeration value="FloatArray"/>
 *     &lt;enumeration value="Boolean"/>
 *     &lt;enumeration value="BooleanArrayList"/>
 *     &lt;enumeration value="BooleanLinkedList"/>
 *     &lt;enumeration value="BooleanArray"/>
 *     &lt;enumeration value="File"/>
 *     &lt;enumeration value="FileArrayList"/>
 *     &lt;enumeration value="FileLinkedList"/>
 *     &lt;enumeration value="FileArray"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "TValueType")
@XmlEnum
public enum TValueType {

    @XmlEnumValue("String")
    STRING("String"),
    @XmlEnumValue("StringArrayList")
    STRING_ARRAY_LIST("StringArrayList"),
    @XmlEnumValue("StringLinkedList")
    STRING_LINKED_LIST("StringLinkedList"),
    @XmlEnumValue("StringArray")
    STRING_ARRAY("StringArray"),
    @XmlEnumValue("BigInteger")
    BIG_INTEGER("BigInteger"),
    @XmlEnumValue("BigIntegerArrayList")
    BIG_INTEGER_ARRAY_LIST("BigIntegerArrayList"),
    @XmlEnumValue("BigIntegerLinkedList")
    BIG_INTEGER_LINKED_LIST("BigIntegerLinkedList"),
    @XmlEnumValue("BigIntegerArray")
    BIG_INTEGER_ARRAY("BigIntegerArray"),
    @XmlEnumValue("Long")
    LONG("Long"),
    @XmlEnumValue("LongArrayList")
    LONG_ARRAY_LIST("LongArrayList"),
    @XmlEnumValue("LongLinkedList")
    LONG_LINKED_LIST("LongLinkedList"),
    @XmlEnumValue("LongArray")
    LONG_ARRAY("LongArray"),
    @XmlEnumValue("Integer")
    INTEGER("Integer"),
    @XmlEnumValue("IntegerArrayList")
    INTEGER_ARRAY_LIST("IntegerArrayList"),
    @XmlEnumValue("IntegerLinkedList")
    INTEGER_LINKED_LIST("IntegerLinkedList"),
    @XmlEnumValue("IntegerArray")
    INTEGER_ARRAY("IntegerArray"),
    @XmlEnumValue("Short")
    SHORT("Short"),
    @XmlEnumValue("ShortArrayList")
    SHORT_ARRAY_LIST("ShortArrayList"),
    @XmlEnumValue("ShortLinkedList")
    SHORT_LINKED_LIST("ShortLinkedList"),
    @XmlEnumValue("ShortArray")
    SHORT_ARRAY("ShortArray"),
    @XmlEnumValue("Byte")
    BYTE("Byte"),
    @XmlEnumValue("ByteArrayList")
    BYTE_ARRAY_LIST("ByteArrayList"),
    @XmlEnumValue("ByteLinkedList")
    BYTE_LINKED_LIST("ByteLinkedList"),
    @XmlEnumValue("ByteArray")
    BYTE_ARRAY("ByteArray"),
    @XmlEnumValue("BigDecimal")
    BIG_DECIMAL("BigDecimal"),
    @XmlEnumValue("BigDecimalArrayList")
    BIG_DECIMAL_ARRAY_LIST("BigDecimalArrayList"),
    @XmlEnumValue("BigDecimalLinkedList")
    BIG_DECIMAL_LINKED_LIST("BigDecimalLinkedList"),
    @XmlEnumValue("BigDecimalArray")
    BIG_DECIMAL_ARRAY("BigDecimalArray"),
    @XmlEnumValue("Double")
    DOUBLE("Double"),
    @XmlEnumValue("DoubleArrayList")
    DOUBLE_ARRAY_LIST("DoubleArrayList"),
    @XmlEnumValue("DoubleLinkedList")
    DOUBLE_LINKED_LIST("DoubleLinkedList"),
    @XmlEnumValue("DoubleArray")
    DOUBLE_ARRAY("DoubleArray"),
    @XmlEnumValue("Float")
    FLOAT("Float"),
    @XmlEnumValue("FloatArrayList")
    FLOAT_ARRAY_LIST("FloatArrayList"),
    @XmlEnumValue("FloatLinkedList")
    FLOAT_LINKED_LIST("FloatLinkedList"),
    @XmlEnumValue("FloatArray")
    FLOAT_ARRAY("FloatArray"),
    @XmlEnumValue("Boolean")
    BOOLEAN("Boolean"),
    @XmlEnumValue("BooleanArrayList")
    BOOLEAN_ARRAY_LIST("BooleanArrayList"),
    @XmlEnumValue("BooleanLinkedList")
    BOOLEAN_LINKED_LIST("BooleanLinkedList"),
    @XmlEnumValue("BooleanArray")
    BOOLEAN_ARRAY("BooleanArray"),
    @XmlEnumValue("File")
    FILE("File"),
    @XmlEnumValue("FileArrayList")
    FILE_ARRAY_LIST("FileArrayList"),
    @XmlEnumValue("FileLinkedList")
    FILE_LINKED_LIST("FileLinkedList"),
    @XmlEnumValue("FileArray")
    FILE_ARRAY("FileArray");
    private final String value;

    TValueType(String v) {
        value = v;
    }

    @SuppressWarnings("unchecked")
	public <T extends TCollection<?>> Class<T> wrapperClass()
    {
		Class<T> ret = null;
		switch (fromValue(value))
		{
			case DOUBLE_ARRAY:
			case DOUBLE_ARRAY_LIST:
			case DOUBLE_LINKED_LIST:
				ret = (Class<T>) TDoubleCollection.class;
				break;
				
			case FILE_ARRAY:
			case FILE_ARRAY_LIST:
			case FILE_LINKED_LIST:
				ret = (Class<T>) TFileCollection.class;
				break;
				
			case INTEGER_ARRAY:
			case INTEGER_ARRAY_LIST:
			case INTEGER_LINKED_LIST:
				ret = (Class<T>) TIntCollection.class;
				break;
				
			case STRING_ARRAY:
			case STRING_ARRAY_LIST:
			case STRING_LINKED_LIST:
				ret = (Class<T>) TStringCollection.class;
				break;
				
			case BIG_DECIMAL_ARRAY:
			case BIG_DECIMAL_ARRAY_LIST:
			case BIG_DECIMAL_LINKED_LIST:
				ret = (Class<T>) TBigDecimalCollection.class;
				break;
				
			case BIG_INTEGER_ARRAY:
			case BIG_INTEGER_ARRAY_LIST:
			case BIG_INTEGER_LINKED_LIST:
				ret = (Class<T>) TBigIntegerCollection.class; 
				break;
				
			case BOOLEAN_ARRAY:
			case BOOLEAN_ARRAY_LIST:
			case BOOLEAN_LINKED_LIST:
				ret = (Class<T>) TBooleanCollection.class;
				break;
			
			case BYTE_ARRAY:
			case BYTE_ARRAY_LIST:
			case BYTE_LINKED_LIST:
				ret = (Class<T>) TByteCollection.class;
				break;
				
			case FLOAT_ARRAY:
			case FLOAT_ARRAY_LIST:
			case FLOAT_LINKED_LIST:
				ret = (Class<T>) TFloatCollection.class;
				break;

			case LONG_ARRAY:
			case LONG_ARRAY_LIST:
			case LONG_LINKED_LIST:
				ret = (Class<T>) TLongCollection.class;
				break;

			case SHORT_ARRAY:
			case SHORT_ARRAY_LIST:
			case SHORT_LINKED_LIST:
				ret = (Class<T>) TShortCollection.class;
				break;

			default:
				break;
		}
		
		return ret;
    }
    
    public Class<?> baseClass()
    {
        switch (fromValue(value))
        {
        	case BIG_DECIMAL:
        		return BigDecimal.class;
        		
        	case BIG_DECIMAL_ARRAY:
        		return BigDecimal[].class;
        		
			case DOUBLE:
				return Double.class;
				
			case DOUBLE_ARRAY:
				return Double[].class;
			
			case FLOAT:
				return Float.class;
				
			case FLOAT_ARRAY:
				return Float[].class;
				
			case BIG_INTEGER:
				return Integer.class;
			
			case BIG_INTEGER_ARRAY:
				return BigInteger[].class;
			
			case INTEGER:
				return Integer.class;
				
			case INTEGER_ARRAY:
				return Integer[].class;

			case SHORT:
				return Short.class;
				
			case SHORT_ARRAY:
				return Short[].class;
			
			case BYTE:
				return Byte.class;
				
			case BYTE_ARRAY:
				return Byte[].class;
				
			case BOOLEAN:
				return Boolean.class;
				
			case BOOLEAN_ARRAY:
				return Boolean[].class;
				
			case FILE:
				return File.class;
				
			case FILE_ARRAY:
				return File[].class;

			case STRING:
				return String.class;
				
			case STRING_ARRAY:
				return String[].class;

			default:
		    	if (value.endsWith("ArrayList"))
		    	{
		    		return ArrayList.class;
		    	}
		    	else if (value.endsWith("LinkedList"))
		    	{
		    		return LinkedList.class;
		    	}
		    	else return void.class;
        }
    }
    
    public String value() {
        return value;
    }

    /**
     * Creates TValueType instance from the real class and its optional component class in case of collections.
     * @param clazz type of the value
     * @param componentClazz type the component, if the actual value is collection
     * @return TCollectionType
     */
    public static TValueType fromBaseClass(Class<?> clazz, Class<?> componentClazz)
    {
    	TValueType ret = TValueType.STRING;
    	
		if (clazz == String.class)
		{
			ret = TValueType.STRING;
		}
		else if (clazz == BigInteger.class)
		{
			ret = TValueType.BIG_INTEGER;
		}
		else if (clazz == Long.class)
		{
			ret = TValueType.LONG;
		}
		else if (clazz == Integer.class)
		{
			ret = TValueType.BIG_INTEGER;
		}
		else if (clazz == Short.class)
		{
			ret = TValueType.SHORT;
		}
		else if (clazz == Byte.class)
		{
			ret = TValueType.BYTE;
		}
		else if (clazz == BigDecimal.class)
		{
			ret = TValueType.BIG_DECIMAL;
		}
		else if (clazz == Double.class)
		{
			ret = TValueType.DOUBLE;
		}
		else if (clazz == Float.class)
		{
			ret = TValueType.FLOAT;
		}
		else if (clazz == Boolean.class)
		{
			ret = TValueType.BOOLEAN;
		}
		else if (clazz == File.class)
		{
			ret = TValueType.FILE;
		}
		else if (clazz == LinkedList.class)
    	{
    		if (componentClazz == String.class)
    		{
    			ret = TValueType.STRING_LINKED_LIST;
    		}
    		else if (componentClazz == BigInteger.class)
    		{
    			ret = TValueType.BIG_INTEGER_LINKED_LIST;
    		}
    		else if (componentClazz == Long.class)
    		{
    			ret = TValueType.LONG_LINKED_LIST;
    		}
    		else if (componentClazz == Integer.class)
    		{
    			ret = TValueType.INTEGER_LINKED_LIST;
    		}
    		else if (componentClazz == Short.class)
    		{
    			ret = TValueType.SHORT_LINKED_LIST;
    		}
    		else if (componentClazz == Byte.class)
    		{
    			ret = TValueType.BYTE_LINKED_LIST;
    		}
    		else if (componentClazz == BigDecimal.class)
    		{
    			ret = TValueType.BIG_DECIMAL_LINKED_LIST;
    		}    			
    		else if (componentClazz == Double.class)
    		{
    			ret = TValueType.DOUBLE_LINKED_LIST;
    		}
    		else if (componentClazz == Float.class)
    		{
    			ret = TValueType.FLOAT_LINKED_LIST;
    		}    			
    		else if (componentClazz == Boolean.class)
    		{
    			ret = TValueType.BOOLEAN_LINKED_LIST;
    		}
    		else if (componentClazz == File.class)
    		{
    			ret = TValueType.FILE_ARRAY_LIST;
    		}
    	}
    	else if (clazz == ArrayList.class)
    	{
    		if (componentClazz == String.class)
    		{
    			ret = TValueType.STRING_ARRAY_LIST;
    		}
    		else if (componentClazz == BigInteger.class)
    		{
    			ret = TValueType.BIG_INTEGER_ARRAY_LIST;
    		}
    		else if (componentClazz == Long.class)
    		{
    			ret = TValueType.LONG_ARRAY_LIST;
    		}
    		else if (componentClazz == Integer.class)
    		{
    			ret = TValueType.INTEGER_ARRAY_LIST;
    		}
    		else if (componentClazz == Short.class)
    		{
    			ret = TValueType.SHORT_ARRAY_LIST;
    		}
    		else if (componentClazz == Byte.class)
    		{
    			ret = TValueType.BYTE_ARRAY_LIST;
    		}
    		else if (componentClazz == BigDecimal.class)
    		{
    			ret = TValueType.BIG_DECIMAL_ARRAY_LIST;
    		}    			
    		else if (componentClazz == Double.class)
    		{
    			ret = TValueType.DOUBLE_ARRAY_LIST;
    		}
    		else if (componentClazz == Float.class)
    		{
    			ret = TValueType.FLOAT_ARRAY_LIST;
    		}    			
    		else if (componentClazz == Boolean.class)
    		{
    			ret = TValueType.BOOLEAN_ARRAY_LIST;
    		}
    		else if (componentClazz == File.class)
    		{
    			ret = TValueType.FILE_ARRAY_LIST;
    		}
    	}
    	else if (clazz.isArray())
    	{
    		if (clazz == String[].class)
    		{
    			ret = TValueType.STRING_ARRAY;
    		}
    		else if (clazz == BigInteger[].class)
    		{
    			ret = TValueType.BIG_INTEGER_ARRAY;
    		}
    		else if (clazz == Long[].class)
    		{
    			ret = TValueType.LONG_ARRAY;
    		}
    		else if (clazz == Integer[].class)
    		{
    			ret = TValueType.BIG_INTEGER_ARRAY;
    		}
    		else if (clazz == Short[].class)
    		{
    			ret = TValueType.SHORT_ARRAY;
    		}
    		else if (clazz == Byte[].class)
    		{
    			ret = TValueType.BYTE_ARRAY;
    		}
    		else if (clazz == BigDecimal[].class)
    		{
    			ret = TValueType.BIG_DECIMAL_ARRAY;
    		}
    		else if (clazz == Double[].class)
    		{
    			ret = TValueType.DOUBLE_ARRAY;
    		}
    		else if (clazz == Float[].class)
    		{
    			ret = TValueType.FLOAT_ARRAY;
    		}
    		else if (clazz == Boolean[].class)
    		{
    			ret = TValueType.BOOLEAN_ARRAY;
    		}
    		else if (clazz == File[].class)
    		{
    			ret = TValueType.FILE_ARRAY;
    		}
    	}    	

		return ret;
    }
    
    /**
     * Creates TValueType instance from a string representing the name of TCollectionType.
     * @param v name of the TCollectionType
     * @return TCollectionType
     */
    public static TValueType fromValue(String v) {
        for (TValueType c: TValueType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
