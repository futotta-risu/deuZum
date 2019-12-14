package io.github.fatsquirrels.deuzum.visual.panels;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.sql.Connection;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import io.github.fatsquirrels.deuzum.database.GeneralSQLFunctions;
import io.github.fatsquirrels.deuzum.database.tableName;
import io.github.fatsquirrels.deuzum.net.Server;
import io.github.fatsquirrels.deuzum.visual.Dialogs.Account.*;
import io.github.fatsquirrels.deuzum.visual.Dialogs.Group.*;
import io.github.fatsquirrels.deuzum.visual.Dialogs.Project.*;
import io.github.fatsquirrels.deuzum.visual.Dialogs.Transactions.*;
import io.github.fatsquirrels.deuzum.visual.Dialogs.User.*;
import io.github.fatsquirrels.deuzum.visual.Dialogs.general.ListPanel;
import io.github.fatsquirrels.deuzum.visual.Dialogs.general.generalCreateDialog;
import io.github.fatsquirrels.deuzum.visual.Style.Layout.VerticalFlowLayout;
import io.github.fatsquirrels.deuzum.visual.components.buttons.FlatButton;
import io.github.fatsquirrels.deuzum.visual.Dialogs.general.generalCreateDialog;



public class MenuPanel extends JPanel{

	private static final long serialVersionUID = 3963889402535275585L;
	
	
	private tableName panelType;
	ListPanel leftList;


	private JPanel rightMenu;
	

	
	
	public MenuPanel(tableName type) {
		this.panelType = type;
		setLayout(new BorderLayout());
		leftList = new ListPanel(type);
		add(leftList, BorderLayout.CENTER);
		rightMenu = new JPanel();
		rightMenu.setLayout(new VerticalFlowLayout(10,10,10));
		//Create
		JButton create = new FlatButton("Crear " + type.getName());
		create.addActionListener(e->new generalCreateDialog(Server.createConnection(),panelType));
		rightMenu.add(create);
		
		// Edit
		if(type!=tableName.TRANSACCION) {
			JButton editar = new FlatButton("Editar " + type.getName());
			
			editar.addActionListener(e->editDialog());
			rightMenu.add(editar);
		}
		// See
		
		JButton see = new FlatButton("Lista de " + type.getName());
		see.addActionListener(e->new ListPanel( this.panelType));
		rightMenu.add(see);
		
		// Delete
		
		JButton delete = new FlatButton("Editar " + type.getName());
		delete.addActionListener(e->deleteDialog());
		rightMenu.add(delete);
		add(rightMenu, BorderLayout.EAST);
	}
	
	
	private void editDialog() {
		int ans = Integer.parseInt( JOptionPane.showInputDialog(
		        "Primer numero"
		        ));
		new generalCreateDialog(Server.createConnection(), panelType, ans);
	}
	
	// TODO Crear este delete
	private void deleteDialog() {
		switch(this.panelType) {
		case USUARIO:
			new deleteUser(Server.createConnection());
			break;
		case CUENTA:
			new deleteAccount(Server.createConnection());
			break;
		case GRUPO:
			new deleteGroup(Server.createConnection());
			break;
		case PROYECTO:
			new deleteProyect(Server.createConnection());
			break;
		case TRANSACCION:
			new deleteTransaction(Server.createConnection());
			break;
		default:
		}
	}
	
}
