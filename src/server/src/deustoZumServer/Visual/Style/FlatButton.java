package deustoZumServer.Visual.Style;

import java.awt.Color;

import javax.swing.JButton;

/**
 * Boton con estilo Flat.
 */
public class FlatButton extends JButton {

	public FlatButton() {
		setStyle();
	}
	public FlatButton(String text) {
		setStyle();
		setText(text);
	}
	public void setStyle() {
		setBackground(Color.WHITE);
		setBorderPainted(true);
		setFocusPainted(false);
		setContentAreaFilled(true);
	}
	
}
