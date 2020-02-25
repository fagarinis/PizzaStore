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

import it.pizzastore.model.Ordine;
import it.pizzastore.service.OrdineService;

/**
 * Servlet implementation class ExecuteDettaglioMunicipioServlet
 */
@WebServlet("/pizzaiolo/ordini/ExecuteDettaglioOrdineServlet")
public class ExecuteDettaglioOrdineServlet extends HttpServlet {
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
	public ExecuteDettaglioOrdineServlet() {
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
		
		request.setAttribute("ordineAttr", ordineAttr);
		request.getRequestDispatcher("/pizzaiolo/ordini/dettaglio.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
