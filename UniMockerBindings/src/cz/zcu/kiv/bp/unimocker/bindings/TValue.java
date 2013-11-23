package cz.zcu.kiv.bp.unimocker.bindings;

import java.io.File;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.BigInteger;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import cz.zcu.kiv.bp.datatypes.bindings.*;
import cz.zcu.kiv.bp.datatypes.bindings.adapted.*;


/**
 * <p>Java class for TValue complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TValue">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;element name="String" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="BigInteger" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *         &lt;element name="Long" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="Integer" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Short" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *         &lt;element name="Byte" type="{http://www.w3.org/2001/XMLSchema}byte"/>
 *         &lt;element name="BigDecimal" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="Double" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="Float" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element ref="{http://www.kiv.zcu.cz/component-testing/types}File"/>
 *         &lt;element name="Boolean" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element ref="{http://www.kiv.zcu.cz/component-testing/types}Strings"/>
 *         &lt;element ref="{http://www.kiv.zcu.cz/component-testing/types}BigInts"/>
 *         &lt;element ref="{http://www.kiv.zcu.cz/component-testing/types}Longs"/>
 *         &lt;element ref="{http://www.kiv.zcu.cz/component-testing/types}Ints"/>
 *         &lt;element ref="{http://www.kiv.zcu.cz/component-testing/types}Shorts"/>
 *         &lt;element ref="{http://www.kiv.zcu.cz/component-testing/types}Bytes"/>
 *         &lt;element ref="{http://www.kiv.zcu.cz/component-testing/types}BigDecimals"/>
 *         &lt;element ref="{http://www.kiv.zcu.cz/component-testing/types}Doubles"/>
 *         &lt;element ref="{http://www.kiv.zcu.cz/component-testing/types}Floats"/>
 *         &lt;element ref="{http://www.kiv.zcu.cz/component-testing/types}Booleans"/>
 *         &lt;element ref="{http://www.kiv.zcu.cz/component-testing/types}Files"/>
 *         &lt;element ref="{http://www.kiv.zcu.cz/component-testing/types}Null"/>
 *         &lt;element name="AnyValue" type="{http://www.kiv.zcu.cz/component-testing/mocker}TAnyValue"/>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TValue", propOrder = {
    "string",
    "bigInteger",
    "_long",
    "integer",
    "_short",
    "_byte",
    "bigDecimal",
    "_double",
    "_float",
    "file",
    "_boolean",
    "strings",
    "bigInts",
    "longs",
    "ints",
    "shorts",
    "bytes",
    "bigDecimals",
    "doubles",
    "floats",
    "booleans",
    "files",
    "_null",
    "anyValue",
//    "collection"
})
@XmlSeeAlso({
    TArgument.class
})
public class TValue
{
    @XmlElement(name = "String")
    protected String string;
    
    @XmlElement(name = "BigInteger")
    protected BigInteger bigInteger;
    
    @XmlElement(name = "Long")
    protected Long _long;
    
    @XmlElement(name = "Integer")
    protected Integer integer;
    
    @XmlElement(name = "Short")
    protected Short _short;
    
    @XmlElement(name = "Byte")
    protected Byte _byte;
    
    @XmlElement(name = "BigDecimal")
    protected BigDecimal bigDecimal;
    
    @XmlElement(name = "Double")
    protected Double _double;
    
    @XmlElement(name = "Float")
    protected Float _float;
    
    @XmlElement(name = "Boolean")
    protected Boolean _boolean;
    
    @XmlElement(name = "File", namespace = "http://www.kiv.zcu.cz/component-testing/types")
    @XmlJavaTypeAdapter(FileAdapter.class)
    protected File file;
    
    @XmlElement(name = "Strings", namespace = "http://www.kiv.zcu.cz/component-testing/types", type = TStringCollection.class)
    protected StringCollection strings;
    
    @XmlElement(name = "BigInts", namespace = "http://www.kiv.zcu.cz/component-testing/types", type = TBigIntegerCollection.class)
    protected BigIntegerCollection bigInts;
    
    @XmlElement(name = "Longs", namespace = "http://www.kiv.zcu.cz/component-testing/types", type = TLongCollection.class)
    protected LongCollection longs;
    
    @XmlElement(name = "Ints", namespace = "http://www.kiv.zcu.cz/component-testing/types", type = TIntCollection.class)
    protected IntegerCollection ints;
    
    @XmlElement(name = "Shorts", namespace = "http://www.kiv.zcu.cz/component-testing/types", type = TShortCollection.class)
    protected ShortCollection shorts;
    
    @XmlElement(name = "Bytes", namespace = "http://www.kiv.zcu.cz/component-testing/types", type = TByteCollection.class)
    protected ByteCollection bytes;
    
    @XmlElement(name = "BigDecimals", namespace = "http://www.kiv.zcu.cz/component-testing/types", type = TBigDecimalCollection.class)
    protected BigDecimalCollection bigDecimals;
    
    @XmlElement(name = "Doubles", namespace = "http://www.kiv.zcu.cz/component-testing/types", type = TDoubleCollection.class)
    protected DoubleCollection doubles;
    
    @XmlElement(name = "Floats", namespace = "http://www.kiv.zcu.cz/component-testing/types", type = TFloatCollection.class)
    protected FloatCollection floats;
    
    @XmlElement(name = "Booleans", namespace = "http://www.kiv.zcu.cz/component-testing/types", type = TBooleanCollection.class)
    protected BooleanCollection booleans;
    
    @XmlElement(name = "Files", namespace = "http://www.kiv.zcu.cz/component-testing/types", type = TFileCollection.class)
    protected FileCollection files;
    
// TODO try to simplify the collection support structure
//    @XmlElements({
//    	@XmlElement(name = "Files", namespace = "http://www.kiv.zcu.cz/component-testing/types", type = TFileCollection.class),    
//    	@XmlElement(name = "Booleans", namespace = "http://www.kiv.zcu.cz/component-testing/types", type = TBooleanCollection.class),    
//        @XmlElement(name = "Ints", namespace = "http://www.kiv.zcu.cz/component-testing/types", type = TIntCollection.class)    	
//    })
//    protected MyCollection<?> collection;
    
    @XmlElement(name = "Null", namespace = "http://www.kiv.zcu.cz/component-testing/types")
    protected TNull _null;
    
    @XmlElement(name = "AnyValue")
    protected TAnyValue anyValue;

    /**
     * Gets the value of the string property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getString() {
        return string;
    }

    /**
     * Sets the value of the string property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setString(String value) {
        this.string = value;
    }

    /**
     * Gets the value of the bigInteger property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getBigInteger() {
        return bigInteger;
    }

    /**
     * Sets the value of the bigInteger property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setBigInteger(BigInteger value) {
        this.bigInteger = value;
    }

    /**
     * Gets the value of the long property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getLong() {
        return _long;
    }

    /**
     * Sets the value of the long property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setLong(Long value) {
        this._long = value;
    }

    /**
     * Gets the value of the integer property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getInteger() {
        return integer;
    }

    /**
     * Sets the value of the integer property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setInteger(Integer value) {
        this.integer = value;
    }

    /**
     * Gets the value of the short property.
     * 
     * @return
     *     possible object is
     *     {@link Short }
     *     
     */
    public Short getShort() {
        return _short;
    }

    /**
     * Sets the value of the short property.
     * 
     * @param value
     *     allowed object is
     *     {@link Short }
     *     
     */
    public void setShort(Short value) {
        this._short = value;
    }

    /**
     * Gets the value of the byte property.
     * 
     * @return
     *     possible object is
     *     {@link Byte }
     *     
     */
    public Byte getByte() {
        return _byte;
    }

    /**
     * Sets the value of the byte property.
     * 
     * @param value
     *     allowed object is
     *     {@link Byte }
     *     
     */
    public void setByte(Byte value) {
        this._byte = value;
    }

    /**
     * Gets the value of the bigDecimal property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getBigDecimal() {
        return bigDecimal;
    }

    /**
     * Sets the value of the bigDecimal property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setBigDecimal(BigDecimal value) {
        this.bigDecimal = value;
    }

    /**
     * Gets the value of the double property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getDouble() {
        return _double;
    }

    /**
     * Sets the value of the double property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setDouble(Double value) {
        this._double = value;
    }

    /**
     * Gets the value of the float property.
     * 
     * @return
     *     possible object is
     *     {@link Float }
     *     
     */
    public Float getFloat() {
        return _float;
    }

    /**
     * Sets the value of the float property.
     * 
     * @param value
     *     allowed object is
     *     {@link Float }
     *     
     */
    public void setFloat(Float value) {
        this._float = value;
    }

    /**
     * Gets the value of the file property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public File getFile() {
        return file;
    }

    /**
     * Sets the value of the file property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFile(File value) {
        this.file = value;
    }

    /**
     * Gets the value of the boolean property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean getBoolean() {
        return _boolean;
    }

    /**
     * Sets the value of the boolean property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setBoolean(Boolean value) {
        this._boolean = value;
    }

    /**
     * Gets the value of the strings property.
     * 
     * @return
     *     possible object is
     *     {@link TStringCollection }
     *     
     */
    public StringCollection getStrings() {
        return strings;
    }

    /**
     * Sets the value of the strings property.
     * 
     * @param value
     *     allowed object is
     *     {@link TStringCollection }
     *     
     */
    public void setStrings(StringCollection value) {
        this.strings = value;
    }

    /**
     * Gets the value of the bigInts property.
     * 
     * @return
     *     possible object is
     *     {@link TBigIntegerCollection }
     *     
     */
    public BigIntegerCollection getBigInts() {
        return bigInts;
    }

    /**
     * Sets the value of the bigInts property.
     * 
     * @param value
     *     allowed object is
     *     {@link TBigIntegerCollection }
     *     
     */
    public void setBigInts(BigIntegerCollection value) {
        this.bigInts = value;
    }

    /**
     * Gets the value of the longs property.
     * 
     * @return
     *     possible object is
     *     {@link TLongCollection }
     *     
     */
    public LongCollection getLongs() {
        return longs;
    }

    /**
     * Sets the value of the longs property.
     * 
     * @param value
     *     allowed object is
     *     {@link TLongCollection }
     *     
     */
    public void setLongs(LongCollection value) {
        this.longs = value;
    }

    /**
     * Gets the value of the ints property.
     * 
     * @return
     *     possible object is
     *     {@link TIntCollection }
     *     
     */
    public IntegerCollection getInts() {
//    	return (IntegerCollection) this.collection;
        return ints;
    }

    /**
     * Sets the value of the ints property.
     * 
     * @param value
     *     allowed object is
     *     {@link TIntCollection }
     *     
     */
    public void setInts(IntegerCollection value) {
//    	this.collection = value;
        this.ints = value;
    }

    /**
     * Gets the value of the shorts property.
     * 
     * @return
     *     possible object is
     *     {@link TShortCollection }
     *     
     */
    public ShortCollection getShorts() {
        return shorts;
    }

    /**
     * Sets the value of the shorts property.
     * 
     * @param value
     *     allowed object is
     *     {@link TShortCollection }
     *     
     */
    public void setShorts(ShortCollection value) {
        this.shorts = value;
    }

    /**
     * Gets the value of the bytes property.
     * 
     * @return
     *     possible object is
     *     {@link TByteCollection }
     *     
     */
    public ByteCollection getBytes() {
        return bytes;
    }

    /**
     * Sets the value of the bytes property.
     * 
     * @param value
     *     allowed object is
     *     {@link TByteCollection }
     *     
     */
    public void setBytes(ByteCollection value) {
        this.bytes = value;
    }

    /**
     * Gets the value of the bigDecimals property.
     * 
     * @return
     *     possible object is
     *     {@link TBigDecimalCollection }
     *     
     */
    public BigDecimalCollection getBigDecimals() {
        return bigDecimals;
    }

    /**
     * Sets the value of the bigDecimals property.
     * 
     * @param value
     *     allowed object is
     *     {@link TBigDecimalCollection }
     *     
     */
    public void setBigDecimals(BigDecimalCollection value) {
        this.bigDecimals = value;
    }

    /**
     * Gets the value of the doubles property.
     * 
     * @return
     *     possible object is
     *     {@link TDoubleCollection }
     *     
     */
    public DoubleCollection getDoubles() {
        return doubles;
    }

    /**
     * Sets the value of the doubles property.
     * 
     * @param value
     *     allowed object is
     *     {@link TDoubleCollection }
     *     
     */
    public void setDoubles(DoubleCollection value) {
        this.doubles = value;
    }

    /**
     * Gets the value of the floats property.
     * 
     * @return
     *     possible object is
     *     {@link TFloatCollection }
     *     
     */
    public FloatCollection getFloats() {
        return floats;
    }

    /**
     * Sets the value of the floats property.
     * 
     * @param value
     *     allowed object is
     *     {@link TFloatCollection }
     *     
     */
    public void setFloats(FloatCollection value) {
        this.floats = value;
    }

    /**
     * Gets the value of the booleans property.
     * 
     * @return
     *     possible object is
     *     {@link TBooleanCollection }
     *     
     */
    public BooleanCollection getBooleans() {
//    	return (BooleanCollection) this.collection;
        return booleans;
    }

    /**
     * Sets the value of the booleans property.
     * 
     * @param value
     *     allowed object is
     *     {@link TBooleanCollection }
     *     
     */
    public void setBooleans(BooleanCollection value) {
//    	this.collection = value;
        this.booleans = value;
    }

    /**
     * Gets the value of the files property.
     * 
     * @return
     *     possible object is
     *     {@link TFileCollection }
     *     
     */
    public FileCollection getFiles() {
//    	return (FileCollection) this.collection;
        return files;
    }

    /**
     * Sets the value of the files property.
     * 
     * @param value
     *     allowed object is
     *     {@link TFileCollection }
     *     
     */
    public void setFiles(FileCollection value) {
//    	this.collection = value;
        this.files = value;
    }

    /**
     * Gets the value of the null property.
     * 
     * @return
     *     possible object is
     *     {@link TNull }
     *     
     */
    public TNull getNull() {
        return _null;
    }

    /**
     * Sets the value of the null property.
     * 
     * @param value
     *     allowed object is
     *     {@link TNull }
     *     
     */
    public void setNull(TNull value) {
        this._null = value;
    }

    /**
     * Gets the value of the anyValue property.
     * 
     * @return
     *     possible object is
     *     {@link TAnyValue }
     *     
     */
    public TAnyValue getAnyValue() {
        return anyValue;
    }

    /**
     * Sets the value of the anyValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link TAnyValue }
     *     
     */
    public void setAnyValue(TAnyValue value) {
        this.anyValue = value;
    }

    @Override
    public String toString()
    {
    	StringBuilder sb = new StringBuilder();
    	
    	for (Field field : TValue.class/*this.getClass()*/.getDeclaredFields())
    	{
    		try {
				Object val = field.get(this);
				if (val == null) { /*System.out.println("field " + field.getName() + " is null.");*/ continue; }
				sb.append(field.getName()).append(" - ").append(field.getType().getSimpleName()).append(": ").append(val);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	
    	return sb.toString();
    }

//    public void setCollection(MyCollection<?> value)
//    {
//    	this.collection = value;
//    }
//    
//    public MyCollection<?> getCollection()
//    {
//    	return this.collection;
//    }
}
