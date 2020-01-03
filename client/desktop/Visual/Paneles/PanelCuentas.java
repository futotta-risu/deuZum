package Paneles;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JList;

public class PanelCuentas extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private JLabel lblEstasSonMis = new JLabel();
	private JList listCuentas = new JList();
	
	public PanelCuentas() {
<<<<<<< HEAD
		setLayout(new VerticalFlowLayout(10,10,10));

=======
		setLayout(null);
>>>>>>> parent of 0c786a1... Merge branch 'master' of https://github.com/futotta-risu/deuZum
		
		lblEstasSonMis = new JLabel("Estas son mis cuentas:");
		add(lblEstasSonMis);
		
		listCuentas = new JList();
		add(listCuentas);
		
	}
}
