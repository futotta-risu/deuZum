package deustoZumServer.Algorithms.Math;

import static org.junit.Assert.*;

import org.junit.Test;

public class VectorsTest {

	@Test
	public void testSumV() {
		double[] arr1 = {0.5, 0.5, 0.5};
		double[] arr2 = {0.5, 0.5, 0.5};	
		double[] result = Vectors.sumV(arr1, arr2);
		double[] expected = {1, 1, 1};
		//assertEquals(expected, result);
	}
	
	@Test
	public void testSumVC() {
		double[] arr1 = {0.5, 0.5, 0.5};
		double val = 0.5;	
		double[] result = Vectors.sumVC(arr1, val);
		double[] expected = {1, 1, 1};
		//assertEquals(expected, result);
	}
	
	@Test
	public void testSubV() {
		double[] arr1 = {0.5, 0.5, 0.5};
		double[] arr2 = {0.5, 0.5, 0.5};	
		double[] result = Vectors.subV(arr1, arr2);
		double[] expected = {0, 0, 0};
		//assertEquals(expected, result);
	}
	
	@Test
	public void testSubVC() {
		double[] arr1 = {0.5, 0.5, 0.5};
		double val = 2;	
		double[] result = Vectors.subVC(arr1, val);
		double[] expected = {1, 1, 1};
		//assertEquals(expected, result);
	}
	
	@Test
	public void testDivVC() {
		double[] arr1 = {0.5, 0.5, 0.5};
		double val = 3;	
		double[] result = Vectors.divVC(arr1, val);
		double[] expected = {1, 1, 1};
		//assertEquals(expected, result);
	}
	
	@Test
	public void testNormalize() {
		double[] arr1 = {0.5, 0.5, 0.5};
		double[] result = Vectors.normalize(arr1);
		double[] expected = {1, 1, 1};
		assertEquals(expected, result);
	}

}
