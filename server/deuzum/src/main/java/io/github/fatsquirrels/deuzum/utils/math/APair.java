package io.github.fatsquirrels.deuzum.utils.math;


public class APair<I,V> {
	
	public final I index;
	public final V value;
	
	public APair(I index,V value) {
		this.index =index;
		this.value = value;
	}
	
	
	public I getIndex() {
		return index;
	}
	
	public V getValue() {
		return value;
	}
	
}