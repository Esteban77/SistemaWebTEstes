package testes;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;

import aula011.dao.PessoaDAO;
import aula011.entity.Pessoa;

public class ListaPessoasLogic implements Logica{

	@Override
	public String executa(HttpServletRequest req, HttpServletResponse res) {
		Connection conn = (Connection) req.getAttribute("conexao");
		List<Pessoa> lista = new PessoaDAO(conn).listarPessoas();
		req.setAttribute("Lista", lista);
				
		return "/testes/lista-pessoas.jsp";
	}
		
	

}
