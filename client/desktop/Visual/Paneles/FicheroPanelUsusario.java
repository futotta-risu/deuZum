package Paneles;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
 
public class FicheroPanelUsusario {
	
	
	
	public static void crearArchivo(ArrayList lista) {
		FileWriter flwriter = null;
		try {
			//crea el flujo para escribir en el archivo
			flwriter = new FileWriter("C:\\archivos\\Usuarios.txt");
			//crea un buffer o flujo intermedio antes de escribir directamente en el archivo
			BufferedWriter bfwriter = new BufferedWriter(flwriter);
			
			PanelUsuario usuario=new PanelUsuario();
			
				bfwriter.write(usuario.getTextPaneNombre() + "," + usuario.getTextPaneApellidos() + "," + usuario.getTextPaneTelefono()
						+ "," + usuario.getTextPaneEmail() + "," + usuario.getTextPaneDireccion() + "\n");
			
			//cierra el buffer intermedio
			bfwriter.close();
			System.out.println("Archivo creado satisfactoriamente..");
 
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (flwriter != null) {
				try {//cierra el flujo principal
					flwriter.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	
	//añadir más estudiantes al archivo
	public static void aniadirArchivo(ArrayList lista) {
		FileWriter flwriter = null;
		try {//además de la ruta del archivo recibe un parámetro de tipo boolean, que le indican que se va añadir más registros 
			flwriter = new FileWriter("C:\\archivos\\Usuarios.txt", true);
			BufferedWriter bfwriter = new BufferedWriter(flwriter);
			PanelUsuario usuario=new PanelUsuario();
			
				bfwriter.write(usuario.getTextPaneNombre() + "," + usuario.getTextPaneApellidos() + "," + usuario.getTextPaneTelefono()
						+ "," + usuario.getTextPaneEmail() + "," + usuario.getTextPaneDireccion() + "\n");
			
			bfwriter.close();
			System.out.println("Archivo modificado satisfactoriamente..");
 
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (flwriter != null) {
				try {
					flwriter.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}	
}