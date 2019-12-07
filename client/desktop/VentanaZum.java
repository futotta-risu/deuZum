import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class VentanaZum {
	private JPanel panelGeneral;
	private JFrame frame;

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
		configuración.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panel.add(configuración);
		
		JPanel panelGeneral = new JPanel();
		panelGeneral.setBounds(135, 0, 299, 261);
		frame.getContentPane().add(panelGeneral);
		panelGeneral.setLayout(null);
		
		JPanel panelUsuario = new JPanel();
		panelUsuario.setBounds(1, 0, 296, 261);
		panelGeneral.add(panelUsuario);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(21, 11, 46, 19);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(207, 219, 82, 31);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JLabel lblApellidos = new JLabel("Apellidos:");
		lblApellidos.setBounds(21, 40, 46, 14);
		
		JLabel lblTelfono = new JLabel("Tel\u00E9fono:");
		lblTelfono.setBounds(21, 65, 46, 14);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(21, 93, 46, 14);
		
		JLabel lblDireccion = new JLabel("Direcci\u00F3n:");
		lblDireccion.setBounds(21, 118, 56, 14);
		
		JLabel lblFecha_Nacimiento = new JLabel("Fecha de nacimiento:");
		lblFecha_Nacimiento.setBounds(21, 147, 102, 14);
		
		JLabel lblSexo = new JLabel("Sexo:");
		lblSexo.setBounds(21, 179, 46, 14);
		panelUsuario.setLayout(null);
		panelUsuario.add(lblNombre);
		panelUsuario.add(btnNewButton);
		panelUsuario.add(lblApellidos);
		panelUsuario.add(lblTelfono);
		panelUsuario.add(lblEmail);
		panelUsuario.add(lblDireccion);
		panelUsuario.add(lblFecha_Nacimiento);
		panelUsuario.add(lblSexo);
		
		JPanel panelConfiguracion = new JPanel();
		panelConfiguracion.setBounds(1, 0, 296, 260);
		panelGeneral.add(panelConfiguracion);
		panelConfiguracion.setLayout(null);
		
		JPanel panelTrasacciones = new JPanel();
		panelTrasacciones.setBounds(1, 0, 296, 260);
		panelGeneral.add(panelTrasacciones);
		panelTrasacciones.setLayout(null);
		
		JPanel panelMisGrupos = new JPanel();
		panelMisGrupos.setBounds(1, 0, 296, 260);
		panelGeneral.add(panelMisGrupos);
		panelMisGrupos.setLayout(null);
		
		JPanel panelMisCuentas = new JPanel();
		panelMisCuentas.setBounds(1, 0, 296, 260);
		panelGeneral.add(panelMisCuentas);
		panelMisCuentas.setLayout(null);
		
		
	}
	public void switchPanel ( JPanel panelUsuario ){
		panelGeneral.removeAll();
		panelGeneral.add(panelUsuario);
		panelGeneral.validate();
		panelGeneral.repaint();
	}
	
	}

