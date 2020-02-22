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
 * Servlet implementation class ExecuteEliminaMunicipioServlet
 */
@WebServlet("/pizzaiolo/ingredienti/ExecuteEliminaIngredienteServlet")
public class ExecuteEliminaIngredienteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Autowired
	private IngredienteService ingredienteService;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ExecuteEliminaIngredienteServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Long id = Long.parseLong(request.getParameter("idMunicipio"));

		ingredienteService.rimuovi(new Ingrediente(id));

		request.setAttribute("listaIngredientiAttr", ingredienteService.listAll());
		request.setAttribute("messaggioConferma", "Cancellazione avvenuta con successo");

		RequestDispatcher rd = request.getRequestDispatcher("/pizzaiolo/ingredienti/result.jsp");
		rd.forward(request, response);

	}

}
