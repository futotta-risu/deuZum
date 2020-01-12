package io.github.fatsquirrels.deuzum.IA;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import io.github.fatsquirrels.deuzum.utils.math.APair;

public class ClusterGroup  implements Serializable{

	private static final long serialVersionUID = -4461939921541058341L;

	HashMap<ClusterElement, Integer> dataCategory;
	
	int dataWidth = -1;
	
	public ClusterGroup() {
		dataCategory = new HashMap<ClusterElement, Integer>();
	}
	public void addElement(ClusterElement element) {
		if(dataWidth==-1) 
			dataWidth = element.getWidth();
		if(dataWidth!= element.getWidth()) 
			System.err.println("Error en el tamanio de la data");
		dataCategory.put(element, -1);
	}
	
	public void setCategory(ClusterElement index, Integer category) {
		dataCategory.put(index,category);
	}
	
	public APair<ArrayList<ClusterElement>,double[][]> prepareData(){
		
		int rows = dataCategory.size();
		int columns = dataWidth;

		double[][] realData = new double[rows][columns];
		int i = 0;
		
		ArrayList<ClusterElement> orden = new ArrayList<ClusterElement>();
		
		for(Entry<ClusterElement, Integer> element : dataCategory.entrySet()) {
			ArrayList<Double> tempData = element.getKey().getData();
			for(int j = 0; j < tempData.size(); j++)
				realData[i][j] = tempData.get(j);
			orden.add(element.getKey());
			i++;
		}
		return new APair<ArrayList<ClusterElement>,double[][]>(orden,realData);
		
	}
	public HashMap<ClusterElement, Integer> getCategory(){
		return this.dataCategory;
	}
	
	
}
