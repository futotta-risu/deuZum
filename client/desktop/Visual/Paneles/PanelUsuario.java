package Paneles;

import javax.swing.JPanel;

import java.awt.Container;

import javax.swing.JButton;

public class PanelUsuario extends JPanel{
	
	private JButton btn1;
	
	
	public PanelUsuario() {
		
		
		
		
		btn1 = new JButton("New button");
		btn1.setBounds(51, 29, 72, 42);
		add(btn1);
		
		
		
		//this.setSize(500, 200);
		this.setVisible(true);
		setLayout(null);
	}
	


}
