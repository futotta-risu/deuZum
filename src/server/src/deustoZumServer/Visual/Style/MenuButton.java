package deustoZumServer.Visual.Style;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;

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
		}
		public MenuButton isBotton() {
			setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.WHITE)); 
			return this;
		}
}
