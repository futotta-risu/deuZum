package io.github.fatsquirrels.deuzum.utils.math;

import java.util.Arrays;

import io.github.fatsquirrels.deuzum.utils.meta.anotations.Tested;

/**
 * Clase Vectores
 * La funcion de esta clase es obtener diferentes calculos entre Vectores
 * @see #sumV
 * @see #sumVC
 * @see #subV
 * @see #subVC
 * @see #divVC
 * @see #normalize
 * @see #isZero
 * @see #isEqual
 */
@Tested(tested = true)
public class Vectors {
	
	/**
	 * Este metodo calcula la suma de dos Vectores
	 * @param arr1 1.Array de Doubles
	 * @param arr2 2.Array de Doubles
	 * @return Array de Doubles que contiene la suma de los dos Vectores
	 */
	public static double[] sumV(double[] arr1, double[] arr2) {
		if(arr1.length != arr2.length) return null;
		double[] arr3 = new double[arr1.length];
		for(int i = 0; i < arr1.length; i++)
			arr3[i]=arr1[i]+arr2[i];
		return arr3;
	}
	
	/**
	 * Este metodo calcula la suma de un valor a los valores del Vector recibido
	 * @param arr1 Array de Doubles
	 * @param val Valor que se suma al vector recibido
	 * @return Array de Doubles que contiene la suma de los valores del Vector y el valor
	 */
	public static double[] sumVC(double[] arr1, double val) {
		double[] arr3 = new double[arr1.length];
		for(int i = 0; i < arr1.length; i++)
			arr3[i]=arr1[i]+val;
		return arr3;
	}
	
	/**
	 * Este metodo calcula la resta de dos Vectores
	 * @param arr1 1.Array de Doubles
	 * @param arr2 2.Array de Doubles
	 * @return Array de Doubles que contiene la resta de los dos Vectores
	 */
	public static double[] subV(double[] arr1, double[] arr2) {
		if(arr1.length != arr2.length) return null;
		double[] arr3 = new double[arr1.length];
		for(int i = 0; i < arr1.length; i++)
			arr3[i]=arr1[i]-arr2[i];
		return arr3;
	}
	
	/**
	 * Este metodo calcula la resta de un valor a los valores del Vector recibido
	 * @param arr1 Array de Doubles
	 * @param val Valor que se resta al vector recibido
	 * @return Array de Doubles que contiene la suma de los valores del Vector y el valor
	 */
	public static double[] subVC(double[] arr1, double val) {
		double[] arr3 = new double[arr1.length];
		for(int i = 0; i < arr1.length; i++)
			arr3[i]=arr1[i]-val;
		return arr3;
	}
	
	/**
	 * Este metodo calcula la division de un valor a los valores del Vector recibido
	 * @param arr1 Array de Doubles
	 * @param val Valor que se divide al vector recibido
	 * @return Array de Doubles que contiene la division entre los valores del Vector y el valor
	 */
	public static double[] divVC(double[] arr1, double val) {
		double[] arr3 = new double[arr1.length];
		for(int i = 0; i < arr1.length; i++)
			arr3[i]=arr1[i]/val;
		return arr3;
	}
	
	/**
	 * 
	 * @param arr1
	 * @return
	 */
	public static double[] normalize(double[] arr1) {
		return divVC(arr1, Metrics.euclideanDistance(arr1));
	}
	
	/**
	 * Este metodo recibe un array y comprueba si este esta vacio
	 * @param arr1 Array de Doubles
	 * @return Devuelve un booleano indicando si el Array recibido esta vacio
	 */
	public static final boolean isZero(double[] arr1) {
		if(Arrays.equals(arr1, new double[arr1.length])) return true;
		else return false;
	}
	
	/**
	 * Este metodo recibe dos Arrays y los compara entre si para ver si tienes el mismo contenido
	 * @param arr1 1.Array de Doubles
	 * @param arr2 2.Array de Doubles
	 * @return Devuelve un booleano indicando si los Arrays recibidos son iguales
	 */
	public static final boolean isEqual(double[] arr1, double[] arr2) {
		if(Arrays.equals(arr1, arr2)) return true;
		else return false;
	}
	
	
}
