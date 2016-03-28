package aula06;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/atividade04")
public class TExemplo04Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public TExemplo04Servlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String[] valores = request.getParameterValues("valor");
		int total = 0;
		for(String valor : valores){
			out.println("Valor: "+valor);
			total += Integer.parseInt(valor);
		}
			out.println("Total: "+ total);
	}

}
