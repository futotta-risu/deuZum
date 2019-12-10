package Paneles;

import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JTable;

public class PanelConfiguracion extends JPanel {
	private JPasswordField passwordField;
	private JLabel lblCambiarContrasea = new JLabel();
	
	
	public PanelConfiguracion() {
		setLayout(null);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(191, 60, 109, 20);
		add(passwordField);
		
		lblCambiarContrasea = new JLabel("Cambiar contrase\u00F1a:");
		lblCambiarContrasea.setBounds(26, 63, 100, 14);
		add(lblCambiarContrasea);
		
	}
}
