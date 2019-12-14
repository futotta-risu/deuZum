package io.github.fatsquirrels.deuzum.visual.Dialogs.general;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import io.github.fatsquirrels.deuzum.database.GeneralSQLFunctions;
import io.github.fatsquirrels.deuzum.visual.Style.Layout.VerticalFlowLayout;

public class generalCreateDialog extends JDialog{

	public enum tableName{
		usuario("usuario");
		
		public String name;
		
		tableName(String name) {
			this.name = name;
		}
	};
	
	/**
	 * 
	 * @param conn
	 * @param table
	 * @param openType 1- Create, 2- Edit
	 */
	public generalCreateDialog(Connection conn, tableName table, int openType) {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setSize(540,350);
		setLayout(new VerticalFlowLayout(10,10,10));
		ArrayList<String> columnNames = new ArrayList<String>();
		ArrayList<Integer> columnTypes = new ArrayList<Integer>();
		try {
			ResultSet rs = GeneralSQLFunctions.getExecQuery(conn, "SELECT * FROM "+ table.name+ " LIMIT 1");
			ResultSetMetaData rsmd = rs.getMetaData();
			for(int i = 1; i <= rsmd.getColumnCount(); i++) {
				columnNames.add(rsmd.getColumnName(i));
				columnTypes.add(rsmd.getColumnType(i));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Error al crear un dialogo general.");
			dispose();
		}
		for(int i =0 ; i < columnNames.size(); i++) {
			JPanel temp = new JPanel();
			temp.add(new JLabel(columnNames.get(i)));
			temp.add(getComponentByType(columnTypes.get(i)));
			add(temp);
		}
		setVisible(true);
			
	}
	
	public JComponent getComponentByType(int type) {
		switch(type) {
		case 4:
		case 12:
			return new JTextField(10);
		case 93:
			return new JLabel("Tiempo");
		default:
			return new JLabel("Otro");
		}
	}
	
}
