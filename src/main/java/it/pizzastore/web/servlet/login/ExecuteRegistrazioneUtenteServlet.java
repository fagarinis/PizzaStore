package it.pizzastore.web.servlet.login;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import it.pizzastore.dto.UtenteDTO;
import it.pizzastore.model.Utente;
import it.pizzastore.service.UtenteService;

/**
 * Servlet implementation class ExecuteRegistrazioneUtenteServlet
 */
@WebServlet("/ExecuteRegistrazioneUtenteServlet")
public class ExecuteRegistrazioneUtenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Autowired
	private UtenteService utenteService;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ExecuteRegistrazioneUtenteServlet() {
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
		// binding
		String nomeInput = request.getParameter("nomeInput");
		String cognomeInput = request.getParameter("cognomeInput");
		String usernameInput = request.getParameter("usernameInput");
		String passwordInput = request.getParameter("passwordInput");

		UtenteDTO utenteDTO = new UtenteDTO();
		utenteDTO.setNome(nomeInput);
		utenteDTO.setCognome(cognomeInput);
		utenteDTO.setUsername(usernameInput);
		utenteDTO.setPassword(passwordInput);
		utenteDTO.setDataRegistrazione(new Date());

		// verifica se ci sono errori, in caso ritorna indietro
		List<String> utenteErrors = utenteDTO.errors();
		Utente utenteConStessoUsername = utenteService.cercaDaUsername(utenteDTO.getUsername());
		if (utenteConStessoUsername != null) {
			utenteErrors.add("username non disponibile");
		}

		if (!utenteErrors.isEmpty()) {
			request.setAttribute("utenteAttr", utenteDTO);
			request.setAttribute("utenteErrors", utenteErrors);
			request.getRequestDispatcher("/registrati.jsp").forward(request, response);
			return;
		}

		// inserisco nel DB
		Utente utenteInstance = UtenteDTO.buildModelFromDto(utenteDTO);

		utenteService.inserisciNuovo(utenteInstance);

		// vado in pagina con OK
		request.setAttribute("messaggioConferma", "Registrazione avvenuta con successo, contattare l'Admin per attivare il tuo account");
		request.getRequestDispatcher("/login.jsp").forward(request, response);
	}

}
