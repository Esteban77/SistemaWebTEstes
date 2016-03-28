package aula09;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/atividadeRevServlet")
public class AtividadeRevServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
		String op = retornaParametro(request, "op");
		String valor1 = retornaParametro(request, "valor1");
		String valor2 = retornaParametro(request, "valor2");
		String resultado = "";

		switch (op) {
		case "soma":
			resultado = String.valueOf(somaValores(valor1, valor2));
			break;

		case "subt":
			resultado = String.valueOf(subtraiValores(valor1, valor2));
			break;

		default:
			break;
		}

		PrintWriter out = response.getWriter();
		this.escreveSaida(resultado, out);
	}

	private String retornaParametro(HttpServletRequest request, String parametro) throws ServletException
	{
		if(request.getParameter(parametro) == null)
		{
			throw new ServletException();
		}
		else
			return request.getParameter(parametro);
	}
	
	private int somaValores(String valor1, String valor2)
	{
		return Integer.parseInt(valor1) + Integer.parseInt(valor2);
	}

	private int subtraiValores(String valor1, String valor2)
	{
		return Integer.parseInt(valor1) - Integer.parseInt(valor2);
	}

	private void escreveSaida(String resultado, PrintWriter out)
	{
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Servlet</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>Resultado: "+ resultado +"</h1>");
		out.println("</body>");
		out.println("</html>");
	}
}
