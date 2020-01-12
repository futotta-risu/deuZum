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
	public static BotBase addMailBot(String name, int temp) {
		
		List<String> destinatarios = new ArrayList<String>();
		FileFilter filter = new FileNameExtensionFilter("TXT File","txt");
		JFileChooser jfc = new JFileChooser();
		jfc.setDialogTitle("Elegir el archivo con los destinatarios");
		jfc.addChoosableFileFilter(filter);
		jfc.showOpenDialog(null);
		File selected = jfc.getSelectedFile();
		
		try(BufferedReader br = new BufferedReader(new FileReader(selected))){
			for(String line =br.readLine() ;  line!=null; line = br.readLine()) 
				if(!line.trim().isEmpty())
					destinatarios.add(line);
			 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		FileFilter filter2 = new FileNameExtensionFilter("TXT File","txt");
		JFileChooser jfc2 = new JFileChooser();
		jfc2.setDialogTitle("Elegir el archivo con el Mensaje");
		jfc2.addChoosableFileFilter(filter2);
		jfc2.showOpenDialog(null);
		File selected2 = jfc2.getSelectedFile();
		

		String cabecera = "";
		String cuerpo = "";
		
		try(BufferedReader br = new BufferedReader(new FileReader(selected2))) {
			cabecera =br.readLine();
			
			for( String line = br.readLine();  line!=null; line = br.readLine()) 
				cuerpo += line + "\n";
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		APair<String, String> mensaje = new APair<String,String>(cabecera, cuerpo);
		
		MailBot mb = new MailBot(name, mensaje, destinatarios);
		return mb;

	}
	
}
