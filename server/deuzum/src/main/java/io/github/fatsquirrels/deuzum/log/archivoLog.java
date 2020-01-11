package io.github.fatsquirrels.deuzum.log;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.StreamHandler;

public class archivoLog {
	
	public static Logger logger = Logger.getLogger(archivoLog.class.getName());
	
	public archivoLog(String nombre) {
		logger.setLevel(Level.ALL);
		Handler h = new StreamHandler(System.out, new SimpleFormatter());
		h.setLevel(Level.FINEST);
		
		logger.addHandler(h);
		
		
			try {
				logger.addHandler(new FileHandler("data/logs/" + nombre + ".log.xml"));
			} catch (SecurityException e){ 
				e.printStackTrace(); 
			} catch (IOException e) {
				e.printStackTrace();
			} 
	}
	
    public static void addLine(Level level, String message) {
    	logger.log(level, message);
    }
    
    public static void addLineError(Level level, String message, Exception e) {
    	logger.log(level, message, e);
    }
}



	
