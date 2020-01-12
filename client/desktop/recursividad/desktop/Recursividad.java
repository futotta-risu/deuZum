package desktop;

public class Recursividad {
	
	public static void main(String[] args) {
		int n=5;
		int resultado= sumaRecursiva(n);
		System.out.println("resultado suma Recursiva: " + resultado);
		
		System.out.println("resultado fibonacci: " + fibonacci(12));
		
		
	}
	
	public static int sumaRecursiva(int numero) {
		int res;
		if (numero == 1) {
			return 1;
		} else {
			res= numero + sumaRecursiva(numero - 1);
			
		}
		
		return res;
	}
	
	public static int fibonacci(int s) {
		if (s==0) {
			return 0;
		} else if(s==1) {
			return 1;
		}else {
			return fibonacci(s-1) + fibonacci(s-2);
		}
	}

}
