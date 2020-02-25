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

import it.pizzastore.dto.OrdineDTO;
import it.pizzastore.model.Ordine;
import it.pizzastore.service.OrdineService;

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

		String codiceInput = request.getParameter("codiceInput");
		String dataInput = request.getParameter("dataInput");
		String costoTotaleInput = request.getParameter("costoTotaleInput");
		String chiusoInput = request.getParameter("chiusoInput");

		OrdineDTO example = new OrdineDTO();
		example.setCodice(codiceInput);
		example.setData(dataInput);
		example.setCostoTotale(costoTotaleInput);
		example.setClosed(chiusoInput);

		Ordine exampleOrdine = OrdineDTO.buildModelFromDto(example);

		request.setAttribute("listaOrdiniAttr", ordineService.findByExampleOrderByData(exampleOrdine));
		request.getRequestDispatcher("/pizzaiolo/ordini/result.jsp").forward(request, response);
	}

}
