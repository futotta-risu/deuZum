package io.github.fatsquirrels.deuzum.visual.statistics;

import java.awt.Container;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class GraficoTransaciones extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int[] cantidades;
		
	public GraficoTransaciones(int[] data) {
		this.cantidades = data;
		Container cp = this.getContentPane();
		
		cp.add(crearGraficoBarras(data));
		
		setVisible(true);
		setSize(500,500);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("Grafico Transaciones");
	}
	private static ChartFrame crearGraficoBarras(int[] array) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		
		HashMap<Integer, Integer > mapaNumeros = contarRepeticiones(array);
		
		for (Entry<Integer, Integer> entry : mapaNumeros.entrySet()) {
			dataset.setValue(entry.getValue(), ""+2019, entry.getKey());
		}		
		
		JFreeChart chart = ChartFactory.createBarChart("Repeticiones de cantidad transferida", null, 
		null, dataset, PlotOrientation.VERTICAL, true, true, false);
		  
		ChartFrame frame = new ChartFrame("Grafico de repeticiones de cantidades de dinero transferidas", chart);
		frame.pack();
		frame.setVisible(true);
	    
		return frame;
	}
	
	
	public static HashMap<Integer, Integer> contarRepeticiones(int[] array) {
		
		HashMap<Integer, Integer > mapaNumeros = new HashMap<Integer, Integer>();
		for (int i = 0; i < array.length; i++) {
			if(mapaNumeros.isEmpty()) {
				mapaNumeros.put(array[i], 1);
			}else {
				boolean existe = false;
				for (Entry<Integer, Integer> entry : mapaNumeros.entrySet()) {			
					if(array[i] == entry.getKey()) { 
						entry.setValue(entry.getValue()+1);
						existe = true;
						break;
					}
				}
				if(!existe) {
					mapaNumeros.put(array[i], 1);
				}
			}
		}
		
		return mapaNumeros;
		
	}
	
	/*public static void main(String[] args) {
		GraficoTransaciones gt = new GraficoTransaciones(new int[] {1,1,1,1,2,2,2,3,3,4});
		gt.setVisible(true);
	}-*/
	
}