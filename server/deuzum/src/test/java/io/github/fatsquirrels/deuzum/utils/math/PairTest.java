package io.github.fatsquirrels.deuzum.utils.math;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;

import io.github.fatsquirrels.deuzum.utils.math.Pair;

@DisplayName("Math-> Pair Specifications")
public class PairTest {

	Pair p,p2;
	int[] a;
	
	@BeforeAll
	public void setup() {
		a = new int[4];
		for(int i = 0; i < 4; i++)
			a[i] = (new Random()).nextInt(7);
		p = new Pair(a[0], a[1]);
		p2 = new Pair(a[2],a[3]);
		
	}
	
	@Test
	public void testCompareTo() {
		int result;
		if(p.getValue()>p2.getValue()) result = 1;
		else if(p.getValue()<p2.getValue()) result = -1;
		else result = 0;
		assertEquals(result, p.compareTo(p2));
	}
	
	@Test
	public void testGetIndex() {
		assertEquals(p.getIndex(),a[0] );
		assertEquals(p2.getIndex(),a[2]);
	}
	
	@Test
	public void testGetValue() {
		assertEquals(a[1], p.getValue());
		assertEquals(a[3], p2.getValue());
	}

}
