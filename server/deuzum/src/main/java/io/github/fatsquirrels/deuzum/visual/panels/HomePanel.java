package io.github.fatsquirrels.deuzum.visual.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import io.github.fatsquirrels.deuzum.IA.bots.*;
import io.github.fatsquirrels.deuzum.IA.bots.types.AccountBot;
import io.github.fatsquirrels.deuzum.IA.bots.types.CleaningBot;
import io.github.fatsquirrels.deuzum.IA.bots.types.GroupBot;
import io.github.fatsquirrels.deuzum.IA.bots.types.MailBot;
import io.github.fatsquirrels.deuzum.IA.bots.types.ProyectBot;
import io.github.fatsquirrels.deuzum.IA.bots.types.ProyectTransactionBot;
import io.github.fatsquirrels.deuzum.IA.bots.types.TransactionBot;
import io.github.fatsquirrels.deuzum.IA.bots.types.UserBot;
import io.github.fatsquirrels.deuzum.utils.WebpageConnection;
import io.github.fatsquirrels.deuzum.utils.math.APair;
import io.github.fatsquirrels.deuzum.visual.components.textAreaNoWrite;
import io.github.fatsquirrels.deuzum.visual.components.buttons.ActivatedButton;
import io.github.fatsquirrels.deuzum.visual.components.buttons.FlatButton;
import io.github.fatsquirrels.deuzum.visual.components.buttons.IconizedButton;
import io.github.fatsquirrels.deuzum.visual.style.CustomColors;
import io.github.fatsquirrels.deuzum.visual.style.layout.VerticalFlowLayout;
import net.miginfocom.swing.MigLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.ScrollPaneConstants;



public class HomePanel extends JPanel{

	private static final long serialVersionUID = -2649734373790386687L;

	
	private final String FAQ_URL = "https://github.com/futotta-risu/deuZum/wiki";
	private final String ABOUT_US_URL = "https://github.com/futotta-risu/deuZum/graphs/contributors";
	
	private JLabel lblNumBotsLimpieza;
	private JLabel lblNumBotsMail;
	private JLabel lblNumBotsUsuario;
	private JLabel lblNumBotsTransacion;
	private JLabel lblNumBotsCuenta;
	private JLabel lblNumBotsGrupo;
	private JLabel lblNumBotsProyecto;
	private JLabel lblNumBotsTransacionProyecto;

		
	private IconizedButton cleaningBotIcon;
	private IconizedButton mailBotIcon;
	private IconizedButton userBotIcon;
	private IconizedButton transactionBotIcon;
	private IconizedButton accountBotIcon;
	private IconizedButton groupBotIcon;
	private IconizedButton proyectBotIcon;
	private IconizedButton proyectTransactionBotIcon;

	

	private IconizedButton deleteCleaningIcon;
	private IconizedButton deleteMailIcon;
	private IconizedButton deleteUserIcon;
	private IconizedButton deleteTransactionIcon;
	private IconizedButton deleteAccountIcon;
	private IconizedButton deleteGroupIcon;
	private IconizedButton deleteProyectIcon;
	private IconizedButton deleteProyectTransactionIcon;


	private IconizedButton pauseCleaningIcon;
	private IconizedButton pauseMailIcon;
	private IconizedButton pauseUserIcon;
	private IconizedButton pauseTransactionIcon;
	private IconizedButton pauseAccountIcon;
	private IconizedButton pauseGroupIcon;
	private IconizedButton pauseProyectIcon;
	private IconizedButton pauseProyectTransactionIcon;

	
	private ArrayList<BotBase> cleaningBots;
	private ArrayList<BotBase> mailBots;
	private List<String> destinatarios;
	private APair<String, String> mensaje;
	private ArrayList<BotBase> userBots;
	private ArrayList<BotBase> transactionBots ;
	private ArrayList<BotBase> accountBots;
	private ArrayList<BotBase> groupBots;
	private ArrayList<BotBase> proyectBots;
	private ArrayList<BotBase> proyectTransactionBots; 


	
	private ActivatedButton actButtonCleaning;
	private ActivatedButton actButtonMail;
	private ActivatedButton actButtonUsuario;
	private ActivatedButton actButtonTransacion;
	private ActivatedButton actButtonAccount;
	private ActivatedButton actButtonGroup;
	private ActivatedButton actButtonProyect;
	private ActivatedButton actButtonProyectTransaction;



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
		setLayout(new MigLayout("fill,insets 0", "[455.00px,grow,center][278.00px,grow 60,center]", "[491.00px,grow]"));
		
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
		panel_Home_Lateral.setSize(500, 500);
		panel_Home_Lateral.setBackground(new Color(176, 224, 230));		
		panel_Home_Lateral.setLayout(new  VerticalFlowLayout(0,true));
		
		JPanel panel_Home_Lateral_Status = new JPanel();
		panel_Home_Lateral.add(panel_Home_Lateral_Status);
		panel_Home_Lateral_Status.setBackground(CustomColors.mBOrangeL);
		panel_Home_Lateral_Status.setLayout(new VerticalFlowLayout(10,10,10));
		
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
		
		JPanel panel_Home_Lateral_Bots = new JPanel();
		panel_Home_Lateral.add(panel_Home_Lateral_Bots);
		panel_Home_Lateral_Bots.setPreferredSize(new Dimension(200, 675));
		panel_Home_Lateral_Bots.setBackground(CustomColors.mBBlueGrayL);
		panel_Home_Lateral_Bots.setLayout(null);
		
		
		
		//////////////////////////////////////////////////////////
		//						   BOTS						    //
		//////////////////////////////////////////////////////////
		
		
		cleaningBots = new ArrayList<BotBase>();
		mailBots = new ArrayList<BotBase>();
		userBots = new ArrayList<BotBase>();
		transactionBots = new ArrayList<BotBase>();
		accountBots = new ArrayList<BotBase>();
		groupBots = new ArrayList<BotBase>();
		proyectBots = new ArrayList<BotBase>();
		proyectTransactionBots = new ArrayList<BotBase>();
		
		

		
		///////////////////		CLEANING	//////////////////////
		cleaningBotIcon = new IconizedButton("symbol","robot",35,40,e -> addCleaningBot());
		cleaningBotIcon.setBounds(30, 42, 59, 47);
		panel_Home_Lateral_Bots.add(cleaningBotIcon);
		cleaningBotIcon.setVisible(false);
			
		deleteCleaningIcon = new IconizedButton("symbol","borrar",35,40,e -> deleteCleaningBot());
		deleteCleaningIcon.setBounds(77, 51, 72, 29);
		panel_Home_Lateral_Bots.add(deleteCleaningIcon);
		deleteCleaningIcon.setVisible(false);
		
		pauseCleaningIcon = new IconizedButton("symbol","pauseBot",35,40,e -> pauseCleaningBot());
		pauseCleaningIcon.setBounds(141, 46, 65, 38);
		panel_Home_Lateral_Bots.add(pauseCleaningIcon);
		pauseCleaningIcon.setVisible(false);
					
		lblNumBotsLimpieza = new JLabel("0");
		lblNumBotsLimpieza.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumBotsLimpieza.setBounds(202, 51, 41, 40);
		panel_Home_Lateral_Bots.add(lblNumBotsLimpieza);
		lblNumBotsLimpieza.setVisible(false);
		
		
		///////////////////		MAIL	//////////////////////////
		mailBotIcon = new IconizedButton("symbol","bot",35,40,e -> addMailBot());
		mailBotIcon.setBounds(30, 122, 59, 47);
		panel_Home_Lateral_Bots.add(mailBotIcon);
		mailBotIcon.setVisible(false);
		
		deleteMailIcon = new IconizedButton("symbol","borrar",35,40,e -> deleteMailBot());
		deleteMailIcon.setBounds(77, 131, 72, 29);
		panel_Home_Lateral_Bots.add(deleteMailIcon);
		deleteMailIcon.setVisible(false);
		deleteMailIcon.setVisible(false);
		
		pauseMailIcon = new IconizedButton("symbol","pauseBot",35,40,e -> pauseMailBot());
		pauseMailIcon.setBounds(141, 126, 65, 38);
		panel_Home_Lateral_Bots.add(pauseMailIcon);
		pauseMailIcon.setVisible(false);
		
		lblNumBotsMail = new JLabel("0");
		lblNumBotsMail.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumBotsMail.setBounds(202, 131, 41, 29);
		panel_Home_Lateral_Bots.add(lblNumBotsMail);
		lblNumBotsMail.setVisible(false);
		
		
		/////////////////// 	USER	////////////////////////////
		userBotIcon = new IconizedButton("symbol","cyborg",35,40,e -> addUserBot());
		userBotIcon.setBounds(30, 202, 59, 47);
		panel_Home_Lateral_Bots.add(userBotIcon);
		userBotIcon.setVisible(false);
		
		deleteUserIcon = new IconizedButton("symbol","borrar",35,40,e -> deleteUserBot());
		deleteUserIcon.setBounds(77, 211, 72, 29);
		panel_Home_Lateral_Bots.add(deleteUserIcon);
		deleteUserIcon.setVisible(false);
		
		pauseUserIcon = new IconizedButton("symbol","pauseBot",35,40,e -> pauseUserBot());
		pauseUserIcon.setBounds(141, 206, 65, 38);
		panel_Home_Lateral_Bots.add(pauseUserIcon);
		pauseUserIcon.setVisible(false);
				
		lblNumBotsUsuario = new JLabel("0");
		lblNumBotsUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumBotsUsuario.setBounds(202, 211, 41, 29);
		panel_Home_Lateral_Bots.add(lblNumBotsUsuario);
		lblNumBotsUsuario.setVisible(false);
		
		
		///////////////////		TRANSACTION		/////////////////////
		transactionBotIcon = new IconizedButton("symbol","moneyBot",35,40,e -> addTransactionBot());
		transactionBotIcon.setBounds(30, 282, 59, 47);
		panel_Home_Lateral_Bots.add(transactionBotIcon);
		transactionBotIcon.setVisible(false);
		
		deleteTransactionIcon = new IconizedButton("symbol","borrar",35,40,e -> deleteTransactionBot());
		deleteTransactionIcon.setBounds(77, 291, 72, 29);
		panel_Home_Lateral_Bots.add(deleteTransactionIcon);
		deleteTransactionIcon.setVisible(false);
		
		pauseTransactionIcon = new IconizedButton("symbol","pauseBot",35,40,e -> pauseTransactionBot());
		pauseTransactionIcon.setBounds(141, 286, 65, 38);
		panel_Home_Lateral_Bots.add(pauseTransactionIcon);
		pauseTransactionIcon.setVisible(false);
			
		lblNumBotsTransacion = new JLabel("0");
		lblNumBotsTransacion.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumBotsTransacion.setBounds(202, 291, 41, 27);
		panel_Home_Lateral_Bots.add(lblNumBotsTransacion);
		lblNumBotsTransacion.setVisible(false);
		
		
		/////////////////////	 ACCOUNT 	/////////////////////////////
		accountBotIcon = new IconizedButton("symbol","creditCard",35,40,e -> addAccountBot());
		accountBotIcon.setBounds(30, 362, 59, 47);
		panel_Home_Lateral_Bots.add(accountBotIcon);
		accountBotIcon.setVisible(false);
		
		deleteAccountIcon = new IconizedButton("symbol","borrar",35,40,e -> deleteAccountBot());
		deleteAccountIcon.setBounds(77, 371, 72, 29);
		panel_Home_Lateral_Bots.add(deleteAccountIcon);
		deleteAccountIcon.setVisible(false);
		
		pauseAccountIcon = new IconizedButton("symbol","pauseBot",35,40,e -> pauseAccountBot());
		pauseAccountIcon.setBounds(141, 366, 65, 38);
		panel_Home_Lateral_Bots.add(pauseAccountIcon);
		pauseAccountIcon.setVisible(false);
			
		lblNumBotsCuenta = new JLabel("0");
		lblNumBotsCuenta.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumBotsCuenta.setBounds(202, 371, 41, 27);
		panel_Home_Lateral_Bots.add(lblNumBotsCuenta);
		lblNumBotsCuenta.setVisible(false);
		
		
		/////////////////////	  GROUP    //////////////////////////////////
		groupBotIcon = new IconizedButton("symbol","group",35,40,e -> addGroupBot());
		groupBotIcon.setBounds(30, 442, 59, 47);
		panel_Home_Lateral_Bots.add(groupBotIcon);
		groupBotIcon.setVisible(false);
		
		deleteGroupIcon = new IconizedButton("symbol","borrar",35,40,e -> deleteGroupBot());
		deleteGroupIcon.setBounds(77, 451, 72, 29);
		panel_Home_Lateral_Bots.add(deleteGroupIcon);
		deleteGroupIcon.setVisible(false);
		
		pauseGroupIcon = new IconizedButton("symbol","pauseBot",35,40,e -> pauseGroupBot());
		pauseGroupIcon.setBounds(141, 446, 65, 38);
		panel_Home_Lateral_Bots.add(pauseGroupIcon);
		pauseGroupIcon.setVisible(false);
			
		lblNumBotsGrupo = new JLabel("0");
		lblNumBotsGrupo.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumBotsGrupo.setBounds(202, 451, 41, 27);
		panel_Home_Lateral_Bots.add(lblNumBotsGrupo);
		lblNumBotsGrupo.setVisible(false);
		
		
		////////////////////// 	   PROYECT     ///////////////////////////////
		proyectBotIcon = new IconizedButton("symbol","proyecto",35,40,e -> addProyectBot());
		proyectBotIcon.setBounds(30, 522, 59, 47);
		panel_Home_Lateral_Bots.add(proyectBotIcon);
		proyectBotIcon.setVisible(false);
		
		deleteProyectIcon = new IconizedButton("symbol","borrar",35,40,e -> deleteProyectBot());
		deleteProyectIcon.setBounds(77, 531, 72, 29);
		panel_Home_Lateral_Bots.add(deleteProyectIcon);
		deleteProyectIcon.setVisible(false);
		
		pauseProyectIcon = new IconizedButton("symbol","pauseBot",35,40,e -> pauseProyectBot());
		pauseProyectIcon.setBounds(141, 526, 65, 38);
		panel_Home_Lateral_Bots.add(pauseProyectIcon);
		pauseProyectIcon.setVisible(false);
			
		lblNumBotsProyecto = new JLabel("0");
		lblNumBotsProyecto.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumBotsProyecto.setBounds(202, 531, 41, 27);
		panel_Home_Lateral_Bots.add(lblNumBotsProyecto);
		lblNumBotsProyecto.setVisible(false);
		
		
		//////////////////    TRANSACION PROYECTO    ////////////////////////////
		proyectTransactionBotIcon = new IconizedButton("symbol","proyectTransaction",35,40,e -> addProyectTransactionBot());
		proyectTransactionBotIcon.setBounds(30, 602, 59, 47);
		panel_Home_Lateral_Bots.add(proyectTransactionBotIcon);
		proyectTransactionBotIcon.setVisible(false);
		
		deleteProyectTransactionIcon = new IconizedButton("symbol","borrar",35,40,e -> deleteProyectTransactionBot());
		deleteProyectTransactionIcon.setBounds(77, 611, 72, 29);
		panel_Home_Lateral_Bots.add(deleteProyectTransactionIcon);
		deleteProyectTransactionIcon.setVisible(false);
		
		pauseProyectTransactionIcon = new IconizedButton("symbol","pauseBot",35,40,e -> pauseProyectTransactionBot());
		pauseProyectTransactionIcon.setBounds(141, 606, 65, 38);
		panel_Home_Lateral_Bots.add(pauseProyectTransactionIcon);
		pauseProyectTransactionIcon.setVisible(false);
			
		lblNumBotsTransacionProyecto = new JLabel("0");
		lblNumBotsTransacionProyecto.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumBotsTransacionProyecto.setBounds(202, 611, 41, 27);
		panel_Home_Lateral_Bots.add(lblNumBotsTransacionProyecto);
		lblNumBotsTransacionProyecto.setVisible(false);
		
		
		
		
		/////////////////ACTIVATED BUTTONS////////////////////////////////		
		
		actButtonCleaning = new ActivatedButton("Activar Bot Limpieza");
		actButtonCleaning.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refreshComponentsCleaning();
			}
		});
		actButtonCleaning.setSize(223, 29);
		actButtonCleaning.setLocation(20, 10);
		panel_Home_Lateral_Bots.add(actButtonCleaning);
		actButtonCleaning.setVisible(false);
		
		
		actButtonMail = new ActivatedButton("Activar Bot Mail");
		actButtonMail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refreshComponentsMail();
			}
		});
		actButtonMail.setSize(223, 29);
		actButtonMail.setLocation(20, 90);
		panel_Home_Lateral_Bots.add(actButtonMail);
		actButtonMail.setVisible(false);
		
		
		actButtonUsuario = new ActivatedButton("Activar Bot Usuario");
		actButtonUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refreshComponentsUsuario();
			}
		});
		actButtonUsuario.setSize(223, 29);
		actButtonUsuario.setLocation(20, 170);
		panel_Home_Lateral_Bots.add(actButtonUsuario);
		actButtonUsuario.setVisible(false);
		
		
		actButtonTransacion = new ActivatedButton("Activar Bot Transacion");
		actButtonTransacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refreshComponentsTransacion();
			}
		});
		actButtonTransacion.setSize(223, 29);
		actButtonTransacion.setLocation(20, 250);
		panel_Home_Lateral_Bots.add(actButtonTransacion);
		actButtonTransacion.setVisible(false);
		
		actButtonAccount = new ActivatedButton("Activar Bot Cuenta");
		actButtonAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refreshComponentsAccount();
			}
		});
		actButtonAccount.setSize(223, 29);
		actButtonAccount.setLocation(20, 330);
		panel_Home_Lateral_Bots.add(actButtonAccount);
		actButtonAccount.setVisible(false);
		
		
		actButtonGroup = new ActivatedButton("Activar Bot Grupo");
		actButtonGroup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refreshComponentsGroup();
			}
		});
		actButtonGroup.setSize(223, 29);
		actButtonGroup.setLocation(20, 410);
		panel_Home_Lateral_Bots.add(actButtonGroup);
		actButtonGroup.setVisible(false);
		
		
		actButtonProyect = new ActivatedButton("Activar Bot Proyecto");
		actButtonProyect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refreshComponentsProyect();
			}
		});
		actButtonProyect.setSize(223, 29);
		actButtonProyect.setLocation(20, 490);
		panel_Home_Lateral_Bots.add(actButtonProyect);
		actButtonProyect.setVisible(false);
		
		
		actButtonProyectTransaction = new ActivatedButton("Activar Bot Transacion Proyecto");
		actButtonProyectTransaction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refreshComponentsTransacionProyecto();
			}
		});
		actButtonProyectTransaction.setSize(223, 29);
		actButtonProyectTransaction.setLocation(20, 570);
		panel_Home_Lateral_Bots.add(actButtonProyectTransaction);
		actButtonProyectTransaction.setVisible(false);
		
		
		JScrollPane scrollPanel_Lateral = new JScrollPane(panel_Home_Lateral,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
	            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPanel_Lateral.setBorder(new EmptyBorder(0, 0, 0, 0));
		add(scrollPanel_Lateral, "cell 1 0,grow");
		
		
		
		panel_Home_Info.add(panel_Home_Info_Botones, BorderLayout.SOUTH);
		panel_Home_Info.add(tf_Software_Info, BorderLayout.EAST);
		panel_Home_Info.add(tf_Information, BorderLayout.NORTH);
		
		panel_Home_Center.add(panel_Home_Info,BorderLayout.CENTER);
		panel_Home_Center.add(panel_Home_Title, BorderLayout.NORTH);
		
		add(panel_Home_Center, "cell 0 0,grow");
		
	}
	
	

	private void pauseProyectTransactionBot() {
		Thread hiloPauseProyectTransaction = new Thread(new Runnable() {

			public void run() {
				if(proyectTransactionBots.isEmpty()) {
					JOptionPane.showMessageDialog(null, "No hay ningun bot de Proyecto en ejecucion","ERROR" , 1);
				}else {
					int tiempo = Integer.parseInt(JOptionPane.showInputDialog("Introduce el tiempo de pausa:"));
					for (BotBase botBase : proyectTransactionBots) {
						botBase.stop(tiempo*1000);;
					}	
				}
			}
		});
		hiloPauseProyectTransaction.run();		
	}

	
	private void pauseProyectBot() {
		Thread hiloPauseProyect = new Thread(new Runnable() {

			public void run() {
				if(proyectBots.isEmpty()) {
					JOptionPane.showMessageDialog(null, "No hay ningun bot de Proyecto en ejecucion","ERROR" , 1);
				}else {
					int tiempo = Integer.parseInt(JOptionPane.showInputDialog("Introduce el tiempo de pausa:"));
					for (BotBase botBase : proyectBots) {
						botBase.stop(tiempo*1000);;
					}	
				}
			}
		});
		hiloPauseProyect.run();	
	}

	
	private void pauseGroupBot() {
		Thread hiloPauseGroup = new Thread(new Runnable() {

			public void run() {
				if(groupBots.isEmpty()) {
					JOptionPane.showMessageDialog(null, "No hay ningun bot de Grupo en ejecucion","ERROR" , 1);
				}else {
					int tiempo = Integer.parseInt(JOptionPane.showInputDialog("Introduce el tiempo de pausa:"));
					for (BotBase botBase : groupBots) {
						botBase.stop(tiempo*1000);;
					}	
				}
			}
		});
		hiloPauseGroup.run();		
	}


	private void pauseAccountBot() {
		Thread hiloPauseAccount = new Thread(new Runnable() {

			public void run() {
				if(accountBots.isEmpty()) {
					JOptionPane.showMessageDialog(null, "No hay ningun bot de Cuenta en ejecucion","ERROR" , 1);
				}else {
					int tiempo = Integer.parseInt(JOptionPane.showInputDialog("Introduce el tiempo de pausa:"));
					for (BotBase botBase : accountBots) {
						botBase.stop(tiempo*1000);;
					}	
				}
			}
		});
		hiloPauseAccount.run();	
	}

	
	private void pauseTransactionBot() {
		Thread hiloPauseTransaction = new Thread(new Runnable() {

			public void run() {
				if(transactionBots.isEmpty()) {
					JOptionPane.showMessageDialog(null, "No hay ningun bot de Transacion en ejecucion","ERROR" , 1);
				}else {
					int tiempo = Integer.parseInt(JOptionPane.showInputDialog("Introduce el tiempo de pausa:"));
					for (BotBase botBase : transactionBots) {
						botBase.stop(tiempo*1000);;
					}	
				}
			}
		});
		hiloPauseTransaction.run();
	}

	private void pauseUserBot() {
		Thread hiloPauseUser = new Thread(new Runnable() {

			public void run() {
				if(userBots.isEmpty()) {
					JOptionPane.showMessageDialog(null, "No hay ningun bot de Usuario en ejecucion","ERROR" , 1);
				}else {
					int tiempo = Integer.parseInt(JOptionPane.showInputDialog("Introduce el tiempo de pausa:"));
					for (BotBase botBase : userBots) {
						botBase.stop(tiempo*1000);;
					}	
				}
			}
		});
		hiloPauseUser.run();	
	}

	private void pauseMailBot() {
		Thread hiloPauseMail = new Thread(new Runnable() {

			public void run() {
				if(mailBots.isEmpty()) {
					JOptionPane.showMessageDialog(null, "No hay ningun bot de Mail en ejecucion","ERROR" , 1);
				}else {
					int tiempo = Integer.parseInt(JOptionPane.showInputDialog("Introduce el tiempo de pausa:"));
					for (BotBase botBase : mailBots) {
						botBase.stop(tiempo*1000);;
					}
				}
			}
		});
		hiloPauseMail.run();	}

	private void pauseCleaningBot() {
		Thread hiloPauseCleaning = new Thread(new Runnable() {

			public void run() {
				if(cleaningBots.isEmpty()) {
					JOptionPane.showMessageDialog(null, "No hay ningun bot de Limpieza en ejecucion","ERROR" , 1);
				}else {
					int tiempo = Integer.parseInt(JOptionPane.showInputDialog("Introduce el tiempo de pausa:"));
					for (BotBase botBase : cleaningBots) {
						botBase.stop(tiempo*1000);;
					}	
				}
			}
		});
		hiloPauseCleaning.run();	
	}
	
	private void deleteProyectBot() {
		Thread hiloBorrarProyecto = new Thread(new Runnable() {
			public void run() {
				if(!proyectBots.isEmpty()) {
				for (BotBase botBase : proyectBots) {
					botBase.kill();
					proyectBots.remove(botBase);
				}
				lblNumBotsGrupo.setText(0+"");	
				}
			}
		});
		hiloBorrarProyecto.run();	}

	
	

	private void deleteProyectTransactionBot() {
		Thread hiloBorrarTransacionProyecto = new Thread(new Runnable() {
			public void run() {
				for (BotBase botBase : proyectTransactionBots) {
					botBase.kill();
					proyectTransactionBots.remove(botBase);
				}
				lblNumBotsGrupo.setText(0+"");				
			}
		});
		hiloBorrarTransacionProyecto.run();	}
	
	
	private void deleteGroupBot() {
		Thread hiloBorrarGrupo = new Thread(new Runnable() {
			public void run() {
				for (BotBase botBase : groupBots) {
					botBase.kill();
					groupBots.remove(botBase);
				}
				lblNumBotsGrupo.setText(0+"");				
			}
		});
		hiloBorrarGrupo.run();
	}

	
	private void deleteTransactionBot() {
		Thread hiloBorrarTransacion = new Thread(new Runnable() {
			public void run() {
				for (BotBase botBase : transactionBots) {
					botBase.kill();
					transactionBots.remove(botBase);
				}
				lblNumBotsTransacion.setText(0+"");				
			}
		});
		hiloBorrarTransacion.run();
		
	}

	private void deleteUserBot() {
		Thread hiloBorrarUser = new Thread(new Runnable() {
			public void run() {
				for (BotBase botBase : userBots) {
					botBase.kill();
					userBots.remove(botBase);
				}
				lblNumBotsUsuario.setText(0+"");				
			}
		});
		hiloBorrarUser.run();
		
	}

	private void deleteMailBot() {
		Thread hiloBorrarMail = new Thread(new Runnable() {
			public void run() {
				for (BotBase botBase : mailBots) {
					botBase.kill();
					mailBots.remove(botBase);
				}
				lblNumBotsMail.setText(0+"");				
			}
		});
		hiloBorrarMail.run();
		

	}

	private void deleteCleaningBot() {
		Thread hiloBorrarCleaning = new Thread(new Runnable() {
			
			@Override
			public void run() {
				for (BotBase botBase : cleaningBots) {
					botBase.kill();
					cleaningBots.remove(botBase);
				}
				lblNumBotsLimpieza.setText(0+"");				
			}
		});
		hiloBorrarCleaning.run();
	}
	private void deleteAccountBot() {
		Thread hiloBorrarCuenta = new Thread(new Runnable() {
			public void run() {
				for (BotBase botBase : accountBots) {
					botBase.kill();
					accountBots.remove(botBase);
				}
				lblNumBotsCuenta.setText(0+"");				
			}
		});
		hiloBorrarCuenta.run();	
	}
	
	private void addProyectTransactionBot() {
		Thread hiloProyectTransacyion = new Thread(new Runnable() {
			public void run() {
				int cantTransacionesProyecto = Integer.parseInt(JOptionPane.showInputDialog("Introduce la cantidad de Transaciones de Proyecto"));
				int count = Integer.parseInt(lblNumBotsTransacionProyecto.getText());
				ProyectTransactionBot ptb = new ProyectTransactionBot("ProyectTransactionBot" + count, cantTransacionesProyecto);
				transactionBots.add(ptb);
				count = count +1;
				lblNumBotsTransacionProyecto.setText(count + "");
				ptb.execute();				
			}
		});
		hiloProyectTransacyion.run();	
	}

	
	private void addProyectBot() {
		Thread hiloProyecto = new Thread(new Runnable() {
			public void run() {
				int cantProyectos = Integer.parseInt(JOptionPane.showInputDialog("Introduce la cantidad de Proyectos"));
				int count = Integer.parseInt(lblNumBotsProyecto.getText());
				ProyectBot pb = new ProyectBot("ProyectBot" + count, cantProyectos);
				groupBots.add(pb);
				count = count +1;
				lblNumBotsProyecto.setText(count + "");
				pb.execute();				
			}
		});
		hiloProyecto.run();
	}

	private void addGroupBot() {
		Thread hiloGrupo = new Thread(new Runnable() {
			public void run() {
				int cantGrupos = Integer.parseInt(JOptionPane.showInputDialog("Introduce la cantidad de Grupos"));
				int count = Integer.parseInt(lblNumBotsGrupo.getText());
				GroupBot gb = new GroupBot("GroupBot" + count, cantGrupos);
				groupBots.add(gb);
				count = count +1;
				lblNumBotsGrupo.setText(count + "");
				gb.execute();				
			}
		});
		hiloGrupo.run();
	}

	private void addAccountBot() {
		Thread hiloCuentas = new Thread(new Runnable() {
			public void run() {
				int cantCuentas = Integer.parseInt(JOptionPane.showInputDialog("Introduce la cantidad de Cuentas"));
				int count = Integer.parseInt(lblNumBotsTransacion.getText());
				AccountBot ab = new AccountBot("AccountBot" + count, cantCuentas);
				accountBots.add(ab);
				count = count +1;
				lblNumBotsCuenta.setText(count + "");
				ab.execute();				
			}
		});
		hiloCuentas.run();
	}

	private void addTransactionBot() {
		Thread hiloTransacion = new Thread(new Runnable() {
			public void run() {
				int cantTransaciones = Integer.parseInt(JOptionPane.showInputDialog("Introduce la cantidad de Transaciones"));
				int count = Integer.parseInt(lblNumBotsTransacion.getText());
				TransactionBot tb = new TransactionBot("TransactionBot" + count, cantTransaciones);
				transactionBots.add(tb);
				count = count +1;
				lblNumBotsTransacion.setText(count + "");
				tb.execute();				
			}
		});
		hiloTransacion.run();	
	}

	private void addUserBot() {
		Thread hiloUser = new Thread(new Runnable() {
			public void run() {
				int cantUsuarios = Integer.parseInt(JOptionPane.showInputDialog("Introduce la cantidad de usuarios"));
				int count = Integer.parseInt(lblNumBotsUsuario.getText());
				UserBot ub = new UserBot(cantUsuarios);
				userBots.add(ub);
				count = count +1;
				lblNumBotsUsuario.setText(count + "");
				ub.execute();
			}
		});
		hiloUser.run();
	}

	private void addCleaningBot() {
		Thread hiloClean = new Thread(new Runnable() {
			public void run() {
				int count = Integer.parseInt(lblNumBotsLimpieza.getText());
				//BotBase cb = BotGenerator.generateBot(BotType.CleaningBot, "Cleaning Bot " + count);
				CleaningBot cb = new CleaningBot("CleaninBot" + count);
				cleaningBots.add(cb);
				count = count +1;
				lblNumBotsLimpieza.setText(count + "");
				cb.execute();
			}
		});
		hiloClean.run();
		
	}

	private void addMailBot() {
		Thread hiloMail = new Thread(new Runnable() {
			
			@Override
			public void run() {
				destinatarios = new ArrayList<String>();
				FileFilter filter = new FileNameExtensionFilter("TXT File","txt");
				JFileChooser jfc = new JFileChooser();
				jfc.setDialogTitle("Elegir el archivo con los destinatarios");
				jfc.addChoosableFileFilter(filter);
				jfc.showOpenDialog(null);
				File selected = jfc.getSelectedFile();
				
				FileReader f = null;
				BufferedReader b = null;
				try {
					f = new FileReader(selected);
					b = new BufferedReader(f);
					 try {
						String linea = b.readLine();
						while(linea!=null) {
							destinatarios.add(linea);
							linea = b.readLine();
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}finally {			
					try {
						b.close();
						f.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
					
				}
				
				
				FileFilter filter2 = new FileNameExtensionFilter("TXT File","txt");
				JFileChooser jfc2 = new JFileChooser();
				jfc2.setDialogTitle("Elegir el archivo con el Mensaje");
				jfc2.addChoosableFileFilter(filter2);
				jfc2.showOpenDialog(null);
				File selected2 = jfc2.getSelectedFile();
				
				FileReader f2 = null;
				BufferedReader b2 = null;
				String cabecera = "";
				String cuerpo = "";
				
				try {
					f2 = new FileReader(selected2);
					b2 = new BufferedReader(f2);
					 try {
						String linea = b2.readLine();
						while(linea!=null) {
							cabecera = linea;
							linea = b2.readLine();
							while(linea!=null) {
								cuerpo = cuerpo.concat(linea + "\n");
								linea = b2.readLine();
							}
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}finally {
					try {
						b2.close();
						f2.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				
				mensaje = new APair<String,String>(cabecera, cuerpo);
				
				int count = Integer.parseInt(lblNumBotsMail.getText());
				MailBot mb = new MailBot("MailBot" + count, mensaje, destinatarios);
				mailBots.add(mb);
				count = count +1;
				lblNumBotsMail.setText(count + "");		
				mb.execute();			
			}
		});
		hiloMail.run();
	
	}

	public void changeServerStatus(StatusType s) {
		this.stType = s;
		serverLabel.setText("Status: " + stType.getStatus());
		revalidate();
		if(stType.getStatus().equals("Running")) {
			actButtonCleaning.setVisible(true);
			actButtonMail.setVisible(true);
			actButtonUsuario.setVisible(true);
			actButtonTransacion.setVisible(true);
			actButtonAccount.setVisible(true);
			actButtonGroup.setVisible(true);
			actButtonProyect.setVisible(true);
			actButtonProyectTransaction.setVisible(true);
		}else {
			actButtonCleaning.setVisible(false);
			actButtonCleaning.setSelected(false);
			actButtonMail.setVisible(false);
			actButtonMail.setSelected(false);
			actButtonUsuario.setVisible(false);
			actButtonUsuario.setSelected(false);
			actButtonTransacion.setVisible(false);
			actButtonTransacion.setSelected(false);
			actButtonAccount.setVisible(false);
			actButtonAccount.setSelected(false);
			actButtonGroup.setVisible(false);
			actButtonGroup.setSelected(false);
			actButtonProyect.setVisible(false);
			actButtonProyect.setSelected(false);
			actButtonProyectTransaction.setVisible(false);
			actButtonProyectTransaction.setSelected(false);
			deleteCleaningBot();
			deleteMailBot();
			deleteUserBot();
			deleteTransactionBot();
			deleteAccountBot();
			deleteGroupBot();
			deleteProyectBot();
			deleteProyectTransactionBot();
			refreshComponentsCleaning();
			refreshComponentsMail();
			refreshComponentsUsuario();
			refreshComponentsTransacion();
			refreshComponentsAccount();
			refreshComponentsGroup();
			refreshComponentsProyect();
			refreshComponentsTransacionProyecto();
		}
		
	}
	
	public void refreshComponentsCleaning() {
		if(actButtonCleaning.isSelected()) {
			actButtonCleaning.setText("Desactivar Bot Limpieza");
			actButtonCleaning.setBackground(CustomColors.mBGreenLight);
			cleaningBotIcon.setVisible(true);
			deleteCleaningIcon.setVisible(true);
			pauseCleaningIcon.setVisible(true);
			lblNumBotsLimpieza.setVisible(true);
		}else {
			actButtonCleaning.setText("Activar Bot Limpieza");
			actButtonCleaning.setBackground(CustomColors.mBRedLight);
			lblNumBotsLimpieza.setText(0+"");
			cleaningBotIcon.setVisible(false);
			deleteCleaningIcon.setVisible(false);
			pauseCleaningIcon.setVisible(false);
			lblNumBotsLimpieza.setVisible(false);
		}
	}
	
	public void refreshComponentsMail() {
		if(actButtonMail.isSelected()) {
			actButtonMail.setText("Desactivar Bot Mail");
			actButtonMail.setBackground(CustomColors.mBGreenLight);
			mailBotIcon.setVisible(true);
			deleteMailIcon.setVisible(true);
			pauseMailIcon.setVisible(true);
			lblNumBotsMail.setVisible(true);
		}else {
			actButtonMail.setText("Activar Bot Mail");
			actButtonMail.setBackground(CustomColors.mBRedLight);
			lblNumBotsMail.setText(0+"");
			mailBotIcon.setVisible(false);
			deleteMailIcon.setVisible(false);
			pauseMailIcon.setVisible(false);
			lblNumBotsMail.setVisible(false);
		}
	}
	
	public void refreshComponentsUsuario() {
		if(actButtonUsuario.isSelected()) {
			actButtonUsuario.setText("Desactivar Bot Usuario");
			actButtonUsuario.setBackground(CustomColors.mBGreenLight);
			userBotIcon.setVisible(true);
			deleteUserIcon.setVisible(true);
			pauseUserIcon.setVisible(true);
			lblNumBotsUsuario.setVisible(true);
		}else {
			actButtonUsuario.setText("Activar Bot Usuario");
			actButtonUsuario.setBackground(CustomColors.mBRedLight);
			lblNumBotsUsuario.setText(0+"");
			userBotIcon.setVisible(false);
			deleteUserIcon.setVisible(false);
			pauseUserIcon.setVisible(false);
			lblNumBotsUsuario.setVisible(false);
		}
	}
	
	public void refreshComponentsTransacion() {
		if(actButtonTransacion.isSelected()) {
			actButtonTransacion.setText("Desactivar Bot Transacion");
			actButtonTransacion.setBackground(CustomColors.mBGreenLight);
			transactionBotIcon.setVisible(true);
			deleteTransactionIcon.setVisible(true);
			pauseTransactionIcon.setVisible(true);
			lblNumBotsTransacion.setVisible(true);
		}else {
			actButtonTransacion.setText("Activar Bot Transacion");
			actButtonTransacion.setBackground(CustomColors.mBRedLight);
			lblNumBotsTransacion.setText(0+"");
			transactionBotIcon.setVisible(false);
			deleteTransactionIcon.setVisible(false);
			pauseTransactionIcon.setVisible(false);
			lblNumBotsTransacion.setVisible(false);
		}
	}
	
	public void refreshComponentsAccount() {
		if(actButtonAccount.isSelected()) {
			actButtonAccount.setText("Desactivar Bot Cuenta");
			actButtonAccount.setBackground(CustomColors.mBGreenLight);
			accountBotIcon.setVisible(true);
			deleteAccountIcon.setVisible(true);
			pauseAccountIcon.setVisible(true);
			lblNumBotsCuenta.setVisible(true);
		}else {
			actButtonAccount.setText("Activar Bot Cuenta");
			actButtonAccount.setBackground(CustomColors.mBRedLight);
			lblNumBotsTransacion.setText(0+"");
			accountBotIcon.setVisible(false);
			deleteAccountIcon.setVisible(false);
			pauseAccountIcon.setVisible(false);
			lblNumBotsCuenta.setVisible(false);
		}
	}
	
	public void refreshComponentsGroup() {
		if(actButtonGroup.isSelected()) {
			actButtonGroup.setText("Desactivar Bot Grupo");
			actButtonGroup.setBackground(CustomColors.mBGreenLight);
			groupBotIcon.setVisible(true);
			deleteGroupIcon.setVisible(true);
			pauseGroupIcon.setVisible(true);
			lblNumBotsGrupo.setVisible(true);
		}else {
			actButtonGroup.setText("Activar Bot Grupo");
			actButtonGroup.setBackground(CustomColors.mBRedLight);
			lblNumBotsGrupo.setText(0+"");
			groupBotIcon.setVisible(false);
			deleteGroupIcon.setVisible(false);
			pauseGroupIcon.setVisible(false);
			lblNumBotsGrupo.setVisible(false);
		}
	}
	
	public void refreshComponentsProyect() {
		if(actButtonProyect.isSelected()) {
			actButtonProyect.setText("Desactivar Bot Proyecto");
			actButtonProyect.setBackground(CustomColors.mBGreenLight);
			proyectBotIcon.setVisible(true);
			deleteProyectIcon.setVisible(true);
			pauseProyectIcon.setVisible(true);
			lblNumBotsProyecto.setVisible(true);
		}else {
			actButtonProyect.setText("Activar Bot Proyecto");
			actButtonGroup.setBackground(CustomColors.mBRedLight);
			lblNumBotsProyecto.setText(0+"");
			proyectBotIcon.setVisible(false);
			deleteProyectIcon.setVisible(false);
			pauseProyectIcon.setVisible(false);
			lblNumBotsProyecto.setVisible(false);
		}
	}
	
	public void refreshComponentsTransacionProyecto() {
		if(actButtonProyectTransaction.isSelected()) {
			actButtonProyectTransaction.setText("Desactivar Bot Transacion Proyecto");
			actButtonProyectTransaction.setBackground(CustomColors.mBGreenLight);
			proyectTransactionBotIcon.setVisible(true);
			deleteProyectTransactionIcon.setVisible(true);
			pauseProyectTransactionIcon.setVisible(true);
			lblNumBotsTransacionProyecto.setVisible(true);
		}else {
			actButtonProyectTransaction.setText("Activar Bot Transacion proyecto");
			actButtonProyectTransaction.setBackground(CustomColors.mBRedLight);
			lblNumBotsTransacionProyecto.setText(0+"");
			proyectTransactionBotIcon.setVisible(false);
			deleteProyectTransactionIcon.setVisible(false);
			pauseProyectTransactionIcon.setVisible(false);
			lblNumBotsTransacionProyecto.setVisible(false);
		}
	}
	
}
