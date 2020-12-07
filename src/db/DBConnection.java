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
	
	public DBConnection() {
	}
		
	public void setConnection(String url, String user, String password) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			connection = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setConnectionByProperties(ServletContext context) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			UtilProp.loadProperty(context);
			connection = DriverManager.getConnection(UtilProp.getProp("url"), UtilProp.getProp("user"), UtilProp.getProp("password"));
		
		} catch(Exception e) {
			e.printStackTrace();
			return;
		}
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
				event.setTitle(rs.getString("title"));
				event.setURL(rs.getString("url"));
				
				ret.add(event);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}
	
	public List<Recurring> getAllRecurringForUser(String user){
		List<Recurring> ret = new ArrayList<>();
		PreparedStatement ps = null;
		
		try {
			String select = "SELECT * FROM recurring where utoken = '" + user +"'";
			ps = connection.prepareStatement(select);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Recurring rec = new Recurring();
				rec.setRecurId(rs.getInt("recurringID"));
				rec.setUToken(rs.getString("utoken"));
				rec.setDays(rs.getString("days"));
				rec.setEndDate(rs.getString("endDate"));
				
				ret.add(rec);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}
	
	public boolean insertNewEvent(Events event){
		PreparedStatement ps = null;
		boolean ret = false;
		try {
			String select = "";
			if (event.getRecurringID() == null) {
				select = String.format("INSERT INTO Events (utoken, date, title, url) VALUES (\'%s\', \'%s\', \'%s\', \'%s\');", 
						event.getUToken(), event.getDate(), event.getTitle(), event.getURL());
			} else {
			select = String.format("INSERT INTO Events (utoken, date, title, url, recurringID) VALUES (\'%s\', \'%s\', \'%s\', \'%s\', \'%s\');", 
					event.getUToken(), event.getDate(), event.getTitle(), event.getURL(), event.getRecurringID());
			}
			
			ps = connection.prepareStatement(select);
			ret = ps.execute();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}
	
	public String getRecurringEventId(Recurring in){
		PreparedStatement ps = null;
		
		try {
			String select = "SELECT * FROM recurring where utoken = '" + in.getUToken() 
				+ "' && endDate = '" + in.getEndDate() 
				+ "' && days = '" + in.getDays() + "'";
			
			ps = connection.prepareStatement(select);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				return rs.getString("recurringID");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return "-1";
	}
	
	public boolean insertNewRecurring(Recurring rec){
		PreparedStatement ps = null;
		boolean ret = false;
		try {
			String select = String.format("INSERT INTO recurring (utoken, endDate, days) VALUES (\'%s\', \'%s\', \'%s\');", 
					rec.getUToken(), rec.getEndDate(), rec.getDays());
			ps = connection.prepareStatement(select);
			ret = ps.execute();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}
	
	public Events getEventFromShareId(String id) {
		PreparedStatement ps = null;
				
		try {
			String select = "SELECT * FROM Events where id = '" + id + "'";
			
			ps = connection.prepareStatement(select);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				Events event = new Events();
				
				event.setId(rs.getInt("id"));	
				event.setUToken(rs.getString("utoken"));
				event.setDate(rs.getTimestamp("date").toString());
				event.setRecurringID(rs.getString("recurringID"));
				event.setTitle(rs.getString("title"));
				event.setURL(rs.getString("url"));
				
				return event;
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Recurring getRecurringFromRecurringId(String id) {
		PreparedStatement ps = null;
				
		try {
			String select = "SELECT * FROM recurring where recurringId = '" + id + "'";
			
			ps = connection.prepareStatement(select);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				Recurring rec = new Recurring();
				
				rec.setRecurId(rs.getInt("recurringID"));
				rec.setUToken(rs.getString("utoken"));
				rec.setDays(rs.getString("days"));
				rec.setEndDate(rs.getString("endDate"));
				
				return rec;
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean deleteEventById(String id) {
		PreparedStatement ps = null;
				
		try {
			String select = "delete from Events where id = '" + id + "'";
			
			ps = connection.prepareStatement(select);
			return ps.execute();
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean deleteRecurringById(String id) {
		PreparedStatement ps = null;
				
		try {
			String select = "delete from recurring where recurringID = '" + id + "'";
			
			ps = connection.prepareStatement(select);
			return ps.execute();
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}