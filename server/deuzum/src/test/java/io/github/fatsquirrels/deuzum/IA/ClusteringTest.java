package io.github.fatsquirrels.deuzum.IA;



import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName(" Clustering Algorithms Specification")
class ClusteringTest {

	double[][] dots = {{1,1},{1,2},{2,1},{2,2},{1.5,1.5},
			{5,5},{5,7},{7,5},{7,7},{6,6}};

	double[][] dots2 = {{1,1},{1,2},{2,1},{2,2},{1.5,1.5},
			{5,5},{5,7},{7,5},{7,7},{6,6},{11,11},{-5,-5}};
	
	
	@Test
	void MSCBigTest() {
		ArrayList<double[][]> users = new ArrayList<double[][]>();
		users.add(dots);
		double[] kernelSize = new double[] {2};
		ArrayList<int[]> result = new ArrayList<int[]>();
		result.add(new int[] {1,1,1,1,1,2,2,2,2,2});
		assertEquals("Error en el MSC en multitest", Arrays.deepToString(result.toArray()), 
				Arrays.deepToString(Clustering.MSC(users, kernelSize).toArray()));
		//MSC(ArrayList<double[][]> users, double[] kernelSize)
	}
	
	@Test
	void MSCTest() {
		
		//MSC(double[][] users, double kernelSize) 
		assertEquals("Error en el MSC single Test", true, Arrays.equals(Clustering.MSC(dots, 2),new int[]{1,1,1,1,1,2,2,2,2,2}));
	}
	
	@Test
	void DBSCANBigTest() {
		
		ArrayList<double[][]> users = new ArrayList<double[][]>();
		double[] radius = {2.1,2.1};
		int[] minPoints = {3,3};
		
		users.add(dots);
		users.add(dots2);
		
		ArrayList<int[]> equals = new ArrayList<int[]>();
		equals.add(new int[]{1,1,1,1,1,2,2,2,2,2});
		equals.add(new int[] {1,1,1,1,1,2,2,2,2,2,0,0});
		
		ArrayList<int[]> labels = Clustering.DBSCAN(users, radius, minPoints);
		assertAll(
			() -> assertEquals("Error en el primer caso del DBSCAN multiple", true, Arrays.equals(labels.get(0),equals.get(0))),
			() ->  assertEquals("Error en el segundo caso del DBSCAN multiple",true, Arrays.equals(labels.get(1),equals.get(1)))
		);
	}
	
	@Test
	void DBSCAN0Test() {
		assertAll(
			() -> assertEquals("Error en primer caso del DBSCAN0", true, Arrays.equals(Clustering.DBSCAN0(dots, 2.1),new int[]{1,1,1,1,1,2,2,2,2,2})),
			() -> assertEquals("Error en segundo caso del DBSCAN0", true, Arrays.equals(Clustering.DBSCAN0(dots2, 2.1),new int[]{1,1,1,1,1,2,2,2,2,2,3,4}))
		);
		
	}
	
	@Test
	void DBSACNTest() {
		assertAll(
			() -> assertEquals("Error en el primer caso del DBSCAN", true, Arrays.equals(Clustering.DBSCAN(dots, 2.1,3),new int[]{1,1,1,1,1,2,2,2,2,2})),
			() -> assertEquals("Error en el segundo caso del DBSCAN",true, Arrays.equals(Clustering.DBSCAN(dots2, 2.1,3),new int[]{1,1,1,1,1,2,2,2,2,2,0,0}))
		);
		
		
	}
	
	@Test
	void KMCBigTest() {
		ArrayList<double[][]> users = new ArrayList<double[][]>();

		users.add(dots);
		int[] clusters = {2};
		int[] result = Clustering.KMC(users.get(0), clusters[0]);
		
		assertEquals("Error en el KMC multiple",true,  Arrays.equals(result,new int[]{1,1,1,1,1,0,0,0,0,0})
				|| Arrays.equals(result,new int[]{0,0,0,0,0,1,1,1,1,1}));
		// Empieza al azar y el nombre los puntos puede variar, pero siempre entre permutaciones
		
	}
	
	@Test
	 void KMCTest(){
		int[] result = Clustering.KMC(dots, 2);
		assertEquals("Error en el KMC",true,  Arrays.equals(result,new int[]{1,1,1,1,1,0,0,0,0,0})
				|| Arrays.equals(result,new int[]{0,0,0,0,0,1,1,1,1,1}));
		// Empieza al azar y el nombre los puntos puede variar, pero siempre entre permutaciones
	 }
	 

	

}
