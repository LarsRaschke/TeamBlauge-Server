package model;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import javax.xml.bind.JAXBException;

import model.interfaces.RMI_Projektmanager;
import xml.Xml_Server;
import xml.projects.ObjectFactoryProjects;
import xml.projects.Project;

public class Projektmanager implements RMI_Projektmanager {
	
	public Projektmanager()
	{
		
	}
	
	public void erstelleProjekt(User u, String projektname, String beschreibung) throws JAXBException
	{
		Projekt projekt = new Projekt(u, projektname, beschreibung);
		ObjectFactoryProjects objFactoryProjects = new ObjectFactoryProjects();
		Project project = objFactoryProjects.createProject();
		project.setProjectname(projektname);
		ZonedDateTime now = ZonedDateTime.now(ZoneId.of("Europe/France"));
		project.setCreatedOn(now.toString());
		project.setLastmod(now.toString());
		project.setCreator(u.getNutzername());
		project.setDescription(beschreibung);
		Xml_Server.marshalToProjectFile(project, projektname);
		System.out.println("Erstellt!");
	}

}
