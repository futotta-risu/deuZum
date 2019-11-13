package io.github.fatsquirrels.deuzum.Visual.Style.Components.Buttons;


import java.awt.Dimension;

import javax.swing.Icon;
import javax.swing.JButton;

import io.github.fatsquirrels.deuzum.FileManagement.Icons;

public class IconizedButton extends JButton{

	private Icon icon, hoverIcon;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public IconizedButton() {
		setStyle();
	}
	public IconizedButton(String text) {
		setStyle();
		setText(text);
	}
	public IconizedButton(Icon icono) {
		super(icono);
		setStyle();
		
	}
	public IconizedButton(String folder, String name, int imgSize, int size) {
		
		
		icon = Icons.loadIcon(folder+"/"+name+".png", imgSize);
		hoverIcon = Icons.loadIcon(folder+"/"+name+"Hover.png", imgSize);
		setIcon(icon);
		setRolloverIcon(hoverIcon);
		setStyle();
		setPreferredSize(new Dimension(size,size));
		
	}
	public void setStyle() {
		setOpaque( false );
		setBorderPainted( false );
		setFocusPainted( false );
		setContentAreaFilled( false );
		
	}
}
