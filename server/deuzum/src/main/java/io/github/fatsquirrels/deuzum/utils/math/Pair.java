package io.github.fatsquirrels.deuzum.utils.math;

import io.github.fatsquirrels.deuzum.utils.meta.anotations.Tested;

/**
 * Clase pareja.
 * La clase pareja se puede ver como un valor double indexado.
 * Esta clase busca su utilidad en poder ordenar arrays de Pair y despuer de haberlos ordenado por su value, seguir sabiendo la posición del index antes del ordenamiento.
 *
 */
@Tested(tested = true)
public class Pair implements Comparable<Pair>{
	
	public final int index;
	public final double value;
	
	public Pair(int index,double value) {
		this.index =index;
		this.value = value;
	}
	/**
	 * Devualve 1 si el valor de la pareja 1 es mayor que el valor de la pareja 2.
	 * Devuelve -1 si el valor de la pareja 2 es mayor que el valor de la pareja 1.
	 * Devuelve 0 si los valores son iguales
	 */
	@Override
	public int compareTo(Pair o) {
		return Double.valueOf(this.value).compareTo(o.value);
	}
	
	public int getIndex() {
		return index;
	}
	
	public double getValue() {
		return value;
	}
	
	
	
}
