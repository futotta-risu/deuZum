package io.github.fatsquirrels.deuzum.visual.Dialogs.Group;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import io.github.fatsquirrels.deuzum.database.GeneralSQLFunctions;
import io.github.fatsquirrels.deuzum.net.ServerUserFunctionality;
import io.github.fatsquirrels.deuzum.utils.ObjectMapper;
import io.github.fatsquirrels.deuzum.utils.math.APair;
import io.github.fatsquirrels.deuzum.utils.text.ConcreteText;
import io.github.fatsquirrels.deuzum.utils.text.TextTypes;

import javax.swing.JComboBox;

import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

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
	private Connection conn;
	private HashMap<String, Component> componentMap; 
	private JPanel groupData;
	
	/**
	 * Crea un objeto de createGroup, el cual contiene un Dialogo que permite crear un grupo nuevo.
	 */

	public createGroup(Connection conn) {
		this.conn= conn;
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setSize(500,500);
		setTitle("Crear Grupo");
		setVisible(true);
		
		initialize(conn);
		this.componentMap = ObjectMapper.createComponentMap(groupData);
	}
	
	public void initialize(Connection conn) {
		
		
		Container cp = this.getContentPane();
		cp.setLayout(null);

		groupData = new JPanel();
		groupData.setBounds(0, 0, 700, 372);
		groupData.setLayout(null);
		groupData.setVisible(true);
		
		lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(38, 63, 61, 16);
		groupData.add(lblNombre);
		
		lblMiembro1 = new JLabel("Miembro 1:");
		lblMiembro1.setBounds(38, 132, 77, 16);
		groupData.add(lblMiembro1);
		
		txtMiembro1 = new JTextField();
		txtMiembro1.setBounds(34, 150, 130, 26);
		groupData.add(txtMiembro1);
		txtMiembro1.setColumns(10);
		
		lblMiembro2 = new JLabel("Miembro 2:");
		lblMiembro2.setBounds(183, 132, 77, 16);
		groupData.add(lblMiembro2);
		
		txtMiembro2 = new JTextField();
		txtMiembro2.setBounds(176, 150, 130, 26);
		groupData.add(txtMiembro2);
		txtMiembro2.setColumns(10);
		
		lblMiembro3 = new JLabel("Miembro 3:");
		lblMiembro3.setBounds(322, 132, 77, 16);
		groupData.add(lblMiembro3);
		
		txtMiembro3 = new JTextField();
		txtMiembro3.setBounds(318, 150, 130, 26);
		groupData.add(txtMiembro3);
		txtMiembro3.setColumns(10);
		
		lblMiembro4 = new JLabel("Miembro 4:");
		lblMiembro4.setBounds(38, 229, 77, 16);
		groupData.add(lblMiembro4);
		
		txtMiembro4 = new JTextField();
		txtMiembro4.setBounds(34, 246, 130, 26);
		groupData.add(txtMiembro4);
		txtMiembro4.setColumns(10);
		
		lblMiembro5 = new JLabel("Miembro 5:");
		lblMiembro5.setBounds(183, 229, 77, 16);
		groupData.add(lblMiembro5);
		
		txtMiembro5 = new JTextField();
		txtMiembro5.setBounds(183, 246, 130, 26);
		groupData.add(txtMiembro5);
		txtMiembro5.setColumns(10);
		
	
		lblMiembro6 = new JLabel("Miembro 6:");
		lblMiembro6.setBounds(322, 229, 77, 16);
		groupData.add(lblMiembro6);
		
		txtMiembro6 = new JTextField();
		txtMiembro6.setBounds(322, 246, 130, 26);
		groupData.add(txtMiembro6);
		txtMiembro6.setColumns(10);
		
		lblMiembro7 = new JLabel("Miembro 7:");
		lblMiembro7.setBounds(38, 340, 77, 16);
		groupData.add(lblMiembro7);
		
		txtMiembro7 = new JTextField();
		txtMiembro7.setBounds(38, 368, 130, 26);
		groupData.add(txtMiembro7);
		txtMiembro7.setColumns(10);
		
		
		lblMiembro8 = new JLabel("Miembro 8:");
		lblMiembro8.setBounds(183, 340, 77, 16);
		groupData.add(lblMiembro8);
		
		txtMiembro9 = new JTextField();
		txtMiembro9.setBounds(183, 368, 130, 26);
		groupData.add(txtMiembro9);
		txtMiembro9.setColumns(10);

		//Permiso administrador es el que va a poder añadir a mas gente en el grupo, puede crear proyectos dentro del grupo
		String permisoAd = "administrador";
		//Permiso participante es el que esta en el grupo
		String permisoPar = "participante";
		
		comboPermiso1 = new JComboBox<String>();
		comboPermiso1.setToolTipText("Permiso");
		comboPermiso1.setBounds(38, 188, 126, 27);
		groupData.add(comboPermiso1);
		
		comboPermiso1.addItem(permisoAd);
		comboPermiso1.addItem(permisoPar);
	
		
		comboPermiso2 = new JComboBox<String>();
		comboPermiso2.setToolTipText("Permiso");
		comboPermiso2.setBounds(176, 188, 126, 27);
		groupData.add(comboPermiso2);	
		
		comboPermiso2.addItem(permisoAd);
		comboPermiso2.addItem(permisoPar);
	
		comboPermiso3 = new JComboBox<String>();
		comboPermiso3.setToolTipText("Permiso");
		comboPermiso3.setBounds(316, 188, 126, 27);
		groupData.add(comboPermiso3);
		
		comboPermiso3.addItem(permisoAd);
		comboPermiso3.addItem(permisoPar);
	
		
		comboPermiso4 = new JComboBox<String>();
		comboPermiso4.setToolTipText("Permiso");
		comboPermiso4.setBounds(38, 283, 126, 27);
		groupData.add(comboPermiso4);
		
		comboPermiso4.addItem(permisoAd);
		comboPermiso4.addItem(permisoPar);
		
		comboPermiso5 = new JComboBox<String>();
		comboPermiso5.setToolTipText("Permiso");
		comboPermiso5.setBounds(183, 283, 126, 27);
		groupData.add(comboPermiso5);
		
		comboPermiso5.addItem(permisoAd);
		comboPermiso5.addItem(permisoPar);
		
		comboPermiso6 = new JComboBox<String>();
		comboPermiso6.setToolTipText("Permiso");
		comboPermiso6.setBounds(322, 283, 126, 27);
		groupData.add(comboPermiso6);
		
		comboPermiso6.addItem(permisoAd);
		comboPermiso6.addItem(permisoPar);
	
		comboPermiso7 = new JComboBox<String>();
		comboPermiso7.setToolTipText("Permiso");
		comboPermiso7.setBounds(38, 400, 126, 27);
		groupData.add(comboPermiso7);
		
		comboPermiso7.addItem(permisoAd);
		comboPermiso7.addItem(permisoPar);
	
		comboPermiso8 = new JComboBox<String>();
		comboPermiso8.setToolTipText("Permiso");
		comboPermiso8.setBounds(183, 400, 126, 27);
		groupData.add(comboPermiso8);
		
		comboPermiso8.addItem(permisoAd);
		comboPermiso8.addItem(permisoPar);
		
		txtNombreGrupo = new JTextField();
		txtNombreGrupo.setBounds(111, 58, 130, 26);
		groupData.add(txtNombreGrupo);
		txtNombreGrupo.setColumns(10);
		
		btnCrearGrupo = new JButton("Crear Grupo");
		btnCrearGrupo.setBounds(337, 381, 117, 29);
		getContentPane().add(btnCrearGrupo);
		
		cp.add(groupData);	

		btnCrearGrupo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				crearGrupo();
			}
			
		});
		
		}
	
	public void crearGrupo() {
		
		//Para comprobar si hay textos obligatorios sin rellenar
		ArrayList<APair<String, String>>  compulsoryVars = new ArrayList<>();
		compulsoryVars.add((new APair<String,String>("txtNombreGrupo","Nombre del grupo")));
		compulsoryVars.add((new APair<String,String>("txtMiembro1","Nombre de usuario del miembro 1")));
		compulsoryVars.add((new APair<String,String>("txtMiembro2","Nombre de usuario del miembro 2")));
		
		int nError = 0;
		for (APair<String, String> i : compulsoryVars) {
			JTextField tempC = (JTextField) ObjectMapper.getComponentByName(i.getIndex(), componentMap);
			if(tempC != null) {
			if(tempC.getText().isEmpty()) {
				System.err.println("Error " + String.valueOf(++nError) + ": No ha indicado ninguna respuesta en " + i.getValue());
			}
		}
		}
		
		//Para comprobar si el formato usado es el correcto
		ArrayList<APair<String,ConcreteText>>  formatVars = new ArrayList<>();
		formatVars.add(new APair<String, ConcreteText>("txtNombreGrupo", new ConcreteText("Nombre del Grupo",TextTypes.NAME)));
		formatVars.add(new APair<String, ConcreteText>("txtMiembro1", new ConcreteText("Nombre de usuario del miembro 1",TextTypes.USER)));
		formatVars.add(new APair<String, ConcreteText>("txtMiembro2", new ConcreteText("Nombre de usuario del miembro 2",TextTypes.USER)));
		formatVars.add(new APair<String, ConcreteText>("txtMiembro3", new ConcreteText("Nombre de usuario del miembro 3",TextTypes.USER)));
		formatVars.add(new APair<String, ConcreteText>("txtMiembro4", new ConcreteText("Nombre de usuario del miembro 4",TextTypes.USER)));
		formatVars.add(new APair<String, ConcreteText>("txtMiembro5", new ConcreteText("Nombre de usuario del miembro 5",TextTypes.USER)));
		formatVars.add(new APair<String, ConcreteText>("txtMiembro6", new ConcreteText("Nombre de usuario del miembro 6",TextTypes.USER)));
		formatVars.add(new APair<String, ConcreteText>("txtMiembro7", new ConcreteText("Nombre de usuario del miembro 7",TextTypes.USER)));
		formatVars.add(new APair<String, ConcreteText>("txtMiembro8", new ConcreteText("Nombre de usuario del miembro 8",TextTypes.USER)));
		
		for (APair<String, ConcreteText> i : formatVars) {
			JTextField tempC = (JTextField) ObjectMapper.getComponentByName(i.getIndex(), componentMap);
			if(tempC!=null) {
			if(tempC.getText().isEmpty() && !ConcreteText.isValid(tempC.getText(), i.getValue().getTextType())) {
				System.err.println("Error " + String.valueOf(++nError) + ": No ha indicado ninguna respuesta en " + i.getValue());				
			}
			}
		}
		
		//Si no hay ningun error lo enviamos
		
		if(nError==0) {
			//Añadir nombre del grupo a la tabla grupo
			ServerUserFunctionality.createGroup(conn, new String[] {txtNombreGrupo.getText(), txtDescripcion.getText()});
			
			//Buscamos el grupo creado para encontrar el id que se ha generado
			String groupID = null;
			try {
				groupID = GeneralSQLFunctions.getEntryFromDatabase(conn, "grupo", "id", "grupo = '" + txtNombreGrupo.getText() + "'");
				System.out.println(groupID);
			} catch (SQLException e) {	
				e.printStackTrace();
			}

			System.out.println(groupID);
			
			//Buscamos el id de los usuarios que van a formar parte del grupo
			
			try {
			String[] txtMiembros = new String[7];
			txtMiembros[0] = txtMiembro1.getText();
			txtMiembros[1] = txtMiembro2.getText();
			txtMiembros[2] = txtMiembro3.getText();
			txtMiembros[3] = txtMiembro4.getText();
			txtMiembros[4] = txtMiembro5.getText();
			txtMiembros[5] = txtMiembro6.getText();
			txtMiembros[6] = txtMiembro7.getText();
			txtMiembros[7] = txtMiembro9.getText();
			
			String userId[] = new String[7];
			
			for (int i = 0; i < txtMiembros.length; i++) {
				if(!(txtMiembros[i].isEmpty())) {
				userId[i] = GeneralSQLFunctions.getEntryFromDatabase(conn, "usuario", "id", "usuario= '" + txtMiembros[i] + "'");
				}
			}
			
			String groupId;
			for (int i = 0; i < txtMiembros.length; i++) {
				if(txtMiembros[i]!=null) {
				groupId = GeneralSQLFunctions.getEntryFromDatabase(conn, "grupo", "id", "usuario= '" + txtMiembros[i] + "'");
				System.out.println(groupId);
				}
			}
			String permisos[] = new String[7];
				permisos[0] = (String) comboPermiso1.getSelectedItem();
				permisos[1] = (String) comboPermiso2.getSelectedItem();
				permisos[2] = (String) comboPermiso3.getSelectedItem();
				permisos[3] = (String) comboPermiso4.getSelectedItem();
				permisos[4] = (String) comboPermiso5.getSelectedItem();
				permisos[5] = (String) comboPermiso6.getSelectedItem();
				permisos[6] = (String) comboPermiso7.getSelectedItem();
				permisos[7] = (String) comboPermiso8.getSelectedItem();
				
			for (int j = 0; j < userId.length; j++) {
				ServerUserFunctionality.addToGroup(conn, userId[j], groupID, permisos[j]);
			
			}
				}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		JOptionPane.showMessageDialog(null, "Grupo registrado con exito", "REGISTRADO", 1);
		dispose();
		
	}
	
	public static void main(String[] args) {
		
	}
	}
