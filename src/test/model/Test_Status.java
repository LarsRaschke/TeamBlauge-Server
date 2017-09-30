package test.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.junit.Test;

import model.Status;

/**
 * Test class for Status.
 *
 */
public class Test_Status {
	
	Status toDo;
	Status doing;
	Status finished;
	
	/**
	 * set up for the test cases.
	 */
	@Before
	public void setUp(){
		toDo = new Status("ToDo");
		doing = new Status("Doing");
		finished = new Status("Finished");
	}
	
	@Test
	public void testVorgaenger(){
		
		doing.setVorgaenger(toDo);
		assertEquals(toDo, doing.getVorgaenger());
		
		finished.setVorgaenger(doing);
		assertEquals(doing, finished.getVorgaenger());
		
		doing.setVorgaenger(null);
		assertEquals(null, doing.getVorgaenger());
	}
	
	@Test
	public void testNachfolger(){
		
		toDo.setNachfolger(doing);
		assertEquals(doing, toDo.getNachfolger());
		
		doing.setNachfolger(finished);
		assertEquals(finished, doing.getNachfolger());
		
		toDo.setNachfolger(null);
		assertEquals(null, toDo.getNachfolger());
	}
	
	@Test
	public void testName(){
		
		assertEquals("ToDo", toDo.getName());
		assertEquals("Doing", doing.getName());
		assertEquals("Finished", finished.getName());
	}
	
	@Test
	public void testEquals(){
		
		Status status = new Status("ToDo");
		
		assertTrue(toDo.equals(status));
		assertFalse(doing.equals(status));
		assertFalse(finished.equals(status));
		
		status = new Status("Testing");
		
		assertFalse(toDo.equals(status));
		assertFalse(doing.equals(status));
		assertFalse(finished.equals(status));
		
		status = null;
		
		assertFalse(toDo.equals(status));
		assertFalse(doing.equals(status));
		assertFalse(finished.equals(status));
		
	}

}