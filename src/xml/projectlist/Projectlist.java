//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 generiert 
// Siehe <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Änderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2017.09.08 um 10:54:53 AM CEST 
//


package xml.projectlist;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;


/**
 * <p>Java-Klasse für anonymous complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="projectOverviewEntries" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="projectname" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="creator" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="createdOn" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="lastMod" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="userEntries" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;simpleContent>
 *                         &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *                           &lt;attribute name="Admin" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *                         &lt;/extension>
 *                       &lt;/simpleContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *                 &lt;attribute name="ID" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
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
    "projectOverviewEntries"
})
@XmlRootElement(name = "projectlist")
public class Projectlist {

    protected List<Projectlist.ProjectOverviewEntries> projectOverviewEntries;

    /**
     * Gets the value of the projectOverviewEntries property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the projectOverviewEntries property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getProjectOverviewEntries().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Projectlist.ProjectOverviewEntries }
     * 
     * 
     */
    public List<Projectlist.ProjectOverviewEntries> getProjectOverviewEntries() {
        if (projectOverviewEntries == null) {
            projectOverviewEntries = new ArrayList<Projectlist.ProjectOverviewEntries>();
        }
        return this.projectOverviewEntries;
    }


    /**
     * <p>Java-Klasse für anonymous complex type.
     * 
     * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="projectname" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="creator" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="createdOn" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="lastMod" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="userEntries" maxOccurs="unbounded" minOccurs="0">
     *           &lt;complexType>
     *             &lt;simpleContent>
     *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
     *                 &lt;attribute name="Admin" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
     *               &lt;/extension>
     *             &lt;/simpleContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *       &lt;/sequence>
     *       &lt;attribute name="ID" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "projectname",
        "creator",
        "createdOn",
        "lastMod",
        "description",
        "userEntries"
    })
    public static class ProjectOverviewEntries {

        @XmlElement(required = true)
        protected String projectname;
        @XmlElement(required = true)
        protected String creator;
        @XmlElement(required = true)
        protected String createdOn;
        @XmlElement(required = true)
        protected String lastMod;
        @XmlElement(required = true)
        protected String description;
        protected List<Projectlist.ProjectOverviewEntries.UserEntries> userEntries;
        @XmlAttribute(name = "ID", required = true)
        protected int id;

        /**
         * Ruft den Wert der projectname-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getProjectname() {
            return projectname;
        }

        /**
         * Legt den Wert der projectname-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setProjectname(String value) {
            this.projectname = value;
        }

        /**
         * Ruft den Wert der creator-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCreator() {
            return creator;
        }

        /**
         * Legt den Wert der creator-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCreator(String value) {
            this.creator = value;
        }

        /**
         * Ruft den Wert der createdOn-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCreatedOn() {
            return createdOn;
        }

        /**
         * Legt den Wert der createdOn-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCreatedOn(String value) {
            this.createdOn = value;
        }

        /**
         * Ruft den Wert der lastMod-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getLastMod() {
            return lastMod;
        }

        /**
         * Legt den Wert der lastMod-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setLastMod(String value) {
            this.lastMod = value;
        }

        /**
         * Ruft den Wert der description-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDescription() {
            return description;
        }

        /**
         * Legt den Wert der description-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDescription(String value) {
            this.description = value;
        }

        /**
         * Gets the value of the userEntries property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the userEntries property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getUserEntries().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Projectlist.ProjectOverviewEntries.UserEntries }
         * 
         * 
         */
        public List<Projectlist.ProjectOverviewEntries.UserEntries> getUserEntries() {
            if (userEntries == null) {
                userEntries = new ArrayList<Projectlist.ProjectOverviewEntries.UserEntries>();
            }
            return this.userEntries;
        }

        /**
         * Ruft den Wert der id-Eigenschaft ab.
         * 
         */
        public int getID() {
            return id;
        }

        /**
         * Legt den Wert der id-Eigenschaft fest.
         * 
         */
        public void setID(int value) {
            this.id = value;
        }


        /**
         * <p>Java-Klasse für anonymous complex type.
         * 
         * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;simpleContent>
         *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
         *       &lt;attribute name="Admin" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
         *     &lt;/extension>
         *   &lt;/simpleContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "value"
        })
        public static class UserEntries {

            @XmlValue
            protected String value;
            @XmlAttribute(name = "Admin", required = true)
            protected boolean admin;

            /**
             * Ruft den Wert der value-Eigenschaft ab.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getValue() {
                return value;
            }

            /**
             * Legt den Wert der value-Eigenschaft fest.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setValue(String value) {
                this.value = value;
            }

            /**
             * Ruft den Wert der admin-Eigenschaft ab.
             * 
             */
            public boolean isAdmin() {
                return admin;
            }

            /**
             * Legt den Wert der admin-Eigenschaft fest.
             * 
             */
            public void setAdmin(boolean value) {
                this.admin = value;
            }

        }

    }

}
