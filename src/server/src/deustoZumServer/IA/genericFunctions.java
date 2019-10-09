package deustoZumServer.IA;

import java.util.ArrayList;
import java.util.LinkedList;

import deustoZumServer.Algorithms.Math.Metrics;

public class genericFunctions {
	
	public static ArrayList<int[]> KNN(ArrayList<double[][]> users, ArrayList<int[]> labels, ArrayList<double[][]> newVector){
		ArrayList<int[]> resultMatrix= new ArrayList<int[]>();
		int totalCases = users.size();
		for(int i = 0;  i < totalCases; i++) {
			int KNNCaseSize = newVector.get(i).length;
			// Para cada caso de KNN
			int[] tempVal = new int[KNNCaseSize];
			for(int j = 0; j < KNNCaseSize; j++) 
				// Para cada vector que queremos comprobar
				tempVal[j] = KNN(users.get(i), labels.get(i), newVector.get(i)[j]);
			
			resultMatrix.add(tempVal);
		}
		return resultMatrix;
	}
	
	public static int KNN(double[][] users, int[] lables, double[] newVector) {
		// TODO Lander, crea esta funcion
		return 0;
	}
	
	public static ArrayList<int[]> KMC(ArrayList<double[][]> users, int[] clusters){
		ArrayList<int[]> resultMatrix = new ArrayList<int[]>();
		int totalCases = users.size();
		for(int i = 0; i < totalCases; i++) 
			resultMatrix.add(KMC(users.get(i), clusters[i]));
		
		return resultMatrix;
		
	}
	
	public static int[] KMC(double[][] users, int clusters) {
		
		// TODO Implementar
		return null;
	}
	
	public static ArrayList<int[]> DBSCAN(ArrayList<double[][]> users, double[] radius){
		ArrayList<int[]> resultMatrix = new ArrayList<int[]>();
		int totalCases = users.size();
		for(int i = 0; i < totalCases; i++) 
			resultMatrix.add(DBSCAN(users.get(i), radius[i]));
		
		return resultMatrix;
	}
	
	public static int[] DBSCAN(double[][] users, double radius){
		int popSize = users.length;
		int[] cluster = new int[popSize];
		Boolean[] bool = new Boolean[popSize];
		int actCluster = 0;
		for(int i = 0; i < popSize; i++) {
			if(bool[i]==true) continue; //Alredy visited
			cluster[i]= actCluster++;
			
			for(int j = i+1; j < popSize; j++) {
				if(bool[j]==true) continue;
				if(Metrics.euclideanDistance(users[i],users[j])<radius) {
					cluster[j]=cluster[i];
					bool[j]=true;
				}
			}
			
		}
		
		return cluster;
	}
	
}
