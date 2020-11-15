package db;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DBConnection")
public class DBConnection extends HttpServlet {
	
	static Connection connection = null;
	
	public DBConnection(ServletContext context) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			UtilProp.loadProperty(context);
			connection = DriverManager.getConnection(getURL(), getUserName(), getPassword());
		
		} catch(Exception e) {
			e.printStackTrace();
			return;
		}
	}
	
	private String getURL() {
		return UtilProp.getProp("url");
	}
	
	private String getUserName() {
		return UtilProp.getProp("user");
	}
	
	private String getPassword() {
		return UtilProp.getProp("password");
	}
	
	public Connection getConnection() {
		return connection;
	}
	
	public List<Events> getAllEventsForUser(String user){
		List<Events> ret = new ArrayList<>();
		PreparedStatement ps = null;
		
		try {
			String select = "SELECT * FROM Events where utoken = '" + user +"'";
			ps = connection.prepareStatement(select);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Events event = new Events();
				
				
				event.setId(rs.getInt("id"));	
				event.setUToken(rs.getString("utoken"));
				event.setDate(rs.getTimestamp("date").toString());
				event.setRecurringID(rs.getString("recurringID"));
				
				ret.add(event);
			}
			rs.close();
			ps.close();
			connection.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}
}
