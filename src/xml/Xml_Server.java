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

import xml.projects.Project;
import xml.projectlist.Projectlist;
import xml.projects.Project.Statuslist;
import xml.projects.Project.Tasklist;
import xml.projects.Project.Tasklist.Task;
import xml.projectlist.Projectlist.ProjectOverview;
import xml.projectlist.Projectlist.ProjectOverview.Userlist;
import xml.projectlist.Projectlist.ProjectOverview.Userlist.User;
import xml.projectlist.ObjectFactory;
import xml.projects.ObjectFactoryProject;

@XmlAccessorType(XmlAccessType.FIELD)
public class Xml_Server {
	
	
	   
     
   //fügt neues Projekt an die Projektlist und erzeugt neue xml Datei
	public static void addtoprojectList(Project pr, String comment) throws JAXBException, DatatypeConfigurationException
	{
	
		    
		    marshalToFile(pr, pr.getProjectname());
		    

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
		
		//ProjectOverview pOver = new ProjectOverview("Testprojekt", "Das ist ein Testprojekt", "blabliblubkey");
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
		
		ObjectFactoryProject facPro = new ObjectFactoryProject();
		Task task1 = facPro.createProjectTasklistTask();
		task1.setTaskname("dumme sau");
		task1.setStatusname("todo");
		task1.setColor(0);
		task1.setComment("dumme sau sau");
		task1.setLastmod(null);
		task1.setID(2);
		
		Tasklist tList = facPro.createProjectTasklist();
		tList.getTask().add(task1);
		
        
		
		Statuslist sList = facPro.createProjectStatuslist();
		
		sList.getStatus().add("todo");
		
		Project pro = facPro.createProject();
		
		pro.setTasklist(tList);
		pro.setStatuslist(sList);
		pro.setCreatedOn(null);
		pro.setCreator("hans");
		pro.setID(007);
		pro.setLastmod(null);
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
