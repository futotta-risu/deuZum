package io.github.fatsquirrels.deuzumDesktop.visual.panel.util;

import java.awt.BorderLayout;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PairPanel extends JPanel{

	private static final long serialVersionUID = 8127948996173845416L;

	public PairPanel(String texto) {
		setLayout(new BorderLayout());
		JLabel temp1 = new JLabel(texto);
		add(temp1, BorderLayout.WEST);
		add(new JTextField(10), BorderLayout.EAST);
	}
	public PairPanel(String texto, String texto2) {
		setLayout(new BorderLayout());
		JLabel temp1 = new JLabel(texto);
		add(temp1, BorderLayout.WEST);
		JTextField temp2 = new JTextField(10);
		temp2.setText(texto2);
		add(temp2, BorderLayout.EAST);
	}
	
	public PairPanel(String texto, JComponent temp2) {
		setLayout(new BorderLayout());
		JLabel temp1 = new JLabel(texto);
		add(temp1, BorderLayout.WEST);
		add(temp2, BorderLayout.EAST);
	}
	
	public PairPanel(JComponent temp1, JComponent temp2) {
		setLayout(new BorderLayout());
		add(temp1, BorderLayout.WEST);
		add(temp2, BorderLayout.EAST);
	}
	
	
	
}
