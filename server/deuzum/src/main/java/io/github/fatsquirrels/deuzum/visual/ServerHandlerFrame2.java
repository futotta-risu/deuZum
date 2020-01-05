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
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;

import javax.swing.JPanel;

import io.github.fatsquirrels.deuzum.database.tableName;
import io.github.fatsquirrels.deuzum.log.archivoLog;
import io.github.fatsquirrels.deuzum.net.Server;
import io.github.fatsquirrels.deuzum.net.ServerThread;
import io.github.fatsquirrels.deuzum.visual.components.buttons.FlatButton;
import io.github.fatsquirrels.deuzum.visual.components.buttons.IconizedButton;
import io.github.fatsquirrels.deuzum.visual.components.buttons.MenuButton;
import io.github.fatsquirrels.deuzum.visual.panels.ConfigPanel;
import io.github.fatsquirrels.deuzum.visual.panels.FunctionalityPanel;
import io.github.fatsquirrels.deuzum.visual.panels.HomePanel;
import io.github.fatsquirrels.deuzum.visual.panels.MenuPanel;
import io.github.fatsquirrels.deuzum.visual.panels.PanelListProperties;
import io.github.fatsquirrels.deuzum.visual.panels.PanelProperties;
import io.github.fatsquirrels.deuzum.visual.panels.HomePanel.StatusType;
import io.github.fatsquirrels.deuzum.visual.panels.mainFrame.MenuBar;
import io.github.fatsquirrels.deuzum.visual.style.CustomColors;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.ImageIcon;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.JComponent;

/**
 * Clase de la ventana principal del servidor.
 *
 */
public class ServerHandlerFrame2  extends GenericSMFrame{
	
	
	String[] framePanelNames = {"Home","Usuario","Cuentas","Transaccion","Grupos","Proyectos","Funcionalidades","Configuracion"};
	
	PanelListProperties plp;
	
	
	
	private ServerThread hiloStart;
	
	public static Properties properties;
	
	
	private static final long serialVersionUID = 1L;
	

	
	/**
	 * Crea un objeto de ServerHandlerFrame, el cual contiene un frame que controla una instancia de Server.
	 */
	public ServerHandlerFrame2() {
		plp  = new PanelListProperties();
		openProperties();
		configWindow();
		configLayout();
		
	}
	
	/**
	 * Configuracion del JFrame de ServerHandlerFrame
	 */
	public void configWindow() {
		
		setIconImage(new ImageIcon("data/img/iconoP.png").getImage());
		setTitle("DeuZum Servidor");
		
		setSize(new Dimension(1280, 720));
		setMinimumSize(new Dimension(600, 400));
		setVisible(true);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void configLayout() {
	
		addComponentS(true, new JLabel(new ImageIcon("data/img/logo.png")));
		addComponentS(false,new JComponent[]{
				new IconizedButton("symbol","play",35,40,e -> runServer()),
				new IconizedButton("symbol","stop",35,40,e -> stopServer())
		});
		
		generateBasicPanels();
		
		createMenuButtons();
		System.out.println(plp.getSize());
		setPanelC(plp.getPanel("Home"));
	}
	

	/**
	 * Abre y carga el archivo properties.
	 */
	public static void openProperties() {
		ServerHandlerFrame2.properties = new Properties();
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	public void generateBasicPanels() {
		plp.addPanel("Home",new PanelProperties(new HomePanel("Bienvenido","Texto de muestra"), true));
		plp.addPanel("Funcionalidades",new PanelProperties(new FunctionalityPanel(), true));
		plp.addPanel("Configuracion",new PanelProperties(new ConfigPanel(), true));
	}
	
	public void loadMenuPanels() {
		plp.addPanel("Usuario",new PanelProperties(new MenuPanel(tableName.USUARIO), false));
		plp.addPanel("Cuentas",new PanelProperties(new MenuPanel(tableName.CUENTA), false));
		plp.addPanel("Transaccion",new PanelProperties(new MenuPanel(tableName.TRANSACCION), false));
		plp.addPanel("Grupos",new PanelProperties(new MenuPanel(tableName.GRUPO), false));
		plp.addPanel("Proyectos",new PanelProperties(new MenuPanel(tableName.PROYECTO), false));
	}
	
	public void createMenuButtons() {
		
		deleteButtonsM();
		for(String panelN : plp) 
			addButtonM(panelN, new MenuButton(panelN, e -> setPanelC(plp.getPanel(panelN))));
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
			((HomePanel)plp.getPanel("Home")).changeServerStatus(StatusType.on);
			loadMenuPanels();
			createMenuButtons();
			revalidate();
		}else if(timeOut ==40) {
			hiloStart.interrupt();
		}else JOptionPane.showMessageDialog(null,"Parece que ha habido algun tipo de error al ejecutar el servidor","Error al activar el Server",1);
	
	}
	
	public void stopServer() {
		if(hiloStart == null) return;
		hiloStart.stopServer();
		plp.cleanPLP();
		resetButtons();
		createMenuButtons();
		revalidate();
		((HomePanel)plp.getPanel("Home")).changeServerStatus(StatusType.off);
		setPanelC(plp.getPanel("Home"));
	}
	
	public static void logServerHandlerFrame(String message) {
		archivoLog log = new archivoLog("logServerHandlerFrame");
		log.addLine(Level.INFO, message);
	}
	
}
