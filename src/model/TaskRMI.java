package model;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface TaskRMI extends Remote{

	void �ndereName(String name) throws RemoteException;
	
	void f�geKommentarHinzu(String kommentar) throws RemoteException;
	
	Boolean �ndereFarbe(int farbe) throws RemoteException;
	
}
