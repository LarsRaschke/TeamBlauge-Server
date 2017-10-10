package model.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import model.User;

/**
 * Interface f�r den RMI-Zugriff auf den Projektmanager.
 * 
 * @author withakea
 *
 */
public interface RMI_Projektmanager extends Remote {
	
	public boolean erstelleProjekt(User u, String projektname, String beschreibung) throws RemoteException;

	public ArrayList<String> ladeProjekte(User user) throws RemoteException;
}
