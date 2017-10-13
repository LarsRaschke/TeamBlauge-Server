package test.model;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.bind.JAXBException;
import javax.xml.datatype.DatatypeConfigurationException;

import org.xml.sax.SAXException;

import model.Projekt;
import model.Status;
import model.Statusliste;
import model.Task;
import model.User;
import xml.XML_translator;

public class test_for_xml {
	
	
	public static Projekt FakeProjekt(String name, int a)
	{
		User us = new User("MCD", true, "hans", "Peter");
		Status stat1 = new Status("Do");
		Status stat2 = new Status("Did");
		Status stat3 = new Status("Done");
		
		Statusliste statList = new Statusliste();
		
		Projekt pro = new Projekt(us, name, statList, "Kauabanga");
		
		pro.setId(a);
		
		User us1 = new User("MCD2", false, "hans", "Peter");
		User us2 = new User("MCD3", false, "hans", "Peter");
		User us3 = new User("MCD4", false, "hans", "Peter");
		User us4 = new User("MCD5", false, "hans", "Peter");
		
		HashMap<String, User> users = new HashMap<String, User>();
		
		pro.addUserToHashMap(us1);
		pro.addUserToHashMap(us2);
		pro.addUserToHashMap(us3);
		pro.addUserToHashMap(us4);

		Task ta1 = new Task("Hausaufgaben1",stat1, "mache Hausaufgaben", us4);
		Task ta2 = new Task("Hausaufgaben2", stat2,"mache Hausaufgaben", us1);
		Task ta3 = new Task("Hausaufgaben3", stat3,"mache Hausaufgaben", us2);
		Task ta4 = new Task("Hausaufgaben4", stat1,"mache Hausaufgaben", us3);
		
		ta1.fügeTagHinzu("hans", us1);
		ta2.fügeTagHinzu("hans", us1);
		ta3.fügeTagHinzu("hans", us1);
		ta4.fügeTagHinzu("hans", us1);
		
		ta1.fügeKommentarHinzu("joj", us1);
		ta2.fügeKommentarHinzu("joj", us1);
		ta3.fügeKommentarHinzu("joj", us1);
		ta4.fügeKommentarHinzu("joj", us1);
		
		
		pro.addTaskToHashMap(ta1);
		pro.addTaskToHashMap(ta2);
		pro.addTaskToHashMap(ta3);
		pro.addTaskToHashMap(ta4);
	
		
		return pro;
		
	}
	
	

	public static void main(String[] args) throws FileNotFoundException, JAXBException, DatatypeConfigurationException, SAXException, IOException
	{
		
		////Eingeben von Projekten
		//Projekt pro = FakeProjekt("bll", 4);
		
		//XML_translator.createProject(pro);
		
		
		
		///Ausgabe von Mehreren Projekten
		/*int i = 0;
		User us1 = new User("MCD2", false, "hans", "Peter");
		List<Projekt> list = XML_translator.multiProject(us1);
		for (Projekt pro: list)
		{
			i++;
		}
		*/
		
		////Ausgabe ein Projekt
		
		Projekt pro = XML_translator.singelProject(1);
		String dis = pro.getBeschreibung();
		
		
		
		
		

	}

}
