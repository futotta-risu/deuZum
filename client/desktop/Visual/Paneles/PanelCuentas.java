package Paneles;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JList;

public class PanelCuentas extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private JLabel lblEstasSonMis = new JLabel();
	private JList listCuentas = new JList();
	
	public PanelCuentas() {
		
		
		lblEstasSonMis = new JLabel("Estas son mis cuentas:");
		lblEstasSonMis.setBounds(30, 48, 109, 14);
		add(lblEstasSonMis);
		
		listCuentas = new JList();
		listCuentas.setBounds(175, 47, 251, 113);
		add(listCuentas);
		
	}
}
