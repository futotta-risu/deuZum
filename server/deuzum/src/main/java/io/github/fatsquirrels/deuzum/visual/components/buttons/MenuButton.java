package io.github.fatsquirrels.deuzum.visual.components.buttons;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;

import io.github.fatsquirrels.deuzum.visual.style.CustomColors;

public class MenuButton extends JButton{
	private static final long serialVersionUID = -3682688504237276231L;
		public MenuButton() {
			setStyle();
		}
		public MenuButton(String text) {
			setStyle();
			setText(text);
		}
		public MenuButton(String text, ActionListener e) {
			setStyle();
			setText(text);
			addActionListener(e);
		}
		public void setStyle() {
			setBackground(CustomColors.mBBlue);
			setForeground(Color.WHITE);
			setFont(new Font("Georgia", Font.PLAIN, 16));
			setBorderPainted(true);
			setFocusPainted(false);
			setContentAreaFilled(true);
			setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.WHITE)); 
			setPreferredSize(new Dimension(180,40));
		}
		public MenuButton isBotton() {
			setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.WHITE)); 
			return this;
		}
}
