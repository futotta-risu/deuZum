package io.github.fatsquirrels.deuzum.visual.Dialogs.Group;

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
public class deleteGroup extends JDialog{

	
	private static final long serialVersionUID = 1L;
	private JTextField txtIdGrupo;
	private JLabel lblIntroducirIdGrupo;
	private JButton btnEliminar;
	private Connection conn;
	
	/**
	 * Crea un objeto de deleteGroup, este contiene un Dialogo que permite eliminar un grupo buscandolo 
	 * a traves de su ID.
	 */
	public deleteGroup(Connection c) {
		this.conn = c;
		setSize(500,130);
		setTitle("Eliminar Grupo");
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		initialize(conn);
		
	}
	
	public void initialize(Connection c) {
	
		getContentPane().setLayout(null);
		lblIntroducirIdGrupo = new JLabel("Introducir ID Grupo:");
		lblIntroducirIdGrupo.setBounds(27, 41, 131, 16);
		getContentPane().add(lblIntroducirIdGrupo);
		
		txtIdGrupo = new JTextField();
		txtIdGrupo.setBounds(170, 36, 130, 26);
		getContentPane().add(txtIdGrupo);
		txtIdGrupo.setColumns(10);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(338, 36, 117, 29);
		getContentPane().add(btnEliminar);
		
		btnEliminar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				JOptionPane.showConfirmDialog(null, "¿Estás seguro de que deseas eliminar el grupo?");
				ServerUserFunctionality.deleteGroup(c, txtIdGrupo.getText());
				
			}
			
		});
	}
	

}
