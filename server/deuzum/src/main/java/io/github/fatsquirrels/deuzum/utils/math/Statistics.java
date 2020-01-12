package io.github.fatsquirrels.deuzum.utils.math;


import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import io.github.fatsquirrels.deuzum.utils.meta.anotations.Tested;

@Tested
/**
 * Clase Estadistica
 * La utilidad de esta clase es obtener diferentes tipos de Modas
 * @see #getModa
 * @see #getCategorizedMode
 * @see #getMode
 */
public final class Statistics {

	/**
	 * Este metodo calcula la moda de un array en un tiempo O(n^2)
	 * @param muestras Array de INTs para el calculo
	 * @return Moda del vector recibido.
	 */
	public static int getModa(int[] muestras) {

	    int actMax= 0;
	    int moda= 0;
	    HashMap<Integer,Integer> reps = new HashMap<Integer,Integer>();
	    for(int i=0; i<muestras.length; i++) {
	    	if(reps.containsKey(muestras[i]))
	    		reps.put(muestras[i], reps.get(muestras[i])+1);
	    	else reps.put(muestras[i], 1);
	    }
	    for(Entry<Integer, Integer> a : reps.entrySet())
	    	if(actMax<a.getValue()) {
	    		actMax = a.getValue();
	    		moda = a.getKey();
	    	}
	   return moda;
	}   
	
	
	/**
	 * Metodo que calcula la moda de un Array en un tiempo O(n)=O(n+k), siempre que k < n
	 * @param categories Array de INTs, debe contener numeros no negativos
	 * @param categorySize >= max( categories ) +1
	 * @return Moda del vector recibido
	 */
	public static final int getCategorizedMode(int[] categories, int categorySize) {
		int[] totalAparition = new int[categorySize];
		for(int i : categories) 
			totalAparition[i]++;
		int maxIndice = 0;
		int maxVal = totalAparition[0];
		for(int i = 0; i < categorySize; i++) 
			if(maxVal < totalAparition[i]) {
				maxVal = totalAparition[i];
				maxIndice = i;
			}
		return maxIndice;
	}
	
	
	//O(n) although technically is O(nlogn) since hashmap doesn't always work O(1)
	/**
	 * Metodo que calcula la moda de un Array de Objects en un tiempo O(nlogn)
	 * @param objets Array de Objects para el calculo de la moda
	 * @return Devuelve el objeto mas frecuente del array
	 */
	public static final Object getMode(Object[] objets) {
		Map<Object, Integer> count = new HashMap<Object, Integer>();
		
		for(Object obj : objets) 
			if(count.containsKey(obj)) count.put(obj, count.get(obj)+1);
			else count.put(obj, 1);
		
		int max = Collections.max(count.values());
		for(Object obj : count.keySet())
			if(count.get(obj)==max)
				return obj;
		
		//As there always is a mode, we never really reach here
		return null; 
	}
	
}
