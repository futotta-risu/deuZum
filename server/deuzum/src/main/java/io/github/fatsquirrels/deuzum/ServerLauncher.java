package io.github.fatsquirrels.deuzum;

import javax.swing.SwingUtilities;

import io.github.fatsquirrels.deuzum.visual.ServerHandlerFrame;
import io.github.fatsquirrels.deuzum.visual.ServerHandlerFrame2;

public class ServerLauncher {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new ServerHandlerFrame2());
			
	}
	
}
