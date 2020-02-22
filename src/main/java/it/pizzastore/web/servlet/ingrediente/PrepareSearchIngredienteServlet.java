package it.pizzastore.web.servlet.ingrediente;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/pizzaiolo/ingredienti/PrepareSearchIngredienteServlet")
public class PrepareSearchIngredienteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PrepareSearchIngredienteServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/pizzaiolo/ingredienti/search.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
