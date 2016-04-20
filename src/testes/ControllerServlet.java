package testes;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/mvc")
public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;       
    
    public ControllerServlet() {
        super();
    }
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException{
		String parametro = request.getParameter("logica");
		String nomeDaClasse = "testes."+parametro;
		
		try {
			Class classe = Class.forName(nomeDaClasse);
			Logica logica = (Logica) classe.newInstance();
			String pagina = logica.executa(request, response);
			
			request.getServletContext().getRequestDispatcher(pagina).forward(request, response);
			
		} catch (Exception e) {
			throw new ServletException(
	             "A lógica de negócios causou uma exceção", e);
		}
		
		
	}

}
