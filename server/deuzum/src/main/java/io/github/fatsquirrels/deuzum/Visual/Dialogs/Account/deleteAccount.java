package io.github.fatsquirrels.deuzum.visual.Dialogs.Account;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.JDialog;
import javax.swing.JButton;
import javax.swing.JTextField;

import io.github.fatsquirrels.deuzum.net.ServerUserFunctionality;

import javax.swing.JLabel;
import javax.swing.JOptionPane;


/**
* Dialogo encargado de eliminar cuentas en la base de datos
*/
public class deleteAccount extends JDialog{
	

	private static final long serialVersionUID = 1L;
	private JButton btnEliminarCuenta;
	private JLabel lblIdCuenta;
	private Connection conn;
	private JTextField txtIdCuenta;

	/**
	 * Crea un objeto de deleteAccount que contiene un Dialogo que permite eliminar una cuenta.
	 * @param Connection
	 */
	public deleteAccount(Connection conn) {
		this.conn= conn;
		setSize(300,120);
		setTitle("Eliminar cuenta");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
		
		initialize(conn);
		
	}
	
	
	public void initialize(Connection conn) {
		Container cp = getContentPane();
		cp.setLayout(null);
		

		
		txtIdCuenta = new JTextField();
		txtIdCuenta.setBounds(21, 45, 130, 26);
		getContentPane().add(txtIdCuenta);
		txtIdCuenta.setColumns(10);
		
		lblIdCuenta = new JLabel("Introducir ID Cuenta");
		lblIdCuenta.setBounds(21, 17, 130, 16);
		getContentPane().add(lblIdCuenta);
		
		btnEliminarCuenta = new JButton("Eliminar Cuenta");
		btnEliminarCuenta.setBounds(163, 45, 131, 29);
		getContentPane().add(btnEliminarCuenta);
		btnEliminarCuenta.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showConfirmDialog(null, "Estas seguro de que deseas eliminar la cuenta?");
				ServerUserFunctionality.deleteAccount(conn, txtIdCuenta.getText());
			}
		});
		
		
		
		
		
		
	}
}
