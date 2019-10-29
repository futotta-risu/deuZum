package deustoZumServer.Visual.Dialogs.Project;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.sql.Connection;

import javax.swing.JButton;

public class deleteProyect extends JDialog{

	
	private static final long serialVersionUID = 1L;
	private JTextField txtIdProyecto;
	private JLabel lblIntroducirIdProyecto;
	private JButton btnEliminar;
	
	/**
	 * Crea un objeto de deleteProyect, el cual contiene un Dialogo que permite borrar un proyecto buscandolo
	 * a traves de su ID.
	 */
	public deleteProyect(Connection c) {
		setSize(500,130);
		setTitle("Eliminar Proyecto");
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		lblIntroducirIdProyecto = new JLabel("Introducir ID Proyecto:");
		lblIntroducirIdProyecto.setBounds(23, 33, 147, 25);
		getContentPane().add(lblIntroducirIdProyecto);
		
		txtIdProyecto = new JTextField();
		txtIdProyecto.setBounds(182, 32, 130, 26);
		getContentPane().add(txtIdProyecto);
		txtIdProyecto.setColumns(10);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(339, 32, 117, 29);
		getContentPane().add(btnEliminar);
	}
	

}
