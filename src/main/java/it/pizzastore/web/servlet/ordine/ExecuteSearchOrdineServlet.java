package it.pizzastore.web.servlet.ordine;

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
import it.pizzastore.service.OrdineService;
import it.pizzastore.service.PizzaService;

@WebServlet("/pizzaiolo/ordini/ExecuteSearchOrdineServlet")
public class ExecuteSearchOrdineServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Autowired
	private OrdineService ordineService;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	public ExecuteSearchOrdineServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String descrizioneInput = request.getParameter("descrizioneInput");
		String codiceInput = request.getParameter("codiceInput");
		String prezzoBaseInput = request.getParameter("prezzoBaseInput");
		String attivoInput = request.getParameter("attivoInput");

		PizzaDTO example = new PizzaDTO();
		example.setDescrizione(descrizioneInput);
		example.setCodice(codiceInput);
		example.setPrezzoBase(prezzoBaseInput);
		example.setAttivo(attivoInput);
		
		request.setAttribute("listaPizzeAttr", pizzaService.findByExample(PizzaDTO.buildModelFromDto(example)));
		request.getRequestDispatcher("/pizzaiolo/ordini/result.jsp").forward(request, response);
	}

}
