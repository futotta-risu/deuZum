 package io.github.fatsquirrels.deuzum.visual.Dialogs.Project;

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

import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JTextArea;

@Deprecated
public class editProyect extends JDialog{

	
	private static final long serialVersionUID = 1L;
	private JTextField txtIdProyecto;
	private JTextField txtIdGrupo;
	private JTextField txtNombre;
	private JLabel lblIntroducirIdProyecto;
	private JButton btnBuscar;
	private JLabel lblIdGrupo;
	private JLabel lblNombre;
	private JLabel lblDescripcion;
	private JTextArea areaDescripcion;
	private JButton btnGuardarCambios;
	private Connection conn;
	private HashMap<String,Component> componentMap;
	private JPanel proyectData;
	/**
	 * Crea un objeto de editProyect el cual contiene un Dialogo que permite editar la informacion de un proyecto
	 * buscandolo a traves de su ID.
	 */
	public editProyect(Connection c) {
		this.conn = c;
		setSize(500,500);
		setTitle("Editar Usuario");
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		initialize(conn);
		this.componentMap = ObjectMapper.createComponentMap(proyectData);
	}
	
	public void initialize(Connection c) {
		Container cp = this.proyectData;
		cp.setLayout(null);
		
		proyectData =new JPanel();
		proyectData.setBounds(0, 0, 700, 378);
		proyectData.setLayout(null);
		proyectData.setVisible(true);
		
		
		lblIntroducirIdProyecto = new JLabel("Introducir ID Proyecto:");
		lblIntroducirIdProyecto.setBounds(36, 41, 147, 16);
		proyectData.add(lblIntroducirIdProyecto);
		
		txtIdProyecto = new JTextField();
		txtIdProyecto.setBounds(195, 36, 130, 26);
		proyectData.add(txtIdProyecto);
		txtIdProyecto.setColumns(10);
		
		btnBuscar = new JButton("Buscar Proyecto");
		btnBuscar.setBounds(337, 36, 135, 29);
		proyectData.add(btnBuscar);
		
		lblIdGrupo = new JLabel("ID Grupo:");
		lblIdGrupo.setBounds(36, 116, 90, 16);
		proyectData.add(lblIdGrupo);
		
		lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(36, 168, 61, 16);
		proyectData.add(lblNombre);
		
		lblDescripcion = new JLabel("Descripcion:");
		lblDescripcion.setBounds(18, 229, 79, 16);
		proyectData.add(lblDescripcion);
		
		txtIdGrupo = new JTextField();
		txtIdGrupo.setBounds(138, 111, 130, 26);
		proyectData.add(txtIdGrupo);
		txtIdGrupo.setColumns(10);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(138, 163, 130, 26);
		proyectData.add(txtNombre);
		txtNombre.setColumns(10);
		
		areaDescripcion = new JTextArea();
		areaDescripcion.setBounds(138, 229, 309, 167);
		proyectData.add(areaDescripcion);
		
		btnGuardarCambios = new JButton("Guardar Cambios");
		btnGuardarCambios.setBounds(169, 418, 147, 29);
		proyectData.add(btnGuardarCambios);
		
		btnBuscar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String proyectID = txtIdProyecto.getText();
				try {
					ResultSet resultProyecto = GeneralSQLFunctions.getResultSetEntryFromDatabase(c, "proyecto", "ID= '" + proyectID +"'");
					while(resultProyecto.next()) {
						txtIdProyecto.setText(resultProyecto.getString("id"));
						txtIdGrupo.setText(resultProyecto.getString("id_grupo"));
						txtNombre.setText(resultProyecto.getString("nombre"));
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			}
			
		});
		
		btnGuardarCambios.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				editarProyecto(conn);
				
			}
			
		});
	
	}
	
	public void editarProyecto(Connection conn) {
		//Comprobar que los campos no queden vacios
				ArrayList<APair<String,String>> compulsoryVars = new ArrayList<>();
				compulsoryVars.add(new APair<String,String> ("txtIdProyecto", "Id del proyecto"));
				compulsoryVars.add(new APair<String,String> ("txtIdGrupo","Id del grupo"));
				compulsoryVars.add(new APair<String,String> ("txtNombre","Nombre del proyecto"));
				compulsoryVars.add(new APair<String,String>("txtDescripcion", "Descripcion del proyecto"));
				
				int nError = 0;
				for (APair<String, String> i : compulsoryVars) {
					JTextField tempC = (JTextField) ObjectMapper.getComponentByName(i.getIndex(), componentMap);
					if(tempC != null) {
					if(tempC.getText().isEmpty()) {
						System.err.println("Error " + String.valueOf(++nError) + ": No ha indicado ninguna respuesta en " + i.getValue());
					}	
				}
				
			}
				//Comproba si el formato usado es el correcto
				ArrayList<APair<String,ConcreteText>> formatVars = new ArrayList<>();
				formatVars.add(new APair<String,ConcreteText>("txtIdProyecto",new ConcreteText("Id del proyecto",TextTypes.ID)));
				formatVars.add(new APair<String,ConcreteText>("txtIdGrupo",new ConcreteText("Id del grupo",TextTypes.ID)));
				formatVars.add(new APair<String,ConcreteText>("txtNombre",new ConcreteText("Nombre del grupo",TextTypes.NAME)));
				formatVars.add(new APair<String,ConcreteText>("txtDescripcion",new ConcreteText("Descripcion del proyecto",TextTypes.DESCRIPTION)));

				for (APair<String, ConcreteText> i : formatVars) {
					JTextField tempC = (JTextField) ObjectMapper.getComponentByName(i.getIndex(), componentMap);
					if(tempC!=null) {
					if(tempC.getText().isEmpty() && !ConcreteText.isValid(tempC.getText(), i.getValue().getTextType())) {
						System.err.println("Error " + String.valueOf(++nError) + ": No ha indicado ninguna respuesta en " + i.getValue());				
					}
					}
					
					//Si no hay errores lo enviamos
					String proyectoID = txtIdProyecto.getText();
					String grupoID = txtIdGrupo.getText();
					String nombreProyecto = txtNombre.getText();
				
					if(nError==0) {
						ServerUserFunctionality.updateProyect(conn, proyectoID, new String[] {grupoID, nombreProyecto});
			
						JOptionPane.showMessageDialog(null, "Proyecto actualizado con exito", "ACTUALIZADO", 1);
					}
					}
		
	}
	
}
