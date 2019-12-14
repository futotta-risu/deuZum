package io.github.fatsquirrels.deuzum.database;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import io.github.fatsquirrels.deuzum.database.CommandBuilderF;
import io.github.fatsquirrels.deuzum.database.OrderType;
import io.github.fatsquirrels.deuzum.database.StatementType;
import io.github.fatsquirrels.deuzum.database.WhereAST;

public class CommandBuilderFTest {
	@Test
	void testInsert() {
		
		CommandBuilderF newCommand = new CommandBuilderF().setSQLType(StatementType.INSERT)
				.setTable("HOSPITAL")
				.addColumns(new String[]{"plantilla","comando"}, new String[]{"plantilla","comando"})
				.addColumn("ejemplo1","ejepmlo2");
		String commando = newCommand.pack();
		assertEquals("INSERT INTO HOSPITAL (ejemplo1,plantilla,comando) VALUES ('ejepmlo2','plantilla','comando')" 
				,commando);
	}
	
	@Test
	void testSelect() {
		
		CommandBuilderF newCommand = new CommandBuilderF().setSQLType(StatementType.SELECT)
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
		//assertEquals("INSERT INTO HOSPITAL (ejemplo1,plantilla,comando) VALUES ('ejepmlo2','plantilla','comando')" ,commando);
	}
	
	@Test
	void testDelete() {
		
		WhereAST where = new WhereAST().addValue("id='2'");
		CommandBuilderF newCommand = new CommandBuilderF(StatementType.DELETE)
				.setTable("tabla2").addWhere(where);
		
		String commando = newCommand.pack();
		System.out.println(commando);
		//assertEquals("INSERT INTO HOSPITAL (ejemplo1,plantilla,comando) VALUES ('ejepmlo2','plantilla','comando')" ,commando);
	}
	@Test
	void testComposedWhere() {
		WhereAST where2 = new WhereAST().addValue("id='2'");
		WhereAST where = new WhereAST().addP().addValue(new CommandBuilderF(StatementType.DELETE)
				.setTable("tabla2").addWhere(where2).pack()).backP();
		CommandBuilderF newCommand = new CommandBuilderF(StatementType.DELETE)
				.setTable("tabla2").addWhere(where);
		
		String commando = newCommand.pack();
		System.out.println(commando);
		//assertEquals("INSERT INTO HOSPITAL (ejemplo1,plantilla,comando) VALUES ('ejepmlo2','plantilla','comando')" ,commando);
	}
	
	
	@Test
	void testUpdate() {
		WhereAST where = new WhereAST().addValue("id='2'");
		CommandBuilderF newCommand = new CommandBuilderF(StatementType.UPDATE)
				.setTable("tabla2").addExpression("name","Jose").addExpression("name2","Jose2").addWhere(where);
		
		String comando = newCommand.pack();
		System.out.println(comando);
		//assertEquals("UPDATE tabla2 SET name=Jose WHERE id='2'", comando);
	}
}
