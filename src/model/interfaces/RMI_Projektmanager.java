package model.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.xml.bind.JAXBException;

import model.User;

/**
 * Interface für den RMI-Zugriff auf den Projektmanager.
 * 
 * @author withakea
 *
 */
public interface RMI_Projektmanager extends Remote {
	
	public void erstelleProjekt(User u, String projektname, String beschreibung) throws RemoteException, JAXBException;

	public ArrayList<String> ladeProjekte(String nutzername) throws RemoteException;
}
