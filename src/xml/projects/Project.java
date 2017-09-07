//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 generiert 
// Siehe <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Änderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2017.09.07 um 11:57:39 AM CEST 
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
 *         &lt;element name="created_on" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="lastmod" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="tasklist">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence maxOccurs="unbounded" minOccurs="0">
 *                   &lt;element name="task">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="taskname" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="statusname" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="lastmod" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="comment" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="color" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                           &lt;/sequence>
 *                           &lt;attribute name="ID" type="{http://www.w3.org/2001/XMLSchema}int" />
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *                 &lt;attribute name="count" type="{http://www.w3.org/2001/XMLSchema}int" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="statuslist">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
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
    "creator",
    "createdOn",
    "lastmod",
    "tasklist",
    "statuslist"
})
@XmlRootElement(name = "project")
public class Project {

    @XmlElement(required = true)
    protected String projectname;
    @XmlElement(required = true)
    protected String creator;
    @XmlElement(name = "created_on", required = true)
    protected String createdOn;
    @XmlElement(required = true)
    protected String lastmod;
    @XmlElement(required = true)
    protected Project.Tasklist tasklist;
    @XmlElement(required = true)
    protected Project.Statuslist statuslist;
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
     * Ruft den Wert der lastmod-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLastmod() {
        return lastmod;
    }

    /**
     * Legt den Wert der lastmod-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLastmod(String value) {
        this.lastmod = value;
    }

    /**
     * Ruft den Wert der tasklist-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Project.Tasklist }
     *     
     */
    public Project.Tasklist getTasklist() {
        return tasklist;
    }

    /**
     * Legt den Wert der tasklist-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Project.Tasklist }
     *     
     */
    public void setTasklist(Project.Tasklist value) {
        this.tasklist = value;
    }

    /**
     * Ruft den Wert der statuslist-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Project.Statuslist }
     *     
     */
    public Project.Statuslist getStatuslist() {
        return statuslist;
    }

    /**
     * Legt den Wert der statuslist-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Project.Statuslist }
     *     
     */
    public void setStatuslist(Project.Statuslist value) {
        this.statuslist = value;
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
     *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
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
    public static class Statuslist {

        protected List<String> status;
        @XmlAttribute(name = "length")
        protected Integer length;

        /**
         * Gets the value of the status property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the status property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getStatus().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link String }
         * 
         * 
         */
        public List<String> getStatus() {
            if (status == null) {
                status = new ArrayList<String>();
            }
            return this.status;
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
     *       &lt;sequence maxOccurs="unbounded" minOccurs="0">
     *         &lt;element name="task">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="taskname" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="statusname" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="lastmod" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="comment" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="color" type="{http://www.w3.org/2001/XMLSchema}int"/>
     *                 &lt;/sequence>
     *                 &lt;attribute name="ID" type="{http://www.w3.org/2001/XMLSchema}int" />
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *       &lt;/sequence>
     *       &lt;attribute name="count" type="{http://www.w3.org/2001/XMLSchema}int" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "task"
    })
    public static class Tasklist {

        protected List<Project.Tasklist.Task> task;
        @XmlAttribute(name = "count")
        protected Integer count;

        /**
         * Gets the value of the task property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the task property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getTask().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Project.Tasklist.Task }
         * 
         * 
         */
        public List<Project.Tasklist.Task> getTask() {
            if (task == null) {
                task = new ArrayList<Project.Tasklist.Task>();
            }
            return this.task;
        }

        /**
         * Ruft den Wert der count-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link Integer }
         *     
         */
        public Integer getCount() {
            return count;
        }

        /**
         * Legt den Wert der count-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link Integer }
         *     
         */
        public void setCount(Integer value) {
            this.count = value;
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
         *         &lt;element name="lastmod" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="comment" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="color" type="{http://www.w3.org/2001/XMLSchema}int"/>
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
            "lastmod",
            "comment",
            "color"
        })
        public static class Task {

            @XmlElement(required = true)
            protected String taskname;
            @XmlElement(required = true)
            protected String statusname;
            @XmlElement(required = true)
            protected String lastmod;
            @XmlElement(required = true)
            protected String comment;
            protected int color;
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
             * Ruft den Wert der lastmod-Eigenschaft ab.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getLastmod() {
                return lastmod;
            }

            /**
             * Legt den Wert der lastmod-Eigenschaft fest.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setLastmod(String value) {
                this.lastmod = value;
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

        }

    }

}
