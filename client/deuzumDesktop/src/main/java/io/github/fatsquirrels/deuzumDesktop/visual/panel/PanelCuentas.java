package io.github.fatsquirrels.deuzumDesktop.visual.panel;

import javax.swing.JPanel;

import io.github.fatsquirrels.deuzumDesktop.visual.layout.VerticalFlowLayout;

import javax.swing.JLabel;
import javax.swing.JList;

public class PanelCuentas extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private JLabel lblEstasSonMis = new JLabel();
	private JList listCuentas = new JList();
	
	public PanelCuentas() {
		setLayout(new VerticalFlowLayout(10,10,10));

		
		lblEstasSonMis = new JLabel("Estas son mis cuentas:");
		add(lblEstasSonMis);
		
		listCuentas = new JList();
		add(listCuentas);
		
	}
}
