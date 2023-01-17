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
 * Servlet implementation class InsertRoleServlet
 */
public class InsertRoleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InsertRoleServlet() {
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
		String label = "";
		String description = "";
		int level = 0;

		label = request.getParameter("label");
		description = request.getParameter("label");
		level = Integer.parseInt(request.getParameter("level"));

		int row = 0;
		try {
			row = DatabaseManagerSingleton.getInstance().insertRole(label, description, level);
		} catch (ClassNotFoundException | SQLException | IOException e) {
			response.getWriter().append("<p>Role non inserito</p>");
			response.getWriter().append("<p>" + e.getMessage() + "</p>");
			response.getWriter().append("<a href='welcome.html'>Home page</a>");
			e.printStackTrace();
		}

		if (row > 0) {
			response.getWriter().append("<p>Role inserito con successo</p>");
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
