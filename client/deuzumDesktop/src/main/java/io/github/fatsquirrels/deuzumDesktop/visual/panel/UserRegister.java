package io.github.fatsquirrels.deuzumDesktop.visual.panel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import org.json.JSONObject;

import io.github.fatsquirrels.deuzumDesktop.net.MessageSender;
import io.github.fatsquirrels.deuzumDesktop.net.ServerRespuesta;
import io.github.fatsquirrels.deuzumDesktop.visual.frame.VentanaZum;
import io.github.fatsquirrels.deuzumDesktop.visual.layout.VerticalFlowLayout;
import io.github.fatsquirrels.deuzumDesktop.visual.panel.util.PairPanel;

public class UserRegister extends JPanel{

	private JLabel lblUser;
	private JLabel lblContrasea;
	private JLabel lblEmail;
	private JLabel lblRespuesta;
	private JTextField txtUser;
	private JTextField txtEmail;
	private JTextField txtRespuesta;
	private JComboBox comboPregunta;
	private JTextField txtName;
	private JTextField txtSurname;
	private JPasswordField txtContrasena;
	
	public UserRegister() {
		
		this.setLayout(new VerticalFlowLayout(10,10,10));
		
		lblUser = new JLabel("Usuario");
		txtUser = new JTextField();
		txtUser.setColumns(10);
		
		PairPanel userP = new PairPanel(lblUser,txtUser);
		add(userP);
		
		lblContrasea = new JLabel("Contrase\u00F1a");
		txtContrasena = new JPasswordField();
		txtContrasena.setColumns(10);
		PairPanel passP = new PairPanel(lblContrasea,txtContrasena);
		add(passP);
		
		lblEmail = new JLabel("Email");
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		PairPanel emailP = new PairPanel(lblEmail,txtEmail);
		add(emailP);
		
		JLabel lblNombre = new JLabel("Nombre");
		txtName = new JTextField();
		txtName.setColumns(10);
		PairPanel nameP = new PairPanel(lblNombre,txtName);
		add(nameP);
		
		
		JLabel lblApellidos = new JLabel("Apellidos");
		txtSurname = new JTextField();
		txtSurname.setColumns(10);
		PairPanel surnameP = new PairPanel(lblApellidos,txtSurname);
		add(surnameP);
		
		comboPregunta = new JComboBox();
		comboPregunta.setBounds(169, 227, 259, 30);
		comboPregunta.addItem("1");
		add(comboPregunta);
		
		
		lblRespuesta = new JLabel("Respuesta");
		txtRespuesta = new JTextField();
		txtRespuesta.setColumns(10);
		PairPanel pregP = new PairPanel(lblRespuesta,txtRespuesta);
		add(pregP);
		
		JButton registrar = new JButton("Registrar");
		registrar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				registrar();
				
			}
			
		});
		add(registrar);
	}
	
	public void registrar() {
		JSONObject data = new JSONObject();
		data.put("tableName", "usuario");
		data.put("usuario", txtUser.getText());
		data.put("pass", txtContrasena.getText());
		data.put("preg_seguridad", String.valueOf(comboPregunta.getSelectedIndex()+1));
		data.put("resp_seguridad", txtRespuesta.getText());
		
		Thread t1 = new Thread(new MessageSender("addData", data));
		t1.start();
		try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
		
		if(ServerRespuesta.result.equals("1")) {
			new VentanaZum(txtUser.getText());
			SwingUtilities.getWindowAncestor(this).setVisible(false);
		}else {
			System.out.println("Fallo log" + ServerRespuesta.result );
		}
		
		ServerRespuesta.result = "-1";
		
	}
}
