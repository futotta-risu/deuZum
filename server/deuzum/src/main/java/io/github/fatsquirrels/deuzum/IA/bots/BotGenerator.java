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

import io.github.fatsquirrels.deuzum.IA.bots.types.AccountBot;
import io.github.fatsquirrels.deuzum.IA.bots.types.CleaningBot;
import io.github.fatsquirrels.deuzum.IA.bots.types.GroupBot;
import io.github.fatsquirrels.deuzum.IA.bots.types.MailBot;
import io.github.fatsquirrels.deuzum.IA.bots.types.ProyectBot;
import io.github.fatsquirrels.deuzum.IA.bots.types.ProyectTransactionBot;
import io.github.fatsquirrels.deuzum.IA.bots.types.TransactionBot;
import io.github.fatsquirrels.deuzum.IA.bots.types.UserBot;
import io.github.fatsquirrels.deuzum.utils.math.APair;

public class BotGenerator {

	public static BotBase generateBot(BotType botType, String name) {
		BotBase bot = null;
		// Set the Specific Class of the Bot
		switch(botType) {
			case MAIL:
				bot =  addMailBot(name);
				break;
			case CLEANING:
				bot = new CleaningBot(name);
				break;
			case USUARIO:
				bot = new UserBot(name, Integer.parseInt(JOptionPane.showInputDialog("Introduce la cantidad de usuarios")));
				break;
			case PROYECTO:
				bot = new ProyectBot(name, Integer.parseInt(JOptionPane.showInputDialog("Introduce la cantidad de Proyectos")));
				break;
			case PROYECTOTRANSACCION:
				bot = new ProyectTransactionBot(name, Integer.parseInt(JOptionPane.showInputDialog("Introduce la cantidad de Transaciones de Proyecto")));
				break;
			case GRUPO:
				bot = new GroupBot(name, Integer.parseInt(JOptionPane.showInputDialog("Introduce la cantidad de Grupos")));
				break;
			case ACCOUNT:
				bot = new AccountBot(name, Integer.parseInt(JOptionPane.showInputDialog("Introduce la cantidad de Cuentas")));
				break;
			case TRANSACCION:
				bot = new TransactionBot(name, Integer.parseInt(JOptionPane.showInputDialog("Introduce la cantidad de Transaciones")));
				break;
		}
		bot.setName(name);
		return bot;

	}
	
	public static BotBase addMailBot(String name) {
		
		List<String> destinatarios = new ArrayList<String>();
		FileFilter filter = new FileNameExtensionFilter("TXT File","txt");
		JFileChooser jfc = new JFileChooser();
		jfc.setDialogTitle("Elegir el archivo con los destinatarios");
		jfc.addChoosableFileFilter(filter);
		jfc.showOpenDialog(null);
		File selected = jfc.getSelectedFile();
		
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(selected));
			for(String line =br.readLine() ;  line!=null; line = br.readLine()) 
				destinatarios.add(line);
			 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}finally {			
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
		FileFilter filter2 = new FileNameExtensionFilter("TXT File","txt");
		JFileChooser jfc2 = new JFileChooser();
		jfc2.setDialogTitle("Elegir el archivo con el Mensaje");
		jfc2.addChoosableFileFilter(filter2);
		jfc2.showOpenDialog(null);
		File selected2 = jfc2.getSelectedFile();
		

		BufferedReader br2 = null;
		String cabecera = "";
		String cuerpo = "";
		
		try {
			br2 = new BufferedReader(new FileReader(selected2));
			cabecera =br2.readLine();
			
			for( String line = br2.readLine();  line!=null; line = br2.readLine()) 
				cuerpo += line + "\n";
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				br2.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		APair<String, String> mensaje = new APair<String,String>(cabecera, cuerpo);
		
		MailBot mb = new MailBot(name, mensaje, destinatarios);
		return mb;

	}
	
}
