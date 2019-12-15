package io.github.fatsquirrels.deuzum.visual.Dialogs.User;

import javax.swing.JDialog;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.JButton;
import javax.swing.SwingConstants;

import io.github.fatsquirrels.deuzum.net.ServerUserFunctionality;
import io.github.fatsquirrels.deuzum.visual.components.buttons.FlatButton;

import java.awt.Color;
import java.awt.Font;



// Clase Testeada
/**
 * Dialogo encargado de eliminar usuarios de la base de datos
 *
 */
public class deleteUser extends JDialog {


	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JLabel lblIntroducirIdUsuario;
	private JButton btnEliminar;
	
	/**
	 * Crea un objeto de deleteUser que permite eliminar un usuario buscandolo por su ID.
	 */
	public deleteUser( Connection connection) {
		setSize(400, 130);
		setVisible(true);
		setTitle("Eliminar Usuario");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		setBackground(Color.WHITE);
		
		lblIntroducirIdUsuario = new JLabel("Introducir ID usuario");
		lblIntroducirIdUsuario.setFont(new Font("Verdana", Font.PLAIN, 11));
		lblIntroducirIdUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		lblIntroducirIdUsuario.setBounds(10, 16, 178, 16);
		getContentPane().add(lblIntroducirIdUsuario);
		
		textField = new JTextField();
		textField.setBounds(10, 43, 178, 26);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		btnEliminar = new FlatButton("Eliminar");
		btnEliminar.setFont(new Font("Verdana", Font.PLAIN, 14));
		btnEliminar.setBounds(198, 16, 176, 59);
		getContentPane().add(btnEliminar);
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showConfirmDialog(null, "¿Estas seguro de que quieres eliminar el usuario?");
				ServerUserFunctionality.deleteUser(connection, textField.getText());
				dispose();
			}
		});
	
	}

}
