package aula08;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/a11Servlet")
public class TAula08ativ11 extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public TAula08ativ11() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] array = {"Esteban", "João", "Maria", "Andressa", "Pedro"};		
		this.getServletContext().setAttribute("nomes", array);
		
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Servlet (Atividade 11 - Aula 08)</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>Nomes</h1>");
		
		for(String nome : array){
			out.println("Nome: " + nome + "<br>");
		}
		out.println("</body>");
        out.println("</html>");
	}

}
