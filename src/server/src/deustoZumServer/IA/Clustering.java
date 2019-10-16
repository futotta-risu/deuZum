package deustoZumServer.IA;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import deustoZumServer.Algorithms.Math.Metrics;
import deustoZumServer.Algorithms.Math.Vectors;

public class Clustering {
	
	public static final ArrayList<int[]> KNN(ArrayList<double[][]> users, ArrayList<int[]> labels, ArrayList<double[][]> newVector){
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
	
	public static final int KNN(double[][] users, int[] lables, double[] newVector) {
		// TODO Lander, crea esta funcion
		return 0;
	}
	
	public static final ArrayList<int[]> MSC(ArrayList<double[][]> users, int[] clusters, int[] convergencePeriod){
		ArrayList<int[]> resultMatrix = new ArrayList<int[]>();
		int totalCases = users.size();
		for(int i = 0; i < totalCases; i++) 
			resultMatrix.add(MSC(users.get(i), clusters[i],convergencePeriod[i]));
		
		return resultMatrix;
		
	}
	
	public static final int[] MSC(double[][] users, int clusters, int convergencePeriod) {
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
	
	public static final ArrayList<int[]> DBSCAN(ArrayList<double[][]> users, double[] radius){
		ArrayList<int[]> resultMatrix = new ArrayList<int[]>();
		int totalCases = users.size();
		for(int i = 0; i < totalCases; i++) 
			resultMatrix.add(DBSCAN(users.get(i), radius[i]));
		
		return resultMatrix;
	}
	
	public static final int[] DBSCAN(double[][] users, double radius){
		int popSize = users.length;
		int[] cluster = new int[popSize];
		int actCluster = 1;
		for(int i = 0; i < popSize; i++) {
			if(cluster[i]==0) 
				cluster[i]= actCluster++;
			
			for(int j = i+1; j < popSize; j++) {
				if(cluster[j]!=0)
					continue;
				
				if(Metrics.euclideanDistance(users[i],users[j])<radius) 
					cluster[j]=cluster[i];
			}
		}
		
		return cluster;
	}
	
	public static final ArrayList<int[]> KMC(ArrayList<double[][]> users, int[] clusters, int[] iterations){
		ArrayList<int[]> resultMatrix = new ArrayList<int[]>();
		
		for(int i  = 0; i < users.size(); i++) 
			resultMatrix.add(KMC(users.get(i), clusters[i], iterations[i]));
		
		return resultMatrix;
	}
	
	public static final int[] KMC(double[][] users, int clusters, int iteration){
		
		if(users.length == 0) return null;
		if(clusters > users[0].length) return null;
		
		
		int userCount = users.length;
		int dimension = users[0].length;
		
		double[][] clusterPoints = new double[clusters][dimension];
		
		// TODO Revisar que algoritmo usar para optener numeros aleatorios
		
		Set<Integer> puntosIniciales = new HashSet<Integer>();
		Random ran = new Random();
		int tempSize = 0;
		while(tempSize< clusters) {
			int n = ran.nextInt(userCount);
			if(puntosIniciales.contains(n))
				continue;
			puntosIniciales.add(n);
			clusterPoints[tempSize] = users[n];
			tempSize++;
		}
		
		
		int[] nearCluster = new int[userCount];
		
		for(int i  = 0; i < iteration; i++) {
			int[] clusterCount = new int[clusters];
			double[][] clusterMoves = new double[clusters][dimension];
			//Fase de calculo de distancias
			for(int j = 0; j < userCount; j++) {
				// Encontramos el cluster mas cercano respecto a el usuario j
				int minCluster = Metrics.getMinimumDistancePoint(users[j], clusterPoints);
				
				nearCluster[j] = minCluster;
				clusterCount[minCluster]++;
				clusterMoves[minCluster] = Vectors.sumV(clusterMoves[minCluster], users[j]);
			}
			
			for(int j = 0; j < clusters; j++)
				clusterPoints[j] = Vectors.divVC(clusterMoves[j], clusterCount[j]);
			
		}
		
		return nearCluster;
	}
	
}
