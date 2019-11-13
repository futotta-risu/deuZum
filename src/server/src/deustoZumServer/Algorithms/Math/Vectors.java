package deustoZumServer.Algorithms.Math;

import java.util.Arrays;

public class Vectors {
	public static double[] sumV(double[] arr1, double[] arr2) {
		if(arr1.length != arr2.length) return null;
		double[] arr3 = new double[arr1.length];
		for(int i = 0; i < arr1.length; i++)
			arr3[i]=arr1[i]+arr2[i];
		return arr3;
	}
	public static double[] sumVC(double[] arr1, double val) {
		double[] arr3 = new double[arr1.length];
		for(int i = 0; i < arr1.length; i++)
			arr3[i]=arr1[i]+val;
		return arr3;
	}
	
	public static double[] subV(double[] arr1, double[] arr2) {
		if(arr1.length != arr2.length) return null;
		double[] arr3 = new double[arr1.length];
		for(int i = 0; i < arr1.length; i++)
			arr3[i]=arr1[i]-arr2[i];
		return arr3;
	}
	public static double[] subVC(double[] arr1, double val) {
		double[] arr3 = new double[arr1.length];
		for(int i = 0; i < arr1.length; i++)
			arr3[i]=arr1[i]-val;
		return arr3;
	}
	
	public static double[] divVC(double[] arr1, double val) {
		double[] arr3 = new double[arr1.length];
		for(int i = 0; i < arr1.length; i++)
			arr3[i]=arr1[i]/val;
		return arr3;
	}
	public static double[] normalize(double[] arr1) {
		return divVC(arr1, Metrics.euclideanDistance(arr1));
	}
	
	public static final boolean isZero(double[] arr1) {
		if(Arrays.equals(arr1, new double[arr1.length])) return true;
		else return false;
	}
	
	public static final boolean isEqual(double[] arr1, double[] arr2) {
		if(Arrays.equals(arr1, arr2)) return true;
		else return false;
	}
	
	
}
