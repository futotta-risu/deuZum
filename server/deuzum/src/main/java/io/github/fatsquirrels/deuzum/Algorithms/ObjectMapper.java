package io.github.fatsquirrels.deuzum.Algorithms;

import java.awt.Component;
import java.util.HashMap;

import javax.swing.JPanel;

/**
 * Contiene las funciones para trabajar con mapas de componentes
 * @author erik.terres.es
 *
 */
public class ObjectMapper {
	
	
	public static HashMap<String,Component> createComponentMap(JPanel temp) {
		HashMap<String, Component >componentMap = new HashMap<String,Component>();
        Component[] components = temp.getComponents();
        for (int i=0; i < components.length; i++) 
                componentMap.put(components[i].getName(), components[i]);
        return componentMap;
        
	}
	
	
	public static Component getComponentByName(String name, HashMap<String,Component> componentMap) {
        if (componentMap.containsKey(name)) 
                return (Component) componentMap.get(name);
        else return null;
	}
}
