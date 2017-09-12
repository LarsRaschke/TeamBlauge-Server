package xml;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.RemoteException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
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


public class XML_translator  
{
	
	public XML_translator()
	{
		
	}
	
	public void createProject(Projekt orginalProjekt) throws JAXBException, FileNotFoundException, DatatypeConfigurationException, SAXException, IOException
	{
		ObjectFactory ocFac = new ObjectFactory();
		HashMap<String, User> users = orginalProjekt.getUsers();
		ProjectOverviewEntries prooverentries = ocFac.createProjectlistProjectOverviewEntries();
		
		ProjectOverviewEntries.UserEntries  usList = ocFac.createProjectlistProjectOverviewEntriesUserEntries();
		
		for (User us:users.values()) 
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
		
		for(model.Task ta:tasks.values())
		{
			xml.projects.Project.TaskEntries xmlTask = ocFachPro.createProjectTaskEntries();
			xmlTask.setColor(ta.getFarbe());
			xmlTask.setComment(ta.getKommentar());
			xmlTask.setID(ta.getId());
			xmlTask.setLastMod(ta.getLetzteAenderung().toString());
			xmlTask.setStatusname(ta.getStatus().getName());
			xmlTask.setTaskname(ta.getName());
			xmlProject.getTaskEntries().add(xmlTask);

		}
		
		
		Statusliste orginalStatusListe = orginalProjekt.getStatusliste();
		
		
		//Statuslist sListe = ocFachPro.createProjectStatuslist();
		
		for(Status st:orginalStatusListe.getAll())
		{
			StatusEntries stat = ocFachPro.createProjectStatusEntries();
			stat.setStatus(st.getName());
			xmlProject.getStatusEntries().add(stat);
			//sListe.getStatus().add(st.getName());
		}

		prooverentries.setCreatedOn(orginalProjekt.getErstellungsDatum().toString());
		prooverentries.setCreator(orginalProjekt.getErsteller().getNutzername());
		xmlProject.setDescription(orginalProjekt.getBeschreibung());
		xmlProject.setID(orginalProjekt.getId());
		xmlProject.setLastMod(orginalProjekt.getLetzteAenderung().toString());
		xmlProject.setProjectname(orginalProjekt.getProjektname());
		//xmlProject.setStatuslist(sListe);
		//xmlProject.setTasklist(tList)
		prooverentries.setID(orginalProjekt.getId());
		
		Xml_Server.addtoprojectList(xmlProject, prooverentries);

	}

	//**not in use
	public void fügeTaskhinzu(Projekt orginalProjekt,model.Task orginalTask) throws JAXBException
	{
		ObjectFactoryProjects ocFachPro = new ObjectFactoryProjects();
		xml.projects.Project.Tasklist.Task xmlTask = ocFachPro.createProjectTaskEntries();
		
		xmlTask.setColor(orginalTask.getFarbe());
		xmlTask.setComment(orginalTask.getKommentar());
		xmlTask.setID(orginalTask.getId());
		xmlTask.setLastmod(orginalTask.getLetzteAenderung().toString());
		xmlTask.setStatusname(orginalTask.getStatus().getName());
		xmlTask.setTaskname(orginalTask.getName());
		
		Xml_Server.addTasktoProject(orginalProjekt.getProjektname(), xmlTask);
	}
	
	
	public List<Projekt> multiProject(User orginaluser) throws JAXBException, DatatypeConfigurationException
	{
		String userName = orginaluser.getNutzername();
		HashMap<Project, ProjectOverviewEntries> pList = Xml_Server.checkProjectListandgiveProjectsback(userName);
		
		for (Entry<Project, ProjectOverviewEntries> entry :pList.entrySet()) 
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
			
			for(StatusEntries stat: pro.getStatusEntries())
			{
				Status orgistat = new Status(stat.getStatus());
				slist.insertStatus(orgistat);
			}
			
			orginalProjekt.setStatusliste(slist);
			
			for( TaskEntries tAs : pro.getTaskEntries())
			{
				ZonedDateTime lasttime = ZonedDateTime.parse(tAs.getLastMod());
				Task orgiTask = new Task(tAs.getTaskname(), null, null);
				orgiTask.setFarbe(tAs.getColor());
				orgiTask.setId(tAs.getID());
				orgiTask.setKommentar(tAs.getComment());
				orgiTask.setLetzteAenderung(lasttime);
				User oris = new User(tAs.getLastUser(), true, null, null);
				orgiTask.setLetzterNutzer(oris);
				Status orStat = new Status(tAs.getStatusname());
				orgiTask.setStatus(orStat);
				
			}
			
			for(UserEntries usa:Over.getUserEntries())
			{
				User orUser = new User(usa.getValue(), usa.isAdmin(), null, null);
				orginalProjekt.addUserToHashMap(orUser);
			}
	
		}

	}
	public Project singelProject(Integer ID) throws JAXBException, DatatypeConfigurationException
	{
		
		
		HashMap<Project, ProjectOverviewEntries> pList = Xml_Server.checkProjectListandgiveProjectsbackSingel(ID);
		
		for (Entry<Project, ProjectOverviewEntries> entry :pList.entrySet()) 
		{
			xml.projects.Project pro = entry.getKey();
			ProjectOverviewEntries Over = entry.getValue();
			
			Projekt orginalProjekt = new Projekt(Over.getCreator(),pro.getProjectname(),pro.getDescription());
			User orgiUser = new User(Over.getCreator(), true, null, null);
			orginalProjekt.setErsteller(orgiUser);
			orginalProjekt.setId(pro.getID());
			
			orginalProjekt.setErstellungsDatum(ZonedDateTime.parse(Over.getCreatedOn()));
			
			orginalProjekt.setLetzteAenderung(ZonedDateTime.parse(Over.getLastMod()));
			
			Statusliste slist = new Statusliste();
			
			for(StatusEntries stat: pro.getStatusEntries())
			{
				Status orgistat = new Status(stat.getStatus());
				slist.insertStatus(orgistat);
			}
			
			orginalProjekt.setStatusliste(slist);
			
			for( TaskEntries tAs : pro.getTaskEntries())
			{
				ZonedDateTime lasttime = ZonedDateTime.parse(tAs.getLastMod());
				Task orgiTask = new Task(tAs.getTaskname(), null, null);
				orgiTask.setFarbe(tAs.getColor());
				orgiTask.setId(tAs.getID());
				orgiTask.setKommentar(tAs.getComment());
				orgiTask.setLetzteAenderung(lasttime);
				User oris = new User(tAs.getLastUser(), true, null, null);
				orgiTask.setLetzterNutzer(oris);
				Status orStat = new Status(tAs.getStatusname());
				orgiTask.setStatus(orStat);
				
			}
			
			for(UserEntries usa:Over.getUserEntries())
			{
				User orUser = new User(usa.getValue(), usa.isAdmin(), null, null);
				orginalProjekt.addUserToHashMap(orUser);
			}
	
		}
	}
	



}
