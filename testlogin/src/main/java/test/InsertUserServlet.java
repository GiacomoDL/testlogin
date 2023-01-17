package test;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import singleton.DatabaseManagerSingleton;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Servlet implementation class InsertUserServlet
 */
public class InsertUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InsertUserServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		int row = 0;
		String name = request.getParameter("name");
		String surname = request.getParameter("surname");
		String email = request.getParameter("email");
		String date = request.getParameter("date");
		int role = Integer.parseInt(request.getParameter("role"));

		try {
			row = DatabaseManagerSingleton.getInstance().insertUser(name, surname, email, date, role);
		} catch (ClassNotFoundException | SQLException | IOException e) {
			response.getWriter().append("<p>User non inserito</p>");
			response.getWriter().append("<p>" + e.getMessage() + "</p>");
			response.getWriter().append("<a href='welcome.html'>Home page</a>");
			e.printStackTrace();
		}

		if (row > 0) {
			response.getWriter().append("<p>User inserito con successo</p>");
			response.getWriter().append("<a href='welcome.html'>Home page</a>");
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
