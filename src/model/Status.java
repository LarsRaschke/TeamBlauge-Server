package model;

/**
 * Model-Klasse für den Projektstatus.
 */
public class Status {
	
	private Status vorgaenger;
	private Status nachfolger;
	private String name; 

	/**
	 * Konstruktor.
	 * 
	 * @param name - Der Statusname.
	 */
	public Status(String name) {
		this.name = name;
		this.vorgaenger = null;
		this.nachfolger = null;
	}
	
	/**
	 * Getter-Methode.
	 * 
	 * @return Den Vorgänger-Status.
	 */
	public Status getVorgaenger() {
		return vorgaenger;
	}
	
	/**
	 * Setter-Methode.
	 * 
	 * @param vorgaenger - Der Vorgänger-Status.
	 */
	public void setVorgaenger(Status vorgaenger) {
		this.vorgaenger = vorgaenger;
	}
	
	/**
	 * Getter-Methode.
	 * 
	 * @return Den Nachfolger-Status.
	 */
	public Status getNachfolger() {
		return nachfolger;
	}
	
	/**
	 * Setter-Methode.
	 * 
	 * @param nachfolger - Der Nachfolger-Status.
	 */
	public void setNachfolger(Status nachfolger) {
		this.nachfolger = nachfolger;
	}
	
	/**
	 * Getter-Methode.
	 * 
	 * @return Den Statusnamen.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Setter-Methode.
	 * 
	 * @param name - Der Statusname.
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public boolean equals(Object object)
	{
		if(object == null)
		{
			return false;
		}
		else if(object instanceof Status == false)
		{
			return false;
		}
		else if(this == object)
		{
			return true;
		}
		
		return this.name.equals(((Status) object).getName());
	}
}
