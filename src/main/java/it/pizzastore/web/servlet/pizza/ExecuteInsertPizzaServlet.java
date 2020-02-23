package it.pizzastore.web.servlet.pizza;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import it.pizzastore.dto.PizzaDTO;
import it.pizzastore.model.Pizza;
import it.pizzastore.service.IngredienteService;
import it.pizzastore.service.PizzaService;

@WebServlet("/pizzaiolo/pizze/ExecuteInsertPizzaServlet")
public class ExecuteInsertPizzaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Autowired
	private PizzaService pizzaService;
	
	@Autowired
	private IngredienteService ingredienteService;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	public ExecuteInsertPizzaServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// binding
		String descrizioneInput = request.getParameter("descrizioneInput");
		String codiceInput = request.getParameter("codiceInput");
		String prezzoBaseInput = request.getParameter("prezzoBaseInput");
		String attivoInput = request.getParameter("attivoInput");
		String[] idIngredientiInput = request.getParameterValues("ingredienteInput");

		PizzaDTO pizzaDTO = new PizzaDTO();
		pizzaDTO.setDescrizione(descrizioneInput);
		pizzaDTO.setCodice(codiceInput);
		pizzaDTO.setPrezzoBase(prezzoBaseInput);
		pizzaDTO.setIngredienti(idIngredientiInput);
		pizzaDTO.setAttivo(attivoInput);
		
		// effettuo la validazione dell'input e se non va bene rimando in pagina
		List<String> pizzaErrors = pizzaDTO.errors();
		if (!pizzaErrors.isEmpty()) {
			request.setAttribute("pizzaAttr", pizzaDTO);
			request.setAttribute("pizzaErrors", pizzaErrors);
			request.setAttribute("listaIngredientiCheckedAttr", pizzaDTO.getIdIngredienti());
			request.setAttribute("ingredientiListAttr", ingredienteService.listAll());
			request.getRequestDispatcher("/pizzaiolo/pizze/insert.jsp").forward(request, response);
			return;
		}

		// se arrivo qui significa che va bene
		Pizza pizzaInstance = PizzaDTO.buildModelFromDto(pizzaDTO);
		pizzaService.inserisciNuovo(pizzaInstance);

		// vado in pagina con ok
		request.setAttribute("messaggioConferma", "Inserimento avvenuto con successo");
		request.setAttribute("listaPizzeAttr", pizzaService.listAll());
		request.getRequestDispatcher("/pizzaiolo/pizze/result.jsp").forward(request, response);
	}

}
