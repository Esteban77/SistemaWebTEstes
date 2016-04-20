package testes;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;

import aula011.dao.PessoaDAO;
import aula011.entity.Endereco;
import aula011.entity.Pessoa;

@WebServlet("/adiciona")
public class AdicionaPessoaLogic extends HttpServlet implements Logica {

	private static final long serialVersionUID = 1L;

	public AdicionaPessoaLogic() {
		super();
	}

	@Override
	public String executa(HttpServletRequest req, HttpServletResponse res) {
		String acao = req.getParameter("acao");
		Connection conn = (Connection) req.getAttribute("conexao");
		try {
			if (acao.equalsIgnoreCase("alter")) {
				int id = Integer.parseInt(req.getParameter("id"));
				PessoaDAO dao = new PessoaDAO(conn);
				Pessoa pe = dao.obterPessoa(id);
				req.setAttribute("id", id);
				req.setAttribute("pessoa", pe);
				// if (!res.isCommitted()){
				return "/testes/alterar-pessoas.jsp";
				// req.getServletContext().getRequestDispatcher("/testes/alterar-pessoas.jsp").forward(req,
				// res);
				// }else{System.out.println("response commited");}
			} else {
				return "/testes/adiciona-pessoas.jsp";
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	protected void service(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		try {
			if (id.equals("")) {
				this.adicionar(request, response);
				request.getServletContext().getRequestDispatcher("/mvc?logica=ListaPessoasLogic").forward(request,
						response);
			} else {
				int cod = Integer.parseInt(id);
				this.alterar(request, response, cod);
				request.getServletContext().getRequestDispatcher("/mvc?logica=ListaPessoasLogic").forward(request,
						response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void alterar(HttpServletRequest req, HttpServletResponse res, int id) {
		Connection conn = (Connection) req.getAttribute("conexao");
		PessoaDAO dao = new PessoaDAO(conn);
		Pessoa pessoa = new PessoaDAO(conn).obterPessoa(id);
		pessoa.setNome(req.getParameter("nome"));
		pessoa.setSobrenome(req.getParameter("sobrenome"));
		Endereco end = pessoa.getEndereco();
		end.setRua(req.getParameter("rua"));
		end.setBairro(req.getParameter("bairro"));
		end.setNumero(Integer.parseInt(req.getParameter("num")));
		pessoa.setEndereco(end);
		dao.atualizar(pessoa);
	}

	public void adicionar(HttpServletRequest req, HttpServletResponse res) {
		Connection conn = (Connection) req.getAttribute("conexao");
		Pessoa pessoa = new Pessoa();
		Endereco end = new Endereco();
		end.setBairro(req.getParameter("bairro"));
		end.setNumero(Integer.parseInt(req.getParameter("num")));
		end.setRua(req.getParameter("rua"));
		pessoa.setEndereco(end);
		pessoa.setNome(req.getParameter("nome"));
		pessoa.setSobrenome(req.getParameter("sobrenome"));
		PessoaDAO dao = new PessoaDAO(conn);
		dao.incluir(pessoa);

	}

}
