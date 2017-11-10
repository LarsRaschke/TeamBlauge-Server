package communication;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import model.Projekt;
import model.Projektmanager;
import model.Task;
import model.interfaces.RMI_Projekt;
import model.interfaces.RMI_Projektmanager;
import model.interfaces.RMI_Task;

/**
 * Server-Klasse.
 * 
 * @author withakea
 *
 */
public class Server implements ServerComm{

	public static Server server;
	
	private ArrayList<ClientComm> clients = new ArrayList<>();
	
	/**
	 * Konstruktor.
	 * 
	 * @throws RemoteException
	 */
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
			
//			ServerComm stub_server = (ServerComm) UnicastRemoteObject.exportObject(server, 0);
//			Registry registry = LocateRegistry.getRegistry();
//			registry.rebind("Server", stub_server);
			
			System.out.println("2");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * Bindet den Projektmanager an die RMI-Registry.
	 * 
	 * @param identifier - Die eindeutige Bezeichnung des Projektmanagers. 
	 * @param manager - Der Projektmanager.
	 */
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
	
	/**
	 * Bindet ein Projekt an die RMI-Registry.
	 * 
	 * @param identifier - Die eindeutige Bezeichnung des Projektes. 
	 * @param projekt - Das Projekt.
	 */
	public void bindProjekt(String identifier, Projekt projekt)
	{
		try {
			
			RMI_Projekt stub_projekt = (RMI_Projekt) UnicastRemoteObject.exportObject(projekt, 0);
			Registry registry = LocateRegistry.getRegistry();
			registry.rebind(identifier, stub_projekt);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Bindet einen Task an die RMI-Registry.
	 * 
	 * @param identifier - Die eindeutige Bezeichnung des Tasks.
	 * @param task - Der Task.
	 */
	public void bindTask(String identifier, Task task)
	{
		try {
			
			RMI_Task stub_task = (RMI_Task) UnicastRemoteObject.exportObject(task, 0);
			Registry registry = LocateRegistry.getRegistry();
			registry.rebind(identifier, stub_task);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void register(ClientComm client) throws RemoteException {
		
		this.clients.add(client);
	}

	@Override
	public void unregister(ClientComm client) throws RemoteException {
		
		this.clients.remove(client);
	}

	@Override
	public void notifyClients(ClientComm client, String gui) throws RemoteException {

		for(ClientComm notifyClient : this.clients)
		{
			if(!notifyClient.equals(client))
			{
				try {
					client.notifyChanges(gui);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
	}
}
