package main.java;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.DBConnection;
import db.Events;

/**
 * Servlet implementation class SaveData
 */
@WebServlet("/savedata")
public class SaveData extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DBConnection db;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaveData() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		db = new DBConnection();
		db.getConnectionByProperties(getServletContext());
		
		Events event = new Events();
		event.setUToken(request.getParameter("utoken")); 
		event.setTitle(request.getParameter("title"));
		event.setURL(request.getParameter("url"));
        event.setDate(request.getParameter("date"));
        
        db.insertNewEvent(event);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
