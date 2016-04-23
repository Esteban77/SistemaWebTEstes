package testes2;

public class Fibonacci {

	private int[] lista = null;
	
	public static void main(String[] args) {		
		new Fibonacci().calculo();
	}
	
	public void calculo(){
		lista = new int[10];
				
		for(int i=0;i<lista.length;i++){	
			if(i<2){
				lista[i]=i;
				System.out.println(lista[i]);
			}else{
				lista[i]= lista[i-1] + lista[i-2];	
				System.out.println(lista[i]);
			}
		}		
	}

}
