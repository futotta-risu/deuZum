package io.github.fatsquirrels.deuzum.Security.Encryption;



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

public class Moderno {


public static String RSAE(String text, PublicKey pKey) throws
NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException,
IllegalBlockSizeException, BadPaddingException {

   Cipher cipher = Cipher.getInstance("RSA");
   cipher.init(Cipher.ENCRYPT_MODE, pKey);

   return bytesToString(cipher.doFinal(text.getBytes()));

}

public String RSAD(String result, PrivateKey privateStr) throws NoSuchAlgorithmException,
   NoSuchPaddingException, InvalidKeyException,
   IllegalBlockSizeException, BadPaddingException {

Cipher cipher = Cipher.getInstance("RSA");
cipher.init(Cipher.DECRYPT_MODE, privateStr);
return bytesToString(cipher.doFinal(stringToBytes(result)));

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
