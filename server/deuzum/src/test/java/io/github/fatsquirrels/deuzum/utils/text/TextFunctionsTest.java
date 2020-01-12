package io.github.fatsquirrels.deuzum.utils.text;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import io.github.fatsquirrels.deuzum.utils.text.TextFunctions;

@DisplayName("FunctionTest Specifications")
public class TextFunctionsTest {

	String[] array1 = {"hola", "adios"};
	String[] array2 = {"kaixo", "agur"};
	
	String suffix = "?";
	String preffix = "#";
	String surround = "#";
	String concat = "=";
	String nullText = "";
	
	@Test
	public void testSurroundText() {
		
		String[] result = TextFunctions.surroundText(array1, preffix);
		String[] expected = {"#hola#", "#adios#"};
		assertArrayEquals(expected, result);
	}
	
	@Test
	public void testSurroundTextPreffixSuffix() {
		
		String[] result = TextFunctions.surroundText(array1, preffix, suffix);
		String[] expected = {"#hola?", "#adios?"};
		assertArrayEquals(expected, result);
	}
	
	@Test
	public void testConcatenateAlternative() {
		
		String[] result = TextFunctions.concatenateAlternative(array1, array2, concat);
		String[] expected = {"hola=kaixo", "adios=agur"};
		assertArrayEquals(expected, result);
	}
	
	@Test
	public void testConcatenateAlternativeWithNull() {

		String[] result = TextFunctions.concatenateAlternative(array1, array2, concat, nullText);
		String[] expected = {"hola=kaixo", "adios=agur"};
		assertArrayEquals(expected, result);
	}

	@Test
	public void testConcatenateAlternativeWithNullAndSurround() {
		String[] result = TextFunctions.concatenateAlternative(array1, array2, concat, nullText,surround);
		String[] expected = {"#hola=kaixo#", "#adios=agur#"};
		assertArrayEquals(expected, result);
	}
}
