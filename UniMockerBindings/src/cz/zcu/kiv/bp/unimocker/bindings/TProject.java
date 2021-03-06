package cz.zcu.kiv.bp.unimocker.bindings;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import cz.zcu.kiv.bp.unimocker.bindings.adapted.BundlesMap;
import cz.zcu.kiv.bp.unimocker.bindings.adapted.BundlesMapAdapter;
import cz.zcu.kiv.bp.unimocker.bindings.TSettings;


/**
 * <p>Java class for TProject complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TProject">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="settings" type="{http://www.kiv.zcu.cz/component-testing/mocker}TSettings" minOccurs="0" maxOccurs="1"/>
 *         &lt;element name="simulated-components" type="{http://www.kiv.zcu.cz/component-testing/mocker}TBundleList" minOccurs="0" maxOccurs="1" />
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TProject", propOrder = {
	"settings",
    "simulatedComponents"
})
public class TProject
{
	@XmlElement(required = false)
    protected TSettings settings;

    @XmlElement(name = "simulated-components", type = TBundleList.class)
    @XmlJavaTypeAdapter(BundlesMapAdapter.class)
    protected BundlesMap simulatedComponents;

    /**
     * Gets the value of the settings property.
     * 
     * @return
     *     possible object is
     *     {@link TSettings }
     *     
     */
    public TSettings getSettings() {
    	if (settings == null) {
    		settings = new TSettings();
    	}
        return settings;
    }

    /**
     * Sets the value of the settings property.
     * 
     * @param value
     *     allowed object is
     *     {@link TSettings }
     *     
     */
    public void setSettings(TSettings value) {
        this.settings = value;
    }

    /**
     * Gets the value of the simulatedComponents property.
     * 
     * @return
     *     possible object is
     *     {@link BundlesMap }
     *     
     */
    public BundlesMap getSimulatedComponents() {
        return simulatedComponents;
    }

    /**
     * Sets the value of the simulatedComponents property.
     * 
     * @param value
     *     allowed object is
     *     {@link BundlesMap }
     *     
     */
    public void setSimulatedComponents(BundlesMap value) {
        this.simulatedComponents = value;
    }
}
