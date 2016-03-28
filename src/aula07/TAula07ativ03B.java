package aula07;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TAula07ativ03B
 */
@WebServlet("/TAula07ativ03B")
public class TAula07ativ03B extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TAula07ativ03B() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String atributo = (String) getServletContext().getAttribute("atributo");
		String para1 = request.getParameter("nome");
		
		PrintWriter out = response.getWriter();
		out.println("GET (Servlet B) (Veio de um REDIRECT)");
		out.println("Atributo: " + atributo);
		out.println("Parametros: " + para1);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String atributo = (String) getServletContext().getAttribute("atributo");
		
		String parametro1 = request.getParameter("nome");
		
		PrintWriter out = response.getWriter();
		out.println("GET (Servlet B) (Veio de um FORWARD)");
		out.println("Atributo: " + atributo);
		
		out.println("Parâmetro: " + parametro1);

	}

}
