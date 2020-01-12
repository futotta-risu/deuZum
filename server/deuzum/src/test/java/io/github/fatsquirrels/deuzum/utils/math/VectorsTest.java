package io.github.fatsquirrels.deuzum.utils.math;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import io.github.fatsquirrels.deuzum.utils.math.Vectors;


@DisplayName("Math-> Vectors Specifications")
public class VectorsTest {

	@Test
	public void testSumV() {
		double[] arr1 = {0.5, 0.5, 0.5};
		double[] arr2 = {0.5, 0.5, 0.5};	
		double[] result = Vectors.sumV(arr1, arr2);
		double[] expected = {1.0, 1.0, 1.0};
		assertEquals(true,Arrays.equals(expected,result) );
	}
	
	@Test
	public void testSumVC() {
		double[] arr1 = {0.5, 0.5, 0.5};
		double val = 0.5;	
		double[] result = Vectors.sumVC(arr1, val);
		double[] expected = {1, 1, 1};
		assertEquals(true,Arrays.equals(expected,result) );
	}
	
	@Test
	public void testSubV() {
		double[] arr1 = {0.5, 0.5, 0.5};
		double[] arr2 = {0.5, 0.5, 0.5};	
		double[] result = Vectors.subV(arr1, arr2);
		double[] expected = {0, 0, 0};
		assertEquals(true,Arrays.equals(expected,result) );
	}
	
	@Test
	public void testSubVC() {
		double[] arr1 = {0.5, 0.5, 0.5};
		double val = 2;	
		double[] result = Vectors.subVC(arr1, val);
		double[] expected = {-1.5, -1.5, -1.5};
		assertEquals(true,Arrays.equals(expected,result) );
	}
	
	@Test
	public void testDivVC() {
		double[] arr1 = {0.5, 0.5, 0.5};
		double val = 3;	
		double[] result = Vectors.divVC(arr1, val);
		double[] expected = {0.5/3, 0.5/3, 0.5/3};
		assertEquals(true,Arrays.equals(expected,result) );
	}
	
	@Test
	public void testNormalize() {
		double[] arr1 = {0.5, 0.5, 0.5};
		double[] result = Vectors.normalize(arr1);
		double[] expected = {Math.sqrt((double) 1/3), Math.sqrt((double) 1/3), Math.sqrt((double)1/3)};
		
		 //0.1 es el epsilon que escogemos por el tema de aproximaciones de raiz cuadrada
		assertEquals(true,Arrays.stream(Vectors.subV(expected,result)).parallel().reduce(0, (a,b)-> a+b)<0.1);
	}
	
	@Test 
	public void testIsEquals() {
		assertEquals(true, Vectors.isEqual(new double[] {0,0,0,0}, new double[] {0,0,0,0}));
		assertEquals(false, Vectors.isEqual(new double[] {0,0,0,0}, new double[] {0.1,0,0,0}));
	}
	
	@Test 
	public void testIsZero() {
		assertEquals(true, Vectors.isZero(new double[] {0,0,0,0}));
		assertEquals(false, Vectors.isZero(new double[] {0.1,0,0,0}));
	}

}
