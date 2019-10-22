package deustoZumServer.Dialogs;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class createTransaction extends JDialog{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtOrigen;
	private JTextField txtDestino;
	private JTextField txtCantidad;
	private JTextField txtFecha;
	
	public createTransaction() {
		setSize(350,350);
		setVisible(true);
		setTitle("Crear Transaccion");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JLabel lblIdCuentaOrigen = new JLabel("ID cuenta Origen:");
		lblIdCuentaOrigen.setBounds(23, 49, 118, 16);
		getContentPane().add(lblIdCuentaOrigen);
		
		JLabel lblIdCuentaDestino = new JLabel("ID cuenta Destino:");
		lblIdCuentaDestino.setBounds(23, 84, 118, 16);
		getContentPane().add(lblIdCuentaDestino);
		
		JLabel lblCantidad = new JLabel("Cantidad:");
		lblCantidad.setBounds(23, 138, 61, 16);
		getContentPane().add(lblCantidad);
		
		JLabel lblFecha = new JLabel("Fecha:");
		lblFecha.setBounds(23, 176, 61, 16);
		getContentPane().add(lblFecha);
		
		txtOrigen = new JTextField();
		txtOrigen.setBounds(153, 44, 130, 26);
		getContentPane().add(txtOrigen);
		txtOrigen.setColumns(10);
		
		txtDestino = new JTextField();
		txtDestino.setBounds(153, 79, 130, 26);
		getContentPane().add(txtDestino);
		txtDestino.setColumns(10);
		
		txtCantidad = new JTextField();
		txtCantidad.setBounds(153, 133, 130, 26);
		getContentPane().add(txtCantidad);
		txtCantidad.setColumns(10);
		
		txtFecha = new JTextField();
		txtFecha.setBounds(153, 171, 130, 26);
		getContentPane().add(txtFecha);
		txtFecha.setColumns(10);
		
		JButton btnCrearTransaccion = new JButton("Crear Transaccion");
		btnCrearTransaccion.setBounds(92, 237, 149, 26);
		getContentPane().add(btnCrearTransaccion);
		
	}
}
