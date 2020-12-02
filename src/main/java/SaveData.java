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
import db.Recurring;

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
		db.setConnectionByProperties(getServletContext());
		
		//these all should have some value
		String utoken = request.getParameter("utoken");
		
		if (utoken == null || utoken.equals("NULL")) {
			return;
		}
		
		String title = request.getParameter("title");
		String url = request.getParameter("url");
		String date = request.getParameter("date");
		//these might not have a value
        String recurringInput = request.getParameter("recurring");
        String[] recurrInfo = recurringInput.split("z");
        
		Events event = new Events();

        if(recurrInfo.length == 2) {
        	Recurring recur = new Recurring();
        	recur.setUToken(utoken);
        	recur.setDays(recurrInfo[0]);
        	recur.setEndDate(recurrInfo[1]);
            db.insertNewRecurring(recur);
            event.setRecurringID(db.getRecurringEventId(recur));
        }
        db = new DBConnection();
		db.setConnectionByProperties(getServletContext());
		
		event.setUToken(utoken); 
		event.setTitle(title);
		event.setURL(url);
        event.setDate(date);

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
