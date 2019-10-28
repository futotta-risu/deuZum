package deustoZumServer.Log;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.time.LocalTime;

import org.apache.log4j.*;

public class archivoLog {
	
	Date fecha = new Date();
	Logger log;
	Calendar hora = Calendar.getInstance();

    public archivoLog(String workspace) throws IOException {
        log = Logger.getLogger(archivoLog.class);
        
       // Necesitamos establecer el formato de la fecha
        SimpleDateFormat formato = new SimpleDateFormat("dd.MM.yyyy");
        String fechaAc = formato.format(fecha);
        
        /* Patrón que seguirá las lineas del log
        Es un diseño flexible de cadena de caracteres mmediante el cual se establece un formato para el resultado 
        (que depende del patrón de conversión)
        Cada especificador de conversión comienza con un signo de porcentaje (%) y es seguido por modificadores de formato)
        El carácter de conversión especifica el tipo de datos, p. categoría, prioridad, fecha, nombre del hilo. 
        m--> Muestra el nombre del método donde se emitió la solicitud de registro 
        n--> Emite el caracter o caracteres del separador de línea dependiente de la plataforma.*/
        
        PatternLayout defaultLayout = new PatternLayout("%p: %d{HH:mm:ss} –> %m%n");
        RollingFileAppender rollingFileAppender = new RollingFileAppender();
       
        /*Definimos la ruta del archivo que vamos a crear y le ponemos en el nombre el día en el que se ha creado 
         además de la hora en milisegundos */
        rollingFileAppender.setFile(workspace + "logs/archivo_" + fechaAc + "_" + hora.getTimeInMillis() + ".log", true, false, 0);
        rollingFileAppender.setLayout(defaultLayout);
        log.removeAllAppenders();
        log.addAppender(rollingFileAppender);
        log.setAdditivity(false);
    }
    public Logger getLog() {
        return log;
    }
    public void setLog(Logger log) {
        this.log = log;
    }

    public static void main(String[] args) throws IOException {
	new archivoLog("C:/Users/ASUS/Documents/GitHub/deuZum/src/server/src/deustoZumServer/Log");
	}
    
}



	
