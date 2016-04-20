package aula011servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import aula011.dao.PessoaDAO;
import aula011.entity.Endereco;
import aula011.entity.Pessoa;

/**
 * Servlet implementation class Aula11Atividade6Servlet
 */
@WebServlet("/formu")
public class Aula11Atividade6Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Aula11Atividade6Servlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.processa(request, response);
		response.getWriter().println("Succesful");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.incluir(request);
		//this.processa(request, response);
		response.getWriter().println("Succesful");
	}
	
	public void processa (HttpServletRequest request, HttpServletResponse response){
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String op = request.getParameter("acao");
		String resultado = "";
		switch (op) {
		case "incluir":
			resultado = this.incluir(request);
			break;
		case "atualizar":
			resultado = this.atualizar(request);
			break;
		case "remover":
			resultado = this.remover(request);
			break;
		case "listar":
			resultado = this.Listar();
		default:
			resultado = "Nehuma ação feita";
			break;
		}
		
		try {
			this.escreversaida(response, resultado);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String incluir(HttpServletRequest request){
		Endereco end = new Endereco();
		end.setBairro(request.getParameter("bairro"));
		end.setRua(request.getParameter("rua"));
		
		String testeNum = request.getParameter("num");
		
		end.setNumero(Integer.parseInt(request.getParameter("num")));
		
		Pessoa pessoa = new Pessoa();
		pessoa.setEndereco(end);
		pessoa.setNome(request.getParameter("nome"));
		pessoa.setSobrenome(request.getParameter("sobrenome"));
		
		PessoaDAO dao = new PessoaDAO();
		int idPessoa = dao.incluir(pessoa);
		return "Pessoa inserida com sucesso" + idPessoa;
	}
	
	public String atualizar(HttpServletRequest request){
		PessoaDAO pessoaDAO = new PessoaDAO();
		Pessoa pessoa = pessoaDAO.obterPessoa(2);
		pessoa.setNome("João");
		pessoa.setSobrenome("da Silva");
		pessoa.setEndereco(obterEndereco(pessoa.getId()));
		
		pessoaDAO = new PessoaDAO();
		if(pessoaDAO.atualizar(pessoa) == true){
			return "Pessoa atualizada com sucesso, ID: " + pessoa.getId();
		}else{
			return "Erro ao atualizar pessoa, ID: " + pessoa.getId();
		}
		
	}
	
	public String remover(HttpServletRequest request){
		return "remover";
	}
	public String Listar(){
		String result = "";
		PessoaDAO dao = new PessoaDAO();
		java.util.List<Pessoa> lista = dao.listarPessoas();
		for(Pessoa p : lista){
			result += "Nome: " + p.getNome()+" " + p.getSobrenome() + "<br>";
			result += "Endereco: " + p.getEndereco().getRua() + ", " + p.getEndereco().getNumero() + "<br><br>";
		}
		
		return result;
	}
	
	public void escreversaida (HttpServletResponse response, String msg) throws IOException{
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
				out.println("<!DOCTYPE html>");
				out.println("<html>");
				out.println("<head>");
				out.println("<title>JDBC - DAO</title>");
				out.println("</head>");
				out.println("<body>");
				out.println("<h1>"+ msg + "</h1>");
				out.println("</body>");
				out.println("</html>");
	}
	private static Endereco obterEndereco(int cdPessoa){
		aula011.dao.EnderecoDAO enderecoDAO = new aula011.dao.EnderecoDAO();
		return enderecoDAO.obterEndereco(cdPessoa);
	}
}
