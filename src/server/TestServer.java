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
			
			System.setProperty("java.rmi.server.hostname", "localhost");
			LocateRegistry.createRegistry(1099);

			ServerController s = new ServerController("TestTask","test");
			TaskRMI taskStub = (TaskRMI) UnicastRemoteObject.exportObject(s.testTask, 0);

			Registry registry = LocateRegistry.getRegistry();
			registry.bind("TestTask", taskStub);
			
	        System.out.println("Server ready!");
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
