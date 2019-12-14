package io.github.fatsquirrels.deuzum.visual.Dialogs.general;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import io.github.fatsquirrels.deuzum.database.CommandBuilderF;
import io.github.fatsquirrels.deuzum.database.GeneralSQLFunctions;
import io.github.fatsquirrels.deuzum.database.StatementType;
import io.github.fatsquirrels.deuzum.database.WhereAST;
import io.github.fatsquirrels.deuzum.database.tableName;
import io.github.fatsquirrels.deuzum.net.ServerUserFunctionality;
import io.github.fatsquirrels.deuzum.utils.math.APair;
import io.github.fatsquirrels.deuzum.visual.Style.Layout.VerticalFlowLayout;
import io.github.fatsquirrels.deuzum.visual.components.buttons.FlatButton;

public class generalCreateDialog extends JDialog{

	private static final long serialVersionUID = 2211943195848294043L;

	private ArrayList<APair<String,Integer>> columnVars;
	
	private int numberOfColumns = 0;
	
	private HashMap<String, APair<JComponent, Integer>> componentMap;
	
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
		componentMap = new HashMap<String, APair<JComponent, Integer>>();
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLayout(new VerticalFlowLayout(10,10,10));
		columnVars = GeneralSQLFunctions.getColumnNameType(conn, table);
		
		numberOfColumns = columnVars.size();
		
		ArrayList<String> columnVarVals = new ArrayList<String>();
		for(int i = 0; i < numberOfColumns+1; i++)
			columnVarVals.add("");
		
		if(isEdit) {
			ResultSet rs;
			try {
				
				rs = GeneralSQLFunctions.getExecQuery(conn, "SELECT * FROM " + table.getName() + " WHERE " + table.getID() + " = " + String.valueOf(id));
				rs.next();
				for(int i = 0; i < numberOfColumns; i++) 
					columnVarVals.set(i, rs.getString(i+1));
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		for(int i =0 ; i < numberOfColumns; i++) {
			JPanel temp = new JPanel();
			temp.setLayout(new BorderLayout());
			temp.setSize(300,30);
			temp.add(new JLabel(columnVars.get(i).getIndex()), BorderLayout.WEST);
			JComponent variableComp = getComponentByType(columnVars.get(i).getValue(), isEdit, columnVarVals.get(i));
			variableComp.setName(columnVars.get(i).getIndex());
			temp.add(variableComp, BorderLayout.EAST);
			componentMap.put(columnVars.get(i).getIndex(), 
					new APair<JComponent, Integer>(variableComp,columnVars.get(i).getValue())
				);
			add(temp);
		}
		
		JPanel botomButtons = new JPanel();
		botomButtons.setLayout(new FlowLayout());
		JButton cancel = new FlatButton("Cancel");
		cancel.addActionListener(e-> dispose());
		botomButtons.add(cancel);
		JButton save = new FlatButton("Guardar");
		save.addActionListener(e-> guardarCambios(conn,table,isEdit,id));
		botomButtons.add(save);
		add(botomButtons);
		setSize(320,35*(numberOfColumns+1)+10);
		setVisible(true);
		setModal(true);
	}
	
	public void guardarCambios(Connection conn, tableName table,boolean isEdit, int id) {
		// TODO lo de añadir
		CommandBuilderF cmbf = new CommandBuilderF().setTable(table.getName());
		
		if(isEdit) cmbf.addWhere(new WhereAST().addValue(table.getID() + "='" + String.valueOf(id)+"'"));
		
		if(!isEdit) cmbf = cmbf.setSQLType(StatementType.INSERT);
		else 		cmbf = cmbf.setSQLType(StatementType.UPDATE);
		
		for(APair<String,Integer> i : columnVars) {
			if(i.getIndex().equals(table.getID())) continue;
			JComponent temp = componentMap.get(i.getIndex()).getIndex();
			int type = componentMap.get(i.getIndex()).getValue();
			
			String value = "";
			
			
			switch(type) {
				case 4:
				case 12:
					value = ((JTextField)temp).getText();
					break;
				default:
				
			}
			
			if(!value.isEmpty()) {
				if(!isEdit) cmbf = cmbf.addColumn(i.getIndex(), value);
				else cmbf = cmbf.addExpression(i.getIndex(), value);
			}
			
		}
		System.out.println(cmbf.pack());
		try {
			GeneralSQLFunctions.execUpdate(conn, cmbf.pack());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dispose();
		
	}
	
	public JComponent getComponentByType(int type, boolean isEdit, String value) {
		switch(type) {
		case 4:
		case 12:
			JTextField txt = new JTextField(10);
			if(isEdit) txt.setText(value);
			return txt;
		case 93:
			return new JLabel("Tiempo");
		default:
			return new JLabel("Otro");
		}
	}
	
}
