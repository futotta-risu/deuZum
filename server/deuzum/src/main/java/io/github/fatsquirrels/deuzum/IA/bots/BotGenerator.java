package io.github.fatsquirrels.deuzum.IA.bots;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import io.github.fatsquirrels.deuzum.IA.bots.types.MailBot;
import io.github.fatsquirrels.deuzum.utils.math.APair;

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
	
	/**
	 * Metodo que crea un MailBot
	 * @param name Nombre del MailBot
	 * @return MailBot generado
	 */
	
	
}
