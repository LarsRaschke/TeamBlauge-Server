package model.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

import javax.xml.bind.JAXBException;

import model.User;

public interface RMI_Projektmanager extends Remote {
	
	public void erstelleProjekt(User u, String projektname, String beschreibung) throws RemoteException, JAXBException;

}
