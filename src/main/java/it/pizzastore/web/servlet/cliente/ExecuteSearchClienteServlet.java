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

@WebServlet("/pizzaiolo/clienti/ExecuteSearchClienteServlet")
public class ExecuteSearchClienteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Autowired
	private ClienteService clienteService;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	public ExecuteSearchClienteServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String nomeInput = request.getParameter("nomeInput");
		String cognomeInput = request.getParameter("cognomeInput");
		String viaInput = request.getParameter("viaInput");
		String civicoInput = request.getParameter("civicoInput");
		String cittaInput = request.getParameter("cittaInput");
		String telefonoInput = request.getParameter("telefonoInput");

		Cliente example = new Cliente();
		example.setNome(nomeInput);
		example.setCognome(cognomeInput);
		example.setVia(viaInput);
		example.setCivico(civicoInput);
		example.setCitta(cittaInput);
		example.setTelefono(telefonoInput);
		
		//questo per far apparire in ricerca solo i clienti non eliminati logicamente
		example.setAttivo(true);
		
		request.setAttribute("listaClientiAttr", clienteService.findByExample(example));
		request.getRequestDispatcher("/pizzaiolo/clienti/result.jsp").forward(request, response);
	}

}
