package testes2;

public class ContaCorrente {

	private int numero;
	private Double entrada;
	private Double saldo;
	private Pessoa cliente;
	
	public ContaCorrente(int numero, Double entrada, Pessoa cliente) {
		super();
		this.numero = numero;
		this.entrada = entrada;
		this.cliente = cliente;
		this.saldo = entrada;
	}

	public int getNumero() {
		return numero;
	}
		
	private void setSaldo(Double saldo) {
		this.saldo = saldo;
	}

	public Double getSaldo() {
		return saldo;
	}

	public Pessoa getCliente() {
		return cliente;
	}
	
	public void depositar(Double valor){
		this.setSaldo(getSaldo()+ valor);	
		System.out.println("Valor depositado: " + valor); 
		System.out.println("Saldo: " + saldo);
	}
	
	public void sacar(Double valor){
		if(valor>this.getSaldo()){
			System.out.println("Valor não permitido. Insira um valor menor.");
		}else{
			this.setSaldo(getSaldo()-valor);
			System.out.println("Valor sacado: " + valor);
			System.out.println("Saldo: "+this.getSaldo());
		}
	}
	public void extrato(){
		System.out.println("Extrato Simples");
		System.out.println("Numero da Conta: "+this.getNumero());
		System.out.println("Nome: "+ this.getCliente().getNome());
		System.out.println("Saldo: " + this.getSaldo());
	}
	
	
}
