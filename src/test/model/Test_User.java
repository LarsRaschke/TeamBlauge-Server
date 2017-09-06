package test.model;

import static org.junit.Assert.*;

import org.junit.Test;
import model.User;

public class Test_User {
	
	User user1 = new User("Nutzername", false, "Nachname", "Vorname");
	
	@Test
	public void testNutzername(){
		user1.setNutzername("Nutzername2");
		assertEquals("Nutzername2", user1.getNutzername());
	}
	
	@Test
	public void testIsAdmin(){
		user1.setAdmin(true);
		assertEquals(true, user1.isAdmin());
	}

	@Test
	public void testNachname(){
		user1.setNachname("Nachname1");
		assertEquals("Nachname1", user1.getNachname());
	}
	
	@Test
	public void testVorname(){
		user1.setVorname("Vorname1");
		assertEquals("Vorname1", user1.getVorname());
	}
}
