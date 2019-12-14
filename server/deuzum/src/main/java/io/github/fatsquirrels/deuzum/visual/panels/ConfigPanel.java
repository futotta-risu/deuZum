package io.github.fatsquirrels.deuzum.visual.panels;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
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

import io.github.fatsquirrels.deuzum.visual.ServerHandlerFrame;
import io.github.fatsquirrels.deuzum.visual.components.buttons.FlatButton;

public class ConfigPanel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -227031561056288280L;

	public ConfigPanel() {
		
		setLayout(new BorderLayout(0, 0));
		JTabbedPane panel_Configuration = new JTabbedPane(JTabbedPane.TOP);
		add(panel_Configuration);
		
		JPanel panel_Config_Server = new JPanel();
		panel_Configuration.addTab("Server", null, panel_Config_Server, null);
		GridBagLayout gbl_panel_Config_Server = new GridBagLayout();
		gbl_panel_Config_Server.columnWidths = new int[]{39, 87, 105, 28, 61, 77, 43, 0};
		gbl_panel_Config_Server.rowHeights = new int[]{22, 22, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel_Config_Server.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_Config_Server.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_Config_Server.setLayout(gbl_panel_Config_Server);
		
		JLabel lbl_Server_Port = new JLabel("Puerto del Servidor");
		GridBagConstraints gbc_lbl_Server_Port = new GridBagConstraints();
		gbc_lbl_Server_Port.anchor = GridBagConstraints.WEST;
		gbc_lbl_Server_Port.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_Server_Port.gridx = 1;
		gbc_lbl_Server_Port.gridy = 1;
		panel_Config_Server.add(lbl_Server_Port, gbc_lbl_Server_Port);
		
		final JSpinner spinner_Port = new JSpinner();
		spinner_Port.setModel(new SpinnerNumberModel(Integer.parseInt((String)ServerHandlerFrame.properties.get("server.port")), 0, 65535, 1));
		GridBagConstraints gbc_spinner_Port = new GridBagConstraints();
		gbc_spinner_Port.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinner_Port.insets = new Insets(0, 0, 5, 5);
		gbc_spinner_Port.gridx = 4;
		gbc_spinner_Port.gridy = 1;
		panel_Config_Server.add(spinner_Port, gbc_spinner_Port);
		
		JLabel lblNombreDelServidor = new JLabel("Nombre del Servidor");
		GridBagConstraints gbc_lblNombreDelServidor = new GridBagConstraints();
		gbc_lblNombreDelServidor.anchor = GridBagConstraints.WEST;
		gbc_lblNombreDelServidor.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombreDelServidor.gridx = 1;
		gbc_lblNombreDelServidor.gridy = 2;
		panel_Config_Server.add(lblNombreDelServidor, gbc_lblNombreDelServidor);
		
		JTextField txf_ServerName = new JTextField();
		txf_ServerName.setText((String) ServerHandlerFrame.properties.get("server.name"));
		GridBagConstraints gbc_txf_ServerName = new GridBagConstraints();
		gbc_txf_ServerName.gridwidth = 2;
		gbc_txf_ServerName.insets = new Insets(0, 0, 5, 5);
		gbc_txf_ServerName.fill = GridBagConstraints.HORIZONTAL;
		gbc_txf_ServerName.gridx = 1;
		gbc_txf_ServerName.gridy = 3;
		panel_Config_Server.add(txf_ServerName, gbc_txf_ServerName);
		txf_ServerName.setColumns(10);
		
		JLabel lblConnectionTimeOut = new JLabel("Connection Time Out");
		GridBagConstraints gbc_lblConnectionTimeOut = new GridBagConstraints();
		gbc_lblConnectionTimeOut.insets = new Insets(0, 0, 5, 5);
		gbc_lblConnectionTimeOut.gridx = 1;
		gbc_lblConnectionTimeOut.gridy = 6;
		panel_Config_Server.add(lblConnectionTimeOut, gbc_lblConnectionTimeOut);
		
		final JSlider slider_ConTimeOut = new JSlider();
		slider_ConTimeOut.setValue(Integer.parseInt((String)ServerHandlerFrame.properties.get("server.conexionTimeOut")));
		GridBagConstraints gbc_slider_ConTimeOut = new GridBagConstraints();
		gbc_slider_ConTimeOut.fill = GridBagConstraints.HORIZONTAL;
		gbc_slider_ConTimeOut.gridwidth = 2;
		gbc_slider_ConTimeOut.insets = new Insets(0, 0, 5, 5);
		gbc_slider_ConTimeOut.gridx = 1;
		gbc_slider_ConTimeOut.gridy = 7;
		panel_Config_Server.add(slider_ConTimeOut, gbc_slider_ConTimeOut);
		
		JLabel lblActualTimeOut = new JLabel("Actual Time Out");
		GridBagConstraints gbc_lblActualTimeOut = new GridBagConstraints();
		gbc_lblActualTimeOut.gridwidth = 2;
		gbc_lblActualTimeOut.insets = new Insets(0, 0, 5, 5);
		gbc_lblActualTimeOut.gridx = 4;
		gbc_lblActualTimeOut.gridy = 7;
		panel_Config_Server.add(lblActualTimeOut, gbc_lblActualTimeOut);
		
		JPanel panel_Config_Seguridad = new JPanel();
		panel_Configuration.addTab("Seguridad", null, panel_Config_Seguridad, null);
		GridBagLayout gbl_panel_Config_Seguridad = new GridBagLayout();
		gbl_panel_Config_Seguridad.columnWidths = new int[]{0, 0, 0, 0, 254, 0, 0};
		gbl_panel_Config_Seguridad.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_panel_Config_Seguridad.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_Config_Seguridad.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_Config_Seguridad.setLayout(gbl_panel_Config_Seguridad);
		
		JLabel lblEncriptacinDeSockets = new JLabel("Encriptaci\u00F3n de Sockets");
		GridBagConstraints gbc_lblEncriptacinDeSockets = new GridBagConstraints();
		gbc_lblEncriptacinDeSockets.insets = new Insets(0, 0, 5, 5);
		gbc_lblEncriptacinDeSockets.gridx = 2;
		gbc_lblEncriptacinDeSockets.gridy = 1;
		panel_Config_Seguridad.add(lblEncriptacinDeSockets, gbc_lblEncriptacinDeSockets);
		
		JToggleButton tglbtnNewToggleButton = new JToggleButton("Si");
		GridBagConstraints gbc_tglbtnNewToggleButton = new GridBagConstraints();
		gbc_tglbtnNewToggleButton.insets = new Insets(0, 0, 5, 5);
		gbc_tglbtnNewToggleButton.gridx = 4;
		gbc_tglbtnNewToggleButton.gridy = 1;
		panel_Config_Seguridad.add(tglbtnNewToggleButton, gbc_tglbtnNewToggleButton);
		
		JLabel lblFuncinDeEncriptacin = new JLabel("Funci\u00F3n de Encriptaci\u00F3n");
		GridBagConstraints gbc_lblFuncinDeEncriptacin = new GridBagConstraints();
		gbc_lblFuncinDeEncriptacin.insets = new Insets(0, 0, 5, 5);
		gbc_lblFuncinDeEncriptacin.gridx = 2;
		gbc_lblFuncinDeEncriptacin.gridy = 2;
		panel_Config_Seguridad.add(lblFuncinDeEncriptacin, gbc_lblFuncinDeEncriptacin);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"RSA", "SHA256", "MD5", "Blowfish", "Cesar", "Vigenere"}));
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 4;
		gbc_comboBox.gridy = 2;
		panel_Config_Seguridad.add(comboBox, gbc_comboBox);
		
		JPanel panel_1 = new JPanel();
		
		panel_1.setLayout(new BorderLayout(0, 0));
		
		
		JButton btn_Save = new FlatButton("Guardar");
		btn_Save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ServerHandlerFrame.updateProperty("server.conexionTimeOut",String.valueOf(slider_ConTimeOut.getValue()) );
				ServerHandlerFrame.updateProperty("server.port",spinner_Port.getValue().toString());
				ServerHandlerFrame.updateProperty("server.name",txf_ServerName.getText());
		
				ServerHandlerFrame.storeProperties();
			}
		});
		panel_1.add(btn_Save, BorderLayout.EAST);
		add(panel_1, BorderLayout.SOUTH);
	}
	

}
