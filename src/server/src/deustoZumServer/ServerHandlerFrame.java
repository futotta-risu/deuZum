package deustoZumServer;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;

import deustoZumServer.FileManagement.Icons;
import deustoZumServer.Visual.Dialogs.*;
import deustoZumServer.Visual.Dialogs.Group.*;
import deustoZumServer.Visual.Dialogs.Project.*;
import deustoZumServer.Visual.Dialogs.Transactions.*;
import deustoZumServer.Visual.Dialogs.User.*;
import deustoZumServer.Visual.Style.CustomColors;
import deustoZumServer.Visual.Style.Components.Buttons.FlatButton;
import deustoZumServer.Visual.Style.Components.Buttons.MenuButton;
import deustoZumServer.Visual.Style.Components.JPanels.MenuPanel;

import java.awt.BorderLayout;
import javax.swing.JOptionPane;
import java.awt.Color;
import java.awt.Font;

import javax.swing.Icon;
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

public class ServerHandlerFrame  extends JFrame{
	
	private Server server = null;
	

	public static Properties properties;
	
	private static final long serialVersionUID = 1L;
	private JPanel panel_Usuario;
	private JPanel panel_Transaccion;
	private JPanel panel_Proyectos;
	private JPanel panel_Grupos;
	private JTabbedPane panel_Configuration;
	private JPanel central_Mutable_Panel;
	private JTextField txf_ServerName;
	private JTabbedPane panel_Funcionality;
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
		
		
		setSize(700, 500);
		setVisible(true);
		setTitle("DeuZum Servidor");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void configLayout() {
		
		Icon playIcon = Icons.loadIcon("play.png", 35);
		Icon stopIcon = Icons.loadIcon("stop.png", 35);
		Icon pauseIcon = Icons.loadIcon("pause.png", 35);
		
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		/*
		 * 			STATUS BAR
		 */
		
		// Configuracion del Panel
		JPanel status_Bar = new JPanel();
		status_Bar.setBackground(CustomColors.mBlueR);
		status_Bar.setPreferredSize(new Dimension(0, 50));
		getContentPane().add(status_Bar, BorderLayout.NORTH);
		
		// Componentes
		JLabel deuzumText = new JLabel();
		deuzumText.setIcon(new ImageIcon("data/img/logo.png"));
		JLabel serverLabel = new JLabel("Status: Off");
		serverLabel.setForeground(Color.WHITE);
		status_Bar.setLayout(new BorderLayout(0, 0));
		
		
		status_Bar.add(deuzumText, BorderLayout.WEST);
		status_Bar.add(serverLabel);
		
		JPanel sup_Right_Panel = new JPanel();
		sup_Right_Panel.setPreferredSize(new Dimension(150,50));
		sup_Right_Panel.setOpaque(false);
		status_Bar.add(sup_Right_Panel, BorderLayout.EAST);
		sup_Right_Panel.setLayout(new GridLayout(0, 3, 0, 0));
		
		JButton btnStartServer = new FlatButton(playIcon,40);
		sup_Right_Panel.add(btnStartServer);
		JButton btnStopServer = new FlatButton(stopIcon,40);
		sup_Right_Panel.add(btnStopServer);
		JButton btnExit = new FlatButton(pauseIcon,40);
		sup_Right_Panel.add(btnExit);
		
		btnExit.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				if(server != null)  server.stop();
				dispose();	
			}
		});
		
		btnStopServer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				server.stop();
				serverLabel.setText("Server: off");
				
			}
		});
		
		btnStartServer.addActionListener(new ActionListener() {
			@Override
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
		central_Direction_Menu_Panel.setPreferredSize(new Dimension(180,200));
		central_Direction_Menu_Panel.setLayout(new GridLayout(0, 1, 0, 0));
		central_Direction_Panel.add(central_Direction_Menu_Panel);
		//       BOTONES DIRECTION
		
		JButton btnUsuario = new MenuButton("Usuarios");
		JButton btnTransaccion = new MenuButton("Transacciones");
		JButton btnProyectos = new MenuButton("Proyectos");
		JButton btnGrupos = new MenuButton("Grupos");
		JButton btnFuncionalidades = new MenuButton("Funcionalidades");
		JButton btnConfiguracionDelServer = new MenuButton("Configuracion");
		
		
		
		btnConfiguracionDelServer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchPanel(panel_Configuration);
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
			central_Mutable_Panel.add(panel_Grupos, BorderLayout.CENTER);
			
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
			btnVerGrupos.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new GroupList(server.getConnection());
				}
			});
			
			panel_Funcionality = new JTabbedPane(JTabbedPane.TOP);
			central_Mutable_Panel.add(panel_Funcionality, BorderLayout.CENTER);
			
			JPanel panel_Funct_Database = new JPanel();
			panel_Funcionality.addTab("New tab", null, panel_Funct_Database, null);
			
			//		BOTONES FUNCIONALIDADES
			
			JButton btnRellenarBd = new FlatButton("Rellenar BD");
			panel_Funct_Database.add(btnRellenarBd);
			btnRellenarBd.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub	
				}
			});
			
			JPanel panel_Funct_IA = new JPanel();
			panel_Funcionality.addTab("New tab", null, panel_Funct_IA, null);
			
			JPanel panel_Funct_Statistics = new JPanel();
			panel_Funcionality.addTab("New tab", null, panel_Funct_Statistics, null);
			
			JPanel panel_5 = new JPanel();
			panel_Funcionality.addTab("New tab", null, panel_5, null);
			
			panel_Configuration = new JTabbedPane(JTabbedPane.TOP);
			central_Mutable_Panel.add(panel_Configuration, BorderLayout.CENTER);
			
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
			
			JSpinner spinner_Port = new JSpinner();
			spinner_Port.setModel(new SpinnerNumberModel(Integer.parseInt((String)this.properties.get("server.port")), 0, 65535, 1));
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
			txf_ServerName.setText((String) this.properties.get("server.name"));
			GridBagConstraints gbc_txf_ServerName = new GridBagConstraints();
			gbc_txf_ServerName.gridwidth = 2;
			gbc_txf_ServerName.insets = new Insets(0, 0, 5, 5);
			gbc_txf_ServerName.fill = GridBagConstraints.HORIZONTAL;
			gbc_txf_ServerName.gridx = 1;
			gbc_txf_ServerName.gridy = 3;
			panel_Config_Server.add(txf_ServerName, gbc_txf_ServerName);
			txf_ServerName.setColumns(10);
			
			
			//		 BOTONES CONFIGURACION
			
			//TODO Hacer que los parametros recibidos en los dialogos cambien el properties del server
			
			
			JButton btnMaxConnection = new FlatButton("Socket Properties");
			GridBagConstraints gbc_btnMaxConnection = new GridBagConstraints();
			gbc_btnMaxConnection.anchor = GridBagConstraints.NORTHWEST;
			gbc_btnMaxConnection.insets = new Insets(0, 0, 5, 5);
			gbc_btnMaxConnection.gridx = 1;
			gbc_btnMaxConnection.gridy = 5;
			panel_Config_Server.add(btnMaxConnection, gbc_btnMaxConnection);
			btnMaxConnection.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String maxConnection = JOptionPane.showInputDialog("Introducir numero maximo de conexiones");
					updateProperty("server.maxConnection", maxConnection);
					
					if(Server.isRunning) server.restart();						
				}
			});
			
			JLabel lblConnectionTimeOut = new JLabel("Connection Time Out");
			GridBagConstraints gbc_lblConnectionTimeOut = new GridBagConstraints();
			gbc_lblConnectionTimeOut.insets = new Insets(0, 0, 5, 5);
			gbc_lblConnectionTimeOut.gridx = 1;
			gbc_lblConnectionTimeOut.gridy = 6;
			panel_Config_Server.add(lblConnectionTimeOut, gbc_lblConnectionTimeOut);
			
			JSlider slider_ConTimeOut = new JSlider();
			slider_ConTimeOut.setValue(Integer.parseInt((String)this.properties.get("server.conexionTimeOut")));
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
			
			JButton btnCancelar = new JButton("Cancelar");
			GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
			gbc_btnCancelar.insets = new Insets(0, 0, 0, 5);
			gbc_btnCancelar.gridx = 4;
			gbc_btnCancelar.gridy = 12;
			panel_Config_Server.add(btnCancelar, gbc_btnCancelar);
			
			JButton btnGuardadConfiguracin = new JButton("Guardad ");
			btnGuardadConfiguracin.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					updateProperty("server.conexionTimeOut",String.valueOf(slider_ConTimeOut.getValue()) );
					updateProperty("server.port",spinner_Port.getValue().toString());
					updateProperty("server.name",txf_ServerName.getText());
			
					storeProperties();
				}
			});
			GridBagConstraints gbc_btnGuardadConfiguracin = new GridBagConstraints();
			gbc_btnGuardadConfiguracin.insets = new Insets(0, 0, 0, 5);
			gbc_btnGuardadConfiguracin.gridx = 5;
			gbc_btnGuardadConfiguracin.gridy = 12;
			panel_Config_Server.add(btnGuardadConfiguracin, gbc_btnGuardadConfiguracin);
			
			JPanel panel_Config_IA = new JPanel();
			panel_Configuration.addTab("IA", null, panel_Config_IA, null);
			
			JPanel panel_Config_Seguridad = new JPanel();
			panel_Configuration.addTab("Seguridad", null, panel_Config_Seguridad, null);
			
			JPanel panel_2 = new JPanel();
			panel_Configuration.addTab("New tab", null, panel_2, null);
			
			JPanel panel_3 = new JPanel();
			panel_Configuration.addTab("New tab", null, panel_3, null);
			
			

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
		this.properties = new Properties();
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
	
	public static void main(String[] args) {
		
		new ServerHandlerFrame();
	}
}
