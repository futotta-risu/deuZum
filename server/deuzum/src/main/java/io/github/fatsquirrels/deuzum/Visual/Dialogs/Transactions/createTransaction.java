package io.github.fatsquirrels.deuzum.Visual.Dialogs.Transactions;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.SwingConstants;

import io.github.fatsquirrels.deuzum.ServerUserFunctionality;
import io.github.fatsquirrels.deuzum.Algorithms.ConcreteText;
import io.github.fatsquirrels.deuzum.Algorithms.ObjectMapper;
import io.github.fatsquirrels.deuzum.Algorithms.TextFunctions;
import io.github.fatsquirrels.deuzum.Algorithms.TextTypes;
import io.github.fatsquirrels.deuzum.Algorithms.Math.APair;
import io.github.fatsquirrels.deuzum.Visual.Style.Components.Buttons.FlatButton;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.HashMap;
import java.awt.event.ActionEvent;

public class createTransaction extends JDialog{

	
	private static final long serialVersionUID = 1L;
	private JTextField txtOrigen;
	private JTextField txtDestino;
	private JTextField txtCantidad;
	private JTextField txtFecha;
	private JLabel lblIdCuentaOrigen;
	private JLabel lblIdCuentaDestino;
	private JLabel lblCantidad;
	private JLabel lblFecha;
	private JButton btnCrearTransaccion;
	private JPanel panel;
	
	private HashMap<String,Component> componentMap;
	
	/**
	 * Crea un objeto de createTransaction el cual contiene un Dialogo que muestra un formulario para
	 * crear una Transaccion.
	 */
	public createTransaction(Connection connection) {
		setSize(300,250);
		setVisible(true);
		setTitle("Crear Transaccion");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		btnCrearTransaccion = new FlatButton("Crear Transaccion");
		btnCrearTransaccion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				crearTransacion(connection);
			}
		});
		btnCrearTransaccion.setPreferredSize(new Dimension(350,55));
		getContentPane().add(btnCrearTransaccion, BorderLayout.SOUTH);
		
		panel = new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		getContentPane().add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{95, 95, 95};
		gbl_panel.rowHeights = new int[]{30, 30, 30, 30};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0};
		panel.setLayout(gbl_panel);
		
		lblIdCuentaOrigen = new JLabel("ID Origen:");
		lblIdCuentaOrigen.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblIdCuentaOrigen = new GridBagConstraints();
		gbc_lblIdCuentaOrigen.fill = GridBagConstraints.BOTH;
		gbc_lblIdCuentaOrigen.insets = new Insets(0, 0, 5, 5);
		gbc_lblIdCuentaOrigen.gridx = 0;
		gbc_lblIdCuentaOrigen.gridy = 0;
		panel.add(lblIdCuentaOrigen, gbc_lblIdCuentaOrigen);
		
		txtOrigen = new JTextField();
		GridBagConstraints gbc_txtOrigen = new GridBagConstraints();
		gbc_txtOrigen.fill = GridBagConstraints.BOTH;
		gbc_txtOrigen.insets = new Insets(0, 0, 5, 0);
		gbc_txtOrigen.gridx = 1;
		gbc_txtOrigen.gridwidth = 2;
		gbc_txtOrigen.gridy = 0;
		panel.add(txtOrigen, gbc_txtOrigen);
		txtOrigen.setColumns(30);
		
		lblIdCuentaDestino = new JLabel("ID Destino:");
		lblIdCuentaDestino.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblIdCuentaDestino = new GridBagConstraints();
		gbc_lblIdCuentaDestino.fill = GridBagConstraints.BOTH;
		gbc_lblIdCuentaDestino.insets = new Insets(0, 0, 5, 5);
		gbc_lblIdCuentaDestino.gridx = 0;
		gbc_lblIdCuentaDestino.gridy = 1;
		
		panel.add(lblIdCuentaDestino, gbc_lblIdCuentaDestino);
		
		txtDestino = new JTextField();
		GridBagConstraints gbc_txtDestino = new GridBagConstraints();
		gbc_txtDestino.fill = GridBagConstraints.BOTH;
		gbc_txtDestino.insets = new Insets(0, 0, 5, 0);
		gbc_txtDestino.gridx = 1;

		gbc_txtDestino.gridwidth = 2;
		gbc_txtDestino.gridy = 1;
		panel.add(txtDestino, gbc_txtDestino);
		txtDestino.setColumns(10);
		
		lblCantidad = new JLabel("Cantidad:");
		lblCantidad.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblCantidad = new GridBagConstraints();
		gbc_lblCantidad.fill = GridBagConstraints.BOTH;
		gbc_lblCantidad.insets = new Insets(0, 0, 5, 5);
		gbc_lblCantidad.gridx = 0;
		
		gbc_lblCantidad.gridy = 2;
		panel.add(lblCantidad, gbc_lblCantidad);
		
		txtCantidad = new JTextField();
		GridBagConstraints gbc_txtCantidad = new GridBagConstraints();
		gbc_txtCantidad.fill = GridBagConstraints.BOTH;
		gbc_txtCantidad.insets = new Insets(0, 0, 5, 0);
		gbc_txtCantidad.gridx = 1;
		gbc_txtCantidad.gridwidth = 2;
		gbc_txtCantidad.gridy = 2;
		panel.add(txtCantidad, gbc_txtCantidad);
		txtCantidad.setColumns(10);
		
		lblFecha = new JLabel("Fecha:");
		lblFecha.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblFecha = new GridBagConstraints();
		gbc_lblFecha.fill = GridBagConstraints.BOTH;
		gbc_lblFecha.insets = new Insets(0, 0, 0, 5);
		gbc_lblFecha.gridx = 0;
		gbc_lblFecha.gridy = 3;
		panel.add(lblFecha, gbc_lblFecha);
		
		txtFecha = new JTextField();
		GridBagConstraints gbc_txtFecha = new GridBagConstraints();
		gbc_txtFecha.fill = GridBagConstraints.BOTH;
		gbc_txtFecha.gridx = 1;
		gbc_txtFecha.gridwidth = 2;
		gbc_txtFecha.gridy = 3;
		panel.add(txtFecha, gbc_txtFecha);
		txtFecha.setColumns(10);
		
	}
	
	public void crearTransacion(Connection connection) {
		
		//Comprobamos campos vacios
				APair[] compulsoryVars = {
						new APair("txtOrigen","Origen"),new APair("txtDestino","Destino"), new APair("txtCantidad","Cantidad"),
						new APair("txtFecha", "Fecha")
						};
				int nError = 0;
				for(APair i :compulsoryVars){
					JTextField tempC = (JTextField) ObjectMapper.getComponentByName(String.valueOf(i.getIndex()), componentMap);
					System.out.println(i.getIndex());
					if(tempC.getText().isEmpty()) 
						System.out.println("Error " + String.valueOf(++nError) + ": No ha indicado ninguna respuesta en " + String.valueOf(i.getValue()));
					
				}
		
		// Comprobamos Fecha
		JTextField dateT = (JTextField) ObjectMapper.getComponentByName("txtFecha", componentMap);
		if(!dateT.getText().isEmpty() && !TextFunctions.dateChecker(dateT.getText()))
			System.out.println("Error " + String.valueOf(++nError) + ": No ha indicado ninguna respuesta en Fecha de Nacimiento");
		
		// Comprobamos Formato y longitud
				APair[] formatVars = {
						new APair("txtOrigen", new ConcreteText("Email",TextTypes.NAME)),
						new APair("txtDestino", new ConcreteText("Nombre",TextTypes.NAME)),
						new APair("txtCantidad", new ConcreteText("Apellido",TextTypes.PHONE)),
					};
				for(APair i : formatVars) {
					JTextField tempC = (JTextField) ObjectMapper.getComponentByName(String.valueOf(i.getIndex()), componentMap);
					String tempText = tempC.getText();
					if(!tempText.isEmpty() && !ConcreteText.isValid(tempText, ((ConcreteText) i.getValue()).getTextType())) 
						System.out.println("Error " + String.valueOf(++nError) + ": No ha indicado ninguna respuesta en " + String.valueOf(i.getValue()));

				}
				
		// Si no hay errores lo enviamos
		if(nError==0) {
			ServerUserFunctionality.createTransaction(connection, txtOrigen.getText(),
					txtDestino.getText(), Integer.valueOf(txtCantidad.getText()));
			dispose();
			
			JOptionPane.showMessageDialog(null, "Usuario registrado con exito", "REGISTRADO", 1);
		}		
			
	}
}
