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
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import javax.swing.JPanel;

import io.github.fatsquirrels.deuzum.database.tableName;
import io.github.fatsquirrels.deuzum.log.archivoLog;
import io.github.fatsquirrels.deuzum.net.Server;
import io.github.fatsquirrels.deuzum.net.ServerThread;
import io.github.fatsquirrels.deuzum.visual.components.buttons.IconizedButton;
import io.github.fatsquirrels.deuzum.visual.components.buttons.MenuButton;
import io.github.fatsquirrels.deuzum.visual.panels.ConfigPanel;
import io.github.fatsquirrels.deuzum.visual.panels.FunctionalityPanel;
import io.github.fatsquirrels.deuzum.visual.panels.HomePanel;
import io.github.fatsquirrels.deuzum.visual.panels.MenuPanel;
import io.github.fatsquirrels.deuzum.visual.panels.HomePanel.StatusType;
import io.github.fatsquirrels.deuzum.visual.panels.mainFrame.MenuBar;
import io.github.fatsquirrels.deuzum.visual.panels.util.PanelListProperties;
import io.github.fatsquirrels.deuzum.visual.panels.util.PanelProperties;
import io.github.fatsquirrels.deuzum.visual.style.CustomColors;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.ImageIcon;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.JComponent;

@Deprecated
/**
 * Clase de la ventana principal del servidor. Se sustityo por ServerHandler
 *
 */
public class ServerHandlerFrame  extends JFrame{
	
	
	String[] framePanelNames = {"Home","Usuario","Cuentas","Transaccion","Grupos","Proyectos","Funcionalidades","Configuracion"};
	PanelListProperties plp;
	
	
	private ServerThread hiloStart;
	
	public static Properties properties;
	
	
	private static final long serialVersionUID = 1L;
	
	// JFrame Components
	private JPanel central_Mutable_Panel;
	private JPanel central_Direction_Menu_Panel, central_Direction_Panel;
	private List<JButton> menu_Buttons;
	
	/**
	 * Crea un objeto de ServerHandlerFrame, el cual contiene un frame que controla una instancia de Server.
	 */
	public ServerHandlerFrame() {
		plp  = new PanelListProperties();
		openProperties();
		configLayout();
		configWindow();
		pack();
		validate();
	}
	
	/**
	 * Configuracion del JFrame de ServerHandlerFrame
	 */
	public void configWindow() {
		
		// Icono de la Aplicacion
		setIconImage(new ImageIcon("data/img/iconoP.png").getImage());
		
		setSize(new Dimension(1280, 720));
		setMinimumSize(new Dimension(600, 400));
		setVisible(true);
		setTitle("DeuZum Servidor");
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void configLayout() {
		
		setLayout(new BorderLayout(0, 0));
		
		// Top Bar 
		MenuBar status_Bar = new MenuBar();
	
		status_Bar.addLeft(new JLabel(new ImageIcon("data/img/logo.png")));
		status_Bar.addRight(new JComponent[]{
				new IconizedButton("symbol","play",35,40,e -> runServer()),
				new IconizedButton("symbol","stop",35,40,e -> stopServer())
			});

		add(status_Bar, BorderLayout.NORTH);
		
		
		//    PANEL CENTRAL
		 
		JPanel central_Panel = new JPanel();
		add(central_Panel);
		central_Panel.setLayout(new BorderLayout(0, 0));
		
		//        DIRECTION
		
		central_Direction_Panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
		central_Panel.add(central_Direction_Panel, BorderLayout.WEST);
		central_Direction_Panel.setBackground(CustomColors.Blue);
		
		central_Direction_Menu_Panel = new JPanel(new GridLayout(0, 1, 0, 0));
		central_Direction_Menu_Panel.setBackground(CustomColors.Blue);
		central_Direction_Menu_Panel.setPreferredSize(new Dimension(180,280));
		central_Direction_Panel.add(central_Direction_Menu_Panel);		
		
		//         MUTABLE TABLE
		
		central_Mutable_Panel = new JPanel(new BorderLayout(0, 0));
		central_Mutable_Panel.setBackground(Color.WHITE);
		central_Panel.add(central_Mutable_Panel, BorderLayout.CENTER);
		

		plp.addPanel("Funcionalidades",new PanelProperties(new FunctionalityPanel(), true));
		plp.addPanel("Configuracion",new PanelProperties(new ConfigPanel(), true));
		plp.addPanel("Home",new PanelProperties(new HomePanel("Bienvenido","Texto de muestra"), true));
		
		
		createMenuPanels();
		switchPanel(plp.getPanel("Home"));
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
			
		logServerHandlerFrame("Prueba");	
			
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
			e.printStackTrace();
		}
	}
	
	/*-------------------
	 *  MENU BUTTONS DISABLE
	 --------------------*/
	
	public void setButtonsEnable(boolean var) {
		for(int i = 0; i <  menu_Buttons.size(); i++) 
			menu_Buttons.get(i).setEnabled(var || plp.getEnabled(framePanelNames[i]));
		
	}
	
	public void loadMenuPanels() {
		plp.addPanel("Usuario",new PanelProperties(new MenuPanel(tableName.USUARIO), false));
		plp.addPanel("Cuentas",new PanelProperties(new MenuPanel(tableName.CUENTA), false));
		plp.addPanel("Transaccion",new PanelProperties(new MenuPanel(tableName.TRANSACCION), false));
		plp.addPanel("Grupos",new PanelProperties(new MenuPanel(tableName.GRUPO), false));
		plp.addPanel("Proyectos",new PanelProperties(new MenuPanel(tableName.PROYECTO), false));
	}
	
	public void createMenuPanels() {
		
		menu_Buttons = new ArrayList<JButton>();
		central_Mutable_Panel.removeAll();
		central_Direction_Menu_Panel.removeAll();
		
		for(int i = 0; i < framePanelNames.length; i++) {
			final int a = i;
			if(plp.containsKey(framePanelNames[i])) {
				menu_Buttons.add(new MenuButton(framePanelNames[i]));
				menu_Buttons.get(i).addActionListener(e->switchPanel(plp.getPanel(framePanelNames[a])));
				
				// If i is not home or config
				menu_Buttons.get(i).setEnabled(plp.getEnabled(framePanelNames[i]));
				central_Direction_Menu_Panel.add(menu_Buttons.get(a));
				
				// If the panel is loaded, add panel
				if(plp.getPanel(framePanelNames[i])!=null)
					central_Mutable_Panel.add(plp.getPanel(framePanelNames[i]));
			}else {
				menu_Buttons.add(new MenuButton(framePanelNames[i]));
				menu_Buttons.get(i).setEnabled(false);
				central_Direction_Menu_Panel.add(menu_Buttons.get(i));
			}
			
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
				e.printStackTrace();
			}		
			timeOut++;
			if(timeOut>=40) break;
		}
		
		if(Server.isRunning) {
			((HomePanel)plp.getPanel("Home")).changeServerStatus(StatusType.on);
			loadMenuPanels();
			createMenuPanels();
			
			setButtonsEnable(true);
			switchPanel(plp.getPanel("Home"));
			revalidate();
		}else if(timeOut ==40) {
			hiloStart.interrupt();
		}else JOptionPane.showMessageDialog(null,"Parece que ha habido algun tipo de error al ejecutar el servidor","Error al activar el Server",1);
	
	}
	
	public void stopServer() {
		if(hiloStart == null) return;
		hiloStart.stopServer();
		setButtonsEnable(false);
		((HomePanel)plp.getPanel("Home")).changeServerStatus(StatusType.off);
	}
	
	public static void logServerHandlerFrame(String message) {
		archivoLog log = new archivoLog("logServerHandlerFrame");
		log.addLine(Level.INFO, message);
	}
	
}
