package io.github.fatsquirrels.deuzum.visual.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import io.github.fatsquirrels.deuzum.utils.WebpageConnection;
import io.github.fatsquirrels.deuzum.visual.Style.CustomColors;
import io.github.fatsquirrels.deuzum.visual.Style.Layout.VerticalFlowLayout;
import io.github.fatsquirrels.deuzum.visual.components.textAreaNoWrite;
import io.github.fatsquirrels.deuzum.visual.components.buttons.FlatButton;
import net.miginfocom.swing.MigLayout;



public class HomePanel extends JPanel{

	private static final long serialVersionUID = -2649734373790386687L;

	
	private final String FAQ_URL = "https://github.com/futotta-risu/deuZum/wiki";
	private final String ABOUT_US_URL = "https://github.com/futotta-risu/deuZum/graphs/contributors";
	
	
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
		setLayout(new MigLayout("fill,insets 0", "[455.00px,grow,center][200.00px,grow 60,center]", "[408px,grow]"));
		
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
		lblBienvenido.setFont(new Font("Maiandra GD", Font.PLAIN, 29));
		panel_Home_Title.add(lblBienvenido);
		
		
		JPanel panel_Home_Info = new JPanel();
		panel_Home_Info.setBackground(Color.WHITE);
		panel_Home_Info.setLayout(new VerticalFlowLayout(0,10,10));
		
		
		JTextArea tf_Information = new textAreaNoWrite();
		tf_Information.setText(texto_central);
		
		
		JLabel lblVersiones = new JLabel("Versiones");
		lblVersiones.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panel_Home_Info.add(lblVersiones, BorderLayout.WEST);
		
		JTextArea tf_Software_Info = new textAreaNoWrite();
		tf_Software_Info.setText("Actualmente esta aplicaci\u00F3n se encuentra en la versi\u00F3n alpha. Esta permite la realizaci\u00F3n de las tareas m\u00E1s basicas pero requiere todav\u00EDa bastantes cambios para evitar errores y mejorar el rendimiento. Las caracteristicas de esta versi\u00F3n han sido las siguientes:\r\n\r\n\t- Ventanas de control de la base de datos\r\n\t- Plantilla de la base de datos.\r\n\t- Estado actual del servidor");
		
		
		JPanel panel_Home_Info_Botones = new JPanel();
		panel_Home_Info_Botones.setBackground(Color.WHITE);
		
		JButton btn_FAQ = new FlatButton("Documentacion");
		btn_FAQ.addActionListener( e -> WebpageConnection.openWebpage(FAQ_URL));
		
		
		
		JButton btn_About_Us = new FlatButton("Sobre Nosotros");
		btn_About_Us.addActionListener(e -> WebpageConnection.openWebpage(ABOUT_US_URL));
		
		panel_Home_Info_Botones.add(btn_FAQ);
		panel_Home_Info_Botones.add(btn_About_Us);
		JPanel panel_Home_Lateral = new JPanel();
		panel_Home_Lateral.setBackground(new Color(176, 224, 230));
		
		panel_Home_Lateral.setLayout(new  VerticalFlowLayout(0,true));
		JPanel panel_Home_Lateral_Status = new JPanel();
		panel_Home_Lateral.add(panel_Home_Lateral_Status);
		panel_Home_Lateral_Status.setBackground(CustomColors.mBOrangeL);
		panel_Home_Lateral_Status.setLayout(new VerticalFlowLayout(0,10,10));
		
		serverLabel = new JLabel("Status: Off");
		serverLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panel_Home_Lateral_Status.add(serverLabel);
		serverLabel.setForeground(new Color(0, 0, 0));
		
		JPanel panel_Lateral_Info = new JPanel();
		panel_Lateral_Info.setLayout(new VerticalFlowLayout(0,5,5));
		panel_Home_Lateral_Status.add(panel_Lateral_Info);
		
		JLabel lblVersion = new JLabel("Version: alpha0.0.1");
		panel_Lateral_Info.add(lblVersion);
		
		JLabel lblNewLabel_1 = new JLabel("FatSquirrels");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		panel_Lateral_Info.add(lblNewLabel_1);
		
		JPanel panel_Home_Lateral_Help = new JPanel();
		panel_Home_Lateral_Help.setPreferredSize(new Dimension(200, 300));
		panel_Home_Lateral.add(panel_Home_Lateral_Help);
		panel_Home_Lateral_Help.setBackground(CustomColors.mBBlueGrayL);
		
		JScrollPane scrollPanel_Lateral = new JScrollPane(panel_Home_Lateral,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
	            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPanel_Lateral.setBorder(new EmptyBorder(0, 0, 0, 0));
		add(scrollPanel_Lateral, "cell 1 0,grow");
		
		
		
		panel_Home_Info.add(panel_Home_Info_Botones, BorderLayout.SOUTH);
		panel_Home_Info.add(tf_Software_Info, BorderLayout.EAST);
		panel_Home_Info.add(tf_Information, BorderLayout.NORTH);
		
		panel_Home_Center.add(panel_Home_Info,BorderLayout.CENTER);
		panel_Home_Center.add(panel_Home_Title, BorderLayout.NORTH);
		
		add(panel_Home_Center, "cell 0 0,grow");
		
	}
	
	public void changeServerStatus(StatusType s) {
		this.stType = s;
		serverLabel.setText("Status: " + stType.getStatus());
		revalidate();
	}
	
	
	
}
