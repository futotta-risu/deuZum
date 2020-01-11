package io.github.fatsquirrels.deuzum.IA;

import java.io.Serializable;
import java.util.ArrayList;

public class ClusterElement implements Serializable{

	private static final long serialVersionUID = 7119337906803855529L;
	
	String name;
	ArrayList<Double> data;
	
	public ClusterElement() {
		data = new ArrayList<Double>();
	}
	public ClusterElement(ArrayList<Double> dataT) {
		data = new ArrayList<Double>();
		data = dataT;
	}
	public ClusterElement(String nameT, ArrayList<Double> dataT) {
		data = new ArrayList<Double>();
		name = nameT;
		data = dataT;
	}
	public ClusterElement(String nameT, Double... dataT) {
		data = new ArrayList<Double>();
		name = nameT;
		for(Double dat : dataT)
			data.add(dat);
	}
	
	public void setName(String nameT) {
		this.name = nameT;
	}
	
	public String getName() {
		return name;
	}
	
	@Override
	public boolean equals(Object o) {
		if(o instanceof ClusterElement)
			if(((ClusterElement)o).getName().equals(this.getName())) return true;
		return false;
	}
	@Override
	public int hashCode() {
		return this.getName().hashCode();
	}
	
	public int getWidth() {
		return data.size();
	}
	
	public ArrayList<Double> getData(){
		return this.data;
	}
	
	
}
