package io.github.fatsquirrels.deuzum.visual.panels;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;


import io.github.fatsquirrels.deuzum.visual.components.buttons.FlatButton;
import io.github.fatsquirrels.deuzum.visual.statistics.GraphFunctions;

public class FunctionalityPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4541688928942198726L;

	public FunctionalityPanel() {
		JTabbedPane mainPanel = new JTabbedPane();
		JPanel panel_Funct_Visual = new JPanel();
		mainPanel.addTab("Visualizacion de datos", null, panel_Funct_Visual, null);
		
		
		
		//		BOTONES FUNCIONALIDADES

		panel_Funct_Visual.add(new FlatButton("Visualizar Grafico de Transaciones", e->GraphFunctions.createGraphTransacciones()));
		panel_Funct_Visual.add(new FlatButton("Visualizar Grafico de cantidades transferidas por usuario",e->GraphFunctions.createGraphUsuario()));
		panel_Funct_Visual.add(new FlatButton("Visualizar Grafico de Permisos", e-> GraphFunctions.createGraphPermisos()));
		panel_Funct_Visual.add(new FlatButton("Visualizar Grafico de Usuarios registrados en el tiempo", e->GraphFunctions.createGraphUsuariosXTiempo()));
		panel_Funct_Visual.add(new FlatButton("Visualizar Grafico de Aportaciones en Proyecto",e -> GraphFunctions.createGraphAportaciones()));
		

		JPanel panel_Funct_Database = new JPanel();
		mainPanel.addTab("Base de Datos", panel_Funct_Database);
		
		JButton btnRellenarBd = new FlatButton("Rellenar BD");
		panel_Funct_Database.add(btnRellenarBd);
		
		JPanel panel_Funct_IA = new JPanel();
		mainPanel.addTab("IA", panel_Funct_IA);
		
		JButton btnClusterizarDb = new JButton("Clusterizar DB");
		panel_Funct_IA.add(btnClusterizarDb);
		
		JPanel panel_Funct_Seguridad = new JPanel(new FlowLayout());
		
		JButton btnEncriptar = new JButton("Encriptar");
		JButton btnDesencriptar = new JButton("Desencriptar");
		
		panel_Funct_Seguridad.add(btnEncriptar);
		panel_Funct_Seguridad.add(btnDesencriptar);
		
		mainPanel.addTab("Seguridad", panel_Funct_Seguridad);
		
		setLayout(new BorderLayout());
		add(mainPanel,BorderLayout.CENTER);
	}
}
