package io.github.fatsquirrels.deuzum.IA.bots;



/**
 * Enum de los tipos de Bots disponibles.
 */
public enum BotType {
	CLEANING("Limpieza","robot"), MAIL("Mail","bot"), ACCOUNT("Cuenta","creditCard"),
	USUARIO("Usuario","cyborg"), PROYECTO("Proyecto","proyecto"), PROYECTOTRANSACCION("Proyecto Transaccion","proyectTransaction"), 
	TRANSACCION("Transaccion","moneyBot"), GRUPO("Grupo","group");
	
	private final String botClass;
	private final String iconName;
	private BotType(String name, String iconNamet) {
		this.botClass = name;
		this.iconName = iconNamet;
	}
	public String getBotClass() {
		return this.botClass;
	}
	public String getIconName() {
		return this.iconName;
	}
}
