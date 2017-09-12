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

import com.sun.jmx.snmp.tasks.TaskServer;

import model.Projekt;
import xml.projects.Project;
import xml.projectlist.Projectlist;
import xml.projects.Project.StatusEntries;
import xml.projects.Project.TaskEntries;
import xml.projectlist.Projectlist.ProjectOverviewEntries;
import xml.projectlist.Projectlist.ProjectOverviewEntries.UserEntries;
import xml.projectlist.ObjectFactory;
import xml.projects.ObjectFactoryProjects;
import xml.XsdValidation;

@XmlAccessorType(XmlAccessType.FIELD)
public class Xml_Server {
	// fügt neues Projekt an die Projektlist und erzeugt neue xml Datei
	public static void saveProject(Project project, ProjectOverviewEntries projectOverviewEntries) throws JAXBException, DatatypeConfigurationException, FileNotFoundException, SAXException, IOException
		{
			marshalToProjectFile(project, project.getID(), project.getProjectname());
			XsdValidation.validateProjects(project.getID(), project.getProjectname());
			
			deleteEntryInProjectlist(projectOverviewEntries.getID());
			Projectlist xmlProjectlist = unmarshalFromProjectlistFile();
			xmlProjectlist.getProjectOverviewEntries().add(projectOverviewEntries);
			marshalToProjectlistFile(xmlProjectlist);
		}
	
	public static void deleteProjectPermanently(Project project) throws JAXBException
	{
		File file = new File("src/xml/files/" + project.getID() + "_" + project.getProjectname() + ".xml");
	       
	        //Zuvor alle mit dem File assoziierten Streams schließen...
	       
	        if(file.exists()){
	            file.delete();
	        }
	        
		deleteEntryInProjectlist(project.getID());
	}

	// in Projectlist suchenp
//	public static List<Project> searchinxml(String name) throws JAXBException, XMLStreamException {
//		ObjectFactory obFacProjectList = new ObjectFactory();
//		Projectlist data = unmarshalFromProjectlistFile();
//		List<Project> proList = new ArrayList<Project>();
//		ProjectOverview pro = obFacProjectList.createProjectlistProjectOverviewEntries();
//
//		Iterator<ProjectOverview> iterator = data.getProjectOverviewEntries().iterator();
//		while (iterator.hasNext()) {
//			if (name.equals(iterator.next().getProjectname())) {
//				pro = iterator.next();
//
//				String projectname = pro.getProjectname();
//				Project project = unmarshalFromProjectFile(projectname);
//				proList.add(project);
//
//			}
//		}
//
//		return proList;
//
//	}

	// Projectlist aus xml erzeugen
	public static Projectlist unmarshalFromProjectlistFile() throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(Projectlist.class);
		javax.xml.bind.Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		return (Projectlist) jaxbUnmarshaller.unmarshal(new File("src/xml/files/_projectlist.xml"));
	}

	//
	public static Project unmarshalFromProjectFile(int id, String fileName) throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(Project.class);
		javax.xml.bind.Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		return (Project) jaxbUnmarshaller.unmarshal(new File("src/xml/files/" + id + "_" + fileName + ".xml"));
	}

	public static void marshalToProjectFile(Project data, int id, String fileName) throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(Project.class);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		jaxbMarshaller.marshal(data, new File("src/xml/files/" + id + "_" + fileName + ".xml"));
	}

	public static void marshalToProjectlistFile(Projectlist data) throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(Projectlist.class);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		jaxbMarshaller.marshal(data, new File("src/xml/files/_projectlist.xml"));
	}

	public static void deleteEntryInProjectlist(int id) throws JAXBException {
		Projectlist xmlProjectlist = unmarshalFromProjectlistFile();

		Iterator<ProjectOverviewEntries> iterator = xmlProjectlist.getProjectOverviewEntries().iterator();
		while (iterator.hasNext()) {
			if (id == iterator.next().getID()) {
				iterator.remove();
			}
		}

		marshalToProjectlistFile(xmlProjectlist);
	}

	// Durchsucht die ProjectList nach einem bestimmten Projecktnamen, dann das
	// Projekt nach einem Bestimmten Task und ändert den Status und die lastmod
//	public static void changeEntryinxml_Status(String name, String Status, String Taskname)
//			throws JAXBException, XMLStreamException {
//		List<Project> proList = searchinxml(name);
//		Project pro = new Project();
//		for (Project proIterator : proList) {
//			Tasklist tlist = proIterator.getTasklist();
//
//			for (Task tIterator : tlist.getTask()) {
//				if (tIterator.getTaskname().equals(Taskname)) {
//					ZonedDateTime now = ZonedDateTime.now(ZoneId.of("Europe/Paris"));
//					tIterator.setStatusname(Status);
//					tIterator.setLastmod(now.toString());
//				}
//			}
//
//			marshalToProjectFile(proIterator, proIterator.getProjectname() + ".xml");
//
//		}
//
//	}

	public static HashMap<Project, ProjectOverviewEntries> getProjectsByUser(String userName)
			throws JAXBException, DatatypeConfigurationException {
		HashMap<Project, ProjectOverviewEntries> wlist = new HashMap<Project, ProjectOverviewEntries>();
		ArrayList<Project> proList = new ArrayList<Project>();
		ArrayList<ProjectOverviewEntries> proOverList = new ArrayList<ProjectOverviewEntries>();
		Projectlist data = unmarshalFromProjectlistFile();

		Iterator<ProjectOverviewEntries> iterator = data.getProjectOverviewEntries().iterator();
		while (iterator.hasNext()) {
			
			List<UserEntries> userEntries = iterator.next().getUserEntries();

			@SuppressWarnings("unchecked")
			Iterator<UserEntries> ite = (Iterator<UserEntries>) userEntries;

			while (ite.hasNext()) {
				if (userName.equals(ite.next().getValue())) {
					Project projectMatch = unmarshalFromProjectFile(iterator.next().getID(), iterator.next().getProjectname());
					proList.add(projectMatch);
					usList.add(userEntries);
					wlist.put(projectMatch, userEntries);

				}
			}

		}

		return wlist;
	}

	public static void main(String[] args) throws Exception {

	}

}
