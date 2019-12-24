package io.github.fatsquirrels.deuzum.visual.panels;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import io.github.fatsquirrels.deuzum.database.GeneralSQLFunctions;
import io.github.fatsquirrels.deuzum.utils.math.APair;
import io.github.fatsquirrels.deuzum.visual.components.buttons.FlatButton;
import io.github.fatsquirrels.deuzum.visual.statistics.GraficoTransaciones;
import io.github.fatsquirrels.deuzum.visual.statistics.GraficoTransacionesUsuario;

public class FunctionalityPanel extends JTabbedPane {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4541688928942198726L;

	public FunctionalityPanel() {
		
		JPanel panel_Funct_Visual = new JPanel();
		addTab("Visualizacion de datos", null, panel_Funct_Visual, null);
		
		
		
		//		BOTONES FUNCIONALIDADES
		
		FlatButton btnVisualizarGraficoTransaciones = new FlatButton("Visualizar Grafico de Transaciones");
		panel_Funct_Visual.add(btnVisualizarGraficoTransaciones);
		
		FlatButton btnVisualizarGraficoUsuario = new FlatButton("Visualizar Grafico de cantidades transferidas por usuario");
		panel_Funct_Visual.add(btnVisualizarGraficoUsuario);
		JPanel panel_Funct_Database = new JPanel();
		addTab("Base de Datos", null, panel_Funct_Database, null);
		
		
		btnVisualizarGraficoUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection conn = GeneralSQLFunctions.connectToDatabase("jdbc:mysql://localhost:3306/deuzumdb", "root", "");
				ArrayList<APair<Integer, Integer>> lista = new ArrayList<APair<Integer, Integer>>();
				try {
					ResultSet rs = GeneralSQLFunctions.getExecQuery(conn, "SELECT id FROM usuario ORDER BY id ASC");
					while(rs.next()) {
						int id = Integer.parseInt(rs.getString("id"));
						ResultSet rs2 = GeneralSQLFunctions.getExecQuery(conn, "SELECT dinero FROM transaccion WHERE source = "+ id);
						int cantidad = 0;
						while(rs2.next()) {
							System.out.println(rs2.getString("dinero"));
							if(!rs2.getString("dinero").equals(null)) {
								cantidad = cantidad + Integer.parseInt(rs2.getString("dinero"));	
							}else {
								cantidad = 0;
							}
							
						}	
						rs2.close();
						lista.add(new APair<Integer,Integer>(id, cantidad));
					}
					rs.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				new GraficoTransacionesUsuario(lista);
			}
		});
		
		btnVisualizarGraficoTransaciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection conn = GeneralSQLFunctions.connectToDatabase("jdbc:mysql://localhost:3306/deuzumdb", "root", "");
				List<Integer> cantidades = new ArrayList<Integer>();
				try {
					ResultSet rs = GeneralSQLFunctions.getExecQuery(conn, "SELECT dinero FROM transaccion ORDER BY dinero ASC");
					while(rs.next()) {
						cantidades.add(Integer.parseInt(rs.getString("dinero")));	
					}
					rs.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
				new GraficoTransaciones(cantidades);
			}
		});
		
		JButton btnRellenarBd = new FlatButton("Rellenar BD");
		panel_Funct_Database.add(btnRellenarBd);
		btnRellenarBd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub	
			}
		});
		
		JPanel panel_Funct_IA = new JPanel();
		addTab("IA", null, panel_Funct_IA, null);
		
		JButton btnClusterizarDb = new JButton("Clusterizar DB");
		panel_Funct_IA.add(btnClusterizarDb);
		
		JPanel panel_Funct_Seguridad = new JPanel();
		addTab("Seguridad", null, panel_Funct_Seguridad, null);
		GridBagLayout gbl_panel_Funct_Seguridad = new GridBagLayout();
		gbl_panel_Funct_Seguridad.columnWidths = new int[]{44, 75, 93, 0};
		gbl_panel_Funct_Seguridad.rowHeights = new int[]{23, 0, 0, 0, 0, 0};
		gbl_panel_Funct_Seguridad.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_Funct_Seguridad.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_Funct_Seguridad.setLayout(gbl_panel_Funct_Seguridad);
		
		JLabel lbl_Encriptar = new JLabel("Encriptaci\u00F3n");
		GridBagConstraints gbc_lbl_Encriptar = new GridBagConstraints();
		gbc_lbl_Encriptar.anchor = GridBagConstraints.WEST;
		gbc_lbl_Encriptar.gridwidth = 2;
		gbc_lbl_Encriptar.insets = new Insets(0, 0, 5, 0);
		gbc_lbl_Encriptar.gridx = 1;
		gbc_lbl_Encriptar.gridy = 1;
		panel_Funct_Seguridad.add(lbl_Encriptar, gbc_lbl_Encriptar);
		
		JButton btnEncriptar = new JButton("Encriptar");
		GridBagConstraints gbc_btnEncriptar = new GridBagConstraints();
		gbc_btnEncriptar.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnEncriptar.insets = new Insets(0, 0, 5, 5);
		gbc_btnEncriptar.gridx = 1;
		gbc_btnEncriptar.gridy = 2;
		panel_Funct_Seguridad.add(btnEncriptar, gbc_btnEncriptar);
		
		JButton btnDesencriptar = new JButton("Desencriptar");
		GridBagConstraints gbc_btnDesencriptar = new GridBagConstraints();
		gbc_btnDesencriptar.insets = new Insets(0, 0, 5, 0);
		gbc_btnDesencriptar.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnDesencriptar.gridx = 2;
		gbc_btnDesencriptar.gridy = 2;
		panel_Funct_Seguridad.add(btnDesencriptar, gbc_btnDesencriptar);
		
		JLabel lblNewLabel = new JLabel("New label");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 4;
		panel_Funct_Seguridad.add(lblNewLabel, gbc_lblNewLabel);
	}
}
