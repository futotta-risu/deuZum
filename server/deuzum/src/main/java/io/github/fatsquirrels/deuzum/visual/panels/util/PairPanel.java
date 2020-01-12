package io.github.fatsquirrels.deuzum.visual.panels.util;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import io.github.fatsquirrels.deuzum.visual.style.CustomColors;

public class PairPanel extends JPanel{

	private static final long serialVersionUID = 8127948996173845416L;

	public PairPanel(String texto) {
		setStyle();
		JLabel temp1 = new JLabel(texto);
		add(temp1, BorderLayout.WEST);
		add(new JTextField(10), BorderLayout.EAST);
	}
	public PairPanel(String texto, String texto2) {
		setStyle();
		JLabel temp1 = new JLabel(texto); 
		add(temp1, BorderLayout.WEST);
		JTextField temp2 = new JTextField(10);
		temp2.setText(texto2);
		add(temp2, BorderLayout.EAST);

	}
	
	public PairPanel(String texto, JComponent temp2) {
		setStyle();
		JLabel temp1 = new JLabel(texto);
		temp1.setFont(new Font("Verdana", Font.PLAIN, 13));
		add(temp1, BorderLayout.WEST);
		add(temp2, BorderLayout.EAST);

	}
	
	public PairPanel(JComponent temp1, JComponent temp2) {
		setStyle();
		add(temp1, BorderLayout.WEST);
		add(temp2, BorderLayout.EAST);

	}
	
	public void setStyle() {
		setLayout(new BorderLayout());
		setBackground(CustomColors.VeryLightGrey);
	}
	
	
	
}
