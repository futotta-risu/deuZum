package io.github.fatsquirrels.deuzum.visual;

import javax.swing.JLabel;
import javax.swing.JOptionPane;


import io.github.fatsquirrels.deuzum.database.tableName;
import io.github.fatsquirrels.deuzum.net.Server;
import io.github.fatsquirrels.deuzum.net.ServerThread;
import io.github.fatsquirrels.deuzum.res.ServerProperties;
import io.github.fatsquirrels.deuzum.visual.components.buttons.IconizedButton;
import io.github.fatsquirrels.deuzum.visual.components.buttons.MenuButton;
import io.github.fatsquirrels.deuzum.visual.panels.ConfigPanel;
import io.github.fatsquirrels.deuzum.visual.panels.FunctionalityPanel;
import io.github.fatsquirrels.deuzum.visual.panels.HomePanel;
import io.github.fatsquirrels.deuzum.visual.panels.MenuPanel;
import io.github.fatsquirrels.deuzum.visual.panels.PanelListProperties;
import io.github.fatsquirrels.deuzum.visual.panels.PanelProperties;
import io.github.fatsquirrels.deuzum.visual.panels.HomePanel.StatusType;

import javax.swing.ImageIcon;

import java.awt.Dimension;
import javax.swing.JComponent;

/**
 * Clase de la ventana principal del servidor.
 *
 */
public class ServerHandler  extends GenericSMFrame{
	
	private static final long serialVersionUID = -2032372544471677287L;

	PanelListProperties plp;
	
	private ServerThread hiloStart;
	
	
	public ServerHandler() {
		plp  = new PanelListProperties();
		ServerProperties.openProperties();
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
		
		plp.addPanel("Home",new PanelProperties(new HomePanel("Bienvenido","Texto de muestra"), true));
		plp.addPanel("Funcionalidades",new PanelProperties(new FunctionalityPanel(), true));
		plp.addPanel("Configuracion",new PanelProperties(new ConfigPanel(), true));
		
		createMenuButtons();
		setPanelC(plp.getPanel("Home"));
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
	
}
