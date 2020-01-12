package io.github.fatsquirrels.deuzum.IA.bots.types;

import java.util.List;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import javax.swing.JOptionPane;

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
	private static String puertoEnvio = "465";
	private static Properties props = new Properties();
	
	public MailBot() {
		
	}
	
	public MailBot(String name, Integer temp) {
		this.name = name;
	}
	
	/**
	 * Constructor de la clase, crea un MailBot con los parametros recibidos
	 * @param name Nombre del bot
	 * @param mensaje APair de String con la cabecera y el cuerpo
	 * @param destinatarios Lista de Destinatarios del mensaje
	 */
	public MailBot(String name, APair<String, String> mensaje, List<String> destinatarios){
		this.name = name;
		this.mensaje = mensaje;
		this.destinatarios = destinatarios;
		
	}

	/**
	 * Metodo que ejecuta el MailBot en un hilo.
	 * El bot envia el mensaje a todos los destinatarios.
	 */
	@Override
	public void execute() {
		hiloMensaje = new Thread(new Runnable() {

			public void run() {
			    props.put("mail.smtp.user", correo);//correo origen
		        props.put("mail.smtp.host", servidorSMTP);//tipo de servidor
		        props.put("mail.smtp.port", puertoEnvio);//puesto de salida
		        props.put("mail.smtp.starttls.enable", "true");//inicializar el servidor
		        props.put("mail.smtp.auth", "true");//autentificacion
		        props.put("mail.smtp.socketFactory.port", puertoEnvio);//activar el puerto
		        props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
		        props.put("mail.smtp.socketFactory.fallback", "false");
		        //SecurityManager security = System.getSecurityManager();
				

	            Authenticator auth = new autentificadorSMTP();//autentificar el correo
	            Session sesion = Session.getInstance(props, auth);//se inica una session
	            //sesion.setDebug(true);
	            
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

            msg.setText(mensaje.value);//seteo el cuertpo del mensaje
            msg.setSubject(mensaje.index);//setea asusto (opcional)
            msg.setFrom(new InternetAddress(correo));//agrega la la propiedad del correo origen
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(mailReceptor));//agrega el destinatario
            System.out.println("enviando");
            Transport.send(msg);//envia el mensaje

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
    

}


