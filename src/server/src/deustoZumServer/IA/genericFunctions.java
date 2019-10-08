package deustoZumServer.IA;

import java.util.ArrayList;
import java.util.LinkedList;

public class genericFunctions {
	
public static ArrayList<int[]> KNN(ArrayList<double[][]> users, ArrayList<int[]> labels, ArrayList<double[][]> newVector, int totalCases){
		ArrayList<int[]> resultMatrix= new ArrayList<int[]>();
		for(int i = 0;  i < totalCases; i++) {
			int KNNCaseSize = newVector.get(i).length;
			// Para cada caso de KNN
			int[] tempVal = new int[KNNCaseSize];
			for(int j = 0; j < KNNCaseSize; j++) 
				// Para cada vector que queremos comprobar
				tempVal[j] = KNN(users.get(i), labels.get(i), newVector.get(i)[j]);
			
			resultMatrix.add(tempVal);
		}
		return resultMatrix;
	}
	
	public static int KNN(double[][] users, int[] lables, double[] newVector) {
		// TODO Lander, crea esta funcion
		return 0;
	}
	
}
