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

/**
 * Servlet implementation class ExecuteSearchReportOrdineServlet
 */
@WebServlet("/admin/reportordini/ExecuteSearchReportOrdineServlet")
public class ExecuteSearchReportOrdineServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Autowired
	private OrdineService ordineService;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ExecuteSearchReportOrdineServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String dataInput = request.getParameter("dataInput");
		String clienteId = request.getParameter("clienteId");
		String pizzaId = request.getParameter("pizzaId");

		OrdineDTO example = new OrdineDTO();
		example.setData(dataInput);
		Boolean closed = null;
		example.setClosed(closed);

		Ordine exampleOrdine = OrdineDTO.buildModelFromDto(example);
		Long idCliente = null;
		Long idPizza = null;

		try {
			idCliente = Long.parseLong(clienteId);
		} catch (Exception e) {
		}
		try {
			idPizza = Long.parseLong(pizzaId);
		} catch (Exception e) {
		}

		request.setAttribute("listaOrdiniAttr", ordineService.cercaDaDataEIdPizzaEIdCliente(exampleOrdine.getSimpleData(),idPizza, idCliente));
		request.getRequestDispatcher("/admin/reportordini/result.jsp").forward(request, response);
	}

}
