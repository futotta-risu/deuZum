package deustoZumServer.Visual.Dialogs;

import javax.swing.JDialog;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JList;

public class ProyectList extends JDialog{

	
	private static final long serialVersionUID = 1L;
	private JTextField txtFiltrar;
	private JComboBox<String> comboFiltrar;
	private JButton btnFiltrar;
	private JButton btnEliminar;
	private JList<String> listaProyectos;
	
	/**
	 * Crea un objeto de ProyectList, el cual contiene un Dialogo que muestra la lista
	 * de todos las proyectos y permite hacer busquedas filtradas para poder eliminarlos.
	 */
	public ProyectList() {
		setSize(500, 500);
		setVisible(true);
		setTitle("Lista de Proyectos");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		comboFiltrar = new JComboBox<String>();
		comboFiltrar.setBounds(28, 20, 150, 27);
		getContentPane().add(comboFiltrar);
		
		txtFiltrar = new JTextField();
		txtFiltrar.setBounds(190, 19, 130, 26);
		getContentPane().add(txtFiltrar);
		txtFiltrar.setColumns(10);
		
		btnFiltrar = new JButton("Filtrar");
		btnFiltrar.setBounds(345, 19, 117, 29);
		getContentPane().add(btnFiltrar);
		
		listaProyectos = new JList<String>();
		listaProyectos.setBounds(56, 98, 308, 325);
		getContentPane().add(listaProyectos);
		
		btnEliminar = new JButton("Boton Eliminar");
		btnEliminar.setBounds(376, 246, 117, 29);
		getContentPane().add(btnEliminar);
	}
	
	
	

}
