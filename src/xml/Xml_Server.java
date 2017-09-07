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
import java.time.ZoneId;
import java.time.ZonedDateTime;
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

import xml.projects.Project;
import xml.projectlist.Projectlist;
import xml.projects.Project.Statuslist;
import xml.projects.Project.Tasklist;
import xml.projects.Project.Tasklist.Task;
import xml.projectlist.Projectlist.ProjectOverview;
import xml.projectlist.Projectlist.ProjectOverview.Userlist;
import xml.projectlist.Projectlist.ProjectOverview.Userlist.User;
import xml.projectlist.ObjectFactory;
import xml.projects.ObjectFactoryProjects;
import xml.XsdValidation;

@XmlAccessorType(XmlAccessType.FIELD)
public class Xml_Server {
	
	
	   
     
   //fügt neues Projekt an die Projektlist und erzeugt neue xml Datei
	public static void addtoprojectList(Project pr, String comment) throws JAXBException, DatatypeConfigurationException, FileNotFoundException, SAXException, IOException
	{
	
		    
		    marshalToFile(pr, "src/xml/files/" + pr.getProjectname());
		   
		    
		    XsdValidation.validateProjects(pr.getProjectname());
		    
		    

			Projectlist data = unmarshalFromFile("src/xml/files/" + "projectlist.xml");
			
			ObjectFactory obFacProjectList = new ObjectFactory();
			
			User use = obFacProjectList.createProjectlistProjectOverviewUserlistUser();
			
			use.setAdmin(false);
			use.setValue("Hans");
			
			Userlist uList = obFacProjectList.createProjectlistProjectOverviewUserlist();
			uList.getUser().add(use);
			

			ProjectOverview proo = obFacProjectList.createProjectlistProjectOverview();
			proo.setCreatedOn(null);
			proo.setDescription(comment);
			proo.setID(007);
			proo.setKey(null);
			proo.setLastmod(null);
			proo.setProjectname("JOJOJO");
			proo.setUserlist(uList);
			
			data.getProjectOverview().add(proo);

			
			marshalToFileProjectList(data, "src/xml/files/" + "projectlist.xml");
			
			

		
	}
  // in Projectlist suchen
	public static List<Project> searchinxml(String name) throws JAXBException, XMLStreamException
	{
		ObjectFactory obFacProjectList = new ObjectFactory();
		Projectlist data = unmarshalFromFile("src/xml/files/" + "projectlist.xml");
		List<Project> proList = new ArrayList<Project>();
		ProjectOverview pro = obFacProjectList.createProjectlistProjectOverview();
		
		Iterator<ProjectOverview> iterator = data.getProjectOverview().iterator();
        while (iterator.hasNext()) {
		    if (name.equals(iterator.next().getProjectname())) {
		         pro =  iterator.next();
		         String projectname = pro.getProjectname();
		         Project project = unmarshalFromFileProject("src/xml/files/" + projectname+".xml");
		         proList.add(project);
		         
		    }
		}
		
		
        
        
		
		return proList;
		
		
	}
	
	
	
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
	
	// Durchsucht die ProjectList nach einem bestimmten Projecktnamen, dann das Projekt nach einem Bestimmten Task und ändert den Status und die lastmod
	public static void changeEntryinxml_Status(String name, String Status, String Taskname) throws JAXBException, XMLStreamException
	{
		ZonedDateTime now = ZonedDateTime.now(ZoneId.of("Europe/Paris"));
		List<Project> proList = searchinxml(name);
		Project pro = new Project();
		for(Project proIterator:proList)
		{
			Tasklist tlist = proIterator.getTasklist();
			
			for(Task tIterator:tlist.getTask())
			{
				if(tIterator.getTaskname().equals(Taskname))
				{
					tIterator.setStatusname(Status);
					tIterator.setLastmod(now.toString());
				}
			}
			
			marshalToFile(proIterator, proIterator.getProjectname()+".xml");
			
		}
		

		
	}
	
	public static List<Project> checkProjectListandgiveProjectsback(String userName) throws JAXBException, DatatypeConfigurationException
	{
		ArrayList<Project> proList = new ArrayList<Project>();
		Projectlist data = unmarshalFromFile("src/xml/files/" + "projectlist.xml");
		
		//ProjectOverview pOver = new ProjectOverview("Testprojekt", "Das ist ein Testprojekt", "blabliblubkey");
		Iterator<ProjectOverview> iterator = data.getProjectOverview().iterator();
		while (iterator.hasNext()) 
		{
			
			Userlist userlist = iterator.next().getUserlist();
			//User us = new User();
			
			@SuppressWarnings("unchecked")
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
		ZonedDateTime now = ZonedDateTime.now(ZoneId.of("Europe/Paris"));
		ObjectFactoryProjects facPro = new ObjectFactoryProjects();
		Task task1 = facPro.createProjectTasklistTask();
		task1.setTaskname("dumme sau");
		task1.setStatusname("todo");
		task1.setColor(0);
		task1.setComment("dumme sau sau");
		task1.setLastmod(now.toString());
		task1.setID(2);
		
		Tasklist tList = facPro.createProjectTasklist();
		tList.getTask().add(task1);
		
        
		
		Statuslist sList = facPro.createProjectStatuslist();
		
		sList.getStatus().add("todo");
		
		Project pro = facPro.createProject();
		
		pro.setTasklist(tList);
		pro.setStatuslist(sList);
		pro.setCreatedOn(now.toString());
		pro.setCreator("hans");
		pro.setID(007);
		pro.setLastmod(now.toString());
		pro.setProjectname("gogogirl");
		
		
		
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
