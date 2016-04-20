package testes;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import aula10.TConectionFactory;

/**
 * Servlet Filter implementation class FiltroConexao
 */
@WebFilter("/*")
public class FiltroConexao implements Filter {

    public FiltroConexao() {  }

	public void destroy() {	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		try {
		
		com.mysql.jdbc.Connection connection = new TConectionFactory().getconection();
		request.setAttribute("conexao", connection);
		chain.doFilter(request, response);
		connection.close();
		} catch (SQLException e) {
			throw new ServletException();
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {}

}
