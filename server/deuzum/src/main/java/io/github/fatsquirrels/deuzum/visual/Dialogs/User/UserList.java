package io.github.fatsquirrels.deuzum.visual.Dialogs.User;

import javax.swing.JDialog;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.border.MatteBorder;

import io.github.fatsquirrels.deuzum.database.GeneralSQLFunctions;
import io.github.fatsquirrels.deuzum.database.WhereAST;

import java.awt.Color;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserList extends JDialog{
	
	private static final long serialVersionUID = 1L;
	private JTextField txtFiltrar;
	private JComboBox<String> comboFiltrar;
	private JButton btnFiltrar;
	private JList<Usuario> listaUsuarios;
	private JButton btnEliminarUsuarios;
	

	/**
	 * Crea un objeto de UserList, el cual contiene un Dialogo que muestra la lista
	 * de todos los usuarios y permite hacer busquedas filtradas para eliminar usuarios.
	 */
	public UserList(Connection c) {
		setSize(600,450);
		setTitle("Lista de usuarios");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		
		comboFiltrar = new JComboBox<String>();
		comboFiltrar.setBounds(43, 30, 170, 27);
		getContentPane().add(comboFiltrar);
		comboFiltrar.addItem("ID");
		comboFiltrar.addItem("Nombre");
		comboFiltrar.addItem("Username");
		comboFiltrar.addItem("Fecha de Nacimineto");
		//comboFiltrar.addItem("Permisos");
		//comboFiltrar.addItem("Categoria");
		//comboFiltrar.addItem("Sexo");
		
		txtFiltrar = new JTextField();
		txtFiltrar.setBounds(225, 29, 170, 26);
		getContentPane().add(txtFiltrar);
		txtFiltrar.setColumns(10);
		
		DefaultListModel<Usuario> model = new DefaultListModel<>();
		listaUsuarios = new JList<>(model);
		listaUsuarios.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		listaUsuarios.setBounds(48, 85, 381, 287);
		listaUsuarios.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		
		getContentPane().add(listaUsuarios);
		
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
						String usuario = resultados.getString("usuario");
						String pass = resultados.getString("contraseña");
						String fecha_creacion = resultados.getString("fecha_creacion");
						String preg_seguridad = resultados.getString("preg_seguridad");
						String resp_seguridad = resultados.getString("resp_seguridad");
						String permisos = resultados.getString("permisos");
						String categoria = resultados.getString("categoria");
						Usuario tempUser = new Usuario(id, usuario, pass, fecha_creacion, preg_seguridad, resp_seguridad, permisos, categoria);
						model.addElement(tempUser);
						
					}
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "ERROR", "No se ha podido realizar la consulta", 0);
				}
			
			}
		});
		
		btnEliminarUsuarios = new JButton("Eliminar Usuarios");
		btnEliminarUsuarios.setBounds(441, 199, 136, 29);
		getContentPane().add(btnEliminarUsuarios);
		setVisible(true);
		btnEliminarUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				List<Usuario> listaBorrar = listaUsuarios.getSelectedValuesList();
				ArrayList<String> ids = new ArrayList<String>();
				for (Usuario user : listaBorrar) {
					ids.add(user.getId());
				}
				try {
					for (String id : ids) {
						WhereAST where = new WhereAST().addValue("user_id='"+id+"'");
						GeneralSQLFunctions.deleteEntryFromDatabase(c, "usuario", where);
					}
				}catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "ERROR", "Ha habido un error al eliminar el usuario", 0);
				}
			}
		});
		
	}
}
