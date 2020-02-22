package it.pizzastore.web.servlet.ingrediente;

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

import it.pizzastore.dto.IngredienteDTO;
import it.pizzastore.model.Ingrediente;
import it.pizzastore.service.IngredienteService;

/**
 * Servlet implementation class ExecuteModificaMunicipioServlet
 */
@WebServlet("/pizzaiolo/ingredienti/ExecuteModificaIngredienteServlet")
public class ExecuteModificaIngredienteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Autowired
	private IngredienteService ingredienteService;


	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	public ExecuteModificaIngredienteServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String idInput = request.getParameter("idInput");
		String codiceInput = request.getParameter("codiceInput");
		String descrizioneInput = request.getParameter("descrizioneInput");
		String prezzoInput = request.getParameter("prezzoInput");

		IngredienteDTO ingredienteDTO = new IngredienteDTO();
		ingredienteDTO.setId(Long.parseLong(idInput));
		ingredienteDTO.setCodice(codiceInput);
		ingredienteDTO.setDescrizione(descrizioneInput);
		ingredienteDTO.setPrezzo(prezzoInput);
		
		List<String> ingredienteErrors = ingredienteDTO.errors();
		if (!ingredienteErrors.isEmpty()) {
			request.setAttribute("ingredienteAttr", ingredienteDTO);
			request.setAttribute("ingredienteErrors", ingredienteErrors);
			request.getRequestDispatcher("/pizzaiolo/ingredienti/modifica.jsp").forward(request, response);
			return;
		}

		Ingrediente ingredienteInstance = IngredienteDTO.buildModelFromDto(ingredienteDTO);
		ingredienteService.aggiorna(ingredienteInstance);

		request.setAttribute("messaggioConferma", "Modifica avvenuta con successo");
		request.setAttribute("listaIngredientiAttr", ingredienteService.listAll());
		request.getRequestDispatcher("/pizzaiolo/ingredienti/result.jsp").forward(request, response);
	}

}
