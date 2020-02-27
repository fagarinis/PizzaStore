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

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import it.pizzastore.model.Cliente;
import it.pizzastore.service.ClienteService;

@WebServlet("/SearchClienteAjaxServlet")
public class SearchClienteAjaxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Autowired
	private ClienteService clienteService;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	public SearchClienteAjaxServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		String term = request.getParameter("term");

		List<Cliente> listaClientiByTerm = clienteService.cercaByNomeCompletoLike(term);
		System.out.println(listaClientiByTerm);
		String json = buildJsonResponse(listaClientiByTerm);
		response.getWriter().write(json);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	private String buildJsonResponse(List<Cliente> listaClienti) {
		JsonArray ja = new JsonArray();
		for (Cliente clienteElement : listaClienti) {
			JsonObject jo = new JsonObject();
			jo.addProperty("value", clienteElement.getId());
			jo.addProperty("label", clienteElement.getCognome());
			jo.addProperty("name", clienteElement.getNome());
			ja.add(jo);
		}
		
		return new Gson().toJson(ja);
	}

}
