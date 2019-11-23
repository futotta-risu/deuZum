package io.github.fatsquirrels.deuzum.UserFunctions;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;

import org.junit.jupiter.api.Test;

import io.github.fatsquirrels.deuzum.ServerUserFunctionality;
import io.github.fatsquirrels.deuzum.Algorithms.ConcreteText;
import io.github.fatsquirrels.deuzum.Algorithms.TextTypes;
import io.github.fatsquirrels.deuzum.Database.GeneralSQLFunctions;

class userManipulation {

	@Test
	void userCreationTest() {
		int nError =1;
		String tempText = "erik@gmail.cm";
		if(ConcreteText.isValid(tempText,  TextTypes.EMAIL)) 
			System.err.println("Error " + String.valueOf(++nError) + ": No ha indicado ninguna respuesta en " + tempText);
		
	}
	@Test
	void userCreateUser() {
		Connection conn = GeneralSQLFunctions.connectToDatabase("jdbc:mysql://localhost/deuzumdb", "root", "");
		ServerUserFunctionality.createUser(conn, new String[] 
				{"erikberter22", "erikberter2", "6", "erikberter2", "1"});
		
	}
}
