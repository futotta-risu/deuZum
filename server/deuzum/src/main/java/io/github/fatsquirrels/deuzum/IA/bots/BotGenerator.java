package io.github.fatsquirrels.deuzum.IA.bots;

import javax.swing.JOptionPane;

/**
 * Clase encargada de generar los Bots
 * @see BotType
 */
public class BotGenerator {

	/**
	 * Metodo estatico que genera un bot
	 * @param botType Tipo de Bot
	 * @param name Nombre del Bot
	 * @return BotBase Bot generado
	 */
	public static BotBase generateBot(BotType botType, String name) {
		BotBase bot;
		if(botType == BotType.MAIL || botType == BotType.CLEANING) bot = botType.createBot(0);
		else bot = botType.createBot(Integer.parseInt(JOptionPane.showInputDialog("Introduce la cantidad de Transaciones")));
		bot.setName(name);
		return bot;
	}
	
}
