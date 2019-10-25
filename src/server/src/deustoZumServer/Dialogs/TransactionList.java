package deustoZumServer.Dialogs;

import javax.swing.JDialog;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JList;

public class TransactionList extends JDialog{

	
	private static final long serialVersionUID = 1L;
	private JTextField txtFiltrado;
	private JComboBox<String> comboFiltrar;
	private JButton btnFiltrar;
	private JButton btnEliminar;
	private JList<String> listsTransacciones;
	
	/**
	 * Crea un objeto de TransactionList, el cual contiene un Dialogo que muestra la lista
	 * de todos las transacciones y permite hacer busquedas filtradas para poder eliminarlas.
	 */
	public TransactionList() {
		setSize(500,500);
		setTitle("Lista de Transacciones");
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		comboFiltrar = new JComboBox<String>();
		comboFiltrar.setBounds(25, 33, 156, 27);
		getContentPane().add(comboFiltrar);
		
		txtFiltrado = new JTextField();
		txtFiltrado.setBounds(193, 32, 130, 26);
		getContentPane().add(txtFiltrado);
		txtFiltrado.setColumns(10);
		
		btnFiltrar = new JButton("Filtrar");
		btnFiltrar.setBounds(354, 32, 117, 29);
		getContentPane().add(btnFiltrar);
		
		listsTransacciones = new JList<String>();
		listsTransacciones.setBounds(25, 106, 337, 338);
		getContentPane().add(listsTransacciones);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(374, 250, 117, 29);
		getContentPane().add(btnEliminar);
	}
}
