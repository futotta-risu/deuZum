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
 * Esta clase permite crear ventanas que contienen un Grafico la cantidad de dinero transferida por cada
 * usuario en transacciones normales (no grupales).
 */
public class GraficoTransacionesUsuario{

	private ArrayList<APair<Integer,Integer>> cantidades;
	
	/**
	 * Constructor de la ventana que recibe un Lista con la informacion a procesar.
	 * @param data Contiene un ArrayList con varias parejas de Integers [IdMiembro - cantidadTransferida]
	 * @see crearGraficoBarrasUsuario
	 */
	public GraficoTransacionesUsuario(ArrayList<APair<Integer, Integer>> data) {
		this.cantidades = data;		
		crearGraficoBarrasUsuario();
	}

	/**
	 * Este metodo crea un grafico de barras y lo devuelve como un objeto ChartFrame
	 * @return ChartFrame Ventana que contiene el grafico
	 * @see contarRepeticiones
	 */
	private ChartFrame crearGraficoBarrasUsuario() {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		
		HashMap<Integer, Integer > mapaNumeros = contarRepeticionesUsuario();
		
		for (Entry<Integer, Integer> entry : mapaNumeros.entrySet()) {
			dataset.setValue(entry.getValue(), ""+2020, entry.getKey());
		}		
		
		JFreeChart chart = ChartFactory.createBarChart("Cantidad de dinero transferida por usuario", null, 
		null, dataset, PlotOrientation.VERTICAL, true, true, false);
	
		CategoryPlot plot = chart.getCategoryPlot();
	    plot.getRenderer().setSeriesPaint(0, new Color(128, 0, 0));
	    
		ChartFrame frame = new ChartFrame("Grafico de cantidades de dinero transferidas por usuario", chart);
		frame.pack();
		frame.setVisible(true);
	    
		return frame;
	}

	/**
	 * Este metodo recorre las parejas de Integers y acumula las cantidades
	 * de dinero transferidas por usuario.
	 * @return HashMap<String, Integer> Mapa que contiene las antidades transferidas por usuario. 
	 */
	private HashMap<Integer, Integer> contarRepeticionesUsuario() {
		
		HashMap<Integer, Integer> mapaResult = new HashMap<Integer,Integer>();
		for (APair<Integer, Integer> pareja : cantidades) {
			if(mapaResult.isEmpty()) {
				mapaResult.put(pareja.getIndex(), pareja.getValue());
			}else {
				boolean existe = false;
				for (Entry<Integer, Integer> entry : mapaResult.entrySet()) {			
					if(pareja.getIndex() == entry.getKey()) { 
						entry.setValue(entry.getValue()+pareja.getValue());
						existe = true;
						break;
					}
				}
				if(!existe) {
					mapaResult.put(pareja.getIndex(), pareja.getValue());
				}
			}
		}
		return mapaResult;
	}

}
