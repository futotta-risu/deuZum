package deustoZumServer.Visual.Style;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.border.Border;

/**
 * Boton con estilo Flat.
 */
public class FlatButton extends JButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
