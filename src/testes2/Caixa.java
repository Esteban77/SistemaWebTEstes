package testes2;

public class Caixa {		
	
	private String nome;
	private int quantidade;
	private Double largura;
	private Double comprimento;
	private Double altura;
	private Double xIni;
	private Double xFim;
	private Double yIni;
	private Double yFim;
	private Double zIni;
	private Double zFim;	

	public Caixa(String nome, int quantidade, Double largura, Double comprimento, Double altura) {
		super();
		this.nome = nome;
		this.quantidade = quantidade;
		this.largura = largura;
		this.comprimento = comprimento;
		this.altura = altura;
	}
	
	public Double getxIni() {
		return xIni;
	}

	public void setxIni(Double xIni) {
		this.xIni = xIni;
	}

	public Double getxFim() {
		return xFim;
	}

	public void setxFim(Double xFim) {
		this.xFim = xFim;
	}

	public Double getyIni() {
		return yIni;
	}

	public void setyIni(Double yIni) {
		this.yIni = yIni;
	}

	public Double getyFim() {
		return yFim;
	}

	public void setyFim(Double yFim) {
		this.yFim = yFim;
	}

	public Double getzIni() {
		return zIni;
	}

	public void setzIni(Double zIni) {
		this.zIni = zIni;
	}

	public Double getzFim() {
		return zFim;
	}

	public void setzFim(Double zFim) {
		this.zFim = zFim;
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
