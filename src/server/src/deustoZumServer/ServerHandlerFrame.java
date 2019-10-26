package deustoZumServer;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;

import deustoZumServer.Visual.Dialogs.User.createUser;
import deustoZumServer.Visual.Dialogs.User.deleteUser;
import deustoZumServer.Visual.Style.FlatButton;

import java.awt.BorderLayout;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;

import javax.swing.ImageIcon;

import java.awt.Dimension;
import java.awt.Insets;
import java.awt.GridBagLayout;

public class ServerHandlerFrame  extends JFrame{
	
	private Server server = null;
	
	private static final long serialVersionUID = 1L;
	private JPanel panel_Usuario;
	private JPanel panel_Historial;
	private JPanel panel_Proyectos;
	private JPanel panel_Grupos;
	private JPanel panel_Funcionalidades;
	private JPanel panel_Configuracion;
	
	private JLayeredPane layeredPane;

	/**
	 * Crea un objeto de ServerHandlerFrame, el cual contiene un frame que controla una instancia de Server.
	 */
	public ServerHandlerFrame() {
		setResizable(false);
		configWindow();
	}
	
	/**
	 * Configuraci�n del JFrame de ServerHandlerFrame
	 */
	public void configWindow() {
		
		setIconImage(new ImageIcon("data/img/iconoP.png").getImage());
		
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel sup_Bar = new JPanel();
		sup_Bar.setBackground(new Color(50, 50, 50));
		sup_Bar.setPreferredSize(new Dimension(660, 70));
		getContentPane().add(sup_Bar, BorderLayout.SOUTH);
		
		sup_Bar.setLayout(new GridBagLayout());
		
		JButton btnStartServer = new FlatButton("Start Server");
		btnStartServer.setFont(new Font("Verdana", Font.PLAIN, 30));
		
		
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.weightx = 1;
		constraints.weighty = 1;
		constraints.gridx = 0;
		constraints.gridwidth  = 3;
		constraints.gridy = 0;
		constraints.gridheight  = 2;
		constraints.fill = GridBagConstraints.BOTH;
		sup_Bar.add(btnStartServer,constraints.clone());
		
		
		JButton btnStopServer = new FlatButton("Stop Server");
		constraints.gridx = 3;
		constraints.gridy = 0;
		constraints.gridwidth  = 1;
		constraints.gridheight  = 1;
		
		sup_Bar.add(btnStopServer,constraints.clone());
		
		
		
		JButton btnExit = new FlatButton("Exit");
		constraints.gridx = 3;
		constraints.gridy = 1;
		sup_Bar.add(btnExit,constraints.clone());
		
		JLabel emptyLabel = new JLabel("Deuzum");
		emptyLabel.setForeground(Color.CYAN);
		emptyLabel.setFont(new Font("DecoType Naskh", Font.BOLD, 20));
		constraints.gridx = 5;
		constraints.gridheight  = 1;
		constraints.gridwidth  = 2;
		constraints.gridy = 0;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.insets = new Insets(0,70,0,0);  //top padding
		sup_Bar.add(emptyLabel,constraints.clone());
		JLabel serverLabel = new JLabel("Status: Off");
		serverLabel.setForeground(Color.WHITE);
		constraints.gridx = 5;
		constraints.gridheight  = 1;
		constraints.gridwidth  = 2;
		constraints.gridy = 1;
		constraints.insets = new Insets(0,15,0,0);  //top padding
		constraints.fill = GridBagConstraints.HORIZONTAL;
		sup_Bar.add(serverLabel,constraints.clone());
		
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
		
		
		
		btnStopServer.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				server.stop();
				serverLabel.setText("Server: off");
				
			}
		});
		
				
		btnExit.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				if(server != null)  server.stop();
				dispose();	
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
		central_Direction_Panel.setLayout(new MigLayout("", "[89px]", "[23px][][][][][][]"));
		central_Direction_Panel.setBackground(new Color(100,100,100));
		//       BOTONES DIRECTION
		
		JButton SQL_User_Button = new FlatButton("Usuarios");
		SQL_User_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchPanel(panel_Usuario);
			}
		});
		central_Direction_Panel.add(SQL_User_Button, "cell 0 0,grow");
		
		JButton btnTransaccion = new FlatButton("Transacciones");
		btnTransaccion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchPanel(panel_Historial);
			}
		});
		central_Direction_Panel.add(btnTransaccion, "cell 0 1,growx");
		
		JButton btnProyectos = new FlatButton("Proyectos");
		btnProyectos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchPanel(panel_Proyectos);
			}
		});
		central_Direction_Panel.add(btnProyectos, "cell 0 2,growx");
		
		
		JButton btnGrupos = new FlatButton("Grupos");
		central_Direction_Panel.add(btnGrupos, "cell 0 3,growx");
		btnGrupos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchPanel(panel_Grupos);
			}
		});
		
		JButton btnFuncionalidades = new FlatButton("Funcionalidades");
		central_Direction_Panel.add(btnFuncionalidades, "cell 0 4,growx");
		btnFuncionalidades.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchPanel(panel_Funcionalidades);
			}
		});
		
		JButton btnConfiguracionDelServer = new FlatButton("Configuracion del server");
		central_Direction_Panel.add(btnConfiguracionDelServer, "cell 0 5");
		btnConfiguracionDelServer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchPanel(panel_Configuracion);
			}
		});
		
		
		//         MUTABLE TABLE
		
		
		JPanel central_Mutable_Panel = new JPanel();
		central_Panel.add(central_Mutable_Panel, BorderLayout.CENTER);
		central_Mutable_Panel.setLayout(new MigLayout("", "[16px,grow]", "[30px,grow]"));
		
		layeredPane = new JLayeredPane();
		central_Mutable_Panel.add(layeredPane, "cell 0 0,grow");
		layeredPane.setLayout(null);
		
		
		//        PANELES MUTABLE
		
		
		panel_Proyectos = new JPanel();
		layeredPane.setLayer(panel_Proyectos, 4);
		panel_Proyectos.setBounds(0, 5, 367, 210);
		layeredPane.add(panel_Proyectos);
		panel_Proyectos.setLayout(new MigLayout("", "[55px]", "[23px][][][][][]"));
		
		panel_Grupos = new JPanel();
		panel_Grupos.setBounds(0, 5, 367, 210);
		layeredPane.add(panel_Grupos);
		
		
		
		panel_Funcionalidades = new JPanel();
		panel_Funcionalidades.setBounds(0, 5, 367, 210);
		layeredPane.add(panel_Funcionalidades);
		panel_Funcionalidades.setLayout(new MigLayout("", "[55px]", "[23px][][]"));
		
		panel_Configuracion = new JPanel();
		panel_Configuracion.setBounds(0, 0, 384, 231);
		layeredPane.add(panel_Configuracion);
		panel_Configuracion.setLayout(new MigLayout("", "[55px]", "[23px][][][][][]"));
		
		panel_Usuario = new JPanel();
		panel_Usuario.setBounds(0, 5, 367, 210);
		layeredPane.add(panel_Usuario);
		panel_Usuario.setLayout(new MigLayout("", "[55px]", "[23px][][][]"));
		
		panel_Historial = new JPanel();
		panel_Historial.setBounds(0, 5, 367, 210);
		layeredPane.add(panel_Historial);
		panel_Historial.setLayout(new MigLayout("", "[55px]", "[23px][][]"));

		layeredPane.setLayer(panel_Usuario, 2);
		layeredPane.setLayer(panel_Historial, 3);
		layeredPane.setLayer(panel_Proyectos, 4);
		layeredPane.setLayer(panel_Grupos, 5);
		layeredPane.setLayer(panel_Funcionalidades, 1);	
		layeredPane.setLayer(panel_Configuracion, 0);
		
		
		
		
		


		
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
		
		
		//  BOTONES TRANSACCION
				
		JButton btnRealizarTransaccion = new FlatButton("Realizar Transaccion");
		btnRealizarTransaccion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panel_Historial.add(btnRealizarTransaccion, "cell 0 0,growx,alignx left,aligny top");
		
		JButton btnEliminarTransaccion = new FlatButton("Eliminar Transaccion");
		panel_Historial.add(btnEliminarTransaccion, "cell 0 1,growx");
		
		JButton btnVerTransacciones = new FlatButton("Ver Transacciones");
		panel_Historial.add(btnVerTransacciones, "cell 0 2,growx");
		btnCrearUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		
			}
		});
		
		
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
				JOptionPane.showInputDialog("Introducir tamaño maximo del socket");
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
