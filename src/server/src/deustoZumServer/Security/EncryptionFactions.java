package deustoZumServer.Security;

import deustoZumServer.Security.Encryption.EncryptionTypes;

public class EncryptionFactions {
	public void Llamada() {
		
	
	
	
	
		
	static String getEncrypt(String a, int key, EncryptionTypes e) {
		switch(e) {
		
		case CesarE:
			
		return CesarE(a,key);
	}
	}
	case CesarD:
	public static final String getDecrypt(String a, int key, EncryptionTypes e) {
	return CesarE(a,key,e) ;
	}
	case VigenereE:
	public static final String getEncrypt(String[] a,  int[] key , EncryptionTypes[] e) {
	
	return VigenereE(a,key,e);
	}
	case VigenereD:
	public static final String getDecrypt(String[] a,  int[] key , EncryptionTypes[] e) {
	return VigenereD(a,key,e);
	}
	
	case RSAE:
	public static final String getEncrypt(String[] a, int key , EncryptionTypes e) {
	return RSAE(a,key,e);
	
	}
	case RSAD:
	public static final String getDecrypt(String[] a, int key , EncryptionTypes e) {
	return RSAD(a,key,e);
	}
	//public static final String getEncrypt(String[] a, int[] key ,EncryptionTypes e) {}
	//public static final String getDecrypt(String[] a, int[] key ,EncryptionTypes e) {}
	
}
}
	