package model;

import javax.xml.bind.JAXBException;

import model.interfaces.RMI_Projektmanager;
import xml.Xml_Server;
import xml.projects.Project;

public class Projektmanager implements RMI_Projektmanager {
	
	public Projektmanager()
	{
		
	}
	
	public void erstelleProjekt(User u, String projektname, String beschreibung) throws JAXBException
	{
		Projekt projekt = new Projekt(u, projektname, beschreibung);
		Project project = new Project();
		project.setProjectname(projektname);
		project.setCreatedOn();
		project.setLastmod();
		project.setCreator(u.getNutzername());
		project.setDescription(beschreibung);
		Xml_Server.marshalToFile(project, projektname);
		System.out.println("Erstellt!");
	}

}
