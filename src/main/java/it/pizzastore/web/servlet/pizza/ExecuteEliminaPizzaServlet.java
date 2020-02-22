package it.pizzastore.web.servlet.pizza;

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

import it.pizzastore.model.Pizza;
import it.pizzastore.service.PizzaService;

/**
 * Servlet implementation class ExecuteEliminaMunicipioServlet
 */
@WebServlet("/pizzaiolo/pizze/ExecuteEliminaPizzaServlet")
public class ExecuteEliminaPizzaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Autowired
	private PizzaService pizzaService;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ExecuteEliminaPizzaServlet() {
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
		Long id = Long.parseLong(request.getParameter("idPizza"));

		pizzaService.rimuovi(new Pizza(id));

		request.setAttribute("listaPizzeAttr", pizzaService.listAll());
		request.setAttribute("messaggioConferma", "Cancellazione avvenuta con successo");

		RequestDispatcher rd = request.getRequestDispatcher("/pizzaiolo/pizze/result.jsp");
		rd.forward(request, response);

	}

}
