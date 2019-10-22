package deustoZumServer.Dialogs;

import javax.swing.JDialog;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.border.MatteBorder;
import java.awt.Color;

public class UserList extends JDialog{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textField;

	
	public UserList() {
		setSize(600,450);
		setTitle("Lista de usuarios");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(43, 30, 170, 27);
		getContentPane().add(comboBox);
		
		textField = new JTextField();
		textField.setBounds(225, 29, 170, 26);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnFiltrar = new JButton("Filtrar");
		btnFiltrar.setBounds(432, 29, 117, 29);
		getContentPane().add(btnFiltrar);
		
		JList list = new JList();
		list.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		list.setBounds(48, 85, 381, 287);
		getContentPane().add(list);
		
		JButton btnEliminarUsuarios = new JButton("Eliminar Usuarios");
		btnEliminarUsuarios.setBounds(441, 199, 136, 29);
		getContentPane().add(btnEliminarUsuarios);
		setVisible(true);
		
	}
}
