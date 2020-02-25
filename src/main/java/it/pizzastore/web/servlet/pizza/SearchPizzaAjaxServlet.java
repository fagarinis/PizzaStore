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

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import it.pizzastore.model.Pizza;
import it.pizzastore.service.PizzaService;

@WebServlet("/SearchPizzaAjaxServlet")
public class SearchPizzaAjaxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Autowired
	private PizzaService pizzaService;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	public SearchPizzaAjaxServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		String term = request.getParameter("term");

		List<Pizza> listaPizzaByTerm = pizzaService.cercaByDescrizioneLike(term);
		String json = buildJsonResponse(listaPizzaByTerm);
		response.getWriter().write(json);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	private String buildJsonResponse(List<Pizza> listaPizze) {
		JsonArray ja = new JsonArray();
		for (Pizza pizzaElement : listaPizze) {
			JsonObject jo = new JsonObject();
			jo.addProperty("value", pizzaElement.getId());
			jo.addProperty("label", pizzaElement.getDescrizione());
			ja.add(jo);
		}
		
		return new Gson().toJson(ja);
	}

}
