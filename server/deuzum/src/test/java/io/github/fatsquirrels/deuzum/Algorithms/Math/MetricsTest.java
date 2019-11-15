package io.github.fatsquirrels.deuzum.Algorithms.Math;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

public class MetricsTest {

	
	
	
	@Test
	public void testEuclideanDistance() {
		double[] arr1 = {0.1, 0.2, 0.3};
		double result = Metrics.euclideanDistance(arr1);
		assertEquals(true, Math.abs(result-0.374166)<0.1);
	}
	
	
	@Test
	public void testEclideanDistanceDoubleArray() {
		double[] arr1 = {0.3, 0.2, 0.7};
		double[] arr2 = {0.1, 0.4, 0.3};
		double result = Metrics.euclideanDistance(arr1, arr2);
		assertEquals(true, Math.abs(result-0.489898)<0.1);
	}
	
	@Test
	public void testFlatKernel() {
		double[] center = {1, 2, 3};
		double radious = 2;
		double[] newPos = {3, 2, 1};
		double result = Metrics.flatKernel(center, radious, newPos);
		assertEquals(0, result);
			
	}
	
	@Test
	public void getMiniumDistancePoint() {
		//TODO implementar este test
		double[] point = null;
		double[][] pointList = null;
		//int result = Metrics.getMinimumDistancePoint(point, pointList);
		//assertEquals(expected, result);
	
	}
	
	
	

}
