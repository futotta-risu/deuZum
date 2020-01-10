package io.github.fatsquirrels.deuzum.visual.panels;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import io.github.fatsquirrels.deuzum.database.CommandBuilderF;
import io.github.fatsquirrels.deuzum.database.GeneralSQLFunctions;
import io.github.fatsquirrels.deuzum.database.StatementType;
import io.github.fatsquirrels.deuzum.database.WhereAST;
import io.github.fatsquirrels.deuzum.database.tableName;
import io.github.fatsquirrels.deuzum.database.exceptions.CommandBuilderBuildException;
import io.github.fatsquirrels.deuzum.net.Server;
import io.github.fatsquirrels.deuzum.utils.math.APair;
import io.github.fatsquirrels.deuzum.visual.Dialogs.general.generalCreateDialog;
import io.github.fatsquirrels.deuzum.visual.components.buttons.FlatButton;
import io.github.fatsquirrels.deuzum.visual.style.layout.VerticalFlowLayout;



public class MenuPanel extends JPanel{

	private static final long serialVersionUID = 3963889402535275585L;
	
	private tableName panelType;
	JScrollPane leftList;
	
	private JPanel rightMenu;
	private JTable table;
	private JButton create, editar=null, delete, refresh;
	
	
	public MenuPanel(tableName type) {
		this.panelType = type;
		setLayout(new BorderLayout());
		
		leftList = new JScrollPane();
		table = crearTabla(type);
		
		leftList.setViewportView(table);
		
		add(leftList, BorderLayout.CENTER);
		
		rightMenu = new JPanel();
		rightMenu.setLayout(new VerticalFlowLayout(10,10,10));
		//Create
		create = new FlatButton("Crear " + type.getName());
		create.addActionListener(e->createElement());
		rightMenu.add(create);
		
		// Edit
		if(type!=tableName.TRANSACCION) {
			editar = new FlatButton("Editar " + type.getName());
			
			editar.addActionListener(e->editElement());
			rightMenu.add(editar);
		}
		
		// Delete
		
		delete = new FlatButton("Eliminar " + type.getName());
		delete.addActionListener(e->deleteElement(Server.createConnection()));
		rightMenu.add(delete);
		
		
		refresh = new FlatButton("Refresh");
		refresh.addActionListener(e-> refreshTable(panelType));
		rightMenu.add(refresh);
		add(rightMenu, BorderLayout.EAST);
		table.getSelectionModel().addListSelectionListener(e-> setButtonAble(true));
		setButtonAble(false);
	}
	
	private void setButtonAble(boolean able) {
		if(editar!=null) editar.setEnabled(able);
		delete.setEnabled(able);
	}
	
	private void createElement() {
		
		
		JDialog jd =new generalCreateDialog(Server.createConnection(), panelType);
		jd.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
            	
            	refreshTable(panelType);
            }

        });
	
	}
	
	private void editElement() {
		
		JDialog jd = new generalCreateDialog(Server.createConnection(), panelType, 
				Integer.parseInt(String.valueOf(table.getValueAt(table.getSelectedRow(),0))));
		jd.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
            	
            	refreshTable(panelType);
            }
        });
	
	}
	
	
	public void deleteElement(Connection conn) {
		String ans = String.valueOf(table.getValueAt(table.getSelectedRow(),0));
		WhereAST where = new WhereAST().addValue(this.panelType.getID() + "='"+ans+"'");
		try {
			GeneralSQLFunctions.deleteEntryFromDatabase(conn, this.panelType.getName(), where);
		} catch (SQLException e) {
			System.err.println("Ha habido un error a la hora de eliminar una entrada");
			e.printStackTrace();
		} catch (CommandBuilderBuildException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		refreshTable(panelType);
	}
	
	private JTable crearTabla(tableName panelType) {
		Connection conn = Server.createConnection();
		
		
		ArrayList<APair<String,Integer>> columnNameTypes = GeneralSQLFunctions.getColumnNameType(conn, this.panelType);
		int columnSize = panelType.getVals();
		String[] columnNames = new String[columnSize];
		for(int i = 0 ; i < columnSize;i++)
			columnNames[i] =columnNameTypes.get(i).getIndex();
		
		
		
		CommandBuilderF cmdb = null;
		try {
			cmdb = new CommandBuilderF().setSQLType(StatementType.SELECT)
					.setTable(panelType.getName()).addColumns(columnNames);
		} catch (CommandBuilderBuildException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
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
			System.err.println("Error a la hora de crear la tabla del panel.");
			e.printStackTrace();
		}
		
		DefaultTableModel dtb = new DefaultTableModel(tableData,columnNames);
		return new JTable(dtb) ;
	}
	
	private void refreshTable(tableName panelType) {
		table = crearTabla(panelType);
		leftList.setViewportView(table);
		revalidate();
		repaint();
	}
	
}
