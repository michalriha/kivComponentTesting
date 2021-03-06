package cz.zcu.kiv.bp.uniplayer.bindings;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import cz.zcu.kiv.bp.uniplayer.bindings.adapted.ActionsMap;
import cz.zcu.kiv.bp.uniplayer.bindings.adapted.ActionsMapAdapter;


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
 *         &lt;element name="settings" type="{http://www.kiv.zcu.cz/component-testing/player}TSettings" minOccurs="0"/>
 *         &lt;element name="actions" type="{http://www.kiv.zcu.cz/component-testing/player}TActionsList"/>
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
    "actions"
})
public class TProject {

	@XmlElement(required = false)
    protected TSettings settings;
    
    @XmlElement(required = true, type = TActionsList.class)
    @XmlJavaTypeAdapter(ActionsMapAdapter.class)
    protected ActionsMap actions;

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
     * Gets the value of the actions property.
     * 
     * @return
     *     possible object is
     *     {@link TActionsList }
     *     
     */
    public ActionsMap getActions() {
        return actions;
    }

    /**
     * Sets the value of the actions property.
     * 
     * @param value
     *     allowed object is
     *     {@link TActionsList }
     *     
     */
    public void setActions(ActionsMap value) {
        this.actions = value;
    }

}
