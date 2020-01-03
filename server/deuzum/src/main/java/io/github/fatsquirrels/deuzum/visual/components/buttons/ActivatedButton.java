package io.github.fatsquirrels.deuzum.visual.components.buttons;

import java.awt.Color;

import javax.swing.JToggleButton;

import io.github.fatsquirrels.deuzum.visual.Style.CustomColors;

public class ActivatedButton extends JToggleButton{
	
	
	public ActivatedButton(String text) {
		setStyle();
		setText(text);
	}
	
	public void setStyle() {
		setBackground(CustomColors.mBRedLight);
		setBorderPainted(true);
		setFocusPainted(false);
		setContentAreaFilled(true);
		
	}

}
