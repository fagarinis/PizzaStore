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

import it.pizzastore.model.Cliente;
import it.pizzastore.service.ClienteService;

/**
 * Servlet implementation class PrepareEliminaMunicipioServlet
 */
@WebServlet("/pizzaiolo/clienti/PrepareEliminaClienteServlet")
public class PrepareEliminaClienteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private ClienteService clienteService;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PrepareEliminaClienteServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Long id = Long.parseLong(request.getParameter("idCliente"));

		Cliente clienteDaCancellare = clienteService.caricaSingolo(id);

		request.setAttribute("clienteAttr", clienteDaCancellare);
		request.getRequestDispatcher("/pizzaiolo/clienti/delete.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
