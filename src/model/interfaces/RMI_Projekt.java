package model.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.ZonedDateTime;
import java.util.List;

import model.User;

/**
 * Interface f�r den RMI-Zugriff auf einen Projekt.
 * 
 * @author withakea
 *
 */
public interface RMI_Projekt extends Remote {

	public String getProjektname() throws RemoteException;
	
	public String getBeschreibung() throws RemoteException;
	
	public User getErsteller() throws RemoteException;
	
	public ZonedDateTime getLetzteAenderung() throws RemoteException;
	
	public ZonedDateTime getErstellungsDatum() throws RemoteException;

	public List<User> userListe() throws RemoteException;
	
	public List<String[]> taskListe() throws RemoteException;
	
	public void taskHinzuf�gen(String name, String kommentar, User u) throws RemoteException;
	
	public void userHinzuf�gen(User user) throws RemoteException;
	
	public void �ndereBeschreibung(String beschreibung) throws RemoteException;
	
	public void speichereProjekt() throws RemoteException;
}
