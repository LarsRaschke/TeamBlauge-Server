package model;

import java.rmi.RemoteException;

public class Task implements TaskRMI{
	
	private String name;
	private String kommentar;
	private int farbe;
	
	public Task(String name, String kommentar){
		this.name = name;
		this.kommentar = kommentar + "\n";
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getKommentar() {
		return kommentar;
	}
	public void setKommentar(String kommentar) {
		this.kommentar = kommentar;
	}
	public int getFarbe() {
		return farbe;
	}
	public void setFarbe(int farbe) {
		this.farbe = farbe;
	}

	@Override
	public void ändereName(String name) throws RemoteException {
		this.setName(name);
		System.out.println(this.name);
	}

	@Override
	public void fügeKommentarHinzu(String kommentar) throws RemoteException {
		this.setKommentar(this.getKommentar() + kommentar + "\n");
		System.out.println(this.kommentar);
	}

	@Override
	public Boolean ändereFarbe(int farbe) throws RemoteException {
		try {
			if(farbe == 0) {
				throw new Exception();
			}
			this.setFarbe(farbe);
			System.out.println(this.farbe);
			return true;
		}catch(Exception e) {
			return false;
		}
	}
}
