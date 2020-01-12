package io.github.fatsquirrels.deuzum.utils.math;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

@DisplayName("Math-> APair Specifications")
public class APairTest {
	APair<Integer, String> p,p2;
	int[] a;
	
	@Before
	public void setup() {
		a = new int[2];
		for(int i = 0; i < 2; i++)
			a[i] = (new Random()).nextInt(7);
		p = new APair<Integer, String>(a[0], "P1");
		p2 = new APair<Integer, String>(a[1],"P2");
		
	}
	
	
	@Test
	public void testGetIndex() {
		assertEquals((int) p.getIndex(),a[0] );
		assertEquals((int) p2.getIndex(),a[1]);
	}
	
	@Test
	public void testGetValue() {
		assertEquals("P1", p.getValue());
		assertEquals("P2", p2.getValue());
	}

}
