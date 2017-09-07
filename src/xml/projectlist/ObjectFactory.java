//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 generiert 
// Siehe <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Änderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2017.09.07 um 11:57:21 AM CEST 
//


package xml.projectlist;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the xml.projectlist package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: xml.projectlist
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Projectlist }
     * 
     */
    public Projectlist createProjectlist() {
        return new Projectlist();
    }

    /**
     * Create an instance of {@link Projectlist.ProjectOverview }
     * 
     */
    public Projectlist.ProjectOverview createProjectlistProjectOverview() {
        return new Projectlist.ProjectOverview();
    }

    /**
     * Create an instance of {@link Projectlist.ProjectOverview.Userlist }
     * 
     */
    public Projectlist.ProjectOverview.Userlist createProjectlistProjectOverviewUserlist() {
        return new Projectlist.ProjectOverview.Userlist();
    }

    /**
     * Create an instance of {@link Projectlist.ProjectOverview.Userlist.User }
     * 
     */
    public Projectlist.ProjectOverview.Userlist.User createProjectlistProjectOverviewUserlistUser() {
        return new Projectlist.ProjectOverview.Userlist.User();
    }

}
