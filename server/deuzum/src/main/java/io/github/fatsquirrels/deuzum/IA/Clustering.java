package io.github.fatsquirrels.deuzum.IA;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Set;
import java.util.stream.IntStream;

import io.github.fatsquirrels.deuzum.utils.math.Metrics;
import io.github.fatsquirrels.deuzum.utils.math.Pair;
import io.github.fatsquirrels.deuzum.utils.math.Statistics;
import io.github.fatsquirrels.deuzum.utils.math.Vectors;


/**
 * Clase encargada de los algoritmos de clustering de vectores.
 *
 */
public class Clustering {
	
	public enum ClusteringAlgorithm{
		MSC, DBSCAN,KMC
	}
	
	
	/**
	 * Ejecuta el algoritmo KNN sobre una lista de listas de informacion.
	 * 
	 * @param users Lista con una matriz que representa una lista de vectores de usuarios.
	 * @param labels Etiquetas de los usuarios.
	 * @param newVector Vector de vectores de los que queremos saber la etiqueta.
	 * @param kVal Valor de k.
	 * @return Devuelve una lista con las categorias de los vectores.
	 * @see
	 */
	public static final ArrayList<int[]> KNN(ArrayList<double[][]> users, ArrayList<int[]> labels, ArrayList<double[][]> newVector, int[] kVal){
		ArrayList<int[]> resultMatrix= new ArrayList<int[]>();
		int totalCases = users.size();
		for(int i = 0;  i < totalCases; i++) { 
			int KNNCaseSize = newVector.get(i).length;
			// Para cada caso de KNN
			int[] tempVal = new int[KNNCaseSize];
			for(int j = 0; j < KNNCaseSize; j++) 
				// Para cada vector que queremos comprobar
				tempVal[j] = KNN(users.get(i), labels.get(i), newVector.get(i)[j], kVal[i]);
			
			resultMatrix.add(tempVal);
		}
		return resultMatrix;
	}
	
	
	
	/**
	 * Ejecuta el algoritmo KNN sobre una matriz de informacion.
	 * 
	 * @param users Lista de vectores que contiene las coordenadas de los usuarios
	 * @param labels Lista de labels de los vectores
	 * @param newVector Nuevo vector a añadir
	 * @param k Valor de K
	 * @return Label del vector 
	 */
	public static final int KNN(double[][] users, int[] labels, double[] newVector, int k) {
		 
		int usersSize = users.length;
		
		if (usersSize ==0) return -1;
		if(users[0].length!=newVector.length) return -1;
		
		Pair[] distanciasIndex = new Pair[usersSize];
				
		for (int i = 0; i < usersSize; i++) 
			distanciasIndex[i] = new Pair(i,Metrics.euclideanDistance(users[i], newVector));
		
		Arrays.sort(distanciasIndex);	
		int[] results = IntStream.range(0, usersSize).map(i -> labels[distanciasIndex[i].getIndex()]).toArray();
		
		return Statistics.getModa(results);
	}
	/**
	 * Ejecuta el algoritmo MSC sobre una lista de lista de usuarios
	 * @param users Array de matrices de información
	 * @param kernelSize Tamaños de los kernels
	 * @return Lista con las labels de los usuarios
	 */
	
	public static final ArrayList<int[]> MSC(ArrayList<double[][]> users, double[] kernelSize){
		ArrayList<int[]> resultMatrix = new ArrayList<int[]>();
		int totalCases = users.size();
		for(int i = 0; i < totalCases; i++) 
			resultMatrix.add(MSC(users.get(i), kernelSize[i]));
		
		return resultMatrix;
	}
	
	/**
	 * Ejecuta el algoritmo MSC sobre una lista de lista de usuarios
	 * @param users Matriz de informacion
	 * @param kernelSize Tamanio del kernel
	 * @return Lista con las labels de los usuarios
	 */
	public static final int[] MSC(double[][] users, double kernelSize) {
		if(users.length==0) return null;
		
		int dimension = users[0].length;
		int points = users.length;
		// TODO revisar el codigo y optimizar
		// TODO Entender esta linea. Sirve para copiar arrays, pero quiero aprender como funciona stream
		double[][] clusterPoints = Arrays.stream(users).map(double[]::clone).toArray(double[][]::new);
		boolean[] hasConverged = new boolean[points];
		int totalConverged = 0;
		while(totalConverged < points)
			for(int i = 0; i < points; i++) {
				if(hasConverged[i]==true) continue;
				
				double[] shift = new double[dimension];
				int totalMove = 0;
				
				for(int j = 0; j < points;j++) 
					if(Metrics.flatKernel(clusterPoints[i],kernelSize,  users[j])>0) {
						shift = Vectors.sumV(shift, users[j]);
						totalMove++;
					}
				shift = Vectors.divVC(shift, totalMove);
				if(Vectors.isZero(Vectors.subV(shift, clusterPoints[i]))) {
					hasConverged[i] = true;
					totalConverged++;
					continue;
				}
				clusterPoints[i] = shift.clone();
			}
		
		int[] resultClusters = new int[points];
		int actCluster = 0;
		for(int i = 0; i < points-1; i++) {
			if(hasConverged[i]==false) continue;
			resultClusters[i] = ++actCluster;
			for(int j = i+1; j < points; j++) 
				if(Vectors.isEqual(clusterPoints[i], clusterPoints[j])) {
					hasConverged[j] = false;
					resultClusters[j] = actCluster;
				}
			
		}
		return resultClusters;
	}
	
	/**
	 * Ejecuta el algoritmo DBSCAN sobre una matriz de informacion.
	 * @param users Array de matrices de información
	 * @param radius Lista con el tamaño de los radios
	 * @param minPoints Lista con los valores de los puntos minimos
	 * @return Lista con las labels de los usuarios de cada caso
	 * @see #DBSCAN0(double[][], double)
	 * @see #DBSCAN(double[][], double)
	 */
	public static final ArrayList<int[]> DBSCAN(ArrayList<double[][]> users, double[] radius, int[] minPoints){
		ArrayList<int[]> resultMatrix = new ArrayList<int[]>();
		int totalCases = users.size();
		for(int i = 0; i < totalCases; i++) 
			resultMatrix.add(DBSCAN(users.get(i), radius[i], minPoints[i]));
		
		return resultMatrix;
	}
	
	/**
	 * Ejecuta el algoritmo DBSCAN sobre una matriz de informacion asumiendo que el valor de minPoints es 0
	 * @param users Matriz de información
	 * @param radius Radio de la n-esfera
	 * @return Lista con las labels de los usuarios de cada caso
	 * @see #DBSCAN0(double[][], double)
	 */
	public static final int[] DBSCAN0(double[][] users, double radius){
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
	
	/**
	 * Ejecuta el algoritmo DBSCAN sobre una matriz de informacion.
	 * @param users Matriz de información
	 * @param radius Radio de la n-esfera
	 * @param minPoints Numero de puntos minimo
	 * @return Lista con las labels de los usuarios de cada caso
	 * @see #DBSCAN0(double[][], double)
	 */
	public static final int[] DBSCAN(double[][] users, double radius, int minPoints) {
		int popSize = users.length;
		
		int[] clusters = new int[popSize];
		
		boolean[] visited = new boolean[popSize];
		Queue<Integer> colaPuntos = new LinkedList<Integer>();
		
		int[] puntosContacto = new int[popSize];
		
		for(int i = 0; i < popSize-1; i++) 
			for(int j = i+1; j < popSize; j++) 
				if(Metrics.euclideanDistance(users[i], users[j])<radius) {
					puntosContacto[j]++;
					puntosContacto[i]++;
				}
		
		int tempMax = 0;
		int actCluster = 0;
		while(true) {
			// Conseguir el siguiente punto libre
			if(colaPuntos.isEmpty()) {
				while(tempMax<popSize && visited[tempMax])
					tempMax++;
				
				if(tempMax==popSize) break;	
				visited[tempMax] = true;
				
				if(puntosContacto[tempMax]<minPoints) 
					continue;
				
				colaPuntos.add(tempMax);
				
			}
			// Explorar el siguiente cluster
			actCluster++;
			while(!colaPuntos.isEmpty()) {
				int actualPunto = colaPuntos.poll();
				clusters[actualPunto] = actCluster;
				for(int i = actualPunto+1; i < popSize; i++) {
					if(Metrics.euclideanDistance(users[actualPunto], users[i])<radius)
						if(!visited[i] & puntosContacto[actualPunto]>=minPoints) {
							visited[i]=true;
							colaPuntos.add(i);
						}
					
				}
				
			}
		}
		return clusters;
	}
	
	
	/**
	 * Ejecuta el algoritmo KMC sobre una matriz de informacion.
	 * @param users Array de matrices de información
	 * @param clusters Lista con el numero de clusters de cada caso
	 * @return Lista con las labels de los usuarios de cada caso
	 * @see #KMC(double[][], int)
	 */
	public static final ArrayList<int[]> KMC(ArrayList<double[][]> users, int[] clusters){
		ArrayList<int[]> resultMatrix = new ArrayList<int[]>();
		
		for(int i  = 0; i < users.size(); i++) 
			resultMatrix.add(KMC(users.get(i), clusters[i]));
		
		return resultMatrix;
	}
	
	/**
	 * Ejecuta el algoritmo KMC sobre una matriz de informacion
	 * @param users Matriz de información
	 * @param clusters Número de clusters
	 * @return Lista con las labels de los usuarios de cada caso
	 */
	public static final int[] KMC(double[][] users, int clusters){
		
		if(users.length == 0) return null;
		if(clusters > users.length) return null;
		
		
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
		
		while(true){
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
			int convergedCount = 0;
			for(int j = 0; j < clusters; j++) {
				double[] temp = Vectors.divVC(clusterMoves[j], clusterCount[j]);
				if(Arrays.equals(clusterPoints[j], temp))
					convergedCount++;
				else clusterPoints[j] = temp;
			}
			if(convergedCount == clusters) break;
			
		}
		
		return nearCluster;
	}
	
	
}
