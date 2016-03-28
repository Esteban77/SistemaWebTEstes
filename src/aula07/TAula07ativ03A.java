package aula07;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.ProcessBuilder.Redirect;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TAula07ativ03A
 */
@WebServlet("/a07Atividade03A")
public class TAula07ativ03A extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TAula07ativ03A() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		out.println("GET");
		
		getServletContext().setAttribute("atributo", "Valor de Atributo");
		
		response.sendRedirect("/Testes/TAula07ativ03B");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		out.println("POST");
		
		getServletContext().setAttribute("atributo", "Valor do Atributo");
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/TAula07ativ03B");
		dispatcher.forward(request, response);
	}

}
