package aula07;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TAula07ativ01
 */

@WebServlet("/TAtividade01")
public class TAula07ativ01 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
     * @see HttpServlet#HttpServlet()
     */
    
    public TAula07ativ01() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.setContentType("text/plain");
		response.setContentType("text/html");
		
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Servlet (Atividade 01 - Aula 07)</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>Exemplo de utilização: setContentType</h1>");
		out.println("<h2>H2</h2>");
		out.println("<p>parágrafo</p>");
        out.println("</body>");
        out.println("</html>");
	}
}
