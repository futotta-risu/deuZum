package Paneles;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JList;

public class panelGrupo extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private JLabel lblMisgrupos= new JLabel();
	private JList listMisGrupos= new JList();

	public panelGrupo() {
<<<<<<< HEAD
		setLayout(new VerticalFlowLayout(10,10,10));

		
=======
		//this.setSize(500, 200);
		this.setVisible(true);
		setLayout(null);
>>>>>>> parent of 0c786a1... Merge branch 'master' of https://github.com/futotta-risu/deuZum
		
		lblMisgrupos = new JLabel("Estos son mis grupos:");
		add(lblMisgrupos);
		
		listMisGrupos = new JList();
		add(listMisGrupos);
				
		
	}
}
