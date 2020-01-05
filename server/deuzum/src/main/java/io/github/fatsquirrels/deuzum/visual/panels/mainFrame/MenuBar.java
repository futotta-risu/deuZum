package io.github.fatsquirrels.deuzum.visual.panels.mainFrame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JComponent;
import javax.swing.JPanel;

import io.github.fatsquirrels.deuzum.visual.style.CustomColors;

public class MenuBar extends JPanel{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6683307666068708856L;
	
	
	JPanel left_Size, right_Size;
	
	public MenuBar() {
		setLayout(new BorderLayout());
		setBackground(CustomColors.mBlueR);
		setPreferredSize(new Dimension(0, 53));
		left_Size = new JPanel();
		left_Size.setBackground(CustomColors.mBlueR);
		right_Size = new JPanel();
		right_Size.setOpaque(false);
		right_Size.setPreferredSize(new Dimension(153, 40));
		right_Size.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		add(left_Size, BorderLayout.WEST);
		add(right_Size, BorderLayout.EAST);
	}
	
	public void addLeft(JComponent j) {
		left_Size.add(j);
	}
	
	public void addLeft(JComponent[] j) {
		for(JComponent i : j)
			left_Size.add(i);
	}
	
	public void addRight(JComponent j) {
		right_Size.add(j);
	}
	
	public void addRight(JComponent[] j) {
		for(JComponent i : j)
			right_Size.add(i);
	}
	
}
