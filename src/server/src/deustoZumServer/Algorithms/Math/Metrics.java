package deustoZumServer.Algorithms.Math;

public class Metrics {

	public static final double euclideanDistance(double[] arr1, double[] arr2) {
		if(arr1.length != arr2.length) return 0;
		double dist = 0;
		for(int i = 0 ; i < arr1.length; i++) 
			dist += (arr1[i]-arr2[i])*(arr1[i]-arr2[i]);
		
		return Math.sqrt(dist);
	}
	
	
	
	public static final double flatKernel(double[] center, double radius, double[] newPos) {
		if(center.length != newPos.length) return -1;
		// TODO radius <0
		double distance = 0;
		for(int i = 0; i < center.length; i++) 
			distance+=(center[i]-newPos[i])*(center[i]-newPos[i]);
		if(distance >= radius*radius) return 0;
		else return 1;
	}
}
