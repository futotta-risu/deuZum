package io.github.fatsquirrels.deuzum.IA.Bots;

public abstract class BotBase {

	int id;
	String name;
	
	boolean isActive;
	
	public BotBase() {
		
	}
	
	// Getters
	public String getName() {
		return name;
	}
	
	// Setters
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getID() {
		return this.id;
	}
	
	
	public void setActive(boolean active) {
		this.isActive = active;
	}
	public boolean isActive() {
		return this.isActive;
	}
	
	public abstract void execute();
	public abstract void stop();
	public abstract void kill();
	
}
