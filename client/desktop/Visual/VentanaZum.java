import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
<<<<<<< HEAD
import javax.swing.JScrollPane;
=======
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;

import javax.swing.JTextField;
>>>>>>> parent of 0c786a1... Merge branch 'master' of https://github.com/futotta-risu/deuZum
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

import Paneles.PanelConfiguracion;
import Paneles.PanelCuentas;
import Paneles.PanelTransacciones;
import Paneles.PanelUsuario;
import Paneles.panelGrupo;

import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextPane;
import javax.swing.JRadioButton;
import java.awt.GridBagConstraints;

public class VentanaZum extends JFrame  {
	private JPanel panelGeneral;
	private JFrame frame;
	private ButtonGroup botones= new ButtonGroup();
	private JPanel panelConfiguracion;
	private JPanel panelUsuario;
	private JPanel panelMisCuentas;
	private JPanel panelMisGrupos;
	private JPanel panelTrasacciones;
<<<<<<< HEAD
    private JPanel panelGeneral;
    private static final long serialVersionUID = 7048969902214333603L;
  
=======

>>>>>>> parent of 0c786a1... Merge branch 'master' of https://github.com/futotta-risu/deuZum



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
<<<<<<< HEAD
		 frame.setLayout(new BorderLayout());
	   	 
	   	 panelGeneral = new JPanel();
	   	 panelGeneral.setLayout(new BorderLayout());
	   	 
	   	 
	   	 
	   	 
	   	 JPanel panel = new JPanel();
	   	 panel.setLayout(new GridLayout(5,1));
	   	 

=======
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 135, 261);
		frame.getContentPane().add(panel);
		panel.setLayout(new GridLayout(5,1));
		
>>>>>>> parent of 0c786a1... Merge branch 'master' of https://github.com/futotta-risu/deuZum
		
		
		JButton usuario = new JButton("Usuario");
		usuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			} 
		});
		panel.add(usuario);
		
		JButton misCuentas = new JButton("Mis Cuentas");
		misCuentas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panel.add(misCuentas);
		
		JButton misGrupos = new JButton("Mis Grupos");
		misGrupos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		panel.add(misGrupos);
		
		JButton Transacciones = new JButton("Trasacciones");
		Transacciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panel.add(Transacciones);
		
		JButton configuración = new JButton("Configuración");
		
		panel.add(configuración);
		
		panelGeneral = new JPanel();
		panelGeneral.setBounds(135, 0, 299, 261);
		frame.getContentPane().add(panelGeneral);
		panelGeneral.setLayout(null);
		
		
		
		
		usuario.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				switchPanel(new PanelUsuario());
				
			}
		});
		
<<<<<<< HEAD
		panel.add(configuración);
		
		
		 
	   	 frame.add(panel, BorderLayout.WEST);
	   	 frame.add(panelGeneral, BorderLayout.CENTER);
	   	 
=======
		misCuentas.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				switchPanel(new PanelCuentas());
				
			}
		});

		
		misGrupos.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				switchPanel(new panelGrupo());
				
			}
		});
>>>>>>> parent of 0c786a1... Merge branch 'master' of https://github.com/futotta-risu/deuZum

		
		Transacciones.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				switchPanel(new PanelTransacciones());
				
			}
		});

		
		configuración.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				switchPanel(new PanelConfiguracion());
				
			}
		});
	}
<<<<<<< HEAD
	public void switchPanel ( JComponent panel ){
		 panelGeneral.removeAll();

	   	 JScrollPane scrolledPane = new JScrollPane();
	   	 scrolledPane.setViewportView(panel);
	   	 panelGeneral.add(scrolledPane, BorderLayout.CENTER);
	   	 panelGeneral.validate();
	   	 panelGeneral.repaint();

=======
	public void switchPanel ( JPanel panel ){
		panelGeneral.removeAll();
		panelGeneral.add(panel);
		panelGeneral.validate();
		panelGeneral.repaint();
>>>>>>> parent of 0c786a1... Merge branch 'master' of https://github.com/futotta-risu/deuZum
	}
	}

