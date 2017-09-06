package test.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.*;

import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

/*
 * Hashmap Funktionen müssen noch getestet werden 
 */

public class Test_Projekt {
	
	User user1 = new User("Nutzername", false, "Nachname", "Vorname");
	Projekt projekt1 = new Projekt(user1, "");
	
	@Before
	public void setUp(){
		
	}
	@Test
	public void testErsteller() {
		projekt1.setErsteller(user1);
		assertEquals(user1, projekt1.getErsteller());
	}
	@Test
	public void testTasks(){
		HashMap<String, Task> map = new HashMap<>();
		Task task1 = new Task("", "", null);
		Task task2 = new Task("", "", null);
		map.put("Teststring1", task1);
		map.put("Teststring2", task2);
		HashMap<String, Task> expected = new HashMap<>();
		expected.put("Teststring1", task1);
		expected.put("Teststring2", task2);
		projekt1.setTasks(map);
		assertEquals(expected, projekt1.getTasks());
	}
	@Test
	public void testUsers(){
		HashMap<String, User> map = new HashMap<>();
		User user1 = new User("", false, "", "");
		User user2 = new User("", false, "", "");
		map.put("Teststring1", user1);
		map.put("Teststring2", user2);
		HashMap<String, User> expected = new HashMap<>();
		expected.put("Teststring1", user1);
		expected.put("Teststring2", user2);
		projekt1.setUsers(map);
		assertEquals(expected, projekt1.getUsers());
	}
	@Test
	public void testBezeichnung(){
		projekt1.setBezeichnung("Projekt1");
		assertEquals("Projekt1", projekt1.getBezeichnung());
	}
	@Test
	public void testId(){
		projekt1.setId(555);
		assertEquals(555, projekt1.getId());
	}
	@Test
	public void testStatusliste(){
		Statusliste statusliste1 = new Statusliste();
		projekt1.setStatusliste(statusliste1);
		assertEquals(statusliste1, projekt1.getStatusliste());
	}
	@Test
	public void testLetzteAenderung(){
		ZonedDateTime datum1 = ZonedDateTime.now();
		projekt1.setLetzteAenderung(datum1);
		assertEquals(datum1, projekt1.getLetzteAenderung());
	}
	@Test
	public void testErstellungsDatum(){
		ZonedDateTime datum1 = ZonedDateTime.now();
		projekt1.setErstellungsDatum(datum1);
		assertEquals(datum1, projekt1.getErstellungsDatum());
	}

}
