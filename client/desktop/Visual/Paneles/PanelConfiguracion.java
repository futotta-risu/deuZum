package Paneles;

import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JTable;

import mensajeRespuesta.MessageSender;
import mensajeRespuesta.ServerRespuesta;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelConfiguracion extends JPanel {
	private JPasswordField passwordField;
	private JLabel lblCambiarContrasea = new JLabel();
	private JPasswordField passwordField_1;
	private JLabel lblRepetirContrasea = new JLabel();
	
	public PanelConfiguracion() {
		
		
		setLayout(new VerticalFlowLayout(10,10,10));

		lblCambiarContrasea = new JLabel("Cambiar contrase\u00F1a:");
		add(lblCambiarContrasea);
		
		passwordField = new JPasswordField();
		add(passwordField);
		
		
		
		lblRepetirContrasea = new JLabel("Repetir contrase\u00F1a:");
		add(lblRepetirContrasea, BorderLayout.NORTH);
		
		passwordField_1 = new JPasswordField();
		add(passwordField_1);
		JButton btnenviar=new JButton("Enviar");
		btnenviar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String cambiarContraseña = lblCambiarContrasea.getText();
				String repetirContraseña = lblRepetirContrasea.getText();
				
				
				
				JSONObject data = new JSONObject();
				
				data.put("cambiar contraseña", cambiarContraseña);
				data.put("repetir contraseña", repetirContraseña);
				
				Thread t1 = new Thread(new MessageSender("logUser", data));
				t1.start();
				try {
		            Thread.sleep(300);
		        } catch (InterruptedException e) {
		            e.printStackTrace();
		        }
				
				ServerRespuesta.result = "-1";
			
			}
		});

		
	}
}
