package cz.zcu.kiv.bp.uniplayer.bindings;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import cz.zcu.kiv.bp.datatypes.bindings.TCustomTypesSupport;


/**
 * <p>Java class for TSettings complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TSettings">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="time-limit" type="{http://www.kiv.zcu.cz/component-testing/player}TPositiveLong" minOccurs="0"/>
 *         &lt;element name="simul-step-delay" type="{http://www.kiv.zcu.cz/component-testing/player}TNonNegativeLong" minOccurs="0"/>
 *         &lt;element ref="{http://www.kiv.zcu.cz/component-testing/types}custom-types-support" />
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(name = "TSettings", propOrder = {
    "timeLimit",
    "simulStepDelay",
    "customTypesSupport"
})
public class TSettings {

	public static final long MAX_SIMUL_DURRATION = Long.MAX_VALUE;
	
	public static final long DEFAULT_SIMUL_STEP = 0L;
	
    protected long timeLimit = MAX_SIMUL_DURRATION;
    
    protected long simulStepDelay = DEFAULT_SIMUL_STEP;

    protected TCustomTypesSupport customTypesSupport;
    
    /**
     * Gets the value of the timeLimit property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    @XmlElement(name = "time-limit")
    public long getTimeLimit() {
        return timeLimit;
    }

    /**
     * Sets the value of the timeLimit property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setTimeLimit(long value) {
        this.timeLimit = value;
    }

    /**
     * Gets the value of the simulStepDelay property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    @XmlElement(name = "simul-step-delay")
    public long getSimulStepDelay() {
        return simulStepDelay;
    }

    /**
     * Sets the value of the simulStepDelay property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setSimulStepDelay(long value) {
        this.simulStepDelay = value;
    }

    /**
     * Gets the value of the customTypesSupport property.
     * 
     * @return
     *     possible object is
     *     {@link TCustomTypesSupport }
     *     
     */
    @XmlElement(
    	namespace = cz.zcu.kiv.bp.namespaces.DataTypes.SCENARIO_URI,
    	name = "custom-types-support", required = false
    )
	public TCustomTypesSupport getCustomTypesSupport() {
		if (customTypesSupport == null) {
			customTypesSupport = new TCustomTypesSupport();
		}
		return customTypesSupport;
	}

    /**
     * Sets the value of the customTypesSupport property.
     * 
     * @param value
     *     allowed object is
     *     {@link TCustomTypesSupport }
     *     
     */
    public void setCustomTypesSupport(TCustomTypesSupport value) {
		this.customTypesSupport = value;
	}
}
