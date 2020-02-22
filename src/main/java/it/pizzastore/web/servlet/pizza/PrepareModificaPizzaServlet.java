package it.pizzastore.web.servlet.pizza;

import java.io.IOException;

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
 * Servlet implementation class PrepareModificaMunicipioServlet
 */
@WebServlet("/pizzaiolo/pizze/PrepareModificaPizzaServlet")
public class PrepareModificaPizzaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Autowired
	private PizzaService pizzaService;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	public PrepareModificaPizzaServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idPizza = request.getParameter("idPizza");
		Pizza result = pizzaService.caricaSingolaPizzaConIngredienti(Long.parseLong(idPizza));

		request.setAttribute("pizzaAttr", result);
		request.getRequestDispatcher("/pizzaiolo/pizze/modifica.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
