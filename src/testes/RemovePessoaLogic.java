package testes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;

import aula011.dao.PessoaDAO;

public class RemovePessoaLogic implements Logica {

	@Override
	public String executa(HttpServletRequest req, HttpServletResponse res) {
		Connection conn = (Connection) req.getAttribute("conexao");
		PessoaDAO pessoa = new PessoaDAO(conn);
		int id = Integer.parseInt(req.getParameter("id"));
		pessoa.remover(id);

		return "/mvc?logica=ListaPessoasLogic";
	}

}
