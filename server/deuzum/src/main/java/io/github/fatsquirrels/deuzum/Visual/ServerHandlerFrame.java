package io.github.fatsquirrels.deuzum.visual;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import javax.swing.JPanel;

import io.github.fatsquirrels.deuzum.net.Server;
import io.github.fatsquirrels.deuzum.visual.Style.CustomColors;
import io.github.fatsquirrels.deuzum.visual.components.buttons.IconizedButton;
import io.github.fatsquirrels.deuzum.visual.components.buttons.MenuButton;
import io.github.fatsquirrels.deuzum.visual.panels.ConfigPanel;
import io.github.fatsquirrels.deuzum.visual.panels.FunctionalityPanel;
import io.github.fatsquirrels.deuzum.visual.panels.MenuBar;
import io.github.fatsquirrels.deuzum.visual.panels.MenuPanel;
import io.github.fatsquirrels.deuzum.visual.panels.MenuPanel.MenuType;
import io.github.fatsquirrels.deuzum.visual.panels.global.homePanel;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.ImageIcon;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.JTabbedPane;
import javax.swing.JComponent;




/**
 * Clase de la ventana principal del servidor.
 *
 */
public class ServerHandlerFrame  extends JFrame{
	
	private Server server = null;
	

	public static Properties properties;
	
	private static final long serialVersionUID = 1L;
	private homePanel panel_Home;
	private JPanel panel_Config_G;
	private JPanel central_Mutable_Panel;
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
		MenuBar status_Bar = new MenuBar();
		
		
	
		status_Bar.addLeft(new JLabel(new ImageIcon("data/img/logo.png")));
		
		JButton btnStartServer = new IconizedButton("symbol","play",35,40);
		JButton btnStopServer = new IconizedButton("symbol","stop",35,40);
		JButton btnExit = new IconizedButton("symbol","pause",35,40);

				
		
		btnExit.addActionListener(e -> pauseServer());
		btnStopServer.addActionListener(e -> stopServer());
		btnStartServer.addActionListener(e -> runServer());
		
		status_Bar.addRight(new JComponent[]{btnStartServer,btnStopServer,btnExit});

		getContentPane().add(status_Bar, BorderLayout.NORTH);
		
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
		
		List<JButton> menu_Buttons = new LinkedList<JButton>();
		
		
		//         MUTABLE TABLE
		
		central_Mutable_Panel = new JPanel();
		central_Mutable_Panel.setBackground(Color.WHITE);
		central_Panel.add(central_Mutable_Panel, BorderLayout.CENTER);
		central_Mutable_Panel.setLayout(new BorderLayout(0, 0));
		
		panel_Funcionality = new FunctionalityPanel();
		
		
		panel_Config_G = new ConfigPanel();
		central_Mutable_Panel.add(panel_Config_G);
		
		
		
		
		// TODO implementarlo
		panel_Home = new homePanel("Bienvenido","Texto de muestra");
		JPanel panel_Usuario = new MenuPanel(MenuType.USUARIO);
		JPanel panel_Cuenta = new MenuPanel(MenuType.CUENTA);
		JPanel panel_Proyectos = new MenuPanel(MenuType.PROYECTO);
		JPanel panel_Grupos = new MenuPanel(MenuType.GRUPO);
		JPanel panel_Transaccion = new MenuPanel(MenuType.TRANSACCION);
		
		
		String[] menu_button_Names = {
				"Menu Principal","Usuarios","Cuentas","Transacciones","Grupos","Proyectos","Funcionalidades","Configuracion"};
		JComponent[] menu_Panels = {
				panel_Home,panel_Usuario,panel_Cuenta,panel_Transaccion,panel_Grupos,panel_Proyectos,panel_Funcionality,panel_Config_G};
		
		
		
		
		
		for(int i = 0; i < menu_button_Names.length; i++) {
			final int a = i;
			menu_Buttons.add(new MenuButton(menu_button_Names[i]));
			menu_Buttons.get(i).addActionListener(e->switchPanel(menu_Panels[a]));
			central_Direction_Menu_Panel.add(menu_Buttons.get(a));
			central_Mutable_Panel.add(menu_Panels[i]);
		}
	}

	/**
	 * Cambia el panel por capas central por otro panel introducido como parametro.
	 * @param menu_Panels
	 */
	public void switchPanel(JComponent menu_Panels) {
		central_Mutable_Panel.removeAll();
		central_Mutable_Panel.add(menu_Panels);
		central_Mutable_Panel.validate();
		central_Mutable_Panel.repaint();
	}
	

	/**
	 * Abre y carga el archivo properties.
	 */
	public static void openProperties() {
		ServerHandlerFrame.properties = new Properties();
		try(FileInputStream f = new FileInputStream("./data/server.properties")){
			properties.load(f);
			properties.toString();
			
		}catch(FileNotFoundException e1) {
			System.err.println("El archivo no se encuentra en el lugar indicado.");
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void updateProperty(String property, String value) {
		properties.setProperty(property, value);
	}
	
	public static void storeProperties() {
		try {
			properties.store(new FileOutputStream("./data/server.properties"), null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void runServer() {
		Thread hiloStart = new Thread() {
			@Override
			public void run() {
				// Checks if the server is alredy running
				if(Server.isRunning) 
					return;
				
				server = new Server();
				server.setDBName(ServerHandlerFrame.properties.getProperty("server.dbName"));
				server.setBotCount(Integer.valueOf(ServerHandlerFrame.properties.getProperty("server.botCount")));
				server.setPort(Integer.valueOf(ServerHandlerFrame.properties.getProperty("server.port")));
				server.runServer();
				server.start();
				
			}
		};
		
		hiloStart.start();
		serverLabel.setText("Server: Running");
	}
	
	public void stopServer() {
		server.stop();
		serverLabel.setText("Server: off");
	}
	
	public void pauseServer() {
		if(server != null)  server.stop();
		dispose();	
	}
	
	
}
