package it.pizzastore.web.servlet.cliente;

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

import it.pizzastore.dto.ClienteDTO;
import it.pizzastore.dto.PizzaDTO;
import it.pizzastore.model.Cliente;
import it.pizzastore.model.Pizza;
import it.pizzastore.service.ClienteService;
import it.pizzastore.service.IngredienteService;
import it.pizzastore.service.PizzaService;

/**
 * Servlet implementation class ExecuteModificaMunicipioServlet
 */
@WebServlet("/pizzaiolo/clienti/ExecuteModificaClienteServlet")
public class ExecuteModificaClienteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Autowired
	private ClienteService clienteService;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	public ExecuteModificaClienteServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// binding
		String idInput = request.getParameter("idInput");
		String nomeInput = request.getParameter("nomeInput");
		String cognomeInput = request.getParameter("cognomeInput");
		String viaInput = request.getParameter("viaInput");
		String civicoInput = request.getParameter("civicoInput");
		String cittaInput = request.getParameter("cittaInput");
		String telefonoInput = request.getParameter("telefonoInput");

		ClienteDTO clienteDTO = new ClienteDTO();
		clienteDTO.setId(Long.parseLong(idInput));
		clienteDTO.setNome(nomeInput);
		clienteDTO.setCognome(cognomeInput);
		clienteDTO.setVia(viaInput);
		clienteDTO.setCivico(civicoInput);
		clienteDTO.setCitta(cittaInput);
		clienteDTO.setTelefono(telefonoInput);
		clienteDTO.setAttivo(true);

		List<String> clienteErrors = clienteDTO.errors();
		if (!clienteErrors.isEmpty()) {
			request.setAttribute("clienteAttr", clienteDTO);
			request.setAttribute("clienteErrors", clienteErrors);
			request.getRequestDispatcher("/pizzaiolo/clienti/modifica.jsp").forward(request, response);
			return;
		}

		Cliente clienteInstance = ClienteDTO.buildModelFromDto(clienteDTO);
		clienteService.aggiorna(clienteInstance);

		request.setAttribute("messaggioConferma", "Modifica avvenuta con successo");
		request.setAttribute("listaClientiAttr", clienteService.listAllActive());
		request.getRequestDispatcher("/pizzaiolo/clienti/result.jsp").forward(request, response);
	}

}
