package deustoZumServer.Visual.Dialogs.Group;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.sql.Connection;

import javax.swing.JButton;

public class deleteGroup extends JDialog{

	
	private static final long serialVersionUID = 1L;
	private JTextField txtIdGrupo;
	private JLabel lblIntroducirIdGrupo;
	private JButton btnEliminar;
	
	/**
	 * Crea un objeto de deleteGroup, este contiene un Dialogo que permite eliminar un grupo buscandolo 
	 * a traves de su ID.
	 */
	public deleteGroup(Connection c) {
		setSize(500,130);
		setTitle("Eliminar Grupo");
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
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
	}
	

}
