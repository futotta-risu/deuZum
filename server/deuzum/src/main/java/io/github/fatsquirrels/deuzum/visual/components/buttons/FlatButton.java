package io.github.fatsquirrels.deuzum.visual.components.buttons;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.JButton;

import io.github.fatsquirrels.deuzum.visual.style.CustomColors;
import io.github.fatsquirrels.deuzum.visual.style.layout.BubbleBorder;

/**
 * Boton con estilo Flat.
 */
public class FlatButton extends JButton {


	private static final long serialVersionUID = 4490645896762745693L;
	public FlatButton() {
		setStyle();
	}
	public FlatButton(String text) {
		setStyle();
		setText(text);
	}
	public FlatButton(Icon icono) {
		super(icono);
		setStyle();
		
	}
	public FlatButton(String text, ActionListener e) {
		setStyle();
		setText(text);
		addActionListener(e);
	}
	public FlatButton(Icon icono, int size) {
		super(icono);
		setStyle();
		setPreferredSize(new Dimension(size,size));
		
	}
	public void setStyle() {
		setBorder(new BubbleBorder(CustomColors.LighterSaturatedBlue,1,8));
		setBackground(Color.WHITE);
		setBorderPainted(true);
		setFocusPainted(false);
		setContentAreaFilled(true);
		
	}
	

	
}
