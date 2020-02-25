package it.pizzastore.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.pizzastore.model.Utente;

@WebFilter(filterName = "CheckAuthFilter", urlPatterns = { "/*" })
public class CheckAuthFilter implements Filter {

	private static final String HOME_PATH = "";
	private static final String[] EXCLUDED_URLS = { "/registrati.jsp","/login.jsp","/ExecuteRegistrazioneUtenteServlet", "/LoginServlet", "/LogoutServlet", "/css/", "/js/" };
	private static final String[] PROTECTED_URLS = { "/admin/" };
	private static final String[] PROTECTED_URLS_PIZZAIOLO = { "/pizzaiolo/" };
	private static final String[] PROTECTED_URLS_FATTORINO = { "/fattorino/" };

	public CheckAuthFilter() {
	}

	public void init(FilterConfig filterConfig) throws ServletException {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		// prendo il path della request che sta passando in questo momento
		String pathAttuale = httpRequest.getServletPath();

		// vediamo se il path risulta tra quelli 'liberi di passare'
		boolean isInWhiteList = isPathInWhiteList(pathAttuale);

		// se non lo e' bisogna controllare sia sessione che percorsi protetti
		if (!isInWhiteList) {
			Utente utenteInSession = (Utente) httpRequest.getSession().getAttribute("userInfo");
			// intanto verifico se utente in sessione
			if (utenteInSession == null) {
				httpResponse.sendRedirect(httpRequest.getContextPath());
				return;
			}
			// controllo che utente abbia ruolo admin se nel path risulta presente /admin/
			if (isPathForOnlyAdministrators(pathAttuale) && !utenteInSession.isAdmin()) {
				httpRequest.setAttribute("messaggio", "Non si è autorizzati alla navigazione richiesta");
				httpRequest.getRequestDispatcher("/home.jsp").forward(httpRequest, httpResponse);
				return;
			}
			// controllo che utente abbia ruolo pizzaiolo (o admin, l'ho inserito per
			// permettergli di usare ExecuteDettaglioOrdineServlet
			// da report ordini) se nel path risulta presente
			// /pizzaiolo/
			if (isPathForOnlyPizzaiolo(pathAttuale) && !utenteInSession.isPizzaiolo() && !utenteInSession.isAdmin()) {
				httpRequest.setAttribute("messaggio", "Non si è autorizzati alla navigazione richiesta");
				httpRequest.getRequestDispatcher("/home.jsp").forward(httpRequest, httpResponse);
				return;
			}
			// controllo che utente abbia ruolo admin se nel path risulta presente
			// /fattorino/
			if (isPathForOnlyFattorino(pathAttuale) && !utenteInSession.isFattorino()) {
				httpRequest.setAttribute("messaggio", "Non si è autorizzati alla navigazione richiesta");
				httpRequest.getRequestDispatcher("/home.jsp").forward(httpRequest, httpResponse);
				return;
			}
		}

		chain.doFilter(request, response);
	}

	private boolean isPathInWhiteList(String requestPath) {
		// bisogna controllare che se il path risulta proprio "" oppure se
		// siamo in presenza un url 'libero'
		if (requestPath.equals(HOME_PATH))
			return true;

		for (String urlPatternItem : EXCLUDED_URLS) {
			if (requestPath.contains(urlPatternItem)) {
				return true;
			}
		}
		return false;
	}

	private boolean isPathForOnlyAdministrators(String requestPath) {
		for (String urlPatternItem : PROTECTED_URLS) {
			if (requestPath.contains(urlPatternItem)) {
				return true;
			}
		}
		return false;
	}

	private boolean isPathForOnlyPizzaiolo(String requestPath) {
		for (String urlPatternItem : PROTECTED_URLS_PIZZAIOLO) {
			if (requestPath.contains(urlPatternItem)) {
				return true;
			}
		}
		return false;
	}

	private boolean isPathForOnlyFattorino(String requestPath) {
		for (String urlPatternItem : PROTECTED_URLS_FATTORINO) {
			if (requestPath.contains(urlPatternItem)) {
				return true;
			}
		}
		return false;
	}

	public void destroy() {
	}

}
