package model.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

import model.User;

public interface RMI_Projekt extends Remote {
	
	public void taskHinzuf�gen(String name, String kommentar, User u) throws RemoteException;

}
