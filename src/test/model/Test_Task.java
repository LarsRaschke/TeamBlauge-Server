package test.model;

import static org.junit.Assert.*;

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
	
	Statusliste statusliste;
	
	/**
	 * set up for the test cases.
	 */
	@Before
	public void setUp() {
		
		User user1 = new User("TestNutzername",false, "TestNachname","TestVorname");
		task = new Task("3Pkt Contest", "nothing but net",user1);
		
		statusliste = new Statusliste();
		statusliste.insertAfter(new Status("Testing"), "Doing");
		statusliste.insertAfter(new Status("Verify"), "Finished");
		
		System.out.println(statusliste.getAllNames());

	}
	
	@Test
	public void testID()
	{
		task.setId(9348357);
		assertEquals(9348357, task.getId());
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
	public void testName() {
		User user1 = new User("TestNutzername",false, "TestNachname","TestVorname");
		Task task = new Task("","", user1);
		assertEquals("", task.getName());
		task.setName("TestTask");
		assertEquals("TestTask", task.getName());
	}
	@Test
	public void testLetzterNutzer()
	{
		User user2 = new User("TestNutzername2",false, "TestNachname2","TestVorname2");
		task.setLetzterNutzer(user2);
		assertEquals(user2, task.getLetzterNutzer());
	}
	@Test
	public void testLetzteAenderung() {
		ZonedDateTime datum1 = ZonedDateTime.now();
		task.setLetzteAenderung(datum1);
		assertEquals(datum1, task.getLetzteAenderung());
	}
	@Test
	public void testKommentar()
	{
		User user1 = new User("TestNutzername",false, "TestNachname","TestVorname");
		Task task = new Task("","", user1);
		ArrayList<String> kommentar = new ArrayList<>();
		kommentar.add("Test");
		task.setKommentar(kommentar);
		assertTrue(task.getKommentar().contains("Test"));
	}	
	@Test
	public void testFarbe()
	{
		task.setFarbe(5);
		assertEquals(5, task.getFarbe());
	}
	@Test
	public void testGetSingleTag(){
		ArrayList<String> tags = new ArrayList<String>();
		tags.add("tag1");
		tags.add("tag2");
		task.setTags(tags);
		assertEquals("tag1", task.getSingleTag(0));
	}
	@Test
	public void testSucheTag(){
		ArrayList<String> tags = new ArrayList<String>();
		tags.add("tag1");
		tags.add("tag2");
		task.setTags(tags);
		assertEquals(true, task.sucheTag("tag1"));
		assertEquals(false, task.sucheTag("tag4939"));
	}
	@Test
	public void testLoescheTagByNr(){
		ArrayList<String> tags = new ArrayList<String>();
		tags.add("tag1");
		tags.add("tag2");
		task.setTags(tags);
		task.loescheTag(1);
		assertFalse(task.getTags().contains("tag2"));
	}
	@Test
	public void testLoescheTagByBezeichnung(){
		ArrayList<String> tags = new ArrayList<String>();
		tags.add("tag1");
		tags.add("tag2");
		task.setTags(tags);
		task.loescheTag("tag1");
		assertFalse(task.getTags().contains("tag1"));
	}
	@Test
	public void testFügeTagHinzu(){
		ArrayList<String> tags = new ArrayList<String>();
		tags.add("tag1");
		tags.add("tag2");
		task.setTags(tags);
		task.getTags().add("tag3");
		assertTrue(task.getTags().contains("tag3"));
	}
	@Test
	public void testFügeKommentarHinzu(){
		String kommentar1 = "Kommentar1";
		String kommentar2 = "Kommentar2";
		User user1 = new User(null, false, null, null);
		task.fügeKommentarHinzu(kommentar1, user1);
		task.fügeKommentarHinzu(kommentar2, user1);
		assertEquals(kommentar1 + kommentar2 + "\n", task.getKommentar());
		assertEquals(user1, task.getLetzterNutzer());
	}
	@Test
	public void testÄndereFarbe(){
		int farbe = 232425;
		User user1 = new User(null, false, null, null);
		task.ändereFarbe(farbe, user1);
		assertEquals(232425, task.getFarbe());
		assertEquals(user1, task.getLetzterNutzer());
	}
	@Test
	public void testTaskNachVorneVerschieben(){
		Status status1 = new Status("toDo");
		Status status2 = new Status("doing");
		Status status3 = new Status("finished");
		task.setStatus(status1);
		status1.setNachfolger(status2);
		status2.setNachfolger(status3);
		task.taskNachVorneVerschieben();
		assertEquals(status2, task.getStatus());
		task.taskNachVorneVerschieben();
		assertEquals(status3, task.getStatus());
		assertEquals(false, task.taskNachVorneVerschieben());	 		
	}	
	@Test
	public void testTaskNachHintenVerschieben(){
		Status status1 = new Status("toDo");
		Status status2 = new Status("doing");
		Status status3 = new Status("finished");
		task.setStatus(status3);
		status3.setVorgaenger(status2);
		status2.setVorgaenger(status1);
		task.taskNachHintenVerschieben();
		assertEquals(status2, task.getStatus());
		task.taskNachHintenVerschieben();
		assertEquals(status1, task.getStatus());
		assertEquals(false, task.taskNachHintenVerschieben());			
	}		
	
	@Test
	public void testVerschieben() {
		
		task.setStatus(statusliste.getHead());
		assertEquals(task.getStatus().getName(), "To Do");
		assertEquals(task.getStatus().getVorgaenger(), null);
		assertEquals(task.getStatus().getNachfolger().getName(), "Doing");
		
		task.taskNachVorneVerschieben();
		assertEquals(task.getStatus().getName(), "Doing");
		assertEquals(task.getStatus().getVorgaenger().getName(), "To Do");
		assertEquals(task.getStatus().getNachfolger().getName(), "Testing");
		
		task.taskNachVorneVerschieben();
		assertEquals(task.getStatus().getName(), "Testing");
		assertEquals(task.getStatus().getVorgaenger().getName(), "Doing");
		assertEquals(task.getStatus().getNachfolger().getName(), "Finished");
		
		task.taskNachHintenVerschieben();
		assertEquals(task.getStatus().getName(), "Doing");
		assertEquals(task.getStatus().getVorgaenger().getName(), "To Do");
		assertEquals(task.getStatus().getNachfolger().getName(), "Testing");
		
		task.taskNachVorneVerschieben();
		assertEquals(task.getStatus().getName(), "Testing");
		assertEquals(task.getStatus().getVorgaenger().getName(), "Doing");
		assertEquals(task.getStatus().getNachfolger().getName(), "Finished");
		
		task.taskNachVorneVerschieben();
		assertEquals(task.getStatus().getName(), "Finished");
		assertEquals(task.getStatus().getVorgaenger().getName(), "Testing");
		assertEquals(task.getStatus().getNachfolger().getName(), "Verify");
		
		task.taskNachVorneVerschieben();
		assertEquals(task.getStatus().getName(), "Verify");
		assertEquals(task.getStatus().getVorgaenger().getName(), "Finished");
		assertEquals(task.getStatus().getNachfolger(), null);
		
		task.taskNachHintenVerschieben();
		assertEquals(task.getStatus().getName(), "Finished");
		assertEquals(task.getStatus().getVorgaenger().getName(), "Testing");
		assertEquals(task.getStatus().getNachfolger().getName(), "Verify");
		
		task.taskNachHintenVerschieben();
		assertEquals(task.getStatus().getName(), "Testing");
		assertEquals(task.getStatus().getVorgaenger().getName(), "Doing");
		assertEquals(task.getStatus().getNachfolger().getName(), "Finished");
		
		task.taskNachVorneVerschieben();
		assertEquals(task.getStatus().getName(), "Finished");
		assertEquals(task.getStatus().getVorgaenger().getName(), "Testing");
		assertEquals(task.getStatus().getNachfolger().getName(), "Verify");
		
		task.taskNachHintenVerschieben();
		assertEquals(task.getStatus().getName(), "Testing");
		assertEquals(task.getStatus().getVorgaenger().getName(), "Doing");
		assertEquals(task.getStatus().getNachfolger().getName(), "Finished");
		
		task.taskNachHintenVerschieben();
		assertEquals(task.getStatus().getName(), "Doing");
		assertEquals(task.getStatus().getVorgaenger().getName(), "To Do");
		assertEquals(task.getStatus().getNachfolger().getName(), "Testing");
		
		task.taskNachHintenVerschieben();
		assertEquals(task.getStatus().getName(), "To Do");
		assertEquals(task.getStatus().getVorgaenger(), null);
		assertEquals(task.getStatus().getNachfolger().getName(), "Doing");
		
		task.taskNachVorneVerschieben();
		assertEquals(task.getStatus().getName(), "Doing");
		assertEquals(task.getStatus().getVorgaenger().getName(), "To Do");
		assertEquals(task.getStatus().getNachfolger().getName(), "Testing");
		
		task.taskNachHintenVerschieben();
		assertEquals(task.getStatus().getName(), "To Do");
		assertEquals(task.getStatus().getVorgaenger(), null);
		assertEquals(task.getStatus().getNachfolger().getName(), "Doing");
	}
	
	@Test
	public void testGetSingleTagBezeichnung(){
		ArrayList<String> tags = new ArrayList<String>();
		tags.add("tag1");
		tags.add("tag2");
		task.setTags(tags);
		assertTrue("tag2", task.sucheTag("tag2"));
	}
}