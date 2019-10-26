package deustoZumServer.Visual.Style;

import java.awt.Color;

import javax.swing.JButton;

public class ButtonStyle {

	public static JButton getFlatButton(String texto) {
		JButton styleButton = new JButton(texto);
		styleButton.setBackground(Color.WHITE);
		styleButton.setBorderPainted(true);
		styleButton.setFocusPainted(false);
		styleButton.setContentAreaFilled(true);
		return styleButton;
	}
	
}
