package it.pizzastore.web.servlet.ordine;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import it.pizzastore.model.Ingrediente;
import it.pizzastore.model.Ordine;
import it.pizzastore.model.Pizza;
import it.pizzastore.service.IngredienteService;
import it.pizzastore.service.OrdineService;
import it.pizzastore.service.PizzaService;

/**
 * Servlet implementation class PrepareEliminaMunicipioServlet
 */
@WebServlet("/pizzaiolo/ordini/PrepareEliminaOrdineServlet")
public class PrepareEliminaOrdineServlet extends HttpServlet {
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
	public PrepareEliminaOrdineServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idInput = request.getParameter("idOrdine");
		Ordine ordineAttr = ordineService.caricaSingoloEager(Long.parseLong(idInput));

		if (ordineAttr.isClosed()) {
			request.setAttribute("messaggioErrore", "Non è possibile eliminare un ordine chiuso");
			request.setAttribute("listaOrdiniAttr", ordineService.listAll());
			request.getRequestDispatcher("/pizzaiolo/ordini/result.jsp").forward(request, response);
			return;
		}

		request.setAttribute("ordineAttr", ordineAttr);
		request.getRequestDispatcher("/pizzaiolo/ordini/delete.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
