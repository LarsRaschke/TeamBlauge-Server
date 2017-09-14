//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 generiert 
// Siehe <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Änderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2017.09.14 um 10:56:25 AM CEST 
//


package xml.projects;

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
 *         &lt;element name="projectname" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="lastMod" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="taskEntries" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="taskname" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="statusname" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="lastMod" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="comment" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="color" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                   &lt;element name="lastUser" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="tagEntries" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;simpleContent>
 *                         &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *                         &lt;/extension>
 *                       &lt;/simpleContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *                 &lt;attribute name="ID" type="{http://www.w3.org/2001/XMLSchema}int" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="statusEntries" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                 &lt;/sequence>
 *                 &lt;attribute name="length" type="{http://www.w3.org/2001/XMLSchema}int" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *       &lt;attribute name="ID" type="{http://www.w3.org/2001/XMLSchema}int" />
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
    "lastMod",
    "description",
    "taskEntries",
    "statusEntries"
})
@XmlRootElement(name = "project")
public class Project {

    @XmlElement(required = true)
    protected String projectname;
    @XmlElement(required = true)
    protected String lastMod;
    @XmlElement(required = true)
    protected String description;
    protected List<Project.TaskEntries> taskEntries;
    protected List<Project.StatusEntries> statusEntries;
    @XmlAttribute(name = "ID")
    protected Integer id;

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
     * Gets the value of the taskEntries property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the taskEntries property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTaskEntries().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Project.TaskEntries }
     * 
     * 
     */
    public List<Project.TaskEntries> getTaskEntries() {
        if (taskEntries == null) {
            taskEntries = new ArrayList<Project.TaskEntries>();
        }
        return this.taskEntries;
    }

    /**
     * Gets the value of the statusEntries property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the statusEntries property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getStatusEntries().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Project.StatusEntries }
     * 
     * 
     */
    public List<Project.StatusEntries> getStatusEntries() {
        if (statusEntries == null) {
            statusEntries = new ArrayList<Project.StatusEntries>();
        }
        return this.statusEntries;
    }

    /**
     * Ruft den Wert der id-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getID() {
        return id;
    }

    /**
     * Legt den Wert der id-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setID(Integer value) {
        this.id = value;
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
     *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *       &lt;/sequence>
     *       &lt;attribute name="length" type="{http://www.w3.org/2001/XMLSchema}int" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "status"
    })
    public static class StatusEntries {

        @XmlElement(required = true)
        protected String status;
        @XmlAttribute(name = "length")
        protected Integer length;

        /**
         * Ruft den Wert der status-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getStatus() {
            return status;
        }

        /**
         * Legt den Wert der status-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setStatus(String value) {
            this.status = value;
        }

        /**
         * Ruft den Wert der length-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link Integer }
         *     
         */
        public Integer getLength() {
            return length;
        }

        /**
         * Legt den Wert der length-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link Integer }
         *     
         */
        public void setLength(Integer value) {
            this.length = value;
        }

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
     *         &lt;element name="taskname" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="statusname" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="lastMod" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="comment" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="color" type="{http://www.w3.org/2001/XMLSchema}int"/>
     *         &lt;element name="lastUser" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="tagEntries" maxOccurs="unbounded" minOccurs="0">
     *           &lt;complexType>
     *             &lt;simpleContent>
     *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
     *               &lt;/extension>
     *             &lt;/simpleContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *       &lt;/sequence>
     *       &lt;attribute name="ID" type="{http://www.w3.org/2001/XMLSchema}int" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "taskname",
        "statusname",
        "lastMod",
        "comment",
        "color",
        "lastUser",
        "tagEntries"
    })
    public static class TaskEntries {

        @XmlElement(required = true)
        protected String taskname;
        @XmlElement(required = true)
        protected String statusname;
        @XmlElement(required = true)
        protected String lastMod;
        @XmlElement(required = true)
        protected String comment;
        protected int color;
        @XmlElement(required = true)
        protected String lastUser;
        protected List<Project.TaskEntries.TagEntries> tagEntries;
        @XmlAttribute(name = "ID")
        protected Integer id;

        /**
         * Ruft den Wert der taskname-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getTaskname() {
            return taskname;
        }

        /**
         * Legt den Wert der taskname-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setTaskname(String value) {
            this.taskname = value;
        }

        /**
         * Ruft den Wert der statusname-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getStatusname() {
            return statusname;
        }

        /**
         * Legt den Wert der statusname-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setStatusname(String value) {
            this.statusname = value;
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
         * Ruft den Wert der comment-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getComment() {
            return comment;
        }

        /**
         * Legt den Wert der comment-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setComment(String value) {
            this.comment = value;
        }

        /**
         * Ruft den Wert der color-Eigenschaft ab.
         * 
         */
        public int getColor() {
            return color;
        }

        /**
         * Legt den Wert der color-Eigenschaft fest.
         * 
         */
        public void setColor(int value) {
            this.color = value;
        }

        /**
         * Ruft den Wert der lastUser-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getLastUser() {
            return lastUser;
        }

        /**
         * Legt den Wert der lastUser-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setLastUser(String value) {
            this.lastUser = value;
        }

        /**
         * Gets the value of the tagEntries property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the tagEntries property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getTagEntries().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Project.TaskEntries.TagEntries }
         * 
         * 
         */
        public List<Project.TaskEntries.TagEntries> getTagEntries() {
            if (tagEntries == null) {
                tagEntries = new ArrayList<Project.TaskEntries.TagEntries>();
            }
            return this.tagEntries;
        }

        /**
         * Ruft den Wert der id-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link Integer }
         *     
         */
        public Integer getID() {
            return id;
        }

        /**
         * Legt den Wert der id-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link Integer }
         *     
         */
        public void setID(Integer value) {
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
        public static class TagEntries {

            @XmlValue
            protected String value;

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

        }

    }

}
