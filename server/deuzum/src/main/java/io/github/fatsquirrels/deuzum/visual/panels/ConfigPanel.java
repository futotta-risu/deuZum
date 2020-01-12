package io.github.fatsquirrels.deuzum.visual.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import io.github.fatsquirrels.deuzum.res.ServerProperties;
import io.github.fatsquirrels.deuzum.visual.components.buttons.FlatButton;
import io.github.fatsquirrels.deuzum.visual.panels.util.PairPanel;
import io.github.fatsquirrels.deuzum.visual.style.CustomColors;
import io.github.fatsquirrels.deuzum.visual.style.layout.VerticalFlowLayout;

public class ConfigPanel extends JPanel{
	
	private static final long serialVersionUID = -227031561056288280L;

	public ConfigPanel() {
		setBackground(Color.WHITE);
		setLayout(new BorderLayout(0, 0));
		
		setBorder(new EmptyBorder(20,20,20,20));
		
		
		JPanel panel_Central = new JPanel(new VerticalFlowLayout(10,10,10));
		panel_Central.setBackground(CustomColors.VeryLightGrey);
		panel_Central.setBorder(BorderFactory.createMatteBorder(1,1,0,1, CustomColors.LightGrey));
		// Panel Titulo
		JPanel panel_Tittle = new JPanel(new BorderLayout());
		JLabel lblTitulo = new JLabel("Configuracion del Servidor", SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 20));
		panel_Tittle.add(lblTitulo);
		panel_Tittle.setBackground(CustomColors.VeryLightGrey);
		panel_Central.add(panel_Tittle);
		
		
		// Panel de Configuracion del server
		
		JPanel panel_Config_Server = new JPanel(new VerticalFlowLayout(10,10,10));
		panel_Config_Server.setBackground(CustomColors.VeryLightGrey);
		panel_Config_Server.setBorder(BorderFactory.createMatteBorder(1,0,1,0, CustomColors.LightGrey));
		panel_Central.add(panel_Config_Server);
		
		JTextField txf_ServerName = new JTextField(10);
		txf_ServerName.setText((String) ServerProperties.properties.get("server.name"));
		
		JTextField txf_SQLServerName = new JTextField(10);
		txf_SQLServerName.setText((String) ServerProperties.properties.get("server.dbName"));
		
		final JSpinner txf_Port = new JSpinner();
		txf_Port.setModel(new SpinnerNumberModel(Integer.parseInt((String)ServerProperties.properties.get("server.dbPort")), 1, 65535, 1));
		
		JTextField txf_User = new JTextField(10);
		txf_User.setText((String) ServerProperties.properties.get("server.dbUser"));
		
		JTextField txf_Pass = new JTextField(10);
		txf_Pass.setText((String) ServerProperties.properties.get("server.dbPass"));
		
		final JSpinner spinner_Port = new JSpinner();
		spinner_Port.setModel(new SpinnerNumberModel(Integer.parseInt((String)ServerProperties.properties.get("server.port")), 1, 65535, 1));
		
		final JSlider slider_ConTimeOut = new JSlider();
		slider_ConTimeOut.setValue(Integer.parseInt((String)ServerProperties.properties.get("server.conexionTimeOut")));
		slider_ConTimeOut.setPaintTicks(true); //las rayitas que marcan los números
		slider_ConTimeOut.setMajorTickSpacing(25); // de cuanto en cuanto los números en el slider
		slider_ConTimeOut.setMinorTickSpacing(5); //las rayitas de cuanto en cuanto
		slider_ConTimeOut.setPaintLabels(true);
		slider_ConTimeOut.setBackground(CustomColors.VeryLightGrey);
		
		slider_ConTimeOut.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				slider_ConTimeOut.setToolTipText(slider_ConTimeOut.getValue() + "");				
			}
		});
		
		panel_Config_Server.add(new PairPanel("Puerto del Servidor visible sobre localhost",spinner_Port));
		panel_Config_Server.add(new PairPanel("Nombre del Servidor",txf_ServerName));
		panel_Config_Server.add(new PairPanel("Puerto del servidor SQL",txf_SQLServerName));
		panel_Config_Server.add(new PairPanel("Puerto de la base de datos",txf_Port));
		panel_Config_Server.add(new PairPanel("Usuario de la base de datos",txf_User));
		panel_Config_Server.add(new PairPanel("Contrase�a de la base de datos",txf_Pass));
		panel_Config_Server.add(new PairPanel("Connection Time Out",slider_ConTimeOut));
		panel_Config_Server.add(new PairPanel("Actual Time Out", new JLabel(ServerProperties.properties.get("server.conexionTimeOut").toString() + " segundos")));
		
	
		JPanel panel_1 = new JPanel();
		
		panel_1.setLayout(new BorderLayout(0, 0));
		
		
		JButton btn_Save = new FlatButton("Guardar");
		btn_Save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ServerProperties.updateProperty("server.conexionTimeOut",String.valueOf(slider_ConTimeOut.getValue()) );
				ServerProperties.updateProperty("server.port",spinner_Port.getValue().toString());
				ServerProperties.updateProperty("server.name",txf_ServerName.getText());
				ServerProperties.updateProperty("server.dbPort",txf_Port.getValue().toString());
				ServerProperties.updateProperty("server.dbUser",txf_User.getText());
				ServerProperties.updateProperty("server.dbPass",txf_Pass.getText());
				ServerProperties.updateProperty("server.dbName",txf_SQLServerName.getText());
				ServerProperties.storeProperties();
			}
		});
		
		panel_1.add(btn_Save, BorderLayout.EAST);
		panel_1.setBackground(CustomColors.VeryLightGrey);
		panel_1.setBorder(BorderFactory.createMatteBorder(0,1,1,1, CustomColors.LightGrey));
		add(panel_1, BorderLayout.SOUTH);
		add(panel_Central, BorderLayout.CENTER);
	}
	

}
