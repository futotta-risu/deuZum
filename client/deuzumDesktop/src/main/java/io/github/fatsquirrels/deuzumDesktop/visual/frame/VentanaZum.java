package io.github.fatsquirrels.deuzumDesktop.visual.frame;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import io.github.fatsquirrels.deuzumDesktop.visual.panel.PanelConfiguracion;
import io.github.fatsquirrels.deuzumDesktop.visual.panel.PanelCuentas;
import io.github.fatsquirrels.deuzumDesktop.visual.panel.PanelTransacciones;
import io.github.fatsquirrels.deuzumDesktop.visual.panel.PanelUsuario;
import io.github.fatsquirrels.deuzumDesktop.visual.panel.panelGrupo;

import javax.swing.JButton;
import javax.swing.JComponent;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.ButtonGroup;

public class VentanaZum extends JFrame  {
	
	private String username;
	
	private ButtonGroup botones= new ButtonGroup();
	private JPanel panelConfiguracion;
	private JPanel panelUsuario;
	private JPanel panelMisCuentas;
	private JPanel panelMisGrupos;
	private JPanel panelTrasacciones;
    private JPanel panelGeneral;
    private static final long serialVersionUID = 7048969902214333603L;
  

	/**
	 * Create the application.
	 */
	public VentanaZum(String user) {
		this.username = user;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
	   	setVisible(true);
	   	panelGeneral = new JPanel();
	   	panelGeneral.setLayout(new BorderLayout());
	   	 
	   	 
	   	JPanel panel = new JPanel();
	   	panel.setLayout(new GridLayout(5,1));
	   	 

		
		
		JButton usuario = new JButton("Usuario");
		usuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				switchPanel(new PanelUsuario());
			} 
		});
		panel.add(usuario);
		
		JButton misCuentas = new JButton("Mis Cuentas");
		misCuentas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchPanel(new PanelCuentas());
			}
		});
		panel.add(misCuentas);
		
		JButton misGrupos = new JButton("Mis Grupos");
		misGrupos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchPanel(new panelGrupo());
			}
		});
		panel.add(misGrupos);
		
		JButton Transacciones = new JButton("Trasacciones");
		Transacciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchPanel(new PanelTransacciones());
			}
		});
		panel.add(Transacciones);
		
		JButton configuracion = new JButton("Configuraci�n");
		configuracion.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				switchPanel(new PanelConfiguracion());
			}
		});
		
		panel.add(configuracion);
		
		
		 
	   	add(panel, BorderLayout.WEST);
	   	add(panelGeneral, BorderLayout.CENTER);
	   	
	}
	public void switchPanel ( JComponent panel ){
		 panelGeneral.removeAll();

	   	 JScrollPane scrolledPane = new JScrollPane();
	   	 scrolledPane.setViewportView(panel);
	   	 panelGeneral.add(scrolledPane, BorderLayout.CENTER);
	   	 panelGeneral.validate();
	   	 panelGeneral.repaint();

	}
	}

