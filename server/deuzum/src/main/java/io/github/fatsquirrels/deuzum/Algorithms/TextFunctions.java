package io.github.fatsquirrels.deuzum.Algorithms;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;


//TODO Arreglar errores con referencias entre llamada y ejecucion en todas
/**
 * 
 * Clase Funciones de Texto
 * La utilidad de esta clase es facilitar varios metodos para realizar operaciones sobre textos
 * @see #surroundText(String[] arr, String presuffix)
 * @see #surroundText(String[] arr, String preffix, String suffix)
 * @see #concatenateAlternative(String[] arr1, String[] arr2,String concat)
 * @see #concatenateAlternative(String[] arr1, String[] arr2,String concat, String nullText)
 * @see #concatenateAlternative(String[] arr1, String[] arr2,String concat, String nullText, String surroundText)
 *
 */
public class TextFunctions {

	
	/**
	 * Devuelve un array de Strings los cuales hemos rodeado con cierta cadena de texto
	 * @param arr Array de String.
	 * @param presuffix Cadena a agregar.
	 */
	public static final String[] surroundText(String[] arr, String presuffix) {
		String[] arrT = new String[arr.length];
		for(int i = 0; i < arr.length; i++)
			arrT[i] = presuffix + arr[i] + presuffix;
		return arrT;
	}
	
	/**
	 * Devuelve un array de Strings los cuales hemos rodeado con ciertas cadenas de texto
	 * @param arr Array de String.
	 * @param preffix Prefijo.
	 * @param suffix Sufijo.
	 */
	public static final String[] surroundText(String[] arr, String preffix, String suffix) {
		String[] arrT = new String[arr.length];
		for(int i = 0; i < arr.length; i++)
			arrT[i] = preffix + arr[i] + suffix;
		return arrT;
	}
	/**
	 * Devuelve un array en el cual cada elemento tiene la forma (arr1[i] + concat + arr2[i]). 
	 * En caso de que tengan diferente longitud, solo se concatenara hasta que uno termine.
	 * @param arr1 Array 1
	 * @param arr2 Array 2
	 * @param concat Cadena intermedia
	 */
	public static final String[] concatenateAlternative(String[] arr1, String[] arr2,String concat) {
		int size;
		if(arr1.length<arr2.length)  size = arr1.length;
		else size = arr2.length;
		
		String[] arr3 = new String[size];
		
		
		for(int i = 0; i < size; i++) 
			arr3[i] = arr1[i]+concat+arr2[i];
		return arr3;
		
	}
	/**
	 * Devuelve un array en el cual cada elemento tiene la forma (arr1[i] + concat + arr2[i]). 
	 * En caso de que tengan diferente longitud, se comienza a concatenar con nullText.
	 * @param arr1 Array 1
	 * @param arr2 Array 2
	 * @param concat Cadena intermedia
	 * @param nullText Cadena que se agrega a los valores del array de menor longitud a partid de haber superado el limite.
	 */
	public static final String[] concatenateAlternative(String[] arr1, String[] arr2,String concat, String nullText) {
		int size;
		if(arr1.length<arr2.length)  size = arr1.length;
		else size = arr2.length;
		
		String[] arr3 = new String[size];
		
		for(int i = 0; i < size; i++) 
			arr1[i] = arr1[i]+concat+arr2[i];
		
		String concatNull = concat+nullText;
		if(arr1.length > size)
			for(int i = size; i< arr1.length; i++)
				arr3[i] = arr1[i]+concatNull;
		else 
			for(int i = size; i< arr2.length; i++)
				arr3[i] = concatNull+arr2[i];
		return arr3;
		
	}
	
	/**
	 * Devuelve un array en el cual cada elemento tiene la forma (surroundText+arr1[i] + concat + arr2[i]+surroundText). 
	 * En caso de que tengan diferente longitud, se comienza a concatenar con nullText. 
	 * @param arr1 Array 1
	 * @param arr2 Array 2
	 * @param concat Cadena intermedia
	 * @param nullText Cadena que se agrega a los valores del array de menor longitud a partid de haber superado el limite.
	 * @param surroundText Cadena que rodeara a cada elemento
	 */
	public static final String[] concatenateAlternative(String[] arr1, String[] arr2,String concat, String nullText, String surroundText) {
		int size;
		if(arr1.length<arr2.length)  size = arr1.length;
		else size = arr2.length;
		
		String[] arr3 = new String[size];
		
		for(int i = 0; i < arr2.length; i++) 
			arr3[i] = surroundText+arr1[i]+concat+arr2[i]+surroundText;
		
		String concatNull = concat+nullText;
		if(arr1.length > size)
			for(int i = size; i< arr1.length; i++)
				arr3[i] = surroundText + arr1[i]+concatNull+surroundText;
		else 
			for(int i = size; i< arr2.length; i++)
				arr3[i] = surroundText + concatNull+arr2[i] + surroundText;
		return arr3;
		
	}
	
	/**
	 * Metodo que comprueba si un String termina con formato @---.com, hemos sustituido la manera de hacer Regex sobre los datos
	 * @param txtEmail String con el email a comprobar
	 * @return booleano indicando si el email introducido es correcto
	 * @see io.github.fatsquirrels.deuzum.Algorithms.TextTypes
	 */
	@Deprecated
	public static final boolean endsWithMail(String txtEmail) {
		if(txtEmail.endsWith("@gmail.com") || txtEmail.endsWith("@opendeusto.com") || txtEmail.endsWith("@hotmail.com") || txtEmail.endsWith("@deusto.com")) {
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * Metodo que comprueba si el String introducido tiene formato de fecha
	 * @param txtDate String con la fecha a comprobar
	 * @return booleano indicando si la fecha introducida es correcta
	 */
	public static final boolean dateChecker(String txtDate) {
		DateFormat df = new SimpleDateFormat();
		df.setLenient(false);
		try {
			df.parse(txtDate);
		}catch (ParseException e) {
			return false;
		}
		return true;
	}
	
	
}
