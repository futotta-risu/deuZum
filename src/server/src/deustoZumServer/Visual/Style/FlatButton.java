package deustoZumServer.Visual.Style;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.border.Border;

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
	public FlatButton(Icon icono) {
		super(icono);
		setStyle();
		
	}
	public void setStyle() {
		setBackground(Color.WHITE);
		setBorderPainted(true);
		setFocusPainted(false);
		setContentAreaFilled(true);
		
	}
	

	
}
