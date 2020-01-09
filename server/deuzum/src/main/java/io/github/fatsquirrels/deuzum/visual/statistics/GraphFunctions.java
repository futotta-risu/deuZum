package io.github.fatsquirrels.deuzum.visual.statistics;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import io.github.fatsquirrels.deuzum.database.GeneralSQLFunctions;
import io.github.fatsquirrels.deuzum.net.Server;
import io.github.fatsquirrels.deuzum.utils.math.APair;

public class GraphFunctions {

	
	public static void createGraphAportaciones() {
		Connection conn = Server.createConnection();
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
	
	public static void createGraphUsuariosXTiempo() {
		Connection conn = Server.createConnection();
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
	
	public static void createGraphPermisos() {
		Connection conn = Server.createConnection();
		ArrayList<APair<String, Integer>> lista = new ArrayList<APair<String, Integer>>();
		try {
			ResultSet rs = GeneralSQLFunctions.getExecQuery(conn, "SELECT permisos FROM usuario");
			while(rs.next()) 
				lista.add(new APair<String,Integer>(rs.getString("permisos"), 1));
			
		}catch (Exception ex) {
			ex.printStackTrace();
		}
		
		new GraficoPermisos(lista);
	}
	
	public static void createGraphTransacciones() {
		Connection conn = Server.createConnection();
		List<Integer> cantidades = new ArrayList<Integer>();
		try {
			ResultSet rs = GeneralSQLFunctions.getExecQuery(conn, "SELECT dinero FROM transaccion ORDER BY dinero ASC");
			while(rs.next()) 
				cantidades.add(Integer.parseInt(rs.getString("dinero")));	
			
			rs.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		new GraficoTransaciones(cantidades);
	}
	
	public static void createGraphUsuario() {
		Connection conn = Server.createConnection();
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
}
