package io.github.fatsquirrels.deuzum;

import javax.swing.SwingUtilities;


import io.github.fatsquirrels.deuzum.visual.ServerHandler;

/**
 * Clase main de la aplicacion
 *
 */
public class ServerLauncher {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new ServerHandler());
	}
	
}
