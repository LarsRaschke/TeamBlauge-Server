package model.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.ZonedDateTime;
import java.util.ArrayList;

import model.User;

/**
 * Interface f�r den RMI-Zugriff auf einen Task.
 * 
 * @author withakea
 *
 */
public interface RMI_Task extends Remote {

	public String getName() throws RemoteException;
	
	public String getBeschreibung() throws RemoteException;
	
	public ArrayList<String> getKommentar() throws RemoteException;
	
	public int getFarbe() throws RemoteException;
	
	public User getLetzterNutzer() throws RemoteException;
	
	public ZonedDateTime getLetzteAenderung() throws RemoteException;
	
	public ArrayList<String> getTags() throws RemoteException;
	
	public void �ndereBeschreibung(String beschreibung, User user) throws RemoteException;
	
	public void f�geKommentarHinzu(String kommentar, User user) throws RemoteException;
	
	public void �ndereFarbe(int farbe, User user) throws RemoteException;
	
	public void f�geTagHinzu(String tag, User user) throws RemoteException;
	
	public boolean taskNachVorneVerschieben() throws RemoteException;
	
	public boolean taskNachHintenVerschieben() throws RemoteException;
}
