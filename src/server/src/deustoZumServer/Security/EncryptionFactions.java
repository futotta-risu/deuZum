package deustoZumServer.Security;

import deustoZumServer.Security.Encryption.Clasica;
import deustoZumServer.Security.Encryption.EncryptionTypes;

public class EncryptionFactions {


public static String getEncrypt(String a, int key, EncryptionTypes e) {
switch(e) {
case Cesar:
return Clasica.CesarE(a, key);
case Vigenere:
return Clasica.VigenereE(a, new int[] {key});
default:
return Clasica.CesarE(a, key);
}

}
public static final String getDecrypt(String a, int key, EncryptionTypes e) {
switch(e) {
case Cesar:
return Clasica.CesarD(a, key);
case Vigenere:
return Clasica.VigenereD(a, new int[] {key});
default:
return Clasica.CesarD(a, key);
}
}
public static final String[] getEncrypt(String[] a,  int[] key , EncryptionTypes[] e) {
if(a.length!=key.length || key.length != e.length) return null;
String[] result = new String[a.length];
for(int i = 0; i < a.length; i++)
switch(e[i]) {
case Cesar:
result[i] = Clasica.CesarE(a[i], key[i]);
case Vigenere:
result[i] = Clasica.VigenereE(a[i], new int[] {key[i]});
default:
result[i] = Clasica.CesarE(a[i], key[i]);
}
return result;
}
public static final String[] getDecrypt(String[] a,  int[] key , EncryptionTypes[] e) {
if(a.length!=key.length || key.length != e.length) return null;
String[] result = new String[a.length];
for(int i = 0; i < a.length; i++)
switch(e[i]) {
case Cesar:
result[i] = Clasica.CesarD(a[i], key[i]);
case Vigenere:
result[i] = Clasica.VigenereD(a[i], new int[] {key[i]});
default:
result[i] = Clasica.CesarD(a[i], key[i]);
}
return result;
}

public static final String[] getEncrypt(String[] a, int[] key ,EncryptionTypes e) {
if(a.length!=key.length) return null;
String[] result = new String[a.length];
for(int i = 0; i < a.length; i++)
switch(e) {
case Cesar:
result[i] = Clasica.CesarD(a[i], key[i]);
case Vigenere:
result[i] = Clasica.VigenereD(a[i], new int[] {key[i]});
default:
result[i] = Clasica.CesarD(a[i], key[i]);
}
return result;
}
public static final String[] getDecrypt(String[] a, int[] key ,EncryptionTypes e) {
if(a.length!=key.length) return null;
String[] result = new String[a.length];
for(int i = 0; i < a.length; i++)
switch(e) {
case Cesar:
result[i] = Clasica.CesarD(a[i], key[i]);
case Vigenere:
result[i] = Clasica.VigenereD(a[i], new int[] {key[i]});
default:
result[i] = Clasica.CesarD(a[i], key[i]);
}
return result;
}



}
