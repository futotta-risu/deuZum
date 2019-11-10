package deustoZumServer.Visual.Style.Components.Buttons;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;

import deustoZumServer.Visual.Style.CustomColors;

public class MenuButton extends JButton{

		public MenuButton() {
			setStyle();
		}
		public MenuButton(String text) {
			setStyle();
			setText(text);
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
