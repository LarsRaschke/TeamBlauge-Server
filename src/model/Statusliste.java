package model;

import java.util.ArrayList;

/**
 * Model-Klasse für die Statusliste eine Projekts.
 */
public class Statusliste {
	
	private Status head;
	private int laenge;

	/**
	 * Konstruktor für Standart-Statusliste.
	 */
	public Statusliste() {
		this.head = new Status("To Do");
		this.insertStatus(new Status("Doing"));
		this.insertStatus(new Status("Finished"));
	}

	/**
	 * Konstruktor.
	 * 
	 * @param head - Den ersten Status (Head).
	 */
	public Statusliste(Status head) {
		this.head = head;
	}

	/**
	 * Getter-Methode.
	 * 
	 * @return Den Head-Status.
	 */
	public Status getHead() {
		return head;
	}

	/**
	 * Setter-Methode.
	 * 
	 * @param head - Der Head-Status.
	 */
	public void setHead(Status head) {
		this.head = head;
	}

	/**
	 * Getter-Methode.
	 * 
	 * @return Die Länge der Statusliste.
	 */
	public int getLaenge() {
		return laenge;
	}

	/**
	 * Setter-Methode.
	 * 
	 * @param laenge - Die Länge der Statusliste.
	 */
	public void setLaenge(int laenge) {
		this.laenge = laenge;
	}

	/**
	 * Fügt einen Status am Ende der Liste ein.
	 * 
	 * @param status - Der Status, der eingefügt wird.
	 */
	public void insertStatus(Status status) {
		this.laenge ++;
		Status tmp = this.head;
		if (this.head == null) {
			this.head = status;
		} else {
			while (tmp.getNachfolger() != null) {
				tmp = tmp.getNachfolger();
			}
			status.setVorgaenger(tmp);
			tmp.setNachfolger(status);
		}
	}
	
	/**
	 * Fügt einen Status zwischen zwei ausgewählten Stati ein.
	 * 
	 * @param status - Der Status, der eingefügt wird.
	 * @param vorg - Der Vorgänger-Status.
	 * @param nachf - Der Nachfolger-Status.
	 */
	public void insertBetween(Status status, Status vorg, Status nachf) {
		if (this.search(vorg) && this.search(nachf)) {
			Status lauf = this.head;
			while(lauf != null && !lauf.equals(vorg)) {
				lauf = lauf.getNachfolger();
			}
			if(lauf.equals(vorg)) {
				status.setNachfolger(nachf);
				nachf.setVorgaenger(status);
				status.setVorgaenger(lauf);
				lauf.setNachfolger(status);
			}
		}		
	}
	
	/**
	 * Fügt einen Status nach einem ausgewählten Stati ein.
	 * 
	 * @param status - Der Status, der eingefügt wird.
	 * @param vorg - Der Vorgänger-Status.
	 */
	public void insertAfter(Status status, String vorg) {
		if (this.search(vorg)) {
			Status lauf = this.head;
			while(lauf != null && !lauf.getName().equals(vorg)) {
				lauf = lauf.getNachfolger();
			}
			if(lauf.getName().equals(vorg)) {
				status.setNachfolger(lauf.getNachfolger());
				if(lauf.getNachfolger() != null)
				{
					lauf.getNachfolger().setVorgaenger(status);
				}
				status.setVorgaenger(lauf);
				lauf.setNachfolger(status);
			}
		}		
	}

	/**
	 * Gibt alle in der Liste enthaltenen Stati zurück.
	 * 
	 * @return Eine ArrayList mit allen Stati.
	 */
	public ArrayList<Status> getAll() {
		ArrayList<Status> erg = new ArrayList<>();
		Status tmp = this.head;
		while (tmp != null) {
			erg.add(tmp);
			tmp = tmp.getNachfolger();
		}
		return (erg);
	}
	
	/**
	 * Gibt alle in der Liste enthaltenen Stati zurück.
	 * 
	 * @return Eine ArrayList mit allen Stati.
	 */
	public ArrayList<String> getAllNames() {
		ArrayList<String> erg = new ArrayList<>();
		Status tmp = this.head;
		while (tmp != null) {
			erg.add(tmp.getName());
			tmp = tmp.getNachfolger();
		}
		return (erg);
	}
	
	/**
	 * Sucht einen Status in der Liste.
	 * 
	 * @param status - Der gesuchte Status
	 * 
	 * @return True, falls der Status in der Liste enthalten ist, andernfalls False.
	 */
	public boolean search(Status status) {
		Status lauf = this.head;
		while (lauf != null && !lauf.equals(status)) {
			lauf = lauf.getNachfolger();
		}
		return (lauf != null);
	}
	
	/**
	 * Sucht einen Status in der Liste.
	 * 
	 * @param status - Der gesuchte Status
	 * 
	 * @return True, falls der Status in der Liste enthalten ist, andernfalls False.
	 */
	public boolean search(String status) {
		Status lauf = this.head;
		while (lauf != null && !lauf.getName().equals(status)) {
			lauf = lauf.getNachfolger();
		}
		return (lauf != null);
	}

}
