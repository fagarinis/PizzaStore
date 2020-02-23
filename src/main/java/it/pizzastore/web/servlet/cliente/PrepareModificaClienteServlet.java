package it.pizzastore.web.servlet.cliente;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import it.pizzastore.dto.PizzaDTO;
import it.pizzastore.model.Cliente;
import it.pizzastore.model.Ingrediente;
import it.pizzastore.model.Pizza;
import it.pizzastore.service.ClienteService;
import it.pizzastore.service.IngredienteService;
import it.pizzastore.service.PizzaService;

/**
 * Servlet implementation class PrepareModificaMunicipioServlet
 */
@WebServlet("/pizzaiolo/clienti/PrepareModificaClienteServlet")
public class PrepareModificaClienteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Autowired
	private ClienteService clienteService;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	public PrepareModificaClienteServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idCliente = request.getParameter("idCliente");
		Cliente result = clienteService.caricaSingolo(Long.parseLong(idCliente));
		
		
		request.setAttribute("clienteAttr", result);
		request.getRequestDispatcher("/pizzaiolo/clienti/modifica.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
