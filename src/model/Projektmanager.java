package model;

import java.util.ArrayList;

import communication.Server;
import model.interfaces.RMI_Projektmanager;

/**
 * Model-Klasse für den Projektmanager.
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
	 * Lädt die Namen aller Projekte, denen ein User zugewiesen ist.
	 * 
	 * @param username - Der Username des Users.
	 * 
	 * @return Eine Liste mit den Projektnamen
	 */
	public ArrayList<String> ladeProjekte(String username)
	{
		ArrayList<String> projektliste = new ArrayList<>();
		
		// ToDo: aus XML
		
		return projektliste;
	}

}
