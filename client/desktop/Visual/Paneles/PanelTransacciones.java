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
<<<<<<< HEAD
		setLayout(new VerticalFlowLayout(10,10,10));

=======
		setLayout(null);
		
>>>>>>> parent of 0c786a1... Merge branch 'master' of https://github.com/futotta-risu/deuZum
		lblNombreDest = new JLabel("Nombre del destinatario:");
		add(lblNombreDest);
		
		textFieldNombreDest = new JTextField();
		add(textFieldNombreDest);
		textFieldNombreDest.setColumns(10);
		
		lblImporte = new JLabel("Importe:");
		add(lblImporte);
		
		textFieldImporte = new JTextField();
		add(textFieldImporte);
		textFieldImporte.setColumns(10);
		
		lblEuro = new JLabel(" \u20AC");
		add(lblEuro);
		
		lblAadirComentarios = new JLabel("A\u00F1adir comentarios:");
		add(lblAadirComentarios);
		
		textAreaComentario = new JTextArea();
		add(textAreaComentario);
		
		btnFinalizar = new JButton("Finalizar");
		add(btnFinalizar);
		
	
	}
}
