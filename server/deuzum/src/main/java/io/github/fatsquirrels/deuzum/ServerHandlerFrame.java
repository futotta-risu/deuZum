package io.github.fatsquirrels.deuzum;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Properties;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;

import io.github.fatsquirrels.deuzum.Visual.Dialogs.*;
import io.github.fatsquirrels.deuzum.Visual.Dialogs.Group.*;
import io.github.fatsquirrels.deuzum.Visual.Dialogs.Project.*;
import io.github.fatsquirrels.deuzum.Visual.Dialogs.Transactions.*;
import io.github.fatsquirrels.deuzum.Visual.Dialogs.User.*;
import io.github.fatsquirrels.deuzum.Visual.Style.CustomColors;
import io.github.fatsquirrels.deuzum.Visual.Style.Components.Buttons.FlatButton;
import io.github.fatsquirrels.deuzum.Visual.Style.Components.Buttons.IconizedButton;
import io.github.fatsquirrels.deuzum.Visual.Style.Components.Buttons.MenuButton;
import io.github.fatsquirrels.deuzum.Visual.Style.Components.JPanels.MenuPanel;
import io.github.fatsquirrels.deuzum.Visual.Style.Layout.VerticalFlowLayout;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;

import javax.swing.ImageIcon;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.JTabbedPane;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JTextField;
import javax.swing.JSlider;
import javax.swing.JToggleButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;

import net.miginfocom.swing.MigLayout;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

public class ServerHandlerFrame  extends JFrame{
	
	private Server server = null;
	

	public static Properties properties;
	
	private static final long serialVersionUID = 1L;
	private JPanel panel_Home;
	private JPanel panel_Usuario;
	private JPanel panel_Transaccion;
	private JPanel panel_Config_G;
	private JPanel panel_Proyectos;
	private JPanel panel_Grupos;
	private JTabbedPane panel_Configuration;
	private JPanel central_Mutable_Panel;
	private JTextField txf_ServerName;
	private JTabbedPane panel_Funcionality;
	
	JLabel serverLabel;
	/**
	 * Crea un objeto de ServerHandlerFrame, el cual contiene un frame que controla una instancia de Server.
	 */
	public ServerHandlerFrame() {
		openProperties();
		configLayout();
		configWindow();
		validate();
	}
	
	/**
	 * Configuraci�n del JFrame de ServerHandlerFrame
	 */
	public void configWindow() {
		// TODO Hacer que el icono se pueda cambiar desde propierties
		// Icono de la Aplicacion
		setIconImage(new ImageIcon("data/img/iconoP.png").getImage());
		
		
		setSize(new Dimension(900, 500));
		setMinimumSize(new Dimension(600, 400));
		setVisible(true);
		setTitle("DeuZum Servidor");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void configLayout() {
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		/*
		 * 			STATUS BAR
		 */
		
		// Configuracion del Panel
		JPanel status_Bar = new JPanel();
		status_Bar.setBackground(CustomColors.mBlueR);
		status_Bar.setPreferredSize(new Dimension(0, 53));
		getContentPane().add(status_Bar, BorderLayout.NORTH);
		
		// Componentes
		JLabel deuzumText = new JLabel();
		deuzumText.setIcon(new ImageIcon("data/img/logo.png"));
		status_Bar.setLayout(new BorderLayout(0, 0));
		
		
		status_Bar.add(deuzumText, BorderLayout.WEST);
		
		JPanel sup_Right_Panel = new JPanel();
		sup_Right_Panel.setOpaque(false);
		sup_Right_Panel.setPreferredSize(new Dimension(153, 40));
		status_Bar.add(sup_Right_Panel, BorderLayout.EAST);
		sup_Right_Panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnStartServer = new IconizedButton("symbol","play",35,40);
		sup_Right_Panel.add(btnStartServer);
		JButton btnStopServer = new IconizedButton("symbol","stop",35,40);
		sup_Right_Panel.add(btnStopServer);
		JButton btnExit = new IconizedButton("symbol","pause",35,40);
		sup_Right_Panel.add(btnExit);
		
		btnExit.addActionListener(new ActionListener() {	
			public void actionPerformed(ActionEvent e) {
				if(server != null)  server.stop();
				dispose();	
			}
		});
		
		btnStopServer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				server.stop();
				serverLabel.setText("Server: off");
				
			}
		});
		
		btnStartServer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO hacer que el boton start de error si no hay un localServer de SQL
				Thread hiloStart = new Thread() {
					@Override
					public void run() {
						// Checks if the server is alredy running
						if(Server.isRunning) 
							return;
						
						server = new Server();
						server.setDBName(ServerHandlerFrame.properties.getProperty("server.dbName"));
						server.setBotCount(Integer.valueOf(ServerHandlerFrame.properties.getProperty("server.port")));
						server.setPort(Integer.valueOf(ServerHandlerFrame.properties.getProperty("server.botCount")));
						server.runServer();
						server.start();
						
					}
				};
				
				hiloStart.start();
				serverLabel.setText("Server: Running");
		}});
		
		
		/*
		 *      PANEL CENTRAL
		 */
		
		
		JPanel central_Panel = new JPanel();
		getContentPane().add(central_Panel);
		central_Panel.setLayout(new BorderLayout(0, 0));
		
		//        DIRECTION
		
		JPanel central_Direction_Panel = new JPanel();
		central_Panel.add(central_Direction_Panel, BorderLayout.WEST);
		central_Direction_Panel.setBackground(CustomColors.mBBlue);
		central_Direction_Panel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		
		JPanel central_Direction_Menu_Panel = new JPanel();
		central_Direction_Menu_Panel.setBackground(CustomColors.mBBlue);
		central_Direction_Menu_Panel.setPreferredSize(new Dimension(180,280));
		central_Direction_Menu_Panel.setLayout(new GridLayout(0, 1, 0, 0));
		central_Direction_Panel.add(central_Direction_Menu_Panel);
		//       BOTONES DIRECTION
		
		
		JButton btnHome = new MenuButton("Menu Principal");
		JButton btnUsuario = new MenuButton("Usuarios");
		JButton btnTransaccion = new MenuButton("Transacciones");
		JButton btnProyectos = new MenuButton("Proyectos");
		JButton btnGrupos = new MenuButton("Grupos");
		JButton btnFuncionalidades = new MenuButton("Funcionalidades");
		JButton btnConfiguracionDelServer = new MenuButton("Configuracion");
		
		
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchPanel(panel_Home);
			}
		});
		btnConfiguracionDelServer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchPanel(panel_Config_G);
			}
		});
		btnFuncionalidades.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchPanel(panel_Funcionality);
			}
		});
		btnGrupos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchPanel(panel_Grupos);
			}
		});
		btnProyectos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchPanel(panel_Proyectos);
			}
		});
		btnTransaccion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchPanel(panel_Transaccion);
			}
		});
		btnUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchPanel(panel_Usuario);
			}
		});
		
		central_Direction_Menu_Panel.add(btnHome);
		central_Direction_Menu_Panel.add(btnUsuario);
		central_Direction_Menu_Panel.add(btnTransaccion);
		central_Direction_Menu_Panel.add(btnProyectos);
		central_Direction_Menu_Panel.add(btnGrupos);
		central_Direction_Menu_Panel.add(btnFuncionalidades);
		central_Direction_Menu_Panel.add(btnConfiguracionDelServer);
		
		//         MUTABLE TABLE
		
		central_Mutable_Panel = new JPanel();
		central_Mutable_Panel.setBackground(Color.WHITE);
		central_Panel.add(central_Mutable_Panel, BorderLayout.CENTER);
		central_Mutable_Panel.setLayout(new BorderLayout(0, 0));
		
		panel_Usuario = new MenuPanel();
		central_Mutable_Panel.add(panel_Usuario);
		
		//  BOTONES USUARIO
		
		JButton btnCrearUser = new FlatButton("Crear Usuario");
		panel_Usuario.add(btnCrearUser, "cell 0 0,growx");
		btnCrearUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new createUser(server.getConnection());
			}
		});
		
			JButton btnEditarUsuario = new FlatButton("Editar Usuario");
			panel_Usuario.add(btnEditarUsuario, "cell 0 1,growx");
			btnEditarUsuario.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new editUser(server.getConnection());
				}
			});
			
			JButton btnEliminarUsuario = new FlatButton("Eliminar Usuario");
			panel_Usuario.add(btnEliminarUsuario, "cell 0 2,growx");
			btnEliminarUsuario.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new deleteUser(server.getConnection());
				}
			});
			
			JButton btnVerCuentas = new FlatButton("Ver cuentas Usuario");
			panel_Usuario.add(btnVerCuentas, "cell 0 3");
			central_Mutable_Panel.add(panel_Usuario);
			btnVerCuentas.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new UserList(server.getConnection());
				}
			});
			
			
			//        PANELES MUTABLE
			
			
			panel_Proyectos = new MenuPanel();
			central_Mutable_Panel.add(panel_Proyectos);
			
			
			//   BOTONES PROYECTO
			
			JButton btnCrearProyecto = new FlatButton("Crear Proyecto");
			panel_Proyectos.add(btnCrearProyecto, "cell 0 0,growx");
			btnCrearProyecto.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new createProyect(server.getConnection());
				}
			});
			
			JButton btnEditarProyecto = new FlatButton("Editar Proyecto");
			panel_Proyectos.add(btnEditarProyecto, "cell 0 1,growx");
			btnEditarProyecto.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new editProyect(server.getConnection());
				}
			});
			
			JButton btnEliminarProyecto = new FlatButton("Eliminar Proyecto");
			btnEliminarProyecto.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new deleteProyect(server.getConnection());
				}
			});
			panel_Proyectos.add(btnEliminarProyecto, "cell 0 2,growx");
			
			JButton btnVerProyectos = new FlatButton("Ver Proyectos");
			panel_Proyectos.add(btnVerProyectos, "cell 0 3,growx");
			btnVerProyectos.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new ProyectList(server.getConnection());
				}
			});
			
			
				
			panel_Grupos = new MenuPanel();
			central_Mutable_Panel.add(panel_Grupos);
			
			// BOTONES GRUPO
			
			JButton btnCrearGrupo = new FlatButton("Crear Grupo");
			panel_Grupos.add(btnCrearGrupo, "cell 0 0,growx");
			btnCrearGrupo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new createGroup(server.getConnection());
				}
			});
			
			JButton btnEliminarGrupo = new FlatButton("Eliminar Grupo");
			panel_Grupos.add(btnEliminarGrupo, "cell 0 1,growx");
			btnEliminarGrupo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new deleteGroup(server.getConnection());
				}
			});
			
			JButton btnEditarGrupo = new FlatButton("Editar Grupo");
			btnEditarGrupo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new editGroup(server.getConnection());
				}
			});
			panel_Grupos.add(btnEditarGrupo, "cell 0 2,growx,aligny top");
			
			
			JButton btnVerGrupos = new FlatButton("Ver Grupos");
			panel_Grupos.add(btnVerGrupos, "cell 0 3,growx,aligny top");
			
			
			panel_Transaccion = new JPanel();
			central_Mutable_Panel.add(panel_Transaccion);
			
			
			//  BOTONES TRANSACCION
					
			JButton btnRealizarTransaccion = new FlatButton("Realizar Transaccion");
			btnRealizarTransaccion.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new createTransaction(server.getConnection());
				}
			});
			panel_Transaccion.add(btnRealizarTransaccion, "cell 0 0,growx,alignx left,aligny top");
			
			JButton btnEliminarTransaccion = new FlatButton("Eliminar Transaccion");
			panel_Transaccion.add(btnEliminarTransaccion, "cell 0 1,growx");
			btnEliminarTransaccion.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new deleteTransaction(server.getConnection());
				}
			});
			
			JButton btnVerTransacciones = new FlatButton("Ver Transacciones");
			panel_Transaccion.add(btnVerTransacciones, "cell 0 2,growx");
			btnVerTransacciones.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new TransactionList(server.getConnection());
				}
			});
			
			panel_Funcionality = new JTabbedPane(JTabbedPane.TOP);
			central_Mutable_Panel.add(panel_Funcionality);
			
			JPanel panel_Funct_Database = new JPanel();
			panel_Funcionality.addTab("Base de Datos", null, panel_Funct_Database, null);
			
			//		BOTONES FUNCIONALIDADES
			
			JButton btnRellenarBd = new FlatButton("Rellenar BD");
			panel_Funct_Database.add(btnRellenarBd);
			btnRellenarBd.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub	
				}
			});
			
			JPanel panel_Funct_IA = new JPanel();
			panel_Funcionality.addTab("IA", null, panel_Funct_IA, null);
			
			JButton btnClusterizarDb = new JButton("Clusterizar DB");
			panel_Funct_IA.add(btnClusterizarDb);
			
			JPanel panel_Funct_Seguridad = new JPanel();
			panel_Funcionality.addTab("Seguridad", null, panel_Funct_Seguridad, null);
			GridBagLayout gbl_panel_Funct_Seguridad = new GridBagLayout();
			gbl_panel_Funct_Seguridad.columnWidths = new int[]{44, 75, 93, 0};
			gbl_panel_Funct_Seguridad.rowHeights = new int[]{23, 0, 0, 0, 0, 0};
			gbl_panel_Funct_Seguridad.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
			gbl_panel_Funct_Seguridad.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			panel_Funct_Seguridad.setLayout(gbl_panel_Funct_Seguridad);
			
			JLabel lbl_Encriptar = new JLabel("Encriptaci\u00F3n");
			GridBagConstraints gbc_lbl_Encriptar = new GridBagConstraints();
			gbc_lbl_Encriptar.anchor = GridBagConstraints.WEST;
			gbc_lbl_Encriptar.gridwidth = 2;
			gbc_lbl_Encriptar.insets = new Insets(0, 0, 5, 0);
			gbc_lbl_Encriptar.gridx = 1;
			gbc_lbl_Encriptar.gridy = 1;
			panel_Funct_Seguridad.add(lbl_Encriptar, gbc_lbl_Encriptar);
			
			JButton btnEncriptar = new JButton("Encriptar");
			GridBagConstraints gbc_btnEncriptar = new GridBagConstraints();
			gbc_btnEncriptar.anchor = GridBagConstraints.NORTHWEST;
			gbc_btnEncriptar.insets = new Insets(0, 0, 5, 5);
			gbc_btnEncriptar.gridx = 1;
			gbc_btnEncriptar.gridy = 2;
			panel_Funct_Seguridad.add(btnEncriptar, gbc_btnEncriptar);
			
			JButton btnDesencriptar = new JButton("Desencriptar");
			GridBagConstraints gbc_btnDesencriptar = new GridBagConstraints();
			gbc_btnDesencriptar.insets = new Insets(0, 0, 5, 0);
			gbc_btnDesencriptar.anchor = GridBagConstraints.NORTHWEST;
			gbc_btnDesencriptar.gridx = 2;
			gbc_btnDesencriptar.gridy = 2;
			panel_Funct_Seguridad.add(btnDesencriptar, gbc_btnDesencriptar);
			
			JLabel lblNewLabel = new JLabel("New label");
			GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
			gbc_lblNewLabel.insets = new Insets(0, 0, 0, 5);
			gbc_lblNewLabel.gridx = 1;
			gbc_lblNewLabel.gridy = 4;
			panel_Funct_Seguridad.add(lblNewLabel, gbc_lblNewLabel);
			
			panel_Config_G = new JPanel();
			central_Mutable_Panel.add(panel_Config_G);
			panel_Config_G.setLayout(new BorderLayout(0, 0));
			
			panel_Configuration = new JTabbedPane(JTabbedPane.TOP);
			panel_Config_G.add(panel_Configuration);
			
			JPanel panel_Config_Server = new JPanel();
			panel_Configuration.addTab("Server", null, panel_Config_Server, null);
			GridBagLayout gbl_panel_Config_Server = new GridBagLayout();
			gbl_panel_Config_Server.columnWidths = new int[]{39, 87, 105, 28, 61, 77, 43, 0};
			gbl_panel_Config_Server.rowHeights = new int[]{22, 22, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
			gbl_panel_Config_Server.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			gbl_panel_Config_Server.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			panel_Config_Server.setLayout(gbl_panel_Config_Server);
			
			JLabel lbl_Server_Port = new JLabel("Puerto del Servidor");
			GridBagConstraints gbc_lbl_Server_Port = new GridBagConstraints();
			gbc_lbl_Server_Port.anchor = GridBagConstraints.WEST;
			gbc_lbl_Server_Port.insets = new Insets(0, 0, 5, 5);
			gbc_lbl_Server_Port.gridx = 1;
			gbc_lbl_Server_Port.gridy = 1;
			panel_Config_Server.add(lbl_Server_Port, gbc_lbl_Server_Port);
			
			final JSpinner spinner_Port = new JSpinner();
			spinner_Port.setModel(new SpinnerNumberModel(Integer.parseInt((String)ServerHandlerFrame.properties.get("server.port")), 0, 65535, 1));
			GridBagConstraints gbc_spinner_Port = new GridBagConstraints();
			gbc_spinner_Port.fill = GridBagConstraints.HORIZONTAL;
			gbc_spinner_Port.insets = new Insets(0, 0, 5, 5);
			gbc_spinner_Port.gridx = 4;
			gbc_spinner_Port.gridy = 1;
			panel_Config_Server.add(spinner_Port, gbc_spinner_Port);
			
			JLabel lblNombreDelServidor = new JLabel("Nombre del Servidor");
			GridBagConstraints gbc_lblNombreDelServidor = new GridBagConstraints();
			gbc_lblNombreDelServidor.anchor = GridBagConstraints.WEST;
			gbc_lblNombreDelServidor.insets = new Insets(0, 0, 5, 5);
			gbc_lblNombreDelServidor.gridx = 1;
			gbc_lblNombreDelServidor.gridy = 2;
			panel_Config_Server.add(lblNombreDelServidor, gbc_lblNombreDelServidor);
			
			txf_ServerName = new JTextField();
			txf_ServerName.setText((String) ServerHandlerFrame.properties.get("server.name"));
			GridBagConstraints gbc_txf_ServerName = new GridBagConstraints();
			gbc_txf_ServerName.gridwidth = 2;
			gbc_txf_ServerName.insets = new Insets(0, 0, 5, 5);
			gbc_txf_ServerName.fill = GridBagConstraints.HORIZONTAL;
			gbc_txf_ServerName.gridx = 1;
			gbc_txf_ServerName.gridy = 3;
			panel_Config_Server.add(txf_ServerName, gbc_txf_ServerName);
			txf_ServerName.setColumns(10);
			
			JLabel lblConnectionTimeOut = new JLabel("Connection Time Out");
			GridBagConstraints gbc_lblConnectionTimeOut = new GridBagConstraints();
			gbc_lblConnectionTimeOut.insets = new Insets(0, 0, 5, 5);
			gbc_lblConnectionTimeOut.gridx = 1;
			gbc_lblConnectionTimeOut.gridy = 6;
			panel_Config_Server.add(lblConnectionTimeOut, gbc_lblConnectionTimeOut);
			
			final JSlider slider_ConTimeOut = new JSlider();
			slider_ConTimeOut.setValue(Integer.parseInt((String)ServerHandlerFrame.properties.get("server.conexionTimeOut")));
			GridBagConstraints gbc_slider_ConTimeOut = new GridBagConstraints();
			gbc_slider_ConTimeOut.fill = GridBagConstraints.HORIZONTAL;
			gbc_slider_ConTimeOut.gridwidth = 2;
			gbc_slider_ConTimeOut.insets = new Insets(0, 0, 5, 5);
			gbc_slider_ConTimeOut.gridx = 1;
			gbc_slider_ConTimeOut.gridy = 7;
			panel_Config_Server.add(slider_ConTimeOut, gbc_slider_ConTimeOut);
			
			JLabel lblActualTimeOut = new JLabel("Actual Time Out");
			GridBagConstraints gbc_lblActualTimeOut = new GridBagConstraints();
			gbc_lblActualTimeOut.gridwidth = 2;
			gbc_lblActualTimeOut.insets = new Insets(0, 0, 5, 5);
			gbc_lblActualTimeOut.gridx = 4;
			gbc_lblActualTimeOut.gridy = 7;
			panel_Config_Server.add(lblActualTimeOut, gbc_lblActualTimeOut);
			
			JPanel panel_Config_Seguridad = new JPanel();
			panel_Configuration.addTab("Seguridad", null, panel_Config_Seguridad, null);
			GridBagLayout gbl_panel_Config_Seguridad = new GridBagLayout();
			gbl_panel_Config_Seguridad.columnWidths = new int[]{0, 0, 0, 0, 254, 0, 0};
			gbl_panel_Config_Seguridad.rowHeights = new int[]{0, 0, 0, 0, 0};
			gbl_panel_Config_Seguridad.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
			gbl_panel_Config_Seguridad.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			panel_Config_Seguridad.setLayout(gbl_panel_Config_Seguridad);
			
			JLabel lblEncriptacinDeSockets = new JLabel("Encriptaci\u00F3n de Sockets");
			GridBagConstraints gbc_lblEncriptacinDeSockets = new GridBagConstraints();
			gbc_lblEncriptacinDeSockets.insets = new Insets(0, 0, 5, 5);
			gbc_lblEncriptacinDeSockets.gridx = 2;
			gbc_lblEncriptacinDeSockets.gridy = 1;
			panel_Config_Seguridad.add(lblEncriptacinDeSockets, gbc_lblEncriptacinDeSockets);
			
			JToggleButton tglbtnNewToggleButton = new JToggleButton("Si");
			GridBagConstraints gbc_tglbtnNewToggleButton = new GridBagConstraints();
			gbc_tglbtnNewToggleButton.insets = new Insets(0, 0, 5, 5);
			gbc_tglbtnNewToggleButton.gridx = 4;
			gbc_tglbtnNewToggleButton.gridy = 1;
			panel_Config_Seguridad.add(tglbtnNewToggleButton, gbc_tglbtnNewToggleButton);
			
			JLabel lblFuncinDeEncriptacin = new JLabel("Funci\u00F3n de Encriptaci\u00F3n");
			GridBagConstraints gbc_lblFuncinDeEncriptacin = new GridBagConstraints();
			gbc_lblFuncinDeEncriptacin.insets = new Insets(0, 0, 5, 5);
			gbc_lblFuncinDeEncriptacin.gridx = 2;
			gbc_lblFuncinDeEncriptacin.gridy = 2;
			panel_Config_Seguridad.add(lblFuncinDeEncriptacin, gbc_lblFuncinDeEncriptacin);
			
			JComboBox<String> comboBox = new JComboBox<String>();
			comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"RSA", "SHA256", "MD5", "Blowfish", "Cesar", "Vigenere"}));
			GridBagConstraints gbc_comboBox = new GridBagConstraints();
			gbc_comboBox.insets = new Insets(0, 0, 5, 5);
			gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
			gbc_comboBox.gridx = 4;
			gbc_comboBox.gridy = 2;
			panel_Config_Seguridad.add(comboBox, gbc_comboBox);
			
			JPanel panel_1 = new JPanel();
			panel_Config_G.add(panel_1, BorderLayout.SOUTH);
			
			JButton btn_Save = new FlatButton("Guardar");
			btn_Save.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					updateProperty("server.conexionTimeOut",String.valueOf(slider_ConTimeOut.getValue()) );
					updateProperty("server.port",spinner_Port.getValue().toString());
					updateProperty("server.name",txf_ServerName.getText());
			
					storeProperties();
				}
			});
			panel_1.setLayout(new BorderLayout(0, 0));
			panel_1.add(btn_Save, BorderLayout.EAST);
			
			panel_Home = new JPanel();
			panel_Home.setBackground(new Color(255, 255, 255));
			central_Mutable_Panel.add(panel_Home, BorderLayout.CENTER);
			panel_Home.setLayout(new MigLayout("fill,insets 0", "[455.00px,grow,center][200.00px,grow 60,center]", "[408px,grow]"));
			
			JPanel panel_Home_Center = new JPanel();
			panel_Home_Center.setBackground(new Color(255, 255, 255));
			panel_Home.add(panel_Home_Center, "cell 0 0,grow");
			panel_Home_Center.setLayout(new BorderLayout(0, 0));
			
			JPanel panel_Home_Title = new JPanel();
			panel_Home_Title.setBackground(new Color(255, 255, 255));
			panel_Home_Center.add(panel_Home_Title, BorderLayout.NORTH);
			
			JLabel lblBienvenido = new JLabel("Bienvenido");
			panel_Home_Title.add(lblBienvenido);
			lblBienvenido.setFont(new Font("Maiandra GD", Font.PLAIN, 29));
			
			JPanel panel_Home_Info = new JPanel();
			panel_Home_Info.setBackground(new Color(255, 255, 255));
			panel_Home_Center.add(panel_Home_Info);
			panel_Home_Info.setLayout(new VerticalFlowLayout(0,10,10));
			
			
			JTextArea txtrEsteEsEl = new JTextArea();
			panel_Home_Info.add(txtrEsteEsEl, BorderLayout.NORTH);
			txtrEsteEsEl.setWrapStyleWord(true);
			txtrEsteEsEl.setEditable(false);
			
			txtrEsteEsEl.setText("Este es el software Deuzum, creado por el grupo FatSquirrels para ejecutar un servidor de gesti\u00F3n de dinero online con diversas funciones de IA implementadas dentro. Dentro de estas se encontrar\u00EDan las siguientes: \r\n\t-IA \r\n\t-Seguridad\r\n");
			txtrEsteEsEl.setLineWrap(true);
			txtrEsteEsEl.setOpaque(false);
			txtrEsteEsEl.setFocusable(false);
			txtrEsteEsEl.setBackground(UIManager.getColor("Label.background"));
			txtrEsteEsEl.setFont(UIManager.getFont("Label.font"));
			txtrEsteEsEl.setBorder(UIManager.getBorder("Label.border"));
			
			JLabel lblVersiones = new JLabel("Versiones");
			lblVersiones.setFont(new Font("Tahoma", Font.PLAIN, 18));
			panel_Home_Info.add(lblVersiones, BorderLayout.WEST);
			
			JTextArea txtrActualmenteEstaAplicacin = new JTextArea();
			txtrActualmenteEstaAplicacin.setEditable(false);
			txtrActualmenteEstaAplicacin.setLineWrap(true);
			txtrActualmenteEstaAplicacin.setWrapStyleWord(true);
			txtrActualmenteEstaAplicacin.setLineWrap(true);
			txtrActualmenteEstaAplicacin.setOpaque(false);
			txtrActualmenteEstaAplicacin.setFocusable(false);
			txtrActualmenteEstaAplicacin.setBackground(UIManager.getColor("Label.background"));
			txtrActualmenteEstaAplicacin.setFont(UIManager.getFont("Label.font"));
			txtrActualmenteEstaAplicacin.setBorder(UIManager.getBorder("Label.border"));
			txtrActualmenteEstaAplicacin.setText("Actualmente esta aplicaci\u00F3n se encuentra en la versi\u00F3n alpha. Esta permite la realizaci\u00F3n de las tareas m\u00E1s basicas pero requiere todav\u00EDa bastantes cambios para evitar errores y mejorar el rendimiento. Las caracteristicas de esta versi\u00F3n han sido las siguientes:\r\n\r\n\t- Ventanas de control de la base de datos\r\n\t- Plantilla de la base de datos.\r\n\t- Estado actual del servidor");
			panel_Home_Info.add(txtrActualmenteEstaAplicacin, BorderLayout.EAST);
			
			JPanel panel_Home_Info_Botones = new JPanel();
			panel_Home_Info_Botones.setBackground(Color.WHITE);
			panel_Home_Info.add(panel_Home_Info_Botones, BorderLayout.SOUTH);
			
			JButton btn_FAQ = new JButton("Documentacion");
			btn_FAQ.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					URL url = null;
					try {
						url = new URL("https://github.com/futotta-risu/deuZum/wiki");
					} catch (MalformedURLException e1) {
						e1.printStackTrace();
					}
					openWebpage(url);
				}
			});
			panel_Home_Info_Botones.add(btn_FAQ);
			
			JButton btnNewButton_1 = new JButton("Sobre Nosotros");
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					URL url = null;
					try {
						url = new URL("https://github.com/futotta-risu/deuZum/graphs/contributors");
					} catch (MalformedURLException e1) {
						e1.printStackTrace();
					}
					openWebpage(url);
				}
			});
			panel_Home_Info_Botones.add(btnNewButton_1);
			
			JPanel panel_Home_Lateral = new JPanel();
			panel_Home_Lateral.setBackground(new Color(176, 224, 230));
			
			panel_Home_Lateral.setLayout(new  VerticalFlowLayout(0,true));
			JPanel panel_Home_Lateral_Status = new JPanel();
			panel_Home_Lateral.add(panel_Home_Lateral_Status);
			panel_Home_Lateral_Status.setBackground(CustomColors.mBOrangeL);
			panel_Home_Lateral_Status.setLayout(new VerticalFlowLayout(0,10,10));
			serverLabel = new JLabel("Status: Off");
			serverLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
			panel_Home_Lateral_Status.add(serverLabel);
			serverLabel.setForeground(new Color(0, 0, 0));
			
			JPanel panel_Lateral_Info = new JPanel();
			panel_Lateral_Info.setLayout(new VerticalFlowLayout(0,5,5));
			panel_Home_Lateral_Status.add(panel_Lateral_Info);
			
			JLabel lblVersion = new JLabel("Version: alpha0.0.1");
			panel_Lateral_Info.add(lblVersion);
			
			JLabel lblNewLabel_1 = new JLabel("FatSquirrels");
			lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
			panel_Lateral_Info.add(lblNewLabel_1);
			
			JPanel panel_Home_Lateral_Help = new JPanel();
			panel_Home_Lateral_Help.setPreferredSize(new Dimension(200,600));
			panel_Home_Lateral.add(panel_Home_Lateral_Help);
			panel_Home_Lateral_Help.setBackground(CustomColors.mBBlueGrayL);
			
			JScrollPane scrollPanel_Lateral = new JScrollPane(panel_Home_Lateral,
					JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
		            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			scrollPanel_Lateral.setBorder(new EmptyBorder(0, 0, 0, 0));
			panel_Home.add(scrollPanel_Lateral, "cell 1 0,grow");
			btnVerGrupos.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new GroupList(server.getConnection());
				}
			});
			
			

	}

	/**
	 * Cambia el panel por capas central por otro panel introducido como parametro.
	 * @param panel
	 */
	public void switchPanel(JPanel panel) {
		central_Mutable_Panel.removeAll();
		central_Mutable_Panel.add(panel);
		central_Mutable_Panel.validate();
		central_Mutable_Panel.repaint();
	}
	public void switchPanel(JTabbedPane panel) {
		central_Mutable_Panel.removeAll();
		central_Mutable_Panel.add(panel);
		central_Mutable_Panel.validate();
		central_Mutable_Panel.repaint();
	}
	

	/**
	 * Abre y carga el archivo properties.
	 */
	public void openProperties() {
		ServerHandlerFrame.properties = new Properties();
		try(FileInputStream f = new FileInputStream("./data/server.properties")){
			properties.load(f);
			
		}catch(FileNotFoundException e1) {
			System.err.println("El archivo no se encuentra en el lugar indicado.");
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void updateProperty(String property, String value) {
		properties.setProperty(property, value);
	}
	
	public void storeProperties() {
		try {
			properties.store(new FileOutputStream("./data/server.properties"), null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static boolean openWebpage(URI uri) {
	    Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
	    if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
	        try {
	            desktop.browse(uri);
	            return true;
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	    return false;
	}

	public static boolean openWebpage(URL url) {
	    try {
	        return openWebpage(url.toURI());
	    } catch (URISyntaxException e) {
	        e.printStackTrace();
	    }
	    return false;
	}
	
	
	public static void main(String[] args) {
		
		new ServerHandlerFrame();
	}
}
