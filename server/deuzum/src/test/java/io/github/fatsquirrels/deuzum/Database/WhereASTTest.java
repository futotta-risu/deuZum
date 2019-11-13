package io.github.fatsquirrels.deuzum.Database;

import org.junit.Test;

public class WhereASTTest {

	
	@Test
	public void testCreation() {
		WhereAST newWhere = new WhereAST();
		System.out.println(newWhere.pack());
	}
	
	@Test
	public void testOneCommand() {
		WhereAST newWhere = new WhereAST()
				.addColumValueLO(new String[]{"ej1","ej2"}, 
						new String[]{"e1j1","ej12"}, logicOP.AND, ariOP.EQ);
		System.out.println(newWhere.pack());
	}
	
	@Test
	public void testParenthesis() {
		
		// When we set twice the value it replaces it
		WhereAST newWhere = new WhereAST().addP()
				.addValue(" asddsa" ).addValue("dasd").backP();
		System.out.println(newWhere.pack());
	}
	@Test
	public void testMultiParenthesis() {
		
		// When we set twice the value it replaces it
		WhereAST newWhere = new WhereAST().addP()
				.addValue(" asddsa" ).backP().addP().addValue("dasd").backP();
		System.out.println(newWhere.pack());
	}
}
