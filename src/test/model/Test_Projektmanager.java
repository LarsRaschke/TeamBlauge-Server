package test.model;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import model.Projekt;
import model.User;

/**
 * Test class for Projektmanager.
 *
 */
public class Test_Projektmanager {

	/**
	 * set up for the test cases.
	 */
	@Before
	public void setUp(){
		
	}
	@Test
	public void testLadeProjekte(){
		User user1 = new User(null, false, null, null);
		Projekt projekt1 = new Projekt(user1, null, null);
		Projekt projekt2 = new Projekt(null, null, null);
		Projekt projekt3 = new Projekt(user1, null, null);
		ArrayList<String> expected = new ArrayList<>();
		expected.add("Projekt1");
		expected.add("Projekt3");
		// not finished, no server to test it
		
	}
	
}
