//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.06.01 at 06:58:03 PM CEST 
//


package generated;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


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
 *         &lt;element ref="{}offer_description"/>
 *         &lt;element ref="{}travelAgency"/>
 *       &lt;/sequence>
 *       &lt;attribute ref="{}offer_id use="required""/>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "offerDescription",
    "travelAgency"
})
@XmlRootElement(name = "offer")
public class Offer {

    @XmlElement(name = "offer_description", required = true)
    protected OfferDescription offerDescription;
    @XmlElement(required = true)
    protected TravelAgency travelAgency;
    @XmlAttribute(name = "offer_id", required = true)
    protected long offerId;

    /**
     * Gets the value of the offerDescription property.
     * 
     * @return
     *     possible object is
     *     {@link OfferDescription }
     *     
     */
    public OfferDescription getOfferDescription() {
        return offerDescription;
    }

    /**
     * Sets the value of the offerDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link OfferDescription }
     *     
     */
    public void setOfferDescription(OfferDescription value) {
        this.offerDescription = value;
    }

    /**
     * Gets the value of the travelAgency property.
     * 
     * @return
     *     possible object is
     *     {@link TravelAgency }
     *     
     */
    public TravelAgency getTravelAgency() {
        return travelAgency;
    }

    /**
     * Sets the value of the travelAgency property.
     * 
     * @param value
     *     allowed object is
     *     {@link TravelAgency }
     *     
     */
    public void setTravelAgency(TravelAgency value) {
        this.travelAgency = value;
    }

    /**
     * Gets the value of the offerId property.
     * 
     */
    public long getOfferId() {
        return offerId;
    }

    /**
     * Sets the value of the offerId property.
     * 
     */
    public void setOfferId(long value) {
        this.offerId = value;
    }

}
