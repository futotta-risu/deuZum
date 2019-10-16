package deustoZumServer.Algorithms;

public class TextFunctions {

	public static final String[] surroundText(String[] arr, String presuffix) {
		for(int i = 0; i < arr.length; i++)
			arr[i] = presuffix + arr[i] + presuffix;
		return arr;
	}
	
	public static final String[] surroundText(String[] arr, String preffix, String suffix) {
		for(int i = 0; i < arr.length; i++)
			arr[i] = preffix + arr[i] + suffix;
		return arr;
	}
	
	public static final String[] concatenateAlternative(String[] arr1, String[] arr2,String concat) {
		if(arr1.length<arr2.length) 
			return concatenateAlternative(arr2, arr1,concat);
		
		for(int i = 0; i < arr2.length; i++) 
			arr1[i] = arr1[i]+concat+arr2[i];
		
		for(int i = arr1.length; i< arr1.length; i++)
			arr1[i] = arr1[i]+concat;
		return arr1;
		
	}
	
	public static final String[] concatenateAlternative(String[] arr1, String[] arr2,String concat, String nullText) {
		if(arr1.length<arr2.length) 
			return concatenateAlternative(arr2, arr1, concat, nullText);
		
		for(int i = 0; i < arr2.length; i++) 
			arr1[i] = arr1[i]+concat+arr2[i];
		
		String concatNull = concat+nullText;
		for(int i = arr1.length; i< arr1.length; i++)
			arr1[i] = arr1[i]+concatNull;
		return arr1;
		
	}
	public static final String[] concatenateAlternative(String[] arr1, String[] arr2,String concat, String nullText, String surroundText) {
		if(arr1.length<arr2.length)
			return concatenateAlternative(arr2, arr1,concat, nullText, surroundText);
		
		for(int i = 0; i < arr2.length; i++) 
			arr1[i] = surroundText+arr1[i]+concat+arr2[i]+surroundText;
		
		String concatNull = concat+nullText+surroundText;
		for(int i = arr1.length; i< arr1.length; i++)
			arr1[i] = surroundText+arr1[i]+concatNull;
		return arr1;
		
	}
	
	
	
}
