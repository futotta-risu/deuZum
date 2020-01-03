package Paneles;

import javax.swing.JPanel;

import java.awt.Container;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextPane;


import javax.swing.JRadioButton;

public class PanelUsuario extends JPanel{
	
	private static final long serialVersionUID = 1L;
	private ButtonGroup botones= new ButtonGroup();
	private JTextPane textPaneNombre = new JTextPane() ;
	private JTextPane textPaneApellidos = new JTextPane() ;
	private JTextPane textPaneTelefono = new JTextPane() ;
	private JTextPane textPaneEmail = new JTextPane() ;
	private JTextPane textPaneDireccion = new JTextPane() ;
	private JLabel lblNombre= new JLabel();
	private JLabel lblApellidos= new JLabel();
	private JLabel lblTelfono= new JLabel();
	private JLabel lblEmail= new JLabel();
	private JLabel lblDireccion= new JLabel();
	private JLabel lblSexo= new JLabel();
	private JRadioButton rdbtnFemenino = new JRadioButton();
	private JRadioButton rdbtnMasculino = new JRadioButton();
	
	public PanelUsuario() {
		
		
<<<<<<< HEAD
		setLayout(new VerticalFlowLayout(10,10,10));

=======
		
		//this.setSize(500, 200);
		this.setVisible(true);
		setLayout(null);
>>>>>>> parent of 0c786a1... Merge branch 'master' of https://github.com/futotta-risu/deuZum
		
		lblNombre = new JLabel("Nombre:");
		add(lblNombre);
		
		textPaneNombre = new JTextPane();
		add(textPaneNombre);
		
		lblApellidos = new JLabel("Apellidos:");
		add(lblApellidos);
			
		
		textPaneApellidos = new JTextPane();
		add(textPaneApellidos);
		
		lblTelfono = new JLabel("Tel\u00E9fono:");
		add(lblTelfono);
		
		textPaneTelefono = new JTextPane();
		add(textPaneTelefono);
		
		lblEmail = new JLabel("Email:");
		add(lblEmail);
		
		textPaneEmail = new JTextPane();
		add(textPaneEmail);
		
		lblDireccion = new JLabel("Direccion:");
		add(lblDireccion);
		
		textPaneDireccion = new JTextPane();
		add(textPaneDireccion);
		
		lblSexo = new JLabel("Sexo:");
		add(lblSexo);
		
		rdbtnFemenino = new JRadioButton("Femenino");
		add(rdbtnFemenino);
		
		rdbtnMasculino = new JRadioButton("Masculino");
		add(rdbtnMasculino);
		
		botones.add(rdbtnFemenino);
		botones.add(rdbtnMasculino);
		
		
    }

	public ButtonGroup getBotones() {
		return botones;
	}

	public void setBotones(ButtonGroup botones) {
		this.botones = botones;
	}

	public JTextPane getTextPaneNombre() {
		return textPaneNombre;
	}

	public void setTextPaneNombre(JTextPane textPaneNombre) {
		this.textPaneNombre = textPaneNombre;
	}

	public JTextPane getTextPaneApellidos() {
		return textPaneApellidos;
	}

	public void setTextPaneApellidos(JTextPane textPaneApellidos) {
		this.textPaneApellidos = textPaneApellidos;
	}

	public JTextPane getTextPaneTelefono() {
		return textPaneTelefono;
	}

	public void setTextPaneTelefono(JTextPane textPaneTelefono) {
		this.textPaneTelefono = textPaneTelefono;
	}

	public JTextPane getTextPaneEmail() {
		return textPaneEmail;
	}

	public void setTextPaneEmail(JTextPane textPaneEmail) {
		this.textPaneEmail = textPaneEmail;
	}

	public JTextPane getTextPaneDireccion() {
		return textPaneDireccion;
	}

	public void setTextPaneDireccion(JTextPane textPaneDireccion) {
		this.textPaneDireccion = textPaneDireccion;
	}

	public JLabel getLblNombre() {
		return lblNombre;
	}

	public void setLblNombre(JLabel lblNombre) {
		this.lblNombre = lblNombre;
	}

	public JLabel getLblApellidos() {
		return lblApellidos;
	}

	public void setLblApellidos(JLabel lblApellidos) {
		this.lblApellidos = lblApellidos;
	}

	public JLabel getLblTelfono() {
		return lblTelfono;
	}

	public void setLblTelfono(JLabel lblTelfono) {
		this.lblTelfono = lblTelfono;
	}

	public JLabel getLblEmail() {
		return lblEmail;
	}

	public void setLblEmail(JLabel lblEmail) {
		this.lblEmail = lblEmail;
	}

	public JLabel getLblDireccion() {
		return lblDireccion;
	}

	public void setLblDireccion(JLabel lblDireccion) {
		this.lblDireccion = lblDireccion;
	}

	public JLabel getLblSexo() {
		return lblSexo;
	}

	public void setLblSexo(JLabel lblSexo) {
		this.lblSexo = lblSexo;
	}

	public JRadioButton getRdbtnFemenino() {
		return rdbtnFemenino;
	}

	public void setRdbtnFemenino(JRadioButton rdbtnFemenino) {
		this.rdbtnFemenino = rdbtnFemenino;
	}

	public JRadioButton getRdbtnMasculino() {
		return rdbtnMasculino;
	}

	public void setRdbtnMasculino(JRadioButton rdbtnMasculino) {
		this.rdbtnMasculino = rdbtnMasculino;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

		
		
		
	}

