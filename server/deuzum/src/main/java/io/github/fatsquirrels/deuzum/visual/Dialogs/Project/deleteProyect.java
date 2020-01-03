package io.github.fatsquirrels.deuzum.visual.Dialogs.Project;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import io.github.fatsquirrels.deuzum.net.ServerUserFunctionality;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.JButton;

@Deprecated
public class deleteProyect extends JDialog{

	
	private static final long serialVersionUID = 1L;
	private JTextField txtIdProyecto;
	private JLabel lblIntroducirIdProyecto;
	private JButton btnEliminar;
	private Connection conn;
	
	/**
	 * Crea un objeto de deleteProyect, el cual contiene un Dialogo que permite borrar un proyecto buscandolo
	 * a traves de su ID.
	 */
	public deleteProyect(Connection c) {
		this.conn = c;
		setSize(500,130);
		setTitle("Eliminar Proyecto");
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		initialize(conn);
	}
	
	public void initialize(Connection c) {
		
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
		
		btnEliminar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showConfirmDialog(null, "¿Estás seguro de que deseas eliminar el proyecto?");
				ServerUserFunctionality.deleteGroup(c, txtIdProyecto.getText());
				
			}
			
		});
	}
}
