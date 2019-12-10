package io.github.fatsquirrels.deuzum.Algorithms;

import java.awt.Component;
import java.util.HashMap;

import javax.swing.JPanel;

import io.github.fatsquirrels.deuzum.Annotations.Tested;



/**
 * Contiene las funciones para trabajar con mapas de componentes
 * @see #createComponentMap
 * @see #getComponentByName
 * @author erik.terres.es
 *
 */
@Tested(tested = true)
public class ObjectMapper {
	
	/**
	 * Metodo que crea un HashMap que contiene los Componentes de un Panel y sus nombres
	 * @param panel Panel que contiene los objetos a Mapear
	 * @return HashMap < String, Component > con los nombres y componentes del panel introducido
	 */
	public static HashMap<String,Component> createComponentMap(JPanel panel) {
		HashMap<String, Component >componentMap = new HashMap<String,Component>();
        Component[] components = panel.getComponents();
        for (int i=0; i < components.length; i++) 
                componentMap.put(components[i].getName(), components[i]);
        return componentMap;
        
	}
	
	/**
	 * Metodo que obtiene un componente mediante su nombre en el HashMap recibido
	 * @param name Nombre del componente
	 * @param componentMap HashMap < String, Component > Contiene componentes junto a sus nombres
	 * @return Componente que tiene el mismo nombre que el parametro introducido
	 */
	public static Component getComponentByName(String name, HashMap<String,Component> componentMap) {
        if (componentMap.containsKey(name)) 
                return (Component) componentMap.get(name);
        else return null;
	}
}
