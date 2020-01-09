package io.github.fatsquirrels.deuzum.visual.panels;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import io.github.fatsquirrels.deuzum.IA.bots.BotBase;
import io.github.fatsquirrels.deuzum.IA.bots.BotGenerator;
import io.github.fatsquirrels.deuzum.IA.bots.BotType;
import io.github.fatsquirrels.deuzum.visual.components.buttons.ActivatedButton;
import io.github.fatsquirrels.deuzum.visual.components.buttons.IconizedButton;
import io.github.fatsquirrels.deuzum.visual.style.CustomColors;

public class BotPanel extends JPanel{
	
	private static final long serialVersionUID = -2465158538015026363L;

	private BotType type;
	
	private boolean isEnabled;
	
	private JLabel lblBotCount;
	private ActivatedButton btnEnabled;
	private JButton btnCreate, btnDelete, btnPause;
	
	private JPanel bottomPanel;
	private int botCount = 0;
	private ArrayList<BotBase> botList;
	
	public BotPanel(BotType typeT) {
		this.type = typeT;
		botList = new ArrayList<BotBase>();
		
		setLayout(new GridLayout(2,1));
		btnEnabled = new ActivatedButton("Activar");
		btnEnabled.addActionListener(e -> setEnabled(!isEnabled()));
		
		
		bottomPanel = new JPanel(new FlowLayout());
		btnCreate = new IconizedButton("symbol",type.getIconName(),35,40,e -> addBot());
		
		
		btnDelete = new IconizedButton("symbol","borrar",35,40,e -> deleteBot());
		
		
		btnPause = new IconizedButton("symbol","pauseBot",35,40,e -> pauseBots());
		
		lblBotCount = new JLabel("0");
		lblBotCount.setHorizontalAlignment(SwingConstants.CENTER);
		
		add(btnEnabled);
		
		setEnabled(false);
		refreshComponents();
	}
	
	public boolean isEnabled() {
		return this.isEnabled;
	}
	
	public void setEnabled(boolean state) {
		this.isEnabled = state;
		refreshComponents();
	}
	
	private void addBot() {
		Thread t1 = new Thread(()->{
				BotBase newBot = BotGenerator.generateBot(type, type.getBotClass() + " Bot " + String.valueOf(botCount++));
				System.out.println(newBot.toString());
				botList.add(newBot);
				lblBotCount.setText(botCount +"");
				newBot.execute();
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
					botList.remove(botBase);
				}
				this.botCount = botList.size();
				lblBotCount.setText(this.botCount+"");		
		});
		t1.run();	
	}
	
	public void refreshComponents() {
		if(btnEnabled.isSelected()) {
			btnEnabled.setText("Desactivar Bot "+ type.getBotClass());
			btnEnabled.setBackground(CustomColors.Green);
			bottomPanel.add(btnCreate);
			bottomPanel.add(btnPause);
			bottomPanel.add(btnDelete);
			bottomPanel.add(lblBotCount);
			setLayout(new GridLayout(2,1));
			add(bottomPanel);
		}else {
			btnEnabled.setText("Activar Bot "+ type.getBotClass());
			btnEnabled.setBackground(CustomColors.SaturatedRed);
			lblBotCount.setText(0+"");
			remove(bottomPanel);
			setLayout(new GridLayout(1,1));
			revalidate();
			repaint();
		}
	}
	
}
