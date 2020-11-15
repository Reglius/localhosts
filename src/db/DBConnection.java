package db;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DBConnection")
public class DBConnection extends HttpServlet {
	static Connection connection = null;
	static ServletContext servletContext;
	
	static void getDBConnection() {
		connection = null;
		try {
			UtilProp.loadProperty(servletContext);
			connection = DriverManager.getConnection(getURL(), getUserName(), getPassword());
		} catch(Exception e) {
			e.printStackTrace();
			return;
		}
	}
	
	static String getURL() {
		String url = UtilProp.getProp("url");
		return url;
	}
	
	static String getUserName() {
		String user = UtilProp.getProp("user");
		return user;
	}
	
	static String getPassword() {
		String password = UtilProp.getProp("password");
		return password;
	}

	static void getDBConnection(ServletContext context) {
		servletContext = context;
		getDBConnection();
	}
	
}
