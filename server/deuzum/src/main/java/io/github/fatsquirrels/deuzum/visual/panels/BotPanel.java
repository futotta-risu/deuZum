package io.github.fatsquirrels.deuzum.visual.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import io.github.fatsquirrels.deuzum.IA.bots.BotBase;
import io.github.fatsquirrels.deuzum.IA.bots.BotGenerator;
import io.github.fatsquirrels.deuzum.IA.bots.BotType;
import io.github.fatsquirrels.deuzum.net.Server;
import io.github.fatsquirrels.deuzum.visual.components.buttons.ActivatedButton;
import io.github.fatsquirrels.deuzum.visual.components.buttons.IconizedButton;
import io.github.fatsquirrels.deuzum.visual.style.CustomColors;
import io.github.fatsquirrels.deuzum.visual.style.animation.DropAnimation;

public class BotPanel extends JPanel{
	
	private static final long serialVersionUID = -2465158538015026363L;

	private BotType type;
	
	private boolean isEnabled = false;
	
	private JLabel lblBotCount;
	private ActivatedButton btnEnabled;
	private JButton btnCreate, btnDelete, btnPause;
	private DropAnimation animate;
	private JPanel bottomPanel;
	private int botCount = 0;
	private static ArrayList<BotBase> botList;
	
	public BotPanel(BotType typeT) {
		this.type = typeT;
		botList = new ArrayList<BotBase>();
		
		setLayout(new BorderLayout());
		btnEnabled = new ActivatedButton("Activar");
		btnEnabled.addActionListener(e -> setEnabled(!isEnabled()));
		
		
		bottomPanel = new JPanel(new FlowLayout());
		btnCreate = new IconizedButton("symbol",type.getIconName(),35,40,e -> addBot());
		
		
		btnDelete = new IconizedButton("symbol","borrar",35,40,e -> deleteBot());
		
		
		btnPause = new IconizedButton("symbol","pauseBot",35,40,e -> pauseBots());
		
		lblBotCount = new JLabel("0");
		lblBotCount.setHorizontalAlignment(SwingConstants.CENTER);
		
	
		add(btnEnabled, BorderLayout.NORTH);
		

		refreshComponents();
		
        revalidate();
        repaint();
	}
	
	public void animateOpen() {
		Rectangle from = new Rectangle(this.getX(), this.getY(), 211, 32);
        Rectangle to = new Rectangle(this.getX(), this.getY(), 211, 80);
        if(this.isEnabled) animate = new DropAnimation(this, from, to);
        else animate = new DropAnimation(this, to, from);
        
		animate.start();

	}
	
	public boolean isEnabled() {
		return this.isEnabled;
	}
	
	public void setEnabled(boolean state) {
		
		if(!Server.isRunning) {
			JOptionPane.showMessageDialog(this, "No puede activar bots con el servidor apagado.\n Enciendalo por favor.");
			
			btnEnabled.setSelected(false);
		}
			
		
		if(this.isEnabled != state && Server.isRunning) {
			this.isEnabled = state;
			refreshComponents();
			animateOpen();
		}else btnEnabled.setSelected(false);
		
	}
	
	private void addBot() {
		Thread t1 = new Thread(()->{
				BotBase newBot = BotGenerator.generateBot(type, type.getBotClass() + " Bot " + String.valueOf(botCount++));
				System.out.println(newBot.toString());
				botList.add(newBot);
				lblBotCount.setText(botCount +"");
		});
		t1.run();
		
	}
	
	private void pauseBots() {
		Thread t1 = new Thread(()->{
			if(botList.isEmpty()) return;
			else 
				for (BotBase botBase : botList) 
					botBase.stop(1000*Integer.parseInt(JOptionPane.showInputDialog("Introduce el tiempo de pausa:")));
			});
		t1.run();	
	}
	
	private void deleteBot() {
		Thread t1 = new Thread(() -> {
				for (BotBase botBase : botList) {
					botBase.kill();
				}
				botList.clear();
				this.botCount = botList.size();
				lblBotCount.setText(this.botCount+"");		
		});
		t1.run();	
	}
	
	public void refreshComponents() {
		if(btnEnabled.isSelected() && this.isEnabled) {
			btnEnabled.setText("Desactivar Bot "+ type.getBotClass());
			btnEnabled.setBackground(CustomColors.Green);
			bottomPanel.add(btnCreate);
			bottomPanel.add(btnPause);
			bottomPanel.add(btnDelete);
			bottomPanel.add(lblBotCount);
			add(bottomPanel, BorderLayout.CENTER);
		}else {
			btnEnabled.setText("Activar Bot "+ type.getBotClass());
			btnEnabled.setBackground(Color.WHITE);
			lblBotCount.setText(0+"");
			remove(bottomPanel);
			revalidate();
			repaint();
		}
	}
	
	public static ArrayList<BotBase> getBotList(){
		return botList;
	}
	
}
