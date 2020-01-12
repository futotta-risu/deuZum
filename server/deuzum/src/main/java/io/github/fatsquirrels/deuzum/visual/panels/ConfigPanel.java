package io.github.fatsquirrels.deuzum.visual.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import io.github.fatsquirrels.deuzum.res.ServerProperties;
import io.github.fatsquirrels.deuzum.visual.components.buttons.FlatButton;
import io.github.fatsquirrels.deuzum.visual.panels.util.PairPanel;
import io.github.fatsquirrels.deuzum.visual.style.layout.VerticalFlowLayout;

public class ConfigPanel extends JPanel{
	
	private static final long serialVersionUID = -227031561056288280L;

	public ConfigPanel() {
		setBackground(Color.WHITE);
		setLayout(new BorderLayout(0, 0));
		JTabbedPane panel_Configuration = new JTabbedPane(JTabbedPane.TOP);
		panel_Configuration.setBackground(Color.WHITE);
		add(panel_Configuration);
		
		// Panel de Configuracion del server
		JPanel panel_Config_Server = new JPanel(new VerticalFlowLayout(10,10,10));
		panel_Config_Server.setBackground(Color.WHITE);
		panel_Configuration.addTab("Server", null, panel_Config_Server, null);
		
		JTextField txf_ServerName = new JTextField(10);
		txf_ServerName.setText((String) ServerProperties.properties.get("server.name"));
		
		final JSpinner spinner_Port = new JSpinner();
		spinner_Port.setModel(new SpinnerNumberModel(Integer.parseInt((String)ServerProperties.properties.get("server.port")), 0, 65535, 1));
		
		final JSlider slider_ConTimeOut = new JSlider();
		slider_ConTimeOut.setValue(Integer.parseInt((String)ServerProperties.properties.get("server.conexionTimeOut")));
		slider_ConTimeOut.setPaintTicks(true); //las rayitas que marcan los números
		slider_ConTimeOut.setMajorTickSpacing(25); // de cuanto en cuanto los números en el slider
		slider_ConTimeOut.setMinorTickSpacing(5); //las rayitas de cuanto en cuanto
		slider_ConTimeOut.setPaintLabels(true);
		slider_ConTimeOut.setBackground(Color.WHITE);
		
		slider_ConTimeOut.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				slider_ConTimeOut.setToolTipText(slider_ConTimeOut.getValue() + "");				
			}
		});
		
		panel_Config_Server.add(new PairPanel("Puerto del Servidor",spinner_Port));
		panel_Config_Server.add(new PairPanel("Nombre del Servidor",txf_ServerName));
		panel_Config_Server.add(new PairPanel("Connection Time Out",slider_ConTimeOut));
		panel_Config_Server.add(new PairPanel("Actual Time Out", new JLabel(ServerProperties.properties.get("server.conexionTimeOut").toString() + " segundos")));
		
		// Panel de Configuraci�n de Seguridad
		
		JPanel panel_Config_Seguridad = new JPanel(new VerticalFlowLayout(10,10,10));
		panel_Config_Seguridad.setBackground(Color.WHITE);		
		panel_Configuration.addTab("Seguridad", null, panel_Config_Seguridad, null);
		
		
		JToggleButton tglEncriptacion = new JToggleButton("Si");
		
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"RSA", "SHA256", "MD5", "Blowfish", "Cesar", "Vigenere"}));
		
		panel_Config_Seguridad.add(new PairPanel("Encriptacion de Sockets",tglEncriptacion));
		panel_Config_Seguridad.add(new PairPanel("Funcion de Encriptacion",comboBox));
		
		
		JPanel panel_1 = new JPanel();
		
		panel_1.setLayout(new BorderLayout(0, 0));
		
		
		JButton btn_Save = new FlatButton("Guardar");
		btn_Save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ServerProperties.updateProperty("server.conexionTimeOut",String.valueOf(slider_ConTimeOut.getValue()) );
				ServerProperties.updateProperty("server.port",spinner_Port.getValue().toString());
				ServerProperties.updateProperty("server.name",txf_ServerName.getText());
		
				ServerProperties.storeProperties();
			}
		});
		
		panel_1.add(btn_Save, BorderLayout.EAST);
		add(panel_1, BorderLayout.SOUTH);
	}
	

}
