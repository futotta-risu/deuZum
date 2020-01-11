package io.github.fatsquirrels.deuzum.visual.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.Map.Entry;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import io.github.fatsquirrels.deuzum.IA.bots.*;
import io.github.fatsquirrels.deuzum.utils.WebpageConnection;
import io.github.fatsquirrels.deuzum.visual.components.textAreaNoWrite;
import io.github.fatsquirrels.deuzum.visual.components.buttons.FlatButton;
import io.github.fatsquirrels.deuzum.visual.style.CustomColors;
import io.github.fatsquirrels.deuzum.visual.style.layout.VerticalFlowLayout;
import io.github.fatsquirrels.deuzum.visual.style.layout.*;
import java.util.HashMap;
import javax.swing.ScrollPaneConstants;



public class HomePanel extends JPanel{

	private static final long serialVersionUID = -2649734373790386687L;

	private final String FAQ_URL = "https://github.com/futotta-risu/deuZum/wiki";
	private final String ABOUT_US_URL = "https://github.com/futotta-risu/deuZum/graphs/contributors";

	private HashMap<String,BotPanel> botPanels;

	public enum StatusType{
		on("Running"), off("Off");
		
		private String status;
		StatusType(String status) {
			this.status = status;
		}
		public String getStatus() {
			return this.status;
		}
	}
	private StatusType stType;
	
	public JLabel serverLabel;
	
	public HomePanel(String titulo, String texto_central) {
		
		stType = StatusType.off;
		initializeProperties();
		initializeComponents(titulo, texto_central);
		
	}
	
	private void  initializeProperties() {
		setBackground(new Color(255, 255, 255));
		setLayout(new BorderLayout());
		
	}
	
	private void initializeComponents(String titulo, String texto_central) {
		
		// Contenedor principal
		JPanel panel_Home_Center = new JPanel();
		panel_Home_Center.setBackground(Color.WHITE);
		panel_Home_Center.setLayout(new BorderLayout(0, 0));
		
		// Titulo
		JPanel panel_Home_Title = new JPanel();
		panel_Home_Title.setBackground(Color.WHITE);
		
		// Titulo
		JLabel lblBienvenido = new JLabel(titulo);
		lblBienvenido.setFont(new Font("Helvetica", Font.BOLD, 29));
		panel_Home_Title.add(lblBienvenido);
		
		
		JPanel panel_Home_Info = new JPanel();
		panel_Home_Info.setBackground(Color.WHITE);
		panel_Home_Info.setLayout(new VerticalFlowLayout(0,10,10));
		
		
		JTextArea tf_Information = new textAreaNoWrite();
		tf_Information.setText(texto_central);
		
		
		JTextArea tf_Software_Info = new textAreaNoWrite();
		tf_Software_Info.setText("Actualmente esta aplicaci\u00F3n se encuentra en la versi\u00F3n alpha. Esta permite la realizaci\u00F3n de las tareas m\u00E1s basicas pero requiere todav\u00EDa bastantes cambios para evitar errores y mejorar el rendimiento. Las caracteristicas de esta versi\u00F3n han sido las siguientes:\r\n\r\n\t- Ventanas de control de la base de datos\r\n\t- Plantilla de la base de datos.\r\n\t- Estado actual del servidor");
		
		
		JPanel panel_Home_Info_Botones = new JPanel();
		panel_Home_Info_Botones.setBackground(Color.WHITE);
		
		JButton btn_FAQ = new FlatButton("Documentacion");
		btn_FAQ.addActionListener( e -> {
			try {
				WebpageConnection.openWebpage(FAQ_URL);
			} catch (MalformedURLException | URISyntaxException malformedURL) {
				JOptionPane.showMessageDialog(this, "El enlace no es valido.");
			} catch (IOException IOExcp) {
				JOptionPane.showMessageDialog(this, "El enlace no se ha podido abrir.");
			} 
			
		});
		
		JButton btn_About_Us = new FlatButton("Sobre Nosotros");
		btn_About_Us.addActionListener(e -> {
			try {
				WebpageConnection.openWebpage(ABOUT_US_URL);
			} catch (MalformedURLException | URISyntaxException malformedURL) {
				JOptionPane.showMessageDialog(this, "El enlace no es valido.");
			} catch (IOException IOExcp) {
				JOptionPane.showMessageDialog(this, "El enlace no se ha podido abrir.");
			} 
			
		});
		
		panel_Home_Info_Botones.add(btn_FAQ);
		panel_Home_Info_Botones.add(btn_About_Us);
		
		JPanel panel_Home_Lateral = new JPanel();
		panel_Home_Lateral.setSize(500, 500);
		panel_Home_Lateral.setBackground(new Color(176, 224, 230));		
		panel_Home_Lateral.setLayout(new VerticalFlowLayout(10,10,10));
		panel_Home_Lateral.setBorder(BorderFactory.createMatteBorder(1,1,0,0, CustomColors.LightSaturatedBlue));
		
		JPanel panel_Home_Lateral_Status = new JPanel(new VerticalFlowLayout(10,10,10));
		panel_Home_Lateral.add(panel_Home_Lateral_Status);
		panel_Home_Lateral_Status.setBackground(CustomColors.BlueGray);
		
		
		
		panel_Home_Lateral_Status.setBorder(new BubbleBorder(CustomColors.LightSaturatedBlue,1,16));
		serverLabel = new JLabel("Status: Off", SwingConstants.CENTER);
		serverLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		serverLabel.setForeground(new Color(0, 0, 0));
		panel_Home_Lateral_Status.add(serverLabel);
		
		
		JPanel panel_Lateral_Info = new JPanel(new VerticalFlowLayout(0,0,0));
		panel_Home_Lateral_Status.add(panel_Lateral_Info);
		
		JLabel lblVersion = new JLabel("Version: v1.0", SwingConstants.CENTER);
		panel_Lateral_Info.add(lblVersion);
		
		JLabel lblGroup = new JLabel("Creado por FatSquirrels", SwingConstants.CENTER);
		panel_Lateral_Info.add(lblGroup);
		
		panel_Lateral_Info.setLayout(new VerticalFlowLayout(10,10,10));
		panel_Lateral_Info.setBorder(new BubbleBorder(CustomColors.LightSaturatedBlue,1,16));
		panel_Lateral_Info.setBackground(Color.WHITE);
		
		JPanel panel_Home_Lateral_Bots = new JPanel(new VerticalFlowLayout(10,10,10));
		panel_Home_Lateral.add(panel_Home_Lateral_Bots);
		panel_Home_Lateral_Bots.setBackground(CustomColors.BlueGray);
		panel_Home_Lateral_Bots.setBorder(new BubbleBorder(CustomColors.LightSaturatedBlue,1,16));
		
		
		botPanels = new HashMap<String,BotPanel>();
		botPanels.put("mail", new BotPanel(BotType.MAIL));
		botPanels.put("cleaning", new BotPanel(BotType.CLEANING));
		botPanels.put("user", new BotPanel(BotType.USUARIO));
		botPanels.put("account", new BotPanel(BotType.ACCOUNT));
		botPanels.put("transaction", new BotPanel(BotType.TRANSACCION));
		botPanels.put("group", new BotPanel(BotType.GRUPO));
		botPanels.put("proyect", new BotPanel(BotType.PROYECTO));
		botPanels.put("proyectTransaction", new BotPanel(BotType.PROYECTOTRANSACCION));
		
		for(Entry<String, BotPanel> botPanel : botPanels.entrySet())
			panel_Home_Lateral_Bots.add(botPanel.getValue());
		
		JScrollPane scrollPanel_Lateral = new JScrollPane(panel_Home_Lateral,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
	            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPanel_Lateral.setBorder(new EmptyBorder(0, 0, 0, 0));
		scrollPanel_Lateral.setPreferredSize(new Dimension(300,100));
		add(scrollPanel_Lateral, BorderLayout.EAST);
		
		panel_Home_Info.add(panel_Home_Info_Botones, BorderLayout.SOUTH);
		panel_Home_Info.add(tf_Software_Info, BorderLayout.EAST);
		panel_Home_Info.add(tf_Information, BorderLayout.NORTH);
		
		panel_Home_Title.setBorder(new EmptyBorder(0,0,20,0));
		
		panel_Home_Center.add(panel_Home_Info,BorderLayout.CENTER);
		panel_Home_Center.add(panel_Home_Title, BorderLayout.NORTH);
		
		panel_Home_Center.setBorder(new EmptyBorder(20,20,20,20));
		JPanel panel_Shadow_Home_Center = new JPanel(new BorderLayout());
		
		panel_Shadow_Home_Center.setBorder(BorderFactory.createMatteBorder(1,2,0,0, CustomColors.LightGrey));
		panel_Shadow_Home_Center.add(panel_Home_Center,BorderLayout.CENTER);
		
		add(panel_Shadow_Home_Center,BorderLayout.CENTER);
		
	}




	public void changeServerStatus(StatusType s) {
		this.stType = s;
		serverLabel.setText("Status: " + stType.getStatus());
		revalidate();
	}
	
	
}
