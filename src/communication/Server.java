package communication;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import model.Projektmanager;
import model.interfaces.RMI_Projektmanager;

public class Server {

	public static Server server;
	
	public Server() throws RemoteException
	{
		System.setProperty("java.rmi.server.hostname", "localhost");
		LocateRegistry.createRegistry(1099);
		
		System.out.println("1");
	}
	
	public static void main(String[] args) {

		try {

			server = new Server();
			
			Projektmanager manager = new Projektmanager();
			server.bindProjektmanager("manager", manager);
			
			System.out.println("2");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void bindProjektmanager(String identifier, Projektmanager manager)
	{
		try {
			
			RMI_Projektmanager stub_manager = (RMI_Projektmanager) UnicastRemoteObject.exportObject(manager, 0);
			Registry registry = LocateRegistry.getRegistry();
			registry.rebind(identifier, stub_manager);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
