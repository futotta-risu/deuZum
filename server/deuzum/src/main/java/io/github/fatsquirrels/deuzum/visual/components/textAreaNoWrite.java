package io.github.fatsquirrels.deuzum.visual.components;

import javax.swing.JTextArea;
import javax.swing.UIManager;

public class textAreaNoWrite extends JTextArea{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8681092823807818205L;

	public textAreaNoWrite() {
		setEditable(false);
		setLineWrap(true);
		setWrapStyleWord(true);
		setLineWrap(true);
		setOpaque(false);
		setFocusable(false);
		setBackground(UIManager.getColor("Label.background"));
		setFont(UIManager.getFont("Label.font"));
		setBorder(UIManager.getBorder("Label.border"));
	}
	
}
