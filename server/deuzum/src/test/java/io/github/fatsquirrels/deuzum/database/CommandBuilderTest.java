package io.github.fatsquirrels.deuzum.database;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import io.github.fatsquirrels.deuzum.database.CommandBuilder;

		
@SuppressWarnings("deprecation")
class CommandBuilderTest {

	@Test
	void testGetWhereEqualsClause() {
		
		String[] columnNames = {"user_id","numero"};
		String[] data = {"23","1"};
		
		assertEquals("WHERE user_id='23' AND numero='1'",CommandBuilder.getWhereEqualsClause(columnNames, data));
	}

}
