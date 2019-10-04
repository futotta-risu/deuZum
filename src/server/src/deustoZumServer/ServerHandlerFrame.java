package deustoZumServer;

import javax.swing.JFrame;

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
		
		JPanel central_Panel = new JPanel();
		central_Panel.setBounds(0, 32, 484, 229);
		getContentPane().add(central_Panel);
		central_Panel.setLayout(new BorderLayout(0, 0));
		
		JPanel central_Direction_Panel = new JPanel();
		central_Panel.add(central_Direction_Panel, BorderLayout.WEST);
		central_Direction_Panel.setLayout(new MigLayout("", "[89px]", "[23px][]"));
		
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
		
		JPanel central_Mutable_Panel = new JPanel();
		central_Panel.add(central_Mutable_Panel, BorderLayout.CENTER);
		central_Mutable_Panel.setLayout(new MigLayout("", "[16px,grow]", "[30px,grow]"));
		
		layeredPane = new JLayeredPane();
		central_Mutable_Panel.add(layeredPane, "cell 0 0,grow");
		layeredPane.setLayout(null);
		
		panel_Usuario = new JPanel();
		panel_Usuario.setBounds(0, 5, 367, 210);
		layeredPane.add(panel_Usuario);
		panel_Usuario.setLayout(new MigLayout("", "[99px]", "[23px][]"));
		
		JButton btnCrearUser = new JButton("Crear Usuario");
		panel_Usuario.add(btnCrearUser, "cell 0 0");
		
		panel_Historial = new JPanel();
		layeredPane.setLayer(panel_Historial, 1);
		panel_Historial.setBounds(0, 5, 367, 210);
		layeredPane.add(panel_Historial);
		panel_Historial.setLayout(new MigLayout("", "[55px]", "[23px]"));
		
		JButton btnJejej = new JButton("Realizar Transaccion");
		btnJejej.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		// TODO cambiar el nombre
		panel_Historial.add(btnJejej, "cell 0 0,alignx left,aligny top");
		btnCrearUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new createUser(server.getConnection());
			}
		});
		
		
		/*
		 *       LISTENERS BOTONES
		 */
		
		
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
