package deustoZumServer.Security.Encryption;

public class Clasica {

	public static String CesarE(String texto, int codigo) {
		String vacio="";
		codigo=codigo %26;
		for (int i = 0; i < texto.length(); i++) {
			
			int c=((int)texto.charAt(i))-97;
			if (c<0&&c>26) 
				vacio+=(char) c;
			vacio+=(char)((c + codigo)%26);
		}
		
		
		return vacio;
	}
	
	
	
	
	
	public static String VigenereE(String texto, int[] codigo) {
		String vacio="";
		int codigoL = codigo.length;
		for (int i = 0; i < texto.length(); i++) {
			
			int c=((int)texto.charAt(i))-97;
			if (c<0&&c>26) 
				vacio+=(char) c;
			vacio+=(char)((c + codigo[i%codigoL])%26);
		
		
		
		return vacio;
	}
	
	
	
}
