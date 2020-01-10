package io.github.fatsquirrels.deuzum.visual;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import io.github.fatsquirrels.deuzum.visual.panels.mainFrame.ButtonMenu;
import io.github.fatsquirrels.deuzum.visual.panels.mainFrame.MenuBar;

/**
 * Generic Status-Menu Frame.
 * 
 * Este modelo de panel contiene una fila que actua como menu superior,
 * una columna de botones de redirección y un panel central que actua 
 * como contenedor del frame.
 * 
 * @author whiwho
 *
 */
public class GenericSMFrame extends JFrame{

	private static final long serialVersionUID = -7157700499871893057L;
	private JPanel centerPanel;
	private MenuBar topPanel;
	private ButtonMenu buttonMenu;
	
	
	public GenericSMFrame(){
		setVisible(true);
		setLayout(new BorderLayout());
		
		centerPanel = new JPanel();
		add(centerPanel, BorderLayout.CENTER);
		// Generacion del panel de Menu
		
		topPanel = new MenuBar();
		add(topPanel, BorderLayout.NORTH);
		
		// Generacion del panel de ButtonMenu
		buttonMenu = new ButtonMenu();
		add(buttonMenu, BorderLayout.WEST);
		
	}
	
	public void addComponentS(boolean isLeft, JComponent comp) {
		if(isLeft)
			topPanel.addLeft(comp);
		else
			topPanel.addRight(comp);
	}
	
	public void addComponentS(boolean isLeft, JComponent comp[]) {
		if(isLeft)
			topPanel.addLeft(comp);
		else
			topPanel.addRight(comp);
	}
	
	public void addButtonM(String name, JButton but) {
		buttonMenu.addButton(name, but);
		revalidate();
		repaint();
	}
	
	public void deleteButtonsM() {
		buttonMenu.deleteAll();
	}
	
	public void resetButtons() {
		buttonMenu.resetButtons();
	}
	
	public void setPanelC(JPanel pan) {
		remove(centerPanel);
		centerPanel = pan;
		add(centerPanel);
		revalidate();
		repaint();
	}
	
}
