package it.pizzastore.web.servlet.ordine;

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
import it.pizzastore.service.OrdineService;
import it.pizzastore.service.PizzaService;

/**
 * Servlet implementation class ExecuteModificaMunicipioServlet
 */
@WebServlet("/pizzaiolo/ordini/ExecuteModificaOrdineServlet")
public class ExecuteModificaOrdineServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Autowired
	private OrdineService ordineService;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	public ExecuteModificaOrdineServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String idInput = request.getParameter("idInput");
		String descrizioneInput = request.getParameter("descrizioneInput");
		String codiceInput = request.getParameter("codiceInput");
		String prezzoBaseInput = request.getParameter("prezzoBaseInput");
		String attivoInput = request.getParameter("attivoInput");
		String[] idIngredientiInput = request.getParameterValues("ingredienteInput");

		PizzaDTO pizzaDTO = new PizzaDTO();
		pizzaDTO.setId(Long.parseLong(idInput));
		pizzaDTO.setDescrizione(descrizioneInput);
		pizzaDTO.setCodice(codiceInput);
		pizzaDTO.setPrezzoBase(prezzoBaseInput);
		pizzaDTO.setIngredienti(idIngredientiInput);
		pizzaDTO.setAttivo(attivoInput);

		List<String> pizzaErrors = pizzaDTO.errors();
		if (!pizzaErrors.isEmpty()) {
			request.setAttribute("pizzaAttr", pizzaDTO);
			request.setAttribute("pizzaErrors", pizzaErrors);
			request.setAttribute("listaIngredientiCheckedAttr", pizzaDTO.getIdIngredienti());
			request.setAttribute("ingredientiListAttr", ingredienteService.listAll());
			request.getRequestDispatcher("/pizzaiolo/ordini/modifica.jsp").forward(request, response);
			return;
		}

		Pizza pizzaInstance = PizzaDTO.buildModelFromDto(pizzaDTO);
		pizzaService.aggiornaConIngredienti(pizzaInstance);

		request.setAttribute("messaggioConferma", "Modifica avvenuta con successo");
		request.setAttribute("listaPizzeAttr", pizzaService.listAll());
		request.getRequestDispatcher("/pizzaiolo/ordini/result.jsp").forward(request, response);
	}

}
