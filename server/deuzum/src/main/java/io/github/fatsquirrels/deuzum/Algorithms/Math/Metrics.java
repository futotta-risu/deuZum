package io.github.fatsquirrels.deuzum.Algorithms.Math;

public class Metrics {

	//O(dim(cord))
	public static final double euclideanDistance(double[] cord1) {
		double dist = 0;
		for(int i = 0 ; i < cord1.length; i++) 
			dist += cord1[i]*cord1[i];
		
		return Math.sqrt(dist);
	}
	//O(dim(cord))
	public static final double euclideanDistance(double[] cord1, double[] cord2) {
		if(cord1.length != cord2.length) return 0;
		double dist = 0;
		for(int i = 0 ; i < cord1.length; i++) 
			dist += (cord1[i]-cord2[i])*(cord1[i]-cord2[i]);
		
		return Math.sqrt(dist);
	}
	
	
	//O(dim(center))
	public static final double flatKernel(double[] center, double radius, double[] newPos) {
		if(center.length != newPos.length) return -1;
		if(radius <= 0) return -1;
		
		double distance = 0;
		for(int i = 0; i < center.length; i++) 
			distance+=(center[i]-newPos[i])*(center[i]-newPos[i]);
		if(distance > radius*radius) return 0;
		else return 1;
	}
	
	//O(|pointList|*dim(point))
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
	
	
}
