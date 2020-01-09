package io.github.fatsquirrels.deuzum.visual.panels.util;

import javax.swing.JPanel;

public class PanelProperties {
	JPanel panel;
	boolean isChangeable;
	
	public PanelProperties(JPanel panelT, boolean isChangeableT){
		panel = panelT;
		isChangeable = isChangeableT;
	}
	
	public JPanel getPanel() {
		return this.panel;
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
