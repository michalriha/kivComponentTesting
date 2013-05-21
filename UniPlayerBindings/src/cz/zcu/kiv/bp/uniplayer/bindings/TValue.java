//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.05.09 at 09:44:48 AM CEST 
//


package cz.zcu.kiv.bp.uniplayer.bindings;

import java.io.File;
import java.math.BigDecimal;
import java.math.BigInteger;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


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
 *         &lt;element name="string" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="short" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *         &lt;element name="int" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="long" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="float" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="double" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="byte" type="{http://www.w3.org/2001/XMLSchema}byte"/>
 *         &lt;element name="boolean" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="BigInteger" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *         &lt;element name="BigDecimal" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="base64" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
 *         &lt;element name="file" type="{http://www.w3.org/2001/XMLSchema}anyURI"/>
 *         &lt;element name="stringList" type="{}TStringList"/>
 *         &lt;element name="longList" type="{}TLongList"/>
 *         &lt;element name="intList" type="{}TIntList"/>
 *         &lt;element name="shortList" type="{}TShortList"/>
 *         &lt;element name="doubleList" type="{}TDoubleList"/>
 *         &lt;element name="floatList" type="{}TFloatList"/>
 *         &lt;element name="BigDecimalList" type="{}TBigDecimalList"/>
 *         &lt;element name="BigIntegerList" type="{}TBigIntegerList"/>
 *         &lt;element name="booleanList" type="{}TBooleanList"/>
 *         &lt;element name="fileList" type="{}TFileList"/>
 *         &lt;element name="byteList" type="{}TByteList"/>
 *         &lt;element name="byteArrayList" type="{}TByteArrayList"/>
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
    "_short",
    "_int",
    "_long",
    "_float",
    "_double",
    "_byte",
    "_boolean",
    "bigInteger",
    "bigDecimal",
    "base64",
    "file",
    "stringList",
    "longList",
    "intList",
    "shortList",
    "doubleList",
    "floatList",
    "bigDecimalList",
    "bigIntegerList",
    "booleanList",
    "fileList",
    "byteList",
    "byteArrayList"
})
@XmlSeeAlso({
    TArgument.class
})
public class TValue {

    @XmlElementRef(name = "string", type = JAXBElement.class, required = false)
    protected JAXBElement<String> string;
    @XmlElementRef(name = "short", type = JAXBElement.class, required = false)
    protected JAXBElement<Short> _short;
    @XmlElementRef(name = "int", type = JAXBElement.class, required = false)
    protected JAXBElement<Integer> _int;
    @XmlElementRef(name = "long", type = JAXBElement.class, required = false)
    protected JAXBElement<Long> _long;
    @XmlElementRef(name = "float", type = JAXBElement.class, required = false)
    protected JAXBElement<Float> _float;
    @XmlElementRef(name = "double", type = JAXBElement.class, required = false)
    protected JAXBElement<Double> _double;
    @XmlElementRef(name = "byte", type = JAXBElement.class, required = false)
    protected JAXBElement<Byte> _byte;
    @XmlElementRef(name = "boolean", type = JAXBElement.class, required = false)
    protected JAXBElement<Boolean> _boolean;
    @XmlElementRef(name = "BigInteger", type = JAXBElement.class, required = false)
    protected JAXBElement<BigInteger> bigInteger;
    @XmlElementRef(name = "BigDecimal", type = JAXBElement.class, required = false)
    protected JAXBElement<BigDecimal> bigDecimal;
    @XmlElementRef(name = "base64", type = JAXBElement.class, required = false)
    protected JAXBElement<byte[]> base64;
    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "anyURI")
    protected File file;
    protected TStringList stringList;
    protected TLongList longList;
    protected TIntList intList;
    protected TShortList shortList;
    protected TDoubleList doubleList;
    protected TFloatList floatList;
    @XmlElement(name = "BigDecimalList")
    protected TBigDecimalList bigDecimalList;
    @XmlElement(name = "BigIntegerList")
    protected TBigIntegerList bigIntegerList;
    protected TBooleanList booleanList;
    protected TFileList fileList;
    protected TByteList byteList;
    protected TByteArrayList byteArrayList;

    /**
     * Gets the value of the string property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getString() {
        return string;
    }

    /**
     * Sets the value of the string property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setString(JAXBElement<String> value) {
        this.string = value;
    }

    /**
     * Gets the value of the short property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Short }{@code >}
     *     
     */
    public JAXBElement<Short> getShort() {
        return _short;
    }

    /**
     * Sets the value of the short property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Short }{@code >}
     *     
     */
    public void setShort(JAXBElement<Short> value) {
        this._short = value;
    }

    /**
     * Gets the value of the int property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public JAXBElement<Integer> getInt() {
        return _int;
    }

    /**
     * Sets the value of the int property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public void setInt(JAXBElement<Integer> value) {
        this._int = value;
    }

    /**
     * Gets the value of the long property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Long }{@code >}
     *     
     */
    public JAXBElement<Long> getLong() {
        return _long;
    }

    /**
     * Sets the value of the long property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Long }{@code >}
     *     
     */
    public void setLong(JAXBElement<Long> value) {
        this._long = value;
    }

    /**
     * Gets the value of the float property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Float }{@code >}
     *     
     */
    public JAXBElement<Float> getFloat() {
        return _float;
    }

    /**
     * Sets the value of the float property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Float }{@code >}
     *     
     */
    public void setFloat(JAXBElement<Float> value) {
        this._float = value;
    }

    /**
     * Gets the value of the double property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Double }{@code >}
     *     
     */
    public JAXBElement<Double> getDouble() {
        return _double;
    }

    /**
     * Sets the value of the double property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Double }{@code >}
     *     
     */
    public void setDouble(JAXBElement<Double> value) {
        this._double = value;
    }

    /**
     * Gets the value of the byte property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Byte }{@code >}
     *     
     */
    public JAXBElement<Byte> getByte() {
        return _byte;
    }

    /**
     * Sets the value of the byte property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Byte }{@code >}
     *     
     */
    public void setByte(JAXBElement<Byte> value) {
        this._byte = value;
    }

    /**
     * Gets the value of the boolean property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Boolean }{@code >}
     *     
     */
    public JAXBElement<Boolean> getBoolean() {
        return _boolean;
    }

    /**
     * Sets the value of the boolean property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Boolean }{@code >}
     *     
     */
    public void setBoolean(JAXBElement<Boolean> value) {
        this._boolean = value;
    }

    /**
     * Gets the value of the bigInteger property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link BigInteger }{@code >}
     *     
     */
    public JAXBElement<BigInteger> getBigInteger() {
        return bigInteger;
    }

    /**
     * Sets the value of the bigInteger property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link BigInteger }{@code >}
     *     
     */
    public void setBigInteger(JAXBElement<BigInteger> value) {
        this.bigInteger = value;
    }

    /**
     * Gets the value of the bigDecimal property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public JAXBElement<BigDecimal> getBigDecimal() {
        return bigDecimal;
    }

    /**
     * Sets the value of the bigDecimal property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public void setBigDecimal(JAXBElement<BigDecimal> value) {
        this.bigDecimal = value;
    }

    /**
     * Gets the value of the base64 property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link byte[]}{@code >}
     *     
     */
    public JAXBElement<byte[]> getBase64() {
        return base64;
    }

    /**
     * Sets the value of the base64 property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link byte[]}{@code >}
     *     
     */
    public void setBase64(JAXBElement<byte[]> value) {
        this.base64 = value;
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
     * Gets the value of the stringList property.
     * 
     * @return
     *     possible object is
     *     {@link TStringList }
     *     
     */
    public TStringList getStringList() {
        return stringList;
    }

    /**
     * Sets the value of the stringList property.
     * 
     * @param value
     *     allowed object is
     *     {@link TStringList }
     *     
     */
    public void setStringList(TStringList value) {
        this.stringList = value;
    }

    /**
     * Gets the value of the longList property.
     * 
     * @return
     *     possible object is
     *     {@link TLongList }
     *     
     */
    public TLongList getLongList() {
        return longList;
    }

    /**
     * Sets the value of the longList property.
     * 
     * @param value
     *     allowed object is
     *     {@link TLongList }
     *     
     */
    public void setLongList(TLongList value) {
        this.longList = value;
    }

    /**
     * Gets the value of the intList property.
     * 
     * @return
     *     possible object is
     *     {@link TIntList }
     *     
     */
    public TIntList getIntList() {
        return intList;
    }

    /**
     * Sets the value of the intList property.
     * 
     * @param value
     *     allowed object is
     *     {@link TIntList }
     *     
     */
    public void setIntList(TIntList value) {
        this.intList = value;
    }

    /**
     * Gets the value of the shortList property.
     * 
     * @return
     *     possible object is
     *     {@link TShortList }
     *     
     */
    public TShortList getShortList() {
        return shortList;
    }

    /**
     * Sets the value of the shortList property.
     * 
     * @param value
     *     allowed object is
     *     {@link TShortList }
     *     
     */
    public void setShortList(TShortList value) {
        this.shortList = value;
    }

    /**
     * Gets the value of the doubleList property.
     * 
     * @return
     *     possible object is
     *     {@link TDoubleList }
     *     
     */
    public TDoubleList getDoubleList() {
        return doubleList;
    }

    /**
     * Sets the value of the doubleList property.
     * 
     * @param value
     *     allowed object is
     *     {@link TDoubleList }
     *     
     */
    public void setDoubleList(TDoubleList value) {
        this.doubleList = value;
    }

    /**
     * Gets the value of the floatList property.
     * 
     * @return
     *     possible object is
     *     {@link TFloatList }
     *     
     */
    public TFloatList getFloatList() {
        return floatList;
    }

    /**
     * Sets the value of the floatList property.
     * 
     * @param value
     *     allowed object is
     *     {@link TFloatList }
     *     
     */
    public void setFloatList(TFloatList value) {
        this.floatList = value;
    }

    /**
     * Gets the value of the bigDecimalList property.
     * 
     * @return
     *     possible object is
     *     {@link TBigDecimalList }
     *     
     */
    public TBigDecimalList getBigDecimalList() {
        return bigDecimalList;
    }

    /**
     * Sets the value of the bigDecimalList property.
     * 
     * @param value
     *     allowed object is
     *     {@link TBigDecimalList }
     *     
     */
    public void setBigDecimalList(TBigDecimalList value) {
        this.bigDecimalList = value;
    }

    /**
     * Gets the value of the bigIntegerList property.
     * 
     * @return
     *     possible object is
     *     {@link TBigIntegerList }
     *     
     */
    public TBigIntegerList getBigIntegerList() {
        return bigIntegerList;
    }

    /**
     * Sets the value of the bigIntegerList property.
     * 
     * @param value
     *     allowed object is
     *     {@link TBigIntegerList }
     *     
     */
    public void setBigIntegerList(TBigIntegerList value) {
        this.bigIntegerList = value;
    }

    /**
     * Gets the value of the booleanList property.
     * 
     * @return
     *     possible object is
     *     {@link TBooleanList }
     *     
     */
    public TBooleanList getBooleanList() {
        return booleanList;
    }

    /**
     * Sets the value of the booleanList property.
     * 
     * @param value
     *     allowed object is
     *     {@link TBooleanList }
     *     
     */
    public void setBooleanList(TBooleanList value) {
        this.booleanList = value;
    }

    /**
     * Gets the value of the fileList property.
     * 
     * @return
     *     possible object is
     *     {@link TFileList }
     *     
     */
    public TFileList getFileList() {
        return fileList;
    }

    /**
     * Sets the value of the fileList property.
     * 
     * @param value
     *     allowed object is
     *     {@link TFileList }
     *     
     */
    public void setFileList(TFileList value) {
        this.fileList = value;
    }

    /**
     * Gets the value of the byteList property.
     * 
     * @return
     *     possible object is
     *     {@link TByteList }
     *     
     */
    public TByteList getByteList() {
        return byteList;
    }

    /**
     * Sets the value of the byteList property.
     * 
     * @param value
     *     allowed object is
     *     {@link TByteList }
     *     
     */
    public void setByteList(TByteList value) {
        this.byteList = value;
    }

    /**
     * Gets the value of the byteArrayList property.
     * 
     * @return
     *     possible object is
     *     {@link TByteArrayList }
     *     
     */
    public TByteArrayList getByteArrayList() {
        return byteArrayList;
    }

    /**
     * Sets the value of the byteArrayList property.
     * 
     * @param value
     *     allowed object is
     *     {@link TByteArrayList }
     *     
     */
    public void setByteArrayList(TByteArrayList value) {
        this.byteArrayList = value;
    }

}