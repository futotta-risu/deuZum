package io.github.fatsquirrels.deuzum.Visual.Dialogs.Account;

import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import io.github.fatsquirrels.deuzum.ServerUserFunctionality;
import io.github.fatsquirrels.deuzum.Algorithms.ConcreteText;
import io.github.fatsquirrels.deuzum.Algorithms.ObjectMapper;
import io.github.fatsquirrels.deuzum.Algorithms.TextTypes;
import io.github.fatsquirrels.deuzum.Algorithms.Math.APair;

import javax.swing.JButton;

/**
* Dialogo encargado de crear cuentas en la base de datos
*/
public class createAccount extends JDialog{
	
	
	private static final long serialVersionUID = 1L;
	private JTextField txtNumCuenta;
	private JTextField txtIdUsuario;
	private JTextField txtDinero;
	private JTextField txtTipoCuenta;
	private JTextField txtDescripcion;
	private JTextField txtEstado;
	private JTextField txtCategoria;

	private JLabel lblNumCuenta;
	private JLabel lblIdUsuario;
	private JLabel lblDinero;
	private JLabel lblTipoCuenta;
	private JLabel lblDescripcion;
	private JLabel lblEstado;
	private JLabel lblCategoria;
	
	private JButton btnCrearCuenta;
	
	private JPanel accountData;
	
	private Connection conn;
	private HashMap<String,Component> componentMap;

	
	
	/**
	 * Crea un objeto de createAccount que contiene un Dialogo que permite crear una cuenta.
	 * @param Connection
	 */
	public createAccount(Connection conn) {
		this.conn =conn;
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle("Crear Cuenta");
		setVisible(true);
		setSize(700,400);
		
		initialize(conn);
		this.componentMap = ObjectMapper.createComponentMap(accountData);

		
		
	}
	public void initialize(Connection conn) {
		Container cp = this.getContentPane();
		cp.setLayout(null);

		accountData = new JPanel();
		accountData.setBounds(0, 0, 700, 372);
		accountData.setLayout(null);
		accountData.setVisible(true);
		
		
		
		txtNumCuenta = new JTextField();
		txtNumCuenta.setBounds(168, 32, 474, 40);
		txtIdUsuario = new JTextField();
		txtIdUsuario.setBounds(168, 96, 127, 40);
		txtDinero = new JTextField();
		txtDinero.setBounds(168, 164, 127, 40);
		txtTipoCuenta = new JTextField();
		txtTipoCuenta.setBounds(168, 232, 127, 40);
		txtDescripcion = new JTextField();
		txtDescripcion.setBounds(515, 96, 127, 40);
		txtEstado = new JTextField();
		txtEstado.setBounds(515, 164, 127, 40);
		txtCategoria = new JTextField();
		txtCategoria.setBounds(515, 232, 127, 40);
		
		lblNumCuenta = new JLabel("Numero de Cuenta");
		lblNumCuenta.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumCuenta.setBounds(38, 32, 127, 40);
		lblIdUsuario = new JLabel("ID Usuario");
		lblIdUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		lblIdUsuario.setBounds(38, 99, 127, 34);
		lblDinero = new JLabel("Dinero");
		lblDinero.setHorizontalAlignment(SwingConstants.CENTER);
		lblDinero.setBounds(38, 161, 127, 47);
		lblTipoCuenta = new JLabel("Tipo de Cuenta");
		lblTipoCuenta.setHorizontalAlignment(SwingConstants.CENTER);
		lblTipoCuenta.setBounds(38, 232, 127, 40);
		lblDescripcion  = new JLabel("Descripcion");
		lblDescripcion.setHorizontalAlignment(SwingConstants.CENTER);
		lblDescripcion.setBounds(389, 97, 127, 39);
		lblEstado = new JLabel("Estado");
		lblEstado.setHorizontalAlignment(SwingConstants.CENTER);
		lblEstado.setBounds(389, 164, 126, 40);
		lblCategoria = new JLabel("Categoria");
		lblCategoria.setHorizontalAlignment(SwingConstants.CENTER);
		lblCategoria.setBounds(389, 232, 127, 40);
		
		
		btnCrearCuenta = new JButton("Crear Cuenta");
		btnCrearCuenta.setBounds(295, 307, 117, 29);
		getContentPane().add(btnCrearCuenta);
		
		btnCrearCuenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				crearCuenta();
			}
		});
		
		
		accountData.add(txtNumCuenta);
		accountData.add(txtIdUsuario);
		accountData.add(txtDinero);
		accountData.add(txtTipoCuenta);
		accountData.add(txtDescripcion);
		accountData.add(txtEstado);
		accountData.add(txtCategoria);
		accountData.add(lblNumCuenta);
		accountData.add(lblIdUsuario);
		accountData.add(lblDinero);
		accountData.add(lblTipoCuenta);
		accountData.add(lblDescripcion);
		accountData.add(lblEstado);
		accountData.add(lblCategoria);
		
		cp.add(accountData);


	}
	
	/**
	 *  Metodo encargado de comprobar los datos introducidos y añadir la Cuenta a la Base de Datos
	 */
	public void crearCuenta() {
		//Comprobamos campos vacios
				ArrayList<APair<String,String>> compulsoryVars = new ArrayList<>();
				compulsoryVars.add(new APair<String,String>("txtNumCuenta","Numero de Cuenta"));
				compulsoryVars.add(new APair<String,String>("txtIdUsuario","ID Usuario"));
				compulsoryVars.add(new APair<String,String>("txtDinero","Dinero"));
				compulsoryVars.add(new APair<String,String>("txtTipoCuenta","Tipo de Cuenta"));
				compulsoryVars.add(new APair<String,String>("txtDescripcion","Descripcion"));
				compulsoryVars.add(new APair<String,String>("txtEstado","Estado de la Cuenta"));
				compulsoryVars.add(new APair<String,String>("txtCategoria","Categoria"));


				int nError = 0;
				for(APair<String,String> i :compulsoryVars){
					JTextField tempC = (JTextField) ObjectMapper.getComponentByName(i.getIndex(), componentMap);
					if(tempC.getText().isEmpty()) 
						System.err.println("Error " + String.valueOf(++nError) + ": No ha indicado ninguna respuesta en " + i.getValue());
					
				}
				
				
				// Comprobamos Formato y longitud
				ArrayList<APair<String,ConcreteText>> formatVars = new ArrayList<>();
				formatVars.add(new APair<String, ConcreteText>("txtNumCuenta", new ConcreteText("Numero de Cuenta",TextTypes.ACCOUNT)));
				formatVars.add(new APair<String, ConcreteText>("txtIdUsuario", new ConcreteText("ID Usuario",TextTypes.ID)));
				formatVars.add(new APair<String, ConcreteText>("txtDinero", new ConcreteText("Dinero",TextTypes.MONEY)));
				formatVars.add(new APair<String, ConcreteText>("txtTipoCuenta", new ConcreteText("Tipo de Cuenta",TextTypes.ACCOUNT_TYPE)));
				formatVars.add(new APair<String, ConcreteText>("txtDescripcion", new ConcreteText("Descripcion",TextTypes.DESCRIPTION)));
				formatVars.add(new APair<String, ConcreteText>("txtEstado", new ConcreteText("Estado de la Cuenta",TextTypes.STATUS)));
				formatVars.add(new APair<String, ConcreteText>("txtCategoria", new ConcreteText("Categoria",TextTypes.CATEGORY)));
				
				for(APair<String,ConcreteText> i : formatVars) {
					JTextField tempC = (JTextField) ObjectMapper.getComponentByName(i.getIndex(), componentMap);
					String tempText = tempC.getText();
					if(tempText.isEmpty() && !ConcreteText.isValid(tempText,  i.getValue().getTextType())) 
						System.err.println("Error " + String.valueOf(++nError) + ": No ha indicado ninguna respuesta en " + i.getValue());

				}
				
				// Si no hay errores lo enviamos
				if(nError==0) {
								
					//Añadimos informacion a la tabla Cuenta
					ServerUserFunctionality.createAccount(conn, new String[]{txtNumCuenta.getText(), txtIdUsuario.getText(),
							txtDinero.getText(), txtTipoCuenta.getText(), txtDescripcion.getText(),
							txtEstado.getText(), txtCategoria.getText()});;
					
					
				}
				
				// TODO Añadir ventana de error
	}
	

}