package io.github.fatsquirrels.deuzum.Visual.Style.Components.JPanels;

import javax.swing.JPanel;

import io.github.fatsquirrels.deuzum.Visual.Style.CustomColors;
import net.miginfocom.swing.MigLayout;

public class MenuPanel extends JPanel{
	private static final long serialVersionUID = -7675988252190546922L;

	public MenuPanel() {
		setBackground(CustomColors.mBGreyL);
		setLayout(new MigLayout("", "[55px]", "[23px][][][][][]"));
	}
}
