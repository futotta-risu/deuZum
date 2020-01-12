package io.github.fatsquirrels.deuzum.utils;



import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import static org.junit.Assert.assertEquals;

import java.awt.Component;
import java.util.HashMap;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import io.github.fatsquirrels.deuzum.utils.ObjectMapper;

@DisplayName("ObjectMapper Specifications")
class ObjectMapperTest {
	
	@Test
	void testCreateComponentMap() {
		JPanel panel = new JPanel();
		JButton btn1 = new JButton("Boton1");
		JTextField txt1 = new JTextField();
		panel.add(btn1);
		panel.add(txt1);
		
		HashMap<String, Component > expected = new HashMap<String, Component>();
		expected.put("btn1", btn1);
		expected.put("txt1", txt1);
		
		HashMap<String, Component > actual = ObjectMapper.createComponentMap(panel);
		assertEquals("Error al intentar crear un mapa de componentes",expected, actual);
	}
	
	
	@Test
	void testGetComponentByName() {
		JButton btn1 = new JButton("Boton1");
		JTextField txt1 = new JTextField();
		HashMap<String, Component > hashMap = new HashMap<String, Component>();
		hashMap.put("btn1", btn1);
		hashMap.put("txt1", txt1);
		
		Component expected = btn1;
		Component actual = ObjectMapper.getComponentByName("btn1", hashMap);
		assertEquals("Error al intentar obtener un component del mapa",expected, actual);
		
		
	}

}
