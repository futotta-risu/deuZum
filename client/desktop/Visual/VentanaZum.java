import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JComponent;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import Paneles.PanelConfiguracion;
import Paneles.PanelCuentas;
import Paneles.PanelTransacciones;
import Paneles.PanelUsuario;
import Paneles.panelGrupo;

import javax.swing.ButtonGroup;

public class VentanaZum extends JFrame  {
	private JFrame frame;
	private ButtonGroup botones= new ButtonGroup();
	private JPanel panelConfiguracion;
	private JPanel panelUsuario;
	private JPanel panelMisCuentas;
	private JPanel panelMisGrupos;
	private JPanel panelTrasacciones;
    private JPanel panelGeneral;
    private static final long serialVersionUID = 7048969902214333603L;
  



	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaZum window = new VentanaZum();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VentanaZum() {
	initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 frame.setLayout(new BorderLayout());
	   	 
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
		
		JButton configuración = new JButton("Configuración");
		configuración.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				switchPanel(new PanelConfiguracion());
			}
		});
		
		panel.add(configuración);
		
		
		 
	   	 frame.add(panel, BorderLayout.WEST);
	   	 frame.add(panelGeneral, BorderLayout.CENTER);
	   	 

		
		
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

