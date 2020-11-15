package db;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class test
 */
@WebServlet("/test")
public class test extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public test() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DBConnection.getDBConnection(getServletContext());
		Connection connection = DBConnection.connection;
		PreparedStatement ps = null;
		try {
			String select = "SELECT * FROM recurring";
			ps = connection.prepareStatement(select);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				System.out.println(id);
				String utoken = rs.getString("utoken");
				System.out.println(utoken);
				String date = rs.getTimestamp("date").toString();
				System.out.println(date);
				String rcid = rs.getString("recurringID");
				System.out.println(rcid);
			}
			rs.close();
			ps.close();
			connection.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
