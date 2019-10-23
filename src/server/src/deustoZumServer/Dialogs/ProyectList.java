package deustoZumServer.Dialogs;

import javax.swing.JDialog;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JList;

public class ProyectList extends JDialog{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtFiltrar;
	
	public ProyectList() {
		setSize(500, 500);
		setVisible(true);
		setTitle("Lista de Proyectos");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JComboBox comboBoxFiltrar = new JComboBox();
		comboBoxFiltrar.setBounds(28, 20, 150, 27);
		getContentPane().add(comboBoxFiltrar);
		
		txtFiltrar = new JTextField();
		txtFiltrar.setBounds(190, 19, 130, 26);
		getContentPane().add(txtFiltrar);
		txtFiltrar.setColumns(10);
		
		JButton btnFiltrar = new JButton("Filtrar");
		btnFiltrar.setBounds(345, 19, 117, 29);
		getContentPane().add(btnFiltrar);
		
		JList listaProyectos = new JList();
		listaProyectos.setBounds(56, 98, 308, 325);
		getContentPane().add(listaProyectos);
		
		JButton btnBotonEliminar = new JButton("Boton Eliminar");
		btnBotonEliminar.setBounds(376, 246, 117, 29);
		getContentPane().add(btnBotonEliminar);
	}
	
	
	

}
