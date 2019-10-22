package deustoZumServer.Dialogs;

import javax.swing.JDialog;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class createGroup extends JDialog{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtMiembro1;
	private JTextField txtMiembro2;
	private JTextField txtMiembro3;
	private JTextField txtMiembro4;
	private JTextField txtMiembro5;
	private JTextField txtMiembro6;
	private JTextField txtMiembro7;
	private JTextField txtMiembro9;
	private JTextField txtNombreGrupo;
	
	public createGroup() {
		setSize(500,500);
		setTitle("crear Grupo");
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(38, 63, 61, 16);
		getContentPane().add(lblNombre);
		
		JLabel lblMiembro = new JLabel("Miembro 1:");
		lblMiembro.setBounds(38, 132, 77, 16);
		getContentPane().add(lblMiembro);
		
		txtMiembro1 = new JTextField();
		txtMiembro1.setBounds(34, 150, 130, 26);
		getContentPane().add(txtMiembro1);
		txtMiembro1.setColumns(10);
		
		JLabel lblMiembro_1 = new JLabel("Miembro 2:");
		lblMiembro_1.setBounds(183, 132, 77, 16);
		getContentPane().add(lblMiembro_1);
		
		txtMiembro2 = new JTextField();
		txtMiembro2.setBounds(176, 150, 130, 26);
		getContentPane().add(txtMiembro2);
		txtMiembro2.setColumns(10);
		
		JLabel lblMiembro_2 = new JLabel("Miembro 3:");
		lblMiembro_2.setBounds(322, 132, 77, 16);
		getContentPane().add(lblMiembro_2);
		
		txtMiembro3 = new JTextField();
		txtMiembro3.setBounds(318, 150, 130, 26);
		getContentPane().add(txtMiembro3);
		txtMiembro3.setColumns(10);
		
		JLabel lblMiembro_3 = new JLabel("Miembro 4:");
		lblMiembro_3.setBounds(38, 229, 77, 16);
		getContentPane().add(lblMiembro_3);
		
		txtMiembro4 = new JTextField();
		txtMiembro4.setBounds(34, 246, 130, 26);
		getContentPane().add(txtMiembro4);
		txtMiembro4.setColumns(10);
		
		JLabel lblMiembro5 = new JLabel("Miembro 5:");
		lblMiembro5.setBounds(183, 229, 77, 16);
		getContentPane().add(lblMiembro5);
		
		txtMiembro5 = new JTextField();
		txtMiembro5.setBounds(183, 246, 130, 26);
		getContentPane().add(txtMiembro5);
		txtMiembro5.setColumns(10);
		
		JComboBox comboPermiso1 = new JComboBox();
		comboPermiso1.setToolTipText("Permiso");
		comboPermiso1.setBounds(38, 188, 126, 27);
		getContentPane().add(comboPermiso1);
		
		JComboBox comboPermiso2 = new JComboBox();
		comboPermiso2.setToolTipText("Permiso");
		comboPermiso2.setBounds(176, 188, 126, 27);
		getContentPane().add(comboPermiso2);
		
		JComboBox comboPermiso3 = new JComboBox();
		comboPermiso3.setToolTipText("Permiso");
		comboPermiso3.setBounds(316, 188, 126, 27);
		getContentPane().add(comboPermiso3);
		
		JComboBox comboPermiso4 = new JComboBox();
		comboPermiso4.setToolTipText("Permiso");
		comboPermiso4.setBounds(38, 283, 126, 27);
		getContentPane().add(comboPermiso4);
		
		JComboBox comboPermiso5 = new JComboBox();
		comboPermiso5.setToolTipText("Permiso");
		comboPermiso5.setBounds(183, 283, 126, 27);
		getContentPane().add(comboPermiso5);
		
		JLabel lblMiembro6 = new JLabel("Miembro 6:");
		lblMiembro6.setBounds(322, 229, 77, 16);
		getContentPane().add(lblMiembro6);
		
		txtMiembro6 = new JTextField();
		txtMiembro6.setBounds(322, 246, 130, 26);
		getContentPane().add(txtMiembro6);
		txtMiembro6.setColumns(10);
		
		JComboBox comboPermiso6 = new JComboBox();
		comboPermiso6.setToolTipText("Permiso");
		comboPermiso6.setBounds(322, 283, 126, 27);
		getContentPane().add(comboPermiso6);
		
		JLabel lblMiembro7 = new JLabel("Miembro 7:");
		lblMiembro7.setBounds(38, 340, 77, 16);
		getContentPane().add(lblMiembro7);
		
		txtMiembro7 = new JTextField();
		txtMiembro7.setBounds(38, 368, 130, 26);
		getContentPane().add(txtMiembro7);
		txtMiembro7.setColumns(10);
		
		JComboBox comboPermiso7 = new JComboBox();
		comboPermiso7.setToolTipText("Permiso");
		comboPermiso7.setBounds(38, 400, 126, 27);
		getContentPane().add(comboPermiso7);
		
		JLabel lblMiembro8 = new JLabel("Miembro 8:");
		lblMiembro8.setBounds(183, 340, 77, 16);
		getContentPane().add(lblMiembro8);
		
		txtMiembro9 = new JTextField();
		txtMiembro9.setBounds(183, 368, 130, 26);
		getContentPane().add(txtMiembro9);
		txtMiembro9.setColumns(10);
		
		JComboBox comboPermiso8 = new JComboBox();
		comboPermiso8.setToolTipText("Permiso");
		comboPermiso8.setBounds(183, 400, 126, 27);
		getContentPane().add(comboPermiso8);
		
		txtNombreGrupo = new JTextField();
		txtNombreGrupo.setBounds(111, 58, 130, 26);
		getContentPane().add(txtNombreGrupo);
		txtNombreGrupo.setColumns(10);
		
		JButton btnCrearGrupo = new JButton("Crear Grupo");
		btnCrearGrupo.setBounds(337, 381, 117, 29);
		getContentPane().add(btnCrearGrupo);
	}
	
	

}
