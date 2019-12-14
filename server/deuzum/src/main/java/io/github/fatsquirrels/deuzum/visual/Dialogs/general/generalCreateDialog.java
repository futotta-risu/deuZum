package io.github.fatsquirrels.deuzum.visual.Dialogs.general;

import java.awt.BorderLayout;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import io.github.fatsquirrels.deuzum.database.GeneralSQLFunctions;
import io.github.fatsquirrels.deuzum.database.tableName;
import io.github.fatsquirrels.deuzum.utils.math.APair;
import io.github.fatsquirrels.deuzum.visual.Style.Layout.VerticalFlowLayout;

public class generalCreateDialog extends JDialog{

	private static final long serialVersionUID = 2211943195848294043L;

	private ArrayList<APair<String,Integer>> columnVars;
	
	private int numberOfColumns = 0;
	
	public generalCreateDialog(Connection conn, tableName table, int id) {
		initialize(conn, table,true, id);
	}
	
	/**
	 * Crea una ventana derivada de una tabla para la creacion de valores
	 * @param conn
	 * @param table
	 */
	public generalCreateDialog(Connection conn, tableName table) {
		
		initialize(conn,  table, false, -1);
			
	}
	
	public void initialize(Connection conn, tableName table,boolean isEdit, int id) {
		ArrayList<String> columnVarVals = new ArrayList<String>();
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLayout(new VerticalFlowLayout(10,10,10));
		columnVars = GeneralSQLFunctions.getColumnNameType(conn, table);
		
		
		numberOfColumns = columnVars.size();
		for(int i =0 ; i < numberOfColumns; i++) {
			JPanel temp = new JPanel();
			temp.setLayout(new BorderLayout());
			temp.setSize(300,30);
			temp.add(new JLabel(columnVars.get(i).getIndex()), BorderLayout.WEST);
			temp.add(getComponentByType(columnVars.get(i).getValue(), isEdit, columnVarVals.get(i)), BorderLayout.EAST);
			add(temp);
		}
		
		// TODO Add Save and cancel buttons
		
		setSize(320,35*numberOfColumns);
		setVisible(true);
	}
	
	
	public JComponent getComponentByType(int type, boolean isEdit, String id) {
		switch(type) {
		case 4:
		case 12:
			JTextField txt = new JTextField(10);
			if(isEdit) txt.setText(id);
			return txt;
		case 93:
			return new JLabel("Tiempo");
		default:
			return new JLabel("Otro");
		}
	}
	
}
