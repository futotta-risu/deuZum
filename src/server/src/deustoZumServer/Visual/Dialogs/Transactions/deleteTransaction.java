package deustoZumServer.Visual.Dialogs.Transactions;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class deleteTransaction extends JDialog{

	
	private static final long serialVersionUID = 1L;
	private JTextField txtId;
	private JLabel lblIntroducirIdTransaccion;
	private JButton btnEliminar;
	
	
	/**
	 * Crea un objeto de deleteTransaction, el cual contiene un Dialogo que da la opcion de buscar una
	 * transaccion por su ID y eliminarla.
	 */
	public deleteTransaction() {
		setSize(450,100);
		setTitle("Eliminar Transaccion");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		lblIntroducirIdTransaccion = new JLabel("Introducir  ID Transaccion:");
		lblIntroducirIdTransaccion.setBounds(24, 34, 175, 16);
		getContentPane().add(lblIntroducirIdTransaccion);
		
		txtId = new JTextField();
		txtId.setBounds(199, 29, 130, 26);
		getContentPane().add(txtId);
		txtId.setColumns(10);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(327, 29, 117, 29);
		getContentPane().add(btnEliminar);
		setVisible(true);
	}
	
	

}
