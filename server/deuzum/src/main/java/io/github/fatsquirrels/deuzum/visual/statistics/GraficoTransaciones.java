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
 * Esta clase permite crear ventanas que contienen un Grafico las repeticiones de cantidad de dinero transferidas
 * en transacciones normales (no grupales).
 */
public class GraficoTransaciones{

	private static List<Integer> cantidades;
	
	/**
	 * Constructor de la ventana que recibe un Lista con la informacion a procesar.
	 * @param data Contiene un Lista con varios Enteros [CantidadTransferida]
	 * @see crearGraficoBarras
	 */
	public GraficoTransaciones(List<Integer> data) {
		GraficoTransaciones.cantidades = data;
		crearGraficoBarras();
	}
	
	/**
	 * Este metodo crea un grafico de barras y lo devuelve como un objeto ChartFrame
	 * @return ChartFrame Ventana que contiene el grafico
	 * @see contarRepeticiones
	 */
	private static ChartFrame crearGraficoBarras() {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		
		HashMap<Integer, Integer > mapaNumeros = contarRepeticiones();
		
		for (Entry<Integer, Integer> entry : mapaNumeros.entrySet()) {
			dataset.setValue(entry.getValue(), ""+2020, entry.getKey());
		}		
		
		JFreeChart chart = ChartFactory.createBarChart("Repeticiones de cantidad transferida", null, 
		null, dataset, PlotOrientation.VERTICAL, true, true, false);
		
		CategoryPlot plot = chart.getCategoryPlot();
	    plot.getRenderer().setSeriesPaint(0, new Color(0, 0, 255));
		  
		ChartFrame frame = new ChartFrame("Grafico de repeticiones de cantidades de dinero transferidas", chart);
		frame.pack();
		frame.setVisible(true);
	    
		return frame;
	}
	
	/**
	 * Este metodo recorre la lista de Integers y acumula las repeticiones de las cantidades
	 * de dinero transferidas.
	 * @return HashMap<String, Integer> Mapa que contiene las repeticiones de cantidad transferida. 
	 */
	public static HashMap<Integer, Integer> contarRepeticiones() {
		
		HashMap<Integer, Integer > mapaNumeros = new HashMap<Integer, Integer>();
		for (Integer integer : cantidades) {		
			
			if(mapaNumeros.isEmpty()) {
				mapaNumeros.put(integer, 1);
			}else {
				boolean existe = false;
				for (Entry<Integer, Integer> entry : mapaNumeros.entrySet()) {			
					if(integer == entry.getKey()) { 
						entry.setValue(entry.getValue()+1);
						existe = true;
						break;
					}
				}
				if(!existe) {
					mapaNumeros.put(integer, 1);
				}
			}
		}
		
		return mapaNumeros;
		
	}
	
	
	
}