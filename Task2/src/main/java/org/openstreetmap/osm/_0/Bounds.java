//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.1-b171012.0423 
//         See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
//         Any modifications to this file will be lost upon recompilation of the source schema. 
//         Generated on: 2020.04.24 at 09:08:03 PM NOVT 
//


package org.openstreetmap.osm._0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected         content contained within this class.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;attribute name="minlat" type="{http://www.w3.org/2001/XMLSchema}double" /&gt;
 *       &lt;attribute name="minlon" type="{http://www.w3.org/2001/XMLSchema}double" /&gt;
 *       &lt;attribute name="maxlat" type="{http://www.w3.org/2001/XMLSchema}double" /&gt;
 *       &lt;attribute name="maxlon" type="{http://www.w3.org/2001/XMLSchema}double" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "bounds")
public class Bounds {

    @XmlAttribute(name = "minlat")
    protected Double minlat;
    @XmlAttribute(name = "minlon")
    protected Double minlon;
    @XmlAttribute(name = "maxlat")
    protected Double maxlat;
    @XmlAttribute(name = "maxlon")
    protected Double maxlon;

    /**
     * Gets the value of the minlat property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getMinlat() {
        return minlat;
    }

    /**
     * Sets the value of the minlat property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setMinlat(Double value) {
        this.minlat = value;
    }

    /**
     * Gets the value of the minlon property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getMinlon() {
        return minlon;
    }

    /**
     * Sets the value of the minlon property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setMinlon(Double value) {
        this.minlon = value;
    }

    /**
     * Gets the value of the maxlat property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getMaxlat() {
        return maxlat;
    }

    /**
     * Sets the value of the maxlat property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setMaxlat(Double value) {
        this.maxlat = value;
    }

    /**
     * Gets the value of the maxlon property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getMaxlon() {
        return maxlon;
    }

    /**
     * Sets the value of the maxlon property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setMaxlon(Double value) {
        this.maxlon = value;
    }

}
