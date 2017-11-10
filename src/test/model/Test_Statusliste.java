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
	Status toDo = null;
	Status doing = null;
	Status finished = null;
	Status testing = null;
	
	/**
	 * set up for the test cases.
	 */
	@Before
	public void setUp() {
		list = new Statusliste(null);
		toDo = new Status("toDo");
		doing = new Status("doing");
		finished = new Status("finished");
		testing = new Status("testing");
		list.insertStatus(toDo);
		list.insertStatus(doing);
		list.insertStatus(finished);
	}
	
	@Test
	public void testHead(){
		list.setHead(toDo);
		assertEquals(toDo, list.getHead());
	}
	
	@Test
	public void testLaenge(){
		list.setLaenge(4972);
		assertEquals(4972, list.getLaenge());
	}
	
	@Test
	public void insertStatusTest() {
		assertFalse(list.search(testing));
		list.insertStatus(testing);
		assertTrue(list.search(testing));
	}
	
	@Test
	public void insertBetweenTest() {
		assertFalse(list.search(testing));
		list.insertBetween(testing, doing, finished);
		assertTrue(list.search(testing));
		assertTrue(doing == testing.getVorgaenger());
		assertTrue(finished == testing.getNachfolger());
	}
	
	@Test
	public void insertAfterTest() {
		assertFalse(list.search(testing));
		list.insertAfter(testing, "doing");
		assertTrue(list.search(testing));
		
		assertTrue(doing.getNachfolger().getName().equals("testing"));
		assertTrue(finished.getVorgaenger().getName().equals("testing"));
	}
	
	@Test
	public void getAllTest(){
		ArrayList<Status> testliste = list.getAll();
		assertTrue(testliste.contains(toDo));
		assertTrue(testliste.contains(doing));
		assertTrue(testliste.contains(finished));
		assertFalse(testliste.contains(testing));
	}

	@Test
	public void searchTest() {
		assertTrue(list.search(toDo));
		assertFalse(list.search(testing));
	}
}