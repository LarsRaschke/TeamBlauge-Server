package model;

import model.interfaces.RMI_Projektmanager;

public class Projektmanager implements RMI_Projektmanager {
	
	public Projektmanager()
	{
		
	}
	
	public void erstelleProjekt(User u, String projektname, String beschreibung)
	{
		Projekt projekt = new Projekt(u, projektname, beschreibung);
		
		System.out.println("Erstellt!");
	}

}
