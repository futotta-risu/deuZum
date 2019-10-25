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
        
        // Patrón que seguirá las lineas del log
        PatternLayout defaultLayout = new PatternLayout("%p: %d{HH:mm:ss} –> %m%n");
        RollingFileAppender rollingFileAppender = new RollingFileAppender();
       
        //Definimos la ruta del archivo que vamos a crear y le ponemos en el nombre el día en el que se ha creado 
        // además de la hora en milisegundos
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



	
