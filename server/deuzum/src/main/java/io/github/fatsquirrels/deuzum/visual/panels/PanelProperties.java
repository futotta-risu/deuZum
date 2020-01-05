package io.github.fatsquirrels.deuzum.visual.panels;

import javax.swing.JPanel;

public class PanelProperties {
	JPanel panel;
	boolean isChangeable;
	
	public PanelProperties(JPanel panelT, boolean isChangeableT){
		panel = panelT;
		isChangeable = isChangeableT;
	}
	
	public JPanel getPanel() {
		return panel;
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

	public boolean isChangeable() {
		return isChangeable;
	}

	public void setChangeable(boolean isChangeable) {
		this.isChangeable = isChangeable;
	}
}
