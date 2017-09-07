package test.model;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.time.ZonedDateTime;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import model.*;

/**
 * Test class for Task.
 *
 */
public class Test_Task {

	Task task = null;
	
	/**
	 * set up for the test cases.
	 */
	@Before
	public void setUp() {
		
		User user1 = new User("TestNutzername",false, "TestNachname","TestVorname");
		task = new Task("3Pkt Contest", "nothing but net",user1);

	}
	
	
	@Test
	public void testName() {
		User user1 = new User("TestNutzername",false, "TestNachname","TestVorname");
		Task task = new Task("","", user1);
		assertEquals("", task.getName());
		task.setName("TestTask");
		assertEquals("TestTask", task.getName());
	}
	@Test
	public void testKommentar()
	{
		User user1 = new User("TestNutzername",false, "TestNachname","TestVorname");
		Task task = new Task("","", user1);
		assertEquals("", task.getKommentar());
		task.setKommentar("Test");
		assertEquals("Test", task.getKommentar());
	}
	@Test
	public void testStatus()
	{
		User user1 = new User("TestNutzername",false, "TestNachname","TestVorname");
		Status toDo = new Status("toDo");
		Task task = new Task("","", user1);
		task.setStatus(toDo);
		assertEquals(toDo, task.getStatus());
		
	}
	@Test
	public void testLetzteAenderung() {
		ZonedDateTime datum1 = ZonedDateTime.now();
		task.setLetzteAenderung(datum1);
		assertEquals(datum1, task.getLetzteAenderung());
	}
	@Test
	public void testFarbe()
	{
		task.setFarbe(5);
		assertEquals(5, task.getFarbe());
	}
	@Test
	public void testID()
	{
		task.setId(9348357);
		assertEquals(9348357, task.getId());
	}
	@Test
	public void testTags()
	{
		// fehlt in aktuellstem UML
	}
	@Test
	public void testLetzterNutzer()
	{
		User user2 = new User("TestNutzername2",false, "TestNachname2","TestVorname2");
		task.setLetzterNutzer(user2);
		assertEquals(user2, task.getLetzterNutzer());
	}
	
	@Test
	public void testTaskNachVorne(){
		Status status1 = new Status("toDo");
		Status status2 = new Status("doing");
		Status status3 = new Status("finished");
		Status status4 = new Status(null);
		task.setStatus(status1);
		status1.setNachfolger(status2);
		status2.setNachfolger(status3);
		status3.setNachfolger(status4);
		task.taskNachVorne();
		assertEquals(status2, task.getStatus());
		task.taskNachVorne();
		assertEquals(status3, task.getStatus());
		
		
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		String err = "ERROR: Task already in last status.";
		task.taskNachVorne();
		assertEquals(err, outContent.toString());
		 		
	}
	
	@Test
	public void testTaskNachHinten(){
		Status status1 = new Status("toDo");
		Status status2 = new Status("doing");
		Status status3 = new Status("finished");
		task.setStatus(status3);
		status3.setVorgaenger(status2);
		status2.setVorgaenger(status1);
		task.taskNachHinten();
		assertEquals(status2, task.getStatus());
		task.taskNachHinten();
		assertEquals(status1, task.getStatus());
		/*
		 * ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		 * String err = "ERROR: Task already in first status.";
		 * task.taskNachHinten();
		 * assertTrue(err == outContent.toString());
		 */		
	}
	
	@Test
	public void testGetSingleTagNr(){
		ArrayList<String> tags = new ArrayList<String>();
		tags.add("tag1");
		tags.add("tag2");
		task.setTags(tags);
		assertEquals("tag1", task.getSingleTag(0));
		/*
		 *	Error Fall muss noch rein 
		 */
	}
	
	@Test
	public void testGetSingleTagBezeichnung(){
		ArrayList<String> tags = new ArrayList<String>();
		tags.add("tag1");
		tags.add("tag2");
		task.setTags(tags);
		assertEquals("tag2", task.getSingleTag("tag2"));
		/*
		 *	Error Fall muss noch rein 
		 */
	}
	
	@Test
	public void testLoescheTagNr(){
		ArrayList<String> tags = new ArrayList<String>();
		tags.add("tag1");
		tags.add("tag2");
		task.setTags(tags);
		task.loescheTag(1);
		assertFalse(task.getTags().contains("tag2"));
		/*
		 *	Error Fall muss noch rein 
		 */
	}
	
	@Test
	public void testLoescheTagBezeichnung(){
		ArrayList<String> tags = new ArrayList<String>();
		tags.add("tag1");
		tags.add("tag2");
		task.setTags(tags);
		task.loescheTag("tag1");
		assertFalse(task.getTags().contains("tag1"));
		/*
		 *	Error Fall muss noch rein 
		 */
	}
	
	@Test
	public void testErstelleTag(){
		ArrayList<String> tags = new ArrayList<String>();
		tags.add("tag1");
		tags.add("tag2");
		task.setTags(tags);
		task.getTags().add("tag3");
		assertTrue(task.getTags().contains("tag3"));
	}
}