package io.github.fatsquirrels.deuzum.utils;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import io.github.fatsquirrels.deuzum.utils.text.ConcreteText;
import io.github.fatsquirrels.deuzum.utils.text.TextTypes;

class ConcreteTextTest {

	@Test
	void testIsValidEmail() {
		ConcreteText ct = new ConcreteText("lander@gmail.com", TextTypes.EMAIL);
		boolean actual = ct.isValid();
		ConcreteText ct2 = new ConcreteText("lan@gmail.com", TextTypes.EMAIL);
		boolean actual2 = ct2.isValid();
		assertEquals(true, actual);
		assertEquals(false, actual2);
	}
	
	@Test
	void testIsValidName() {
		ConcreteText ct = new ConcreteText("lander", TextTypes.NAME);
		boolean actual = ct.isValid();
		ConcreteText ct2 = new ConcreteText("lan456", TextTypes.NAME);
		boolean actual2 = ct2.isValid();
		assertEquals(true, actual);
		assertEquals(false, actual2);

	}
	
	@Test
	void testIsValidPassword() {
		ConcreteText ct = new ConcreteText("__3456", TextTypes.PASSWORD);
		boolean actual = ct.isValid();
		ConcreteText ct2 = new ConcreteText("lan45", TextTypes.PASSWORD);
		boolean actual2 = ct2.isValid();
		assertEquals(true, actual);
		assertEquals(false, actual2);
		
	}
	
	@Test
	void testIsValidParametersEmail() {
		boolean actual = ConcreteText.isValid("lander@gmail.com", TextTypes.EMAIL);
		// 32 MAX LENGTH
		boolean actual2 = ConcreteText.isValid("ddddddddddddddddddddddddddddddddddddddddddddd@gmail.com", TextTypes.EMAIL);
		// 4 MIN LENGTH
		boolean actual3 = ConcreteText.isValid("123@gmail.com", TextTypes.EMAIL);
		assertEquals(true, actual);
		assertEquals(false, actual2);
		assertEquals(false, actual3);

	}
	
	@Test
	void testIsValidParametersName() {
		boolean actual = ConcreteText.isValid("lander", TextTypes.NAME);
		// 3 MIN LENGTH
		boolean actual2 = ConcreteText.isValid("hj", TextTypes.NAME);
		// CARACTER ERROR
		boolean actual3 = ConcreteText.isValid("12@", TextTypes.NAME);
		// 32 MAX LENGTH
		boolean actual4 = ConcreteText.isValid("hjJJJJJJJJJJJJJJJJJJJHUJHJHJHJUJJ", TextTypes.NAME);
		assertEquals(true, actual);
		assertEquals(false, actual2);
		assertEquals(false, actual3);
		assertEquals(false, actual4);


	}
	
	@Test
	void testIsValidParametersPassword() {
		boolean actual = ConcreteText.isValid("123456", TextTypes.PASSWORD);
		// 6 MIN LENGTH
		boolean actual2 = ConcreteText.isValid("12345", TextTypes.PASSWORD);
		// CARACTER ERROR
		boolean actual3 = ConcreteText.isValid("12@456", TextTypes.PASSWORD);
		// 32 MAX LENGTH
		boolean actual4 = ConcreteText.isValid("hjJJJJJJJJJJJJJJJJJJJHUJHJHJHJUJJ", TextTypes.PASSWORD);
		assertEquals(true, actual);
		assertEquals(false, actual2);
		assertEquals(false, actual3);
		assertEquals(false, actual4);


	}
	
	

}
