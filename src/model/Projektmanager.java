package model;

import java.util.ArrayList;

import communication.Server;
import model.interfaces.RMI_Projektmanager;

public class Projektmanager implements RMI_Projektmanager {
	
	public Projektmanager()
	{
		
	}
	
	/**
	 * @param u
	 * @param projektname
	 * @param beschreibung
	 * creates project
	 */
	public void erstelleProjekt(User u, String projektname, String beschreibung)
	{
		Projekt projekt = new Projekt(u, projektname, beschreibung);
		Server.server.bindProjekt(projekt.getProjektname(), projekt);
		
		System.out.println("Erstellt!");
	}
	
	/**
	 * @param username
	 * @return returns a list of projects
	 */
	public ArrayList<String> ladeProjekte(String username)
	{
		ArrayList<String> projektliste = new ArrayList<>();
		
		return projektliste;
	}

}
