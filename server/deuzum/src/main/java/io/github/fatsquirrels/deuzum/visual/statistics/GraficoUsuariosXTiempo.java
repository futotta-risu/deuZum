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

public class GraficoUsuariosXTiempo extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<APair<String,Integer>> cantidades;
	
	
	
	public GraficoUsuariosXTiempo(ArrayList<APair<String, Integer>> data){
		this.cantidades = data;
		Container cp = this.getContentPane();
		
		cp.add(crearGraficoUsuariosXTiempo());
		
		setVisible(true);
		setSize(500,500);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("Grafico cantidad transferida por usuario");
	}



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
	
private HashMap<String, Integer> contarRepeticionesFecha() {
		
		HashMap<String, Integer> mapaResult = new HashMap<String,Integer>();
		for (APair<String, Integer> pareja : cantidades) {
			if(mapaResult.isEmpty()) {
				mapaResult.put(pareja.getIndex(), pareja.getValue());
			}else {
				boolean existe = false;
				for (Entry<String, Integer> entry : mapaResult.entrySet()) {			
					if(pareja.getIndex().equals(entry.getKey())) { 
						mapaResult.replace(pareja.getIndex(),entry.getValue()+pareja.getValue());
						existe = true;
						break;
					}
				}
				if(!existe) {
					mapaResult.put(pareja.getIndex(), pareja.getValue());
				}
			}
		}
		
		for (Entry<String, Integer> entry : mapaResult.entrySet()) {
			System.out.println(entry.getKey() + " con valor " + entry.getValue() );
		}
		return mapaResult;
	}

}
