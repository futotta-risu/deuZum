package io.github.fatsquirrels.deuzum.IA.bots.types;

import java.util.List;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import javax.swing.JOptionPane;

import io.github.fatsquirrels.deuzum.IA.bots.BotBase;
import io.github.fatsquirrels.deuzum.IA.bots.BotFunctions;
import io.github.fatsquirrels.deuzum.utils.math.APair;

public class MailBot extends BotBase implements BotFunctions{
	
	private APair<String, String> mensaje;
	private List<String> destinatarios;
	private Thread hiloMensaje = null;
	
	private static String correo = "deuzumNoReply@gmail.com";
	private static String password = "deuzum1234";
	private static String servidorSMTP = "smtp.gmail.com";
	private static String puertoEnvio = "465";
	
	
	public MailBot() {
	}
	

	//Utilizo un APair usando los asuntos de los mensajes como claves para obtener los mensajes
	public MailBot(String name, APair<String, String> mensaje, List<String> destinatarios){
		this.name = name;
		this.mensaje = mensaje;
		this.destinatarios = destinatarios;
		
	}

	@Override
	public void execute() {
		hiloMensaje = new Thread(new Runnable() {

			public void run() {
				for (String mail : destinatarios) {
						enviaEmail(mail);		
				}				
			}
		});
		hiloMensaje.run();
				
	}

	@Override
	public void stop(long time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void kill() {
		hiloMensaje.interrupt();
	}
	
	
    public void enviaEmail(String mailReceptor) {

        Properties props = new Properties();//propiedades a agragar

        props.put("mail.smtp.user", correo);//correo origen
        props.put("mail.smtp.host", servidorSMTP);//tipo de servidor
        props.put("mail.smtp.port", puertoEnvio);//puesto de salida
        props.put("mail.smtp.starttls.enable", "true");//inicializar el servidor
        props.put("mail.smtp.auth", "true");//autentificacion
        props.put("mail.smtp.socketFactory.port", puertoEnvio);//activar el puerto
        props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "false");


        //SecurityManager security = System.getSecurityManager();

        try {

            Authenticator auth = new autentificadorSMTP();//autentificar el correo
            Session session = Session.getInstance(props, auth);//se inica una session
            // session.setDebug(true);


            MimeMessage msg = new MimeMessage(session);//se crea un objeto donde ira la estructura del correo

            msg.setText(mensaje.value);//seteo el cuertpo del mensaje
            msg.setSubject(mensaje.index);//setea asusto (opcional)
            msg.setFrom(new InternetAddress(correo));//agrega la la propiedad del correo origen
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(mailReceptor));//agrega el destinatario

            Transport.send(msg);//envia el mensaje

            JOptionPane.showMessageDialog(null, "Email enviado");//alerta de que mensaje fue enviado correctamente

        } catch (Exception mex) {
        	mex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Email no enviado");

        }

    }


    private class autentificadorSMTP extends javax.mail.Authenticator {

        @Override
        public PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(correo, password);//autentifica tanto el correo como la contraseña
        }

    }
    
   /*
    public static void main(String[] args) {
    	
    	APair<String, String> pareja = new APair<String, String>("CABECERA", "CUERPO");
    	ArrayList<String> lista = new ArrayList<String>();
    	lista.add("deuzumNoReply@gmail.com");
		MailBot mb1 = new MailBot("MailBot1", pareja ,lista);
		mb1.execute();
	}
	*/

}


