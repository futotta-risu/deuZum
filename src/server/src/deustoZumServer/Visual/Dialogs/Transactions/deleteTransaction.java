package deustoZumServer.Visual.Dialogs.Transactions;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class deleteTransaction extends JDialog{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtId;
	
	public deleteTransaction() {
		setSize(450,100);
		setTitle("Eliminar Transaccion");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JLabel lblIntroducirIdTransaccion = new JLabel("Introducir  ID Transaccion:");
		lblIntroducirIdTransaccion.setBounds(24, 34, 175, 16);
		getContentPane().add(lblIntroducirIdTransaccion);
		
		txtId = new JTextField();
		txtId.setBounds(199, 29, 130, 26);
		getContentPane().add(txtId);
		txtId.setColumns(10);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(327, 29, 117, 29);
		getContentPane().add(btnEliminar);
		setVisible(true);
	}
	
	

}
