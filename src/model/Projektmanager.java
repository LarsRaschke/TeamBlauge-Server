package model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.RemoteException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.List;

import javax.xml.bind.JAXBException;
import javax.xml.datatype.DatatypeConfigurationException;

import org.xml.sax.SAXException;

import model.interfaces.RMI_Projektmanager;
import xml.Xml_Server;
import xml.projectlist.Projectlist.ProjectOverviewEntries.UserEntries;
import xml.projectlist.Projectlist.ProjectOverviewEntries;
import xml.projects.ObjectFactoryProjects;
import xml.projectlist.ObjectFactory;
import xml.projects.Project;
import xml.projects.Project.StatusEntries;
import xml.projects.Project.TaskEntries.TagEntries;
import xml.projects.Project.TaskEntries;
import model.Projekt;
import model.User;
import xml.projectlist.Projectlist;


public class Projektmanager implements RMI_Projektmanager 
{
	
	public Projektmanager()
	{
		
	}
	
	public void erstelleProjekt(Projekt orginalProjekt) throws JAXBException, FileNotFoundException, DatatypeConfigurationException, SAXException, IOException
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
	
	public List<Projekt> gibtProjektedesUserszurück(User orginaluser) throws JAXBException, DatatypeConfigurationException
	{
		String userName = orginaluser.getNutzername();
		HashMap<Project, Userlist> pList = Xml_Server.checkProjectListandgiveProjectsback(userName);
		
		for (HashMap.Entry<Project, Userlist> entry :pList.entrySet()) 
		{
			xml.projects.Project pro = entry.getKey();
			xml.projectlist.Projectlist.ProjectOverview.Userlist usList = entry.getValue();
			
			Projekt orginalProjekt = new Projekt(orginaluser,pro.getProjectname(),pro.getDescription());
			User orgiUser = new User(pro.getCreator(), true, null, null);
			orginalProjekt.setErsteller(orgiUser);
			orginalProjekt.setId(pro.getID());
			
			orginalProjekt.setErstellungsDatum(ZonedDateTime.parse(pro.getCreatedOn()));
			
			orginalProjekt.setLetzteAenderung(ZonedDateTime.parse(pro.getLastmod()));
			
			Statusliste slist = new Statusliste();
			
			for(String stat: pro.getStatuslist().getStatus())
			{
				Status orgistat = new Status(stat);
				slist.insertStatus(orgistat);
			}
			
			orginalProjekt.setStatusliste(slist);
			
			for( xml.projects.Project.Tasklist.Task tAs : pro.getTasklist().getTask())
			{
				Task orgiTask = new Task()
			}
			
			for(xml.projectlist.Projectlist.ProjectOverview.Userlist.User usa:usList.getUser())
			{
				User orUser = new User(usa.getValue(), usa.isAdmin(), null, null);
				orginalProjekt.addUserToHashMap(orUser);
			}
			
			
		}
		
				
		
		
		
		
	}
	@Override
	public void erstelleProjekt(User u, String projektname, String beschreibung) throws RemoteException, JAXBException {
		// TODO Auto-generated method stub
		
	}



}
