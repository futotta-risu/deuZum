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
	
	
	public ListPanel() {
		
	}
	
}
