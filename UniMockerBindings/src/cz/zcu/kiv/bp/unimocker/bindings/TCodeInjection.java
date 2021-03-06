package cz.zcu.kiv.bp.unimocker.bindings;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for TCodeInjection complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TCodeInjection">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;element name="call">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;choice>
 *                   &lt;element name="static">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="bundle">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;attribute name="symbolic-name" type="{http://www.kiv.zcu.cz/component-testing/mocker}TBundleName" />
 *                                     &lt;attribute name="version" type="{http://www.kiv.zcu.cz/component-testing/mocker}TVersion" />
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                             &lt;element name="method">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;attribute name="name" type="{http://www.kiv.zcu.cz/component-testing/mocker}TInterfaceName" />
 *                                     &lt;attribute name="overrides-return-values" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="service">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="method">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;attribute name="name" type="{http://www.kiv.zcu.cz/component-testing/mocker}TMethodName" />
 *                                     &lt;attribute name="overrides-return-values" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                           &lt;/sequence>
 *                           &lt;attribute name="name" type="{http://www.kiv.zcu.cz/component-testing/mocker}TInterfaceName" />
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/choice>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TCodeInjection", namespace = "http://www.kiv.zcu.cz/component-testing/mocker", propOrder = {
    "call"
})
public class TCodeInjection {

    protected TCodeInjection.Call call;

    /**
     * Gets the value of the call property.
     * 
     * @return
     *     possible object is
     *     {@link TCodeInjection.Call }
     *     
     */
    public TCodeInjection.Call getCall() {
        return call;
    }

    /**
     * Sets the value of the call property.
     * 
     * @param value
     *     allowed object is
     *     {@link TCodeInjection.Call }
     *     
     */
    public void setCall(TCodeInjection.Call value) {
        this.call = value;
    }

    /**
     * Returns true if current injection is method call.
     * So far no other injection is supported.
     * @return this injected code is call
     */
    public boolean isCall()
    {
    	return this.call != null ? true : false; 
    }
    
    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;choice>
     *         &lt;element name="static">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="bundle">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;attribute name="symbolic-name" type="{http://www.kiv.zcu.cz/component-testing/mocker}TBundleName" />
     *                           &lt;attribute name="version" type="{http://www.kiv.zcu.cz/component-testing/mocker}TVersion" />
     *                         &lt;/restriction>
     *                       &lt;/complexContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                   &lt;element name="method">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;attribute name="name" type="{http://www.kiv.zcu.cz/component-testing/mocker}TInterfaceName" />
     *                           &lt;attribute name="overrides-return-values" type="{http://www.w3.org/2001/XMLSchema}boolean" />
     *                         &lt;/restriction>
     *                       &lt;/complexContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="service">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="method">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;attribute name="name" type="{http://www.kiv.zcu.cz/component-testing/mocker}TMethodName" />
     *                           &lt;attribute name="overrides-return-values" type="{http://www.w3.org/2001/XMLSchema}boolean" />
     *                         &lt;/restriction>
     *                       &lt;/complexContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                 &lt;/sequence>
     *                 &lt;attribute name="name" type="{http://www.kiv.zcu.cz/component-testing/mocker}TInterfaceName" />
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *       &lt;/choice>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "_static",
        "service"
    })
    public static class Call {

        @XmlElement(name = "static", namespace = "http://www.kiv.zcu.cz/component-testing/mocker")
        protected TCodeInjection.Call.Static _static;
        
        @XmlElement(namespace = "http://www.kiv.zcu.cz/component-testing/mocker")
        protected TCodeInjection.Call.Service service;

        /**
         * Gets the value of the static property.
         * 
         * @return
         *     possible object is
         *     {@link TCodeInjection.Call.Static }
         *     
         */
        public TCodeInjection.Call.Static getStatic() {
            return _static;
        }

        /**
         * Sets the value of the static property.
         * 
         * @param value
         *     allowed object is
         *     {@link TCodeInjection.Call.Static }
         *     
         */
        public void setStatic(TCodeInjection.Call.Static value) {
            this._static = value;
        }

        /**
         * Gets the value of the service property.
         * 
         * @return
         *     possible object is
         *     {@link TCodeInjection.Call.Service }
         *     
         */
        public TCodeInjection.Call.Service getService() {
            return service;
        }

        /**
         * Sets the value of the service property.
         * 
         * @param value
         *     allowed object is
         *     {@link TCodeInjection.Call.Service }
         *     
         */
        public void setService(TCodeInjection.Call.Service value) {
            this.service = value;
        }

        /**
         * Returns true if current injection is represented by a call of a static method.
         * @return this injected code is event
         */
        public boolean isStatic()
        {
        	return this._static == null ? false : true;
        }

        /**
         * Returns true if current injection is represented by a call of
         * a method on an instance of a service provider. 
         * @return this injected code usage of service
         */
        public boolean isService()
        {
        	return this.service == null ? false : true;
        }
        
        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;sequence>
         *         &lt;element name="method">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;attribute name="name" type="{http://www.kiv.zcu.cz/component-testing/mocker}TMethodName" />
         *                 &lt;attribute name="overrides-return-values" type="{http://www.w3.org/2001/XMLSchema}boolean" />
         *               &lt;/restriction>
         *             &lt;/complexContent>
         *           &lt;/complexType>
         *         &lt;/element>
         *       &lt;/sequence>
         *       &lt;attribute name="name" type="{http://www.kiv.zcu.cz/component-testing/mocker}TInterfaceName" />
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "method"
        })
        public static class Service {

            @XmlElement(namespace = "http://www.kiv.zcu.cz/component-testing/mocker", required = true)
            protected TCodeInjection.Call.Service.Method method;
            
            @XmlAttribute(name = "name")
            @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
            protected String name;

            /**
             * Gets the value of the method property.
             * 
             * @return
             *     possible object is
             *     {@link TCodeInjection.Call.Service.Method }
             *     
             */
            public TCodeInjection.Call.Service.Method getMethod() {
                return method;
            }

            /**
             * Sets the value of the method property.
             * 
             * @param value
             *     allowed object is
             *     {@link TCodeInjection.Call.Service.Method }
             *     
             */
            public void setMethod(TCodeInjection.Call.Service.Method value) {
                this.method = value;
            }

            /**
             * Gets the value of the name property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getName() {
                return name;
            }

            /**
             * Sets the value of the name property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setName(String value) {
                this.name = value;
            }


            /**
             * <p>Java class for anonymous complex type.
             * 
             * <p>The following schema fragment specifies the expected content contained within this class.
             * 
             * <pre>
             * &lt;complexType>
             *   &lt;complexContent>
             *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *       &lt;attribute name="name" type="{http://www.kiv.zcu.cz/component-testing/mocker}TMethodName" />
             *       &lt;attribute name="overrides-return-values" type="{http://www.w3.org/2001/XMLSchema}boolean" />
             *     &lt;/restriction>
             *   &lt;/complexContent>
             * &lt;/complexType>
             * </pre>
             * 
             * 
             */
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "")
            public static class Method {

                @XmlAttribute(name = "name")
                @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
                protected String name;
                @XmlAttribute(name = "overrides-return-values")
                protected Boolean overridesReturnValues;

                /**
                 * Gets the value of the name property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getName() {
                    return name;
                }

                /**
                 * Sets the value of the name property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setName(String value) {
                    this.name = value;
                }

                /**
                 * Gets the value of the overridesReturnValues property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Boolean }
                 *     
                 */
                public Boolean isOverridesReturnValues() {
                    return overridesReturnValues;
                }

                /**
                 * Sets the value of the overridesReturnValues property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Boolean }
                 *     
                 */
                public void setOverridesReturnValues(Boolean value) {
                    this.overridesReturnValues = value;
                }
                
                public String toString()
                {
                	return this.getName() + " (" + this.isOverridesReturnValues() + ")";
                }

            }
            
            public String toString()
            {
            	StringBuilder sb = new StringBuilder();
            	
            	sb.append(this.getName() + "." + this.getMethod());
            	
            	return sb.toString();
            }

        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;sequence>
         *         &lt;element name="bundle">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;attribute name="symbolic-name" type="{http://www.kiv.zcu.cz/component-testing/mocker}TBundleName" />
         *                 &lt;attribute name="version" type="{http://www.kiv.zcu.cz/component-testing/mocker}TVersion" />
         *               &lt;/restriction>
         *             &lt;/complexContent>
         *           &lt;/complexType>
         *         &lt;/element>
         *         &lt;element name="method">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;attribute name="name" type="{http://www.kiv.zcu.cz/component-testing/mocker}TInterfaceName" />
         *                 &lt;attribute name="overrides-return-values" type="{http://www.w3.org/2001/XMLSchema}boolean" />
         *               &lt;/restriction>
         *             &lt;/complexContent>
         *           &lt;/complexType>
         *         &lt;/element>
         *       &lt;/sequence>
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "bundle",
            "method"
        })
        public static class Static {

            @XmlElement(namespace = "http://www.kiv.zcu.cz/component-testing/mocker", required = true)
            protected TCodeInjection.Call.Static.Bundle bundle;
            @XmlElement(namespace = "http://www.kiv.zcu.cz/component-testing/mocker", required = true)
            protected TCodeInjection.Call.Static.Method method;

            /**
             * Gets the value of the bundle property.
             * 
             * @return
             *     possible object is
             *     {@link TCodeInjection.Call.Static.Bundle }
             *     
             */
            public TCodeInjection.Call.Static.Bundle getBundle() {
                return bundle;
            }

            /**
             * Sets the value of the bundle property.
             * 
             * @param value
             *     allowed object is
             *     {@link TCodeInjection.Call.Static.Bundle }
             *     
             */
            public void setBundle(TCodeInjection.Call.Static.Bundle value) {
                this.bundle = value;
            }

            /**
             * Gets the value of the method property.
             * 
             * @return
             *     possible object is
             *     {@link TCodeInjection.Call.Static.Method }
             *     
             */
            public TCodeInjection.Call.Static.Method getMethod() {
                return method;
            }

            /**
             * Sets the value of the method property.
             * 
             * @param value
             *     allowed object is
             *     {@link TCodeInjection.Call.Static.Method }
             *     
             */
            public void setMethod(TCodeInjection.Call.Static.Method value) {
                this.method = value;
            }


            /**
             * <p>Java class for anonymous complex type.
             * 
             * <p>The following schema fragment specifies the expected content contained within this class.
             * 
             * <pre>
             * &lt;complexType>
             *   &lt;complexContent>
             *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *       &lt;attribute name="symbolic-name" type="{http://www.kiv.zcu.cz/component-testing/mocker}TBundleName" />
             *       &lt;attribute name="version" type="{http://www.kiv.zcu.cz/component-testing/mocker}TVersion" />
             *     &lt;/restriction>
             *   &lt;/complexContent>
             * &lt;/complexType>
             * </pre>
             * 
             * 
             */
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "")
            public static class Bundle {

                @XmlAttribute(name = "symbolic-name")
                @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
                protected String symbolicName;
                @XmlAttribute(name = "version")
                @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
                protected String version;

                /**
                 * Gets the value of the symbolicName property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getSymbolicName() {
                    return symbolicName;
                }

                /**
                 * Sets the value of the symbolicName property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setSymbolicName(String value) {
                    this.symbolicName = value;
                }

                /**
                 * Gets the value of the version property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getVersion() {
                    return version;
                }

                /**
                 * Sets the value of the version property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setVersion(String value) {
                    this.version = value;
                }
                
                public String toString()
                {
                	return this.getSymbolicName() + ":" + this.getVersion();
                }

            }


            /**
             * <p>Java class for anonymous complex type.
             * 
             * <p>The following schema fragment specifies the expected content contained within this class.
             * 
             * <pre>
             * &lt;complexType>
             *   &lt;complexContent>
             *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *       &lt;attribute name="name" type="{http://www.kiv.zcu.cz/component-testing/mocker}TInterfaceName" />
             *       &lt;attribute name="overrides-return-values" type="{http://www.w3.org/2001/XMLSchema}boolean" />
             *     &lt;/restriction>
             *   &lt;/complexContent>
             * &lt;/complexType>
             * </pre>
             * 
             * 
             */
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "")
            public static class Method {

                @XmlAttribute(name = "name")
                @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
                protected String name;
                @XmlAttribute(name = "overrides-return-values")
                protected Boolean overridesReturnValues;

                /**
                 * Gets the value of the name property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getName() {
                    return name;
                }

                /**
                 * Sets the value of the name property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setName(String value) {
                    this.name = value;
                }

                /**
                 * Gets the value of the overridesReturnValues property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Boolean }
                 *     
                 */
                public Boolean isOverridesReturnValues() {
                    return overridesReturnValues;
                }

                /**
                 * Sets the value of the overridesReturnValues property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Boolean }
                 *     
                 */
                public void setOverridesReturnValues(Boolean value) {
                    this.overridesReturnValues = value;
                }
                
                public String toString()
                {
                	return this.getName() + " (" + this.isOverridesReturnValues() + ")";
                }

            }
            
            public String toString()
            {
            	StringBuilder sb = new StringBuilder();
            	
            	sb.append(this.getBundle());
            	sb.append("!");
            	sb.append(this.getMethod());
            	
            	return sb.toString();
            }

        }

    }

}
