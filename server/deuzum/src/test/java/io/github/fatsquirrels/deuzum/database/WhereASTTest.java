package io.github.fatsquirrels.deuzum.database;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import io.github.fatsquirrels.deuzum.database.WhereAST;

@DisplayName("Where Abstract Syntax Tree Specification")
public class WhereASTTest {

	
	private WhereAST newWhere;
	
	@BeforeEach
	public void setUp() {
		newWhere = new WhereAST();
	}
	
	
	@Test
	public void testCreation() {
		assertEquals(newWhere.pack(), "WHERE ");
	}
	
	@Test
	public void testOneCommand() {
		newWhere = newWhere
				.addColumValueLO(new String[]{"ej1","ej2"}, 
						new String[]{"e1j1","ej12"}, WhereAST.logicOP.AND, WhereAST.ariOP.EQ);
		assertEquals(newWhere.pack(), "WHERE ej1='e1j1' AND ej2='ej12'");
	}
	
	@Test
	public void testOneCommandNulled() {
		newWhere = newWhere.addValue("id='2'");
		assertEquals(newWhere.pack(), "WHERE id='2'");
	}
	
	@Test
	public void testParenthesis() {
		// Solo se puede dar un add Value. Se escoge el ultimo. 
		// Para añadir multiples valores hay que hacer con addColumValueLO.
		// Value es solo para una unica informacion
		newWhere = newWhere.addP()
				.addValue(" asddsa" ).addValue("dasd").backP();
		assertEquals(newWhere.pack(), "WHERE (dasd)");
	}
	@Test
	public void testMultiParenthesis() {
		newWhere = newWhere.addP()
				.addValue(" asddsa" ).backP().addP().addValue("dasd").backP().addP().addValue("dassa4d").backP();
		assertEquals(newWhere.pack(), "WHERE ( asddsa) AND (dasd) AND (dassa4d)");
	}
}
