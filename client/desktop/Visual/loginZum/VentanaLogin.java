package loginZum;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;

import Paneles.PanelConfiguracion;
import Paneles.UserLogin;
import Paneles.UserRegister;
import Paneles.PanelRegistro;
import Paneles.PanelLogin;

public class VentanaLogin extends JFrame{
	
	private JTabbedPane tabbedPane;
	
	private JButton loginP, registerP;
	
	private JPanel top, center;
	
	public VentanaLogin() {
		
		this.setTitle("Login");
		this.setSize(500,500);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setLayout(new BorderLayout());
		
		JPanel PanelLogin = new UserRegister();
		JPanel PanelRegistro = new UserRegister();
		center = new JPanel();
		center.setLayout(new BorderLayout());
		
		JPanel top = new JPanel();
		top.setLayout(new GridLayout());
		loginP = new JButton("Login");
		loginP.setEnabled(false);
		loginP.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				center.removeAll();
				center.add(PanelLogin, BorderLayout.CENTER);
				registerP.setEnabled(true);
				loginP.setEnabled(false);
				revalidate();
				repaint();
				
				switchPanel(new PanelLogin());
				
				center.add(PanelLogin);
				
			}
		});
		registerP = new JButton("Register");
		registerP.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				center.removeAll();
				center.add(PanelRegistro, BorderLayout.CENTER);
				registerP.setEnabled(false);
				loginP.setEnabled(true);
				revalidate();
				repaint();
				switchPanel(new PanelRegistro());
				
				center.add(PanelRegistro);
				
			}
		});
		
		top.add(loginP);
		top.add(registerP);
		
		center.add(PanelLogin, BorderLayout.CENTER);
		center.add(PanelRegistro, BorderLayout.CENTER);
		add(top, BorderLayout.NORTH);
		add(center, BorderLayout.CENTER);
		}
		
		public void switchPanel ( JComponent panel ){
			 center.removeAll();

		   	 JScrollPane scrolledPane = new JScrollPane();
		   	 scrolledPane.setViewportView(top);
		   	center.add(scrolledPane, BorderLayout.CENTER);
		   	center.validate();
		   	center.repaint();

		}
		


	public static void main(String[] args) {
		new VentanaLogin();
	}

}
