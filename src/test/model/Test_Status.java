package test.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.Status;

/**
 * Test class for Status.
 *
 */
public class Test_Status {
	
	Status status1 = new Status("toDo");
	Status status2 = new Status("finished");
	Status status3 = new Status("doing");
	
	/**
	 * set up for the test cases.
	 */
	@Before
	public void setUp(){
		
	}
	
	@Test
	public void testVorgaenger(){
		status1.setVorgaenger(status2);
		assertEquals(status2, status1.getVorgaenger());
	}
	
	@Test
	public void testNachfolger(){
		status1.setNachfolger(status3);
		assertEquals(status3, status1.getNachfolger());
	}
	
	@Test
	public void testName(){
		assertEquals("toDo", status1.getName());
	}

}