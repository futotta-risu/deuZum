package io.github.fatsquirrels.deuzum.utils;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

import io.github.fatsquirrels.deuzum.utils.text.TextFunctions;

public class TextFunctionsTest {

	@Test
	public void testSurroundText() {
		String[] arr = {"hola", "adios"};
		String presuffix = "#";
		String[] result = TextFunctions.surroundText(arr, presuffix);
		String[] expected = {"#hola#", "#adios#"};
		//assertEquals(expected, result);
	}
	
	@Test
	public void testSurroundTextPreffixSuffix() {
		String[] arr = {"hola", "adios"};
		String preffix = "#";
		String suffix = "?";
		String[] result = TextFunctions.surroundText(arr, preffix, suffix);
		String[] expected = {"#hola?", "#adios?"};
		//assertEquals(expected, result);
	}
	
	@Test
	public void testConcatenateAlternative() {
		String[] arr1 = {"hola", "adios"};
		String[] arr2 = {"kaixo", "agur"};
		String concat = "=";
		String[] result = TextFunctions.concatenateAlternative(arr1, arr2, concat);
		String[] expected = {"hola=kaixo", "adios=agur"};
		//assertEquals(expected, result);
	}
	
	@Test
	public void testConcatenateAlternativeWithNull() {
		String[] arr1 = {"hola", "adios"};
		String[] arr2 = {"kaixo", "agur"};
		String concat = "=";
		String nullText = "";
		String[] result = TextFunctions.concatenateAlternative(arr1, arr2, concat, nullText);
		String[] expected = {"hola=kaixo", "adios=agur"};
		//assertEquals(expected, result);
	}

	@Test
	public void testConcatenateAlternativeWithNullAndSurround() {
		String[] arr1 = {"hola", "adios"};
		String[] arr2 = {"kaixo", "agur"};
		String concat = "=";
		String nullText = "";
		String surroundText = "k";
		String[] result = TextFunctions.concatenateAlternative(arr1, arr2, concat, nullText);
		String[] expected = {"hola=kaixo", "adios=agur"};
		//assertEquals(expected, result);
	}
}
