package Paneles;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JList;

public class panelGrupo extends JPanel {
	private JLabel lblMisgrupos= new JLabel();
	private JList listMisGrupos= new JList();

	public panelGrupo() {
		//this.setSize(500, 200);
		this.setVisible(true);
		setLayout(null);
		
		lblMisgrupos = new JLabel("Estos son mis grupos:");
		lblMisgrupos.setBounds(26, 45, 112, 14);
		add(lblMisgrupos);
		
		listMisGrupos = new JList();
		listMisGrupos.setBounds(154, 77, 262, 132);
		add(listMisGrupos);
				
		
	}
}
