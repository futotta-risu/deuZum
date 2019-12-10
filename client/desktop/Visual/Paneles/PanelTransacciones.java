package Paneles;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.JButton;

public class PanelTransacciones extends JPanel {
	private JTextField textFieldNombreDest;
	private JTextField textFieldImporte;
	private JLabel lblNombreDest= new JLabel();
	private JLabel lblImporte= new JLabel();
	private JLabel lblEuro= new JLabel();
	private JLabel lblAadirComentarios= new JLabel();
	private JTextArea textAreaComentario = new JTextArea();
	private JButton btnFinalizar = new JButton();
	
	public PanelTransacciones() {
		setLayout(null);
		
		lblNombreDest = new JLabel("Nombre del destinatario:");
		lblNombreDest.setBounds(15, 28, 146, 14);
		add(lblNombreDest);
		
		textFieldNombreDest = new JTextField();
		textFieldNombreDest.setBounds(193, 25, 154, 20);
		add(textFieldNombreDest);
		textFieldNombreDest.setColumns(10);
		
		lblImporte = new JLabel("Importe:");
		lblImporte.setBounds(15, 84, 46, 14);
		add(lblImporte);
		
		textFieldImporte = new JTextField();
		textFieldImporte.setBounds(193, 81, 154, 20);
		add(textFieldImporte);
		textFieldImporte.setColumns(10);
		
		lblEuro = new JLabel(" \u20AC");
		lblEuro.setBounds(357, 84, 46, 14);
		add(lblEuro);
		
		lblAadirComentarios = new JLabel("A\u00F1adir comentarios:");
		lblAadirComentarios.setBounds(15, 162, 120, 14);
		add(lblAadirComentarios);
		
		textAreaComentario = new JTextArea();
		textAreaComentario.setBounds(193, 157, 154, 89);
		add(textAreaComentario);
		
		btnFinalizar = new JButton("Finalizar");
		btnFinalizar.setBounds(333, 266, 89, 23);
		add(btnFinalizar);
		
	
	}
}
