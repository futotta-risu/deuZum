package deustoZumServer.Visual.Dialogs.Transactions;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.GridLayout;
import javax.swing.SwingConstants;

import deustoZumServer.ServerUserFunctionality;
import deustoZumServer.Visual.Style.FlatButton;

import java.awt.event.ActionListener;
import java.sql.Connection;
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
				ServerUserFunctionality.createTransaction(connection, txtOrigen.getText(),
						txtDestino.getText(), Integer.valueOf(txtCantidad.getText()));
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
}
