package deustoZumServer.Algorithms.Math;

public class Vectors {
	public static double[] sumV(double[] arr1, double[] arr2) {
		if(arr1.length != arr2.length) return null;
		
		for(int i = 0; i < arr1.length; i++)
			arr1[i]+=arr2[i];
		return arr1;
	}
	public static double[] sumVC(double[] arr1, double val) {
		
		for(int i = 0; i < arr1.length; i++)
			arr1[i]+=val;
		return arr1;
	}
	
	public static double[] subV(double[] arr1, double[] arr2) {
		if(arr1.length != arr2.length) return null;
		
		for(int i = 0; i < arr1.length; i++)
			arr1[i]-=arr2[i];
		return arr1;
	}
	public static double[] subVC(double[] arr1, double val) {
		
		for(int i = 0; i < arr1.length; i++)
			arr1[i]-=val;
		return arr1;
	}
	
	public static double[] divVC(double[] arr1, double val) {
		
		for(int i = 0; i < arr1.length; i++)
			arr1[i]/=val;
		return arr1;
	}
}
