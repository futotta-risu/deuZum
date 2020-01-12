package io.github.fatsquirrels.deuzum.visual.statistics;

import java.awt.Color;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;


import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;


/**
 * Esta clase permite crear ventanas que contienen un Grafico la cantidad de usuarios
 * registrados a traves del tiempo (solo contiene usuarios que siguen registrados en la BD)
 */
public class GraficoUsuariosXTiempo{

	private List<String> cantidades;
		
	/**
	 * Constructor de la ventana que recibe un ArrayList de Strings con las fechas a procesar.
	 * @param data Contiene un ArrayList con varios Strings [fechaRegistro]
	 * @see crearGraficoBarrasUsuario
	 */
	public GraficoUsuariosXTiempo(List<String> data){
		this.cantidades = data;		
		crearGraficoUsuariosXTiempo();			
	}

	/**
	 * Este metodo crea un grafico lineal y lo devuelve como un objeto ChartFrame
	 * @return ChartFrame Ventana que contiene el grafico
	 * @see contarRepeticiones
	 */
	private ChartFrame crearGraficoUsuariosXTiempo() {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();		
		
		HashMap<String, Integer > mapaNumeros = contarRepeticionesFecha();
		
		for (Entry<String, Integer> entry : mapaNumeros.entrySet()) {
			dataset.addValue(entry.getValue(), "usuarios", entry.getKey());
		}		
		
		JFreeChart chart = ChartFactory.createLineChart("Cantidad de usuarios registrados en el tiempo", null, 
		null, dataset, PlotOrientation.VERTICAL, true, true, false);
	
		CategoryPlot plot = chart.getCategoryPlot();
	    plot.getRenderer().setSeriesPaint(0, new Color(128, 0, 0));
	    
		ChartFrame frame = new ChartFrame("Grafico de cantidad de usuarios registrados en el tiempo", chart);
		frame.pack();
		frame.setVisible(true);
	    
		return frame;
	}
	
	/**
	 * Este metodo recorre la lista de fechas y acumula la cantidad
	 * usuarios registrados en un mismo dia.
	 * @return HashMap<String, Integer> Mapa que contiene los usuarios registrados por dia. 
	 */
	private HashMap<String, Integer> contarRepeticionesFecha() {
		
		HashMap<String, Integer> mapaResult = new HashMap<String,Integer>();
		for (String fecha : cantidades) {
			if(mapaResult.isEmpty()) {
				mapaResult.put(fecha, 1);
			}else {
				boolean existe = false;
				for (Entry<String, Integer> entry : mapaResult.entrySet()) {			
					if(fecha.equals(entry.getKey())) { 
						mapaResult.replace(fecha,entry.getValue()+1);
						existe = true;
						break;
					}
				}
				if(!existe) {
					mapaResult.put(fecha, 1);
				}
			}
		}
		
		return mapaResult;
	}

}
