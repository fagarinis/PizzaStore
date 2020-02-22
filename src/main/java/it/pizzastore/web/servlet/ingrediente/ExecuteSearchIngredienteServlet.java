package it.pizzastore.web.servlet.ingrediente;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import it.pizzastore.model.Ingrediente;
import it.pizzastore.service.IngredienteService;

@WebServlet("/pizzaiolo/ingredienti/ExecuteSearchIngredienteServlet")
public class ExecuteSearchIngredienteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Autowired
	private IngredienteService ingredienteService;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	public ExecuteSearchIngredienteServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String codiceInput = request.getParameter("codiceInput");
		String descrizioneInput = request.getParameter("descrizioneInput");
		String prezzoInput = request.getParameter("prezzoInput");

		request.setAttribute("listaIngredientiAttr",
				ingredienteService.findByExample(new Ingrediente(descrizioneInput, codiceInput, prezzoInput)));

		RequestDispatcher rd = request.getRequestDispatcher("/pizzaiolo/ingredienti/result.jsp");
		rd.forward(request, response);
	}

}
