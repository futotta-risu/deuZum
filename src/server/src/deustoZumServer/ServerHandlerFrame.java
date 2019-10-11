package deustoZumServer;

import javax.swing.JFrame;

import deustoZumServer.Algorithms.TextFunctions;
import deustoZumServer.Database.GeneralSQLFunctions;
import deustoZumServer.Dialogs.createUser;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLayeredPane;

public class ServerHandlerFrame  extends JFrame{
	
	private Server server = null;
	
	private static final long serialVersionUID = 1L;
	private JPanel panel_Usuario;
	private JPanel panel_Historial;
	private JLayeredPane layeredPane;

	public ServerHandlerFrame() {
		configWindow();
	}
	
	public void configWindow() {
		getContentPane().setLayout(null);
		
		/*
		 *    BARRA SUPERIOR
		 */
		
		JPanel sup_Bar = new JPanel();
		sup_Bar.setBounds(0, 0, 484, 33);
		getContentPane().add(sup_Bar);
		
		
		JButton btnStartServer = new JButton("Start Server");
		sup_Bar.add(btnStartServer);
		btnStartServer.setBounds(36, 48, 103, 23);
		
		btnStartServer.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				Thread hiloStart = new Thread() {
					
					@Override
					public void run() {
						ServerCommands.createMethodArray();
						server = new Server();
						server.start(1352);
						
					}
				};
				
				hiloStart.start();
				
				
				
			}
		});
		
		JButton btnStopServer = new JButton("Stop Server");
		sup_Bar.add(btnStopServer);
		btnStopServer.setBounds(36, 106, 103, 23);
		
		btnStopServer.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				server.stop();
				
			}
		});
		
		JButton btnExit = new JButton("Exit");
		sup_Bar.add(btnExit);
		btnExit.setBounds(36, 167, 103, 23);		
		btnExit.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				server.stop();
				dispose();	
			}
		});
		
		/*
		 *      PANEL CENTRAL
		 */
		
		
		JPanel central_Panel = new JPanel();
		central_Panel.setBounds(0, 32, 484, 229);
		getContentPane().add(central_Panel);
		central_Panel.setLayout(new BorderLayout(0, 0));
		
		//        DIRECTION
		
		JPanel central_Direction_Panel = new JPanel();
		central_Panel.add(central_Direction_Panel, BorderLayout.WEST);
		central_Direction_Panel.setLayout(new MigLayout("", "[89px]", "[23px][][][][][][]"));
		
		//       BOTONES DIRECTION
		
		JButton SQL_User_Button = new JButton("Usuarios");
		SQL_User_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchPanel(panel_Usuario);
			}
		});
		central_Direction_Panel.add(SQL_User_Button, "cell 0 0,grow");
		
		JButton btnTransaccion = new JButton("Transacciones");
		btnTransaccion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchPanel(panel_Historial);
			}
		});
		central_Direction_Panel.add(btnTransaccion, "cell 0 1,growx");
		
		JButton btnProyectos = new JButton("Proyectos");
		btnProyectos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		central_Direction_Panel.add(btnProyectos, "cell 0 2,growx");
		
		
		JButton btnGrupos = new JButton("Grupos");
		central_Direction_Panel.add(btnGrupos, "cell 0 3,growx");
		
		JButton btnFuncionalidades = new JButton("Funcionalidades");
		central_Direction_Panel.add(btnFuncionalidades, "cell 0 4,growx");
		
		JButton btnConfiguracionDelServer = new JButton("Configuracion del server");
		central_Direction_Panel.add(btnConfiguracionDelServer, "cell 0 6");
		
		
		//         MUTABLE TABLE
		
		
		JPanel central_Mutable_Panel = new JPanel();
		central_Panel.add(central_Mutable_Panel, BorderLayout.CENTER);
		central_Mutable_Panel.setLayout(new MigLayout("", "[16px,grow]", "[30px,grow]"));
		
		layeredPane = new JLayeredPane();
		central_Mutable_Panel.add(layeredPane, "cell 0 0,grow");
		layeredPane.setLayout(null);
		
		
		//        PANELES MUTABLE
		
		
		JPanel panel_Proyectos = new JPanel();
		layeredPane.setLayer(panel_Proyectos, 1);
		panel_Proyectos.setBounds(0, 5, 367, 210);
		layeredPane.add(panel_Proyectos);
		
		JPanel panel_Grupos = new JPanel();
		panel_Grupos.setBounds(0, 5, 367, 210);
		layeredPane.add(panel_Grupos);
		
		JPanel panel_Funcionalidades = new JPanel();
		panel_Funcionalidades.setBounds(0, 5, 367, 210);
		layeredPane.add(panel_Funcionalidades);
		
		JPanel panel_Configuracion = new JPanel();
		panel_Configuracion.setBounds(0, 5, 367, 210);
		layeredPane.add(panel_Configuracion);
		
		panel_Usuario = new JPanel();
		panel_Usuario.setBounds(0, 5, 367, 210);
		layeredPane.add(panel_Usuario);
		panel_Usuario.setLayout(new MigLayout("", "[99px]", "[23px][][][]"));
		
		panel_Historial = new JPanel();
		
	
		panel_Historial.setBounds(0, 5, 367, 210);
		layeredPane.add(panel_Historial);
		panel_Historial.setLayout(new MigLayout("", "[55px]", "[23px][][]"));

		layeredPane.setLayer(panel_Usuario, 2);
		layeredPane.setLayer(panel_Historial, 1);
		layeredPane.setLayer(panel_Proyectos, 5);
		panel_Proyectos.setLayout(new MigLayout("", "[]", "[]"));
		
		JButton btnCrearProyecto = new JButton("Crear Proyecto");
		panel_Proyectos.add(btnCrearProyecto, "cell 0 0,growx");
		layeredPane.setLayer(panel_Grupos, 3);
		layeredPane.setLayer(panel_Funcionalidades, 4);
		layeredPane.setLayer(panel_Configuracion, 0);
		
		

		
		//  BOTONES USUARIO
		
		JButton btnCrearUser = new JButton("Crear Usuario");
		panel_Usuario.add(btnCrearUser, "cell 0 0");
	
		JButton btnEditarUsuario = new JButton("Editar Usuario");
		panel_Usuario.add(btnEditarUsuario, "cell 0 1");
		
		JButton btnEliminarUsuario = new JButton("Eliminar Usuario");
		panel_Usuario.add(btnEliminarUsuario, "cell 0 2");
		
		JButton btnVerCuentas = new JButton("Ver cuentas Usuario");
		panel_Usuario.add(btnVerCuentas, "cell 0 3");
		
		
		//  BOTONES TRANSACCION
		
		
		JButton btnRealizarTransaccion = new JButton("Realizar Transaccion");
		btnRealizarTransaccion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panel_Historial.add(btnRealizarTransaccion, "cell 0 0,alignx left,aligny top");
		
		JButton btnVerTransacciones = new JButton("Ver Transacciones");
		panel_Historial.add(btnVerTransacciones, "cell 0 1");
		
		JButton btnFiltrarTransacciones = new JButton("Filtrar Transacciones");
		panel_Historial.add(btnFiltrarTransacciones, "cell 0 2");
		btnCrearUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new createUser(server.getConnection());
			}
		});
		
		
		//   BOTONES PROYECTO
		
		
		
		this.setSize(500, 300);
		this.setVisible(true);
		this.setTitle("Configuracion del server");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
	}

	
	public void switchPanel(JPanel panel) {
		layeredPane.removeAll();
		layeredPane.add(panel);
		layeredPane.repaint();
	}
	
	public static void main(String[] args) {
		new ServerHandlerFrame();
		
	
	}
}
