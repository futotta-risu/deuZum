package io.github.fatsquirrels.deuzumDesktop.visual.panel;

import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JTable;

import io.github.fatsquirrels.deuzumDesktop.visual.layout.VerticalFlowLayout;

import javax.swing.JEditorPane;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

public class PanelConfiguracion extends JPanel {
	private JPasswordField passwordField;
	private JLabel lblCambiarContrasea = new JLabel();
	private JPasswordField passwordField_1;
	private JLabel lblRepetirContrasea = new JLabel();
	
	public PanelConfiguracion() {
		
		
		setLayout(new VerticalFlowLayout(10,10,10));

		lblCambiarContrasea = new JLabel("Cambiar contrase\u00F1a:");
		add(lblCambiarContrasea);
		
		passwordField = new JPasswordField();
		add(passwordField);
		
		
		
		lblRepetirContrasea = new JLabel("Repetir contrase\u00F1a:");
		add(lblRepetirContrasea, BorderLayout.NORTH);
		
		passwordField_1 = new JPasswordField();
		add(passwordField_1);
		
		
	}
}
