package deustoZumServer.Visual.Dialogs.Project;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.sql.Connection;

import javax.swing.JButton;
import javax.swing.JTextArea;

public class editProyect extends JDialog{

	
	private static final long serialVersionUID = 1L;
	private JTextField txtIdProyecto;
	private JTextField txtIdGrupo;
	private JTextField txtNombre;
	private JLabel lblIntroducirIdProyecto;
	private JButton btnBuscar;
	private JLabel lblIdGrupo;
	private JLabel lblNombre;
	private JLabel lblDescripcion;
	private JTextArea areaDescripcion;
	private JButton btnGuardarCambios;
		
	/**
	 * Crea un objeto de editProyect el cual contiene un Dialogo que permite editar la informacion de un proyecto
	 * buscandolo a traves de su ID.
	 */
	public editProyect(Connection c) {
		setSize(500,500);
		setTitle("Editar Usuario");
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		lblIntroducirIdProyecto = new JLabel("Introducir ID Proyecto:");
		lblIntroducirIdProyecto.setBounds(36, 41, 147, 16);
		getContentPane().add(lblIntroducirIdProyecto);
		
		txtIdProyecto = new JTextField();
		txtIdProyecto.setBounds(195, 36, 130, 26);
		getContentPane().add(txtIdProyecto);
		txtIdProyecto.setColumns(10);
		
		btnBuscar = new JButton("Buscar Proyecto");
		btnBuscar.setBounds(337, 36, 135, 29);
		getContentPane().add(btnBuscar);
		
		lblIdGrupo = new JLabel("ID Grupo:");
		lblIdGrupo.setBounds(36, 116, 90, 16);
		getContentPane().add(lblIdGrupo);
		
		lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(36, 168, 61, 16);
		getContentPane().add(lblNombre);
		
		lblDescripcion = new JLabel("Descripcion:");
		lblDescripcion.setBounds(18, 229, 79, 16);
		getContentPane().add(lblDescripcion);
		
		txtIdGrupo = new JTextField();
		txtIdGrupo.setBounds(138, 111, 130, 26);
		getContentPane().add(txtIdGrupo);
		txtIdGrupo.setColumns(10);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(138, 163, 130, 26);
		getContentPane().add(txtNombre);
		txtNombre.setColumns(10);
		
		areaDescripcion = new JTextArea();
		areaDescripcion.setBounds(138, 229, 309, 167);
		getContentPane().add(areaDescripcion);
		
		btnGuardarCambios = new JButton("Guardar Cambios");
		btnGuardarCambios.setBounds(169, 418, 147, 29);
		getContentPane().add(btnGuardarCambios);
	}
	
}
