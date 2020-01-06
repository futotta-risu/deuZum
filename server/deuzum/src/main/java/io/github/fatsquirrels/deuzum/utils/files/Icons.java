package io.github.fatsquirrels.deuzum.utils.files;

import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import io.github.fatsquirrels.deuzum.res.Paths;

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
		Icon playIcon = new ImageIcon(Paths.IconPath+fileName);
		Image img = ((ImageIcon) playIcon).getImage().getScaledInstance(size, size,  java.awt.Image.SCALE_SMOOTH);
		return new ImageIcon(img);

	}
	
}
