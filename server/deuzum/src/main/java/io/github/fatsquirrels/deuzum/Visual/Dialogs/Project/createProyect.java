package io.github.fatsquirrels.deuzum.visual.Dialogs.Project;

import javax.swing.JDialog;
import javax.swing.JLabel;
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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;

public class createProyect extends JDialog{

	
	private static final long serialVersionUID = 1L;
	private JTextField txtIdGrupo;
	private JTextField txtNombre;
	private JTextField txtDescripcion;
	private JButton btnCrearProyecto;
	private JLabel lblIdGrupo;
	private JLabel lblNombre;
	private JLabel lblDescripcion;
	private Connection conn;
	private HashMap<String, Component> componentMap; 
	private JPanel proyectData;
	
	
	/**
	 * Crea un objeto de createProyect, este Contiene un Dialogo que permite crear un nuevo Proyecto.
	 */
	public createProyect(Connection conn) {
		this.conn = conn;
		setSize(500,430);
		setTitle("Crear Proyecto");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);
		
		initialize(conn);
		this.componentMap = ObjectMapper.createComponentMap(proyectData);
	}

	public void initialize (Connection conn) {
		
		Container cp = this.getContentPane();
		cp.setLayout(null);
		
		proyectData = new JPanel();
		proyectData.setBounds(0, 0, 700, 372);
		proyectData.setLayout(null);
		proyectData.setVisible(true);
		
		
		lblIdGrupo = new JLabel("ID grupo:");
		lblIdGrupo.setBounds(67, 65, 94, 26);
		proyectData.add(lblIdGrupo);
		
		lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(67, 128, 61, 16);
		proyectData.add(lblNombre);
		
		lblDescripcion = new JLabel("Descripcion:");
		lblDescripcion.setBounds(49, 185, 79, 16);
		proyectData.add(lblDescripcion);
		
		txtIdGrupo = new JTextField();
		txtIdGrupo.setBounds(173, 65, 130, 26);
		proyectData.add(txtIdGrupo);
		txtIdGrupo.setColumns(10);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(173, 123, 130, 26);
		proyectData.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtDescripcion = new JTextField();
		txtDescripcion.setBounds(173, 180, 281, 139);
		proyectData.add(txtDescripcion);
		txtDescripcion.setColumns(10);
		
		btnCrearProyecto = new JButton("Crear Proyecto");
		btnCrearProyecto.setBounds(158, 366, 145, 29);
		getContentPane().add(btnCrearProyecto);
		
		cp.add(proyectData);
		
		btnCrearProyecto.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				crearProyecto();
				
			}
			
		});
	}
	
	public void crearProyecto() {
	
		
		//Comprobar que los campos no queden vacios
		ArrayList<APair<String,String>> compulsoryVars = new ArrayList<>();
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
		
			String grupoID = txtIdGrupo.getText();
			String nombreProyecto = txtNombre.getText();
			String descripcion = txtDescripcion.getText();
		
			if(nError==0) {
				ServerUserFunctionality.createProyect(conn, new String[] {grupoID, nombreProyecto, descripcion});
			}
		}
}
}
