import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

import Paneles.PanelUsuario;

import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextPane;
import javax.swing.JRadioButton;
import java.awt.GridBagConstraints;

public class VentanaZum {
	private JPanel panelGeneral;
	private JFrame frame;
	private ButtonGroup botones= new ButtonGroup();
	private JPanel panelConfiguracion;
	private JPanel panelUsuario;
	private JPanel panelMisCuentas;
	private JPanel panelMisGrupos;
	private JPanel panelTrasacciones;




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
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 135, 261);
		frame.getContentPane().add(panel);
		panel.setLayout(new GridLayout(5,1));
		
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
		
		panelUsuario = new JPanel();
		panelUsuario.setBounds(1, 0, 296, 261);
		panelGeneral.add(panelUsuario);
		
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(21, 10, 74, 19);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(207, 219, 82, 31);
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JLabel lblApellidos = new JLabel("Apellidos:");
		lblApellidos.setBounds(21, 46, 74, 14);
		
		JLabel lblTelfono = new JLabel("Tel\u00E9fono:");
		lblTelfono.setBounds(21, 82, 74, 14);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(21, 107, 46, 14);
		
		JLabel lblDireccion = new JLabel("Direcci\u00F3n:");
		lblDireccion.setBounds(21, 132, 74, 14);
		
		JLabel lblFecha_Nacimiento = new JLabel("Fecha de nacimiento:");
		lblFecha_Nacimiento.setBounds(21, 157, 142, 14);
		
		JLabel lblSexo = new JLabel("Sexo:");
		lblSexo.setBounds(21, 194, 46, 14);
		panelUsuario.setLayout(null);
		panelUsuario.add(lblNombre);
		panelUsuario.add(btnGuardar);
		panelUsuario.add(lblApellidos);
		panelUsuario.add(lblTelfono);
		panelUsuario.add(lblEmail);
		panelUsuario.add(lblDireccion);
		panelUsuario.add(lblFecha_Nacimiento);
		panelUsuario.add(lblSexo);
		
		JTextPane txtpnApellidos = new JTextPane();
		txtpnApellidos.setBounds(96, 40, 177, 20);
		panelUsuario.add(txtpnApellidos);
		
		JTextPane textPaneNombre = new JTextPane();
		textPaneNombre.setBounds(96, 10, 177, 20);
		panelUsuario.add(textPaneNombre);
		
		JTextPane textPaneTelefono = new JTextPane();
		textPaneTelefono.setBounds(96, 76, 175, 20);
		panelUsuario.add(textPaneTelefono);
		
		JTextPane textPaneEmail = new JTextPane();
		textPaneEmail.setBounds(96, 101, 177, 20);
		panelUsuario.add(textPaneEmail);
		
		JTextPane textPaneDireccion = new JTextPane();
		textPaneDireccion.setBounds(96, 126, 177, 20);
		panelUsuario.add(textPaneDireccion);
		
		JTextPane textPanefecha = new JTextPane();
		textPanefecha.setBounds(158, 151, 113, 20);
		panelUsuario.add(textPanefecha);
		
		JRadioButton rdbtnNewRadioButtonF = new JRadioButton("Femenino");
		rdbtnNewRadioButtonF.setBounds(96, 190, 67, 23);
		panelUsuario.add(rdbtnNewRadioButtonF);
		
		JRadioButton rdbtnNewRadioButtonM = new JRadioButton("Masculino");
		rdbtnNewRadioButtonM.setBounds(199, 189, 74, 23);
		panelUsuario.add(rdbtnNewRadioButtonM);
		
		botones.add(rdbtnNewRadioButtonM);
		botones.add(rdbtnNewRadioButtonF);
		
		panelConfiguracion = new JPanel();
		panelConfiguracion.setBounds(1, 0, 296, 260);
		panelConfiguracion.setLayout(null);
		panelConfiguracion.setVisible(true);
		panelGeneral.add(panelConfiguracion);
		
		JButton btn = new JButton("Boton");
		panelConfiguracion.add(btn);
		
		JPanel panelTrasacciones = new JPanel();
		panelTrasacciones.setBounds(1, 0, 296, 260);
		//panelGeneral.add(panelTrasacciones);
		panelTrasacciones.setLayout(null);
		
		JLabel lblNombredestin = new JLabel("Nombre destinatario:");
		lblNombredestin.setBounds(21, 10, 74, 19);
		
		JPanel panelMisGrupos = new JPanel();
		panelMisGrupos.setBounds(1, 0, 296, 260);
		//panelGeneral.add(panelMisGrupos);
		panelMisGrupos.setLayout(null);
		
		JPanel panelMisCuentas = new JPanel();
		panelMisCuentas.setBounds(1, 0, 296, 260);
		//panelGeneral.add(panelMisCuentas);
		panelMisCuentas.setLayout(null);
		
		
		usuario.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				switchPanel(new PanelUsuario());
				
			}
		});
		
		misCuentas.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//switchPanel());
				
			}
		});

		
		misGrupos.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				switchPanel(panelMisGrupos);
				
			}
		});

		
		Transacciones.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				switchPanel(panelTrasacciones);
				
			}
		});

		
		configuración.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				switchPanel(panelConfiguracion);
				
			}
		});
	}
	public void switchPanel ( JPanel panel ){
		panelGeneral.removeAll();
		panelGeneral.add(panel);
		panelGeneral.validate();
		panelGeneral.repaint();
	}
	}

