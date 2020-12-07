package main.java;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.DBConnection;
import db.Events;
import db.Recurring;

/**
 * Servlet implementation class ShareCodeInput
 */
@WebServlet("/sharecodeinput")
public class ShareCodeInput extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DBConnection db;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShareCodeInput() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		db = new DBConnection();
		db.setConnectionByProperties(getServletContext());

		String shareId = request.getParameter("shareId");
		String userId = request.getParameter("userId");
		
		if (!userId.equals("null")) {
			Events event = db.getEventFromShareId(shareId);
			event.setUToken(userId);
			
			if (!event.getRecurringID().equals("NULL")) {
				Recurring recur = db.getRecurringFromRecurringId(event.getRecurringID());
				recur.setUToken(userId);
				event.setRecurringID(db.getRecurringEventId(recur));

				db.insertNewRecurring(recur);
			}
			
			db.insertNewEvent(event);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
