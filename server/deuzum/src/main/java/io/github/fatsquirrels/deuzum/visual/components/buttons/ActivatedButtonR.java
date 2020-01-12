package io.github.fatsquirrels.deuzum.visual.components.buttons;

import java.awt.Color;

import javax.swing.JButton;

import io.github.fatsquirrels.deuzum.visual.style.CustomColors;
import io.github.fatsquirrels.deuzum.visual.style.layout.BubbleBorder;

public class ActivatedButtonR extends JButton{

	private static final long serialVersionUID = 358345752120253285L;

	public ActivatedButtonR(String text) {
		setStyle();
		setText(text);
	}
	
	public void setStyle() {
		
		
		setBorder(new BubbleBorder(CustomColors.LighterSaturatedBlue,1,8));
		setBackground(Color.WHITE);
		setBorderPainted(true);
		setFocusPainted(true);
		setContentAreaFilled(true);
		

	}
}
