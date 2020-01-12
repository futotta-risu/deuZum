package io.github.fatsquirrels.deuzumDesktop.visual.panel;

import javax.swing.JPanel;

import io.github.fatsquirrels.deuzumDesktop.visual.layout.VerticalFlowLayout;

import javax.swing.JLabel;
import javax.swing.JList;

public class panelGrupo extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private JLabel lblMisgrupos= new JLabel();
	private JList listMisGrupos= new JList();

	public panelGrupo() {
		setLayout(new VerticalFlowLayout(10,10,10));

		
		
		lblMisgrupos = new JLabel("Estos son mis grupos:");
		add(lblMisgrupos);
		
		listMisGrupos = new JList();
		add(listMisGrupos);
				
		
	}
}
