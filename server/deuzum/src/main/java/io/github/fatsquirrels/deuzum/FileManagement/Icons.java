package io.github.fatsquirrels.deuzum.FileManagement;

import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;


/**
 * Clase Icono
 * Contiene el metodo para cargar un Icono
 * @see #loadIcon
 */
public class Icons {

	/**
	 * Metodo encargado de devolver un icono reescalado situado en la carpeta icons
	 * @param fileName Nombre del archivo
	 * @param size Tamaño del icono
	 * @return Devuelve un ImageIcon con el icono solicitado
	 */
	public static Icon loadIcon(String fileName, int size) {
		// TODO a�adir en algun lado el path para los iconos
		Icon playIcon = new ImageIcon("data/img/icons/"+fileName);
		Image img = ((ImageIcon) playIcon).getImage().getScaledInstance(size, size,  java.awt.Image.SCALE_SMOOTH);
		return new ImageIcon(img);

	}
	
}
