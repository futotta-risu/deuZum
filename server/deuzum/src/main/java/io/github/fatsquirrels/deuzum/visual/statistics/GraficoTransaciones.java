package io.github.fatsquirrels.deuzum.visual.statistics;

import java.awt.Color;
import java.awt.Container;

import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;


public class GraficoTransaciones extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Integer> cantidades;
		
	public GraficoTransaciones(List<Integer> data) {
		this.cantidades = data;
		Container cp = this.getContentPane();
		

		cp.add(crearGraficoBarras(data));
		
		
		setVisible(true);
		setSize(500,500);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("Grafico Transaciones");
	}
	private static ChartFrame crearGraficoBarras(List<Integer> array) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		
		HashMap<Integer, Integer > mapaNumeros = contarRepeticiones(array);
		
		for (Entry<Integer, Integer> entry : mapaNumeros.entrySet()) {
			dataset.setValue(entry.getValue(), ""+2019, entry.getKey());
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
	
	
	public static HashMap<Integer, Integer> contarRepeticiones(List<Integer> array) {
		
		HashMap<Integer, Integer > mapaNumeros = new HashMap<Integer, Integer>();
		for (Integer integer : array) {		
			
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