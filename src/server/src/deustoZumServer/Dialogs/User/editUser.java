package deustoZumServer.Dialogs.User;

import javax.swing.JDialog;

import com.mysql.jdbc.Connection;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class editUser extends JDialog{

	private static final long serialVersionUID = 1L;
	private JTextField txtIDUser;
	private JTextField txtUser;
	private JTextField txtPass;
	private JTextField txtP_Seguridad;
	private JTextField txtRespuesta;
	private JTextField txtNombre;
	private JTextField txtApellidos;
	private JTextField txtTelefono;
	private JTextField txtEmail;
	private JTextField txtF_Nacimiento;
	private JTextField txtSexo;
	
	private JLabel lblIntroducirIdUsuario;
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
	
	private JButton btnBuscarUsuario;
	private JButton btnGuardarCambios;
	
	/**
	 * Crea un objeto de editUser, este contiene un Dialogo que sirve para buscar un usuario por su ID 
	 * y modificar sus datos
	 * @param Connection
	 */	
	public editUser(Connection c) {
		setSize(550,300);
		setTitle("Editar usuario");
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		lblIntroducirIdUsuario = new JLabel("Introducir ID usuario");
		lblIntroducirIdUsuario.setBounds(47, 19, 139, 16);
		getContentPane().add(lblIntroducirIdUsuario);
		
		txtIDUser = new JTextField();
		txtIDUser.setBounds(208, 14, 130, 26);
		getContentPane().add(txtIDUser);
		txtIDUser.setColumns(10);
		
		btnBuscarUsuario = new JButton("Buscar Usuario");
		btnBuscarUsuario.setBounds(377, 14, 117, 29);
		getContentPane().add(btnBuscarUsuario);
		
		lblUsuario = new JLabel("User");
		lblUsuario.setBounds(31, 62, 61, 16);
		getContentPane().add(lblUsuario);
		
		lblPass = new JLabel("Pass");
		lblPass.setBounds(31, 93, 61, 16);
		getContentPane().add(lblPass);
		
		lblRespuesta = new JLabel("Respuesta");
		lblRespuesta.setBounds(31, 159, 77, 16);
		getContentPane().add(lblRespuesta);
		
		lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(31, 200, 61, 16);
		getContentPane().add(lblNombre);
		
		lblApellidos = new JLabel("Apellidos");
		lblApellidos.setBounds(31, 228, 61, 16);
		getContentPane().add(lblApellidos);
		
		lblPregSeguridad = new JLabel("Preg. Seguridad");
		lblPregSeguridad.setBounds(6, 131, 102, 16);
		getContentPane().add(lblPregSeguridad);
		
		lblTelefono = new JLabel("Telefono");
		lblTelefono.setBounds(277, 93, 61, 16);
		getContentPane().add(lblTelefono);
		
		lblEmail = new JLabel("Email");
		lblEmail.setBounds(287, 131, 61, 16);
		getContentPane().add(lblEmail);
		
		lblFNacimiento = new JLabel("F. Nacimiento");
		lblFNacimiento.setBounds(261, 159, 87, 16);
		getContentPane().add(lblFNacimiento);
		
		lblSexo = new JLabel("Sexo");
		lblSexo.setBounds(287, 200, 61, 16);
		getContentPane().add(lblSexo);
		
		txtUser = new JTextField();
		txtUser.setBounds(92, 57, 130, 26);
		getContentPane().add(txtUser);
		txtUser.setColumns(10);
		
		txtPass = new JTextField();
		txtPass.setBounds(92, 90, 130, 26);
		getContentPane().add(txtPass);
		txtPass.setColumns(10);
		
		txtP_Seguridad = new JTextField();
		txtP_Seguridad.setBounds(117, 126, 130, 26);
		getContentPane().add(txtP_Seguridad);
		txtP_Seguridad.setColumns(10);
		
		txtRespuesta = new JTextField();
		txtRespuesta.setBounds(117, 154, 130, 26);
		getContentPane().add(txtRespuesta);
		txtRespuesta.setColumns(10);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(104, 195, 130, 26);
		getContentPane().add(txtNombre);
		txtNombre.setColumns(10);
		
		txtApellidos = new JTextField();
		txtApellidos.setBounds(104, 228, 130, 26);
		getContentPane().add(txtApellidos);
		txtApellidos.setColumns(10);
		
		txtTelefono = new JTextField();
		txtTelefono.setBounds(365, 88, 130, 26);
		getContentPane().add(txtTelefono);
		txtTelefono.setColumns(10);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(360, 126, 130, 26);
		getContentPane().add(txtEmail);
		txtEmail.setColumns(10);
		
		txtF_Nacimiento = new JTextField();
		txtF_Nacimiento.setBounds(360, 154, 130, 26);
		getContentPane().add(txtF_Nacimiento);
		txtF_Nacimiento.setColumns(10);
		
		txtSexo = new JTextField();
		txtSexo.setBounds(360, 195, 130, 26);
		getContentPane().add(txtSexo);
		txtSexo.setColumns(10);
		
		btnGuardarCambios = new JButton("Guardar Cambios");
		btnGuardarCambios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnGuardarCambios.setBounds(289, 228, 205, 29);
		getContentPane().add(btnGuardarCambios);
	
	}
}
