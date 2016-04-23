package testes2;

public class Container {
	
	private String nome;
	private int quantidade;
	private Double largura;
	private Double comprimento;
	private Double altura;
	
	public Container(String nome, int quantidade, Double largura, Double comprimento, Double altura) {
		super();
		this.nome = nome;
		this.quantidade = quantidade;
		this.largura = largura;
		this.comprimento = comprimento;
		this.altura = altura;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public Double getLargura() {
		return largura;
	}

	public void setLargura(Double largura) {
		this.largura = largura;
	}

	public Double getComprimento() {
		return comprimento;
	}

	public void setComprimento(Double comprimento) {
		this.comprimento = comprimento;
	}

	public Double getAltura() {
		return altura;
	}

	public void setAltura(Double altura) {
		this.altura = altura;
	}
	
	
	
	
}
