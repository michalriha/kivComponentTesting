package cz.zcu.kiv.bp.datatypes.bindings;

import java.math.BigDecimal;
import java.math.BigInteger;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the cz.zcu.kiv.bp.datatypes.bindings.gen2 package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Null_QNAME = new QName("http://www.kiv.zcu.cz/component-testing/types", "Null");
    private final static QName _Integer_QNAME = new QName("http://www.kiv.zcu.cz/component-testing/types", "Integer");
    private final static QName _BigDecimals_QNAME = new QName("http://www.kiv.zcu.cz/component-testing/types", "BigDecimals");
    private final static QName _Bytes_QNAME = new QName("http://www.kiv.zcu.cz/component-testing/types", "Bytes");
    private final static QName _BigDecimal_QNAME = new QName("http://www.kiv.zcu.cz/component-testing/types", "BigDecimal");
    private final static QName _Shorts_QNAME = new QName("http://www.kiv.zcu.cz/component-testing/types", "Shorts");
    private final static QName _String_QNAME = new QName("http://www.kiv.zcu.cz/component-testing/types", "String");
    private final static QName _Floats_QNAME = new QName("http://www.kiv.zcu.cz/component-testing/types", "Floats");
    private final static QName _Files_QNAME = new QName("http://www.kiv.zcu.cz/component-testing/types", "Files");
    private final static QName _Strings_QNAME = new QName("http://www.kiv.zcu.cz/component-testing/types", "Strings");
    private final static QName _Byte_QNAME = new QName("http://www.kiv.zcu.cz/component-testing/types", "Byte");
    private final static QName _Ints_QNAME = new QName("http://www.kiv.zcu.cz/component-testing/types", "Ints");
    private final static QName _Longs_QNAME = new QName("http://www.kiv.zcu.cz/component-testing/types", "Longs");
    private final static QName _BigInteger_QNAME = new QName("http://www.kiv.zcu.cz/component-testing/types", "BigInteger");
    private final static QName _Boolean_QNAME = new QName("http://www.kiv.zcu.cz/component-testing/types", "Boolean");
    private final static QName _CustomTypesSupport_QNAME = new QName("http://www.kiv.zcu.cz/component-testing/types", "custom-types-support");
    private final static QName _Short_QNAME = new QName("http://www.kiv.zcu.cz/component-testing/types", "Short");
    private final static QName _CustomTypeData_QNAME = new QName("http://www.kiv.zcu.cz/component-testing/types", "CustomTypeData");
    private final static QName _File_QNAME = new QName("http://www.kiv.zcu.cz/component-testing/types", "File");
    private final static QName _BigInts_QNAME = new QName("http://www.kiv.zcu.cz/component-testing/types", "BigInts");
    private final static QName _Doubles_QNAME = new QName("http://www.kiv.zcu.cz/component-testing/types", "Doubles");
    private final static QName _Booleans_QNAME = new QName("http://www.kiv.zcu.cz/component-testing/types", "Booleans");
    private final static QName _Double_QNAME = new QName("http://www.kiv.zcu.cz/component-testing/types", "Double");
    private final static QName _Float_QNAME = new QName("http://www.kiv.zcu.cz/component-testing/types", "Float");
    private final static QName _Long_QNAME = new QName("http://www.kiv.zcu.cz/component-testing/types", "Long");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: cz.zcu.kiv.bp.datatypes.bindings.gen2
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link TImportedType }
     * 
     */
    public TImportedType createTImportedType() {
        return new TImportedType();
    }

    /**
     * Create an instance of {@link TByteCollection }
     * 
     */
    public TByteCollection createTByteCollection() {
        return new TByteCollection();
    }

    /**
     * Create an instance of {@link TBigDecimalCollection }
     * 
     */
    public TBigDecimalCollection createTBigDecimalCollection() {
        return new TBigDecimalCollection();
    }

    /**
     * Create an instance of {@link TShortCollection }
     * 
     */
    public TShortCollection createTShortCollection() {
        return new TShortCollection();
    }

    /**
     * Create an instance of {@link TFloatCollection }
     * 
     */
    public TFloatCollection createTFloatCollection() {
        return new TFloatCollection();
    }

    /**
     * Create an instance of {@link TFileCollection }
     * 
     */
    public TFileCollection createTFileCollection() {
        return new TFileCollection();
    }

    /**
     * Create an instance of {@link TStringCollection }
     * 
     */
    public TStringCollection createTStringCollection() {
        return new TStringCollection();
    }

    /**
     * Create an instance of {@link TIntCollection }
     * 
     */
    public TIntCollection createTIntCollection() {
        return new TIntCollection();
    }

    /**
     * Create an instance of {@link TLongCollection }
     * 
     */
    public TLongCollection createTLongCollection() {
        return new TLongCollection();
    }

    /**
     * Create an instance of {@link TBigIntegerCollection }
     * 
     */
    public TBigIntegerCollection createTBigIntegerCollection() {
        return new TBigIntegerCollection();
    }

    /**
     * Create an instance of {@link TBooleanCollection }
     * 
     */
    public TBooleanCollection createTBooleanCollection() {
        return new TBooleanCollection();
    }

    /**
     * Create an instance of {@link TDoubleCollection }
     * 
     */
    public TDoubleCollection createTDoubleCollection() {
        return new TDoubleCollection();
    }

    /**
     * Create an instance of {@link TCustomTypeData }
     * 
     */
    public TCustomTypeData createTCustomTypeData() {
        return new TCustomTypeData();
    }

    /**
     * Create an instance of {@link TCustomTypesSupport }
     * 
     */
    public TCustomTypesSupport createTCustomTypesSupport() {
        return new TCustomTypesSupport();
    }

    /**
     * Create an instance of {@link TNull }
     * 
     */
    public TNull createTNull() {
        return new TNull();
    }

    /**
     * Create an instance of {@link TValue }
     * 
     */
    public TValue createTValue() {
        return new TValue();
    }

    /**
     * Create an instance of {@link TImportedTypes }
     * 
     */
    public TImportedTypes createTImportedTypes() {
        return new TImportedTypes();
    }

    /**
     * Create an instance of {@link TStaticMemberFactory }
     * 
     */
    public TStaticMemberFactory createTStaticMemberFactory() {
        return new TStaticMemberFactory();
    }

    /**
     * Create an instance of {@link TMethod }
     * 
     */
    public TMethod createTMethod() {
        return new TMethod();
    }

    /**
     * Create an instance of {@link TConstructFactory }
     * 
     */
    public TConstructFactory createTConstructFactory() {
        return new TConstructFactory();
    }

    /**
     * Create an instance of {@link TArgumentsList }
     * 
     */
    public TArgumentsList createTArgumentsList() {
        return new TArgumentsList();
    }

    /**
     * Create an instance of {@link TListOfValuesOfImportedTypes }
     * 
     */
    public TListOfValuesOfImportedTypes createTListOfValuesOfImportedTypes() {
        return new TListOfValuesOfImportedTypes();
    }

    /**
     * Create an instance of {@link TBundle }
     * 
     */
    public TBundle createTBundle() {
        return new TBundle();
    }

    /**
     * Create an instance of {@link TValueOfImportedType }
     * 
     */
    public TValueOfImportedType createTValueOfImportedType() {
        return new TValueOfImportedType();
    }

    /**
     * Create an instance of {@link TArgument }
     * 
     */
    public TArgument createTArgument() {
        return new TArgument();
    }

    /**
     * Create an instance of {@link TExternalFactory }
     * 
     */
    public TExternalFactory createTExternalFactory() {
        return new TExternalFactory();
    }

    /**
     * Create an instance of {@link TFactory }
     * 
     */
    public TFactory createTImportedTypeFactory() {
        return new TFactory();
    }

    /**
     * Create an instance of {@link TByteCollection.Item }
     * 
     */
    public TByteCollection.Item createTByteCollectionItem() {
        return new TByteCollection.Item();
    }

    /**
     * Create an instance of {@link TBigDecimalCollection.Item }
     * 
     */
    public TBigDecimalCollection.Item createTBigDecimalCollectionItem() {
        return new TBigDecimalCollection.Item();
    }

    /**
     * Create an instance of {@link TShortCollection.Item }
     * 
     */
    public TShortCollection.Item createTShortCollectionItem() {
        return new TShortCollection.Item();
    }

    /**
     * Create an instance of {@link TFloatCollection.Item }
     * 
     */
    public TFloatCollection.Item createTFloatCollectionItem() {
        return new TFloatCollection.Item();
    }

    /**
     * Create an instance of {@link TFileCollection.Item }
     * 
     */
    public TFileCollection.Item createTFileCollectionItem() {
        return new TFileCollection.Item();
    }

    /**
     * Create an instance of {@link TStringCollection.Item }
     * 
     */
    public TStringCollection.Item createTStringCollectionItem() {
        return new TStringCollection.Item();
    }

    /**
     * Create an instance of {@link TIntCollection.Item }
     * 
     */
    public TIntCollection.Item createTIntCollectionItem() {
        return new TIntCollection.Item();
    }

    /**
     * Create an instance of {@link TLongCollection.Item }
     * 
     */
    public TLongCollection.Item createTLongCollectionItem() {
        return new TLongCollection.Item();
    }

    /**
     * Create an instance of {@link TBigIntegerCollection.Item }
     * 
     */
    public TBigIntegerCollection.Item createTBigIntegerCollectionItem() {
        return new TBigIntegerCollection.Item();
    }

    /**
     * Create an instance of {@link TBooleanCollection.Item }
     * 
     */
    public TBooleanCollection.Item createTBooleanCollectionItem() {
        return new TBooleanCollection.Item();
    }

    /**
     * Create an instance of {@link TDoubleCollection.Item }
     * 
     */
    public TDoubleCollection.Item createTDoubleCollectionItem() {
        return new TDoubleCollection.Item();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TNull }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.kiv.zcu.cz/component-testing/types", name = "Null")
    public JAXBElement<TNull> createNull(TNull value) {
        return new JAXBElement<TNull>(_Null_QNAME, TNull.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.kiv.zcu.cz/component-testing/types", name = "Integer")
    public JAXBElement<Integer> createInteger(Integer value) {
        return new JAXBElement<Integer>(_Integer_QNAME, Integer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TBigDecimalCollection }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.kiv.zcu.cz/component-testing/types", name = "BigDecimals")
    public JAXBElement<TBigDecimalCollection> createBigDecimals(TBigDecimalCollection value) {
        return new JAXBElement<TBigDecimalCollection>(_BigDecimals_QNAME, TBigDecimalCollection.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TByteCollection }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.kiv.zcu.cz/component-testing/types", name = "Bytes")
    public JAXBElement<TByteCollection> createBytes(TByteCollection value) {
        return new JAXBElement<TByteCollection>(_Bytes_QNAME, TByteCollection.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.kiv.zcu.cz/component-testing/types", name = "BigDecimal")
    public JAXBElement<BigDecimal> createBigDecimal(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_BigDecimal_QNAME, BigDecimal.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TShortCollection }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.kiv.zcu.cz/component-testing/types", name = "Shorts")
    public JAXBElement<TShortCollection> createShorts(TShortCollection value) {
        return new JAXBElement<TShortCollection>(_Shorts_QNAME, TShortCollection.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.kiv.zcu.cz/component-testing/types", name = "String")
    public JAXBElement<String> createString(String value) {
        return new JAXBElement<String>(_String_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TFloatCollection }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.kiv.zcu.cz/component-testing/types", name = "Floats")
    public JAXBElement<TFloatCollection> createFloats(TFloatCollection value) {
        return new JAXBElement<TFloatCollection>(_Floats_QNAME, TFloatCollection.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TFileCollection }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.kiv.zcu.cz/component-testing/types", name = "Files")
    public JAXBElement<TFileCollection> createFiles(TFileCollection value) {
        return new JAXBElement<TFileCollection>(_Files_QNAME, TFileCollection.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TStringCollection }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.kiv.zcu.cz/component-testing/types", name = "Strings")
    public JAXBElement<TStringCollection> createStrings(TStringCollection value) {
        return new JAXBElement<TStringCollection>(_Strings_QNAME, TStringCollection.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Byte }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.kiv.zcu.cz/component-testing/types", name = "Byte")
    public JAXBElement<Byte> createByte(Byte value) {
        return new JAXBElement<Byte>(_Byte_QNAME, Byte.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TIntCollection }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.kiv.zcu.cz/component-testing/types", name = "Ints")
    public JAXBElement<TIntCollection> createInts(TIntCollection value) {
        return new JAXBElement<TIntCollection>(_Ints_QNAME, TIntCollection.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TLongCollection }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.kiv.zcu.cz/component-testing/types", name = "Longs")
    public JAXBElement<TLongCollection> createLongs(TLongCollection value) {
        return new JAXBElement<TLongCollection>(_Longs_QNAME, TLongCollection.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.kiv.zcu.cz/component-testing/types", name = "BigInteger")
    public JAXBElement<BigInteger> createBigInteger(BigInteger value) {
        return new JAXBElement<BigInteger>(_BigInteger_QNAME, BigInteger.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.kiv.zcu.cz/component-testing/types", name = "Boolean")
    public JAXBElement<Boolean> createBoolean(Boolean value) {
        return new JAXBElement<Boolean>(_Boolean_QNAME, Boolean.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TCustomTypesSupport }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.kiv.zcu.cz/component-testing/types", name = "custom-types-support")
    public JAXBElement<TCustomTypesSupport> createCustomTypesSupport(TCustomTypesSupport value) {
        return new JAXBElement<TCustomTypesSupport>(_CustomTypesSupport_QNAME, TCustomTypesSupport.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Short }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.kiv.zcu.cz/component-testing/types", name = "Short")
    public JAXBElement<Short> createShort(Short value) {
        return new JAXBElement<Short>(_Short_QNAME, Short.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TCustomTypeData }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.kiv.zcu.cz/component-testing/types", name = "CustomTypeData")
    public JAXBElement<TCustomTypeData> createCustomTypeData(TCustomTypeData value) {
        return new JAXBElement<TCustomTypeData>(_CustomTypeData_QNAME, TCustomTypeData.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.kiv.zcu.cz/component-testing/types", name = "File")
    public JAXBElement<String> createFile(String value) {
        return new JAXBElement<String>(_File_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TBigIntegerCollection }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.kiv.zcu.cz/component-testing/types", name = "BigInts")
    public JAXBElement<TBigIntegerCollection> createBigInts(TBigIntegerCollection value) {
        return new JAXBElement<TBigIntegerCollection>(_BigInts_QNAME, TBigIntegerCollection.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TDoubleCollection }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.kiv.zcu.cz/component-testing/types", name = "Doubles")
    public JAXBElement<TDoubleCollection> createDoubles(TDoubleCollection value) {
        return new JAXBElement<TDoubleCollection>(_Doubles_QNAME, TDoubleCollection.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TBooleanCollection }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.kiv.zcu.cz/component-testing/types", name = "Booleans")
    public JAXBElement<TBooleanCollection> createBooleans(TBooleanCollection value) {
        return new JAXBElement<TBooleanCollection>(_Booleans_QNAME, TBooleanCollection.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.kiv.zcu.cz/component-testing/types", name = "Double")
    public JAXBElement<Double> createDouble(Double value) {
        return new JAXBElement<Double>(_Double_QNAME, Double.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Float }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.kiv.zcu.cz/component-testing/types", name = "Float")
    public JAXBElement<Float> createFloat(Float value) {
        return new JAXBElement<Float>(_Float_QNAME, Float.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.kiv.zcu.cz/component-testing/types", name = "Long")
    public JAXBElement<Long> createLong(Long value) {
        return new JAXBElement<Long>(_Long_QNAME, Long.class, null, value);
    }

}
