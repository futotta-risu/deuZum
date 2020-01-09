package io.github.fatsquirrels.deuzum.visual.panels.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.swing.JPanel;


public class PanelListProperties implements Iterable<String>{
	
	
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
	
	public void cleanPLP() {
		HashMap<String, PanelProperties> temp = new HashMap<String, PanelProperties>();
		for(Map.Entry<String, PanelProperties> i : panelMap.entrySet())
			if(i.getValue().isChangeable) temp.put(i.getKey(),i.getValue());
		panelMap = temp;
	}

	@Override
	public Iterator<String> iterator() {
		Iterator<String> iter = panelMap.keySet().iterator();
		return iter;
	}
	
}
