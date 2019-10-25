package deustoZumServer.Dialogs.User;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import deustoZumServer.ServerUserFunctionality;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.awt.event.ActionEvent;

public class createUser extends JDialog{

	private static final long serialVersionUID = 1L;
	private JTextField tFUser;
	private JTextField tFPass;
	private JTextField tfPregSeg;
	private JTextField tFRes;
	private JTextField txtNombre;
	private JTextField txtApellidos;
	private JTextField txtTelefono;
	private JTextField txtEmail;
	private JTextField txtF_Nacimiento;
	private JTextField txtSexo;
	
	private JLabel lblUsuario;
	private JLabel lblPass;
	private JLabel lblPregSeguridad;
	private JLabel lblRespuesta;
	private JLabel lblNombre;
	private JLabel lblApellidos;
	private JLabel lblTelefono;
	private JLabel lblEmail;
	private JLabel lblFNacimiento;
	private JLabel lblSexo;
	
	private JButton btnCrear;
	
	/**
	 * Crea un objeto de createUser que contiene un Dialogo que permite crear un usuario
	 * @param Connection
	 */
	public createUser(Connection conn) {
		// TODO Actualizar esta ventana respecto a los nuevos parametros de la base de datos
		setSize(533,300);
		setVisible(true);
		getContentPane().setLayout(null);
		
		lblUsuario = new JLabel("user");
		lblUsuario.setBounds(10, 11, 102, 14);
		getContentPane().add(lblUsuario);
		
		lblPass = new JLabel("pass");
		lblPass.setBounds(10, 36, 102, 14);
		getContentPane().add(lblPass);
		
		lblPregSeguridad = new JLabel("Preg Seguridad");
		lblPregSeguridad.setBounds(10, 81, 102, 14);
		getContentPane().add(lblPregSeguridad);
		
		lblRespuesta = new JLabel("Respuesta");
		lblRespuesta.setBounds(10, 124, 102, 14);
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
		tfPregSeg.setBounds(122, 78, 86, 20);
		getContentPane().add(tfPregSeg);
		tfPregSeg.setColumns(10);
		
		tFRes = new JTextField();
		tFRes.setBounds(122, 121, 86, 20);
		getContentPane().add(tFRes);
		tFRes.setColumns(10);
		
		
		btnCrear.setBounds(216, 228, 89, 23);
		getContentPane().add(btnCrear);
		
		lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(10, 165, 61, 16);
		getContentPane().add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(122, 160, 86, 26);
		getContentPane().add(txtNombre);
		txtNombre.setColumns(10);
		
		lblApellidos = new JLabel("Apellidos");
		lblApellidos.setBounds(10, 193, 61, 16);
		getContentPane().add(lblApellidos);
		
		txtApellidos = new JTextField();
		txtApellidos.setBounds(122, 188, 86, 26);
		getContentPane().add(txtApellidos);
		txtApellidos.setColumns(10);
		
		lblTelefono = new JLabel("Telefono");
		lblTelefono.setBounds(271, 53, 61, 16);
		getContentPane().add(lblTelefono);
		
		txtTelefono = new JTextField();
		txtTelefono.setBounds(344, 48, 130, 26);
		getContentPane().add(txtTelefono);
		txtTelefono.setColumns(10);
		
		lblEmail = new JLabel("Email");
		lblEmail.setBounds(291, 103, 61, 16);
		getContentPane().add(lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(344, 96, 130, 26);
		getContentPane().add(txtEmail);
		txtEmail.setColumns(10);
		
		lblFNacimiento = new JLabel("Fecha de Nacimiento");
		lblFNacimiento.setBounds(216, 139, 136, 32);
		getContentPane().add(lblFNacimiento);
		
		txtF_Nacimiento = new JTextField();
		txtF_Nacimiento.setBounds(368, 142, 130, 26);
		getContentPane().add(txtF_Nacimiento);
		txtF_Nacimiento.setColumns(10);
		
		lblSexo = new JLabel("Sexo");
		lblSexo.setBounds(291, 193, 61, 16);
		getContentPane().add(lblSexo);
		
		txtSexo = new JTextField();
		txtSexo.setBounds(344, 188, 130, 26);
		getContentPane().add(txtSexo);
		txtSexo.setColumns(10);
		
		btnCrear = new JButton("Crear");
		btnCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO verificar que los datos sena validos (e.g. |tFUser|<33)
				ServerUserFunctionality.createUser(conn, new String[] {tFUser.getText(), tFPass.getText(), tfPregSeg.getText(), tFRes.getText()});
				dispose();
			}
		});
		
		
	}
}
