package io.github.fatsquirrels.deuzum.utils;

import java.awt.Desktop;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class WebpageConnection {
	
	public static void openWebpage(URI uri) throws IOException{
	    Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
	    if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) 
	    	desktop.browse(uri);
	}

	public static void openWebpage(URL url) throws IOException, URISyntaxException{
	   openWebpage(url.toURI());   
	}
	
	public static void openWebpage(String link) throws MalformedURLException, IOException, URISyntaxException {
		openWebpage(new URL(link));
	}

}
