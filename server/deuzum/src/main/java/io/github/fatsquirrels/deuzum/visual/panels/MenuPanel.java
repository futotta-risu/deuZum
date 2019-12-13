package io.github.fatsquirrels.deuzum.visual.panels;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import io.github.fatsquirrels.deuzum.net.Server;
import io.github.fatsquirrels.deuzum.visual.Dialogs.Account.*;
import io.github.fatsquirrels.deuzum.visual.Dialogs.Group.*;
import io.github.fatsquirrels.deuzum.visual.Dialogs.Project.*;
import io.github.fatsquirrels.deuzum.visual.Dialogs.Transactions.*;
import io.github.fatsquirrels.deuzum.visual.Dialogs.User.*;

import io.github.fatsquirrels.deuzum.visual.components.buttons.FlatButton;

public class MenuPanel extends JPanel{

	private static final long serialVersionUID = 3963889402535275585L;
	
	public enum MenuType{
		USUARIO("usuario"),CUENTA("cuenta"),GRUPO("grupo"),PROYECTO("proyecto"),TRANSACCION("transaccion");
		
		private String valor;
		
		MenuType(String valor){
			this.valor = valor;
		}};
	private MenuType panelType;
	
	

	
	
	public MenuPanel(MenuType type) {
		this.panelType = type;
		setLayout(new FlowLayout());
		//Create
		JButton create = new FlatButton("Crear " + type.valor);
		create.addActionListener(e->createDialog());
		add(create);
		
		// Edit
		
		JButton editar = new FlatButton("Editar " + type.valor);
		editar.addActionListener(e->editDialog());
		add(editar);
		
		// See
		
		JButton see = new FlatButton("Lista de " + type.valor);
		see.addActionListener(e->seeDialog());
		add(see);
		
		// Delete
		
		JButton delete = new FlatButton("Editar " + type.valor);
		delete.addActionListener(e->deleteDialog());
		add(delete);
	}
	
	private void createDialog() {
		switch(this.panelType) {
		case USUARIO:
			new createUser(Server.createConnection());
			break;
		case CUENTA:
			new createAccount(Server.createConnection());
			break;
		case GRUPO:
			new createGroup(Server.createConnection());
			break;
		case PROYECTO:
			new createProyect(Server.createConnection());
			break;
		case TRANSACCION:
			new createTransaction(Server.createConnection());
			break;
		default:
		}
	}
	
	private void editDialog() {
		switch(this.panelType) {
		case USUARIO:
			new editUser(Server.createConnection());
			break;
		case CUENTA:
			new editAccount(Server.createConnection());
			break;
		case GRUPO:
			new editGroup(Server.createConnection());
			break;
		case PROYECTO:
			new editProyect(Server.createConnection());
			break;
		default:
		}
	}
	
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
	
	private void seeDialog() {
		switch(this.panelType) {
		case USUARIO:
			new deleteUser(Server.createConnection());
			break;
		case CUENTA:
			new AccountList(Server.createConnection());
			break;
		case GRUPO:
			new GroupList(Server.createConnection());
			break;
		case PROYECTO:
			new ProyectList(Server.createConnection());
			break;
		case TRANSACCION:
			new TransactionList(Server.createConnection());
			break;
		default:
		}
	}
	
}
