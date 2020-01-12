package io.github.fatsquirrels.deuzum.IA.bots;

import java.lang.reflect.InvocationTargetException;

import io.github.fatsquirrels.deuzum.IA.bots.types.*;

/**
 * Enum de los tipos de Bots disponibles.
 */
public enum BotType {
	
	
	CLEANING("Limpieza","robot", CleaningBot.class) , 
	MAIL("Mail","bot", MailBot.class),
	ACCOUNT("Cuenta","creditCard", AccountBot.class),
	USUARIO("Usuario","cyborg", UserBot.class),
	PROYECTO("Proyecto","proyecto", ProyectBot.class),
	PROYECTOTRANSACCION("Proyecto Transaccion","proyectTransaction", ProyectTransactionBot.class), 
	TRANSACCION("Transaccion","moneyBot", TransactionBot.class),
	GRUPO("Grupo","group", GroupBot.class);
	
	private final String botClass;
	private final String iconName;
	private Class<? extends BotBase> classBot;
	
	private BotType(String name, String iconNamet, Class<? extends BotBase> classBotT) {
		this.botClass = name;
		this.iconName = iconNamet;
		this.classBot = classBotT;
	}
	public String getBotClass() {
		return this.botClass;
	}
	public String getIconName() {
		return this.iconName;
	}
	public BotBase createBot(int val) {
		try {
			
			return this.classBot.getConstructor(String.class,Integer.class).newInstance(this.botClass, val);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		return null;
	}
}
