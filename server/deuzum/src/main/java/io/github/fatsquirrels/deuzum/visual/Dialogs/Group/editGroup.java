package io.github.fatsquirrels.deuzum.visual.Dialogs.Group;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import io.github.fatsquirrels.deuzum.database.GeneralSQLFunctions;
import io.github.fatsquirrels.deuzum.net.ServerUserFunctionality;
import io.github.fatsquirrels.deuzum.utils.ObjectMapper;
import io.github.fatsquirrels.deuzum.utils.math.APair;
import io.github.fatsquirrels.deuzum.utils.text.ConcreteText;
import io.github.fatsquirrels.deuzum.utils.text.TextTypes;

import javax.swing.JComboBox;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;

@Deprecated
public class editGroup extends JDialog{

	private static final long serialVersionUID = 1L;
	private JTextField txtMiembro1;
	private JTextField txtMiembro2;
	private JTextField txtMiembro3;
	private JTextField txtMiembro4;
	private JTextField txtMiembro5;
	private JTextField txtMiembro6;
	private JTextField txtMiembro7;
	private JTextField txtMiembro8;
	private JTextField txtNombreGrupo;
	private JTextField txtIdGrupo;
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
	private JButton btnGuardarCambio;
	private JLabel lblIntroducirIdGrupo;
	private JButton btonBuscarGrupo;
	private String groupID;
	private HashMap<String,Component> componentMap;
	private Connection conn;

	/**
	 * Crea un objeto de editGroup, el cual contiene un Dialogo que permite buscar un grupo por su
	 * ID y modificar todos los datos de este.
	 */
	public editGroup(Connection c) {
		setSize(500,500);
		setTitle("Editar Grupo");
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		
		lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(38, 104, 61, 16);
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
		
		txtMiembro8 = new JTextField();
		txtMiembro8.setBounds(183, 368, 130, 26);
		getContentPane().add(txtMiembro8);
		txtMiembro8.setColumns(10);
		
		
		
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
		txtNombreGrupo.setBounds(111, 99, 130, 26);
		getContentPane().add(txtNombreGrupo);
		txtNombreGrupo.setColumns(10);
		
		btnGuardarCambio = new JButton("Guardar Cambios");
		btnGuardarCambio.setBounds(337, 381, 143, 29);
		getContentPane().add(btnGuardarCambio);
		
		lblIntroducirIdGrupo = new JLabel("Introducir ID Grupo:");
		lblIntroducirIdGrupo.setBounds(38, 53, 130, 16);
		getContentPane().add(lblIntroducirIdGrupo);
		
		txtIdGrupo = new JTextField();
		txtIdGrupo.setBounds(183, 48, 130, 26);
		getContentPane().add(txtIdGrupo);
		txtIdGrupo.setColumns(10);
		
		btonBuscarGrupo= new JButton("Buscar Grupo");
		btonBuscarGrupo.setBounds(337, 48, 117, 29);
		getContentPane().add(btonBuscarGrupo);
		
		btonBuscarGrupo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Thread hiloBuscar = new Thread(new Runnable() {
					
					@Override
					public void run() {

						groupID = txtIdGrupo.getText();
						ResultSet resultGroup = null;
						ResultSet resultGroupMiembros = null;
						try {
							resultGroup = GeneralSQLFunctions.getResultSetEntryFromDatabase(conn, "grupo", "ID = '" + groupID + "'" );
							resultGroupMiembros = GeneralSQLFunctions.getResultSetEntryFromDatabase(conn, "grupomiembro" , "ID = '" + groupID + "'");
							while(resultGroup.next()) {
								txtNombreGrupo.setText(resultGroup.getString("grupo")); 
							}
							while(resultGroupMiembros.next()) {
								ArrayList<String> idTemp = new ArrayList<String>();
								idTemp.add((resultGroupMiembros.getString("id_miembro")));
								ArrayList<String> username = new ArrayList<String>();
								username.add(GeneralSQLFunctions.getEntryFromDatabase(conn, "usuario", "usuario", "ID = '" + idTemp + "'"));
								
							}
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						
						
					}
				});
						
				}
			
		});
		btnGuardarCambio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				editarGrupo(conn);
			}
			
		});
	}
	
	public void editarGrupo(Connection conn) {
		//Comprobar campos vacios
		APair<?,?> [] compulsaryVars = {
				new APair<String, String> ("txtNombreGrupo","Nombre del grupo"), new APair<String, String> ("txtMiembro1", "Nombre de usuario del miembro 1"), 
				new APair<String,String>("txtMiembro2","Nombre de usuario del miembro 2")};
		int nError = 0;
		for (APair<?,?> i : compulsaryVars) {
			JTextField tempC = (JTextField) ObjectMapper.getComponentByName(String.valueOf(i.getIndex()),componentMap);
			if(tempC != null) {
				if(tempC.getText().isEmpty()) {
					System.err.println("Error " + String.valueOf(++nError) + ": No ha indicado ninguna respuesta en " + i.getValue());
				}
			}
		}
		
		APair [] formatVars = {
			new APair<String, ConcreteText>("txtNombreGrupo", new ConcreteText("Nombre del Grupo",TextTypes.NAME)),
			new APair<String, ConcreteText>("txtMiembro1", new ConcreteText("Nombre de usuario del miembro 1",TextTypes.USER)),
			new APair<String, ConcreteText>("txtMiembro2", new ConcreteText("Nombre de usuario del miembro 2",TextTypes.USER)),
			new APair<String, ConcreteText>("txtMiembro3", new ConcreteText("Nombre de usuario del miembro 3",TextTypes.USER)),
			new APair<String, ConcreteText>("txtMiembro4", new ConcreteText("Nombre de usuario del miembro 4",TextTypes.USER)),
			new APair<String, ConcreteText>("txtMiembro5", new ConcreteText("Nombre de usuario del miembro 5",TextTypes.USER)),
			new APair<String, ConcreteText>("txtMiembro6", new ConcreteText("Nombre de usuario del miembro 6",TextTypes.USER)),
			new APair<String, ConcreteText>("txtMiembro7", new ConcreteText("Nombre de usuario del miembro 7",TextTypes.USER)),
			new APair<String, ConcreteText>("txtMiembro8", new ConcreteText("Nombre de usuario del miembro 8",TextTypes.USER))};
			
		int txtFieldRelleno = 0;
		ArrayList<String> usernames= new ArrayList<String>();
				for (APair<String, ConcreteText> i : formatVars) {
					JTextField tempC = (JTextField) ObjectMapper.getComponentByName(i.getIndex(), componentMap);
					if(tempC!=null) {
						txtFieldRelleno++;
						usernames.add(tempC.getText());
					if(tempC.getText().isEmpty() && !ConcreteText.isValid(tempC.getText(), i.getValue().getTextType())) {
						System.err.println("Error " + String.valueOf(++nError) + ": No ha indicado ninguna respuesta en " + i.getValue());				
					}
					}
				}
				
				//Si no hay errores lo enviamos
				if(nError == 0 ) {
					ServerUserFunctionality.updateGroup(conn, groupID, new String [] {txtNombreGrupo.getText()});
					
					String[] userId = new String[7];
					for (int i = 0; i < userId.length; i++) {					
					try {
						userId[i] = GeneralSQLFunctions.getEntryFromDatabase(conn, "usuario", "id", "usuario= '" + usernames.get(i) + "'");
					} catch (SQLException e) {

						e.printStackTrace();
					}
					}
				
					
					String[] comboPermisos = {comboPermiso1.getSelectedItem().toString(),comboPermiso2.getSelectedItem().toString(),comboPermiso3.getSelectedItem().toString(),
							comboPermiso4.getSelectedItem().toString(),comboPermiso5.getSelectedItem().toString(),comboPermiso6.getSelectedItem().toString(),
							comboPermiso7.getSelectedItem().toString(),comboPermiso8.getSelectedItem().toString()};
					
						ServerUserFunctionality.updateGroupMiembros(conn, groupID, userId , comboPermisos);
					
					JOptionPane.showMessageDialog(null, "Grupo actualizado con exito", "ACTUALIZADO", 1);
				}
	}
	
}