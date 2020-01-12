package Paneles;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PanelCuenta extends JPanel {
	private JTextField textFieldNumTarjetat;
	private JTextField textFieldCVV;
	private JTextField textFieldFechaCaducidad;
	private JLabel lblNumTarjetat= new JLabel();
	private JLabel lblCVV= new JLabel();
	private JLabel lblFechaCaducidad= new JLabel();
	
	public PanelCuenta() {
		setLayout(new VerticalFlowLayout(10,10,10));
		
		lblNumTarjetat = new JLabel("Número de tarjeta:");
		add(lblNumTarjetat);
		
		textFieldNumTarjetat = new JTextField();
		add(textFieldNumTarjetat);
		textFieldNumTarjetat.setColumns(10);
		
		lblCVV = new JLabel("Importe:");
		add(lblCVV);
		
		textFieldCVV = new JTextField();
		add(textFieldCVV);
		textFieldCVV.setColumns(10);
		
		lblFechaCaducidad = new JLabel(" Fecha de caducidad");
		add(lblFechaCaducidad);
		
		textFieldFechaCaducidad = new JTextField();
		add(textFieldFechaCaducidad);
		textFieldFechaCaducidad.setColumns(10);
		
		
		
	}

}
