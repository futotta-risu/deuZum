package Paneles;

import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JEditorPane;

public class PanelConfiguracion extends JPanel {
	private JPasswordField passwordField;
	private JLabel lblCambiarContrasea = new JLabel();
	private JPasswordField passwordField_1;
	private JLabel lblRepetirContrasea = new JLabel();
	
	public PanelConfiguracion() {
		setLayout(null);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(191, 60, 109, 20);
		add(passwordField);
		
		lblCambiarContrasea = new JLabel("Cambiar contrase\u00F1a:");
		lblCambiarContrasea.setBounds(26, 63, 100, 14);
		add(lblCambiarContrasea);
		
		lblRepetirContrasea = new JLabel("Repetir contrase\u00F1a:");
		lblRepetirContrasea.setBounds(26, 98, 100, 14);
		add(lblRepetirContrasea);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(191, 95, 109, 20);
		add(passwordField_1);
		
		
	}
}
