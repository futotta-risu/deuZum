package io.github.fatsquirrels.deuzum.visual.statistics;

import java.awt.Container;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;

import org.jfree.data.general.DefaultPieDataset;

import io.github.fatsquirrels.deuzum.utils.math.APair;

public class GraficoPermisos extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<APair<String, Integer>> cantidades;
	
	public GraficoPermisos(ArrayList<APair<String, Integer>> data) {
		this.cantidades = data;
		Container cp = this.getContentPane();
		
		cp.add(crearGraficoPermisosUsuario());
		
		setVisible(true);
		setSize(500,500);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("Grafico Permisos de usuario");
	}

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

	private HashMap<String, Integer> contarRepeticiones() {
		HashMap<String, Integer > mapaNumeros = new HashMap<String, Integer>();
		
		for (APair<String, Integer> pareja : cantidades) {	
			if(mapaNumeros.isEmpty()) {
				mapaNumeros.put(pareja.getIndex(), 1);
			}else {
				boolean existe = false;
				for (Entry<String, Integer> entry : mapaNumeros.entrySet()) {	
					if(pareja.getIndex().equals(entry.getKey())) { 
						mapaNumeros.replace(pareja.getIndex(), entry.getValue() + 1);
						existe = true;
						break;
					}
				}
				if(!existe) {
					mapaNumeros.put(pareja.getIndex(), 1);
				}
			}
		}		
		return mapaNumeros;
	}

}
