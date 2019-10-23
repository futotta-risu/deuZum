package deustoZumServer.Dialogs;

import javax.swing.JDialog;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JList;

public class TransactionList extends JDialog{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtFiltrado;
	
	public TransactionList() {
		setSize(500,500);
		setTitle("Lista de Transacciones");
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(25, 33, 156, 27);
		getContentPane().add(comboBox);
		
		txtFiltrado = new JTextField();
		txtFiltrado.setBounds(193, 32, 130, 26);
		getContentPane().add(txtFiltrado);
		txtFiltrado.setColumns(10);
		
		JButton btnFiltrar = new JButton("Filtrar");
		btnFiltrar.setBounds(354, 32, 117, 29);
		getContentPane().add(btnFiltrar);
		
		JList listsTransacciones = new JList();
		listsTransacciones.setBounds(25, 106, 337, 338);
		getContentPane().add(listsTransacciones);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(374, 250, 117, 29);
		getContentPane().add(btnEliminar);
	}
}
