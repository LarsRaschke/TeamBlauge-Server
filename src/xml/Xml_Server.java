package xml;

import java.io.FileInputStream;
//import org.junit.Assert; 
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.io.*;
import java.text.DecimalFormat;
import java.util.*;
//import org.junit.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.*;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.stream.StreamSource;
import javax.xml.bind.annotation.XmlElement;
import org.xml.sax.SAXException;

import xml.Backup.Project;
import xml.Backup.Projectlist;
import xml.Backup.Project.Statuslist;
import xml.Backup.Project.Tasklist;
import xml.Backup.Project.Tasklist.Task;
import xml.Backup.Projectlist.ProjectOverview;
import xml.Backup.Projectlist.ProjectOverview.Userlist;
import xml.Backup.Projectlist.ProjectOverview.Userlist.User;

@XmlAccessorType(XmlAccessType.FIELD)
public class Xml_Server {
	
	
	   
     
   //f�gt neues Projekt an die Projektlist und erzeugt neue xml Datei
	public static void addtoprojectList(Project pr, String comment) throws JAXBException, DatatypeConfigurationException
	{
	
		  /*  try {
		        JAXBContext jc = JAXBContext.newInstance(Project.class);
		        javax.xml.bind.Marshaller marshaller = jc.createMarshaller();
		        marshaller.setProperty(marshaller.JAXB_FORMATTED_OUTPUT,
		                Boolean.TRUE);
		        File xmlfile = new File(pr.getProjectname()+".xml");
		        marshaller.marshal(pr, xmlfile);
		    } catch (JAXBException e) {
		        e.printStackTrace();
		    }
		    */
		    
		   // marshalToFile(pr, pr.getProjectname());
		    

			Projectlist data = unmarshalFromFile("src/xml/files/" + "projectlist.xml");
			
			ProjectOverview over = new ProjectOverview(pr.getProjectname(), comment, null);
			
			data.addProjectOverview(over);
			
			marshalToFileProjectList(data, "src/xml/files/" + "projectlist.xml");
			
			

		
	}
  // in Projectlist suchen
	/*public static ProjectOverview searchinxml(String name) throws JAXBException, XMLStreamException
	{
		XMLInputFactory xif = XMLInputFactory.newFactory();
        StreamSource xml = new StreamSource("src/xml/files/" + "projectlist.xml");
        XMLStreamReader xsr = xif.createXMLStreamReader(xml);

        
        while(xsr.hasNext()) {
            if(xsr.isStartElement() && name.equals(xsr.getLocalName()))
            {
                break;
            }
            xsr.next();
         }

        // Unmarshal from the xmlStreamReader that has been advanced
        JAXBContext jc = JAXBContext.newInstance(Projectlist.class);
        javax.xml.bind.Unmarshaller unmarshaller = jc.createUnmarshaller();
        Projectlist data = unmarshaller.unmarshal(xsr, Projectlist.class).getValue();
        ProjectOverview pro = new ProjectOverview("Testprojekt", "Das ist ein Testprojekt", "blabliblubkey");
        Iterator<ProjectOverview> iterator = data.getProjectOverview().iterator();
        while (iterator.hasNext()) {
		    if (name.equals(iterator.next().getName())) {
		         pro =  iterator.next();
		    }
		}
        
		
		return pro;
		
	}*/
	
	
	// Projectlist aus xml erzeugen
	private static Projectlist unmarshalFromFile(String fileName) throws JAXBException {
	    JAXBContext jaxbContext = JAXBContext.newInstance(Projectlist.class);
	    javax.xml.bind.Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
	    return (Projectlist) jaxbUnmarshaller.unmarshal(new File(fileName));
	}
	// 
	private static Project unmarshalFromFileProject(String fileName) throws JAXBException {
	    JAXBContext jaxbContext = JAXBContext.newInstance(Project.class);
	    javax.xml.bind.Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
	    return (Project) jaxbUnmarshaller.unmarshal(new File(fileName));
	}
	
	private static void marshalToFile(Project data, String fileName) throws JAXBException
	{
		
	    JAXBContext jaxbContext = JAXBContext.newInstance(Project.class);
	    Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

	    jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
	    jaxbMarshaller.marshal(data, new File(fileName));
	}
	
	private static void marshalToFileProjectList(Projectlist data, String fileName) throws JAXBException
	{
	    JAXBContext jaxbContext = JAXBContext.newInstance(Projectlist.class);
	    Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

	    jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
	    jaxbMarshaller.marshal(data, new File(fileName));
	}
	
	
	private static void deleteEntry(String deleteword) throws JAXBException
	{
		Projectlist data = unmarshalFromFile("src/xml/files/" + "projectlist.xml");
		
		Iterator<ProjectOverview> iterator = data.getProjectOverview().iterator();
		while (iterator.hasNext()) {
		    if (deleteword.equals(iterator.next().getProjectname())) {
		         iterator.remove();
		    }
		}
		
		marshalToFileProjectList(data, "src/xml/files/" + "projectlist.xml");
		
	}
	
	
	/*public static void changeEntryinxml(XMLGregorianCalendar time, String name) throws JAXBException, XMLStreamException
	{
		ProjectOverview pro = searchinxml(name);
		
		pro.setLastmod(time);
		String delete = pro.getName();
		
		Projectlist data = unmarshalFromFile("src/xml/files/" + "projectlist.xml");
		
		Iterator<ProjectOverview> iterator = data.getProjectOverview().iterator();
		while (iterator.hasNext()) {
		    if (delete.equals(iterator.next().getName())) {
		         iterator.remove();
		    }
		}
		data.getProjectOverview().add(pro);
		
		marshalToFileProjectList(data, "src/xml/files/" + "projectlist.xml");
		
	}*/
	
	public static List<Project> checkProjectListandgiveProjectsback(String userName) throws JAXBException, DatatypeConfigurationException
	{
		ArrayList<Project> proList = new ArrayList<Project>();
		Projectlist data = unmarshalFromFile("src/xml/files/" + "projectlist.xml");
		
		ProjectOverview pOver = new ProjectOverview("Testprojekt", "Das ist ein Testprojekt", "blabliblubkey");
		Iterator<ProjectOverview> iterator = data.getProjectOverview().iterator();
		while (iterator.hasNext()) 
		{
			
			Userlist userlist = iterator.next().getUserlist();
			//User us = new User();
			
			Iterator<User> ite = (Iterator<User>) userlist.getUser();
			
			while(ite.hasNext())
			{
				if(userName.equals(ite.next().getValue()))
				{
					Project pro = unmarshalFromFileProject("src/xml/files/" + iterator.next().getProjectname().toString() + ".xml");
					proList.add(pro);
					
		
				}
			}

		}
		
		
		
		return proList;
	}


	
	
	public static void main(String[] args) throws Exception 
	{
		Task task1 = new Task("dumm gucken", "todo", "dumme sau", 0, 1223);
		Task task2 = new Task("dumm gucken2", "todo", "dumme sau", 0, 1323);
		Task task3 = new Task("dumm gucken3", "todo", "dumme sau", 0, 1423);
		
		List<Project.Tasklist.Task> task = new ArrayList<Project.Tasklist.Task>();

		List<String> status = new ArrayList<String>();
		
		status.add("todo");
		status.add("done");
		status.add("fin");
		
		task.add(task1);
		task.add(task2);
		task.add(task3);
		Statuslist statuslist = new Statuslist(status, status.size());
		Tasklist tasklist = new Tasklist(task, task.size());
				
		Project pro = new Project("arschlochmodus 1", "Sauron", tasklist,  statuslist, 12345);
		
		addtoprojectList(pro, "gib gas");
		
		/*
		
		User us = new User();
		us.setIsAdmin(false);
		us.setValue("Peter");
		
		
	    ProjectOverview pr = new ProjectOverview("Testprojekt", "Das ist ein Testprojekt", "blabliblubkey");
	    pr.setProjectname("kanban1");
	    
	    pr.setDescription("kauabanaga");
	    pr.setLastmod(null);
	    Userlist userlist = new Userlist();
	    
	    userlist = pr.getUserlist();
	    userlist.getUser().add(us);
	    */
	}
	 
	
	

}
