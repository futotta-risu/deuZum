package deustoZumServer;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.UIDefaults;
import javax.swing.UIManager;

import deustoZumServer.Visual.Dialogs.Transactions.createTransaction;
import deustoZumServer.Visual.Dialogs.User.createUser;
import deustoZumServer.Visual.Dialogs.User.deleteUser;
import deustoZumServer.Visual.Style.CustomColors;
import deustoZumServer.Visual.Style.FlatButton;
import deustoZumServer.Visual.Style.MenuButton;

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
	
	private JLayeredPane layeredPane;

	/**
	 * Crea un objeto de ServerHandlerFrame, el cual contiene un frame que controla una instancia de Server.
	 */
	public ServerHandlerFrame() {
		configWindow();
	}
	
	/**
	 * Configuración del JFrame de ServerHandlerFrame
	 */
	public void configWindow() {
		
		Icon playIcon = new ImageIcon("data/img/icons/play.png");
		Image img = ((ImageIcon) playIcon).getImage().getScaledInstance(25, 25,  java.awt.Image.SCALE_SMOOTH);
		playIcon = new ImageIcon(img);

		Icon stopIcon = new ImageIcon("data/img/icons/stop.png");
		img = ((ImageIcon) stopIcon).getImage().getScaledInstance(25, 25,  java.awt.Image.SCALE_SMOOTH);
		stopIcon = new ImageIcon(img);
		
		Icon pauseIcon = new ImageIcon("data/img/icons/pause.png");
		img = ((ImageIcon) pauseIcon).getImage().getScaledInstance(25, 25,  java.awt.Image.SCALE_SMOOTH);
		pauseIcon = new ImageIcon(img);
		
		
		
		
		setIconImage(new ImageIcon("data/img/iconoP.png").getImage());
		
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel status_Bar = new JPanel();
		status_Bar.setBackground(CustomColors.mBlueR);
		status_Bar.setPreferredSize(new Dimension(getWidth(),38));
		getContentPane().add(status_Bar, BorderLayout.NORTH);
		
		JLabel emptyLabel = new JLabel("Deuzum");
		status_Bar.add(emptyLabel);
		emptyLabel.setForeground(Color.CYAN);
		emptyLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		JLabel serverLabel = new JLabel("Status: Off");
		status_Bar.add(serverLabel);
		serverLabel.setForeground(Color.WHITE);
		
		JButton btnStartServer = new FlatButton(playIcon);
		btnStartServer.setPreferredSize(new Dimension(30,30));
		status_Bar.add(btnStartServer);
		
		
		JButton btnStopServer = new FlatButton(stopIcon);
		btnStopServer.setPreferredSize(new Dimension(30,30));
		status_Bar.add(btnStopServer);
		
		
		
		JButton btnExit = new FlatButton(pauseIcon);
		btnExit.setPreferredSize(new Dimension(30,30));
		status_Bar.add(btnExit);
		
				
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
				
				
			}
		});
		
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
		central_Direction_Panel.add(central_Direction_Menu_Panel);
		central_Direction_Menu_Panel.setLayout(new GridLayout(0, 1, 0, 0));
		//       BOTONES DIRECTION
		
		JButton SQL_User_Button = new MenuButton("Usuarios");
		central_Direction_Menu_Panel.add(SQL_User_Button);
		
		JButton btnTransaccion = new MenuButton("Transacciones");
		central_Direction_Menu_Panel.add(btnTransaccion);
		
		JButton btnProyectos = new MenuButton("Proyectos");
		central_Direction_Menu_Panel.add(btnProyectos);
		
		
		JButton btnGrupos = new MenuButton("Grupos");
		central_Direction_Menu_Panel.add(btnGrupos);
		
		JButton btnFuncionalidades = new MenuButton("Funcionalidades");
		central_Direction_Menu_Panel.add(btnFuncionalidades);
		
		JButton btnConfiguracionDelServer = new MenuButton("Configuracion del server");
		central_Direction_Menu_Panel.add(btnConfiguracionDelServer);
		btnConfiguracionDelServer.setText("Configuracion");
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
		SQL_User_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchPanel(panel_Usuario);
			}
		});
		
		//         MUTABLE TABLE
		
		JPanel central_Mutable_Panel = new JPanel();
		central_Mutable_Panel.setBackground(Color.WHITE);
		central_Panel.add(central_Mutable_Panel, BorderLayout.CENTER);
		central_Mutable_Panel.setLayout(new MigLayout("", "[16px,grow]", "[30px,grow]"));
		
		layeredPane = new JLayeredPane();
		central_Mutable_Panel.add(layeredPane, "cell 0 0,grow");
		layeredPane.setLayout(null);
		
		panel_Usuario = new JPanel();
		panel_Usuario.setSize(8, 3);
		panel_Usuario.setBackground(SystemColor.menu);
		layeredPane.add(panel_Usuario);
		panel_Usuario.setLayout(new MigLayout("", "[55px]", "[23px][][][]"));
		
				layeredPane.setLayer(panel_Usuario, 9);
				
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
					btnCrearUser.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
					
						}
					});
		
		panel_Transaccion = new JPanel();
		panel_Transaccion.setBackground(CustomColors.mBBlueLight);
		layeredPane.add(panel_Transaccion);
		panel_Transaccion.setLayout(new MigLayout("", "[55px]", "[23px][][]"));
		layeredPane.setLayer(panel_Transaccion, 3);
		
		
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
		
		JButton btnVerTransacciones = new FlatButton("Ver Transacciones");
		panel_Transaccion.add(btnVerTransacciones, "cell 0 2,growx");
		
		
		//        PANELES MUTABLE
		
		
		panel_Proyectos = new JPanel();
		panel_Proyectos.setBackground(CustomColors.mBBlueLight);
		layeredPane.setLayer(panel_Proyectos, 4);
		layeredPane.add(panel_Proyectos);
		panel_Proyectos.setLayout(new MigLayout("", "[55px]", "[23px][][][][][]"));
		
		panel_Grupos = new JPanel();
		panel_Grupos.setBackground(CustomColors.mBBlueLight);
		layeredPane.add(panel_Grupos);
		
		
		
		panel_Funcionalidades = new JPanel();
		panel_Funcionalidades.setBackground(CustomColors.mBBlueLight);
		layeredPane.add(panel_Funcionalidades);
		panel_Funcionalidades.setLayout(new MigLayout("", "[55px]", "[23px][][]"));
		
		panel_Configuracion = new JPanel();
		panel_Configuracion.setBackground(CustomColors.mBBlueLight);
		layeredPane.add(panel_Configuracion);
		panel_Configuracion.setLayout(new MigLayout("", "[55px]", "[23px][][][][][]"));
		layeredPane.setLayer(panel_Proyectos, 4);
		layeredPane.setLayer(panel_Grupos, 5);
		layeredPane.setLayer(panel_Funcionalidades, 8);	
		layeredPane.setLayer(panel_Configuracion, 1);
		
		
		//   BOTONES PROYECTO
		
		JButton btnCrearProyecto = new FlatButton("Crear Proyecto");
		panel_Proyectos.add(btnCrearProyecto, "cell 0 0,growx");
		
		JButton btnEditarProyecto = new FlatButton("Editar Proyecto");
		panel_Proyectos.add(btnEditarProyecto, "cell 0 1,growx");
		
		JButton btnEliminarProyecto = new FlatButton("Eliminar Proyecto");
		btnEliminarProyecto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panel_Proyectos.add(btnEliminarProyecto, "cell 0 2,growx");
		
		JButton btnVerProyectos = new FlatButton("Ver Proyectos");
		panel_Proyectos.add(btnVerProyectos, "cell 0 3,growx");
		
		
		//		BOTONES GRUPO
				
		JButton btnCrearGrupo = new FlatButton("Crear Grupo");
		btnCrearGrupo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panel_Grupos.setLayout(new MigLayout("", "[113px][4px][129px]", "[29px][29px][][]"));
		
		JButton btnCrearGrupo_1 = new FlatButton("Crear Grupo");
		panel_Grupos.add(btnCrearGrupo_1, "cell 0 0,growx");
		
		JButton btnEliminarGrupo = new FlatButton("Eliminar Grupo");
		panel_Grupos.add(btnEliminarGrupo, "cell 0 1,growx");
		
		JButton btnEditarGrupo = new FlatButton("Editar Grupo");
		btnEditarGrupo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panel_Grupos.add(btnEditarGrupo, "cell 0 2,growx,aligny top");
		
		
		JButton btnVerGrupos = new FlatButton("Ver Grupos");
		panel_Grupos.add(btnVerGrupos, "cell 0 3,growx,aligny top");
		
		
		//		BOTONES FUNCIONALIDADES
		
		JButton btnRellenarBd = new FlatButton("Rellenar BD");
		panel_Funcionalidades.add(btnRellenarBd, "cell 0 0");
		
		
		//		 BOTONES CONFIGURACION
		
		
		JButton btnMaxConnection = new FlatButton("Max Connection");
		panel_Configuracion.add(btnMaxConnection, "cell 0 0,growx");
		btnMaxConnection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showInputDialog("Introducir numero maximo de conexiones");
				if(Server.isRunning) server.restart();						
			}
		});
		
		JButton btnConnectionTimeout = new FlatButton("Connection TimeOut");
		panel_Configuracion.add(btnConnectionTimeout, "cell 0 1");
		btnConnectionTimeout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showInputDialog("introducir el tiempo de 'Connection TimeOut'");
				if(Server.isRunning) server.restart();						
			}
		});
		
		JButton btnMaxSocketsize = new FlatButton("Max SocketSize");
		panel_Configuracion.add(btnMaxSocketsize, "cell 0 2,growx");
		btnMaxSocketsize.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showInputDialog("Introducir tamaÃ±o maximo del socket");
				if(Server.isRunning) server.restart();						
			}
		});
		
		JButton btnServerPort = new FlatButton("Server Port");
		panel_Configuracion.add(btnServerPort, "cell 0 3,growx");
		btnServerPort.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showInputDialog("Introducir el puerto del servidor");
				if(Server.isRunning) server.restart();						
			}
		});
		
		JButton btnServerName = new FlatButton("Server Name");
		panel_Configuracion.add(btnServerName, "cell 0 4,growx");
		btnServerName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showInputDialog("Introducir nombre del servidor");
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
		
		
		
		
		this.setSize(700, 500);
		this.setVisible(true);
		this.setTitle("Configuracion del server");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
	}

	/**
	 * Cambia el panel por capas central por otro panel introducido como parametro.
	 * @param panel
	 */
	public void switchPanel(JPanel panel) {
		layeredPane.removeAll();
		layeredPane.add(panel);
		layeredPane.repaint();
	}
	
	public static void main(String[] args) {
		new ServerHandlerFrame();
		
	
	}
}
