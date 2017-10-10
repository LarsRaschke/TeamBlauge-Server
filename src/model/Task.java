package model;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;

import model.interfaces.RMI_Task;

/**
 * Model-Klasse für einen Task.
 */
public class Task implements RMI_Task{
	
	private int id;
	private Status status;
	private String name;
	private String beschreibung;
	private ZonedDateTime letzteAenderung;
	private ArrayList<String> kommentar  = new ArrayList<>();
	private int farbe;
	private ArrayList<String> tags = new ArrayList<>();
	private User letzterNutzer;

	/**
	 * Konstruktor.
	 * 
	 * @param name - Der Taskname.
	 * @param bescheibung - Beschreibung zum Task.
	 * @param user - Der Ersteller
	 */
	public Task(String name, String beschreibung, User user){
		this.name = name;
		this.beschreibung = beschreibung;
		this.letzteAenderung = ZonedDateTime.now(ZoneId.systemDefault());
		this.setLetzterNutzer(user);
	}
	
	/**
	 * Konstruktor.
	 * 
	 * @param name - Der Taskname.
	 * @param status - Der Status zum Task.
	 * @param bescheibung - Beschreibung zum Task.
	 * @param user - Der Ersteller
	 */
	public Task(String name, Status status, String beschreibung, User user){
		this.name = name;
		this.status = status;
		this.beschreibung = beschreibung;
		this.letzteAenderung = ZonedDateTime.now(ZoneId.systemDefault());
		this.setLetzterNutzer(user);
	}
	
	/**
	 * Getter-Methode.
	 * 
	 * @return Die Task-ID.
	 */
	public int getId() {
		return id;
	}

	/**
	 * Setter-Methode.
	 * 
	 * @param id - Die Task-ID.
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * Getter-Methode.
	 * 
	 * @return Den Status.
	 */
	public Status getStatus() {
		return status;
	}
	
	/**
	 * Setter-Methode.
	 * 
	 * @param status - Den Status.
	 */
	public void setStatus(Status status) {
		this.status = status;
	}
	
	/**
	 * Getter-Methode.
	 * 
	 * @return Den Namen.
	 */
	@Override
	public String getName() {
		return name;
	}
	
	/**
	 * Setter-Methode.
	 * 
	 * @param name - Den Namen.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Getter-Methode.
	 * 
	 * @return Die Beschreibung.
	 */
	public String getBeschreibung() {
		return beschreibung;
	}

	/**
	 * Setter-Methode.
	 * 
	 * @param beschreibung - Die Beschreibung.
	 */
	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}

	/**
	 * Getter-Methode.
	 * 
	 * @return Den letzten Nutzer.
	 */
	@Override
	public User getLetzterNutzer() {
		return letzterNutzer;
	}

	/**
	 * Setter-Methode.
	 * 
	 * @param letzterNutzer -  Den letzten Nutzer.
	 */
	public void setLetzterNutzer(User letzterNutzer) {
		this.letzterNutzer = letzterNutzer;
	}

	/**
	 * Getter-Methode.
	 * 
	 * @return Das letzte Änderungsdatum.
	 */
	@Override
	public ZonedDateTime getLetzteAenderung() {
		return letzteAenderung;
	}
	
	/**
	 * Setter-Methode.
	 * 
	 * @param letzteAenderung - Das letzte Änderungsdatum.
	 */
	public void setLetzteAenderung(ZonedDateTime letzteAenderung) {
		this.letzteAenderung = letzteAenderung;
	}
	
	/**
	 * Getter-Methode.
	 * 
	 * @return Den Kommentar.
	 */
	@Override
	public ArrayList<String> getKommentar() {
		return kommentar;
	}
	
	/**
	 * Setter-Methode.
	 * 
	 * @param kommentar - Den Kommentar.
	 */
	public void setKommentar(ArrayList<String> kommentar) {
		this.kommentar = kommentar;
	}
	
	/**
	 * Getter-Methode.
	 * 
	 * @return Die Farbe.
	 */
	@Override
	public int getFarbe() {
		return farbe;
	}
	
	/**
	 * Setter-Methode.
	 * 
	 * @param farbe - Die Farbe.
	 */
	public void setFarbe(int farbe) {
		this.farbe = farbe;
	}
	
	/**
	 * Getter-Methode.
	 * 
	 * @return Die Tags.
	 */
	@Override
	public ArrayList<String> getTags()
	{
		return this.tags;
	}
	
	/**
	 * Setter-Methode.
	 * 
	 * @param tags - Die Tags.
	 */
	public void setTags(ArrayList<String> tags)
	{
		this.tags = tags;
	}
	
	/**
	 * Gibt den gesuchten Tag aus.
	 * 
	 * @param nr - Die gesuchte Stelle.
	 * 
	 * @return Den Tag an der gesuchten Stelle.
	 */
	public String getSingleTag(int nr)
	{
		if(this.getTags().get(nr) != null)
		{
			return this.getTags().get(nr);
		}
		else
		{
			System.out.println("ERROR: Tag does not exist");
			return null;
		}
	}
	
	/**
	 * Sucht einen bestimmten Tag.
	 * 
	 * @param bezeichnung - Der Name des gesuchten Tags.
	 * 
	 * @return True, wenn der Tag vorhanden ist, andernfalls False.
	 */
	public boolean sucheTag(String bezeichnung)
	{
		for(int i=0; i<this.getTags().size(); i++)
		{
			if(this.getTags().get(i).equals(bezeichnung))
			{
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Löscht den Tag an einer bestimmten Stelle.
	 * 
	 * @param nr - Die Stelle des zu löschenden Tags.
	 */
	public void loescheTag(int nr)
	{
		if(nr > this.tags.size())
		{
			System.out.println("ERROR: Tag not found");
		}
		else
		{
			this.getTags().remove(nr);
		}
	}
	
	/**
	 * Löscht einen gesuchten Tag.
	 * 
	 * @param bezeichnung - Der Name des zu löschenden Tags.
	 */
	public void loescheTag(String bezeichnung)
	{
		boolean found = false;
		for(int i=0; i<this.getTags().size(); i++)
		{
			if(this.tags.get(i).equals(bezeichnung))
			{
				this.getTags().remove(i);
				found = true;
			}
		}
		if(found == false)
			System.out.println("ERROR: Tag not found.");
	}
	
	/**
	 * Fügt einen Tag zum Task hinzu.
	 * Synchronisiert um gleichzeitiges Schreiben zu verhindern.
	 * 
	 * @param bezeichnung - Der Name des Tags.
	 * @param user - Der User, der die Änderung eingegeben hat.
	 */
	@Override
	public synchronized void fügeTagHinzu(String bezeichnung, User user)
	{
		this.getTags().add(bezeichnung);
		this.setLetzterNutzer(user);
		this.setLetzteAenderung(ZonedDateTime.now(ZoneId.systemDefault()));
	}

	/**
	 * Fügt einen Kommentar hinzu.
	 * Synchronisiert um gleichzeitiges Schreiben zu verhindern.
	 * 
	 * @param kommentar - Der Kommentar, der angefügt wird.
	 * @param user - Der User, der die Änderung eingegeben hat.
	 */
	@Override
	public synchronized void fügeKommentarHinzu(String kommentar, User user) 
	{
		this.kommentar.add(kommentar + " | " + user.getNachname() + ", " + user.getVorname());
		this.setLetzterNutzer(user);
		this.setLetzteAenderung(ZonedDateTime.now(ZoneId.systemDefault()));
	}

	/**
	 * Setzt eine neue Beschreibung.
	 * Synchronisiert um gleichzeitiges Schreiben zu verhindern.
	 * 
	 * @param beschreibung - Die neue Beschreibung.
	 * @param user - Der User, der die Änderung eingegeben hat.
	 */
	@Override
	public synchronized void ändereBeschreibung(String beschreibung , User user)
	{
		this.setBeschreibung(beschreibung);
		this.setLetzterNutzer(user);
		this.setLetzteAenderung(ZonedDateTime.now(ZoneId.systemDefault()));
	}
	
	/**
	 * Setzt eine neue Farbe.
	 * Synchronisiert um gleichzeitiges Schreiben zu verhindern.
	 * 
	 * @param farbe - Die neue Farbe.
	 * @param user - Der User, der die Änderung eingegeben hat.
	 */
	@Override
	public synchronized void ändereFarbe(int farbe , User user)
	{
		this.setFarbe(farbe);
		this.setLetzterNutzer(user);
		this.setLetzteAenderung(ZonedDateTime.now(ZoneId.systemDefault()));
	}
	
	/**
	 * Verschiebt den Task in den nächsten Status.
	 * Synchronisiert um gleichzeitiges Schreiben zu verhindern.
	 */
	@Override
	public synchronized boolean taskNachVorneVerschieben()
	{
		/**
		 * Task can be moved by any user
		 */
		try{
			Status temp;
			temp = this.getStatus().getNachfolger().getNachfolger();
			if(this.getStatus().getNachfolger() != null)
			{
				this.getStatus().setVorgaenger(this.status);
				this.setStatus(this.getStatus().getNachfolger());
				this.getStatus().setNachfolger(temp);
				this.setLetzteAenderung(ZonedDateTime.now(ZoneId.systemDefault()));
				return true;
			}
		}
		catch(NullPointerException npe){
			npe.printStackTrace(); // Error Log fehlt noch
		}
		return false;
	}
	
	/**
	 * Verschiebt den Status in den vorherigen Status.
	 * Synchronisiert um gleichzeitiges Schreiben zu verhindern.
	 */
	@Override
	public synchronized boolean taskNachHintenVerschieben()
	{
		/**
		 * TODO: Implement admin-only in this method
		 */
		try{
			Status temp;
			temp = this.getStatus().getVorgaenger().getVorgaenger();
			if(this.getStatus().getVorgaenger() != null)
			{
				this.getStatus().setNachfolger(this.getStatus());
				this.setStatus(this.getStatus().getVorgaenger());
				this.getStatus().setVorgaenger(temp);
				this.setLetzteAenderung(ZonedDateTime.now(ZoneId.systemDefault()));
				return true;
			}
		}
		catch(NullPointerException npe){
			npe.printStackTrace(); // Error Log fehlt noch
		}
		return false;
	}

}
