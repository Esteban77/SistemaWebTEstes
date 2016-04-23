package testes2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import com.sun.javafx.collections.NonIterableChange.GenericAddRemoveChange;

public class CaixaBO {

	public static void main(String[] args) {
		Caixa caixa1 = new Caixa("caixa1", 210, 0.253, 0.608, 0.518);
		Caixa caixa2 = new Caixa("caixa2", 200 , 0.263, 0.480, 0.323);
		Caixa caixa3 = new Caixa("caixa3", 200, 0.203, 0.403, 0.413);
		Caixa caixa4 = new Caixa("caixa4", 200, 0.170, 0.530, 0.380);
		Caixa caixa5 = new Caixa("caixa5", 140, 0.285, 0.435, 0.255);
		
		Container container = new Container("container", 1, 2.50, 10.00, 3.28);
		CaixaBO contBO = new CaixaBO(container,caixa1);
		Double melhor = contBO.getMelhorComb();
		System.out.println(melhor);
		int nla = contBO.caixaConjLar;
		int nco = contBO.caixaConjCom; 
		System.out.println("Quantidade de caixa considerando largura: "+nla);
		System.out.println("Quantidade de caixa considerando comprimento: "+nco);
		contBO.gerarCoordenadas();
		//contBO.gerarCord();
		List<Caixa> lista = contBO.listaCaixa;
		for(Caixa caixa : lista){
		System.out.println("Coordenadas X: "+caixa.getxIni()+", "+ caixa.getxFim()+ 
							"\nCoordenadas Y: " + caixa.getyIni()+", "+ caixa.getyFim()+
							"\nCoordenadas Z: "+ caixa.getzIni()+", "+ caixa.getzFim());
		}
	}
	private Container container;
	private Caixa caixa;
	private Integer caixaQuantidadeMaxLargura;
	private Integer caixaQuantidadeMaxComprimento;
	private Integer caixaConjLar;
	private Integer caixaConjCom;
	private Double containerLarguraCont=0.00;
	private Double containerComprimentoCont=0.00;
	private Double containerAlturaCont=0.00;
	private List<Caixa> listaCaixa;
	
	
	public CaixaBO(Container container, Caixa caixa) {
		super();
		this.container = container;
		this.caixa = caixa;
	}

	public int calcaixaQuantidadeMaxLargura(){
		Double quantCaixa = container.getLargura()/caixa.getLargura();
		return caixaQuantidadeMaxLargura= (int) Math.floor(quantCaixa);
	}
	
	public int calcaixaQuantidadeMaxComprimento(){
		Double quantCaixa = container.getLargura()/caixa.getComprimento();
		return caixaQuantidadeMaxComprimento= (int) Math.floor(quantCaixa);
	} 
	
	public List<Integer> conjuntoLargura(){
		List<Integer> lista = new ArrayList<Integer>();
		int num = calcaixaQuantidadeMaxLargura();
		for(int i=0;i<=num;i++){
			lista.add(i);
		}
		return lista;
	}
	
	public List<Integer> conjuntoComprimento(){
		List<Integer> lista = new ArrayList<Integer>();
		int num = calcaixaQuantidadeMaxComprimento();
		for(int i=0;i<=num;i++){
			lista.add(i);
		}
		return lista;
	}
	
	public List<Double> grupoLargura(){
		List<Double> listaL = new ArrayList<>();
		List<Integer> li=this.conjuntoLargura();
		for(Integer num : li){
			listaL.add(num*this.caixa.getLargura());
		}
		return listaL;
	}
	
	public List<Double> grupoComprimento(){
		List<Double> listaC = new ArrayList<>();
		List<Integer> li = this.conjuntoComprimento();
		for(Integer num : li){
			listaC.add(num*this.caixa.getComprimento());
		}
		return listaC;
	}
	
/*	public LinkedHashMap<Integer, Double> grupoComprimento(){
		List<Double> listaC = new ArrayList<>();
		LinkedHashMap<Integer, Double> map = new LinkedHashMap<>();
		int i=0;
		for(Iterator<Integer> it=this.conjuntoComprimento().iterator();it.hasNext();){
			i=this.conjuntoComprimento().iterator().next();
			map.put(i,i*this.caixa.getComprimento() );
		}
		return map;
	}*/
	
	public Double getMelhorComb(){
		Double valorU=0.00;
		Double combMelhor=this.container.getLargura();
		Double valorMelhor=this.container.getLargura();
		for(int i=0;i<this.grupoLargura().size();i++){
			for(int j=0;j<this.grupoComprimento().size();j++){
				Double comUnid = this.grupoLargura().get(i)+this.grupoComprimento().get(j);
				if(comUnid<=this.container.getLargura()){
					valorU = this.container.getLargura()-comUnid;
					if(comUnid==this.container.getLargura()){
						combMelhor=comUnid;
						caixaConjLar=this.conjuntoLargura().get(i);
						caixaConjCom=this.conjuntoComprimento().get(i);
						//break;
					}else if(valorU<valorMelhor){
						valorMelhor=valorU;
						combMelhor = comUnid;
						caixaConjLar=this.conjuntoLargura().get(i);
						caixaConjCom=this.conjuntoComprimento().get(j);
					}
				}
			}
		}
		return combMelhor;
	}
	
	public void gerarCord(){
		listaCaixa = new ArrayList<>();
		for(int i = 0;i<100;i++){	
			caixa = new Caixa(caixa.getNome(), caixa.getQuantidade(), caixa.getLargura(), caixa.getComprimento(), caixa.getAltura());
			if(containerLarguraCont + caixa.getLargura()<=container.getLargura()){
				setarCaixas();
				containerLarguraCont = containerLarguraCont + caixa.getLargura();
			}else if(caixa.getAltura()+containerAlturaCont <= container.getAltura()){
				containerAlturaCont = caixa.getAltura()+containerAlturaCont;
				containerLarguraCont = 0.00;				
			} else if(caixa.getComprimento()+containerComprimentoCont <= container.getComprimento()) { 
				containerAlturaCont = 0.00;
				containerLarguraCont = 0.00;
				containerComprimentoCont = containerComprimentoCont+caixa.getComprimento();				
			}else {
				System.out.println("Acabou o espaço");
				break;
			}			
		}		
	}
	public void gerarCoordenadas(){
		listaCaixa = new ArrayList<>();
		for(int i = 0;i<this.caixaConjLar;i++){
			Caixa caix = new Caixa(caixa.getNome(), caixa.getQuantidade(), caixa.getLargura(), caixa.getComprimento(), caixa.getAltura());
			caix.setxIni(containerLarguraCont);
			caix.setxFim(caixa.getLargura()+ containerLarguraCont);
			caix.setyIni(containerAlturaCont);
			caix.setyFim(caixa.getAltura()+containerAlturaCont);
			caix.setzIni(containerComprimentoCont);
			caix.setzFim(caixa.getComprimento()+containerComprimentoCont);
			listaCaixa.add(caix);
			containerLarguraCont= containerLarguraCont+caix.getLargura();			
		}
		for(int i = 0;i<this.caixaConjCom;i++){
			Caixa caix = new Caixa(caixa.getNome(), caixa.getQuantidade(), caixa.getLargura(), caixa.getComprimento(), caixa.getAltura());
			caix.setxIni(containerLarguraCont);
			caix.setxFim(caixa.getComprimento()+ containerLarguraCont);
			caix.setyIni(containerAlturaCont);
			caix.setyFim(caixa.getAltura()+containerAlturaCont);
			caix.setzIni(containerComprimentoCont);
			caix.setzFim(caixa.getLargura()+containerComprimentoCont);
			listaCaixa.add(caix);
			containerLarguraCont=containerLarguraCont+caix.getComprimento();
			
		}
		
	}
	public void setarCaixas(){
		caixa.setxIni(containerLarguraCont);
		caixa.setxFim(caixa.getLargura()+ containerLarguraCont);
		caixa.setyIni(containerAlturaCont);
		caixa.setyFim(caixa.getAltura()+containerAlturaCont);
		caixa.setzIni(containerComprimentoCont);
		caixa.setzFim(caixa.getComprimento()+containerComprimentoCont);
		listaCaixa.add(caixa);
	}	
	

}
