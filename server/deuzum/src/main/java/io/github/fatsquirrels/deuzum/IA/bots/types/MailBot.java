package io.github.fatsquirrels.deuzum.IA.bots.types;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import io.github.fatsquirrels.deuzum.IA.bots.BotBase;
import io.github.fatsquirrels.deuzum.IA.bots.BotFunctions;
import io.github.fatsquirrels.deuzum.utils.math.APair;

/**
 * Esta clase permite crear un objeto de MailBot que envia un correo a los destinatarios recibidos
 * @see BotBase
 * @see BotFunctions
 */
public class MailBot extends BotBase implements BotFunctions{
	
	private APair<String, String> mensaje;
	private List<String> destinatarios;
	private Thread hiloMensaje = null;
	
	private static String correo = "deuzumNoReply@gmail.com";
	private static String password = "deuzum1234";
	private static String servidorSMTP = "smtp.gmail.com";
	private static String puertoEnvio = "587";
	private static Properties props;
	
	public MailBot() {
		
	}
	
	/**
	 * Constructor de la clase, crea un MailBot con los parametros recibidos
	 * @param name Nombre del bot
	 */
	public MailBot(String name, Integer temp) {
		this.name = name;
		addMailBot(name,temp);
		props = System.getProperties();
	}
	
	
	

	/**
	 * Metodo que ejecuta el MailBot en un hilo.
	 * El bot envia el mensaje a todos los destinatarios.
	 */
	@Override
	public void execute() {
		hiloMensaje = new Thread(new Runnable() {

			public void run() {
			    
		        props.put("mail.smtp.starttls.enable", "true");//inicializar el servidor
		        props.put("mail.smtp.auth", "true");//autentificacion
		        props.put("mail.smtp.starttls.enable", "true"); //Para conectar de manera segura al servidor SMTP
		        props.put("mail.smtp.port", puertoEnvio); //El puerto SMTP seguro de Google
		        //SecurityManager security = System.getSecurityManager();
				

	        
	            //Session sesion = Session.getInstance(props, auth);//se inica una session
	            Session sesion = Session.getDefaultInstance(props,null);
	            sesion.setDebug(true);
	            
		        for (String mail : destinatarios) {
		        		System.out.println("enviando a mail" + mail);
						enviaEmail(mail, sesion);		
				}				
			}
		});
		hiloMensaje.run();
				
	}

	/**
	 * Metodo que detiene el MailBot durante los segundos introducidos
	 */
	@Override
	public void stop(long time) {
		try {
			Thread.sleep(time*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Metodo que elimina el MailBot
	 */
	@Override
	public void kill() {
		hiloMensaje.interrupt();
	}
	
	/**
	 * Metodo que envia el correo electronico al recetor recibido
	 * @param mailReceptor Correo electronico del receptor
	 * @param sesion Objeto de Session que contiene una sesion iniciada
	 */
    public void enviaEmail(String mailReceptor, Session sesion) {

        try {
            MimeMessage msg = new MimeMessage(sesion);//se crea un objeto donde ira la estructura del correo
            msg.setContent(mensaje.value,"text/html");//seteo el cuertpo del mensaje
            msg.setSubject(mensaje.index);//setea asusto (opcional)
            //msg.setFrom(new InternetAddress(correo));//agrega la la propiedad del correo origen
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(mailReceptor));//agrega el destinatario
            System.out.println("enviando");
            Transport transport = sesion.getTransport("smtp");
            transport.connect(servidorSMTP, correo, password);
            transport.sendMessage(msg, msg.getAllRecipients());
            transport.close();

            JOptionPane.showMessageDialog(null, "Email enviado");//alerta de que mensaje fue enviado correctamente

        } catch (Exception mex) {
        	mex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Email no enviado");

        }

    }

    /**
     * Clase privada que autentfica el usuario y contraseña del correo electronico en
     * el servidor SMTP.
     */
    private class autentificadorSMTP extends javax.mail.Authenticator {

    	/**
    	 * Metodo que autentifica el cliente mail.
    	 * @return PasswordAuthentication Objeto de autentificacion
    	 */
        @Override
        public PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(correo, password);//autentifica tanto el correo como la contraseña
        }

    }
    
    public void addMailBot(String name, int temp) {
		
		destinatarios = new ArrayList<String>();
		
		FileFilter filter = new FileNameExtensionFilter("TXT File","txt");
		JFileChooser jfc = new JFileChooser();
		jfc.setDialogTitle("Elegir el archivo con los destinatarios");
		jfc.addChoosableFileFilter(filter);
		jfc.showOpenDialog(null);
		File selected = jfc.getSelectedFile();
		
		try(BufferedReader br = new BufferedReader(new FileReader(selected))){
			for(String line =br.readLine() ;  line!=null; line = br.readLine()) 
				if(!line.trim().isEmpty())
					destinatarios.add(line);
			 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		FileFilter filter2 = new FileNameExtensionFilter("TXT File","txt");
		JFileChooser jfc2 = new JFileChooser();
		jfc2.setDialogTitle("Elegir el archivo con el Mensaje");
		jfc2.addChoosableFileFilter(filter2);
		jfc2.showOpenDialog(null);
		File selected2 = jfc2.getSelectedFile();
		

		String cabecera = "";
		String cuerpo = "";
		
		try(BufferedReader br = new BufferedReader(new FileReader(selected2))) {
			cabecera =br.readLine();
			
			for( String line = br.readLine();  line!=null; line = br.readLine()) 
				cuerpo += line + "\n";
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		mensaje = new APair<String,String>(cabecera, cuerpo);

	}
    

}


