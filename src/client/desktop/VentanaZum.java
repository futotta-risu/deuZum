import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.GridLayout;

public class VentanaZum {

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
		panel.setLayout(new GridLayout(6,1));
		
		JButton cuenta = new JButton("Mi cuenta");
		panel.add(cuenta);
		
		JButton pagos = new JButton("Pagos directos");
		panel.add(pagos);
		
		JButton transacciones = new JButton("Últimas transacciones");
		panel.add(transacciones);
		
		JButton proyecto = new JButton("Proyectos");
		panel.add(proyecto);
		
		JButton ayuda = new JButton("Ayuda");
		panel.add(ayuda);
		
		JButton faq = new JButton("FAQ");
		panel.add(faq);
		
		

	}
}
