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

import it.pizzastore.dto.OrdineDTO;
import it.pizzastore.model.Ordine;
import it.pizzastore.service.ClienteService;
import it.pizzastore.service.OrdineService;
import it.pizzastore.service.PizzaService;
import it.pizzastore.service.UtenteService;

@WebServlet("/pizzaiolo/ordini/ExecuteInsertOrdineServlet")
public class ExecuteInsertOrdineServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Autowired
	private PizzaService pizzaService;

	@Autowired
	private OrdineService ordineService;

	@Autowired
	private ClienteService clienteService;

	@Autowired
	private UtenteService utenteService;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	public ExecuteInsertOrdineServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// binding
		String codiceInput = request.getParameter("codiceInput");
		String dataInput = request.getParameter("dataInput");
		String clienteId = request.getParameter("clienteId");
		String fattorinoId = request.getParameter("fattorinoId");
		String[] idPizzeInput = request.getParameterValues("pizzaInput");
		String[] numeroPizzeInput = request.getParameterValues("numeroPizza");

		OrdineDTO ordineDTO = new OrdineDTO();
		ordineDTO.setCodice(codiceInput);
		ordineDTO.setData(dataInput);
		ordineDTO.setCliente(clienteId);
		ordineDTO.setUtente(fattorinoId);
		ordineDTO.setPizze(idPizzeInput, numeroPizzeInput);

		ordineDTO.setClosed(false);

		// effettuo la validazione dell'input e se non va bene rimando in pagina
		List<String> ordineErrors = ordineDTO.errors();
		if (!ordineErrors.isEmpty()) {
			if (ordineDTO.getCliente() != null) {
				ordineDTO.setCliente(clienteService.caricaSingolo(ordineDTO.getCliente().getId()));
			}
			if (ordineDTO.getUtente() != null) {
				ordineDTO.setUtente(utenteService.caricaSingolo(ordineDTO.getUtente().getId()));
			}

			request.setAttribute("ordineAttr", ordineDTO);
			request.setAttribute("ordineErrors", ordineErrors);
			request.setAttribute("pizzeListAttr", pizzaService.listAllActiveEager());
			request.setAttribute("numeroPizzeInput", numeroPizzeInput);
			request.getRequestDispatcher("/pizzaiolo/ordini/insert.jsp").forward(request, response);
			return;
		}

		// se arrivo qui significa che va bene
		Ordine ordineInstance = OrdineDTO.buildModelFromDto(ordineDTO);
		ordineService.inserisciNuovo(ordineInstance);

		// vado in pagina con ok
		request.setAttribute("messaggioConferma", "Inserimento avvenuto con successo");
		request.setAttribute("listaOrdiniAttr", ordineService.listAll());
		request.getRequestDispatcher("/pizzaiolo/ordini/result.jsp").forward(request, response);
	}

}
