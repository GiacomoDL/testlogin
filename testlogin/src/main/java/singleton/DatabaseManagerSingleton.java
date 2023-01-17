package singleton;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseManagerSingleton {

	private DatabaseManagerSingleton() {
	}

	private static DatabaseManagerSingleton instance;

	public static DatabaseManagerSingleton getInstance() {
		if (instance == null) {
			instance = new DatabaseManagerSingleton();
		}
		return instance;
	}
	
	public String getAllRoles() throws ClassNotFoundException, IOException, SQLException {

		StringBuilder result = new StringBuilder();

		Connection con = dbConnection();

		PreparedStatement query = con.prepareStatement("SELECT * FROM roles_giacomodellaluna");
		ResultSet rs = query.executeQuery();

		result.append("<html><body>");
		result.append("<form action=./DeleteServlet>");
		result.append("<table><tr>");
		result.append("<th></th>");
		result.append("<th>id</th>");
		result.append("<th>label</th>");
		result.append("<th>description</th>");
		result.append("<th>level</th>");
		result.append("</tr>");

		while (rs.next()) {
			result.append("<tr>");
			result.append("<td>");
			result.append("<input type='radio' id='id' name='roles' value='" + rs.getString(1) + "'>");
			result.append("</td>");
			result.append("<td>");
			result.append(rs.getString(1));
			result.append("</td>");
			result.append("<td>");
			result.append(rs.getString(2));
			result.append("</td>");
			result.append("<td>");
			result.append(rs.getString(3));
			result.append("</td>");
			result.append("<td>");
			result.append(rs.getString(4));
			result.append("</td>");
			result.append("</tr>");

		}
		result.append("</table>");
		result.append("<input type='submit' value='Delete'>");
		result.append("</table></form></body></html>");
		return result.toString();

	}
	
	public String getAllUsers() throws ClassNotFoundException, IOException, SQLException {

		StringBuilder result = new StringBuilder();

		Connection con = dbConnection();

		PreparedStatement query = con.prepareStatement("SELECT * FROM users_giacomodellaluna");
		ResultSet rs = query.executeQuery();

		result.append("<html><body>");
		result.append("<form action=./DeleteServlet>");
		result.append("<table><tr>");
		result.append("<th></th>");
		result.append("<th>id</th>");
		result.append("<th>email</th>");
		result.append("<th>firstname</th>");
		result.append("<th>surname</th>");
		result.append("<th>lastname</th>");
		result.append("<th>birthdate</th>");
		result.append("<th>registrationdate</th>");
		result.append("<th>imgpath</th>");
		result.append("<th>role</th>");
		result.append("<th>hrnote</th>");
		result.append("<th>technicalnote</th>");
		result.append("<th>enable</th>");
		result.append("</tr>");

		while (rs.next()) {
			result.append("<tr>");
			result.append("<td>");
			result.append("<input type='radio' id='id' name='users' value='" + rs.getString(1) + "'>");
			result.append("</td>");
			result.append("<td>");
			result.append(rs.getString(1));
			result.append("</td>");
			result.append("<td>");
			result.append(rs.getString(2));
			result.append("</td>");
			result.append("<td>");
			result.append(rs.getString(3));
			result.append("</td>");
			result.append("<td>");
			result.append(rs.getString(4));
			result.append("</td>");
			result.append("<td>");
			result.append(rs.getString(5));
			result.append("</td>");
			result.append("<td>");
			result.append(rs.getString(6));
			result.append("</td>");
			result.append("<td>");
			result.append(rs.getString(7));
			result.append("</td>");
			result.append("<td>");
			result.append(rs.getString(8));
			result.append("</td>");
			result.append("<td>");
			result.append(rs.getString(9));
			result.append("</td>");
			result.append("<td>");
			result.append(rs.getString(10));
			result.append("</td>");
			result.append("<td>");
			result.append(rs.getString(11));
			result.append("</td>");
			result.append("<td>");
			result.append(rs.getString(12));
			result.append("</td>");
			result.append("</tr>");

		}
		result.append("</table>");
		result.append("<input type='submit' value='Delete'>");
		result.append("</table></form></body></html>");
		return result.toString();

	}
	
	public int insertUser(String firstname, String surname, String email, String birthdate, int role)
			throws ClassNotFoundException, SQLException, IOException {
		int row = 0;
		Connection con = dbConnection();
		PreparedStatement query = con.prepareStatement(
				"INSERT INTO users_giacomodellaluna (email, firstname, surname, birthdate, role) VALUES (?,?,?,?,?)");

		query.setString(1, email);
		query.setString(2, firstname);
		query.setString(3, surname);
		query.setString(4, birthdate);
		query.setInt(5, role);

		row = query.executeUpdate();
		
		return row;
	}
	
	public int insertRole(String label, String description, int level)
			throws ClassNotFoundException, SQLException, IOException {
		int row = 0;
		Connection con = dbConnection();
		PreparedStatement query = con.prepareStatement(
				"INSERT INTO roles_giacomodellaluna ( label, description, level) VALUES (?,?,?)");

		
		query.setString(2, label);
		query.setString(3, description);
		query.setInt(4, level);

		row = query.executeUpdate();
		
		return row;
	}

	public int deleteRoles(int id) throws ClassNotFoundException, SQLException, IOException {
		Connection con = dbConnection();

		PreparedStatement query = con.prepareStatement("DELETE FROM roles_giacomodellaluna WHERE id=" + id);
		int row = query.executeUpdate();
		return row;
	}

	public int deleteUsers(int id) throws ClassNotFoundException, SQLException, IOException {
		Connection con = dbConnection();

		PreparedStatement query = con.prepareStatement("DELETE FROM users_giacomodellaluna WHERE id=" + id);
		int row = query.executeUpdate();
		return row;
	}


	public boolean login(String email, String password) throws ClassNotFoundException, IOException, SQLException {
		Connection con = dbConnection();

		PreparedStatement query = con.prepareStatement("SELECT * FROM users WHERE email = ? AND password = ?");
		query.setString(1, email);
		query.setString(2, password);

		System.out.println(query);
		ResultSet rs = query.executeQuery();

		if (rs.next())
			return true;
		else
			return false;
	}

	private Connection dbConnection() throws IOException, ClassNotFoundException, SQLException {
		String driver = "org.mariadb.jdbc.Driver";
		Class.forName(driver);
		String host = "centauri.proximainformatica.com";
		String port = "193";
		String dbName = "academyfs07";
		String url = "jdbc:mariadb://" + host + ":" + port + "/" + dbName;

		String username = "acfs07";
		String password = "acfs07";
		Connection con = DriverManager.getConnection(url, username, password);
		return con;
	}

}
