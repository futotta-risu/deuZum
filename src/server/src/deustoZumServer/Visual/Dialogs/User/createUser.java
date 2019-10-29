package deustoZumServer.Visual.Dialogs.User;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import deustoZumServer.ServerUserFunctionality;
import deustoZumServer.Visual.Style.Components.Buttons.FlatButton;

import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

public class createUser extends JDialog{

	private static final long serialVersionUID = 1L;
	private JTextField tFUser;
	private JTextField tFPass;
	private JComboBox<String> comboPregSeg;
	private JTextField tFRes;
	private JTextField txtNombre;
	private JTextField txtApellidos;
	private JTextField txtTelefono;
	private JTextField txtEmail;
	private JTextField txtF_Nacimiento;
	private JComboBox<String> comboSexo;
	
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
	private JPanel userData;
	private JPanel beautyW;
	private JPanel beautyE;
	private JPanel beautyN;
	
	/**
	 * Crea un objeto de createUser que contiene un Dialogo que permite crear un usuario.
	 * @param Connection
	 */
	public createUser(Connection conn) {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		// TODO Actualizar esta ventana respecto a los nuevos parametros de la base de datos
		setSize(533,300);
		
		
		btnCrear = new FlatButton("Crear Usuario");
		btnCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO verificar que los datos sena validos (e.g. |tFUser|<33)
				ServerUserFunctionality.createUser(conn, new String[] 
						{tFUser.getText(), tFPass.getText(), comboPregSeg.getSelectedItem().toString(), tFRes.getText()
						,"cliente"});
				dispose();
			}
		});
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		
		
		userData = new JPanel();
		
		GridBagLayout gbl_userData = new GridBagLayout();
		gbl_userData.columnWeights = new double[]{1.0, 1.0};
		gbl_userData.columnWidths = new int[]{167, 293};
		userData.setLayout(gbl_userData);
		
		lblUsuario = new JLabel("Usuario");
		lblUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblUsuario = new GridBagConstraints();
		gbc_lblUsuario.fill = GridBagConstraints.BOTH;
		gbc_lblUsuario.gridwidth  = 1;
		gbc_lblUsuario.gridheight  = 1;
		gbc_lblUsuario.gridx = 0;
		gbc_lblUsuario.gridy = 0;
		userData.add(lblUsuario, gbc_lblUsuario);
		
		tFUser = new JTextField();
		GridBagConstraints gbc_tFUser = new GridBagConstraints();
		gbc_tFUser.fill = GridBagConstraints.BOTH;
		gbc_tFUser.gridx = 1;
		gbc_tFUser.gridy = 0;
		userData.add(tFUser, gbc_tFUser);
		tFUser.setColumns(20);
		
		lblPass = new JLabel("Contrase\u00F1a");
		lblPass.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblPass = new GridBagConstraints();
		gbc_lblPass.fill = GridBagConstraints.BOTH;
		gbc_lblPass.gridx = 0;
		gbc_lblPass.gridy = 1;
		userData.add(lblPass, gbc_lblPass);
		
		tFPass = new JPasswordField();
		GridBagConstraints gbc_tFPass = new GridBagConstraints();
		gbc_tFPass.fill = GridBagConstraints.BOTH;
		gbc_tFPass.gridx = 1;
		gbc_tFPass.gridy = 1;
		userData.add(tFPass, gbc_tFPass);
		tFPass.setColumns(20);
		
		lblTelefono = new JLabel("Telefono");
		lblTelefono.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblTelefono = new GridBagConstraints();
		gbc_lblTelefono.fill = GridBagConstraints.BOTH;
		gbc_lblTelefono.gridx = 0;
		gbc_lblTelefono.gridy = 2;
		userData.add(lblTelefono, gbc_lblTelefono);
		
		txtTelefono = new JTextField();
		GridBagConstraints gbc_txtTelefono = new GridBagConstraints();
		gbc_txtTelefono.fill = GridBagConstraints.BOTH;
		gbc_txtTelefono.gridx = 1;
		gbc_txtTelefono.gridy = 2;
		userData.add(txtTelefono, gbc_txtTelefono);
		txtTelefono.setColumns(20);
		
		lblPregSeguridad = new JLabel("Pregunta Seguridad");
		lblPregSeguridad.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblPregSeguridad = new GridBagConstraints();
		gbc_lblPregSeguridad.fill = GridBagConstraints.BOTH;
		gbc_lblPregSeguridad.gridx = 0;
		gbc_lblPregSeguridad.gridy = 3;
		userData.add(lblPregSeguridad, gbc_lblPregSeguridad);
		
		comboPregSeg = new JComboBox<String>();
		comboPregSeg.addItem("¿Cual era tu apodo de pequeñ@?");
		comboPregSeg.addItem("¿En que ciudad conociste a tu pareja?");
		comboPregSeg.addItem("¿Cual era el nombre de tu mejor amig@ de la infacia?");
		comboPregSeg.addItem("¿Cual era el nombre de tu primera mascota?");
		comboPregSeg.addItem("¿Cual era el heroe de tu infacia?");
		comboPregSeg.addItem("¿En que ciudad te gustaria jubilarte?");
		GridBagConstraints gbc_comboPregSeg = new GridBagConstraints();
		gbc_comboPregSeg.fill = GridBagConstraints.BOTH;
		gbc_comboPregSeg.gridx = 1;
		gbc_comboPregSeg.gridy = 3;
		userData.add(comboPregSeg, gbc_comboPregSeg);
		
		
		lblRespuesta = new JLabel("Respuesta");
		lblRespuesta.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblRespuesta = new GridBagConstraints();
		gbc_lblRespuesta.fill = GridBagConstraints.BOTH;
		gbc_lblRespuesta.gridx = 0;
		gbc_lblRespuesta.gridy = 4;
		userData.add(lblRespuesta, gbc_lblRespuesta);
		
		tFRes = new JTextField();
		GridBagConstraints gbc_tFRes = new GridBagConstraints();
		gbc_tFRes.fill = GridBagConstraints.BOTH;
		gbc_tFRes.gridx = 1;
		gbc_tFRes.gridy = 4;
		userData.add(tFRes, gbc_tFRes);
		tFRes.setColumns(20);
		
		lblEmail = new JLabel("Email");
		lblEmail.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblEmail = new GridBagConstraints();
		gbc_lblEmail.fill = GridBagConstraints.BOTH;
		gbc_lblEmail.gridx = 0;
		gbc_lblEmail.gridy = 5;
		userData.add(lblEmail, gbc_lblEmail);
		
		txtEmail = new JTextField();
		GridBagConstraints gbc_txtEmail = new GridBagConstraints();
		gbc_txtEmail.fill = GridBagConstraints.BOTH;
		gbc_txtEmail.gridx = 1;
		gbc_txtEmail.gridy = 5;
		userData.add(txtEmail, gbc_txtEmail);
		txtEmail.setColumns(20);
		
		lblNombre = new JLabel("Nombre");
		lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblNombre = new GridBagConstraints();
		gbc_lblNombre.fill = GridBagConstraints.BOTH;
		gbc_lblNombre.gridx = 0;
		gbc_lblNombre.gridy = 6;
		userData.add(lblNombre, gbc_lblNombre);
		
		txtNombre = new JTextField();
		GridBagConstraints gbc_txtNombre = new GridBagConstraints();
		gbc_txtNombre.fill = GridBagConstraints.BOTH;
		gbc_txtNombre.gridx = 1;
		gbc_txtNombre.gridy = 6;
		userData.add(txtNombre, gbc_txtNombre);
		txtNombre.setColumns(20);
		
		lblApellidos = new JLabel("Apellidos");
		lblApellidos.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblApellidos = new GridBagConstraints();
		gbc_lblApellidos.fill = GridBagConstraints.BOTH;
		gbc_lblApellidos.gridx = 0;
		gbc_lblApellidos.gridy = 7;
		userData.add(lblApellidos, gbc_lblApellidos);
		
		txtApellidos = new JTextField();
		GridBagConstraints gbc_txtApellidos = new GridBagConstraints();
		gbc_txtApellidos.fill = GridBagConstraints.BOTH;
		gbc_txtApellidos.gridx = 1;
		gbc_txtApellidos.gridy = 7;
		userData.add(txtApellidos, gbc_txtApellidos);
		txtApellidos.setColumns(20);
		
		lblFNacimiento = new JLabel("Fecha de Nacimiento");
		lblFNacimiento.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblFNacimiento = new GridBagConstraints();
		gbc_lblFNacimiento.fill = GridBagConstraints.BOTH;
		gbc_lblFNacimiento.gridx = 0;
		gbc_lblFNacimiento.gridy = 8;
		userData.add(lblFNacimiento, gbc_lblFNacimiento);
		
		txtF_Nacimiento = new JTextField();
		GridBagConstraints gbc_txtF_Nacimiento = new GridBagConstraints();
		gbc_txtF_Nacimiento.fill = GridBagConstraints.BOTH;
		gbc_txtF_Nacimiento.gridx = 1;
		gbc_txtF_Nacimiento.gridy = 8;
		userData.add(txtF_Nacimiento, gbc_txtF_Nacimiento);
		txtF_Nacimiento.setColumns(20);
		
		lblSexo = new JLabel("Sexo");
		lblSexo.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblSexo = new GridBagConstraints();
		gbc_lblSexo.fill = GridBagConstraints.BOTH;
		gbc_lblSexo.gridx = 0;
		gbc_lblSexo.gridy = 9;
		userData.add(lblSexo, gbc_lblSexo);
		
		comboSexo = new JComboBox<String>();
		comboSexo.addItem("Hombre");
		comboSexo.addItem("Mujer");
		comboSexo.addItem("Otro");
		GridBagConstraints gbc_comboSexo = new GridBagConstraints();
		gbc_comboSexo.fill = GridBagConstraints.BOTH;
		gbc_comboSexo.gridx = 1;
		gbc_comboSexo.gridy = 9;
		userData.add(comboSexo, gbc_comboSexo);
		
		beautyW = new JPanel();
		getContentPane().add(beautyW, BorderLayout.WEST);
		
		beautyE = new JPanel();
		getContentPane().add(beautyE, BorderLayout.EAST);
		
		beautyN = new JPanel();
		getContentPane().add(beautyN, BorderLayout.NORTH);
		getContentPane().add(btnCrear, BorderLayout.SOUTH);
		getContentPane().add(userData, BorderLayout.CENTER);
		setVisible(true);
		
	}
}
