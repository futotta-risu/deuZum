package io.github.fatsquirrels.deuzum.visual.panels;

import java.util.HashMap;

import javax.swing.JPanel;


public class PanelListProperties {
	
	
	private HashMap<String, PanelProperties> panelMap;

	public PanelListProperties(){
		panelMap = new HashMap<String, PanelProperties>();
	}
	
	public boolean getEnabled(String name) {
		return panelMap.get(name).isChangeable();
	}
	
	public JPanel getPanel(String name) {
		return panelMap.get(name).getPanel();
	}
	
	public void addPanel(String name, PanelProperties properties) {
		panelMap.put(name, properties);
	}
	
	public int getSize() {
		return panelMap.size();
	}
	public boolean containsKey(String name) {
		return panelMap.containsKey(name);
	}
	
}
