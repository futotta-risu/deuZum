package loginZum;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;

import Paneles.UserLogin;
import Paneles.UserRegister;


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
		JPanel login = new UserLogin();
		JPanel register = new UserRegister();
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
				center.add(login, BorderLayout.CENTER);
				registerP.setEnabled(true);
				loginP.setEnabled(false);
				revalidate();
				repaint();
			}
		});
		registerP = new JButton("Register");
		registerP.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				center.removeAll();
				center.add(register, BorderLayout.CENTER);
				registerP.setEnabled(false);
				loginP.setEnabled(true);
				revalidate();
				repaint();
			}
		});
		
		top.add(loginP);
		top.add(registerP);
		
		center.add(login, BorderLayout.CENTER);
		
		add(top, BorderLayout.NORTH);
		add(center, BorderLayout.CENTER);
	}
	public static void main(String[] args) {
		new VentanaLogin();
	}

}
