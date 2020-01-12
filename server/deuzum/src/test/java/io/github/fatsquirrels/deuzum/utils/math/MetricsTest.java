package io.github.fatsquirrels.deuzum.utils.math;

import static io.github.fatsquirrels.deuzum.utils.math.Metrics.euclideanDistance;
import static io.github.fatsquirrels.deuzum.utils.math.Metrics.flatKernel;
import static org.junit.Assert.*;


import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

@DisplayName("Math-> Metric Specifications")
public class MetricsTest {

	
	@Test
	public void testEuclideanDistance() {
		double[] arr1 = {0.1, 0.2, 0.3};
		double result = euclideanDistance(arr1);
		assertEquals("Error en la distancia euclidiana respecto a 0",result, 0.374166,0.1);
	}
	
	
	@Test
	public void testEclideanDistanceDoubleArray() {
		double[] arr1 = {0.3, 0.2, 0.7};
		double[] arr2 = {0.1, 0.4, 0.3};
		double result = euclideanDistance(arr1, arr2);
		assertEquals("Error en la distancia euclidiana entre dos vectores ",result, 0.489898,0.1);
	}
	
	@Test
	public void testFlatKernel() {
		double[] center = {1, 2, 3};
		double radious = 2;
		double[] newPos = {3, 2, 1};
		double result = flatKernel(center, radious, newPos);
		assertEquals("Error en el flat kernel",0, result,0.01);
			
	}
	
	@Test
	public void getMiniumDistancePoint() {
		double[] point = {1,2};
		double[][] pointList = {{2,3},{5,6},{0,0}};
		int result = Metrics.getMinimumDistancePoint(point, pointList);
		assertEquals("Error en la distancia al punto minimo",0, result);
	
	}
	
	
	

}
