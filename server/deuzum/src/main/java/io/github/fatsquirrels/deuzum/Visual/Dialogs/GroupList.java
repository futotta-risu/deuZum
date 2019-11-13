package io.github.fatsquirrels.deuzum.Visual.Dialogs;

import javax.swing.JDialog;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JList;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.awt.event.ActionEvent;

public class GroupList extends JDialog{

	
	private static final long serialVersionUID = 1L;
	private JTextField txtFiltrarGrupos;
	private JComboBox<String> comboFiltrar;
	private JButton btnFiltrar;
	private JButton btnEliminar;
	private JList<String> listaGrupos;
	
	/**
	 * Crea un objeto de GroupList, el cual contiene un Dialogo que muestra la lista
	 * de todos las grupos y permite hacer busquedas filtradas para poder eliminarlos.
	 */
	public GroupList(Connection c) {
		setSize(500,500);
		setTitle("Lista de Grupos");
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		
		comboFiltrar = new JComboBox<String>();
		comboFiltrar.setBounds(26, 22, 138, 27);
		getContentPane().add(comboFiltrar);
		
		txtFiltrarGrupos = new JTextField();
		txtFiltrarGrupos.setBounds(176, 21, 138, 26);
		getContentPane().add(txtFiltrarGrupos);
		txtFiltrarGrupos.setColumns(10);
		
		btnFiltrar = new JButton("Filtrar");
		btnFiltrar.setBounds(326, 21, 138, 29);
		getContentPane().add(btnFiltrar);
		
		listaGrupos = new JList<String>();
		listaGrupos.setBounds(24, 104, 342, 351);
		getContentPane().add(listaGrupos);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnEliminar.setBounds(377, 237, 117, 29);
		getContentPane().add(btnEliminar);
	}
	

}
