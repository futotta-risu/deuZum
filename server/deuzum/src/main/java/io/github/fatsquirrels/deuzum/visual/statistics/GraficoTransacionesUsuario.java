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

public class GraficoTransacionesUsuario extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<APair<Integer,Integer>> cantidades;
	
	public GraficoTransacionesUsuario(ArrayList<APair<Integer, Integer>> data) {
		this.cantidades = data;
		Container cp = this.getContentPane();
		
		cp.add(crearGraficoBarrasUsuario(data));
		
		setVisible(true);
		setSize(500,500);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("Grafico cantidad transferida por usuario");
	}

	private ChartFrame crearGraficoBarrasUsuario(ArrayList<APair<Integer, Integer>> data) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		
		HashMap<Integer, Integer > mapaNumeros = contarRepeticionesUsuario(data);
		
		for (Entry<Integer, Integer> entry : mapaNumeros.entrySet()) {
			dataset.setValue(entry.getValue(), ""+2019, entry.getKey());
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

	private HashMap<Integer, Integer> contarRepeticionesUsuario(ArrayList<APair<Integer, Integer>> data) {
		
		HashMap<Integer, Integer> mapaResult = new HashMap<Integer,Integer>();
		for (APair<Integer, Integer> pareja : data) {
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
