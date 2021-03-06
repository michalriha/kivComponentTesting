package cz.zcu.kiv.bp.unimocker.bindings;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TBundleList complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TBundleList">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="bundle" type="{http://www.kiv.zcu.cz/component-testing/mocker}TBundle" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TBundleList", propOrder = {
    "bundles"
})
public class TBundleList {

    @XmlElement(name = "bundle")
    protected List<TBundle> bundles;

    /**
     * Gets the value of the bundles property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the bundles property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBundles().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TBundle }
     * 
     * 
     */
    public List<TBundle> getBundles() {
        if (bundles == null) {
            bundles = new ArrayList<TBundle>();
        }
        return this.bundles;
    }

}
