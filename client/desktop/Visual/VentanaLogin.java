

import java.awt.*;
import java.awt.event.*;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.*;

public class VentanaLogin extends JFrame{
	
	private JLabel lblUsuario;
	private JLabel lblContrasenia;
	
	private JTextField txtUsuario;
	private JPasswordField txtContrasenia;
	
	private JButton btnLogin;
	private JButton btnRegistrar;
	private JButton btnForgotPass;
	
	
	private JTabbedPane tabbedPane;
	private JPanel panelLogin;
	private JPanel panelRegistrar;
	private JTextField txtUser;
	private JTextField txtEmail;
	private JTextField txtNumCuenta;
	private JTextField txtRespuesta;
	private JLabel lblUser;
	private JLabel lblContrasea;
	private JLabel lblEmail;
	private JLabel lblNumCuenta;
	private JLabel lblPreguntaSeg;
	private JLabel lblRespuesta;
	private JButton btnEndRegistr;
	private JTextField txtName;
	private JTextField txtSurname;
	private JPasswordField txtContrasena;
	
	
	
	public VentanaLogin() {
		
		Container cp = this.getContentPane();
		cp.setLayout(new GridLayout(1,1));
		
		lblUsuario = new JLabel("Usuario:");
		lblUsuario.setFont(new Font("Nirmala UI", Font.BOLD, 14));
		lblUsuario.setBounds(101, 65, 89, 34);
		lblContrasenia = new JLabel("Contraseña:");
		lblContrasenia.setFont(new Font("Nirmala UI", Font.BOLD, 14));
		lblContrasenia.setLocation(77, 110);
		lblContrasenia.setSize(89, 34);
		
		txtUsuario = new JTextField(20);
		txtUsuario.setLocation(192, 60);
		txtUsuario.setSize(166, 34);
		txtContrasenia = new JPasswordField(20);
		txtContrasenia.setLocation(192, 110);
		txtContrasenia.setSize(166, 34);
		
		/**
		 *       BOTONES
		 */
		
		btnRegistrar = new JButton("Registrar");
		btnRegistrar.setBounds(256, 245, 197, 57);

		btnRegistrar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				tabbedPane.addTab("Registrar", panelRegistrar);
				tabbedPane.setSelectedIndex(1);
				
			}
			
		});
		
		
		
		btnLogin = new JButton("Login");
		btnLogin.setBounds(30, 245, 197, 57);
		
		
		btnForgotPass = new JButton("Contraseña Olvidada?");
		btnForgotPass.setLocation(184, 162);
		btnForgotPass.setSize(190, 39);
		

		
		/**
		 *    PANELES
		 */
			
		tabbedPane = new JTabbedPane();
		tabbedPane.setSize(500, 500);
		
		
		panelLogin = new JPanel();
		panelLogin.setSize(500, 500);
		tabbedPane.addTab("Login", panelLogin);
		panelLogin.setLayout(null);
		
		panelLogin.add(btnLogin);
		panelLogin.add(btnRegistrar);
		panelLogin.add(lblUsuario);
		panelLogin.add(lblContrasenia);
		panelLogin.add(txtUsuario);
		panelLogin.add(txtContrasenia);
		panelLogin.add(btnForgotPass);
		
		
		panelRegistrar = new JPanel();
		panelRegistrar.setSize(500,500);
		tabbedPane.addTab("Registrar", panelRegistrar);
		panelRegistrar.setLayout(null);
		
		txtUser = new JTextField();
		txtUser.setBounds(79, 75, 116, 20);
		panelRegistrar.add(txtUser);
		txtUser.setColumns(10);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(79, 115, 116, 20);
		panelRegistrar.add(txtEmail);
		txtEmail.setColumns(10);
		
		txtNumCuenta = new JTextField();
		txtNumCuenta.setBounds(99, 164, 307, 20);
		panelRegistrar.add(txtNumCuenta);
		txtNumCuenta.setColumns(10);
		
		JComboBox comboPregunta = new JComboBox();
		comboPregunta.setBounds(169, 227, 259, 30);
		comboPregunta.addItem("1");
		panelRegistrar.add(comboPregunta);
		
		txtRespuesta = new JTextField();
		txtRespuesta.setBounds(169, 283, 259, 20);
		panelRegistrar.add(txtRespuesta);
		txtRespuesta.setColumns(10);
		
		lblUser = new JLabel("Usuario");
		lblUser.setBounds(32, 78, 46, 14);
		panelRegistrar.add(lblUser);
		
		lblContrasea = new JLabel("Contrase\u00F1a");
		lblContrasea.setBounds(212, 78, 86, 14);
		panelRegistrar.add(lblContrasea);
		
		lblEmail = new JLabel("Email");
		lblEmail.setBounds(32, 118, 46, 14);
		panelRegistrar.add(lblEmail);
		
		lblNumCuenta = new JLabel("IBAN");
		lblNumCuenta.setBounds(43, 167, 46, 14);
		panelRegistrar.add(lblNumCuenta);
		
		lblPreguntaSeg = new JLabel("Pregunta de Seguridad");
		lblPreguntaSeg.setBounds(32, 235, 154, 14);
		panelRegistrar.add(lblPreguntaSeg);
		
		lblRespuesta = new JLabel("Respuesta");
		lblRespuesta.setBounds(32, 286, 116, 14);
		panelRegistrar.add(lblRespuesta);
		
		btnEndRegistr = new JButton("Terminar Registro");
		btnEndRegistr.setBounds(169, 340, 139, 23);
		panelRegistrar.add(btnEndRegistr);
		
		txtName = new JTextField();
		txtName.setBounds(79, 36, 116, 20);
		panelRegistrar.add(txtName);
		txtName.setColumns(10);
		
		txtSurname = new JTextField();
		txtSurname.setBounds(286, 36, 154, 20);
		panelRegistrar.add(txtSurname);
		txtSurname.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(32, 39, 46, 14);
		panelRegistrar.add(lblNombre);
		
		JLabel lblApellidos = new JLabel("Apellidos");
		lblApellidos.setBounds(212, 39, 64, 14);
		panelRegistrar.add(lblApellidos);
		
		txtContrasena = new JPasswordField();
		txtContrasena.setBounds(286, 75, 154, 20);
		panelRegistrar.add(txtContrasena);
		
		JLabel lblRegistroComp = new JLabel("Registro exitoso");
		lblRegistroComp.setForeground(Color.GREEN);
		lblRegistroComp.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegistroComp.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblRegistroComp.setBounds(169, 385, 139, 20);
		lblRegistroComp.setVisible(false);
		panelRegistrar.add(lblRegistroComp);
		
		btnEndRegistr.addActionListener(new ActionListener() {
			
			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent e) {
				try{
		            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/bizum_bd", "root", "");
		            PreparedStatement pst = cn.prepareStatement("insert into usuario values(?,?,?,?,?,?,?,?,?)");
		            
		            pst.setString(1, "0");
		            pst.setString(2, txtUser.getText().trim());   //.TRIM() QUITA LOS ESPACIOS DE LOS LATERALES
		            
		            String passEncrypted = encryptThisString(txtContrasena.getText());
		            System.out.println(passEncrypted);
		            
		            pst.setString(3, passEncrypted.trim());
		            pst.setString(4, txtName.getText().trim());
		            pst.setString(5, txtSurname.getText().trim());
		            pst.setString(6, txtEmail.getText().trim());
		            pst.setString(7, txtNumCuenta.getText().trim());
		            pst.setString(8, comboPregunta.getSelectedItem().toString().trim());
		            pst.setString(9, txtRespuesta.getText().trim());
		            
		            pst.executeUpdate();
		           
					lblRegistroComp.setVisible(true);	

		            txtUser.setText("");
		            txtContrasena.setText("");
		            txtName.setText("");
		            txtSurname.setText("");
		            txtEmail.setText("");
		            txtNumCuenta.setText("");
		            
		            txtRespuesta.setText("");
		        }catch (Exception ex){
		            ex.printStackTrace();
		        }
				
			}
		});
		
		
		btnLogin.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				 try{
			            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/bizum_bd", "root", "");
			            PreparedStatement pst = cn.prepareStatement("select * from usuario where User = ?");
			            pst.setString(1, txtUsuario.getText().trim());
			            
			            ResultSet rs = pst.executeQuery();
			            
			            if(rs.next()){	       
			                String temp_pass = rs.getString("Pass");
			                
			                if (temp_pass.equals(encryptThisString(txtContrasenia.getText()))){
			                	JOptionPane.showMessageDialog(null, "Login exitoso");
			                	
			                }else {
			                	JOptionPane.showMessageDialog(null, "Contraseña incorrecta");		                	
			                }
			                	
			                
			            } else {
			                JOptionPane.showMessageDialog(null, "Usuario no registrado.");
			            }
			            
			        }catch (Exception ex){
			            ex.printStackTrace();
			        }
		};
		
		});
		
		cp.add(tabbedPane);
		
		
		this.setTitle("Login");
		this.setSize(500,500);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		
	}

	
	public static String encryptThisString(String input) 
    { 
        try { 
            // getInstance() method is called with algorithm SHA-512 
            MessageDigest md = MessageDigest.getInstance("SHA-512"); 
  
            // digest() method is called 
            // to calculate message digest of the input string 
            // returned as array of byte 
            byte[] messageDigest = md.digest(input.getBytes()); 
  
            // Convert byte array into signum representation 
            BigInteger no = new BigInteger(1, messageDigest); 
  
            // Convert message digest into hex value 
            String hashtext = no.toString(16); 
  
            // Add preceding 0s to make it 32 bit 
            while (hashtext.length() < 32) { 
                hashtext = "0" + hashtext; 
            } 
  
            // return the HashText 
            return hashtext; 
        } 
  
        // For specifying wrong message digest algorithms 
        catch (NoSuchAlgorithmException e) { 
            throw new RuntimeException(e); 
        } 
    } 
	
	
	public static void main(String[] args) {
		
		// Creamos el logger
		Logger logger = Logger.getLogger(VentanaLogin.class.getName()); 

		// Mensajes log utilizando log(Level level, String msg)
		logger.log(Level.INFO, "Mensage 1"); 
		logger.log(Level.WARNING, "Mensage 2"); 
    
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				new VentanaLogin();
				
			}
			
		});

	}
}
