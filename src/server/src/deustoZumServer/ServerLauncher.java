package deustoZumServer;

import java.sql.*;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.JButton;
import java.awt.event.ActionListener;

import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class ServerLauncher  extends JFrame{
	
	private Server server = null;
	
	private static final long serialVersionUID = 1L;

	public ServerLauncher() {
		
		
		getContentPane().setLayout(null);
		
		JButton btnStartServer = new JButton("Start Server");
		btnStartServer.setBounds(36, 48, 103, 23);
		getContentPane().add(btnStartServer);
		
		JButton btnStopServer = new JButton("Stop Server");
		btnStopServer.setBounds(36, 106, 103, 23);
		getContentPane().add(btnStopServer);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setBounds(36, 167, 103, 23);
		getContentPane().add(btnExit);
		
		JLabel lblEstado = new JLabel("Estado:");
		lblEstado.setBounds(205, 23, 46, 14);
		getContentPane().add(lblEstado);
		
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBounds(205, 48, 185, 173);
		getContentPane().add(textArea);
		
		
		/*
		 *       LISTENERS BOTONES
		 */
		
		btnExit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				
			}
		});
		 
		btnStartServer.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				Thread hiloStart = new Thread() {
					
					@Override
					public void run() {
						ServerCommands.createMethodArray();
						server = new Server();
						server.start(1352);
						textArea.append("Server Started on port 1254");
						
					}
				};
				
				hiloStart.start();
				
				
				
			}
		});
		
		btnStopServer.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				server.stop();
				
			}
		});
		
		
		
		this.setVisible(true);
		this.setTitle("Configuracion del server");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.pack();
		
		
	}

	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				new ServerLauncher();
				
			}
		});
		
		
	
	}
	
}
