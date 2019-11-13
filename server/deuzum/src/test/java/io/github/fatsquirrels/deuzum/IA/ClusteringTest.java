package io.github.fatsquirrels.deuzum.IA;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

class ClusteringTest {

	@Test
	void KNNBigTest() {
		double[][] dots = {{1,1},{1,2},{2,1},{2,2},{1.5,1.5},
				{5,5},{5,7},{7,5},{7,7},{6,6}};
		int[] labels = {0,0,0,0,0,1,1,1,1,1};
		double[][] newVector = {{3,1},{4,3},{6,7}};
		ArrayList<double[][]> usersM = new ArrayList<double[][]>();
		ArrayList<int[]> labelsM = new ArrayList<int[]>();
		usersM.add(dots);
		labelsM.add(labels);
		ArrayList<double[][]> tests = new ArrayList<double[][]>();
		tests.add(newVector);
		int[] k = {3};
		//KNN(ArrayList<double[][]> users, ArrayList<int[]> labels, ArrayList<double[][]> newVector, int[] kVal)
		ArrayList<int[]> result = new ArrayList<int[]>();
		result.add(new int[]{0,0,1});
		
		// TODO cambiar esta linea
		assertEquals(Arrays.deepToString(result.toArray()), 
				Arrays.deepToString(Clustering.KNN(usersM,  labelsM, tests, k).toArray()));
	}
	@Test
	void KNNTest() {
		double[][] dots = {{1,1},{1,2},{2,1},{2,2},{1.5,1.5},
				{5,5},{5,7},{7,5},{7,7},{6,6}};
		int[] labels = {0,0,0,0,0,1,1,1,1,1};
		double[] newVector1 = {3,1};
		double[] newVector2 = {4,3};
		double[] newVector3 = {6,7};
		assertEquals(0,Clustering.KNN(dots, labels, newVector1, 3));
		assertEquals(0,Clustering.KNN(dots, labels, newVector2, 3));
		assertEquals(1,Clustering.KNN(dots, labels, newVector3, 3));
		//KNN(double[][] users, int[] labels, double[] newVector, int k)
	}
	@Test
	void MSCBigTest() {
		double[][] dots = {{1,1},{1,2},{2,1},{2,2},{1.5,1.5},
				{5,5},{5,7},{7,5},{7,7},{6,6}};
		ArrayList<double[][]> users = new ArrayList<double[][]>();
		users.add(dots);
		double[] kernelSize = new double[] {2};
		ArrayList<int[]> result = new ArrayList<int[]>();
		result.add(new int[] {1,1,1,1,1,2,2,2,2,2});
		assertEquals(Arrays.deepToString(result.toArray()), 
				Arrays.deepToString(Clustering.MSC(users, kernelSize).toArray()));
		//MSC(ArrayList<double[][]> users, double[] kernelSize)
	}
	@Test
	void MSCTest() {
		double[][] dots = {{1,1},{1,2},{2,1},{2,2},{1.5,1.5},
				{5,5},{5,7},{7,5},{7,7},{6,6}};
		
		//MSC(double[][] users, double kernelSize) 
		assertEquals(true, Arrays.equals(Clustering.MSC(dots, 2),new int[]{1,1,1,1,1,2,2,2,2,2}));
	}
	@Test
	void DBSCANBigTest() {
		double[][] dots = {{1,1},{1,2},{2,1},{2,2},{1.5,1.5},
				{5,5},{5,7},{7,5},{7,7},{6,6}};
		double[][] dots2 = {{1,1},{1,2},{2,1},{2,2},{1.5,1.5},
				{5,5},{5,7},{7,5},{7,7},{6,6},{11,11},{-5,-5}};
		ArrayList<double[][]> users = new ArrayList<double[][]>();
		double[] radius = {2.1,2.1};
		int[] minPoints = {3,3};
		users.add(dots);
		users.add(dots2);
		ArrayList<int[]> equals = new ArrayList<int[]>();
		equals.add(new int[]{1,1,1,1,1,2,2,2,2,2});
		equals.add(new int[] {1,1,1,1,1,2,2,2,2,2,0,0});
		ArrayList<int[]> labels = Clustering.DBSCAN(users, radius, minPoints);
		for(int i = 0; i < 2; i++)
			assertEquals(true, Arrays.equals(labels.get(i),equals.get(i)));
		//DBSCAN(ArrayList<double[][]> users, double[] radius, int[] minPoints)
	}
	@Test
	void DBSCAN0Test() {
		double[][] dots = {{1,1},{1,2},{2,1},{2,2},{1.5,1.5},
				{5,5},{5,7},{7,5},{7,7},{6,6}};
		double[][] dots2 = {{1,1},{1,2},{2,1},{2,2},{1.5,1.5},
				{5,5},{5,7},{7,5},{7,7},{6,6},{11,11},{-5,-5}};
		assertEquals(true, Arrays.equals(Clustering.DBSCAN0(dots, 2.1),new int[]{1,1,1,1,1,2,2,2,2,2}));
		assertEquals(true, Arrays.equals(Clustering.DBSCAN0(dots2, 2.1),new int[]{1,1,1,1,1,2,2,2,2,2,3,4}));
	}
	@Test
	void DBSACNTest() {
		double[][] dots = {{1,1},{1,2},{2,1},{2,2},{1.5,1.5},
				{5,5},{5,7},{7,5},{7,7},{6,6}};
		double[][] dots2 = {{1,1},{1,2},{2,1},{2,2},{1.5,1.5},
				{5,5},{5,7},{7,5},{7,7},{6,6},{11,11},{-5,-5}};
		assertEquals(true, Arrays.equals(Clustering.DBSCAN(dots, 2.1,3),new int[]{1,1,1,1,1,2,2,2,2,2}));
		assertEquals(true, Arrays.equals(Clustering.DBSCAN(dots2, 2.1,3),new int[]{1,1,1,1,1,2,2,2,2,2,0,0}));
		//DBSCAN(double[][] users, double radius, int minPuntos) 
	}
	@Test
	void KMCBigTest() {
		ArrayList<double[][]> users = new ArrayList<double[][]>();
		double[][] dots = {{1,1},{1,2},{2,1},{2,2},{1.5,1.5},
				{5,5},{5,7},{7,5},{7,7},{6,6}};
		users.add(dots);
		int[] clusters = {2};
		for(int i = 0; i < 1; i++) {
			int[] result = Clustering.KMC(users.get(i), clusters[i]);
			assertEquals(true,  Arrays.equals(result,new int[]{1,1,1,1,1,0,0,0,0,0})
					|| Arrays.equals(result,new int[]{0,0,0,0,0,1,1,1,1,1}));
		}
		//KMC(ArrayList<double[][]> users, int[] clusters)
	}
	
	@Test
	 void KMCTest(){
		double[][] dots = {{1,1},{1,2},{2,1},{2,2},{1.5,1.5},
				{5,5},{5,7},{7,5},{7,7},{6,6}};
		 //KMC(double[][] users, int clusters)
		int[] result = Clustering.KMC(dots, 2);
		assertEquals(true,  Arrays.equals(result,new int[]{1,1,1,1,1,0,0,0,0,0})
				|| Arrays.equals(result,new int[]{0,0,0,0,0,1,1,1,1,1}));
		// Empieza al azar y el nombre los puntos puede variar, pero siempre entre permutaciones
	 }
	 

	

}
