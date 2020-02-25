package it.pizzastore.web.servlet.ordine;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import it.pizzastore.model.Utente;
import it.pizzastore.service.OrdineService;

/**
 * Servlet implementation class ExecuteChiudiOrdineServlet
 */
@WebServlet("/ExecuteChiudiOrdineServlet")
public class ExecuteChiudiOrdineServlet extends HttpServlet {
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
	public ExecuteChiudiOrdineServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Utente utenteInSessione = (Utente) session.getAttribute("userInfo");

		Long idOrdine = Long.parseLong(request.getParameter("idOrdine"));

		if (utenteInSessione.isFattorino()) {
			ordineService.chiudiOrdine(idOrdine);

			// prepara la lista ordini dell'utente fattorino
			request.setAttribute("listaOrdiniAttr",
					ordineService.listAllOrdiniAttiviUtenteOrdinaPerData(utenteInSessione.getId()));
		}

		RequestDispatcher rd = request.getRequestDispatcher("home.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
