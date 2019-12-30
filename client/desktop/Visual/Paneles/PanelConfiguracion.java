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
		setVisible(true);
		setSize(300,261);
		
		
		
		passwordField = new JPasswordField();
		passwordField.setBounds(107, 44, 154, 20);
		add(passwordField);
		
		lblCambiarContrasea = new JLabel("Cambiar contrase\u00F1a:");
		lblCambiarContrasea.setBounds(49, 8, 100, 14);
		add(lblCambiarContrasea);
		
		lblRepetirContrasea = new JLabel("Repetir contrase\u00F1a:");
		lblRepetirContrasea.setBounds(49, 75, 100, 14);
		add(lblRepetirContrasea);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(107, 111, 154, 20);
		add(passwordField_1);
		
		
	}
}
