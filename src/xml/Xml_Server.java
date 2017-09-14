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
	 *  Fügt ein neues oder geändertes Projekt an die _projectlist.xml an und erzeugt neue spezifische id_projektname.xml Datei.
	 *  
	 * @param project - Das spezifische Projekt.
	 * @param projectOverviewEntries  - Die Overview des Projektes.
	 * @throws JAXBException
	 * @throws DatatypeConfigurationException
	 * @throws FileNotFoundException
	 * @throws SAXException
	 * @throws IOException
	 */
	public static void saveProject(Project project, ProjectOverviewEntries projectOverviewEntries)
			throws JAXBException, DatatypeConfigurationException, FileNotFoundException, SAXException, IOException {
		marshalToProjectFile(project);
		XsdValidation.validateProjects(project.getID(), project.getProjectname());

		deleteEntryInProjectlist(projectOverviewEntries.getID());
		Projectlist xmlProjectlist = unmarshalFromProjectlistFile();
		xmlProjectlist.getProjectOverviewEntries().add(projectOverviewEntries);
		marshalToProjectlistFile(xmlProjectlist);
	}
	
	/**
	 * Löscht den Eintrag des Projektes in der _projectlist.xml sowie seine spezifische XML-Dateie unwiderruflich.
	 * 
	 * @param id - Die einzigartige ID des Projektes, das gelöscht werden soll.
	 * @param projectname - Der Name des Projektes, das gelöscht werden soll.
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
	 * Projectlist aus _projectlist.xml laden und Objekt erzeugen. Sollte _projectlist.xml nicht existieren, wird eine leere Projectlist zurückgegeben.
	 * 
	 * @return Die Projectlist aus allen Projekten.
	 * @throws JAXBException
	 */
	public static Projectlist unmarshalFromProjectlistFile() throws JAXBException {
		File file = new File("src/xml/files/_projectlist.xml");
		if (file.exists()) {
			JAXBContext jaxbContext = JAXBContext.newInstance(Projectlist.class);
			javax.xml.bind.Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			return (Projectlist) jaxbUnmarshaller.unmarshal(new File("src/xml/files/_projectlist.xml"));	
		}
		else {
			return new Projectlist();
		}
	}

	/**
	 * Project aus id_projectname.xml laden und Objekt erzeugen.
	 * 
	 * @param id - Die einzigartige ID des Projektes, das geladen werden soll.
	 * @param projectname - Der Name des Projektes, das geladen werden soll.
	 * @return Das spezifische Project.
	 * @throws JAXBException
	 */
	public static Project unmarshalFromProjectFile(int id, String projectname) throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(Project.class);
		javax.xml.bind.Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		return (Project) jaxbUnmarshaller.unmarshal(new File("src/xml/files/" + id + "_" + projectname + ".xml"));
	}
	/**
	 * Project aus Objekt in id_projectname.xml speichern.
	 * 
	 * @param project - Das spezifische Projekt, das gespeichert werden soll.
	 * @throws JAXBException
	 */
	public static void marshalToProjectFile(Project project) throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(Project.class);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		jaxbMarshaller.marshal(project, new File("src/xml/files/" + project.getID() + "_" + project.getProjectname() + ".xml"));
	}
	/**
	 * Projectlist aus Objekt in _projectlist.xml speichern.
	 * 
	 * @param projectname - Der Name des Projektes, das geladen werden soll.
	 * @throws JAXBException
	 */
	public static void marshalToProjectlistFile(Projectlist projectlist) throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(Projectlist.class);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		jaxbMarshaller.marshal(projectlist, new File("src/xml/files/_projectlist.xml"));
	}
	/**
	 * Löscht Eintrag in _projectlist.xml, um ihn danach nochmal anzuhängen.
	 * 
	 * @param id - Die ID des Projekts, das aus der _projectlist.xml gelöscht werden soll.
	 * @throws JAXBException
	 */
	private static void deleteEntryInProjectlist(int id) throws JAXBException {
		Projectlist xmlProjectlist = unmarshalFromProjectlistFile();

		Iterator<ProjectOverviewEntries> iterator = xmlProjectlist.getProjectOverviewEntries().iterator();
		while (iterator.hasNext()) {
			if (id == iterator.next().getID()) {
				iterator.remove();
				marshalToProjectlistFile(xmlProjectlist);
			}
		}
	}

	/**
	 * Erhält Username und gibt eine Hashmap von mehreren Projects und mehreren ProjectOverviewEntries wieder.
	 * 
	 * @param userName - Der Username, nach dem in Projekten gesucht wird.
	 * @return Eine Hashmap aus mehreren Projects und mehreren ProjectOverviewEntries.
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
	 * Erhält eine ID und gibt eine Hashmap aus einem Projekt und einem ProjectOverviewEntries zurück.
	 * 
	 * @param id - ID des Projektes.
	 * @return Eine Hashmap aus einem Projekt und einem ProjectOverviewEntries zurück.
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
