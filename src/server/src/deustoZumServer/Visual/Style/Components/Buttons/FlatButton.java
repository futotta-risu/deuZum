package deustoZumServer.Visual.Style.Components.Buttons;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.Icon;
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
	public FlatButton(Icon icono) {
		super(icono);
		setStyle();
		
	}
	public FlatButton(Icon icono, int size) {
		super(icono);
		setStyle();
		setPreferredSize(new Dimension(size,size));
		
	}
	public void setStyle() {
		setBackground(Color.WHITE);
		setBorderPainted(true);
		setFocusPainted(false);
		setContentAreaFilled(true);
		
	}
	

	
}
