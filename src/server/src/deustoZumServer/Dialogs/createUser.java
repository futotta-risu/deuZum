package deustoZumServer.Dialogs;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import deustoZumServer.ServerUserFunctionality;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.awt.event.ActionEvent;

public class createUser extends JDialog{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField tFUser;
	private JTextField tFPass;
	private JTextField tfPregSeg;
	private JTextField tFRes;
	public createUser(Connection conn) {
		
		setSize(400,300);
		setVisible(true);
		getContentPane().setLayout(null);
		
		JLabel lblUser = new JLabel("user");
		lblUser.setBounds(10, 11, 102, 14);
		getContentPane().add(lblUser);
		
		JLabel lblPass = new JLabel("pass");
		lblPass.setBounds(10, 36, 102, 14);
		getContentPane().add(lblPass);
		
		JLabel lblPregSeguridad = new JLabel("Preg Seguridad");
		lblPregSeguridad.setBounds(10, 73, 102, 14);
		getContentPane().add(lblPregSeguridad);
		
		JLabel lblRespuesta = new JLabel("Respuesta");
		lblRespuesta.setBounds(10, 102, 102, 14);
		getContentPane().add(lblRespuesta);
		
		tFUser = new JTextField();
		tFUser.setBounds(122, 8, 86, 20);
		getContentPane().add(tFUser);
		tFUser.setColumns(10);
		
		tFPass = new JTextField();
		tFPass.setBounds(122, 33, 86, 20);
		getContentPane().add(tFPass);
		tFPass.setColumns(10);
		
		tfPregSeg = new JTextField();
		tfPregSeg.setBounds(122, 70, 86, 20);
		getContentPane().add(tfPregSeg);
		tfPregSeg.setColumns(10);
		
		tFRes = new JTextField();
		tFRes.setBounds(122, 99, 86, 20);
		getContentPane().add(tFRes);
		tFRes.setColumns(10);
		
		JButton btnCrear = new JButton("Crear");
		btnCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO verificar que los datos sena validos (e.g. |tFUser|<33)
				ServerUserFunctionality.createUser(conn, tFUser.getText(), tFPass.getText(), tfPregSeg.getText(), tFRes.getText());
				dispose();
			}
		});
		btnCrear.setBounds(182, 227, 89, 23);
		getContentPane().add(btnCrear);
		
		
	}

}
