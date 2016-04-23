package testes2;

public class testeConta {

	public static void main(String[] args) {
	Pessoa pessoa = new Pessoa("Esteban", 123456);
	ContaCorrente conta = new ContaCorrente(6543, (double) 200, pessoa);
	conta.depositar(54.34);
	conta.sacar(40.00);	
	conta.extrato();
	}

}
