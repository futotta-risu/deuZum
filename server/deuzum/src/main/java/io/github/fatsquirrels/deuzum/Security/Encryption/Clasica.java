package io.github.fatsquirrels.deuzum.Security.Encryption;

public class Clasica {

	
	/**
	 * Este metodo devuelve un texto Encriptado con el algoritmo de Cesar
	 * @param texto Texto a encriptar
	 * @param codigo Codigo variable para la encriptacion
	 * @return Texto encriptado con Cesar
	 */
	public static String CesarE(String texto, int codigo) {
		String result="";
		codigo=codigo %26;
		for (int i = 0; i < texto.length(); i++) {
			int c=((int)texto.charAt(i))-97;
			if (c<0&&c>26) 
				result+=(char) c;
			result+=(char)((c + codigo)%26);
		}
		return result;
	}
	
	/**
	 * Este metodo devuelve un texto Desencriptado, previamente encriptado con el Algoritmo de CesarE
	 * @see #CesarE
	 * @param texto Texto a desencriptar
	 * @param codigo Codigo variable con el que ha sido encriptado el mensaje
	 * @return Texto desencriptado con Cesar
	 */
	public static String CesarD(String texto, int codigo) {
		String result="";
		codigo=codigo %26;
		for (int i = 0; i < texto.length(); i++) {
			int c=((int)texto.charAt(i))+97;
			if (c<97&&c>122) 
				result+=(char) c;
			result+=(char)((c - codigo)%26);
		}
		return result;
	}
	
	
	/**
	 * Este metodo devuelve un texto Encriptado con el algoritmo de Vigenere
	 * @param texto Texto a encriptar
	 * @param codigo Array de INTs que contiene varios codigos para encriptar el texto
	 * @return Texto encriptado con Vigenere
	 */
	public static String VigenereE(String texto, int[] codigo) {
		String result="";
		int codigoL = codigo.length;
		for (int i = 0; i < texto.length(); i++) {
			
			int c=((int)texto.charAt(i))-97;
			if (c<0&&c>26) 
				result+=(char) c;
			result+=(char)((c + codigo[i%codigoL])%26);
		}
		return result;
	}
	
	
	/**
	 * Este metodo devuelve un texto Desencriptado, previamente encriptado con el Algoritmo de VigenereE
	 * @see #VigenereE
	 * @param texto Texto a desencriptar
	 * @param codigo Array de INTs que contienes varios codigos con los que previamente ha sido encriptado el mensaje
	 * @return Texto desencriptado con Vigenere
	 */
	public static String VigenereD(String texto, int[] codigo) {
		String result="";
		int codigoL = codigo.length;
		for (int i = 0; i < texto.length(); i++) {
			int c=((int)texto.charAt(i))+97;
			if (c<97&&c>122) 
				result+=(char) c;
			result+=(char)((c - codigo[i%codigoL])%26);
		}
		return result;
	}
	
		
}