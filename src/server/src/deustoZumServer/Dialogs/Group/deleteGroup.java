package deustoZumServer.Dialogs.Group;

import javax.swing.JDialog;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JButton;

public class deleteGroup extends JDialog{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtIdGrupo;
	
	public deleteGroup() {
		setSize(500,130);
		setTitle("Eliminar Grupo");
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JLabel lblIntroducirIdGrupo = new JLabel("Introducir ID Grupo:");
		lblIntroducirIdGrupo.setBounds(27, 41, 131, 16);
		getContentPane().add(lblIntroducirIdGrupo);
		
		txtIdGrupo = new JTextField();
		txtIdGrupo.setBounds(170, 36, 130, 26);
		getContentPane().add(txtIdGrupo);
		txtIdGrupo.setColumns(10);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(338, 36, 117, 29);
		getContentPane().add(btnEliminar);
	}
	

}
