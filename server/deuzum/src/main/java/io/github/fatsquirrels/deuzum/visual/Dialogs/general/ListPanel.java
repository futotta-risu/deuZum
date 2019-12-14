package io.github.fatsquirrels.deuzum.visual.Dialogs.general;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import io.github.fatsquirrels.deuzum.database.CommandBuilderF;
import io.github.fatsquirrels.deuzum.database.GeneralSQLFunctions;
import io.github.fatsquirrels.deuzum.database.StatementType;
import io.github.fatsquirrels.deuzum.database.tableName;
import io.github.fatsquirrels.deuzum.net.Server;
import io.github.fatsquirrels.deuzum.utils.math.APair;

public class ListPanel extends JScrollPane{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7965963355985373364L;
	
	private tableName panelType;
	
	public ListPanel(tableName panelType) {
		Connection conn = Server.createConnection();
		
		this.panelType = panelType;
		
		ArrayList<APair<String,Integer>> columnNameTypes = GeneralSQLFunctions.getColumnNameType(conn, this.panelType);
		int columnSize = panelType.getVals();
		String[] columnNames = new String[columnSize];
		for(int i = 0 ; i < columnSize;i++)
			columnNames[i] =columnNameTypes.get(i).getIndex();
		
		
		
		CommandBuilderF cmdb = new CommandBuilderF().setSQLType(StatementType.SELECT)
				.setTable(panelType.getName()).addColumns(columnNames);
		ResultSet dataRS, rowCountRS;
		int rowCountI;
		String[][] tableData = null;
		try {
			
			rowCountRS = GeneralSQLFunctions.getExecQuery(conn,"SELECT COUNT(*) FROM " + panelType.getName());
			rowCountRS.next();
			rowCountI = rowCountRS.getInt(1);
			tableData = new String[rowCountI][columnSize];
			
			dataRS = GeneralSQLFunctions.getExecQuery(conn,cmdb.pack());
			int actColumn = 0;
			while(dataRS.next()) {
				for(int i = 0; i < columnSize; i++)
					tableData[actColumn][columnSize-i-1] = dataRS.getString(i+1);
				actColumn++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		DefaultTableModel dtb = new DefaultTableModel(tableData,columnNames);
		JTable list = new JTable(dtb) ;
		setViewportView(list);
	}
	
}
