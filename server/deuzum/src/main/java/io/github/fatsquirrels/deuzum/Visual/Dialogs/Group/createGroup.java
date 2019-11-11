package io.github.fatsquirrels.deuzum.Visual.Dialogs.Group;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.JButton;

public class createGroup extends JDialog{

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
	private JLabel lblNombre;
	private JLabel lblMiembro1;
	private JLabel lblMiembro2;
	private JLabel lblMiembro3;
	private JLabel lblMiembro4;
	private JLabel lblMiembro5;
	private JLabel lblMiembro6;
	private JLabel lblMiembro7;
	private JLabel lblMiembro8;
	private JComboBox<String> comboPermiso1;
	private JComboBox<String> comboPermiso2;
	private JComboBox<String> comboPermiso3;
	private JComboBox<String> comboPermiso4;
	private JComboBox<String> comboPermiso5;
	private JComboBox<String> comboPermiso6;
	private JComboBox<String> comboPermiso7;
	private JComboBox<String> comboPermiso8;
	private JButton btnCrearGrupo;


	/**
	 * Crea un objeto de createGroup, el cual contiene un Dialogo que permite crear un grupo nuevo.
	 */
	public createGroup(Connection c) {
		setSize(500,500);
		setTitle("crear Grupo");
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		
		lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(38, 63, 61, 16);
		getContentPane().add(lblNombre);
		
		lblMiembro1 = new JLabel("Miembro 1:");
		lblMiembro1.setBounds(38, 132, 77, 16);
		getContentPane().add(lblMiembro1);
		
		txtMiembro1 = new JTextField();
		txtMiembro1.setBounds(34, 150, 130, 26);
		getContentPane().add(txtMiembro1);
		txtMiembro1.setColumns(10);
		
		lblMiembro2 = new JLabel("Miembro 2:");
		lblMiembro2.setBounds(183, 132, 77, 16);
		getContentPane().add(lblMiembro2);
		
		txtMiembro2 = new JTextField();
		txtMiembro2.setBounds(176, 150, 130, 26);
		getContentPane().add(txtMiembro2);
		txtMiembro2.setColumns(10);
		
		lblMiembro3 = new JLabel("Miembro 3:");
		lblMiembro3.setBounds(322, 132, 77, 16);
		getContentPane().add(lblMiembro3);
		
		txtMiembro3 = new JTextField();
		txtMiembro3.setBounds(318, 150, 130, 26);
		getContentPane().add(txtMiembro3);
		txtMiembro3.setColumns(10);
		
		lblMiembro4 = new JLabel("Miembro 4:");
		lblMiembro4.setBounds(38, 229, 77, 16);
		getContentPane().add(lblMiembro4);
		
		txtMiembro4 = new JTextField();
		txtMiembro4.setBounds(34, 246, 130, 26);
		getContentPane().add(txtMiembro4);
		txtMiembro4.setColumns(10);
		
		lblMiembro5 = new JLabel("Miembro 5:");
		lblMiembro5.setBounds(183, 229, 77, 16);
		getContentPane().add(lblMiembro5);
		
		txtMiembro5 = new JTextField();
		txtMiembro5.setBounds(183, 246, 130, 26);
		getContentPane().add(txtMiembro5);
		txtMiembro5.setColumns(10);
		
	
		lblMiembro6 = new JLabel("Miembro 6:");
		lblMiembro6.setBounds(322, 229, 77, 16);
		getContentPane().add(lblMiembro6);
		
		txtMiembro6 = new JTextField();
		txtMiembro6.setBounds(322, 246, 130, 26);
		getContentPane().add(txtMiembro6);
		txtMiembro6.setColumns(10);
		
		lblMiembro7 = new JLabel("Miembro 7:");
		lblMiembro7.setBounds(38, 340, 77, 16);
		getContentPane().add(lblMiembro7);
		
		txtMiembro7 = new JTextField();
		txtMiembro7.setBounds(38, 368, 130, 26);
		getContentPane().add(txtMiembro7);
		txtMiembro7.setColumns(10);
		
		
		lblMiembro8 = new JLabel("Miembro 8:");
		lblMiembro8.setBounds(183, 340, 77, 16);
		getContentPane().add(lblMiembro8);
		
		txtMiembro9 = new JTextField();
		txtMiembro9.setBounds(183, 368, 130, 26);
		getContentPane().add(txtMiembro9);
		txtMiembro9.setColumns(10);
		
		comboPermiso1 = new JComboBox<String>();
		comboPermiso1.setToolTipText("Permiso");
		comboPermiso1.setBounds(38, 188, 126, 27);
		getContentPane().add(comboPermiso1);
		
		comboPermiso2 = new JComboBox<String>();
		comboPermiso2.setToolTipText("Permiso");
		comboPermiso2.setBounds(176, 188, 126, 27);
		getContentPane().add(comboPermiso2);
		
		comboPermiso3 = new JComboBox<String>();
		comboPermiso3.setToolTipText("Permiso");
		comboPermiso3.setBounds(316, 188, 126, 27);
		getContentPane().add(comboPermiso3);
		
		comboPermiso4 = new JComboBox<String>();
		comboPermiso4.setToolTipText("Permiso");
		comboPermiso4.setBounds(38, 283, 126, 27);
		getContentPane().add(comboPermiso4);
		
		comboPermiso5 = new JComboBox<String>();
		comboPermiso5.setToolTipText("Permiso");
		comboPermiso5.setBounds(183, 283, 126, 27);
		getContentPane().add(comboPermiso5);
		
		comboPermiso6 = new JComboBox<String>();
		comboPermiso6.setToolTipText("Permiso");
		comboPermiso6.setBounds(322, 283, 126, 27);
		getContentPane().add(comboPermiso6);
		
		comboPermiso7 = new JComboBox<String>();
		comboPermiso7.setToolTipText("Permiso");
		comboPermiso7.setBounds(38, 400, 126, 27);
		getContentPane().add(comboPermiso7);
		
		comboPermiso8 = new JComboBox<String>();
		comboPermiso8.setToolTipText("Permiso");
		comboPermiso8.setBounds(183, 400, 126, 27);
		getContentPane().add(comboPermiso8);
		
		txtNombreGrupo = new JTextField();
		txtNombreGrupo.setBounds(111, 58, 130, 26);
		getContentPane().add(txtNombreGrupo);
		txtNombreGrupo.setColumns(10);
		
		btnCrearGrupo = new JButton("Crear Grupo");
		btnCrearGrupo.setBounds(337, 381, 117, 29);
		getContentPane().add(btnCrearGrupo);

		btnCrearGrupo.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				
				boolean campoVacio = false;
				//Comprobar que estan en la base de datos los usuarios que van a unirse en los grupos
				//Parametros de la base de datos
				//TODO Avisar donde sale el fallo
				
				if(txtNombreGrupo.getText().equals("")) {
					lblNombre.setBackground(Color.RED);
					campoVacio = true;
				
				}else if(txtMiembro1.getText().equals("")) {
					lblMiembro1.setBackground(Color.RED);
					campoVacio = true;
					
				}else if(txtMiembro2.getText().equals("")) {
					lblMiembro2.setBackground(Color.RED);
					campoVacio = true;
					
				}else if(campoVacio) {
					JOptionPane.showMessageDialog(null, "No puede dejar este campo vac�o", "ERROR" , 0);
				}else {
					if(txtMiembro1.getText().length()>15/*?*/) {
						
					}
					
				}
			}
			
		});
		
	}
	

	

}
