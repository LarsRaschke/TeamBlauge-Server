package server;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import model.Task;
import model.TaskRMI;

public class TestServer {
	
	public TestServer()
	{
		
	}
	
	public static void main(String[] args) {
		
		try {
			
			System.setProperty("java.rmi.server.hostname", "192.168.1.153");
			LocateRegistry.createRegistry(1099);

			Task testTask = new Task("TestTask", "Test");
			TaskRMI taskStub = (TaskRMI) UnicastRemoteObject.exportObject(testTask, 0);

			Registry registry = LocateRegistry.getRegistry();
			registry.bind("TestTask", taskStub);
			
	        System.out.println("Server ready!");
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
