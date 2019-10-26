package deustoZumServer.Visual.Dialogs.User;

import javax.swing.JDialog;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class deleteUser extends JDialog {


	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JLabel lblIntroducirIdUsuario;
	private JButton btnEliminar;
	
	/**
	 * Crea un objeto de deleteUser que permite eliminar un usuario buscandolo por su ID.
	 */
	public deleteUser() {
		setSize(400, 200);
		setVisible(true);
		setTitle("Eliminar Usuario");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		lblIntroducirIdUsuario = new JLabel("Introducir ID usuario");
		lblIntroducirIdUsuario.setBounds(28, 16, 140, 16);
		getContentPane().add(lblIntroducirIdUsuario);
		
		textField = new JTextField();
		textField.setBounds(170, 11, 106, 26);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(277, 11, 117, 29);
		getContentPane().add(btnEliminar);
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showConfirmDialog(null, "¿Estas seguro de que quieres eliminar el usuario?");
			}
		});
	
	}

}
