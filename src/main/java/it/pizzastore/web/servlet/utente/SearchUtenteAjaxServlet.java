package it.pizzastore.web.servlet.utente;

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

import it.pizzastore.model.CodiceRuolo;
import it.pizzastore.model.Utente;
import it.pizzastore.service.UtenteService;

//servlet per la ricerca di tutti gli utenti fattorini
@WebServlet("/pizzaiolo/ordini/SearchUtenteAjaxServlet")
public class SearchUtenteAjaxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Autowired
	private UtenteService utenteService;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	public SearchUtenteAjaxServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		String term = request.getParameter("term");

		List<Utente> listaUtentiByTerm = utenteService
				.cercaUtentiByCodiceRuoloAndCognomeLike(CodiceRuolo.FATTORINO_ROLE, term);
		String json = buildJsonResponse(listaUtentiByTerm);
		response.getWriter().write(json);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	private String buildJsonResponse(List<Utente> listaUtenti) {
		JsonArray ja = new JsonArray();
		for (Utente utenteElement : listaUtenti) {
			JsonObject jo = new JsonObject();
			jo.addProperty("value", utenteElement.getId());
			jo.addProperty("label", utenteElement.getCognome());
			jo.addProperty("name", utenteElement.getNome());
			ja.add(jo);
		}

		return new Gson().toJson(ja);
	}

}
