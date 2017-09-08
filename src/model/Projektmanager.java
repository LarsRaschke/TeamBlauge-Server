package model;

import java.util.ArrayList;

import communication.Server;
import model.interfaces.RMI_Projektmanager;

public class Projektmanager implements RMI_Projektmanager {
	
	public Projektmanager()
	{
		
	}
	
	public void erstelleProjekt(User u, String projektname, String beschreibung)
	{
		Projekt projekt = new Projekt(u, projektname, beschreibung);
		Server.server.bindProjekt(projekt.getProjektname(), projekt);
		
		System.out.println("Erstellt!");
	}
	
	public ArrayList<String> ladeProjekte(String username)
	{
		ArrayList<String> projektliste = new ArrayList<>();
		
		return projektliste;
	}

}
