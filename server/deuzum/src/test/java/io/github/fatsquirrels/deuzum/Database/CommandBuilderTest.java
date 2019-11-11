package io.github.fatsquirrels.deuzum.Database;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

		
class CommandBuilderTest {

	@Test
	void testGetWhereEqualsClause() {
		
		String[] columnNames = {"user_id","numero"};
		String[] data = {"23","1"};
		
		assertEquals("WHERE user_id='23' AND numero='1'",CommandBuilder.getWhereEqualsClause(columnNames, data));
	}

}
