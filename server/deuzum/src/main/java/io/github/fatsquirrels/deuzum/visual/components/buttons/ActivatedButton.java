package io.github.fatsquirrels.deuzum.visual.components.buttons;

import javax.swing.JToggleButton;

import io.github.fatsquirrels.deuzum.visual.style.CustomColors;
import io.github.fatsquirrels.deuzum.visual.style.layout.BubbleBorder;


public class ActivatedButton extends JToggleButton{
	
	private static final long serialVersionUID = 8599437877918600714L;

	public ActivatedButton(String text) {
		setStyle();
		setText(text);
	}
	
	public void setStyle() {
		
		
		setBorder(new BubbleBorder(CustomColors.LighterSaturatedBlue,1,8));
		setBorderPainted(true);
		setFocusPainted(true);
		setContentAreaFilled(true);
		

		
	}

}
