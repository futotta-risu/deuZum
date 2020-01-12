package io.github.fatsquirrels.deuzum.visual.statistics;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;


import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import io.github.fatsquirrels.deuzum.utils.math.APair;

/**
 * Esta clase permite crear ventanas que contienen un Grafico con la cantidad
 * que ha aportado cada usuario a un proyecto en especifico.
 */
public class GraficoAportacionesProyecto{
	
	private static ArrayList<APair<Integer,Integer>> cantidades;
	
	/**
	 * Constructor de la ventana que recibe un ArrayList con la informacion a procesar.
	 * @param data Contiene un ArrayList con varias parejas de dos Integer [idMiembro - cantidadAportada]
	 * @see crearGraficoBarras
	 */
	public GraficoAportacionesProyecto(ArrayList<APair<Integer,Integer>> data) {
		GraficoAportacionesProyecto.cantidades = data;
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
		
		JFreeChart chart = ChartFactory.createBarChart("Aportaciones por Usuario", null, 
		null, dataset, PlotOrientation.VERTICAL, true, true, false);
		
		CategoryPlot plot = chart.getCategoryPlot();
	    plot.getRenderer().setSeriesPaint(0, new Color(0, 0, 255));
		  
		ChartFrame frame = new ChartFrame("Grafico de aportaciones por Usuario", chart);
		frame.pack();
		frame.setVisible(true);
	    
		return frame;
	}
	
	
	/**
	 * Este metodo recorre la lista con las parejas de enteros y acumula el dinero total aportado por usuario
	 * @return HashMap<Integer, Integer> Mapa que contiene la cantidad de dinero transferida 
	 * por usuario
	 */
	public static HashMap<Integer, Integer> contarRepeticiones() {
		
		HashMap<Integer, Integer > mapaNumeros = new HashMap<Integer, Integer>();
		for(APair<Integer,Integer> pareja : cantidades) {		
			
			if(mapaNumeros.isEmpty()) {
				mapaNumeros.put(pareja.getIndex(), pareja.getValue());
			}else {
				boolean existe = false;
				for (Entry<Integer, Integer> entry : mapaNumeros.entrySet()) {			
					if(pareja.getIndex() == entry.getKey()) { 
						mapaNumeros.replace(pareja.getIndex(), entry.getValue() + pareja.getValue());
						existe = true;
						break;
					}
				}
				if(!existe) {
					mapaNumeros.put(pareja.getIndex(), pareja.getValue());
				}
			}
		}	
		return mapaNumeros;
		
	}
	

}
