package client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import model.TaskRMI;

public class TestClient {

	public TestClient()
	{
		
	}
	
	public static void main(String[] args) {
		
		try {
			
			Registry registry = LocateRegistry.getRegistry(null);
			
			TaskRMI testTask = (TaskRMI) registry.lookup("TestTask");
			
			System.out.println(testTask.ändereFarbe(1).toString());
			
			System.out.println("Methode aufgerufen.");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
