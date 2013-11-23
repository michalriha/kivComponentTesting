package cz.zcu.kiv.bp.uniplayer.bindings;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the cz.zcu.kiv.bp.uniplayer.bindings package. 
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
public class ObjectFactory
{

	private final static QName _Project_QNAME = new QName("http://www.kiv.zcu.cz/component-testing/player", "project");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: cz.zcu.kiv.bp.uniplayer.bindings
     * 
     */
    public ObjectFactory() {
    }

//    /**
//     * Create an instance of {@link TShortCollection }
//     * 
//     */
//    public TShortCollection createTShortCollection() {
//        return new TShortCollection();
//    }
//
//    /**
//     * Create an instance of {@link TDoubleCollection }
//     * 
//     */
//    public TDoubleCollection createTDoubleCollection() {
//        return new TDoubleCollection();
//    }
//
//    /**
//     * Create an instance of {@link TBigIntegerCollection }
//     * 
//     */
//    public TBigIntegerCollection createTBigIntegerCollection() {
//        return new TBigIntegerCollection();
//    }
//
//    /**
//     * Create an instance of {@link TBooleanCollection }
//     * 
//     */
//    public TBooleanCollection createTBooleanCollection() {
//        return new TBooleanCollection();
//    }
//
//    /**
//     * Create an instance of {@link TLongCollection }
//     * 
//     */
//    public TLongCollection createTLongCollection() {
//        return new TLongCollection();
//    }
//
//    /**
//     * Create an instance of {@link TFileCollection }
//     * 
//     */
//    public TFileCollection createTFileCollection() {
//        return new TFileCollection();
//    }
//
//    /**
//     * Create an instance of {@link TStringCollection }
//     * 
//     */
//    public TStringCollection createTStringCollection() {
//        return new TStringCollection();
//    }
//
//    /**
//     * Create an instance of {@link TBigDecimalCollection }
//     * 
//     */
//    public TBigDecimalCollection createTBigDecimalCollection() {
//        return new TBigDecimalCollection();
//    }
//
//    /**
//     * Create an instance of {@link TFloatCollection }
//     * 
//     */
//    public TFloatCollection createTFloatCollection() {
//        return new TFloatCollection();
//    }
//
//    /**
//     * Create an instance of {@link TByteCollection }
//     * 
//     */
//    public TByteCollection createTByteCollection() {
//        return new TByteCollection();
//    }
//
//    /**
//     * Create an instance of {@link TIntCollection }
//     * 
//     */
//    public TIntCollection createTIntCollection() {
//        return new TIntCollection();
//    }

    /**
     * Create an instance of {@link TProject }
     * 
     */
    public TProject createTProject() {
        return new TProject();
    }

    /**
     * Create an instance of {@link TExponential }
     * 
     */
    public TExponential createTExponential() {
        return new TExponential();
    }

    /**
     * Create an instance of {@link TValue }
     * 
     */
    public TValue createTValue() {
        return new TValue();
    }

//    /**
//     * Create an instance of {@link TNull }
//     * 
//     */
//    public TNull createTNull() {
//        return new TNull();
//    }

    /**
     * Create an instance of {@link TActionsList }
     * 
     */
    public TActionsList createTActionsList() {
        return new TActionsList();
    }

    /**
     * Create an instance of {@link TCall }
     * 
     */
    public TCall createTCall() {
        return new TCall();
    }

    /**
     * Create an instance of {@link TRecurrence }
     * 
     */
    public TRecurrence createTRecurrence() {
        return new TRecurrence();
    }

    /**
     * Create an instance of {@link TAction }
     * 
     */
    public TAction createTAction() {
        return new TAction();
    }

    /**
     * Create an instance of {@link TEvent }
     * 
     */
    public TEvent createTEvent() {
        return new TEvent();
    }

    /**
     * Create an instance of {@link TEquidistant }
     * 
     */
    public TEquidistant createTEquidistant() {
        return new TEquidistant();
    }

    /**
     * Create an instance of {@link TGaussian }
     * 
     */
    public TGaussian createTGaussian() {
        return new TGaussian();
    }

    /**
     * Create an instance of {@link TCommand }
     * 
     */
    public TCommand createTCommand() {
        return new TCommand();
    }

    /**
     * Create an instance of {@link TSettings }
     * 
     */
    public TSettings createTSettings() {
        return new TSettings();
    }

    /**
     * Create an instance of {@link TArgumentsList }
     * 
     */
    public TArgumentsList createTArgumentsList() {
        return new TArgumentsList();
    }

    /**
     * Create an instance of {@link TArgument }
     * 
     */
    public TArgument createTArgument() {
        return new TArgument();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TProject }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.kiv.zcu.cz/component-testing/player", name = "project")
    public JAXBElement<TProject> createProject(TProject value) {
        return new JAXBElement<TProject>(_Project_QNAME, TProject.class, null, value);
    }

}
