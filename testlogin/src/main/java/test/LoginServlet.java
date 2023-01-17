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
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		boolean result = false;
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		System.out.println(email + " " + password);

		try {
			result = DatabaseManagerSingleton.getInstance().login(email, password);
		} catch (ClassNotFoundException | IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (result)
			request.getRequestDispatcher("welcome.html").forward(request, response);
		else
			response.getWriter().append("Login KO");

	}

}
