package io.github.fatsquirrels.deuzum.visual.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
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

import io.github.fatsquirrels.deuzum.IA.bots.BotBase;
import io.github.fatsquirrels.deuzum.IA.bots.BotGenerator;
import io.github.fatsquirrels.deuzum.IA.bots.BotType;
import io.github.fatsquirrels.deuzum.IA.bots.CleaningBot;
import io.github.fatsquirrels.deuzum.IA.bots.MailBot;
import io.github.fatsquirrels.deuzum.utils.WebpageConnection;
import io.github.fatsquirrels.deuzum.utils.math.APair;
import io.github.fatsquirrels.deuzum.visual.Style.CustomColors;
import io.github.fatsquirrels.deuzum.visual.Style.Layout.VerticalFlowLayout;
import io.github.fatsquirrels.deuzum.visual.components.textAreaNoWrite;
import io.github.fatsquirrels.deuzum.visual.components.buttons.ActivatedButton;
import io.github.fatsquirrels.deuzum.visual.components.buttons.FlatButton;
import io.github.fatsquirrels.deuzum.visual.components.buttons.IconizedButton;
import net.miginfocom.swing.MigLayout;
import javax.swing.JToggleButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;



public class HomePanel extends JPanel{

	private static final long serialVersionUID = -2649734373790386687L;

	
	private final String FAQ_URL = "https://github.com/futotta-risu/deuZum/wiki";
	private final String ABOUT_US_URL = "https://github.com/futotta-risu/deuZum/graphs/contributors";
	
	private JLabel lblNumBotsLimpieza;
	private JLabel lblNumBotsMail;
	private JLabel lblNumBotsUsuario;
	private JLabel lblNumBotsTransacion;
		
	private IconizedButton cleaningBotIcon;
	private IconizedButton mailBotIcon;
	private IconizedButton userBotIcon;
	private IconizedButton transactionBotIcon;
	
	private IconizedButton deleteCleaningIcon;
	private IconizedButton deleteMailIcon;
	private IconizedButton deleteUserIcon;
	private IconizedButton deleteTransactionIcon;
	
	private ArrayList<BotBase> cleaningBots;
	private ArrayList<BotBase> mailBots;
	private List<String> destinatarios;
	private APair<String, String> mensaje;
	private ArrayList<BotBase> userBots;
	private ArrayList<BotBase> transactionBots;
	
	private ActivatedButton actButtonCleaning;
	private ActivatedButton actButtonMail;
	private ActivatedButton actButtonUsuario;
	private ActivatedButton actButtonTransacion;

	
	
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
		
		JPanel panel_Home_Lateral_Help = new JPanel();
		panel_Home_Lateral.add(panel_Home_Lateral_Help);
		panel_Home_Lateral_Help.setPreferredSize(new Dimension(200,500));
		panel_Home_Lateral_Help.setBackground(CustomColors.mBBlueGrayL);
		panel_Home_Lateral_Help.setLayout(null);
		
		
		
		//////////////////////////////////////////////////////////
		//						   BOTS						    //
		//////////////////////////////////////////////////////////
		
		cleaningBots = new ArrayList<BotBase>();
		mailBots = new ArrayList<BotBase>();
		userBots = new ArrayList<BotBase>();
		transactionBots = new ArrayList<BotBase>();

		
		///////////////////		CLEANING	//////////////////////
		cleaningBotIcon = new IconizedButton("symbol","robot",35,40,e -> addCleaningBot());
		cleaningBotIcon.setBounds(30, 42, 59, 47);
		panel_Home_Lateral_Help.add(cleaningBotIcon);
		cleaningBotIcon.setVisible(false);
			
		deleteCleaningIcon = new IconizedButton("symbol","borrar",35,40,e -> deleteCleaningBot());
		deleteCleaningIcon.setBounds(99, 51, 72, 29);
		panel_Home_Lateral_Help.add(deleteCleaningIcon);
		deleteCleaningIcon.setVisible(false);
				
		lblNumBotsLimpieza = new JLabel("0");
		lblNumBotsLimpieza.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumBotsLimpieza.setBounds(202, 51, 41, 29);
		panel_Home_Lateral_Help.add(lblNumBotsLimpieza);
		lblNumBotsLimpieza.setVisible(false);
		
		
		///////////////////		MAIL	//////////////////////////
		mailBotIcon = new IconizedButton("symbol","bot",35,40,e -> addMailBot());
		mailBotIcon.setBounds(30, 120, 59, 47);
		panel_Home_Lateral_Help.add(mailBotIcon);
		mailBotIcon.setVisible(false);
		
		deleteMailIcon = new IconizedButton("symbol","borrar",35,40,e -> deleteMailBot());
		deleteMailIcon.setBounds(99, 136, 72, 29);
		panel_Home_Lateral_Help.add(deleteMailIcon);
		deleteMailIcon.setVisible(false);
		deleteMailIcon.setVisible(false);
		
		lblNumBotsMail = new JLabel("0");
		lblNumBotsMail.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumBotsMail.setBounds(202, 136, 41, 22);
		panel_Home_Lateral_Help.add(lblNumBotsMail);
		lblNumBotsMail.setVisible(false);
		
		
		/////////////////// 	USER	////////////////////////////
		userBotIcon = new IconizedButton("symbol","cyborg",35,40,e -> addUserBot());
		userBotIcon.setBounds(30, 202, 59, 47);
		panel_Home_Lateral_Help.add(userBotIcon);
		userBotIcon.setVisible(false);
		
		deleteUserIcon = new IconizedButton("symbol","borrar",35,40,e -> deleteUserBot());
		deleteUserIcon.setBounds(99, 220, 72, 29);
		panel_Home_Lateral_Help.add(deleteUserIcon);
		deleteUserIcon.setVisible(false);
				
		lblNumBotsUsuario = new JLabel("0");
		lblNumBotsUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumBotsUsuario.setBounds(202, 220, 41, 29);
		panel_Home_Lateral_Help.add(lblNumBotsUsuario);
		lblNumBotsUsuario.setVisible(false);
		
		
		///////////////////		TRANSACTION		/////////////////////
		transactionBotIcon = new IconizedButton("symbol","moneyBot",35,40,e -> addTransactionBot());
		transactionBotIcon.setBounds(30, 287, 59, 47);
		panel_Home_Lateral_Help.add(transactionBotIcon);
		transactionBotIcon.setVisible(false);
		
		deleteTransactionIcon = new IconizedButton("symbol","borrar",35,40,e -> deleteTransactionBot());
		deleteTransactionIcon.setBounds(99, 305, 72, 29);
		panel_Home_Lateral_Help.add(deleteTransactionIcon);
		deleteTransactionIcon.setVisible(false);
			
		lblNumBotsTransacion = new JLabel("0");
		lblNumBotsTransacion.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumBotsTransacion.setBounds(202, 305, 41, 27);
		panel_Home_Lateral_Help.add(lblNumBotsTransacion);
		lblNumBotsTransacion.setVisible(false);
		
		
		/////////////////ACTIVATED BUTTONS////////////////////////////////		
		
		actButtonCleaning = new ActivatedButton("Activar Bot Limpieza");
		actButtonCleaning.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refreshComponentsCleaning();
			}
		});
		actButtonCleaning.setSize(223, 29);
		actButtonCleaning.setLocation(20, 11);
		panel_Home_Lateral_Help.add(actButtonCleaning);
		actButtonCleaning.setVisible(false);
		
		
		actButtonMail = new ActivatedButton("Activar Bot Mail");
		actButtonMail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refreshComponentsMail();
			}
		});
		actButtonMail.setSize(223, 29);
		actButtonMail.setLocation(20, 91);
		panel_Home_Lateral_Help.add(actButtonMail);
		actButtonMail.setVisible(false);
		
		
		actButtonUsuario = new ActivatedButton("Activar Bot Usuario");
		actButtonUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refreshComponentsUsuario();
			}
		});
		actButtonUsuario.setSize(223, 29);
		actButtonUsuario.setLocation(20, 174);
		panel_Home_Lateral_Help.add(actButtonUsuario);
		actButtonUsuario.setVisible(false);
		
		
		actButtonTransacion = new ActivatedButton("Activar Bot Transacion");
		actButtonTransacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refreshComponentsTransacion();
			}
		});
		actButtonTransacion.setSize(223, 29);
		actButtonTransacion.setLocation(20, 260);
		panel_Home_Lateral_Help.add(actButtonTransacion);
		actButtonTransacion.setVisible(false);
		
		
		JScrollPane scrollPanel_Lateral = new JScrollPane(panel_Home_Lateral,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
	            JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPanel_Lateral.setBorder(new EmptyBorder(0, 0, 0, 0));
		add(scrollPanel_Lateral, "cell 1 0,grow");
		
		
		
		panel_Home_Info.add(panel_Home_Info_Botones, BorderLayout.SOUTH);
		panel_Home_Info.add(tf_Software_Info, BorderLayout.EAST);
		panel_Home_Info.add(tf_Information, BorderLayout.NORTH);
		
		panel_Home_Center.add(panel_Home_Info,BorderLayout.CENTER);
		panel_Home_Center.add(panel_Home_Title, BorderLayout.NORTH);
		
		add(panel_Home_Center, "cell 0 0,grow");
		
	}
	
	private void deleteTransactionBot() {
		// TODO Auto-generated method stub
		lblNumBotsTransacion.setText(0+"");

	}

	private void deleteUserBot() {
		// TODO Auto-generated method stub
		lblNumBotsUsuario.setText(0+"");

	}

	private void deleteMailBot() {
		Thread hiloBorrarMail = new Thread(new Runnable() {
			public void run() {
				for (BotBase botBase : mailBots) {
					botBase.kill();
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
				}
				lblNumBotsLimpieza.setText(0+"");				
			}
		});
		hiloBorrarCleaning.run();
	}

	private void addTransactionBot() {
		//TODO
		int count = Integer.parseInt(lblNumBotsTransacion.getText());
		count = count +1;
		lblNumBotsTransacion.setText(count + "");
	}

	private void addUserBot() {
		//TODO
		int count = Integer.parseInt(lblNumBotsUsuario.getText());
		count = count +1;
		lblNumBotsUsuario.setText(count + "");
	}

	private void addCleaningBot() {
		int count = Integer.parseInt(lblNumBotsLimpieza.getText());
		BotBase cb = BotGenerator.generateBot(BotType.CleaningBot, "Cleaning Bot " + count);
		cleaningBots.add(cb);
		cb.execute();
		count = count +1;
		lblNumBotsLimpieza.setText(count + "");
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
		}else {
			actButtonCleaning.setVisible(false);
			actButtonCleaning.setSelected(false);
			actButtonMail.setVisible(false);
			actButtonMail.setSelected(false);
			actButtonUsuario.setVisible(false);
			actButtonUsuario.setSelected(false);
			actButtonTransacion.setVisible(false);
			actButtonTransacion.setSelected(false);
			deleteCleaningBot();
			deleteMailBot();
			refreshComponentsCleaning();
			refreshComponentsMail();
			refreshComponentsUsuario();
			refreshComponentsTransacion();
		}
		
	}
	
	public void refreshComponentsCleaning() {
		if(actButtonCleaning.isSelected()) {
			actButtonCleaning.setText("Desactivar Bot Limpieza");
			actButtonCleaning.setBackground(CustomColors.mBGreenLight);
			cleaningBotIcon.setVisible(true);
			deleteCleaningIcon.setVisible(true);
			lblNumBotsLimpieza.setVisible(true);
		}else {
			actButtonCleaning.setText("Activar Bot Limpieza");
			actButtonCleaning.setBackground(CustomColors.mBRedLight);
			lblNumBotsLimpieza.setText(0+"");
			cleaningBotIcon.setVisible(false);
			deleteCleaningIcon.setVisible(false);
			lblNumBotsLimpieza.setVisible(false);
		}
	}
	
	public void refreshComponentsMail() {
		if(actButtonMail.isSelected()) {
			actButtonMail.setText("Desactivar Bot Mail");
			actButtonMail.setBackground(CustomColors.mBGreenLight);
			mailBotIcon.setVisible(true);
			deleteMailIcon.setVisible(true);
			lblNumBotsMail.setVisible(true);
		}else {
			actButtonMail.setText("Activar Bot Mail");
			actButtonMail.setBackground(CustomColors.mBRedLight);
			lblNumBotsMail.setText(0+"");
			mailBotIcon.setVisible(false);
			deleteMailIcon.setVisible(false);
			lblNumBotsMail.setVisible(false);
		}
	}
	
	public void refreshComponentsUsuario() {
		if(actButtonUsuario.isSelected()) {
			actButtonUsuario.setText("Desactivar Bot Usuario");
			actButtonUsuario.setBackground(CustomColors.mBGreenLight);
			userBotIcon.setVisible(true);
			deleteUserIcon.setVisible(true);
			lblNumBotsUsuario.setVisible(true);
		}else {
			actButtonUsuario.setText("Activar Bot Usuario");
			actButtonUsuario.setBackground(CustomColors.mBRedLight);
			lblNumBotsUsuario.setText(0+"");
			userBotIcon.setVisible(false);
			deleteUserIcon.setVisible(false);
			lblNumBotsUsuario.setVisible(false);
		}
	}
	
	public void refreshComponentsTransacion() {
		if(actButtonTransacion.isSelected()) {
			actButtonTransacion.setText("Desactivar Bot Transacion");
			actButtonTransacion.setBackground(CustomColors.mBGreenLight);
			transactionBotIcon.setVisible(true);
			deleteTransactionIcon.setVisible(true);
			lblNumBotsTransacion.setVisible(true);
		}else {
			actButtonTransacion.setText("Activar Bot Transacion");
			actButtonTransacion.setBackground(CustomColors.mBRedLight);
			lblNumBotsTransacion.setText(0+"");
			transactionBotIcon.setVisible(false);
			deleteTransactionIcon.setVisible(false);
			lblNumBotsTransacion.setVisible(false);
		}
	}
}
