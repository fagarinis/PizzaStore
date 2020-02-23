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

import it.pizzastore.dto.PizzaDTO;
import it.pizzastore.model.Ingrediente;
import it.pizzastore.model.Pizza;
import it.pizzastore.service.IngredienteService;
import it.pizzastore.service.PizzaService;

@WebServlet("/pizzaiolo/pizze/ExecuteSearchPizzaServlet")
public class ExecuteSearchPizzaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Autowired
	private PizzaService pizzaService;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	public ExecuteSearchPizzaServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String descrizioneInput = request.getParameter("descrizioneInput");
		String codiceInput = request.getParameter("codiceInput");
		String prezzoBaseInput = request.getParameter("prezzoBaseInput");
		String attivoInput = request.getParameter("attivoInput");

		PizzaDTO example = new PizzaDTO();
		example.setDescrizione(descrizioneInput);
		example.setCodice(codiceInput);
		example.setPrezzoBase(prezzoBaseInput);
		example.setAttivo(attivoInput);
		
		request.setAttribute("listaPizzeAttr", pizzaService.findByExample(PizzaDTO.buildModelFromDto(example)));
		request.getRequestDispatcher("/pizzaiolo/pizze/result.jsp").forward(request, response);
	}

}
