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
import it.pizzastore.model.Pizza;
import it.pizzastore.service.IngredienteService;
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
		String idPizza = request.getParameter("idPizza");
		Pizza result = pizzaService.caricaSingolaPizzaConIngredienti(Long.parseLong(idPizza));
		
		String[] idIngredientiChecked = new String[result.getIngredienti().size()];
		int i = 0;
		for(Ingrediente ingrediente: result.getIngredienti()) {
			idIngredientiChecked[i++] = ingrediente.getId().toString();
		}
		
		request.setAttribute("listaIngredientiCheckedAttr", idIngredientiChecked);
		request.setAttribute("ingredientiListAttr", ingredienteService.listAll());
		request.setAttribute("pizzaAttr", result);
		request.getRequestDispatcher("/pizzaiolo/ordini/modifica.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
