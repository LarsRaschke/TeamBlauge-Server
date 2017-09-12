package model;

import java.rmi.RemoteException;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.interfaces.RMI_Projekt;

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

	public Projekt(User u, String projektname, String beschreibung) {
		this.ersteller = u;
		this.projektname = projektname;
		this.beschreibung = beschreibung;
		users.put(u.getNutzername(), u);
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public User getErsteller() {
		return ersteller;
	}

	public void setErsteller(User ersteller) {
		this.ersteller = ersteller;
	}
	
	public String getProjektname() {
		return projektname;
	}

	public void setProjektname(String bezeichnung) {
		this.projektname = bezeichnung;
	}

	public String getBeschreibung() {
		return beschreibung;
	}

	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}
	
	public HashMap<String, Task> getTasks() {
		return tasks;
	}
	
	public void setTasks(HashMap<String, Task> tasks) {
		this.tasks = tasks;
	}
	
	public HashMap<String, User> getUsers() {
		return users;
	}
	
	public void setUsers(HashMap<String, User> users) {
		this.users = users;
	}

	public ZonedDateTime getLetzteAenderung() {
		return letzteAenderung;
	}

	public void setLetzteAenderung(ZonedDateTime letzteAenderung) {
		this.letzteAenderung = letzteAenderung;
	}

	public ZonedDateTime getErstellungsDatum() {
		return erstellungsDatum;
	}

	public void setErstellungsDatum(ZonedDateTime erstellungsDatum) {
		this.erstellungsDatum = erstellungsDatum;
	}

	public Statusliste getStatusliste() {
		return statusliste;
	}
	
	public void setStatusliste(Statusliste statusliste) {
		this.statusliste = statusliste;
	}

	/**
	 * 
	 * @param task
	 */
	public void addTaskToHashMap(Task task){
		tasks.put(task.getName(), task);
	}
	
	/**
	 * 
	 * @param task
	 */
	public void deleteTaskFromHashMap(Task task){
		tasks.remove(task.getName());
	}
	
	/**
	 * 
	 * @param user
	 */
	public void addUserToHashMap(User user){
		users.put(user.getNutzername(), user);
	}
	
	/**
	 * 
	 * @param user
	 */
	public void deleteUserFromHashMap(User user){
		users.remove(user.getNutzername());
	}
	
	/**
	 * 
	 * @param tagname
	 * @return returns a HashMap of all tasks that have the tagname as tag
	 */
	public HashMap<String, Task> getTasksOfTag(String tagname)
	{
		HashMap<String, Task> taskList = new HashMap<String, Task>();
		try{
			for(int i=0; i<this.getTasks().size(); i++)
			{
				for(int j=0; j<this.getTasks().get(i).getTags().size(); j++)
				{
					if(this.getTasks().get(i).getTags().get(j) == tagname)
					{
						//System.out.println("Tag \"" + tagname + "\" gefunden in " + this.getTasks().get(i).getName() + ".");
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
	 * 
	 * @param name
	 * @param kommentar
	 * @param u
	 * adds a task to the projects HashMap with name, comment and user
	 */
	public void taskHinzufügen(String name, String kommentar, User u)
	{
		Task task = new Task(name,kommentar,u);
		this.addTaskToHashMap(task);
	}
	/**
	 * @return returns a list of users
	 */
	public List<User> userListe() throws RemoteException {
		List<User> userList = new ArrayList<>();
		for(Map.Entry<String, User> entry : this.users.entrySet())
		{
			userList.add(entry.getValue());
		}
		return userList;
	}
	/**
	 * @return returns a list of tasks
	 */
	public List<String[]> taskListe() throws RemoteException {
		List<String[]> taskList = new ArrayList<>();
		for(Map.Entry<String, Task> entry : this.tasks.entrySet())
		{
			String[] postIt = new String[] {entry.getValue().getName(), entry.getValue().getStatus().getName()};
			taskList.add(postIt);
		}
		return taskList;
	}
}
