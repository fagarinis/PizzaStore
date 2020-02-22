package it.pizzastore.web.servlet.ingrediente;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import it.pizzastore.model.Ingrediente;
import it.pizzastore.service.IngredienteService;

/**
 * Servlet implementation class PrepareModificaMunicipioServlet
 */
@WebServlet("/pizzaiolo/ingredienti/PrepareModificaIngredienteServlet")
public class PrepareModificaIngredienteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Autowired
	private IngredienteService ingredienteService;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	public PrepareModificaIngredienteServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String parametroIdIngredienteDaModificare = request.getParameter("idIngrediente");
		Ingrediente result = ingredienteService.caricaSingolo(Long.parseLong(parametroIdIngredienteDaModificare));

		request.setAttribute("ingredienteAttr", result);
		RequestDispatcher rd = request.getRequestDispatcher("/pizzaiolo/ingredienti/modifica.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
