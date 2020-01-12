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

/**
 * Esta clase contiene todos los metodos para crear los graficos, 
 * recogiendo informacion de la BD mediante querys.
 * @see createGraphAportaciones
 * @see createGraphUsuariosXTiempo
 * @see createGraphPermisos
 * @see createGraphTransacciones
 * @see createGraphUsuario
 */
public class GraphFunctions {

	/**
	 * Este metodo realiza una busqueda en la BD para obtener la cantidad transferidad
	 * por usuario en un proyecto en especifico que introduce el usuarioServidor.
	 * Rellena el ArrayList de parejas con la informacion y crea un nuevo objeto de GraficoAportacionesProyecto.
	 * @see GraficoAportacionesProyecto
	 */
	public static void createGraphAportaciones() {
		Connection conn = Server.getDefaultServerConnection();
		ArrayList<APair<Integer, Integer>> lista = new ArrayList<APair<Integer, Integer>>();
		
		int proyectId = Integer.parseInt(JOptionPane.showInputDialog("Introducir ID Proyecto"));
		try {
			ResultSet rs = GeneralSQLFunctions.getExecQuery(conn, "SELECT id_miembro,cantidad FROM proyectotransaccion WHERE id_proyecto = " + proyectId);
			while(rs.next()) {
				lista.add(new APair<Integer, Integer>(rs.getInt("id_miembro"), rs.getInt("cantidad")));
			}
			rs.close();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
		new GraficoAportacionesProyecto(lista);
	}
	
	/**
	 * Este metodo realiza una busqueda en la BD para obtener los usuarios y su fecha de registro.
	 * Rellena el ArrayList de parejas con la informacion y crea un nuevo objeto de GraficoUsuariosXTiempo.
	 * @see GraficoUsuariosXTiempo
	 */
	public static void createGraphUsuariosXTiempo() {
		Connection conn = Server.getDefaultServerConnection();
		List<String> lista = new ArrayList<String>();
		
		try {
			ResultSet rs = GeneralSQLFunctions.getExecQuery(conn, "SELECT fecha_creacion FROM usuario ORDER BY fecha_creacion DESC");
			while(rs.next()) {
				String fecha = rs.getString("fecha_creacion");
				String[] fecha2= fecha.split(" ");
				lista.add(fecha2[0]);
			}
			rs.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		new GraficoUsuariosXTiempo(lista);
	}
	
	/**
	 * Este metodo realiza una busqueda en la BD para obtener los permisos de los usuarios.
	 * Rellena la Lista de Strings con la informacion y crea un nuevo objeto de GraficoPermisos.
	 * @see GraficoPermisos
	 */
	public static void createGraphPermisos() {
		Connection conn = Server.getDefaultServerConnection();
		List<String> lista = new ArrayList<String>();
		try {
			ResultSet rs = GeneralSQLFunctions.getExecQuery(conn, "SELECT permisos FROM usuario");
			while(rs.next()) 
				lista.add(rs.getString("permisos"));
			
		}catch (Exception ex) {
			ex.printStackTrace();
		}
		
		new GraficoPermisos(lista);
	}
	
	/**
	 * Este metodo realiza una busqueda en la BD para obtener las cantidades de dinero que se han transferido.
	 * Rellena la Lista de Integers con la informacion y crea un nuevo objeto de GraficoTransaciones.
	 * @see GraficoTransaciones
	 */
	public static void createGraphTransacciones() {
		Connection conn = Server.getDefaultServerConnection();
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
	
	/**
	 * Este metodo realiza una busqueda en la BD para obtener la cantidad total de dinero transferida
	 * por usuario en transacciones normales (no grupales).
	 * Rellena el ArrayList de parejas con la informacion y crea un nuevo objeto de GraficoTransacionesUsuario.
	 * @see GraficoTransacionesUsuario
	 */
	public static void createGraphUsuario() {
		Connection conn = Server.getDefaultServerConnection();
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
