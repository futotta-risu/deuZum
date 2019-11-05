package deustoZumServer.Visual.Dialogs;

import javax.swing.JDialog;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import java.sql.Connection;

public class UserList extends JDialog{
	
	private static final long serialVersionUID = 1L;
	private JTextField txtFiltrar;
	private JComboBox<String> comboFiltrar;
	private JButton btnFiltrar;
	private JList<String> listaUsuarios;
	private JButton btnEliminarUsuarios;

	/**
	 * Crea un objeto de UserList, el cual contiene un Dialogo que muestra la lista
	 * de todos los usuarios y permite hacer busquedas filtradas para eliminar usuarios.
	 */
	public UserList(Connection c) {
		setSize(600,450);
		setTitle("Lista de usuarios");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		comboFiltrar = new JComboBox<String>();
		comboFiltrar.setBounds(43, 30, 170, 27);
		getContentPane().add(comboFiltrar);
		
		txtFiltrar = new JTextField();
		txtFiltrar.setBounds(225, 29, 170, 26);
		getContentPane().add(txtFiltrar);
		txtFiltrar.setColumns(10);
		
	    btnFiltrar = new JButton("Filtrar");
		btnFiltrar.setBounds(432, 29, 117, 29);
		getContentPane().add(btnFiltrar);
		
		listaUsuarios = new JList<String>();
		listaUsuarios.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		listaUsuarios.setBounds(48, 85, 381, 287);
		getContentPane().add(listaUsuarios);
		
		btnEliminarUsuarios = new JButton("Eliminar Usuarios");
		btnEliminarUsuarios.setBounds(441, 199, 136, 29);
		getContentPane().add(btnEliminarUsuarios);
		setVisible(true);
		
	}
}
