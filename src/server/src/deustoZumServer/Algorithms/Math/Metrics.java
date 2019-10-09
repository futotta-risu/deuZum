package deustoZumServer.Algorithms.Math;

public class Metrics {

	public static final double euclideanDistance(double[] arr1, double[] arr2) {
		if(arr1.length != arr2.length) return 0;
		double dist = 0;
		for(int i = 0 ; i < arr1.length; i++) 
			dist += (arr1[i]-arr2[i])*(arr1[i]-arr2[i]);
		
		return Math.sqrt(dist);
	}
	
}
