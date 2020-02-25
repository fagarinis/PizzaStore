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
import it.pizzastore.service.PizzaService;

/**
 * Servlet implementation class PrepareModificaMunicipioServlet
 */
@WebServlet("/pizzaiolo/ordini/PrepareModificaOrdineServlet")
public class PrepareModificaOrdineServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Autowired
	private OrdineService ordineService;

	@Autowired
	private PizzaService pizzaService;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	public PrepareModificaOrdineServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idInput = request.getParameter("idOrdine");
		Ordine ordineAttr = ordineService.caricaSingoloEager(Long.parseLong(idInput));

		if (ordineAttr.isClosed()) {
			request.setAttribute("messaggioErrore", "Non è possibile modificare un ordine chiuso");
			request.setAttribute("listaOrdiniAttr", ordineService.listAll());
			request.getRequestDispatcher("/pizzaiolo/ordini/result.jsp").forward(request, response);
			return;
		}

		request.setAttribute("pizzeListAttr", pizzaService.listAllActiveEager());
		request.setAttribute("ordineAttr", ordineAttr);
		request.getRequestDispatcher("/pizzaiolo/ordini/modifica.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
