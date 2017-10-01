package test.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import javax.security.auth.login.FailedLoginException;

import org.junit.Before;
import org.junit.Test;

import model.Status;
import model.Statusliste;

import java.util.ArrayList;

/**
 * Test class for Statusliste.
 *
 */
public class Test_Statusliste {
	
	Statusliste list = null;
	
	/**
	 * set up for the test cases.
	 */
	@Before
	public void setUp() {
		
		list = new Statusliste();
		
	}
	
	@Test
	public void listeTest() {
		
		assertTrue(list.getHead().equals((new Status("To Do"))));
		assertTrue(list.getHead().getNachfolger().equals((new Status("Doing"))));
		assertTrue(list.getHead().getNachfolger().getNachfolger().equals((new Status("Finished"))));
		assertTrue(list.getHead().getNachfolger().getNachfolger().getNachfolger() == null);
	}
	
	@Test
	public void searchTest() {
		
		assertTrue(list.search(new Status("To Do")));
		assertTrue(list.search(new Status("Doing")));
		assertTrue(list.search(new Status("Finished")));
		
		assertFalse(list.search(new Status("Testing")));
		
	}
	
	@Test
	public void insertStatusTest() {
		
		assertFalse(list.search(new Status("Closed")));
		
		list.insertStatus(new Status("Closed"));
		
		assertTrue(list.search(new Status("Closed")));
		assertTrue(list.getHead().getNachfolger().getNachfolger().getNachfolger().equals((new Status("Closed"))));
		
	}
	
	@Test
	public void insertBetweenTest() {
		
		assertFalse(list.search(new Status("Testing")));
		
		list.insertBetween(new Status("Testing"), new Status("Doing"), new Status("Finished"));
		
		assertTrue(list.search(new Status("Testing")));
		assertTrue(list.getHead().getNachfolger().getNachfolger().equals((new Status("Testing"))));
		assertTrue(list.getHead().getNachfolger().getNachfolger().getNachfolger().equals((new Status("Finished"))));
	}
	
	@Test
	public void getAllTest(){
		ArrayList<Status> testliste = list.getAll();
		assertTrue(testliste.contains(new Status("To Do")));
		assertTrue(testliste.contains(new Status("Doing")));
		assertTrue(testliste.contains(new Status("Finished")));
		assertFalse(testliste.contains(new Status("Testing")));
	}

}