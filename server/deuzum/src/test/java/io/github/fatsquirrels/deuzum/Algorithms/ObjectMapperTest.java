package io.github.fatsquirrels.deuzum.Algorithms;

import static org.junit.jupiter.api.Assertions.*;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Component;
import java.util.HashMap;

import org.junit.jupiter.api.Test;

class ObjectMapperTest {
	
	//TODO Arreglar esto
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
		assertEquals(expected, actual);
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
		assertEquals(expected, actual);
		
		
	}

}
