package deustoZumServer.Algorithms.Math;

public class Metrics {

	
	public static final double euclideanDistance(double[] arr1) {
		double dist = 0;
		for(int i = 0 ; i < arr1.length; i++) 
			dist += arr1[i]*arr1[i];
		
		return Math.sqrt(dist);
	}
	
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
	
	public static final int getMinimumDistancePoint(double[] point, double[][] pointList) {
		if(pointList.length==0) return -1;
		if(pointList[0].length!=point.length) return -1;
		
		double minDistance = Metrics.euclideanDistance(point, pointList[0]);
		int minCluster = 0;
		// TODO Repasar este algoritmo
		for(int k = 1; k < pointList.length; k++) {
			double minDistanceT = Metrics.euclideanDistance(point, pointList[k]);
			if(minDistanceT < minDistance) {
				minDistance = minDistanceT;
				minCluster = k;
			}
		}
		
		return minCluster;
	}
	
	public static int getModa(int[] muestras) {

	    int maximoNumRepeticiones= 0;
	    int moda= 0;

	    for(int i=0; i<muestras.length; i++)
	    {
	        int numRepeticiones= 0;
	        for(int j=0; j<muestras.length; j++)
	        {
	            if(muestras[i]==muestras[j])
	            {
	                numRepeticiones++;
	            }  
	            if(numRepeticiones>maximoNumRepeticiones)
	            {
	                moda= muestras[i];
	                maximoNumRepeticiones= numRepeticiones;
	            }  
	        }
	    }  
	   return moda;
	}   
}
