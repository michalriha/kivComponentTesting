package cz.zcu.kiv.bp.unimocker.bindings;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the cz.zcu.kiv.bp.unimocker.bindings package. 
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

	private final static QName _Project_QNAME = new QName("http://www.kiv.zcu.cz/component-testing/mocker", "project");
    
    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: cz.zcu.kiv.bp.unimocker.bindings
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link TProject }
     * 
     */
    public TProject createTProject() {
        return new TProject();
    }

    /**
     * Create an instance of {@link TValue }
     * 
     */
    public TValue createTValue() {
        return new TValue();
    }

    /**
     * Create an instance of {@link TInvokedMethod }
     * 
     */
    public TInvokedMethod createTInvokedMethod() {
        return new TInvokedMethod();
    }

    /**
     * Create an instance of {@link TCodeInjection }
     * 
     */
    public TCodeInjection createTCodeInjection() {
        return new TCodeInjection();
    }

    /**
     * Create an instance of {@link TCodeInjection.Call }
     * 
     */
    public TCodeInjection.Call createTCodeInjectionCall() {
        return new TCodeInjection.Call();
    }

    /**
     * Create an instance of {@link TCodeInjection.Call.Service }
     * 
     */
    public TCodeInjection.Call.Service createTCodeInjectionCallService() {
        return new TCodeInjection.Call.Service();
    }

    /**
     * Create an instance of {@link TCodeInjection.Call.Static }
     * 
     */
    public TCodeInjection.Call.Static createTCodeInjectionCallStatic() {
        return new TCodeInjection.Call.Static();
    }

    /**
     * Create an instance of {@link TCodeInjection.Call.Service.Method }
     * 
     */
    public TCodeInjection.Call.Service.Method createTCodeInjectionCallServiceMethod() {
        return new TCodeInjection.Call.Service.Method();
    }

    /**
     * Create an instance of {@link TCodeInjection.Call.Static.Bundle }
     * 
     */
    public TCodeInjection.Call.Static.Bundle createTCodeInjectionCallStaticBundle() {
        return new TCodeInjection.Call.Static.Bundle();
    }

    /**
     * Create an instance of {@link TCodeInjection.Call.Static.Method }
     * 
     */
    public TCodeInjection.Call.Static.Method createTCodeInjectionCallStaticMethod() {
        return new TCodeInjection.Call.Static.Method();
    }

    /**
     * Create an instance of {@link TSimulatedService }
     * 
     */
    public TSimulatedService createTSimulatedService() {
        return new TSimulatedService();
    }

    /**
     * Create an instance of {@link TBundleList }
     * 
     */
    public TBundleList createTBundleList() {
        return new TBundleList();
    }

    /**
     * Create an instance of {@link TArgumentsList }
     * 
     */
    public TArgumentsList createTArgumentsList() {
        return new TArgumentsList();
    }

    /**
     * Create an instance of {@link TBundle }
     * 
     */
    public TBundle createTBundle() {
        return new TBundle();
    }

    /**
     * Create an instance of {@link TAnyValue }
     * 
     */
    public TAnyValue createTAnyValue() {
        return new TAnyValue();
    }

    /**
     * Create an instance of {@link TArgument }
     * 
     */
    public TArgument createTArgument() {
        return new TArgument();
    }

    /**
     * Create an instance of {@link TInvocation }
     * 
     */
    public TInvocation createTInvocation() {
        return new TInvocation();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TProject }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.kiv.zcu.cz/component-testing/mocker", name = "project")
    public JAXBElement<TProject> createProject(TProject value) {
        return new JAXBElement<TProject>(_Project_QNAME, TProject.class, null, value);
    }

}
