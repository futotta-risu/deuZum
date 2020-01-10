package io.github.fatsquirrels.deuzum.visual.statistics;

import java.awt.Container;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;

import org.jfree.data.general.DefaultPieDataset;


/**
 * Esta clase permite crear ventanas que contienen un Grafico con el porcentaje
 * de usuarios que contienen cada permiso.
 */
public class GraficoPermisos extends JFrame{

	private static final long serialVersionUID = 1L;
	private List<String> cantidades;
	
	/**
	 * Constructor de la ventana que recibe un Lista con la informacion a procesar.
	 * @param data Contiene un Lista con varios Strings [permiso]
	 * @see crearGraficoPermisosUsuario
	 */
	public GraficoPermisos(List<String> data) {
		this.cantidades = data;
		Container cp = this.getContentPane();
		
		cp.add(crearGraficoPermisosUsuario());
		
		setVisible(true);
		setSize(500,500);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("Grafico Permisos de usuario");
	}

	/**
	 * Este metodo crea un grafico circular y lo devuelve como un objeto ChartFrame
	 * @return ChartFrame Ventana que contiene el grafico
	 * @see contarRepeticiones
	 */
	private ChartFrame crearGraficoPermisosUsuario() {
		DefaultPieDataset dataset = new DefaultPieDataset();
		
		HashMap<String, Integer > mapaNumeros = contarRepeticiones();
		
		for (Entry<String, Integer> entry : mapaNumeros.entrySet()) {
			dataset.setValue(entry.getKey(), entry.getValue());
		}		
		
		JFreeChart chart = ChartFactory.createPieChart3D("Permisos de usuario",
		dataset, true, true, false);
				  
		ChartFrame frame = new ChartFrame("Grafico de Permisos de usuarios", chart);
		frame.pack();
		frame.setVisible(true);
	    
		return frame;
	}
	
	/**
	 * Este metodo recorre la lista de strings y acumula las repeticiones de lo permisos de usuario
	 * @return HashMap<String, Integer> Mapa que contiene la repeticion de permisos de usuario 
	 */
	private HashMap<String, Integer> contarRepeticiones() {
		HashMap<String, Integer > mapaNumeros = new HashMap<String, Integer>();
		
		for (String permiso : cantidades) {	
			if(mapaNumeros.isEmpty()) {
				mapaNumeros.put(permiso, 1);
			}else {
				boolean existe = false;
				for (Entry<String, Integer> entry : mapaNumeros.entrySet()) {	
					if(permiso.equals(entry.getKey())) { 
						mapaNumeros.replace(permiso, entry.getValue() + 1);
						existe = true;
						break;
					}
				}
				if(!existe) {
					mapaNumeros.put(permiso, 1);
				}
			}
		}		
		return mapaNumeros;
	}

}
