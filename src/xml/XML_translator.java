package xml;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.RemoteException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import javax.xml.bind.JAXBException;
import javax.xml.datatype.DatatypeConfigurationException;

import org.xml.sax.SAXException;

import model.interfaces.RMI_Projektmanager;
import xml.projectlist.Projectlist.ProjectOverviewEntries.UserEntries;
import xml.projectlist.Projectlist.ProjectOverviewEntries;
import xml.projects.ObjectFactoryProjects;
import xml.projectlist.ObjectFactory;
import xml.projects.Project;
import xml.projects.Project.StatusEntries;
import xml.projects.Project.TaskEntries.TagEntries;
import xml.projects.Project.TaskEntries;
import model.Projekt;
import model.Status;
import model.Statusliste;
import model.Task;
import model.User;
import xml.projectlist.Projectlist;

/*
 * �bersetzungklasse
 * XML to Projekt and back
 * Tingar und Wohlrab
 * 
 * 
 */
public class XML_translator  
{
	
	
	/**
	 * Methode erh�lt das Projekt, �bersetz es f�r die XML Project und XML Projectlist und gibt es weiter an den XML Server
	 * @param orginalProjekt
	 * @throws JAXBException
	 * @throws FileNotFoundException
	 * @throws DatatypeConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	public static void createProject(Projekt orginalProjekt) throws JAXBException, FileNotFoundException, DatatypeConfigurationException, SAXException, IOException
	{
		ObjectFactory ocFac = new ObjectFactory();
		HashMap<String, User> users = orginalProjekt.getUsers();
		ProjectOverviewEntries prooverentries = ocFac.createProjectlistProjectOverviewEntries();
		
		ProjectOverviewEntries.UserEntries  usList = ocFac.createProjectlistProjectOverviewEntriesUserEntries();
		
		for (User us:users.values())   // �bergibt User
		{
			ProjectOverviewEntries.UserEntries xmlUser = ocFac.createProjectlistProjectOverviewEntriesUserEntries();
			xmlUser.setValue(us.getNutzername());
			xmlUser.setAdmin(us.isAdmin());
			prooverentries.getUserEntries().add(xmlUser);
					
		}
		
		ObjectFactoryProjects ocFachPro = new ObjectFactoryProjects();
		Project xmlProject = ocFachPro.createProject();
		//Tasklist tList = ocFachPro.createProjectTasklist();
		HashMap<String, model.Task> tasks = new HashMap<String, model.Task>();
		tasks = orginalProjekt.getTasks();
		
		for(model.Task ta:tasks.values()) // �bergibt Tasks
		{
			xml.projects.Project.TaskEntries xmlTask = ocFachPro.createProjectTaskEntries();
			xmlTask.setColor(ta.getFarbe());
			//xmlTask.setComment(ta.getKommentar());
			xmlTask.setID(ta.getId());
			xmlTask.setLastMod(ta.getLetzteAenderung().toString());
			xmlTask.setStatusname(ta.getStatus().getName());
			xmlTask.setTaskname(ta.getName());
			xmlProject.getTaskEntries().add(xmlTask);

		}
		
		
		Statusliste orginalStatusListe = orginalProjekt.getStatusliste();
		
		
		if( orginalStatusListe !=null)
		{
			for(Status st:orginalStatusListe.getAll()) // �bergibt StatusList
			{
				StatusEntries stat = ocFachPro.createProjectStatusEntries();
				stat.setStatus(st.getName());
				xmlProject.getStatusEntries().add(stat);
			
			}
		}
		if(orginalProjekt.getErstellungsDatum() !=null)
		{
		prooverentries.setCreatedOn(orginalProjekt.getErstellungsDatum().toString());
		}
		if (orginalProjekt.getErsteller().getNutzername()!=null)
		{
		prooverentries.setCreator(orginalProjekt.getErsteller().getNutzername());
		}
		if(orginalProjekt.getErsteller().getNutzername() !=null)
		{
		xmlProject.setDescription(orginalProjekt.getBeschreibung());
		}
		if(orginalProjekt.getId() !=0)
		{
		xmlProject.setID(orginalProjekt.getId());
		}
		if(orginalProjekt.getLetzteAenderung() !=null)
		{
		xmlProject.setLastMod(orginalProjekt.getLetzteAenderung().toString());
		}
		xmlProject.setProjectname(orginalProjekt.getProjektname());
		
		prooverentries.setID(orginalProjekt.getId());
		
		Xml_Server.saveProject(xmlProject, prooverentries); // speichert das Projekt

	}
	/**
	 * Erh�lt einen User, gibt eine Hashmap wieder und �bersetzt in das OrginalProjekt und gibt liste davon wieder
	 * @param orginaluser
	 * @return User der Projekt
	 * @throws JAXBException
	 * @throws DatatypeConfigurationException
	 */
	public static List<Projekt> multiProject(User orginaluser) throws JAXBException, DatatypeConfigurationException
	{
		String userName = orginaluser.getNutzername();
		HashMap<Project, ProjectOverviewEntries> pList = Xml_Server.getProjectsByUser(userName);  //erh�lt Hashmap
		List<Projekt> orginalList = new ArrayList<Projekt>();
		
		
		for (Entry<Project, ProjectOverviewEntries> entry :pList.entrySet()) // geht durch die Hashmap
		{
			xml.projects.Project pro = entry.getKey();
			ProjectOverviewEntries Over = entry.getValue();
			
			Projekt orginalProjekt = new Projekt(orginaluser,pro.getProjectname(),pro.getDescription());
			User orgiUser = new User(Over.getCreator(), true, null, null);
			orginalProjekt.setErsteller(orgiUser);
			orginalProjekt.setId(pro.getID());
			
			orginalProjekt.setErstellungsDatum(ZonedDateTime.parse(Over.getCreatedOn()));
			
			orginalProjekt.setLetzteAenderung(ZonedDateTime.parse(Over.getLastMod()));
			
			Statusliste slist = new Statusliste();
			
			for(StatusEntries stat: pro.getStatusEntries()) // �bergibt Statuslist
			{
				Status orgistat = new Status(stat.getStatus());
				slist.insertStatus(orgistat);
			}
			
			orginalProjekt.setStatusliste(slist);
			
			for( TaskEntries tAs : pro.getTaskEntries())  // geht durch Tasklist �bersetzt
			{
				ZonedDateTime lasttime = ZonedDateTime.parse(tAs.getLastMod());
				Task orgiTask = new Task(tAs.getTaskname(), null, null);
				orgiTask.setFarbe(tAs.getColor());
				orgiTask.setId(tAs.getID());
				//orgiTask.setKommentar(tAs.getComment());
				orgiTask.setLetzteAenderung(lasttime);
				User oris = new User(tAs.getLastUser(), true, null, null);
				orgiTask.setLetzterNutzer(oris);
				Status orStat = new Status(tAs.getStatusname());
				orgiTask.setStatus(orStat);
				
			}
			
			for(UserEntries usa:Over.getUserEntries()) // �bersetzt User
			{
				User orUser = new User(usa.getValue(), usa.isAdmin(), null, null);
				orginalProjekt.addUserToHashMap(orUser);
			}
			orginalList.add(orginalProjekt);
			
	
		}
		return orginalList;

	}
	/**
	 * gibt ID an XML Server, erh�lt Hashmap und �bersetzt und gibt OrginalProjekt wieder
	 * @param ID der Projekts
	 * @return OrginalProjekt
	 * @throws JAXBException
	 * @throws DatatypeConfigurationException
	 */
	public Projekt singelProject(int ID) throws JAXBException, DatatypeConfigurationException
	{
		
		Projekt orginalProjekt = null;
		HashMap<Project, ProjectOverviewEntries> pList = Xml_Server.getProjectByID(ID); // erh�lt Hashmap
		
		for (Entry<Project, ProjectOverviewEntries> entry :pList.entrySet()) 
		{
			if(entry.getKey()!= null) // wenn entry null dann geht er aus der Schleife , da nur ein Eintrag
			{
			xml.projects.Project pro = entry.getKey();
			ProjectOverviewEntries Over = entry.getValue();
			User orgi = new User(Over.getCreator(), false, null, null);
			orginalProjekt = new Projekt(orgi,pro.getProjectname(),pro.getDescription());
			User orgiUser = new User(Over.getCreator(), true, null, null);
			orginalProjekt.setErsteller(orgiUser);
			orginalProjekt.setId(pro.getID());
			
			orginalProjekt.setErstellungsDatum(ZonedDateTime.parse(Over.getCreatedOn()));
			
			orginalProjekt.setLetzteAenderung(ZonedDateTime.parse(Over.getLastMod()));
			
			Statusliste slist = new Statusliste();
			
			for(StatusEntries stat: pro.getStatusEntries()) // �bersetzt die Status
			{
				Status orgistat = new Status(stat.getStatus());
				slist.insertStatus(orgistat);
			}
			
			orginalProjekt.setStatusliste(slist);
			
			for( TaskEntries tAs : pro.getTaskEntries()) // �bersetzt die Tasks
			{
				ZonedDateTime lasttime = ZonedDateTime.parse(tAs.getLastMod());
				Task orgiTask = new Task(tAs.getTaskname(), null, null);
				orgiTask.setFarbe(tAs.getColor());
				orgiTask.setId(tAs.getID());
				//orgiTask.setKommentar(tAs.getComment());
				orgiTask.setLetzteAenderung(lasttime);
				User oris = new User(tAs.getLastUser(), true, null, null);
				orgiTask.setLetzterNutzer(oris);
				Status orStat = new Status(tAs.getStatusname());
				orgiTask.setStatus(orStat);
				
			}
			
			for(UserEntries usa:Over.getUserEntries())  // �bersetzt die USer
			{
				User orUser = new User(usa.getValue(), usa.isAdmin(), null, null);
				orginalProjekt.addUserToHashMap(orUser);
			}
			
			}
			
	
		}
		return orginalProjekt;
	}
	/**
	 * l�scht das Projekt aus der Projektliste 
	 * @param orginalProjekt
	 * @return
	 */
	public boolean deleteProjekt(Projekt orginalProjekt)
	{
		boolean check = true;
		try {
			Xml_Server.deleteProjectPermanently(orginalProjekt.getId(), orginalProjekt.getProjektname());
			check = true;
		} catch (JAXBException e) 
		{
			check = false;
		}
		return check;
		
	}
	



}
