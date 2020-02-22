package it.pizzastore.web.servlet.ingrediente;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.pizzastore.dto.IngredienteDTO;

@WebServlet("/pizzaiolo/ingredienti/PrepareInsertIngredienteServlet")
public class PrepareInsertIngredienteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PrepareInsertIngredienteServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setAttribute("ingredienteAttribute", new IngredienteDTO());
		request.getRequestDispatcher("/pizzaiolo/ingredienti/insert.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
