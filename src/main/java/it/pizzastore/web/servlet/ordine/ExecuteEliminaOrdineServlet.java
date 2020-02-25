package it.pizzastore.web.servlet.ordine;

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

import it.pizzastore.model.Ordine;
import it.pizzastore.service.OrdineService;

/**
 * Servlet implementation class ExecuteEliminaMunicipioServlet
 */
@WebServlet("/pizzaiolo/ordini/ExecuteEliminaOrdineServlet")
public class ExecuteEliminaOrdineServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Autowired
	private OrdineService ordineService;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ExecuteEliminaOrdineServlet() {
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
		Long id = Long.parseLong(request.getParameter("idOrdine"));

		ordineService.rimuovi(new Ordine(id));

		request.setAttribute("listaOrdiniAttr", ordineService.listAllOrderByData());
		request.setAttribute("messaggioConferma", "Cancellazione avvenuta con successo");

		RequestDispatcher rd = request.getRequestDispatcher("/pizzaiolo/ordini/result.jsp");
		rd.forward(request, response);

	}

}
