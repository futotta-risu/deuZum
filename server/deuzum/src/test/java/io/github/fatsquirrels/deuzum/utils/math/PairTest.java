package io.github.fatsquirrels.deuzum.utils.math;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

import io.github.fatsquirrels.deuzum.utils.math.Pair;

public class PairTest {

	@Test
	public void testCompareTo() {
		Pair p = new Pair(1, 0.5);
		Pair p2 = new Pair(2, 0.4);
		assertEquals(1, p.compareTo(p2));
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
