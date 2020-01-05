package io.github.fatsquirrels.deuzum.visual.panels;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import io.github.fatsquirrels.deuzum.database.GeneralSQLFunctions;
import io.github.fatsquirrels.deuzum.utils.math.APair;
import io.github.fatsquirrels.deuzum.visual.components.buttons.FlatButton;
import io.github.fatsquirrels.deuzum.visual.statistics.GraficoAportacionesProyecto;
import io.github.fatsquirrels.deuzum.visual.statistics.GraficoPermisos;
import io.github.fatsquirrels.deuzum.visual.statistics.GraficoTransaciones;
import io.github.fatsquirrels.deuzum.visual.statistics.GraficoTransacionesUsuario;
import io.github.fatsquirrels.deuzum.visual.statistics.GraficoUsuariosXTiempo;

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
		
		FlatButton btnVisualizarGraficoTransaciones = new FlatButton("Visualizar Grafico de Transaciones");
		panel_Funct_Visual.add(btnVisualizarGraficoTransaciones);
		
		FlatButton btnVisualizarGraficoUsuario = new FlatButton("Visualizar Grafico de cantidades transferidas por usuario");
		panel_Funct_Visual.add(btnVisualizarGraficoUsuario);
		
		FlatButton btnVisualizarGraficoPermisos = new FlatButton("Visualizar Grafico de Permisos");
		panel_Funct_Visual.add(btnVisualizarGraficoPermisos);
		
		FlatButton btnVisualizarGraficoUsuariosXTiempo = new FlatButton("Visualizar Grafico de Usuarios registrados en el tiempo");
		panel_Funct_Visual.add(btnVisualizarGraficoUsuariosXTiempo);
		
		FlatButton btnVisualizarGraficoAportaciones = new FlatButton("Visualizar Grafico de Aportaciones en Proyecto");
		panel_Funct_Visual.add(btnVisualizarGraficoAportaciones);
		

		JPanel panel_Funct_Database = new JPanel();
		mainPanel.addTab("Base de Datos", null, panel_Funct_Database, null);
		
		btnVisualizarGraficoAportaciones.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Connection conn = GeneralSQLFunctions.connectToDatabase("jdbc:mysql://localhost:3306/deuzumdb", "root", "");
				ArrayList<APair<Integer, Integer>> lista = new ArrayList<APair<Integer, Integer>>();
				
				int proyectId = Integer.parseInt(JOptionPane.showInputDialog("Introducir ID Proyecto"));
				try {
					ResultSet rs = GeneralSQLFunctions.getExecQuery(conn, "SELECT id_miembro,cantidad FROM proyectotransaccion WHERE id_proyecto = " + proyectId);
					while(rs.next()) {
						lista.add(new APair<Integer, Integer>(rs.getInt("id_miembro"), rs.getInt("cantidad")));
						System.out.println(rs.getInt("id_miembro") + "  " + rs.getInt("cantidad"));
					}
					rs.close();
				}catch(Exception ex) {
					ex.printStackTrace();
				}
				
				new GraficoAportacionesProyecto(lista);

			}
		});
		
		btnVisualizarGraficoUsuariosXTiempo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Connection conn = GeneralSQLFunctions.connectToDatabase("jdbc:mysql://localhost:3306/deuzumdb", "root", "");
				ArrayList<APair<String, Integer>> lista = new ArrayList<APair<String, Integer>>();
				
				try {
					ResultSet rs = GeneralSQLFunctions.getExecQuery(conn, "SELECT fecha_creacion FROM usuario ORDER BY fecha_creacion DESC");
					while(rs.next()) {
						String fecha = rs.getString("fecha_creacion");
						String[] fecha2= fecha.split(" ");
						lista.add(new APair<String, Integer>(fecha2[0], 1));
					}
					rs.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				new GraficoUsuariosXTiempo(lista);
			}
		});
		
		
		btnVisualizarGraficoUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection conn = GeneralSQLFunctions.connectToDatabase("jdbc:mysql://localhost:3306/deuzumdb", "root", "");
				ArrayList<APair<Integer, Integer>> lista = new ArrayList<APair<Integer, Integer>>();
				try {
					ResultSet rs = GeneralSQLFunctions.getExecQuery(conn, "SELECT id FROM usuario ORDER BY id ASC");
					while(rs.next()) {
						int id = Integer.parseInt(rs.getString("id"));
						ResultSet rs2 = GeneralSQLFunctions.getExecQuery(conn, "SELECT * FROM cuenta WHERE id_usuario = "+ id);
						int cantidadUsuario = 0;
						while(rs2.next()) {
							String accountId = rs2.getString("numero_cuenta");
							ResultSet rs3 = GeneralSQLFunctions.getExecQuery(conn, "SELECT dinero FROM transaccion WHERE source = "+ accountId);
							int cantidad = 0;
							while(rs3.next()) {
								System.out.println(rs3.getString("dinero"));
								if(!rs3.getString("dinero").equals(null)) {
									cantidad = cantidad + Integer.parseInt(rs3.getString("dinero"));	
								}else {
									cantidad = 0;
								}
								
							}	
							rs3.close();
							cantidadUsuario = cantidadUsuario + cantidad;
						}
						lista.add(new APair<Integer,Integer>(id, cantidadUsuario));
						rs2.close();
						
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
		
		btnVisualizarGraficoPermisos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection conn = GeneralSQLFunctions.connectToDatabase("jdbc:mysql://localhost:3306/deuzumdb", "root", "");
				ArrayList<APair<String, Integer>> lista = new ArrayList<APair<String, Integer>>();
				try {
					ResultSet rs = GeneralSQLFunctions.getExecQuery(conn, "SELECT permisos FROM usuario");
					while(rs.next()) {
						lista.add(new APair<String,Integer>(rs.getString("permisos"), 1));
					}
				}catch (Exception ex) {
					ex.printStackTrace();
				}
				
				new GraficoPermisos(lista);
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
		mainPanel.addTab("IA", null, panel_Funct_IA, null);
		
		JButton btnClusterizarDb = new JButton("Clusterizar DB");
		panel_Funct_IA.add(btnClusterizarDb);
		
		JPanel panel_Funct_Seguridad = new JPanel();
		mainPanel.addTab("Seguridad", null, panel_Funct_Seguridad, null);
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
		setLayout(new BorderLayout());
		add(mainPanel,BorderLayout.CENTER);
	}
}
