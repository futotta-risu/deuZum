package Paneles;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextPane;

public class PanelLogin extends JPanel {
	private JPasswordField passwordField;
	private JLabel lblContrasea = new JLabel();
	private JLabel lblNombreUsuario= new JLabel();
	private JTextPane textPaneNombreUsuario = new JTextPane() ;
	private JRadioButton rdbtnIniciarSesion = new JRadioButton();
	
	public PanelLogin() {
		setLayout(new VerticalFlowLayout(10,10,10));
		
		lblNombreUsuario = new JLabel("Nombre usuario:");
		add(lblNombreUsuario);
		
		textPaneNombreUsuario = new JTextPane();
		add(textPaneNombreUsuario);
		
		lblContrasea = new JLabel("Contraseña:");
		add(lblContrasea);
			
		passwordField = new JPasswordField();
		add(passwordField);
		
		rdbtnIniciarSesion = new JRadioButton("Iniciar sesion:");
		add(rdbtnIniciarSesion);
	}
}
