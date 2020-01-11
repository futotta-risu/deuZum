package io.github.fatsquirrels.deuzum.IA.bots;

/**
 * Clase abstracta que contiene las propiedades base de los bots
 */
public abstract class BotBase {

	int id;
	public String name;	
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
	public abstract void stop(long tiempo);
	public abstract void kill();
	
	
	
}
