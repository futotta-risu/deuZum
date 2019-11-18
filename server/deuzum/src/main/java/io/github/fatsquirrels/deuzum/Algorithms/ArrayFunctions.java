package io.github.fatsquirrels.deuzum.Algorithms;


import io.github.fatsquirrels.deuzum.Algorithms.Math.APair;

public class ArrayFunctions {
	
	
	/**
	 * Dados dos arrays, uno de nombres y uno de valores, devuelve una pareja de arrays las cuales contienen los valores de names y data tales que los valores correspondientes de data no esten en blanco.
	 * @param names
	 * @param data
	 * @return Returns a APair with types
	 */
	public static APair<String[],String[]> getReducedArrayString(String[] names, String[] data){
		if(names.length != data.length) return null;
		int totalEmpty = 0;
		for(int i = 0; i < data.length; i++)
			if(!data[i].isEmpty()) totalEmpty++;
		
		String[] tempN = new String[names.length-totalEmpty];
		String[] tempD= new String[names.length-totalEmpty];
		int j = 0;
		for(int i = 0; i < data.length; i++)
			if(!data[i].isEmpty()) {
				tempN[j] = names[i];
				tempD[j++] = data[i];
			}
		
		return new APair<String[],String[]>(tempN,tempD);
		
	}
	
}
