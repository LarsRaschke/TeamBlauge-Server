package server;

import model.Task;

public class ServerController {

	Task testTask;
	
	public ServerController(String s, String t){
		testTask = new Task(s, t, this);
	}
	
	public void printSomething(){
		System.out.println("Something");
	}
	
	
}
