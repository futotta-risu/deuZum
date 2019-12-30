package io.github.fatsquirrels.deuzum.visual.statistics;

import java.awt.Color;
import java.awt.Container;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import io.github.fatsquirrels.deuzum.utils.math.APair;

public class GraficoAportacionesProyecto extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static ArrayList<APair<Integer,Integer>> cantidades;
	
	public GraficoAportacionesProyecto(ArrayList<APair<Integer,Integer>> data) {
		GraficoAportacionesProyecto.cantidades = data;
		Container cp = this.getContentPane();
		

		cp.add(crearGraficoBarras());
		
		
		setVisible(true);
		setSize(500,500);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("Grafico Aportaciones en Proyecto");
	}

	private static ChartFrame crearGraficoBarras() {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		
		HashMap<Integer, Integer > mapaNumeros = contarRepeticiones();
		
		for (Entry<Integer, Integer> entry : mapaNumeros.entrySet()) {
			dataset.setValue(entry.getValue(), ""+2019, entry.getKey());
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
