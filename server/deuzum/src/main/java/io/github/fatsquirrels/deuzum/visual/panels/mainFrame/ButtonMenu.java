package io.github.fatsquirrels.deuzum.visual.panels.mainFrame;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JPanel;

import io.github.fatsquirrels.deuzum.visual.style.CustomColors;
import io.github.fatsquirrels.deuzum.visual.style.layout.VerticalFlowLayout;

public class ButtonMenu extends JPanel{

	private static final long serialVersionUID = 3735287384001018431L;
	
	
	private HashMap<String,JButton> buttons;
	
	public ButtonMenu(){
		buttons = new HashMap<String,JButton>();
		setBackground(CustomColors.mBBlue);
		setLayout(new VerticalFlowLayout(0, 0, 0));
		setPreferredSize(new Dimension(180,280));
	}
	
	public void addButton(String name, JButton button) {
		buttons.put(name, button);
		drawButtons();
	}
	
	
	public void drawButtons() {
		removeAll();
		for(Map.Entry<String, JButton> button: buttons.entrySet()) 
			add(button.getValue());
		revalidate();
		repaint();
		
	}
	
	public void resetButtons() {
		buttons.clear();
	}
	public void deleteAll() {
		removeAll();
		revalidate();
		repaint();
	}
}
