package io.github.fatsquirrels.deuzum.security.encryption;



import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Arrays;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;


/**
 * Clase Moderno
 * Contiene los Algoritmos para encriptacion y desencriptacion en RSA
 * @see #RSAE
 * @see #RSAD
 */
public class Moderno {

/**
 * Este metodo devuelve un texto Encriptado con el algoritmo de RSA
 * @param text Texto a encriptar
 * @param pKey Llave privada para encriptar el texto
 * @return Texto encriptado con RSA
 * @throws NoSuchAlgorithmException
 * @throws NoSuchPaddingException
 * @throws InvalidKeyException
 * @throws IllegalBlockSizeException
 * @throws BadPaddingException
 */
public static String RSAE(String text, PublicKey pKey) throws
NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException,
IllegalBlockSizeException, BadPaddingException {

   Cipher cipher = Cipher.getInstance("RSA");
   cipher.init(Cipher.ENCRYPT_MODE, pKey);

   return bytesToString(cipher.doFinal(text.getBytes()));

}

/**
 * Este metodo devuelve un texto Desencriptado, previamente encriptado con el Algoritmo de RSAE
 * @see #RSAE
 * @see #bytesToString
 * @see #stringToBytes
 * @param text Texto a desencriptar
 * @param privateStr Llave privada para desencriptar el texto
 * @return Texto desencriptado con RSA
 * @throws NoSuchAlgorithmException
 * @throws NoSuchPaddingException
 * @throws InvalidKeyException
 * @throws IllegalBlockSizeException
 * @throws BadPaddingException
 */
public String RSAD(String text, PrivateKey privateStr) throws NoSuchAlgorithmException,
   NoSuchPaddingException, InvalidKeyException,
   IllegalBlockSizeException, BadPaddingException {

Cipher cipher = Cipher.getInstance("RSA");
cipher.init(Cipher.DECRYPT_MODE, privateStr);
return bytesToString(cipher.doFinal(stringToBytes(text)));

}


// Utility

public static String bytesToString(byte[] b) {
   byte[] b2 = new byte[b.length + 1];
   b2[0] = 1;
   System.arraycopy(b, 0, b2, 1, b.length);
   return new BigInteger(b2).toString(36);
}

public static byte[] stringToBytes(String s) {
   byte[] b2 = new BigInteger(s, 36).toByteArray();
   return Arrays.copyOfRange(b2, 1, b2.length);
}

}
