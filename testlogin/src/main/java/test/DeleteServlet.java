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
 * Servlet implementation class DeleteServlet
 */
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteServlet() {
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

		String roles = request.getParameter("roles");
		String users = request.getParameter("users");
		System.out.println(roles);
		if (roles != null) {
			int row = 0;
			try {
				row = DatabaseManagerSingleton.getInstance().deleteRoles(Integer.parseInt(roles));
			} catch (NumberFormatException | ClassNotFoundException | SQLException | IOException e) {
				response.getWriter().append("<p>Non sono riuscito a eliminare il roles</p>");
				response.getWriter().append("<p>" + e.getMessage() + "</p>");
				response.getWriter().append("<a href='welcome.html'>Home page</a>");
				e.printStackTrace();
			}
			if (row > 0) {
				response.getWriter().append("<p>Roles eliminato</p>");
				response.getWriter().append("<a href='welcome.html'>Home page</a>");
			}
		}

		else if (users != null) {
			int row = 0;
			try {
				row = DatabaseManagerSingleton.getInstance().deleteUsers(Integer.parseInt(users));
			} catch (NumberFormatException | ClassNotFoundException | SQLException | IOException e) {
				response.getWriter().append("<p>Non sono riuscito a eliminare l'user</p>");
				response.getWriter().append("<p>" + e.getMessage() + "</p>");
				response.getWriter().append("<a href='welcome.html'>Home page</a>");
				e.printStackTrace();
			}
			if (row > 0) {
				response.getWriter().append("<p>User eliminato</p>");
				response.getWriter().append("<a href='welcome.html'>Home page</a>");
			}
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
