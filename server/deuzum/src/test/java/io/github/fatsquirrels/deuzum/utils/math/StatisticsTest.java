package io.github.fatsquirrels.deuzum.utils.math;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;

import io.github.fatsquirrels.deuzum.utils.math.Statistics;



class StatisticsTest {

	@Test
	void testGetMode() {
		class Pedro{
			Pedro(){};
		}
		class Juan{
			Juan(){};
		}
		Pedro p = new Pedro();
		Juan j = new Juan();
		assertEquals(j, Statistics.getMode(new Object[] {p,j,j}));
	}
	
	@Test
	void testgetCategorizedMode() {
		assertEquals(1, Statistics.getCategorizedMode(new int[]{1,2,2,3,3,1,1,1,2,3,1,1,4,5},6));
	}

	@Test
	public void testGetModa() {
		int[] muestras = {1, 2 , 2, 3, 5, 5, 5};
		int result = Statistics.getModa(muestras);
		assertEquals(5, result);
	
	}
	
}
