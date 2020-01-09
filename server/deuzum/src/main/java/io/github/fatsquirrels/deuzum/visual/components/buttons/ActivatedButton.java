package io.github.fatsquirrels.deuzum.visual.components.buttons;


import javax.swing.JToggleButton;

import io.github.fatsquirrels.deuzum.visual.style.CustomColors;

public class ActivatedButton extends JToggleButton{
	
	private static final long serialVersionUID = 8599437877918600714L;

	public ActivatedButton(String text) {
		setStyle();
		setText(text);
	}
	
	public void setStyle() {
		setBackground(CustomColors.SaturatedRed);
		setBorderPainted(true);
		setFocusPainted(false);
		setContentAreaFilled(true);
		
	}

}
