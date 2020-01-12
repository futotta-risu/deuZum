package Paneles;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import mensajeRespuesta.MessageSender;
import mensajeRespuesta.ServerRespuesta;

import javax.swing.JTextArea;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
		setLayout(new VerticalFlowLayout(10,10,10));

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
		
		JButton btnenviar=new JButton();
		btnenviar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String nDestinatario = textFieldNombreDest.getText();
				String importe = textFieldImporte.getText();
				String areaComentario = textAreaComentario.getText();
				
				
				JSONObject data = new JSONObject();
				
				data.put("dest", nDestinatario);
				data.put("dinero", importe);
				data.put("comentario",areaComentario);
				
				Thread t1 = new Thread(new MessageSender("logUser", data));
				t1.start();
				try {
		            Thread.sleep(300);
		        } catch (InterruptedException e) {
		            e.printStackTrace();
		        }
				
				ServerRespuesta.result = "-1";
			
			
				
			}
		});
	


	}
}
