package io.github.fatsquirrels.deuzum.log;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.StreamHandler;




/**
 *  Mediante esta clase, pretendemos que cada vez que nosotros hagamos un cambio, haya algun error en algun
 * metodo/clase, se almacene esa informacion en algun lado.
 * Para ello, hemos usado Logger, creando un archivo log donde aparezca toda la informacion
 * almacenada en la carpeta de logs. 
 */

public class ArchivoLog {
	
	public static Logger logger = Logger.getLogger(ArchivoLog.class.getName());
	
	public ArchivoLog(String nombre) {
		logger.setLevel(Level.ALL);
		Handler h = new StreamHandler(System.out, new SimpleFormatter());
		h.setLevel(Level.FINEST);
		
		logger.addHandler(h);
		
		
			try {
				FileHandler fh = new FileHandler(("data/logs/" + nombre + ".log.xml"),true);
				logger.addHandler(fh);
			} catch (SecurityException e){ 
				e.printStackTrace(); 
			} catch (IOException e) {
				e.printStackTrace();
			} 
	}
	
	
	/**

	 * Aqui podemos encontrar dos metodos que hacen referencia al ArchivoLog
	 * estos seran los metodos a los que se llamaran desde cada clase/ metodo de cada clase para crear el 
	 * archivo log o añadir informaion al mismo.
	 * Desde estos metodos se llamara al metodo ArchivoLog que es mediante el cual se crearan los archivos
	 * o donde se añadira la informacion recibida.
	 * @param level --> Recibe el Level del archivo a añadir, si es de INFO, SEVERE (de errores) ...
	 * @param message --> y el mensaje que debe aparecer respecto al metodo/clase al que se refiere
	 */
	

    public static void addLine(Level level, String message) {
    	logger.log(level, message);
    }

    /**
	 * Aqui podemos encontrar dos metodos que hacen referencia al ArchivoLog
	 * estos seran los metodos a los que se llamaran desde cada clase/ metodo de cada clase para crear el 
	 * archivo log o añadir informaion al mismo.
	 * Desde estos metodos se llamara al metodo ArchivoLog que es mediante el cual se crearan los archivos
	 * o donde se añadira la informacion recibida.
	 * @param level --> Recibe el Level del archivo a añadir, si es de INFO, SEVERE (de errores) ...
	 * @param message --> y el mensaje que debe aparecer respecto al metodo/clase al que se refiere
	 * @param e --> recoge el error del metodo
	 */
	
    
   
    public static void addLineError(Level level, String message, Exception e) {

    	logger.log(level, message, e);
    }
}



	
