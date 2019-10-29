package deustoZumServer.FileManagement;

import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class Icons {

	public static Icon loadIcon(String fileName, int size) {
		// TODO añadir en algun lado el path para los iconos
		Icon playIcon = new ImageIcon("data/img/icons/"+fileName);
		Image img = ((ImageIcon) playIcon).getImage().getScaledInstance(size, size,  java.awt.Image.SCALE_SMOOTH);
		return new ImageIcon(img);

	}
	
}
