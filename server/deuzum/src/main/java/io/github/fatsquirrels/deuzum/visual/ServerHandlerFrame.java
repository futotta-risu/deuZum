package io.github.fatsquirrels.deuzum.visual;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import javax.swing.JPanel;

import io.github.fatsquirrels.deuzum.database.tableName;
import io.github.fatsquirrels.deuzum.net.Server;
import io.github.fatsquirrels.deuzum.net.ServerThread;
import io.github.fatsquirrels.deuzum.utils.math.APair;
import io.github.fatsquirrels.deuzum.visual.Style.CustomColors;
import io.github.fatsquirrels.deuzum.visual.components.buttons.IconizedButton;
import io.github.fatsquirrels.deuzum.visual.components.buttons.MenuButton;
import io.github.fatsquirrels.deuzum.visual.panels.ConfigPanel;
import io.github.fatsquirrels.deuzum.visual.panels.FunctionalityPanel;
import io.github.fatsquirrels.deuzum.visual.panels.MenuBar;
import io.github.fatsquirrels.deuzum.visual.panels.MenuPanel;
import io.github.fatsquirrels.deuzum.visual.panels.HomePanel;
import io.github.fatsquirrels.deuzum.visual.panels.HomePanel.StatusType;

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
	
	// Constantes
	
	final List<APair<String,Boolean>> menu_button_Names =  Collections.unmodifiableList(
		    Arrays.asList(new APair<String,Boolean>("Menu Principal",true),
					new APair<String,Boolean>("Usuarios",false),
					new APair<String,Boolean>("Cuentas",false),
					new APair<String,Boolean>("Transacciones",false),
					new APair<String,Boolean>("Grupos",false),
					new APair<String,Boolean>("Proyectos",false),
					new APair<String,Boolean>("Funcionalidades",true),
					new APair<String,Boolean>("Configuracion",true)));
	
	private ServerThread hiloStart;
	
	public static Properties properties;
	
	private static final long serialVersionUID = 1L;
	private HomePanel panel_Home;
	private JPanel panel_Config_G;
	private JPanel central_Mutable_Panel;
	private JPanel panel_Usuario;
	private JPanel panel_Cuenta;
	private JPanel panel_Proyectos;
	private JPanel panel_Grupos, panel_Transaccion, central_Direction_Menu_Panel, central_Direction_Panel;
	private JTabbedPane panel_Funcionality;
	private List<JButton> menu_Buttons;
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
		setLayout(new BorderLayout(0, 0));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void configLayout() {
		
		  
		
		// Top Bar 
		MenuBar status_Bar = new MenuBar();
	
		status_Bar.addLeft(new JLabel(new ImageIcon("data/img/logo.png")));
		status_Bar.addRight(new JComponent[]{
				new IconizedButton("symbol","play",35,40,e -> runServer()),
				new IconizedButton("symbol","stop",35,40,e -> stopServer())
			});

		add(status_Bar, BorderLayout.NORTH);
		
		
		/*
		 *      PANEL CENTRAL
		 */
		
		JPanel central_Panel = new JPanel();
		add(central_Panel);
		central_Panel.setLayout(new BorderLayout(0, 0));
		
		//        DIRECTION
		
		central_Direction_Panel = new JPanel();
		central_Panel.add(central_Direction_Panel, BorderLayout.WEST);
		central_Direction_Panel.setBackground(CustomColors.mBBlue);
		central_Direction_Panel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		
		central_Direction_Menu_Panel = new JPanel();
		central_Direction_Menu_Panel.setBackground(CustomColors.mBBlue);
		central_Direction_Menu_Panel.setPreferredSize(new Dimension(180,280));
		central_Direction_Menu_Panel.setLayout(new GridLayout(0, 1, 0, 0));
		central_Direction_Panel.add(central_Direction_Menu_Panel);
		//       BOTONES DIRECTION
		
		
		
		
		//         MUTABLE TABLE
		
		central_Mutable_Panel = new JPanel();
		central_Mutable_Panel.setBackground(Color.WHITE);
		central_Panel.add(central_Mutable_Panel, BorderLayout.CENTER);
		central_Mutable_Panel.setLayout(new BorderLayout(0, 0));
		
		panel_Funcionality = new FunctionalityPanel();
		
		
		panel_Config_G = new ConfigPanel();
		
		
		
		// TODO implementarlo
		panel_Home = new HomePanel("Bienvenido","Texto de muestra");
		
		
		
		panel_Usuario = null;
		panel_Cuenta = null;
		panel_Proyectos = null;
		panel_Grupos = null;
		panel_Transaccion = null;
		
		
		
		
		createMenuPanels();
		
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
	
	/*-------------------
	 *  MENU BUTTONS DISABLE
	 --------------------*/
	
	public void changeButtonActivation(boolean var) {
		for(int i = 0; i <  menu_Buttons.size(); i++) 
			menu_Buttons.get(i).setEnabled(var || menu_button_Names.get(i).getValue());
		
	}
	
	public void loadMenuPanels() {
		panel_Usuario = new MenuPanel(tableName.USUARIO);
		panel_Cuenta = new MenuPanel(tableName.CUENTA);
		panel_Proyectos = new MenuPanel(tableName.PROYECTO);
		panel_Grupos = new MenuPanel(tableName.GRUPO);
		panel_Transaccion = new MenuPanel(tableName.TRANSACCION);
	}
	
	public void createMenuPanels() {
		JComponent[] menu_Panels = {
				panel_Home,panel_Usuario,panel_Cuenta,panel_Transaccion,panel_Grupos,panel_Proyectos,panel_Funcionality,panel_Config_G};
		
		menu_Buttons = new ArrayList<JButton>();
		central_Mutable_Panel.removeAll();
		central_Direction_Menu_Panel.removeAll();
		for(int i = 0; i < menu_button_Names.size(); i++) {
			final int a = i;
			menu_Buttons.add(new MenuButton(menu_button_Names.get(i).getIndex()));
			menu_Buttons.get(i).addActionListener(e->switchPanel(menu_Panels[a]));
			// If i is not home or config
			menu_Buttons.get(i).setEnabled(menu_button_Names.get(i).getValue());
			central_Direction_Menu_Panel.add(menu_Buttons.get(a));
			if(menu_Panels[i]!=null)
				central_Mutable_Panel.add(menu_Panels[i]);
		}
		
		
		revalidate();
	}
	
	/* -----------------
	 * 	SERVER RUNNING FUNCTIONS
	  ---------------- */
	public void runServer() {
		 
		int timeOut = 0;
		
		// Crear el hilo
		hiloStart = new ServerThread();
		hiloStart.start();
		
		
		while(!Server.serverLoadFailed && !Server.isRunning) {
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
			timeOut++;
			if(timeOut>=40) break;
		}
		
		if(Server.isRunning) {
			panel_Home.changeServerStatus(StatusType.on);
			loadMenuPanels();
			createMenuPanels();
			
			changeButtonActivation(true);
			revalidate();
		}else if(timeOut ==40) {
			hiloStart.interrupt();
		}else JOptionPane.showMessageDialog(null,"Parece que ha habido algun tipo de error al ejecutar el servidor","Error al activar el Server",1);

			
		
		
		
		

		
	}
	
	public void stopServer() {
		hiloStart.stopServer();
		// TODO if in panel which should be disabled, move to home
		changeButtonActivation(false);
		panel_Home.changeServerStatus(StatusType.off);
	}
	
	
	
}
