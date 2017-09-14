package model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBException;
import javax.xml.datatype.DatatypeConfigurationException;

import communication.Server;
import model.interfaces.RMI_Projektmanager;
import xml.XML_translator;

/**
 * Model-Klasse f�r den Projektmanager.
 */
public class Projektmanager implements RMI_Projektmanager {
	
	/**
	 * Konstruktor.
	 */
	public Projektmanager()
	{
		
	}
	
	/**
	 * Erstellt ein neues Projekt.
	 * 
	 * @param user - Der Ersteller des Projekts.
	 * @param projektname - Der Projektname.
	 * @param beschreibung - Die Projektbeschreibung.
	 */
	public void erstelleProjekt(User user, String projektname, String beschreibung)
	{
		Projekt projekt = new Projekt(user, projektname, beschreibung);
		Server.server.bindProjekt(projekt.getProjektname(), projekt);
		projekt.speichereProjekt();
	}
	
	/**
	 * L�dt die Namen aller Projekte, denen ein User zugewiesen ist.
	 * 
	 * @param username - Der Username des Users.
	 * 
	 * @return Eine Liste mit den Projektnamen
	 */
	public ArrayList<String> ladeProjekte(User user)
	{
		ArrayList<String> projektliste = new ArrayList<>();
		
		List<Projekt> projekte = new ArrayList<>();
		try {
			projekte = XML_translator.multiProject(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		for(Projekt projekt : projekte)
		{
			projektliste.add(projekt.getProjektname());
		}
		
		return projektliste;
	}

}
