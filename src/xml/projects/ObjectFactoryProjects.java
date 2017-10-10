//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 generiert 
// Siehe <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Änderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2017.10.10 um 12:58:17 PM CEST 
//


package xml.projects;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the xml.projects package. 
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
public class ObjectFactoryProjects {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: xml.projects
     * 
     */
    public ObjectFactoryProjects() {
    }

    /**
     * Create an instance of {@link Project }
     * 
     */
    public Project createProject() {
        return new Project();
    }

    /**
     * Create an instance of {@link Project.TaskEntries }
     * 
     */
    public Project.TaskEntries createProjectTaskEntries() {
        return new Project.TaskEntries();
    }

    /**
     * Create an instance of {@link Project.StatusEntries }
     * 
     */
    public Project.StatusEntries createProjectStatusEntries() {
        return new Project.StatusEntries();
    }

    /**
     * Create an instance of {@link Project.TaskEntries.TagEntries }
     * 
     */
    public Project.TaskEntries.TagEntries createProjectTaskEntriesTagEntries() {
        return new Project.TaskEntries.TagEntries();
    }

    /**
     * Create an instance of {@link Project.TaskEntries.CommentEntires }
     * 
     */
    public Project.TaskEntries.CommentEntires createProjectTaskEntriesCommentEntires() {
        return new Project.TaskEntries.CommentEntires();
    }

}
