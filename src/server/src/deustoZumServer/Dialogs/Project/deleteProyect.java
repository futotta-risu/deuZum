package deustoZumServer.Dialogs.Project;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class deleteProyect extends JDialog{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtIdProyecto;
	
	public deleteProyect() {
		setSize(500,130);
		setTitle("Eliminar Proyecto");
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JLabel lblIntroducirIdProyecto = new JLabel("Introducir ID Proyecto:");
		lblIntroducirIdProyecto.setBounds(23, 33, 147, 25);
		getContentPane().add(lblIntroducirIdProyecto);
		
		txtIdProyecto = new JTextField();
		txtIdProyecto.setBounds(182, 32, 130, 26);
		getContentPane().add(txtIdProyecto);
		txtIdProyecto.setColumns(10);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(339, 32, 117, 29);
		getContentPane().add(btnEliminar);
	}
	

}
