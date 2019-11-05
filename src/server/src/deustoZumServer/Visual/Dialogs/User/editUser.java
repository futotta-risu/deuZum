package deustoZumServer.Visual.Dialogs.User;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import deustoZumServer.ServerUserFunctionality;
import deustoZumServer.Algorithms.TextFunctions;
import deustoZumServer.Database.GeneralSQLFunctions;

import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.awt.Color;
import java.awt.event.ActionEvent;

public class editUser extends JDialog{

	private static final long serialVersionUID = 1L;
	private JTextField txtIDUser;
	private JTextField txtUser;
	private JTextField txtPass;
	private JComboBox<String> comboPregSeg;
	private JTextField txtRespuesta;
	private JTextField txtNombre;
	private JTextField txtApellidos;
	private JTextField txtTelefono;
	private JTextField txtEmail;
	private JTextField txtF_Nacimiento;
	private JTextField txtSexo;
	private JTextField txtDireccion;
	private JComboBox<String> comboSexo;

	
	private JLabel lblIntroducirIdUsuario,lblUsuario;
	private JLabel lblPass;
	private JLabel lblPregSeguridad;
	private JLabel lblRespuesta;
	private JLabel lblNombre;
	private JLabel lblApellidos;
	private JLabel lblTelefono;
	private JLabel lblEmail;
	private JLabel lblFNacimiento;
	private JLabel lblSexo;
	private JLabel lblDireccion;
	
	private JButton btnBuscarUsuario;
	private JButton btnGuardarCambios;
	
	private int userID;
	
	/**
	 * Crea un objeto de editUser, este contiene un Dialogo que sirve para buscar un usuario por su ID 
	 * y modificar sus datos
	 * @param Connection
	 */	
	public editUser(Connection connection) {
		setSize(550,300);
		setTitle("Editar usuario");
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		
		lblIntroducirIdUsuario = new JLabel("Introducir ID usuario");
		lblIntroducirIdUsuario.setBounds(47, 19, 139, 16);
		getContentPane().add(lblIntroducirIdUsuario);
		
		txtIDUser = new JTextField();
		txtIDUser.setBounds(208, 14, 130, 26);
		getContentPane().add(txtIDUser);
		txtIDUser.setColumns(10);
		
		
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
		
		comboPregSeg = new JComboBox<String>();
		comboPregSeg.addItem("¿Cual era tu apodo de pequeñ@?");
		comboPregSeg.addItem("¿En que ciudad conociste a tu pareja?");
		comboPregSeg.addItem("¿Cual era el nombre de tu mejor amig@ de la infacia?");
		comboPregSeg.addItem("¿Cual era el nombre de tu primera mascota?");
		comboPregSeg.addItem("¿Cual era el heroe de tu infacia?");
		comboPregSeg.addItem("¿En que ciudad te gustaria jubilarte?");
		comboPregSeg.setBounds(117, 126, 130, 26);
		getContentPane().add(comboPregSeg);
		
		
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
		
		comboSexo = new JComboBox<String>();
		comboSexo.setBounds(360, 195, 130, 26);
		comboSexo.addItem("Masculino");
		comboSexo.addItem("Femenino");
		comboSexo.addItem("Otro");
		getContentPane().add(comboSexo);
		

		btnBuscarUsuario = new JButton("Buscar Usuario");
		btnBuscarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				userID = Integer.parseInt(txtIDUser.getText());
				//GeneralSQLFunctions.getEntryFromDatabase(connection, "usuario", column, conditions)
			}
		});
		btnBuscarUsuario.setBounds(377, 14, 117, 29);
		getContentPane().add(btnBuscarUsuario);
	
		btnGuardarCambios = new JButton("Guardar Cambios");
		btnGuardarCambios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean nulo = false;
				//TODO Comprobar que el usser,email y tlfn no esta en uso
				if(txtUser.getText().equals("")){
					lblUsuario.setBackground(Color.RED);
					nulo = true;
				}else if(txtPass.getText().equals("")) {
					lblPass.setBackground(Color.RED);
					nulo = true;
				}else if(txtRespuesta.getText().equals("")) {
					lblRespuesta.setBackground(Color.RED);
					nulo = true;
				}else if(txtNombre.getText().equals("")) {
					lblNombre.setBackground(Color.RED);
					nulo = true;
				}else if(txtApellidos.getText().equals("")) {
					lblApellidos.setBackground(Color.RED);
					nulo = true;
				}else if(txtTelefono.getText().equals("")) {
					lblTelefono.setBackground(Color.RED);
					nulo = true;			
				}else if(txtEmail.getText().equals("")) {
					lblEmail.setBackground(Color.RED);
					nulo = true;
				}else if(txtF_Nacimiento.getText().equals("")) {
					lblFNacimiento.setBackground(Color.RED);
					nulo = true;
				}else if(txtDireccion.getText().equals("")) {
					lblDireccion.setBackground(Color.RED);
					nulo = true;
				}else if(nulo){
					JOptionPane.showMessageDialog(null, "Se ha introducido algun dato nulo", "ERROR", 0);
				}else {
					boolean format = true;
					String place = "";
					if(!TextFunctions.endsWithMail(txtEmail.getText())) {
						lblEmail.setBackground(Color.RED);
						format = false;
						place = "Email";
					}else if(!TextFunctions.dateChecker(txtF_Nacimiento.getText())) {
						lblFNacimiento.setBackground(Color.RED);
						format = false;
						place = "Fecha de Nacimiento";
					}else if(!format){
						JOptionPane.showMessageDialog(null, "Mal formato de datos en: " + place);
					}else{
						boolean length = true;
						if(txtUser.getText().length()>32) {
							lblUsuario.setBackground(Color.RED);
							length = false;
						}else if(txtPass.getText().length()>32){
							lblPass.setBackground(Color.RED);
							length = false;
						}else if(txtRespuesta.getText().length()>32) {
							lblRespuesta.setBackground(Color.RED);
							length = false;
						}else if(txtNombre.getText().length()>15) {
							lblNombre.setBackground(Color.RED);
							length = false;
						}else if(txtApellidos.getText().length()>15) {
							lblApellidos.setBackground(Color.RED);
							length = false;
						}else if(txtTelefono.getText().length()>9) {
							lblTelefono.setBackground(Color.RED);
							length = false;
						}else if(txtEmail.getText().length()>40) {
							lblEmail.setBackground(Color.RED);
							length = false;
						}else if(txtF_Nacimiento.getText().length()>10){
							lblFNacimiento.setBackground(Color.RED);
							length = false;
						}else if(txtDireccion.getText().length()>50) {
							lblDireccion.setBackground(Color.RED);
							length = false;
						}else if(!length){
							JOptionPane.showMessageDialog(null, "La longitud es incorrecta", "Error", 0);
						}else {
							//TODO Comprobar parametros existentes
							ServerUserFunctionality.updateUser(connection,userID+"" ,new String[] 
									{txtUser.getText(), txtPass.getText(),  comboPregSeg.getSelectedItem().toString(), txtRespuesta.getText()
									,"cliente"});
							dispose();
							ServerUserFunctionality.updateUserInf(connection,userID+"", new String[]
									{txtNombre.getText(), txtApellidos.getText(), txtTelefono.getText(), txtEmail.getText(),
									txtDireccion.getText(), txtF_Nacimiento.getText(), comboSexo.getSelectedItem().toString()});
							JOptionPane.showMessageDialog(null, "Usuario registrado con exito", "REGISTRADO", 1);
						}//end if length
					}//end if format
						
					
				}//end if null
			}
		});
		btnGuardarCambios.setBounds(289, 228, 205, 29);
		getContentPane().add(btnGuardarCambios);
		
	}
}
