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
			
			Registry registry = LocateRegistry.getRegistry("192.168.1.153");
			
			TaskRMI testTask = (TaskRMI) registry.lookup("TestTask");
			
			testTask.ändereName("Peter2");
			
			System.out.println("Methode aufgerufen.");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
