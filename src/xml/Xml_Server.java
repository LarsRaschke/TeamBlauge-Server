package xml;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.*;
import java.util.*;


import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.*;
import javax.xml.datatype.DatatypeConfigurationException;
import org.xml.sax.SAXException;

import xml.projects.Project;
import xml.projectlist.Projectlist;
import xml.projectlist.Projectlist.ProjectOverviewEntries;
import xml.projectlist.Projectlist.ProjectOverviewEntries.UserEntries;
import xml.XsdValidation;


/*
 * Verwaltung der XML Methoden
 * Tingar und Wohlrab
 * 
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Xml_Server {
	
	/**
	 *  fügt neues Projekt an die Projektlist und erzeugt neue xml Datei
	 * @param project
	 * @param projectOverviewEntries
	 * @throws JAXBException
	 * @throws DatatypeConfigurationException
	 * @throws FileNotFoundException
	 * @throws SAXException
	 * @throws IOException
	 */
	public static void saveProject(Project project, ProjectOverviewEntries projectOverviewEntries)
			throws JAXBException, DatatypeConfigurationException, FileNotFoundException, SAXException, IOException {
		marshalToProjectFile(project, project.getID(), project.getProjectname());
		XsdValidation.validateProjects(project.getID(), project.getProjectname());

		deleteEntryInProjectlist(projectOverviewEntries.getID());
		Projectlist xmlProjectlist = unmarshalFromProjectlistFile();
		xmlProjectlist.getProjectOverviewEntries().add(projectOverviewEntries);
		marshalToProjectlistFile(xmlProjectlist);
	}
	
	/**
	 * Löscht ein Projekt
	 * @param project 
	 * @throws JAXBException
	 */
	public static void deleteProjectPermanently(int id, String projectname) throws JAXBException {
		File file = new File("src/xml/files/" + id + "_" + projectname + ".xml");

		// Zuvor alle mit dem File assoziierten Streams schließen...

		if (file.exists()) {
			file.delete();
		}

		deleteEntryInProjectlist(id);
	}

	/**
	 *  Projectlist aus xml erzeugen
	 * @return
	 * @throws JAXBException
	 */
	public static Projectlist unmarshalFromProjectlistFile() throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(Projectlist.class);
		javax.xml.bind.Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		return (Projectlist) jaxbUnmarshaller.unmarshal(new File("src/xml/files/_projectlist.xml"));
	}

	/**
	 * Project aus XML erzeugen
	 * @param id
	 * @param fileName
	 * @return
	 * @throws JAXBException
	 */
	public static Project unmarshalFromProjectFile(int id, String fileName) throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(Project.class);
		javax.xml.bind.Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		return (Project) jaxbUnmarshaller.unmarshal(new File("src/xml/files/" + id + "_" + fileName + ".xml"));
	}
	/**
	 * Project in XML speichern
	 * @param data Project
	 * @param id  Projekt ID
	 * @param fileName Projektname
	 * @throws JAXBException
	 */
	public static void marshalToProjectFile(Project data, int id, String fileName) throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(Project.class);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		jaxbMarshaller.marshal(data, new File("src/xml/files/" + id + "_" + fileName + ".xml"));
	}
	/**
	 * Projectlist in XML speichern
	 * @param data
	 * @throws JAXBException
	 */
	public static void marshalToProjectlistFile(Projectlist data) throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(Projectlist.class);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		jaxbMarshaller.marshal(data, new File("src/xml/files/_projectlist.xml"));
	}
	/**
	 * Löscht eintrag in Projectliste
	 * @param id
	 * @throws JAXBException
	 */
	private static void deleteEntryInProjectlist(int id) throws JAXBException {
		Projectlist xmlProjectlist = unmarshalFromProjectlistFile();

		Iterator<ProjectOverviewEntries> iterator = xmlProjectlist.getProjectOverviewEntries().iterator();
		while (iterator.hasNext()) {
			if (id == iterator.next().getID()) {
				iterator.remove();
			}
		}

		marshalToProjectlistFile(xmlProjectlist);
	}

	/**
	 * Erhält Username und gibt eine Hashmap von Project und ProjectOverview wieder
	 * @param userName
	 * @return
	 * @throws JAXBException
	 * @throws DatatypeConfigurationException
	 */
	public static HashMap<Project, ProjectOverviewEntries> getProjectsByUser(String userName)
			throws JAXBException, DatatypeConfigurationException {
		HashMap<Project, ProjectOverviewEntries> wlist = new HashMap<Project, ProjectOverviewEntries>();
		Projectlist projectList = unmarshalFromProjectlistFile();

		Iterator<ProjectOverviewEntries> projectIterator = projectList.getProjectOverviewEntries().iterator();
		while (projectIterator.hasNext()) {

			Iterator<UserEntries> userIterator = projectIterator.next().getUserEntries().iterator();

			while (userIterator.hasNext()) {
				if (userName.equals(userIterator.next().getValue())) {
					Project projectMatch = unmarshalFromProjectFile(projectIterator.next().getID(),
							projectIterator.next().getProjectname());
					wlist.put(projectMatch, projectIterator.next());

				}
			}

		}

		return wlist;
	}
	/**
	 * Erhält eine ID und gibt ein Projekt wieder
	 * @param id ID vom Projekt
	 * @return
	 * @throws JAXBException
	 * @throws DatatypeConfigurationException
	 */
	public static HashMap<Project, ProjectOverviewEntries> getProjectByID(int id)
			throws JAXBException, DatatypeConfigurationException {
		HashMap<Project, ProjectOverviewEntries> wlist = new HashMap<Project, ProjectOverviewEntries>();
		Projectlist projectList = unmarshalFromProjectlistFile();

		Iterator<ProjectOverviewEntries> projectIterator = projectList.getProjectOverviewEntries().iterator();
		while (projectIterator.hasNext()) {
			if (projectIterator.next().getID() == id) {
				Project projectMatch = unmarshalFromProjectFile(projectIterator.next().getID(),
						projectIterator.next().getProjectname());
				wlist.put(projectMatch, projectIterator.next());
			}

		}

		return wlist;
	}
}
