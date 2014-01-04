package cz.zcu.kiv.bp.datatypes.bindings;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import cz.zcu.kiv.bp.datatypes.bindings.adapted.CustomTypesRegistry;
import cz.zcu.kiv.bp.datatypes.bindings.adapted.CustomTypesRegistryAdapter;


/**
 * <p>Java class for TCustomTypesSupport complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TCustomTypesSupport">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence minOccurs="0" maxOccurs="1" >
 *         &lt;element name="types" type="{http://www.kiv.zcu.cz/component-testing/types}TImportedTypes" minOccurs="0" maxOccurs="1" />               
 *         &lt;element name="values" type="{http://www.kiv.zcu.cz/component-testing/types}TListOfValuesOfImportedTypes" minOccurs="0" maxOccurs="1" />
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TCustomTypesSupport", propOrder = {
    "listOfTypes",
    "listOfValues"
})
public class TCustomTypesSupport {

    @XmlElement(name = "types", required = false)
    @XmlJavaTypeAdapter(CustomTypesRegistryAdapter.class)
    protected CustomTypesRegistry listOfTypes;
    
    @XmlElement(name = "values", required = false)
    protected TListOfValuesOfImportedTypes listOfValues;

    /**
     * Gets the value of the types property.
     * 
     * @return
     *     possible object is
     *     {@link CustomTypesRegistry }
     *     
     */
    public CustomTypesRegistry getListOfTypes() {
    	if (listOfTypes == null) {
    		listOfTypes = new CustomTypesRegistry();
    	}
        return listOfTypes;
    }

    /**
     * Sets the value of the types property.
     * 
     * @param value
     *     allowed object is
     *     {@link CustomTypesRegistry }
     *     
     */
    public void setListOfTypes(CustomTypesRegistry value) {
        this.listOfTypes = value;
    }

    /**
     * Gets the value of the values property.
     * 
     * @return
     *     possible object is
     *     {@link TListOfValuesOfImportedTypes }
     *     
     */
    public TListOfValuesOfImportedTypes getListOfValues() {
    	if (listOfValues == null) {
    		listOfValues = new TListOfValuesOfImportedTypes();
    	}
        return listOfValues;
    }

    /**
     * Sets the value of the values property.
     * 
     * @param value
     *     allowed object is
     *     {@link TListOfValuesOfImportedTypes }
     *     
     */
    public void setListOfValues(TListOfValuesOfImportedTypes value) {
        this.listOfValues = value;
    }

}
