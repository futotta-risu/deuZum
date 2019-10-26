package deustoZumServer.Visual.Dialogs.Project;

import javax.swing.JDialog;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JButton;

public class createProyect extends JDialog{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtIdGrupo;
	private JTextField txtNombre;
	private JTextField txtDescripcion;
	private JButton btnCrearTransaccion;
	
	public createProyect() {
		setSize(500,430);
		setVisible(true);
		setTitle("Crear Proyecto");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JLabel lblIdGrupo = new JLabel("ID grupo:");
		lblIdGrupo.setBounds(67, 65, 94, 26);
		getContentPane().add(lblIdGrupo);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(67, 128, 61, 16);
		getContentPane().add(lblNombre);
		
		JLabel lblDescripcion = new JLabel("Descripcion:");
		lblDescripcion.setBounds(49, 185, 79, 16);
		getContentPane().add(lblDescripcion);
		
		txtIdGrupo = new JTextField();
		txtIdGrupo.setBounds(173, 65, 130, 26);
		getContentPane().add(txtIdGrupo);
		txtIdGrupo.setColumns(10);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(173, 123, 130, 26);
		getContentPane().add(txtNombre);
		txtNombre.setColumns(10);
		
		txtDescripcion = new JTextField();
		txtDescripcion.setBounds(173, 180, 281, 139);
		getContentPane().add(txtDescripcion);
		txtDescripcion.setColumns(10);
		
		btnCrearTransaccion = new JButton("Crear Transaccion");
		btnCrearTransaccion.setBounds(158, 366, 145, 29);
		getContentPane().add(btnCrearTransaccion);
	}
	

}
