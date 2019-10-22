º	package deustoZumServer.Dialogs;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;

public class editProyect extends JDialog{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtIdProyecto;
	private JTextField txtIdGrupo;
	private JTextField txtNombre;
	
	public editProyect() {
		setSize(500,500);
		setTitle("Editar Usuario");
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JLabel lblIntroducirIdProyecto = new JLabel("Introducir ID Proyecto:");
		lblIntroducirIdProyecto.setBounds(36, 41, 147, 16);
		getContentPane().add(lblIntroducirIdProyecto);
		
		txtIdProyecto = new JTextField();
		txtIdProyecto.setBounds(195, 36, 130, 26);
		getContentPane().add(txtIdProyecto);
		txtIdProyecto.setColumns(10);
		
		JButton btnBuscar = new JButton("Buscar Proyecto");
		btnBuscar.setBounds(337, 36, 135, 29);
		getContentPane().add(btnBuscar);
		
		JLabel lblIdGrupo = new JLabel("ID Grupo:");
		lblIdGrupo.setBounds(36, 116, 90, 16);
		getContentPane().add(lblIdGrupo);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(36, 168, 61, 16);
		getContentPane().add(lblNombre);
		
		JLabel lblDescripcion = new JLabel("Descripcion:");
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
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(138, 229, 309, 167);
		getContentPane().add(textArea);
		
		JButton btnGuardarCambios = new JButton("Guardar Cambios");
		btnGuardarCambios.setBounds(169, 418, 147, 29);
		getContentPane().add(btnGuardarCambios);
	}
	
	

}
