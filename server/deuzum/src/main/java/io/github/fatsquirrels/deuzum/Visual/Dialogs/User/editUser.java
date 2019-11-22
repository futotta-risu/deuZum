package io.github.fatsquirrels.deuzum.Visual.Dialogs.User;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import io.github.fatsquirrels.deuzum.ServerUserFunctionality;
import io.github.fatsquirrels.deuzum.Algorithms.ConcreteText;
import io.github.fatsquirrels.deuzum.Algorithms.ObjectMapper;
import io.github.fatsquirrels.deuzum.Algorithms.TextFunctions;
import io.github.fatsquirrels.deuzum.Algorithms.TextTypes;
import io.github.fatsquirrels.deuzum.Algorithms.Math.APair;
import io.github.fatsquirrels.deuzum.Database.GeneralSQLFunctions;

import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
//TODO testear la clase
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
	
	private String userID;
	private HashMap<String,Component> componentMap;
	
	/**
	 * Crea un objeto de editUser, este contiene un Dialogo que sirve para buscar un usuario por su ID 
	 * y modificar sus datos
	 * @param Connection
	 */	
	public editUser(Connection conn) {
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
		
		txtDireccion = new JTextField();
		txtDireccion.setBounds(365, 57, 130, 26);
		getContentPane().add(txtDireccion);
		txtApellidos.setColumns(10);
		
		lblDireccion = new JLabel("Direccion");
		lblDireccion.setSize(61, 20);
		lblDireccion.setLocation(277, 60);
		getContentPane().add(lblDireccion);
		
		
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
				userID = txtIDUser.getText();
				ResultSet resultUser = null;
				ResultSet resultUserInf = null;
				try {
					resultUser = GeneralSQLFunctions.getResultSetEntryFromDatabase(conn, "usuario", "ID = '" + userID + "'" );
					resultUserInf = GeneralSQLFunctions.getResultSetEntryFromDatabase(conn, "infoUsuario" , "ID = '" + userID + "'");
					while(resultUser.next()) {
						txtUser.setText(resultUser.getString("usuario")); 
						comboPregSeg.setSelectedItem(resultUser.getString("preg_seguridad"));
						txtRespuesta.setText((resultUser.getString("resp_seguridad")));
					}
					while(resultUserInf.next()) {
						txtNombre.setText(resultUserInf.getString("nombre")); 
						txtApellidos.setText(resultUserInf.getString("apellido")); 
						txtTelefono.setText(resultUserInf.getString("telefono")); 
						txtEmail.setText(resultUserInf.getString("email"));
						txtDireccion.setText(resultUserInf.getString("direccion"));
						txtF_Nacimiento.setText(resultUserInf.getString("fecha_nacimiento"));
						comboSexo.setSelectedItem(resultUserInf.getString("sexo"));				
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				
			}
		});
		btnBuscarUsuario.setBounds(377, 14, 117, 29);
		getContentPane().add(btnBuscarUsuario);
	
		btnGuardarCambios = new JButton("Guardar Cambios");
		btnGuardarCambios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editarUsuario(conn);
			}
		});
		btnGuardarCambios.setBounds(289, 228, 205, 29);
		getContentPane().add(btnGuardarCambios);
		
	}
	
	
	public void editarUsuario(Connection conn) {
		//Comprobamos campos vacios
				APair[] compulsoryVars = {
						new APair<String, String>("tFUser","Usuario"),new APair<String, String>("tFPass","Contraseña"), new APair<String, String>("tFRes","Respuesta")
						};
				int nError = 0;
				for(APair i :compulsoryVars){
					JTextField tempC = (JTextField) ObjectMapper.getComponentByName(String.valueOf(i.getIndex()), componentMap);
					System.out.println(i.getIndex());
					if(tempC.getText().isEmpty()) 
						System.out.println("Error " + String.valueOf(++nError) + ": No ha indicado ninguna respuesta en " + String.valueOf(i.getValue()));
					
				}
				// Comprobamos Fecha
				JTextField dateT = (JTextField) ObjectMapper.getComponentByName("txtF_Nacimiento", componentMap);
				if(!dateT.getText().isEmpty() && !TextFunctions.dateChecker(dateT.getText()))
					System.out.println("Error " + String.valueOf(++nError) + ": No ha indicado ninguna respuesta en Fecha de Nacimiento");
				
				
				// Comprobamos Formato y longitud
				APair[] formatVars = {
						new APair<String, ConcreteText>("txtEmail", new ConcreteText("Email",TextTypes.EMAIL)),
						new APair<String, ConcreteText>("txtNombre", new ConcreteText("Nombre",TextTypes.NAME)),
						new APair<String, ConcreteText>("txtApellidos", new ConcreteText("Apellido",TextTypes.NAME)),
						new APair<String, ConcreteText>("tFPass", new ConcreteText("Contraseña",TextTypes.PASSWORD)),
						new APair<String, ConcreteText>("tFRes", new ConcreteText("Respuesta",TextTypes.NAME)),
						new APair<String, ConcreteText>("txtTelefono", new ConcreteText("Telefono",TextTypes.PHONE)),
						new APair<String, ConcreteText>("txtDireccion", new ConcreteText("Dirección",TextTypes.NAME)),
					};
				for(APair i : formatVars) {
					JTextField tempC = (JTextField) ObjectMapper.getComponentByName(String.valueOf(i.getIndex()), componentMap);
					String tempText = tempC.getText();
					if(!tempText.isEmpty() && !ConcreteText.isValid(tempText, ((ConcreteText) i.getValue()).getTextType())) 
						System.out.println("Error " + String.valueOf(++nError) + ": No ha indicado ninguna respuesta en " + String.valueOf(i.getValue()));

				}
				
				// Si no hay errores lo enviamos
				if(nError==0) {
				
					ServerUserFunctionality.updateUser(conn, userID, new String[] 
							{txtUser.getText(), txtPass.getText(), "6", txtRespuesta.getText(), "1"
							});
					
					
					ServerUserFunctionality.createUserInf(conn, new String[]
							{txtNombre.getText(), txtApellidos.getText(), txtTelefono.getText(), txtEmail.getText(),
							txtDireccion.getText(), txtF_Nacimiento.getText(), comboSexo.getSelectedItem().toString()});
					JOptionPane.showMessageDialog(null, "Usuario actualizado con exito", "ACTUALIZADO", 1);
				}
				
				// TODO Añadir ventana de error
		
	}
}
