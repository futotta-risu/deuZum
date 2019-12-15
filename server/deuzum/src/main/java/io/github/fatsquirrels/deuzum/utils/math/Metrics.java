package io.github.fatsquirrels.deuzum.utils.math;

import java.util.Arrays;
import java.util.stream.IntStream;

import io.github.fatsquirrels.deuzum.utils.meta.anotations.Tested;

/**
 * Clase Metricas
 * Esta clase proporciona varios metodos para calculos metricos
 * @see #euclideanDistance
 * @see #flatKernel
 * @see #getMinimumDistancePoint
 */
@Tested(tested = true)
public class Metrics {


	/**
	 * Computa la distancia euclidea de un punto al punto 0. Este algoritmo se ejecuta en tiempo O(dim(cord))
	 * @param cord Coordenada del punto
	 * @return Distancia entre los puntos
	 */
	public static final double euclideanDistance(double[] cord) {
		return Math.sqrt(Arrays.stream(cord).reduce(0,(x,y) -> x+y*y));
	}
	/**
	 * Computa la distancia euclidea entre dos puntos. Este algoritmo se ejecuta en tiempo O(dim(cord))
	 * @param cord1 Coordenada del primer punto
	 * @param cord2 Coordenada del segundo punto
	 * @return Distancia entre los puntos
	 */
	public static final double euclideanDistance(double[] cord1, double[] cord2) {
		if(cord1.length != cord2.length) return 0;
		double dist = IntStream.range(0, cord1.length)
				.mapToDouble(i-> cord1[i]-cord2[i]).reduce(0,(x,y) -> x+y*y);
		return Math.sqrt(dist);
	}
	
	/**
	 * Calcula el flat kernel de un punto(p) respecto a un centro(c) y un radio(r).
	 * El flat kernel devuelve 1 si {|p-c|<=r} y devuelve 0 en cualquier otro caso. Se puede ver como que la funcion devuelve uno si el nuevo punto se encuentra dentro de la n-esfera de centro c y radio r.
	 * Este calculo se ejecuta en tiempo O(dim(c))
	 * @param c Centro
	 * @param r Radio
	 * @param p Punto a calcular
	 * @return Double que contiene el flat kernel
	 */
	public static final double flatKernel(double[] c, double r, double[] p) {
		if(c.length != p.length || r <= 0) return -1;
		
		double distance =  IntStream.range(0, c.length)
				.mapToDouble(i-> c[i]-p[i]).reduce(0,(x,y) -> x+y*y);
		
		if(distance > r*r) return 0;
		else return 1;
	}
	
	//O(|pointList|*dim(point))
	/**
	 * Obtiene el punto de minima distancia de una lista respecto a un punto. 
	 * @param point Punto a comparar
	 * @param pointList Lista de puntos
	 * @return Posicion del punto de mínima distancia en la lista
	 */
	public static final int getMinimumDistancePoint(double[] point, double[][] pointList) {
		if(pointList.length==0) return -1;
		if(pointList[0].length!=point.length) return -1;
		
		double minDistance = Metrics.euclideanDistance(point, pointList[0]);
		int minCluster = 0;
		
		for(int k = 1; k < pointList.length; k++) {
			double tempDist = Math.min(minDistance,Metrics.euclideanDistance(point, pointList[k]));
			if(tempDist != minDistance) 
				minCluster = k;
			minDistance = tempDist;
		}
		
		return minCluster;
	}
	
	
}
