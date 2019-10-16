package deustoZumServer.IA;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

import deustoZumServer.Algorithms.Math.Metrics;
import deustoZumServer.Algorithms.Math.Vectors;

public class Clustering {
	
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
	
	public static ArrayList<int[]> MSC(ArrayList<double[][]> users, int[] clusters, int[] convergencePeriod){
		ArrayList<int[]> resultMatrix = new ArrayList<int[]>();
		int totalCases = users.size();
		for(int i = 0; i < totalCases; i++) 
			resultMatrix.add(MSC(users.get(i), clusters[i],convergencePeriod[i]));
		
		return resultMatrix;
		
	}
	
	public static int[] MSC(double[][] users, int clusters, int convergencePeriod) {
		if(users.length==0) return null;
		// TODO convergencePeriod 0, clusters 0 o <0
		int dimension = users[0].length;
		int points = users.length;
		Random gen = new Random();
		// Asumiremos un espacio normalizado
		
		double[][] clusterPoints = new double[clusters][dimension];
		
		for(int i = 0; i < clusters; i++)
			for(int j = 0; j < dimension; j++) 
				clusterPoints[i][j] = 1-2*gen.nextDouble();
		
		for(int n = 0; n < convergencePeriod; n++) 
			for(int i = 0; i < clusters; i++) {
				double[] shift = new double[dimension];
				int totalMove = 0;
				
				for(int j = 0; j < points;j++) 
					if(Metrics.flatKernel(clusterPoints[i],0.1,  users[j])>0) {
						shift = Vectors.sumV(shift, users[j]);
						totalMove++;
					}
				shift = Vectors.divVC(shift, totalMove);
				clusterPoints[i] = Vectors.sumV(clusterPoints[i], shift);
			}
		
		
		
		
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
		int actCluster = 1;
		for(int i = 0; i < popSize; i++) {
			if(cluster[i]==0) 
				cluster[i]= actCluster++;
			
			for(int j = i+1; j < popSize; j++) {
				if(Metrics.euclideanDistance(users[i],users[j])<radius) {
					cluster[j]=cluster[i];
				}
			}
			
		}
		
		return cluster;
	}
	
}
