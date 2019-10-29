package deustoZumServer;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.UIDefaults;
import javax.swing.UIManager;

import deustoZumServer.FileManagement.Icons;
import deustoZumServer.Visual.Dialogs.*;
import deustoZumServer.Visual.Dialogs.Group.createGroup;
import deustoZumServer.Visual.Dialogs.Group.deleteGroup;
import deustoZumServer.Visual.Dialogs.Group.editGroup;
import deustoZumServer.Visual.Dialogs.Project.createProyect;
import deustoZumServer.Visual.Dialogs.Project.deleteProyect;
import deustoZumServer.Visual.Dialogs.Project.editProyect;
import deustoZumServer.Visual.Dialogs.Transactions.*;
import deustoZumServer.Visual.Dialogs.User.*;
import deustoZumServer.Visual.Style.CustomColors;
import deustoZumServer.Visual.Style.Components.Buttons.FlatButton;
import deustoZumServer.Visual.Style.Components.Buttons.MenuButton;
import deustoZumServer.Visual.Style.Components.JPanels.MenuPanel;

import java.awt.BorderLayout;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import java.awt.Dimension;
import java.awt.Insets;
import java.awt.GridBagLayout;
import java.awt.SystemColor;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.FlowLayout;

public class ServerHandlerFrame  extends JFrame{
	
	private Server server = null;
	
	private static final long serialVersionUID = 1L;
	private JPanel panel_Usuario;
	private JPanel panel_Transaccion;
	private JPanel panel_Proyectos;
	private JPanel panel_Grupos;
	private JPanel panel_Funcionalidades;
	private JPanel panel_Configuracion;
	private JPanel central_Mutable_Panel;
	/**
	 * Crea un objeto de ServerHandlerFrame, el cual contiene un frame que controla una instancia de Server.
	 */
	public ServerHandlerFrame() {
		
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
		
		Icon playIcon = Icons.loadIcon("play.png", 25);
		Icon stopIcon = Icons.loadIcon("stop.png", 25);
		Icon pauseIcon = Icons.loadIcon("pause.png", 25);
		
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		/*
		 * 			STATUS BAR
		 */
		
		// Configuracion del Panel
		JPanel status_Bar = new JPanel();
		status_Bar.setBackground(CustomColors.mBlueR);
		status_Bar.setPreferredSize(new Dimension(getWidth(),38));
		getContentPane().add(status_Bar, BorderLayout.NORTH);
		
		// Componentes
		JLabel deuzumText = new JLabel("Deuzum");
		deuzumText.setForeground(Color.CYAN);
		deuzumText.setFont(new Font("Dialog", Font.BOLD, 14));
		
		JLabel serverLabel = new JLabel("Status: Off");
		serverLabel.setForeground(Color.WHITE);
		
		JButton btnStartServer = new FlatButton(playIcon,30);
		JButton btnStopServer = new FlatButton(stopIcon,30);
		JButton btnExit = new FlatButton(pauseIcon,30);
		
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
						server.start();
						
					}
				};
				
				hiloStart.start();
				serverLabel.setText("Server: Running");
		}});
		
		
		status_Bar.add(deuzumText);
		status_Bar.add(serverLabel);
		status_Bar.add(btnStartServer);
		status_Bar.add(btnStopServer);
		status_Bar.add(btnExit);
		
		
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
				switchPanel(panel_Configuracion);
			}
		});
		btnFuncionalidades.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchPanel(panel_Funcionalidades);
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
				createUser cU = new createUser(server.getConnection());
			}
		});
		
			JButton btnEditarUsuario = new FlatButton("Editar Usuario");
			panel_Usuario.add(btnEditarUsuario, "cell 0 1,growx");
			
			JButton btnEliminarUsuario = new FlatButton("Eliminar Usuario");
			panel_Usuario.add(btnEliminarUsuario, "cell 0 2,growx");
			btnEliminarUsuario.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					deleteUser cU = new deleteUser(server.getConnection());
				}
			});
			
			JButton btnVerCuentas = new FlatButton("Ver cuentas Usuario");
			panel_Usuario.add(btnVerCuentas, "cell 0 3");
			central_Mutable_Panel.add(panel_Usuario);
			btnVerCuentas.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					UserList uL = new UserList(server.getConnection());
				}
			});
			
			
			panel_Transaccion = new JPanel();
			central_Mutable_Panel.add(panel_Transaccion);
			
			
			//  BOTONES TRANSACCION
					
			JButton btnRealizarTransaccion = new FlatButton("Realizar Transaccion");
			btnRealizarTransaccion.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					createTransaction cT = new createTransaction(server.getConnection());
				}
			});
			panel_Transaccion.add(btnRealizarTransaccion, "cell 0 0,growx,alignx left,aligny top");
			
			JButton btnEliminarTransaccion = new FlatButton("Eliminar Transaccion");
			panel_Transaccion.add(btnEliminarTransaccion, "cell 0 1,growx");
			btnEliminarTransaccion.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					deleteTransaction dT = new deleteTransaction(server.getConnection());
				}
			});
			
			JButton btnVerTransacciones = new FlatButton("Ver Transacciones");
			panel_Transaccion.add(btnVerTransacciones, "cell 0 2,growx");
			btnVerTransacciones.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					TransactionList tL = new TransactionList(server.getConnection());
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
					createProyect cP = new createProyect(server.getConnection());
				}
			});
			
			JButton btnEditarProyecto = new FlatButton("Editar Proyecto");
			panel_Proyectos.add(btnEditarProyecto, "cell 0 1,growx");
			btnEditarProyecto.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					editProyect eP = new editProyect(server.getConnection());
				}
			});
			
			JButton btnEliminarProyecto = new FlatButton("Eliminar Proyecto");
			btnEliminarProyecto.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					deleteProyect dP = new deleteProyect(server.getConnection());
				}
			});
			panel_Proyectos.add(btnEliminarProyecto, "cell 0 2,growx");
			
			JButton btnVerProyectos = new FlatButton("Ver Proyectos");
			panel_Proyectos.add(btnVerProyectos, "cell 0 3,growx");
			btnVerProyectos.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ProyectList pL = new ProyectList(server.getConnection());
				}
			});
			
			
				
			panel_Grupos = new MenuPanel();
			central_Mutable_Panel.add(panel_Grupos);
			
			// BOTONES GRUPO
			
			JButton btnCrearGrupo = new FlatButton("Crear Grupo");
			panel_Grupos.add(btnCrearGrupo, "cell 0 0,growx");
			btnCrearGrupo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					createGroup cG = new createGroup(server.getConnection());
				}
			});
			
			JButton btnEliminarGrupo = new FlatButton("Eliminar Grupo");
			panel_Grupos.add(btnEliminarGrupo, "cell 0 1,growx");
			btnEliminarGrupo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					deleteGroup dG = new deleteGroup(server.getConnection());
				}
			});
			
			JButton btnEditarGrupo = new FlatButton("Editar Grupo");
			btnEditarGrupo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					editGroup eG = new editGroup(server.getConnection());
				}
			});
			panel_Grupos.add(btnEditarGrupo, "cell 0 2,growx,aligny top");
			
			
			JButton btnVerGrupos = new FlatButton("Ver Grupos");
			panel_Grupos.add(btnVerGrupos, "cell 0 3,growx,aligny top");
			btnVerGrupos.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					GroupList gL = new GroupList(server.getConnection());
				}
			});
			
			
			
			
			panel_Funcionalidades = new MenuPanel();
			central_Mutable_Panel.add(panel_Funcionalidades);
			
			//		BOTONES FUNCIONALIDADES
			
			JButton btnRellenarBd = new FlatButton("Rellenar BD");
			panel_Funcionalidades.add(btnRellenarBd, "cell 0 0");
			btnRellenarBd.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub	
				}
			});
			
			panel_Configuracion = new MenuPanel();
			central_Mutable_Panel.add(panel_Configuracion);
			
			
			//		 BOTONES CONFIGURACION
			
			//TODO Hacer que los parametros recibidos en los dialogos cambien el properties del server
			
			
			JButton btnMaxConnection = new FlatButton("Max Connection");
			panel_Configuracion.add(btnMaxConnection, "cell 0 0,growx");
			btnMaxConnection.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						int maxConnection = Integer.parseInt(JOptionPane.showInputDialog("Introducir numero maximo de conexiones"));
					}catch(NumberFormatException nfe) {
						JOptionPane.showMessageDialog(null, "No se ha introducido un numero", "ERROR", 0);
					}					
					if(Server.isRunning) server.restart();						
				}
			});
			
			JButton btnConnectionTimeout = new FlatButton("Connection TimeOut");
			panel_Configuracion.add(btnConnectionTimeout, "cell 0 1");
			btnConnectionTimeout.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						int connectionTimeout = Integer.parseInt(JOptionPane.showInputDialog("introducir el tiempo de 'Connection TimeOut'"));
					}catch(NumberFormatException nfe) {
						JOptionPane.showMessageDialog(null, "No se ha introducido un numero", "ERROR", 0);
					}
					if(Server.isRunning) server.restart();						
				}
			});
			
			JButton btnMaxSocketsize = new FlatButton("Max SocketSize");
			panel_Configuracion.add(btnMaxSocketsize, "cell 0 2,growx");
			btnMaxSocketsize.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						int maxSocketSize = Integer.parseInt(JOptionPane.showInputDialog("Introducir tamaño maximo del socket"));
					}catch(NumberFormatException nfe) {
						JOptionPane.showMessageDialog(null, "No se ha introducido un numero", "ERROR", 0);
					}
					if(Server.isRunning) server.restart();						
				}
			});
			
			JButton btnServerPort = new FlatButton("Server Port");
			panel_Configuracion.add(btnServerPort, "cell 0 3,growx");
			btnServerPort.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						int serverPort = Integer.parseInt(JOptionPane.showInputDialog("Introducir el puerto del servidor"));
					}catch(NumberFormatException nfe) {
						JOptionPane.showMessageDialog(null, "No se ha introducido un numero", "ERROR", 0);
					}
					if(Server.isRunning) server.restart();						
				}
			});
			
			JButton btnServerName = new FlatButton("Server Name");
			panel_Configuracion.add(btnServerName, "cell 0 4,growx");
			btnServerName.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String serverName = "hola";
					while(serverName.equals("hola")) {
						serverName = JOptionPane.showInputDialog("Introducir nombre del servidor");
						if (serverName.equals("hola")) {
							JOptionPane.showMessageDialog(null, "Introducir un nombre valido", "ERROR", 0);
						}
					}
					if(Server.isRunning) server.restart();						
				}
			});
			
				
				JButton btnBotCount = new FlatButton("Bot Count");
				btnBotCount.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						JOptionPane.showInputDialog("Introducir el numero de Bots en el servidor");
					}
				});
				panel_Configuracion.add(btnBotCount, "cell 0 5,growx");
			btnCrearUser.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
			
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
	
	public static void main(String[] args) {
		new ServerHandlerFrame();
		
	
	}
}
