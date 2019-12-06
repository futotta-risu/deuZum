package io.github.fatsquirrels.deuzum.Visual.Dialogs;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.MatteBorder;

import io.github.fatsquirrels.deuzum.Database.GeneralSQLFunctions;
import io.github.fatsquirrels.deuzum.Database.WhereAST;
import io.github.fatsquirrels.deuzum.Visual.Dialogs.Account.Cuenta;
import io.github.fatsquirrels.deuzum.Visual.Dialogs.User.Usuario;

public class AccountList extends JDialog{
	private static final long serialVersionUID = 1L;
	private JTextField txtFiltrar;
	private JComboBox<String> comboFiltrar;
	private JButton btnFiltrar;
	private JList<Cuenta> listaCuentas;
	private JButton btnEliminarCuentas;
	

	/**
	 * Crea un objeto de UserList, el cual contiene un Dialogo que muestra la lista
	 * de todos los usuarios y permite hacer busquedas filtradas para eliminar usuarios.
	 */
	public AccountList(Connection c) {
		setSize(600,450);
		setTitle("Lista de cuentas");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		
		comboFiltrar = new JComboBox<String>();
		comboFiltrar.setBounds(43, 30, 170, 27);
		getContentPane().add(comboFiltrar);
		comboFiltrar.addItem("ID");
		comboFiltrar.addItem("Numero de Cuenta");
		comboFiltrar.addItem("ID Usuario");
		comboFiltrar.addItem("Dinero");
		comboFiltrar.addItem("Tipo Cuenta");
		//comboFiltrar.addItem("Descripcion");
		comboFiltrar.addItem("Estado");
		comboFiltrar.addItem("Categoria");

		
		txtFiltrar = new JTextField();
		txtFiltrar.setBounds(225, 29, 170, 26);
		getContentPane().add(txtFiltrar);
		txtFiltrar.setColumns(10);
		
		DefaultListModel<Cuenta> model = new DefaultListModel<>();
		listaCuentas = new JList<>(model);
		listaCuentas.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		listaCuentas.setBounds(48, 85, 381, 287);
		listaCuentas.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		
		getContentPane().add(listaCuentas);
		
		btnFiltrar = new JButton("Filtrar");
		btnFiltrar.setBounds(432, 29, 117, 29);
		getContentPane().add(btnFiltrar);
		btnFiltrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String conditions ="";
				try {
					//TODO Actualizar sql statement
					
					ResultSet resultados = GeneralSQLFunctions.getResultSetEntryFromDatabase(c, "usuario", " " + comboFiltrar.getItemAt(comboFiltrar.getSelectedIndex()) + "= '" + txtFiltrar.getText()+ "'" );
						// retrieve and print the values for the current row
						while(resultados.next()) {
						String id = resultados.getString("id");
						String usuario = resultados.getString("num_cuenta");
						String pass = resultados.getString("id_usuario");
						String fecha_creacion = resultados.getString("dinero");
						String preg_seguridad = resultados.getString("tipo_cuenta");
						String resp_seguridad = resultados.getString("descripcion");
						String permisos = resultados.getString("estado");
						String categoria = resultados.getString("categoria");
						Cuenta tempAccount = new Cuenta(id, usuario, pass, fecha_creacion, preg_seguridad, resp_seguridad, permisos, categoria);
						model.addElement(tempAccount);
						
					}
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "ERROR", "No se ha podido realizar la consulta", 0);
				}
			
			}
		});
		
		btnEliminarCuentas = new JButton("Eliminar Cuentas");
		btnEliminarCuentas.setBounds(441, 199, 136, 29);
		getContentPane().add(btnEliminarCuentas);
		setVisible(true);
		btnEliminarCuentas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				List<Cuenta> listaBorrar = listaCuentas.getSelectedValuesList();
				ArrayList<String> ids = new ArrayList<String>();
				for (Cuenta account : listaBorrar) {
					ids.add(account.getIdCuenta());
				}
				try {
					for (String id : ids) {
						WhereAST where = new WhereAST().addValue("id='"+id+"'");
						GeneralSQLFunctions.deleteEntryFromDatabase(c, "cuenta", where);
					}
				}catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "ERROR", "Ha habido un error al eliminar la cuenta", 0);
				}
			}
		});
		
	}
}
