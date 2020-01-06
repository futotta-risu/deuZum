package io.github.fatsquirrels.deuzum;

import javax.swing.SwingUtilities;


import io.github.fatsquirrels.deuzum.visual.ServerHandler;

public class ServerLauncher {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new ServerHandler());
			
	}
	
}
