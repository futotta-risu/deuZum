package io.github.fatsquirrels.deuzum.visual.Dialogs.Transactions;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import io.github.fatsquirrels.deuzum.database.GeneralSQLFunctions;
import io.github.fatsquirrels.deuzum.database.WhereAST;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JButton;

@Deprecated
public class deleteTransaction extends JDialog{

	
	private static final long serialVersionUID = 1L;
	private JTextField txtId;
	private JLabel lblIntroducirIdTransaccion;
	private JButton btnEliminar;
	
	
	/**
	 * Crea un objeto de deleteTransaction, el cual contiene un Dialogo que da la opcion de buscar una
	 * transaccion por su ID y eliminarla.
	 */
	public deleteTransaction(Connection c) {
		setSize(450,100);
		setTitle("Eliminar Transaccion");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
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
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = txtId.getText();
				try {
					WhereAST where = new WhereAST().addValue("user_id='"+id+"'");
					GeneralSQLFunctions.deleteEntryFromDatabase(c, "Transacion", where);
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "No se ha podido eliminar el usuario", "ERROR", 0);
				}
			}
		});
	}
	
	

}
