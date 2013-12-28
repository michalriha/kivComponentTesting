package cz.zcu.kiv.bp.unimocker.bindings;

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
 *		   &lt;element ref="{http://www.kiv.zcu.cz/component-testing/types}custom-types-support" />
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TSettings", propOrder = {
    "customTypesSupport"
})
public class TSettings
{
    @XmlElement(namespace = cz.zcu.kiv.bp.namespaces.DataTypes.SCENARIO_URI, name = "custom-types-support", required = false)
    protected TCustomTypesSupport customTypesSupport;

	public TCustomTypesSupport getCustomTypesSupport()
	{
		return customTypesSupport;
	}

	public void setCustomTypesSupport(TCustomTypesSupport customTypesSupport)
	{
		this.customTypesSupport = customTypesSupport;
	}
}