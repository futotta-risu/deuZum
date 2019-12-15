package io.github.fatsquirrels.deuzum.visual.panels;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import io.github.fatsquirrels.deuzum.visual.components.buttons.FlatButton;

public class FunctionalityPanel extends JTabbedPane {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4541688928942198726L;

	public FunctionalityPanel() {
		JPanel panel_Funct_Database = new JPanel();
		addTab("Base de Datos", null, panel_Funct_Database, null);
		
		//		BOTONES FUNCIONALIDADES
		
		JButton btnRellenarBd = new FlatButton("Rellenar BD");
		panel_Funct_Database.add(btnRellenarBd);
		btnRellenarBd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub	
			}
		});
		
		JPanel panel_Funct_IA = new JPanel();
		addTab("IA", null, panel_Funct_IA, null);
		
		JButton btnClusterizarDb = new JButton("Clusterizar DB");
		panel_Funct_IA.add(btnClusterizarDb);
		
		JPanel panel_Funct_Seguridad = new JPanel();
		addTab("Seguridad", null, panel_Funct_Seguridad, null);
		GridBagLayout gbl_panel_Funct_Seguridad = new GridBagLayout();
		gbl_panel_Funct_Seguridad.columnWidths = new int[]{44, 75, 93, 0};
		gbl_panel_Funct_Seguridad.rowHeights = new int[]{23, 0, 0, 0, 0, 0};
		gbl_panel_Funct_Seguridad.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_Funct_Seguridad.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_Funct_Seguridad.setLayout(gbl_panel_Funct_Seguridad);
		
		JLabel lbl_Encriptar = new JLabel("Encriptaci\u00F3n");
		GridBagConstraints gbc_lbl_Encriptar = new GridBagConstraints();
		gbc_lbl_Encriptar.anchor = GridBagConstraints.WEST;
		gbc_lbl_Encriptar.gridwidth = 2;
		gbc_lbl_Encriptar.insets = new Insets(0, 0, 5, 0);
		gbc_lbl_Encriptar.gridx = 1;
		gbc_lbl_Encriptar.gridy = 1;
		panel_Funct_Seguridad.add(lbl_Encriptar, gbc_lbl_Encriptar);
		
		JButton btnEncriptar = new JButton("Encriptar");
		GridBagConstraints gbc_btnEncriptar = new GridBagConstraints();
		gbc_btnEncriptar.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnEncriptar.insets = new Insets(0, 0, 5, 5);
		gbc_btnEncriptar.gridx = 1;
		gbc_btnEncriptar.gridy = 2;
		panel_Funct_Seguridad.add(btnEncriptar, gbc_btnEncriptar);
		
		JButton btnDesencriptar = new JButton("Desencriptar");
		GridBagConstraints gbc_btnDesencriptar = new GridBagConstraints();
		gbc_btnDesencriptar.insets = new Insets(0, 0, 5, 0);
		gbc_btnDesencriptar.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnDesencriptar.gridx = 2;
		gbc_btnDesencriptar.gridy = 2;
		panel_Funct_Seguridad.add(btnDesencriptar, gbc_btnDesencriptar);
		
		JLabel lblNewLabel = new JLabel("New label");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 4;
		panel_Funct_Seguridad.add(lblNewLabel, gbc_lblNewLabel);
	}
}
