package io.github.fatsquirrels.deuzum.Algorithms.Math;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

public class PairTest {

	@Test
	public void testCompareTo() {
		Pair p = new Pair(1, 0.5);
		Pair p2 = new Pair(2, 0.4);
		double result = p2.compareTo(p);
		//TODO Arreglas esto
		//assertEquals(1, result, 0.01);
	}
	
	@Test
	public void testGetIndex() {
		Pair p = new Pair(99, 0.8);
		int result = p.getIndex();
		assertEquals(99, result);
	}
	
	@Test
	public void testGetValue() {
		Pair p = new Pair(1, 0.455);
		double result = p.getValue();
		assertEquals(0.455, result);
	}

}
