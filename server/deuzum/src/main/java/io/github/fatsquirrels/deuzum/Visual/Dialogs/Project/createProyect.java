package io.github.fatsquirrels.deuzum.Visual.Dialogs.Project;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.sql.Connection;

import javax.swing.JButton;

public class createProyect extends JDialog{

	
	private static final long serialVersionUID = 1L;
	private JTextField txtIdGrupo;
	private JTextField txtNombre;
	private JTextField txtDescripcion;
	private JButton btnCrearTransaccion;
	private JLabel lblIdGrupo;
	private JLabel lblNombre;
	private JLabel lblDescripcion;
	
	
	/**
	 * Crea un objeto de createProyect, este Contiene un Dialogo que permite crear un nuevo Proyecto.
	 */
	public createProyect(Connection c) {
		setSize(500,430);
		setVisible(true);
		setTitle("Crear Proyecto");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		
		lblIdGrupo = new JLabel("ID grupo:");
		lblIdGrupo.setBounds(67, 65, 94, 26);
		getContentPane().add(lblIdGrupo);
		
		lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(67, 128, 61, 16);
		getContentPane().add(lblNombre);
		
		lblDescripcion = new JLabel("Descripcion:");
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
