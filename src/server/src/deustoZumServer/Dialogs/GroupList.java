package deustoZumServer.Dialogs;

import javax.swing.JDialog;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GroupList extends JDialog{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtFiltrarGrupos;
	
	public GroupList() {
		setSize(500,500);
		setTitle("Lista de Grupos");
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JComboBox comboBoxFiltrar = new JComboBox();
		comboBoxFiltrar.setBounds(26, 22, 138, 27);
		getContentPane().add(comboBoxFiltrar);
		
		txtFiltrarGrupos = new JTextField();
		txtFiltrarGrupos.setBounds(176, 21, 138, 26);
		getContentPane().add(txtFiltrarGrupos);
		txtFiltrarGrupos.setColumns(10);
		
		JButton btnFiltrar = new JButton("Filtrar");
		btnFiltrar.setBounds(326, 21, 138, 29);
		getContentPane().add(btnFiltrar);
		
		JList listaGrupos = new JList();
		listaGrupos.setBounds(24, 104, 342, 351);
		getContentPane().add(listaGrupos);
		
		JButton btnBotonEliminar = new JButton("Eliminar");
		btnBotonEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnBotonEliminar.setBounds(377, 237, 117, 29);
		getContentPane().add(btnBotonEliminar);
	}
	

}
