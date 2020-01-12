package io.github.fatsquirrels.deuzumDesktop;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.SwingUtilities;

import io.github.fatsquirrels.deuzumDesktop.visual.frame.VentanaLogin;

public class ClientLauncher {

public static void main(String[] args) {
		
		// Creamos el logger
		Logger logger = Logger.getLogger(VentanaLogin.class.getName()); 

		// Mensajes log utilizando log(Level level, String msg)
		logger.log(Level.INFO, "Mensage 1"); 
		logger.log(Level.WARNING, "Mensage 2"); 
    
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				new VentanaLogin();
				
			}
			
		});

	}
	
}
