package io.github.fatsquirrels.deuzum.visual.Dialogs.Account;

import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import io.github.fatsquirrels.deuzum.database.GeneralSQLFunctions;
import io.github.fatsquirrels.deuzum.net.ServerUserFunctionality;
import io.github.fatsquirrels.deuzum.utils.ObjectMapper;
import io.github.fatsquirrels.deuzum.utils.math.APair;
import io.github.fatsquirrels.deuzum.utils.text.ConcreteText;
import io.github.fatsquirrels.deuzum.utils.text.TextTypes;

/**
* Dialogo encargado de editar cuentas de la base de datos
*/
@Deprecated
public class editAccount extends JDialog{
	

	private static final long serialVersionUID = 1L;
	private JTextField txtNumCuenta;
	private JTextField txtIdUsuario;
	private JTextField txtDinero;
	private JTextField txtTipoCuenta;
	private JTextField txtDescripcion;
	private JTextField txtEstado;
	private JTextField txtCategoria;
	private JTextField txtBuscarCuenta;

	private JLabel lblNumCuenta;
	private JLabel lblIdUsuario;
	private JLabel lblDinero;
	private JLabel lblTipoCuenta;
	private JLabel lblDescripcion;
	private JLabel lblEstado;
	private JLabel lblCategoria;
	private JLabel lblIntroducirId;
	
	private JButton btnBuscarCuenta;
	private JButton btnGuardarCambios;
	
	private JPanel accountData;
	
	private String accountID;

	private Connection conn;
	private HashMap<String,Component> componentMap;

	
	
	/**
	 * Crea un objeto de editAccount que contiene un Dialogo que permite editar una cuenta.
	 * @param Connection
	 */
	public editAccount(Connection conn) {
		this.conn =conn;
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle("Editar Cuenta");
		setVisible(true);
		setSize(700,400);
		
		initialize(conn);
		this.componentMap = ObjectMapper.createComponentMap(accountData);

		
		
	}
	public void initialize(Connection conn) {
		Container cp = this.getContentPane();
		cp.setLayout(null);

		accountData =new JPanel();
		accountData.setBounds(0, 0, 700, 378);
		accountData.setLayout(null);
		accountData.setVisible(true);
		
		
		
		txtNumCuenta = new JTextField();
		txtNumCuenta.setBounds(168, 110, 483, 40);
		txtIdUsuario = new JTextField();
		txtIdUsuario.setBounds(168, 162, 127, 40);
		txtDinero = new JTextField();
		txtDinero.setBounds(168, 214, 127, 40);
		txtTipoCuenta = new JTextField();
		txtTipoCuenta.setBounds(168, 266, 127, 40);
		txtDescripcion = new JTextField();
		txtDescripcion.setBounds(524, 162, 127, 40);
		txtEstado = new JTextField();
		txtEstado.setBounds(524, 214, 127, 40);
		txtCategoria = new JTextField();
		txtCategoria.setBounds(524, 266, 127, 40);
		txtBuscarCuenta = new JTextField();
		txtBuscarCuenta.setBounds(194, 42, 177, 44);
		
		lblNumCuenta = new JLabel("Numero de Cuenta");
		lblNumCuenta.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumCuenta.setBounds(38, 110, 127, 40);
		lblIdUsuario = new JLabel("ID Usuario");
		lblIdUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		lblIdUsuario.setBounds(38, 165, 127, 34);
		lblDinero = new JLabel("Dinero");
		lblDinero.setHorizontalAlignment(SwingConstants.CENTER);
		lblDinero.setBounds(38, 211, 127, 47);
		lblTipoCuenta = new JLabel("Tipo de Cuenta");
		lblTipoCuenta.setHorizontalAlignment(SwingConstants.CENTER);
		lblTipoCuenta.setBounds(38, 266, 127, 40);
		lblDescripcion  = new JLabel("Descripcion");
		lblDescripcion.setHorizontalAlignment(SwingConstants.CENTER);
		lblDescripcion.setBounds(389, 165, 127, 39);
		lblEstado = new JLabel("Estado");
		lblEstado.setHorizontalAlignment(SwingConstants.CENTER);
		lblEstado.setBounds(389, 214, 126, 40);
		lblCategoria = new JLabel("Categoria");
		lblCategoria.setHorizontalAlignment(SwingConstants.CENTER);
		lblCategoria.setBounds(389, 266, 127, 40);
		lblIntroducirId = new JLabel("Introducir ID Cuenta");
		lblIntroducirId.setBounds(38, 51, 144, 26);
		
		
		
		
		
		
		
		btnBuscarCuenta = new JButton("Buscar Cuenta");
		btnBuscarCuenta.setBounds(396, 46, 255, 38);
		btnBuscarCuenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscarCuenta();
			}
		});
		
		
		
		btnGuardarCambios = new JButton("Crear Cuenta");
		btnGuardarCambios.setBounds(254, 318, 190, 47);
		btnGuardarCambios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editarCuenta();
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
		accountData.add(txtBuscarCuenta);
		accountData.add(lblIntroducirId);
		accountData.add(btnGuardarCambios);
		accountData.add(btnBuscarCuenta);

		cp.add(accountData);

	}
	
	/**
	 * Metodo encargado de buscar una cuenta en la BD a traves de su ID y rellenar el Dialogo con la informacion de la cuenta seleccionada
	 */
	public void buscarCuenta() {
		accountID = txtBuscarCuenta.getText();
		ResultSet resultAccount = null;
		try {
			resultAccount = GeneralSQLFunctions.getResultSetEntryFromDatabase(conn, "cuenta", "numero_cuenta = " + accountID  );
			while(resultAccount.next()) {
				txtNumCuenta.setText(resultAccount.getString("numero_cuenta")); 
				txtIdUsuario.setText(resultAccount.getString("id_usuario"));
				txtDinero.setText(resultAccount.getString("dinero"));
				txtTipoCuenta.setText(resultAccount.getString("tipo_cuenta"));
				txtDescripcion.setText(resultAccount.getString("descripcion"));
				txtEstado.setText(resultAccount.getString("estado"));
				txtCategoria.setText(resultAccount.getString("categoria"));
			}
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
	}
	
	/**
	 * Metodo encargado de comprobar la informacion introducida y actualizar los datos en la Base de Datos
	 */
	public void editarCuenta() {
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
					ServerUserFunctionality.updateAccount(conn, accountID,
							new String[] {"numero_cuenta","id_usuario", "dinero", "tipo_cuenta", "descripcion", "estado", "categoria"},
							new String[]{txtNumCuenta.getText(), txtIdUsuario.getText(),
							txtDinero.getText(), txtTipoCuenta.getText(), txtDescripcion.getText(),
							txtEstado.getText(), txtCategoria.getText()});;
					
					
				}
				
	}
	

}
