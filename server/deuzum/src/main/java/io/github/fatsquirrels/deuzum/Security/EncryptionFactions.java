package io.github.fatsquirrels.deuzum.Security;

import io.github.fatsquirrels.deuzum.Security.Encryption.Clasica;
import io.github.fatsquirrels.deuzum.Security.Encryption.EncryptionTypes;

public class EncryptionFactions {

	/**
	 * Metodo que devuelve un texto encriptado con el metodo elegido
	 * @param text Texto a encriptar
	 * @param key Llave para el metodo de encriptacion
	 * @param e Metodo de encriptacion
	 * @return Texto encriptado
	 */
	public static String getEncrypt(String text, int key, EncryptionTypes e) {
		switch(e) {
			case Cesar:
				return Clasica.CesarE(text, key);
			case Vigenere:
				return Clasica.VigenereE(text, new int[] {key});
			default:
				return Clasica.CesarE(text, key);
		}
	}
	
	/**
	 * Metodo que devuelve un texto encriptado con el metodo elegido
	 * @param text Texto a desencriptar
	 * @param key Llave para el metodo de desencriptacion
	 * @param e Metodo de desencriptacion
	 * @see getEncrypt(String text, int key, EncryptionTypes e)
	 * @return Texto desencriptado
	 */
	public static final String getDecrypt(String text, int key, EncryptionTypes e) {
		switch(e) {
			case Cesar:
				return Clasica.CesarD(text, key);
			case Vigenere:
				return Clasica.VigenereD(text, new int[] {key});
			default:
				return Clasica.CesarD(text, key);
		}
	}
	
	/**
	 * Metodo que devuelve un Array de Strings encriptados con el los metodos de encriptacion elegidos
	 * @param texts Array de Strings con los textos a encriptar
	 * @param keys Array de Llaves para los metodos de encriptacion
	 * @param e Array de EncryptionTypes que contiene los metodos de encriptacion
	 * @return Array de Strings con los textos encriptados
	 */
	public static final String[] getEncrypt(String[] texts,  int[] keys , EncryptionTypes[] e) {
		if(texts.length!=keys.length || keys.length != e.length) return null;
		String[] result = new String[texts.length];
		for(int i = 0; i < texts.length; i++)
			switch(e[i]) {
				case Cesar:
					result[i] = Clasica.CesarE(texts[i], keys[i]);
				case Vigenere:
					result[i] = Clasica.VigenereE(texts[i], new int[] {keys[i]});
				default:
					result[i] = Clasica.CesarE(texts[i], keys[i]);
			}
		return result;
	}
	
	/**
	 * Metodo que devuelve un Array de Strings desencriptados con el los metodos de desencriptacion elegidos
	 * @param texts Array de Strings con los textos a desencriptar
	 * @param keys Array de Llaves para los metodos de desencriptacion
	 * @param e Array de EncryptionTypes que contiene los metodos de desencriptacion
	 * @see #getEncrypt(String[] texts,  int[] keys , EncryptionTypes[] e)
	 * @return Array de String con los textos desencriptados
	 */
	public static final String[] getDecrypt(String[] texts,  int[] keys , EncryptionTypes[] e) {
		if(texts.length!=keys.length || keys.length != e.length) return null;
		String[] result = new String[texts.length];
		for(int i = 0; i < texts.length; i++)
			switch(e[i]) {
				case Cesar:
					result[i] = Clasica.CesarD(texts[i], keys[i]);
				case Vigenere:
					result[i] = Clasica.VigenereD(texts[i], new int[] {keys[i]});
				default:
					result[i] = Clasica.CesarD(texts[i], keys[i]);
			}
		return result;
	}

	
	/**
	 * Metodo que devuelve un Array de Strings encriptados con el metodo de desencriptacion elegido
	 * @param text Array de Strings con los textos a encriptar
	 * @param keys Array de Llaves para el metodo de encriptacion
	 * @param e Metodo de encriptacion
	 * @return Array de Strings con los textos encriptados
	 */
	public static final String[] getEncrypt(String[] texts, int[] keys ,EncryptionTypes e) {
		if(texts.length!=keys.length) return null;
		String[] result = new String[texts.length];
		for(int i = 0; i < texts.length; i++)
			switch(e) {
				case Cesar:
					result[i] = Clasica.CesarD(texts[i], keys[i]);
				case Vigenere:
					result[i] = Clasica.VigenereD(texts[i], new int[] {keys[i]});
				default:
					result[i] = Clasica.CesarD(texts[i], keys[i]);
			}
		return result;
	}
	
	/**
	 * Metodo que devuelve un Array de Strings desencriptados con el metodo de desencriptacion elegido
	 * @param text Array de Strings con los textos a desencriptar
	 * @param keys Array de Llaves para el metodo de desencriptacion
	 * @param e Metodo de desencriptacion
	 * @return Array de Strings con los textos desencriptados
	 */
	public static final String[] getDecrypt(String[] texts, int[] keys ,EncryptionTypes e) {
		if(texts.length!=keys.length) return null;
		String[] result = new String[texts.length];
		for(int i = 0; i < texts.length; i++)
			switch(e) {
				case Cesar:
					result[i] = Clasica.CesarD(texts[i], keys[i]);
				case Vigenere:
					result[i] = Clasica.VigenereD(texts[i], new int[] {keys[i]});
				default:
					result[i] = Clasica.CesarD(texts[i], keys[i]);
			}
		return result;
	}


}
