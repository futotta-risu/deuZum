package io.github.fatsquirrels.deuzum.database;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

import io.github.fatsquirrels.deuzum.database.CommandBuilderF;
import io.github.fatsquirrels.deuzum.database.OrderType;
import io.github.fatsquirrels.deuzum.database.StatementType;
import io.github.fatsquirrels.deuzum.database.WhereAST;
import io.github.fatsquirrels.deuzum.database.exceptions.CommandBuilderBuildException;

public class CommandBuilderFTest {
	@Test
	void testInsert() {
		
		CommandBuilderF newCommand = null;
		try {
			newCommand = new CommandBuilderF().setSQLType(StatementType.INSERT)
					.setTable("HOSPITAL")
					.addColumns(new String[]{"plantilla","comando"}, new String[]{"plantilla","comando"})
					.addColumn("ejemplo1","ejepmlo2");
			String commando = newCommand.pack();
			assertEquals("INSERT INTO HOSPITAL (ejemplo1,plantilla,comando) VALUES ('ejepmlo2','plantilla','comando')" 
					,commando);
		} catch (CommandBuilderBuildException e) {
			assertTrue(false);
		}
		
	}
	
	@Test
	void testSelect() {
		
		CommandBuilderF newCommand;
		try {
			newCommand = new CommandBuilderF().setSQLType(StatementType.SELECT)
					.setTable("HOSPITAL").addInnerJoin("EMPRESA")
					.addColumns(new String[]{"plantilla","comando"}).addColumn("ejemplo1")
					.setOrder(OrderType.ASC, new String[] { "plantilla"})
					.setGroupBy(new String[] { "comando"}).setLimit(3)
					.addWhere(new WhereAST().addP()
							.addColumValueLO(new String[] {"pantilla","comando"}, 
									new String[] {"ej1","ej2"}, WhereAST.logicOP.AND, WhereAST.ariOP.EQ)
							.backP()
							);
			String commando = newCommand.pack();
			System.out.println(commando);
			assertEquals("SELECT ejemplo1,plantilla,comando FROM HOSPITAL INNER JOIN EMPRESA WHERE (pantilla='ej1' AND comando='ej2') GROUP BY comando ORDER BY plantilla ASC LIMIT 3" ,commando);
		} catch (CommandBuilderBuildException e) {
			assertTrue(false);
		}
		
	}
	
	@Test
	void testDelete() {
		
		WhereAST where = new WhereAST().addValue("id='2'");
		CommandBuilderF newCommand;
		try {
			newCommand = new CommandBuilderF(StatementType.DELETE)
					.setTable("tabla2").addWhere(where);
			String commando = newCommand.pack();
			System.out.println(commando);
			assertEquals("DELETE FROM tabla2 WHERE id='2'  " ,commando);
		
		} catch (CommandBuilderBuildException e) {
			assertTrue(false);
		}
		
		}
	@Test
	void testComposedWhere() {
		WhereAST where2 = new WhereAST().addValue("id='2'");
		WhereAST where;
		try {
			where = new WhereAST().addP().addValue(new CommandBuilderF(StatementType.DELETE)
					.setTable("tabla2").addWhere(where2).pack()).backP();
			CommandBuilderF newCommand = new CommandBuilderF(StatementType.DELETE)
					.setTable("tabla2").addWhere(where);
			
			String commando = newCommand.pack();
			System.out.println(commando);
			assertEquals("DELETE FROM tabla2 WHERE (DELETE FROM tabla2 WHERE id='2'  )  " ,commando);
		} catch (CommandBuilderBuildException e) {
			assertTrue(false);
		}
		
	}
	
	
	@Test
	void testUpdate() {
		WhereAST where = new WhereAST().addValue("id='2'");
		CommandBuilderF newCommand;
		try {
			newCommand = new CommandBuilderF(StatementType.UPDATE)
					.setTable("tabla2").addExpression("name","Jose").addExpression("name2","Jose2").addWhere(where);
			String comando = newCommand.pack();
			System.out.println(comando);
			assertEquals("UPDATE tabla2 SET name=\"Jose\",name2=\"Jose2\" WHERE id='2'  ", comando);
		} catch (CommandBuilderBuildException e) {
			assertTrue(false);
		}
		
		
	}
}
