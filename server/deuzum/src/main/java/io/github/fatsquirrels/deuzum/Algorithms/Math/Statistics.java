package io.github.fatsquirrels.deuzum.Algorithms.Math;


import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class Statistics {

	// O(n^2)
	public static int getModa(int[] muestras) {

	    int maximoNumRepeticiones= 0;
	    int moda= 0;

	    for(int i=0; i<muestras.length; i++)
	    {
	        int numRepeticiones= 0;
	        for(int j=0; j<muestras.length; j++)
	        {
	            if(muestras[i]==muestras[j])
	            {
	                numRepeticiones++;
	            }  
	            if(numRepeticiones>maximoNumRepeticiones)
	            {
	                moda= muestras[i];
	                maximoNumRepeticiones= numRepeticiones;
	            }  
	        }
	    }  
	   return moda;
	}   
	
	//O(n) (Since k<n, O(n+k)=O(n))
	// Categories debe contener numeros no negativos y categorySize debe ser >= max(categories)+1
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
