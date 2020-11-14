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
		return UtilProp.getProp("url");
	}
	
	static String getUserName() {
		return UtilProp.getProp("user");
	}
	
	static String getPassword() {
		return UtilProp.getProp("password");
	}
}
