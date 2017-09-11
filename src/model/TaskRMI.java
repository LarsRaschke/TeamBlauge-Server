package model;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface TaskRMI extends Remote{

	void ändereName(String name) throws RemoteException;
	
	void fügeKommentarHinzu(String kommentar) throws RemoteException;
	
	Boolean ändereFarbe(int farbe) throws RemoteException;
	
}
