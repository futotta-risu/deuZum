package Paneles;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import loginZum.VentanaZum;
import mensajeRespuesta.MessageSender;
import mensajeRespuesta.ServerRespuesta;

public class UserLogin extends JPanel{

	
	private JTextField txtUsuario;
	private JPasswordField txtContrasenia;
	
	private JButton btnLogin;
	private JButton btnForgotPass;
	
	public UserLogin() {
		
		this.setLayout(new VerticalFlowLayout(10,10,10));
		
		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setFont(new Font("Nirmala UI", Font.BOLD, 14));
		JLabel lblContrasenia = new JLabel("Contrase\u00f1a:");
		lblContrasenia.setFont(new Font("Nirmala UI", Font.BOLD, 14));
		
		
		txtUsuario = new JTextField(20);
		txtContrasenia = new JPasswordField(20);
		
		PairPanel userP = new PairPanel(lblUsuario,txtUsuario );
		PairPanel passP = new PairPanel(lblContrasenia,txtContrasenia );
		add(userP);
		add(passP);
		
		
		btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				login();
			}
		});
		add(btnLogin);
		btnForgotPass = new JButton("He olvidado mi Contrase\u00f1a");
		add(btnForgotPass);
	}
	
	
	public void login() {
		String usuario = txtUsuario.getText();
		String contrasenia = txtContrasenia.getText();
		
		
		JSONObject data = new JSONObject();
		
		data.put("user", usuario);
		data.put("pass", contrasenia);
		
		Thread t1 = new Thread(new MessageSender("logUser", data));
		t1.start();
		try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
		
		if(ServerRespuesta.result.equals("1")) {
			new VentanaZum(usuario);
			SwingUtilities.getWindowAncestor(this).setVisible(false);
		}else {
			System.out.println("Fallo log" + ServerRespuesta.result );
		}
		
		ServerRespuesta.result = "-1";
	}
	
}
