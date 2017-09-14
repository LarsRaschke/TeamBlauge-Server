package model;

import java.rmi.RemoteException;
import java.time.ZonedDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.interfaces.RMI_Projekt;

/**
 * Model-Klasse für ein Projekt.
 */
public class Projekt implements RMI_Projekt{
	private int id;
	private User ersteller;
	private String projektname;
	private String beschreibung;
	private HashMap<String, Task> tasks = new HashMap<String, Task>();
	private HashMap<String, User> users = new HashMap<String, User>();
	private Statusliste statusliste;
	private ZonedDateTime letzteAenderung;
	private ZonedDateTime erstellungsDatum;

	/**
	 * Konstruktor.
	 * 
	 * @param user - Der User, der das Projekt anlegt.
	 * @param projektname - Name des Projekts.
	 * @param beschreibung - Projektbeschreibung.
	 */
	public Projekt(User user, String projektname, String beschreibung) {
		this.ersteller = user;
		this.projektname = projektname;
		this.beschreibung = beschreibung;
		users.put(user.getNutzername(), user);
		this.erstellungsDatum = ZonedDateTime.now(ZoneId.of("Europe/Paris"));
	}

	/**
	 * Getter-Methode.
	 * 
	 * @return  Die Task-ID.
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
	 * @return Den Ersteller des Projekts.
	 */
	@Override
	public User getErsteller() {
		return ersteller;
	}

	/**
	 * Setter-Methode.
	 * 
	 * @param ersteller - Der Ersteller des Projekts.
	 */
	public void setErsteller(User ersteller) {
		this.ersteller = ersteller;
	}
	
	/**
	 * Getter-Methode.
	 * 
	 * @return Den Projektnamen.
	 */
	@Override
	public String getProjektname() {
		return projektname;
	}

	/**
	 * Setter-Methode.
	 * 
	 * @param projektname - Der Projektname.
	 */
	public void setProjektname(String projektname) {
		this.projektname = projektname;
	}

	/**
	 * Getter-Methode.
	 * 
	 * @return Die Projektbeschreibung.
	 */
	@Override
	public String getBeschreibung() {
		return beschreibung;
	}

	/**
	 * Setter-Methode.
	 * 
	 * @param beschreibung - Die Projektbeschreibung.
	 */
	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}
	
	/**
	 * Getter-Methode.
	 * 
	 * @return Die Tasks.
	 */
	public HashMap<String, Task> getTasks() {
		return tasks;
	}
	
	/**
	 * Setter-Methode.
	 * 
	 * @param tasks - Die Tasks.
	 */
	public void setTasks(HashMap<String, Task> tasks) {
		this.tasks = tasks;
	}
	
	/**
	 * Getter-Methode.
	 * 
	 * @return Die User.
	 */
	public HashMap<String, User> getUsers() {
		return users;
	}
	
	/**
	 * Setter-Methode.
	 * 
	 * @param users - Die User.
	 */
	public void setUsers(HashMap<String, User> users) {
		this.users = users;
	}

	/**
	 * Getter-Methode.
	 * 
	 * @return Die letzte Änderung.
	 */
	@Override
	public ZonedDateTime getLetzteAenderung() {
		return letzteAenderung;
	}

	/**
	 * Setter-Methode.
	 * 
	 * @param letzteAenderung - Die letzte Änderung.
	 */
	public void setLetzteAenderung(ZonedDateTime letzteAenderung) {
		this.letzteAenderung = letzteAenderung;
	}

	/**
	 * Getter-Methode.
	 * 
	 * @return Das Erstellungsdatum.
	 */
	@Override
	public ZonedDateTime getErstellungsDatum() {
		return erstellungsDatum;
	}

	/**
	 * Setter-Methode.
	 * 
	 * @param erstellungsDatum - Das Erstellungsdatum.
	 */
	public void setErstellungsDatum(ZonedDateTime erstellungsDatum) {
		this.erstellungsDatum = erstellungsDatum;
	}

	/**
	 * Getter-Methode.
	 * 
	 * @return Die Statusliste des Projekts.
	 */
	public Statusliste getStatusliste() {
		return statusliste;
	}
	
	/**
	 * Setter-Methode.
	 * 
	 * @param statusliste - Die Statusliste des Projekts.
	 */
	public void setStatusliste(Statusliste statusliste) {
		this.statusliste = statusliste;
	}

	/**
	 * Fügt einen neuen Task ein.
	 * 
	 * @param task - Der Task, der hinzugefügt werden soll.
	 */
	public void addTaskToHashMap(Task task){
		tasks.put(task.getName(), task);
	}
	
	/**
	 * Löscht einen bestehenden Task.
	 * 
	 * @param task - Der Task, der gelöscht werden soll.
	 */
	public void deleteTaskFromHashMap(Task task){
		tasks.remove(task.getName());
	}
	
	/**
	 * Fügt einen User zum Projekt hinzu hinzu.
	 * 
	 * @param user Der User, der hinzugefügt werden soll.
	 */
	public void addUserToHashMap(User user){
		users.put(user.getNutzername(), user);
	}
	
	/**
	 * Löscht einen User aus dem Projekt.
	 * 
	 * @param user Der User, der gelöscht werden soll.
	 */
	public void deleteUserFromHashMap(User user){
		users.remove(user.getNutzername());
	}
	
	/**
	 * Gibt alle Tasks zurück, denen der gesuchte Tag zugewiesen wurde.
	 * 
	 * @param tagname - Name des Tags
	 * 
	 * @return Eine HashMap mit allen gesuchten Tasks.
	 */
	public HashMap<String, Task> getTasksOfTag(String tagname)
	{
		HashMap<String, Task> taskList = new HashMap<String, Task>();
		try{
			for(int i=0; i<this.getTasks().size(); i++)
			{
				for(int j=0; j<this.getTasks().get(i).getTags().size(); j++)
				{
					if(this.getTasks().get(i).getTags().get(j).equals(tagname))
					{
						taskList.put(tagname, this.getTasks().get(i));
						break;
					}
				}
			}
		}
		catch(NullPointerException npe){
			System.out.println("NullPointerException gefunden bei getTasksOfTag"); // Error Log fehlt noch
		}
		return taskList;
	}
	
	/**
	 * Gibt alle User zurück, die dem Projekt zugewiesen sind.
	 * 
	 * @return Eine ArrayList mit den Usern.
	 */
	@Override
	public List<User> userListe() throws RemoteException {
		List<User> userList = new ArrayList<>();
		for(Map.Entry<String, User> entry : this.users.entrySet())
		{
			userList.add(entry.getValue());
		}
		return userList;
	}
	
	/**
	 * Gibt eine Liste aller Tasks in Form von Namen und Status zurück.
	 * Mehr Informationen werden für die erste Anzeige im KanbanBoard nicht benötigt,
	 * der detalliertere Aufruf erfolg über RMI_Task.
	 * 
	 * @return Die Liste aller Tasks in dem gegebenen Format.
	 */
	@Override
	public List<String[]> taskListe() throws RemoteException {
		List<String[]> taskList = new ArrayList<>();
		for(Map.Entry<String, Task> entry : this.tasks.entrySet())
		{
			String[] postIt = new String[] {entry.getValue().getName(), entry.getValue().getStatus().getName()};
			taskList.add(postIt);
		}
		return taskList;
	}
	
	/**
	 * Fügt einen Task aus den Eingaben in der GUI hinzu.
	 * Synchronisiert um gleichzeitiges Schreiben zu verhindern.
	 * 
	 * @param name - Der Taskname.
	 * @param kommentar - Der Kommentar zum Task.
	 * @param user - Der User, der den Task angelegt hat.
	 */
	@Override
	public synchronized void taskHinzufügen(String name, String kommentar, User user)
	{
		Task task = new Task(name, kommentar, user);
		this.addTaskToHashMap(task);
	}

	/**
	 * Fügt einen User aus den Eingaben in der GUI hinzu.
	 * Synchronisiert um gleichzeitiges Schreiben zu verhindern.
	 * 
	 * @param user - Der User, der zum Projekt hinzugefügt wird. 
	 */
	@Override
	public synchronized void userHinzufügen(User user) 
	{
		this.addUserToHashMap(user);
	}
	
	/**
	 * Ändert die Projektbeschreibung.
	 * Synchronisiert um gleichzeitiges Schreiben zu verhindern.
	 * 
	 * @param beschreibung - Die neue Projektbeschreibung.
	 */
	@Override
	public synchronized void ändereBeschreibung(String beschreibung)
	{
		this.setBeschreibung(beschreibung);
	}
	
	/**
	 * Speichert das Projekt in der XML.
	 * Synchronisiert um gleichzeitiges Schreiben zu verhindern.
	 */
	@Override
	public synchronized void speichereProjekt()
	{
		//ToDo
	}
}
